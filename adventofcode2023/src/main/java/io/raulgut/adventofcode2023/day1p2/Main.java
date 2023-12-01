package io.raulgut.adventofcode2023.day1p2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Advent of code - Day 1 part 2 in Java
// Argument: test.java.day1-1.txt
public class Main {
    public static String translateNumber(String str) {
        return switch (str) {
            case "one" -> "1one";
            case "two" -> "2two";
            case "three" -> "3three";
            case "four" -> "4four";
            case "five" -> "5five";
            case "six" -> "6six";
            case "seven" -> "7seven";
            case "eight" -> "8eight";
            case "nine" -> "9nine";
            default -> {
                System.err.println("Invalid input number in translateNumber");
                yield "";
            }
        };
    }
    public static String fromLettersToNumber(String str) {
        String[] numbers = { "one", "two", "three", "four", "five"
                           , "six", "seven", "eight", "nine" };
        String processed = str;
        String newStr;
        int pos = processed.length(), posNumber;
        String numberStr = "none";

        System.out.println("Old:" + processed);
        // Instead of replacing the string by the number we just add the
        // number before the string, this removes the problem of having
        // eightwo not translated into 82
        for (String subStr : numbers) {
            posNumber = processed.indexOf(subStr);
            if (posNumber != -1 && posNumber < pos) {
                pos = posNumber;
                numberStr = subStr;
            }
        }
        // We process the first occurrence
        if (pos < processed.length()) {
            newStr = Main.translateNumber(numberStr);
            processed = processed.replaceFirst(numberStr,newStr);
        }

        pos = -1;
        numberStr = "none";

        for (String subStr : numbers) {
            posNumber = processed.lastIndexOf(subStr);
            if (posNumber != -1 && posNumber > pos) {
                pos = posNumber;
                numberStr = subStr;
            }
        }
        if (pos > -1) {
            newStr = Main.translateNumber(numberStr);
            processed = processed.substring(0,pos) + newStr + processed.substring(pos + numberStr.length());
        }
        System.out.println("New:" + processed);

        return processed;
    }
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
                int result = stream.map(Main::fromLettersToNumber)
                        .map(Main::filterNumbers)
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