package io.raulgut.adventofcode2023.day2p1;

public class Handful {
    private final int reds;
    private final int greens;
    private final int blues;

    public Handful(int r, int g, int b) {
        reds = r;
        greens = g;
        blues = b;
    }

    public int getReds() {
        return reds;
    }

    public int getGreens() {
        return greens;
    }

    public int getBlues() {
        return blues;
    }

    @Override
    public String toString() {
        return "Handful{" +
                "reds=" + reds +
                ", greens=" + greens +
                ", blues=" + blues +
                '}';
    }

    public int getPowerSet() {
        return (getReds() * getGreens() * getBlues());
    }
}
