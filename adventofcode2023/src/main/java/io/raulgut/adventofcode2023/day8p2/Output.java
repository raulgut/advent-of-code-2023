package io.raulgut.adventofcode2023.day8p2;

public class Output {
    private final int steps;
    private final String state;
    private final int position;

    public Output(int steps, String state, int position) {
        this.steps = steps;
        this.state = state;
        this.position = position;
    }

    public int getSteps() {
        return steps;
    }

    public String getState() {
        return state;
    }

    public int getPosition() {
        return position;
    }
}
