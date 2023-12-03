package io.raulgut.adventofcode2023.day3p1;

import java.util.Arrays;

public class AdjacentNumbers {
    private int[] adjNumbers;
    private Coordinate[] coordinates;

    public AdjacentNumbers(int[] ns, Coordinate[] cs) {
        adjNumbers = ns;
        coordinates = cs;
    }

    public int[] getAdjNumbers() {
        return adjNumbers;
    }

    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "AdjacentNumbers{" +
                "adjNumbers=" + Arrays.toString(adjNumbers) +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }

    public void addAdjacentNumbers(AdjacentNumbers adjns) {
        if (adjNumbers == null) {
            adjNumbers = adjns.getAdjNumbers();
            coordinates = adjns.getCoordinates();
        } else {
            boolean exists = false;
            for (Coordinate coordinate : coordinates) {
                if ((adjns.getCoordinates()[0].getXcoor() == coordinate.getXcoor()) &&
                        (adjns.getCoordinates()[0].getYcoor() == coordinate.getYcoor()))
                    exists = true;
            }
            if (!exists){
                Coordinate[] newCoordinates = new Coordinate[coordinates.length + adjns.getCoordinates().length];
                int[] newAdjNumbers = new int[adjNumbers.length + adjns.getAdjNumbers().length];
                int i=0;
                int j=0;
                while (j < coordinates.length) {
                    newCoordinates[i] = coordinates[j];
                    i++;
                    j++;
                }
                j = 0;
                while (j < adjns.getCoordinates().length) {
                    newCoordinates[i] = adjns.getCoordinates()[j];
                    i++;
                    j++;
                }
                coordinates = newCoordinates;
                i = 0;
                j = 0;
                while (j < adjNumbers.length) {
                    newAdjNumbers[i] = adjNumbers[j];
                    i++;
                    j++;
                }
                j = 0;
                while (j < adjns.getAdjNumbers().length) {
                    newAdjNumbers[i] = adjns.getAdjNumbers()[j];
                    i++;
                    j++;
                }
                adjNumbers = newAdjNumbers;
            }
        }
    }
}
