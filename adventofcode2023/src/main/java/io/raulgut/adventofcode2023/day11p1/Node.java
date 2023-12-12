package io.raulgut.adventofcode2023.day11p1;

public class Node {
    private final int nId;
    private final int xCoor;
    private final int yCoor;

    public Node(int nId, int xCoor, int yCoor) {
        this.nId = nId;
        this.xCoor = xCoor;
        this.yCoor = yCoor;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nId=" + nId +
                ", xCoor=" + xCoor +
                ", yCoor=" + yCoor +
                '}';
    }

    public int getDistance(Node n) {
        return Math.abs(xCoor - n.xCoor) + Math.abs(yCoor - n.yCoor);
    }
}
