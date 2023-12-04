package io.raulgut.adventofcode2023.day4p2;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private final int cardId;
    private final Set<Integer> wNumbers;
    private final Set<Integer> myNumbers;
    private final Set<Integer> intersection;

    private int amount;

    public Game(int cid, Set<Integer> wns, Set<Integer> mns) {
        cardId = cid;
        wNumbers = wns;
        myNumbers = mns;
        amount = 1;
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
                ", amount=" + amount +
                '}';
    }

    public Integer getIntersectionSize() {
        return intersection.size();
    }

    public void increaseAmount(int quantity) {
        amount += quantity;
    }

    public int getAmount() {
        return amount;
    }
}
