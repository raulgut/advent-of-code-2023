package io.raulgut.adventofcode2023.day8p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

// Advent of code - Day 8 part 1 in Java
// Argument: test.java.day8-1.txt
public class Main {

    public static Node parseLine(String line) {
        String[] nodeAndArcs = line.split("\s=\s+\\(");
        String[] arcs = nodeAndArcs[1].split(",\s");
        return new Node(nodeAndArcs[0], arcs[0], arcs[1].split("\\)")[0]);
    }

    public static int process(int steps, int position, String state
            , String finalState, char[] moves, Map<String,String> mapLeft
            , Map<String, String> mapRight) {
        while (!state.equals(finalState)) {
            if (position >= moves.length) {
                position = 0;
            }
            switch (moves[position]) {
                case 'L' :
                    steps++;
                    position++;
                    state = mapLeft.get(state);
                    break;
                case 'R' :
                    steps++;
                    position++;
                    state = mapRight.get(state);
                    break;
                default: System.err.println("Come on, really?");
            }
        }
        return steps;
    }
    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                List<String> lines = stream.toList();
                char[] moves = lines.get(0).toCharArray();
                Map<String,String> mapLeft = new HashMap<>();
                Map<String,String> mapRight = new HashMap<>();
                for(Node n : lines.stream().skip(2).map(Main::parseLine).toList()) {
                    mapLeft.put(n.getNode(),n.getLeft());
                    mapRight.put(n.getNode(),n.getRight());
                }
                int steps = process(0, 0,"AAA","ZZZ",moves,mapLeft,mapRight);
                System.out.println(steps);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}