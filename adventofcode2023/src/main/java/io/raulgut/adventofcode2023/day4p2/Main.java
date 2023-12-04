package io.raulgut.adventofcode2023.day4p2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

// Advent of code - Day 4 part 1 in Java
// Argument: test.java.day4-1.txt
public class Main {

    private static Game parseLine (String line) {
        String[] gameandnumbers = line.split(":\\s+");
        // {"Game X","Rest"}
        int cardid = Integer.parseInt(gameandnumbers[0].split("\\s+")[1]);
        Set<Integer> winingNumbers = new HashSet<>();
        Set<Integer> myNumbers = new HashSet<>();
        String[] numbers = gameandnumbers[1].split("\\s+\\|\\s+");
        for(int i=0; i < numbers.length; i++) {
            String[] strList = numbers[i].split("\\s+");
            for (String s : strList) {
                switch (i) {
                    case 0:
                        winingNumbers.add(Integer.parseInt(s));
                        break;
                    case 1:
                        myNumbers.add(Integer.parseInt(s));
                        break;
                    default:
                        System.err.println("There must be only 2 lines of numbers!");
                }
            }

        }
        return new Game(cardid, winingNumbers, myNumbers);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                List<Game> cards = stream.map(Main::parseLine).toList();
                for(int i = 0; i < cards.size(); i ++) {
                    for (int j=0; j < cards.get(i).getIntersectionSize(); j++)
                        cards.get(i + j + 1).increaseAmount(cards.get(i).getAmount());
                }
                int result = cards.stream()
                        .map(Game::getAmount)
                        .reduce(0,Integer::sum);
                System.out.println(result);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } else {
          System.err.println("Invalid arguments count:" + args.length);
        }
    }
}