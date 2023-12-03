package io.raulgut.adventofcode2023.day3p1;

public class Coordinate {
    private final int xcoor;
    private final int ycoor;

    public Coordinate(int x, int y) {
        xcoor = x;
        ycoor = y;
    }

    public int getXcoor() {
        return xcoor;
    }

    public int getYcoor() {
        return ycoor;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "xcoor=" + xcoor +
                ", ycoor=" + ycoor +
                '}';
    }
}
