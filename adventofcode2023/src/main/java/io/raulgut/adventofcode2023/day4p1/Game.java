package io.raulgut.adventofcode2023.day4p1;

import java.util.Set;
import java.util.HashSet;

public class Game {
    private final int cardId;
    private final Set<Integer> wNumbers;
    private final Set<Integer> myNumbers;

    private final Set<Integer> intersection;

    public Game(int cid, Set<Integer> wns, Set<Integer> mns) {
        cardId = cid;
        wNumbers = wns;
        myNumbers = mns;
        intersection = new HashSet<>(wNumbers);
        intersection.retainAll(myNumbers);
    }

    @Override
    public String toString() {
        return "Game{" +
                "cardId=" + cardId +
                ", wNumbers=" + wNumbers +
                ", myNumbers=" + myNumbers +
                ", intersection=" + intersection +
                '}';
    }

    public Double getPoints() {
        if (intersection.isEmpty()) {
            return 0.0;
        } else{
            return Math.pow(2,intersection.size() - 1);
        }
    }
}
