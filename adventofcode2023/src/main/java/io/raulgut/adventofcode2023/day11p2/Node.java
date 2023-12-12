package io.raulgut.adventofcode2023.day11p2;

public class Node {
    private final long nId;
    private final long xCoor;
    private final long yCoor;

    public Node(long nId, long xCoor, long yCoor) {
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

    public long getDistance(Node n) {
        return Math.abs(xCoor - n.xCoor) + Math.abs(yCoor - n.yCoor);
    }
}
