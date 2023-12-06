package io.raulgut.adventofcode2023.day5p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

// Advent of code - Day 5 part 1 in Java
// Argument: test.java.day5-1.txt
public class Main {

    //Call it only if st.hasnext()
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

    public static long process (SortedSet<Mapping> ms, String sc,long scValue, String ds) {
        boolean mappingFound = false;
        Iterator<Mapping> mappingIterator = ms.iterator();
        while (mappingIterator.hasNext() && !mappingFound) {
            Mapping m = mappingIterator.next();
            if (m.getFrom().equals(sc)) {
                boolean mappingValuesFound = false;
                Iterator<MappingValues> mappingValuesIterator = m.getValues().iterator();
                while (mappingValuesIterator.hasNext() && !mappingValuesFound) {
                    MappingValues mv = mappingValuesIterator.next();
                    if ((scValue >= mv.getSource()) && (scValue <= mv.getSource() + (mv.getLength() - 1))) {
                        if (m.getTo().equals(ds)) {
                            // We finished
                            return (mv.getDestination() + (scValue - mv.getSource()));
                        } else {
                            // We move to the next type
                            return process(ms, m.getTo(),mv.getDestination() + (scValue - mv.getSource()),ds);
                        }
                    }
                }
                if (!mappingValuesFound) {
                    // There is not special mapping
                    if (m.getTo().equals(ds)) {
                        // We finished
                        return (scValue);
                    } else {
                        // We move to the next type
                        return process(ms, m.getTo(),scValue,ds);
                    }
                }

            }
        }
        return -1;
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

                long lowest = 0, result;
                for(int i=0; i < seeds.length; i++) {
                    result = process(mappingSet,"seed",seeds[i],"location");
                    if (i==0) {
                        lowest = result;
                    } else if (result < lowest) {
                        lowest = result;
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