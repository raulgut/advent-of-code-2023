package io.raulgut.adventofcode2023.day9p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.List;

// Advent of code - Day 9 part 1 in Java
// Argument: test.java.da9-1.txt
public class Main {

    public static List<Long> parseLine(String line) {
        return Arrays.stream(line.split("\s+")).map(Long::parseLong).toList();
    }

    public static long computeMatrix(List<Long> input) {
        long[][] matrix = new long[input.size()+1][input.size()+1];
        boolean end = false;
        int i, j = 0;
        for(i=0; i < input.size(); i++)
            matrix[0][i] = input.get(i);
        for(i=1; (i < input.size() && (!end)); i++) {
            boolean allZero = true;
            for (j=0; j < input.size() - i; j++) {
                long value = matrix[i - 1][j + 1] - matrix[i - 1][j];
                matrix[i][j] = value;
                if (value != 0)
                    allZero = false;
            }
            if (allZero)
              end = true;
        }
        j++;
        for (int k = i - 2; k >= 0; k--) {
            matrix[k][j] = matrix[k][j - 1] + matrix[k + 1][j - 1];
            j++;
        }
        /*for (int a = 0; a < matrix.length; a++) {
            System.out.print("[");
            for (int b = 0; b < matrix[a].length; b++) {
                if (b == 0)
                    System.out.print(matrix[a][b]);
                else
                    System.out.print(", " + matrix[a][b]);
            }
            System.out.println("]");
        }*/
        return matrix[0][input.size()];
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                List <List<Long>> input = stream.map(Main::parseLine).toList();
                long result = 0;
                for (List<Long> longs : input) {
                    long matrixResult = computeMatrix(longs);
                    result += matrixResult;
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