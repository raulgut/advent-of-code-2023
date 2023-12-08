package io.raulgut.adventofcode2023.day8p2;

import io.raulgut.adventofcode2023.day8p1.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

// Advent of code - Day 8 part 2 in Java
// Argument: test.java.day8-1.txt
// Warning: this program only works with this input (or equivalent)!
public class Main {

    public static Node parseLine(String line) {
        String[] nodeAndArcs = line.split("\s=\s+\\(");
        String[] arcs = nodeAndArcs[1].split(",\s");
        return new Node(nodeAndArcs[0], arcs[0], arcs[1].split("\\)")[0]);
    }

    public static boolean allFinal(List<String> state, char finalStateLetter) {
        return state.stream().map(st -> st.charAt(2) == finalStateLetter).reduce(true,Boolean::logicalAnd);
    }

    public static Output reach(int steps, int position, String state
            , char finalStateLetter, char[] moves, Map<String,String> mapLeft
            , Map<String, String> mapRight) {
        String initState = state;
        List<String> singleState = new ArrayList<>();
        singleState.add(state);
        while ((steps == 0) || (!allFinal(singleState,finalStateLetter))) {
            if (position >= moves.length) {
                position = 0;
            }
            switch (moves[position]) {
                case 'L' :
                    steps++;
                    position++;
                    state = mapLeft.get(state);
                    singleState = new ArrayList<>();
                    singleState.add(state);
                    break;
                case 'R' :
                    steps++;
                    position++;
                    state = mapRight.get(state);
                    singleState = new ArrayList<>();
                    singleState.add(state);
                    break;
                default: System.err.println("Come on, really?");
            }
        }
        System.out.println(initState + " " + state + " " + steps + " " + position);
        return new Output(steps, state, position);
    }

    public static long computeGCD(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return computeGCD(b, a % b);
        }
    }

    public static long computeLCM(long a, long b) {
        long gcd = computeGCD(a, b);
        return (a * b) / gcd;
    }
    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                List<String> lines = stream.toList();
                char[] moves = lines.get(0).toCharArray();
                Map<String,String> mapLeft = new HashMap<>();
                Map<String,String> mapRight = new HashMap<>();
                List<String> startingNodes = new ArrayList<>();
                for(Node n : lines.stream().skip(2).map(Main::parseLine).toList()) {
                    mapLeft.put(n.getNode(),n.getLeft());
                    mapRight.put(n.getNode(),n.getRight());
                    if (n.getNode().charAt(2) == 'A') {
                        startingNodes.add(n.getNode());
                    }
                }

                //int steps = process(0, 0,startingNodes,'Z',moves,mapLeft,mapRight);
                List<Output> loopingNodes = new ArrayList<>();
                for(String n : startingNodes) {
                    Output output = reach(0, 0,n,'Z',moves,mapLeft,mapRight);
                    loopingNodes.add(output);
                }
                //List<Output> endLoopingNodes = new ArrayList<>();
                for (Output o : loopingNodes) {
                    Output output = reach(0, o.getPosition(),o.getState(),'Z',moves,mapLeft,mapRight);
                    //endLoopingNodes.add(output);
                }
                //Because loopingNodes[i].steps == endLoopingNodes[i].steps,
                //we can simplify. Also, each loop is independent.
                List<Integer> loopingNodeSteps = loopingNodes.stream().map(Output::getSteps).toList();
                long result = loopingNodeSteps.get(0);
                for (int i=1; i < loopingNodeSteps.size(); i++) {
                    result = computeLCM(result,loopingNodeSteps.get(i));
                }
                System.out.println(result);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}