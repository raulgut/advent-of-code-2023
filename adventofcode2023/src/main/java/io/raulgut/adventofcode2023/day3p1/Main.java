package io.raulgut.adventofcode2023.day3p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Advent of code - Day 3 part 1 in Java
// Argument: test.java.day3-1.txt
public class Main {

    public static AdjacentNumbers getNumberInPosition(int x, int y, char[][] matrix) {
        int left = y, right = y;
        while (((left - 1) >= 0) && (Character.isDigit(matrix[x][left - 1])))
            left--;
        while (((right + 1) < matrix[x].length) && (Character.isDigit(matrix[x][right + 1])))
            right++;
        char [] resultArray = new char[right - left + 1];
        Coordinate[] resultCoordinates = new Coordinate[right - left + 1];
        int j=0;
        int i=left;
        while (i <= right) {
            resultArray[j] = matrix[x][i];
            resultCoordinates[j] = new Coordinate(x,i);
            j++;
            i++;
        }
        int[] result = new int[] {Integer.parseInt(new String(resultArray))};
        return new AdjacentNumbers(result, resultCoordinates);
    }
    public static AdjacentNumbers getAdjacents(int x, int y, char[][] matrix) {
        AdjacentNumbers result = new AdjacentNumbers(null,null);
        for (int i=x - 1; i <= x + 1; i ++)
            for (int j=y - 1; j <= y + 1; j++)
                if ((i >= 0) && (j >= 0) && (i < matrix.length) && (j < matrix[i].length) && ((i != x) || (j != y)) && (matrix[i][j] != '.')) {
                    AdjacentNumbers adjns = getNumberInPosition(i,j,matrix);
                    result.addAdjacentNumbers(adjns);
                }
        return result;
    }
    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                AdjacentNumbers result = new AdjacentNumbers(null,null);
                char[][] lines = stream.map(String::toCharArray).toArray(char[][]::new);
                for(int i=0; i < lines.length; i++) {
                    for (int j = 0; j < lines[i].length; j++)
                        if ((lines[i][j] != '.') && (!Character.isDigit(lines[i][j]))) {
                            AdjacentNumbers adjns = getAdjacents(i,j,lines);
                            result.addAdjacentNumbers(adjns);
                        }
                }
                int addition = 0;
                for(int n : result.getAdjNumbers())
                    addition += n;
                System.out.println(addition);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}