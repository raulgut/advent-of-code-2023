package io.raulgut.adventofcode2023.day2p2;

import io.raulgut.adventofcode2023.day2p1.Game;
import io.raulgut.adventofcode2023.day2p1.Handful;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Advent of code - Day 2 part 2 in Java
// Argument: test.java.day2-1.txt
public class Main {

    private static Game parseLine (String line) {
        String[] gamehandfuls = line.split(": ");
        // {"Game X","Rest"}
        int linegid = Integer.parseInt(gamehandfuls[0].split(" ")[1]);
        String[] strHandfuls = gamehandfuls[1].split("; ");
        Handful[] hs = new Handful[strHandfuls.length];
        for (int i=0; i < strHandfuls.length; i++) {
            hs[i] = parseHandful(strHandfuls[i]);

        }
        return new Game(linegid, hs);
    }

    private static Handful parseHandful(String strHandful) {
        int hreds = 0, hgreens = 0, hblues = 0;
        String[] dices = strHandful.split(", ");

        for (String dice : dices) {
            String[] amountColor = dice.split(" ");
            int amount = Integer.parseInt(amountColor[0]);
            switch (amountColor[1].trim()) {
                case "red":
                    hreds = amount;
                    break;
                case "green":
                    hgreens = amount;
                    break;
                case "blue":
                    hblues = amount;
                    break;
                default:
                    System.err.println("Color not accepted " + amountColor[1]);
            }
        }
        return new Handful(hreds,hgreens,hblues);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
                int result = stream.map(Main::parseLine)
                        .map(Game::getMins)
                        .map(Handful::getPowerSet)
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