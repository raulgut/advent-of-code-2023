package io.raulgut.adventofcode2023.day8p1;

public class Node {
    private String node;
    private String left;
    private String right;

    public Node(String node, String left, String right) {
        this.node = node;
        this.left = left;
        this.right = right;
    }

    public String getNode() {
        return node;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "node='" + node + '\'' +
                ", left='" + left + '\'' +
                ", right='" + right + '\'' +
                '}';
    }
}
