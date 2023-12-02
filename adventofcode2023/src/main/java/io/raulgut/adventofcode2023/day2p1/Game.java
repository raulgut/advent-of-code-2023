package io.raulgut.adventofcode2023.day2p1;

import io.raulgut.adventofcode2023.day2p1.Handful;

import java.util.Arrays;

public class Game {
    private final int gid;
    private final Handful[] handfuls;

    public Game(int lineGid, Handful[] lineHandfuls) {
        gid = lineGid;
        handfuls = lineHandfuls;
    }

    public int getGid() {
        return gid;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gid=" + gid +
                ", handfuls=" + Arrays.toString(handfuls) +
                '}';
    }

    public Boolean isValid (Handful bag) {
        for (Handful h : handfuls) {
            if (h.getReds() > bag.getReds() ||
                    h.getGreens() > bag.getGreens() ||
                    h.getBlues() > bag.getBlues())
                return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Handful getMins() {
        int minReds = 0, minGreens = 0, minBlues = 0;

        for (Handful h : handfuls) {
            if (h.getReds() > minReds)
                minReds = h.getReds();
            if (h.getGreens() > minGreens)
                minGreens = h.getGreens();
            if (h.getBlues() > minBlues)
                minBlues = h.getBlues();
        }
        return new Handful(minReds, minGreens, minBlues);
    }
}
