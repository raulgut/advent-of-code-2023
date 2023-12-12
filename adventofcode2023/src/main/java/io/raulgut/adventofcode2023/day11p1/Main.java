package io.raulgut.adventofcode2023.day11p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Advent of code - Day 11 part 1 in Java
// Argument: test.java.day10-1.txt
public class Main {

    public static void parseLine (Map<Integer,Map<Integer,Character>> galaxies
            , int i, String line) {
        Map<Integer,Character> galaxyY;
        if (galaxies.containsKey(i))
            galaxyY = galaxies.get(i);
        else
            galaxyY = new HashMap<>();
        for (int j=0; j < line.length(); j++)
            if (line.charAt(j) == '#')
                galaxyY.put(j,'#');
        galaxies.put(i,galaxyY);
    }

    public static Set<Integer> getEmptyRows(Map<Integer,Map<Integer,Character>> galaxies) {
        Set<Integer> emptyRows = new HashSet<>();
        for(int i : galaxies.keySet()) {
            if (galaxies.get(i).isEmpty())
                emptyRows.add(i);
        }
        return emptyRows;
    }

    public static Set<Integer> getEmptyColumns(Map<Integer,Map<Integer,Character>> galaxies
            , int yLength) {
        Set<Integer> nonEmptyColumns = new HashSet<>();
        for(int i : galaxies.keySet())
            nonEmptyColumns.addAll(galaxies.get(i).keySet());
        Set<Integer> allColumns = IntStream.range(0,yLength).boxed().collect(Collectors.toSet());
        allColumns.removeAll(nonEmptyColumns);
        return allColumns;
    }

    public static void expandColumns(Map<Integer,Character> yColumn
            , Set<Integer> expandedColumns, int yLength) {
        Set<Integer> auxExpandedColumns = new HashSet<>(expandedColumns);
        for (int j = yLength - 1; j >= 0; j--) {
            if (auxExpandedColumns.contains(j)) {
                auxExpandedColumns.remove(j);
            } else {
                if (yColumn.containsKey(j)) {
                    char c = yColumn.get(j);
                    yColumn.remove(j);
                    yColumn.put(j+auxExpandedColumns.size(),c);
                }
            }
        }
    }
    public static void expand(Map<Integer,Map<Integer,Character>> galaxies
            ,Set<Integer> expandedRows,Set<Integer> expandedColumns
            ,int xLength, int yLength) {
        for(int i = xLength - 1; i >= 0; i--) {
            if (expandedRows.contains(i)) {
                Map<Integer,Character> yColumn = galaxies.get(i);
                expandColumns(yColumn,expandedColumns,yLength);
                galaxies.remove(i);
                expandedRows.remove(i);
                galaxies.put(i+expandedRows.size(),yColumn);
                galaxies.put(i+expandedRows.size()+1,yColumn);
            } else {
                Map<Integer,Character> yColumn = galaxies.get(i);
                expandColumns(yColumn,expandedColumns,yLength);
                galaxies.remove(i);
                galaxies.put(i+expandedRows.size(),yColumn);
            }
        }
    }

    public static List<Node> fromGalaxies(Map<Integer,Map<Integer,Character>> galaxies) {
        int countId = 0;
        List<Node> nodes = new ArrayList<>();
        for (int i : galaxies.keySet()) {
            for (int j : galaxies.get(i).keySet()) {
                nodes.add(new Node(countId,i,j));
                countId++;
            }
        }
        return nodes;
    }

    public static int getShortest(List<Node> nodes) {
        List<Node> visited = new ArrayList<>();
        Node head = nodes.removeFirst();
        visited.add(head);
        int distance = 0;
        while (nodes.size() > 0) {
            for(Node n : nodes) {
                distance += head.getDistance(n);
            }
            head = nodes.removeFirst();
            visited.add(head);
        }
        return distance;
    }
    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                List<String> lines = stream.toList();
                Map<Integer,Map<Integer,Character>> galaxies = new HashMap<>();
                for(int i=0; i < lines.size(); i++) {
                    parseLine(galaxies, i, lines.get(i));
                }
                int xLength = lines.size();
                int yLength = lines.get(0).length();
                Set<Integer> emptyRows = getEmptyRows (galaxies);
                Set<Integer> emptyColumns = getEmptyColumns (galaxies,yLength);
                expand(galaxies, emptyRows, emptyColumns, xLength, yLength);
                System.out.println(galaxies);
                List<Node> nodes = fromGalaxies(galaxies);
                int result = getShortest(nodes);
                System.out.println(result);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}