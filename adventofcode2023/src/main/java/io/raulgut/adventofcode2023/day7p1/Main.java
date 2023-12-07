package io.raulgut.adventofcode2023.day7p1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.SortedSet;
import java.util.TreeSet;

// Advent of code - Day 7 part 1 in Java
// Argument: test.java.day7-1.txt
public class Main {

    private static Hand parseLine (String line) {
        String[] handAndBid = line.split("\s+");

        return new Hand(handAndBid[0],Integer.parseInt(handAndBid[1]));
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                SortedSet<Hand> handSet = stream.map(Main::parseLine)
                        .collect(Collectors.toCollection(TreeSet::new));
                int rank = 1;
                int winnings = 0;
                Hand h;
                for (int i = handSet.size() - 1; i >= 0; i--) {
                    h = handSet.removeLast();
                    winnings += (h.getBid() * rank);
                    rank++;
                }
                System.out.println(winnings);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}