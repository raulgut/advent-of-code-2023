package io.raulgut.adventofcode2023.day10p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

// Advent of code - Day 10 part 1 in Java
// Argument: test.java.day10-1.txt
public class Main {

    public static Map<Integer,Cell> parseLine (int xcoor, String line) {
        Map<Integer,Cell> ycoorMap = new HashMap<>();
        for(int i=0; i < line.length(); i++) {
            ycoorMap.put(i,new Cell(xcoor,i,line.charAt(i)));
        }
        return ycoorMap;
    }

    public static void updateCell(Cell c, Map<Integer,Map<Integer,Cell>> puzzle
            , Map<Integer,Cell> puzzleY, List<Cell> newCells, int distance
            ,int xcoor, int ycoor) {
        if (c.getDistance() == 0) {
            c.setDistance(distance + 1);
            puzzleY.put(ycoor, c);
            puzzle.put(xcoor, puzzleY);
            newCells.add(c);
        }
    }

    public static int process(Map<Integer,Map<Integer,Cell>> puzzle, List<Cell> cells
            , int distance, int lengthX, int lengthY) {
        boolean found = false;
        while (!found) {
            int xcoor, ycoor;
            List<Cell> newCells = new ArrayList<>();
            for (Cell c : cells) {
                switch (c.getCell()) {
                    case 'S':
                        //We need to check the four possibilities
                        xcoor = c.getXcoor();
                        ycoor = c.getYcoor();
                        if (xcoor > 0) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor - 1);
                            Cell north = puzzleY.get(ycoor);
                            switch (north.getCell()) {
                                case '|':
                                case '7':
                                case 'F':
                                    updateCell(north, puzzle, puzzleY, newCells
                                            , distance, xcoor - 1, ycoor);
                            }
                        }
                        if (xcoor < lengthX - 1) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor + 1);
                            Cell south = puzzleY.get(ycoor);
                            switch (south.getCell()) {
                                case '|':
                                case 'L':
                                case 'J':
                                    updateCell(south, puzzle, puzzleY, newCells
                                            , distance, xcoor + 1, ycoor);
                            }
                        }
                        if (ycoor > 0) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor);
                            Cell west = puzzleY.get(ycoor - 1);
                            switch (west.getCell()) {
                                case '-':
                                case 'L':
                                case 'F':
                                    updateCell(west, puzzle, puzzleY, newCells
                                            , distance, xcoor, ycoor - 1);
                            }
                        }
                        if (ycoor < lengthY - 1) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor);
                            Cell east = puzzleY.get(ycoor + 1);
                            switch (east.getCell()) {
                                case '-':
                                case 'J':
                                case '7':
                                    updateCell(east, puzzle, puzzleY, newCells
                                            , distance, xcoor, ycoor + 1);
                            }
                        }
                        break;
                    case '|':
                        xcoor = c.getXcoor();
                        ycoor = c.getYcoor();
                        if (xcoor > 0) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor - 1);
                            Cell north = puzzleY.get(ycoor);
                            switch (north.getCell()) {
                                case '|':
                                case '7':
                                case 'F':
                                    updateCell(north, puzzle, puzzleY, newCells
                                            , distance, xcoor - 1, ycoor);
                            }
                        }
                        if (xcoor < lengthX - 1) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor + 1);
                            Cell south = puzzleY.get(ycoor);
                            switch (south.getCell()) {
                                case '|':
                                case 'L':
                                case 'J':
                                    updateCell(south, puzzle, puzzleY, newCells
                                            , distance, xcoor + 1, ycoor);
                            }
                        }
                        break;
                    case '-':
                        xcoor = c.getXcoor();
                        ycoor = c.getYcoor();
                        if (ycoor > 0) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor);
                            Cell west = puzzleY.get(ycoor - 1);
                            switch (west.getCell()) {
                                case '-':
                                case 'L':
                                case 'F':
                                    updateCell(west, puzzle, puzzleY, newCells
                                            , distance, xcoor, ycoor - 1);
                            }
                        }
                        if (ycoor < lengthY - 1) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor);
                            Cell east = puzzleY.get(ycoor + 1);
                            switch (east.getCell()) {
                                case '-':
                                case 'J':
                                case '7':
                                    updateCell(east, puzzle, puzzleY, newCells
                                            , distance, xcoor, ycoor + 1);
                            }
                        }
                        break;
                    case 'L':
                        xcoor = c.getXcoor();
                        ycoor = c.getYcoor();
                        if (xcoor > 0) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor - 1);
                            Cell north = puzzleY.get(ycoor);
                            switch (north.getCell()) {
                                case '|':
                                case '7':
                                case 'F':
                                    updateCell(north, puzzle, puzzleY, newCells
                                            , distance, xcoor - 1, ycoor);
                            }
                        }
                        if (ycoor < lengthY - 1) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor);
                            Cell east = puzzleY.get(ycoor + 1);
                            switch (east.getCell()) {
                                case '-':
                                case 'J':
                                case '7':
                                    updateCell(east, puzzle, puzzleY, newCells
                                            , distance, xcoor, ycoor + 1);
                            }
                        }
                        break;
                    case 'J':
                        xcoor = c.getXcoor();
                        ycoor = c.getYcoor();
                        if (xcoor > 0) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor - 1);
                            Cell north = puzzleY.get(ycoor);
                            switch (north.getCell()) {
                                case '|':
                                case '7':
                                case 'F':
                                    updateCell(north, puzzle, puzzleY, newCells
                                            , distance, xcoor - 1, ycoor);
                            }
                        }
                        if (ycoor > 0) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor);
                            Cell west = puzzleY.get(ycoor - 1);
                            switch (west.getCell()) {
                                case '-':
                                case 'L':
                                case 'F':
                                    updateCell(west, puzzle, puzzleY, newCells
                                            , distance, xcoor, ycoor - 1);
                            }
                        }
                        break;
                    case '7':
                        xcoor = c.getXcoor();
                        ycoor = c.getYcoor();
                        if (xcoor < lengthX - 1) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor + 1);
                            Cell south = puzzleY.get(ycoor);
                            switch (south.getCell()) {
                                case '|':
                                case 'L':
                                case 'J':
                                    updateCell(south, puzzle, puzzleY, newCells
                                            , distance, xcoor + 1, ycoor);
                            }
                        }
                        if (ycoor > 0) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor);
                            Cell west = puzzleY.get(ycoor - 1);
                            switch (west.getCell()) {
                                case '-':
                                case 'L':
                                case 'F':
                                    updateCell(west, puzzle, puzzleY, newCells
                                            , distance, xcoor, ycoor - 1);
                            }
                        }
                        break;
                    case 'F':
                        xcoor = c.getXcoor();
                        ycoor = c.getYcoor();
                        if (xcoor < lengthX - 1) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor + 1);
                            Cell south = puzzleY.get(ycoor);
                            switch (south.getCell()) {
                                case '|':
                                case 'L':
                                case 'J':
                                    updateCell(south, puzzle, puzzleY, newCells
                                            , distance, xcoor + 1, ycoor);
                            }
                        }
                        if (ycoor < lengthY - 1) {
                            Map<Integer, Cell> puzzleY = puzzle.get(xcoor);
                            Cell east = puzzleY.get(ycoor + 1);
                            switch (east.getCell()) {
                                case '-':
                                case 'J':
                                case '7':
                                    updateCell(east, puzzle, puzzleY, newCells
                                            , distance, xcoor, ycoor + 1);
                            }
                        }
                        break;
                }
            }
            xcoor = newCells.get(0).getXcoor();
            ycoor = newCells.get(0).getYcoor();
            boolean differ = false;
            for (int i = 1; i < newCells.size() && !differ; i++)
                if ((newCells.get(i).getXcoor() != xcoor) || (newCells.get(i).getYcoor() != ycoor))
                    differ = true;
            if (!differ)
                found = true;
            else
                cells = newCells;
            distance++;
        }
        return distance;
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                List<String> lines = stream.toList();
                Map<Integer,Map<Integer,Cell>> puzzle = new HashMap<>();
                for(int i=0; i < lines.size(); i++) {
                    puzzle.put(i,parseLine(i,lines.get(i)));
                }
                // We find S
                int i, j = 0;
                boolean found = false;
                Cell c = null;
                for (i = 0; i < lines.size() && !found; i++)
                    for(j = 0; j < lines.get(i).length() && !found; j++) {
                        c = puzzle.get(i).get(j);
                        if (c.getCell() == 'S')
                            found = true;
                    }
                System.out.println("Starting position: (" + (i - 1) + "," + (j - 1) + ")");
                int distance = process(puzzle, Collections.singletonList(c), 0, lines.size(), lines.get(0).length());
                System.out.println(distance);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}