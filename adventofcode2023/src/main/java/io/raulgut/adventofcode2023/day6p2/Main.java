package io.raulgut.adventofcode2023.day6p2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

// Advent of code - Day 6 part 2 in Java
// Argument: test.java.day6-1.txt
public class Main {

    public static Long parseLine(String str) {
        String[] numbers = str.split(":\s+")[1].split("\s+");
        String number = "";
        for (String numberStr : numbers)
            number = number.concat(numberStr);
        return Long.parseLong(number);
    }

    public static Long getAlternatives (Long time, Long distance) {
        boolean found = false;
        Long speedLeft = 0L;
        long timeLeft;
        do {
            timeLeft = time - speedLeft;
            if (speedLeft * timeLeft > distance)
                found = true;
            else
              speedLeft++;
        } while ((speedLeft <= time) && !found);
        System.out.println(speedLeft);
        Long speedRight = time;
        found = false;
        long timeRight;
        do {
            timeRight = time - speedRight;
            if (speedRight * timeRight > distance)
                found = true;
            else
                speedRight--;
        } while ((speedRight >= 0) && !found);
        System.out.println(speedRight);
        return (speedRight - speedLeft + 1);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                List<Long> lines = stream.map(Main::parseLine).toList();
                Long time = lines.get(0);
                Long distance = lines.get(1);
                System.out.println(time);
                System.out.println(distance);
                Long numAlternatives = getAlternatives(time, distance);
                System.out.println(numAlternatives);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}