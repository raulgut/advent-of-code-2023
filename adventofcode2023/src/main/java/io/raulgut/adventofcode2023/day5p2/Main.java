package io.raulgut.adventofcode2023.day5p2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

// Advent of code - Day 5 part 2 in Java
// Argument: test.java.day2-1.txt
public class Main {

    //Call it only if st.hasNext()
    private static Mapping parseMappings (Iterator<String> st) {
        boolean end = false;
        SortedSet<MappingValues> mvSet = new TreeSet<>();

        String statement = st.next();
        String fromTo = statement.split(" ")[0];
        String[] fromToList = fromTo.split("-");
        while (st.hasNext() && !end) {
            String line = st.next();
            if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
                //We process the line
                String[] numberList = line.split("\s+");
                MappingValues mv = new MappingValues(Long.parseLong(numberList[0])
                        ,Long.parseLong(numberList[1])
                        ,Long.parseLong(numberList[2]));
                mvSet.add(mv);
            } else {
                end = true;
            }
        }
        return new Mapping(fromToList[0],fromToList[2],mvSet);
    }

    public static List<Long> process (SortedSet<Mapping> ms, String sc, long scValue, long scRange, String ds) {
        boolean mappingFound = false;
        Iterator<Mapping> mappingIterator = ms.iterator();
        while (mappingIterator.hasNext() && !mappingFound) {
            Mapping m = mappingIterator.next();
            if (m.getFrom().equals(sc)) {
                // We are in the right mapping
                return processMapping(m, ms, scValue, scRange, ds);
            }
        }
        return null;
    }

    public static List<Long> processMapping (Mapping m, SortedSet<Mapping> ms, long scValue, long scRange, String ds) {
        Iterator<MappingValues> mappingValuesIterator = m.getValues().iterator();
        long currentScRange = scRange;
        List<Long> results = new ArrayList<>();
        while (mappingValuesIterator.hasNext() && (currentScRange > 0)) {
            MappingValues mv = mappingValuesIterator.next();
            if (scValue < mv.getSource()) {
                long distance = mv.getSource() - scValue;
                if (m.getTo().equals(ds)) {
                    // We are in 'location'
                    results.add(scValue);
                    if (currentScRange > distance) {
                        // There is a part in the mapping
                        results.addAll(processMapping(m, ms, scValue + distance, currentScRange - distance, ds));
                    }
                } else {
                    if (currentScRange > distance) {
                        results.addAll(processMapping(m, ms, scValue + distance, currentScRange - distance, ds));
                        // We move to the next type the matching
                        results.addAll(process(ms, m.getTo(), scValue, currentScRange - distance, ds));
                    } else {
                        // We do not need to split
                        results.addAll(process(ms, m.getTo(), scValue, currentScRange, ds));
                    }
                }
                currentScRange = 0;
            } else if ((scValue >= mv.getSource()) && (scValue <= mv.getSource() + (mv.getLength() - 1))) {
                long mappingRange = mv.getLength() - (scValue - mv.getSource());
                if (m.getTo().equals(ds)) {
                    // We finished
                    results.add(mv.getDestination() + (scValue - mv.getSource()));
                    if (currentScRange > mappingRange) {
                        // There is a part in the mapping
                        results.addAll(processMapping(m, ms, scValue + mappingRange, currentScRange - mappingRange, ds));
                    }
                } else {
                    if (currentScRange > mappingRange) {
                        results.addAll(processMapping(m, ms, scValue + mappingRange, currentScRange - mappingRange, ds));
                        // We move to the next type the matching
                        results.addAll(process(ms, m.getTo(), mv.getDestination() + (scValue - mv.getSource()), mappingRange, ds));
                    } else {
                        // We do not need to split
                        results.addAll(process(ms, m.getTo(), mv.getDestination() + (scValue - mv.getSource()), currentScRange, ds));
                    }
                }
                currentScRange = 0;
            }
        }
        if (currentScRange > 0) {
            // We finished all mappings and there is still range
            if (m.getTo().equals(ds)) {
                // We are in 'location'
                results.add(scValue);
            } else {
                results.addAll(process(ms, m.getTo(), scValue, currentScRange, ds));
            }
        }
        return results;
    }
    public static void main(String[] args) {

        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                Iterator<String> it = stream.iterator();
                // Fist, we capture the seeds
                String seedLine = it.next();
                String seedsStr = seedLine.split(": ")[1];
                String[] seedList = seedsStr.split("\s+");
                SortedSet<Mapping> mappingSet = new TreeSet<>();

                long[] seeds = new long[seedList.length];
                for (int i=0; i < seedList.length; i++)
                    seeds[i] = Long.parseLong(seedList[i]);
                //enter
                it.next();
                while (it.hasNext()) {
                    mappingSet.add(parseMappings(it));
                }

                long lowest = 0;
                List<Long> result;
                for(int i=0; i < seeds.length; i+=2) {
                    result = process(mappingSet,"seed",seeds[i],seeds[i+1],"location");
                    if (i==0) {
                        for (int j=0; j< result.size();j++)
                            if (j==0)
                                lowest = result.get(j);
                            else if (result.get(j) < lowest)
                                lowest = result.get(j);
                    } else {
                        for (Long r : result)
                            if (r < lowest)
                              lowest = r;
                    }
                }
                System.out.println(lowest);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}