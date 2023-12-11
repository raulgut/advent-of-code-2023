package io.raulgut.adventofcode2023.day10p1;

public class Cell {
    private final int xcoor;
    private final int ycoor;
    private final char cell;

    private int distance = 0;

    public Cell(int xcoor, int ycoor, char cell) {
        this.xcoor = xcoor;
        this.ycoor = ycoor;
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "xcoor=" + xcoor +
                ", ycoor=" + ycoor +
                ", cell=" + cell +
                ", distance=" + distance +
                '}';
    }

    public char getCell() {
        return cell;
    }

    public int getXcoor() {
        return xcoor;
    }

    public int getYcoor() {
        return ycoor;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
