package io.raulgut.adventofcode2023.day6p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.List;

// Advent of code - Day 6 part 1 in Java
// Argument: test.java.day6-1.txt
public class Main {

    public static List<Integer> parseLine(String st) {
        String[] numbers = st.split(":\s+")[1].split("\s+");
        List<Integer> inputList = new ArrayList<>();
        for (String n : numbers)
            inputList.add(Integer.parseInt(n));
        return inputList;

    }

    public static List<Integer> getAlternatives (List<Integer> times, List<Integer> distances) {
        int total;
        List<Integer> results = new ArrayList<>();
        for(int i = 0; i < times.size(); i++) {
            int speed = 0;
            total = 0;
            boolean found = false;
            int time;
            do {
                time = times.get(i) - speed;
                if (speed * time > distances.get(i)) {
                    total++;
                    found = true;
                }
                speed++;
            } while ((speed <= times.get(i)) && ((!found) || (speed * time > distances.get(i))));
            results.add(total);
        }
        return results;
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                Iterator<List<Integer>> it = stream.map(Main::parseLine).iterator();
                List<Integer> times = it.next();
                List<Integer> distances = it.next();
                System.out.println(times);
                System.out.println(distances);
                List<Integer> numAlternatives = getAlternatives(times, distances);
                System.out.println(numAlternatives.stream().reduce(1,(a,b) -> a * b));
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}