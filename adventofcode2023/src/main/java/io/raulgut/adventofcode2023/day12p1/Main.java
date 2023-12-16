package io.raulgut.adventofcode2023.day12p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

// Advent of code - Day 12 part 1 in Java
// Argument: test.java.day12-1.txt
public class Main {

    public static Spring parseLine (String line) {
        String[] springsMarks = line.split("\s+");
        List<Integer> intMarks = new ArrayList<>();
        for (String n : springsMarks[1].split(",")) {
            intMarks.add(Integer.parseInt(n));
        }
        return new Spring(springsMarks[0],intMarks);

    }


    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                int result = stream.map(Main::parseLine)
                        .map(Spring::getPossibleArrangements)
                        .reduce(0,Integer::sum);
                System.out.println(result);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}