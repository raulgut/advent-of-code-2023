package io.raulgut.adventofcode2023.day11p2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

// Advent of code - Day 11 part 2 in Java
// Argument: test.java.day10-1.txt
public class Main {

    public static void parseLine (Map<Long,Map<Long,Character>> galaxies
            , long i, String line) {
        Map<Long,Character> galaxyY;
        if (galaxies.containsKey(i))
            galaxyY = galaxies.get(i);
        else
            galaxyY = new HashMap<>();
        for (int j=0; j < line.length(); j++)
            if (line.charAt(j) == '#') {
                long longj = j;
                galaxyY.put(longj, '#');
            }
        galaxies.put(i,galaxyY);
    }

    public static Set<Long> getEmptyRows(Map<Long,Map<Long,Character>> galaxies) {
        Set<Long> emptyRows = new HashSet<>();
        for(long i : galaxies.keySet()) {
            if (galaxies.get(i).isEmpty())
                emptyRows.add(i);
        }
        return emptyRows;
    }

    public static Set<Long> getEmptyColumns(Map<Long,Map<Long,Character>> galaxies
            , long yLength) {
        Set<Long> nonEmptyColumns = new HashSet<>();
        for(long i : galaxies.keySet())
            nonEmptyColumns.addAll(galaxies.get(i).keySet());
        Set<Long> allColumns = LongStream.range(0,yLength).boxed().collect(Collectors.toSet());
        allColumns.removeAll(nonEmptyColumns);
        return allColumns;
    }

    public static void expandColumns(Map<Long,Character> yColumn
            , Set<Long> expandedColumns, long yLength) {
        Set<Long> auxExpandedColumns = new HashSet<>(expandedColumns);
        for (long j = yLength - 1; j >= 0; j--) {
            if (auxExpandedColumns.contains(j)) {
                auxExpandedColumns.remove(j);
            } else {
                if (yColumn.containsKey(j)) {
                    char c = yColumn.get(j);
                    yColumn.remove(j);
                    yColumn.put(j + (auxExpandedColumns.size()*(1000000 - 1)),c);
                }
            }
        }
    }
    public static void expand(Map<Long,Map<Long,Character>> galaxies
            ,Set<Long> expandedRows,Set<Long> expandedColumns
            ,long xLength, long yLength) {
        for(long i = xLength - 1; i >= 0; i--) {
            if (expandedRows.contains(i)) {
                Map<Long,Character> yColumn = galaxies.get(i);
                expandColumns(yColumn,expandedColumns,yLength);
                galaxies.remove(i);
                expandedRows.remove(i);
            } else {
                Map<Long,Character> yColumn = galaxies.get(i);
                expandColumns(yColumn,expandedColumns,yLength);
                galaxies.remove(i);
                galaxies.put(i + (expandedRows.size()*(1000000 - 1)),yColumn);
            }
        }
    }

    public static List<Node> fromGalaxies(Map<Long,Map<Long,Character>> galaxies) {
        int countId = 0;
        List<Node> nodes = new ArrayList<>();
        for (long i : galaxies.keySet()) {
            for (long j : galaxies.get(i).keySet()) {
                nodes.add(new Node(countId,i,j));
                countId++;
            }
        }
        return nodes;
    }

    public static long getShortest(List<Node> nodes) {
        List<Node> visited = new ArrayList<>();
        Node head = nodes.removeFirst();
        visited.add(head);
        long distance = 0;
        while (!nodes.isEmpty()) {
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
                Map<Long,Map<Long,Character>> galaxies = new HashMap<>();
                for(int i=0; i < lines.size(); i++) {
                    long longi = i;
                    parseLine(galaxies, longi, lines.get(i));
                }
                long xLength = lines.size();
                long yLength = lines.get(0).length();
                Set<Long> emptyRows = getEmptyRows (galaxies);
                Set<Long> emptyColumns = getEmptyColumns (galaxies,yLength);
                expand(galaxies, emptyRows, emptyColumns, xLength, yLength);
                System.out.println(galaxies);
                List<Node> nodes = fromGalaxies(galaxies);
                long result = getShortest(nodes);
                System.out.println(result);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}