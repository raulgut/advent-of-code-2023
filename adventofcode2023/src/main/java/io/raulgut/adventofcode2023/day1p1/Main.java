package io.raulgut.adventofcode2023.day1p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.Collectors;

// Advent of code - Day 1 part 1 in Java
// Argument: test.java.day1-1.txt
public class Main {
    public static String filterNumbers(String str) {
        return str.chars()
                  .filter(Character::isDigit)
                  .mapToObj(Character::toString)
                  .collect(Collectors.joining());
    }
    public static int getNumber(String str) {
        char[] newChars = { str.charAt(0), str.charAt(str.length() - 1) };
        String newStr = new String(newChars);
        return Integer.parseInt(newStr);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                int result = stream.map(Main::filterNumbers)
                        .map(Main::getNumber)
                        .reduce(0, Integer::sum);
                System.out.println(result);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}