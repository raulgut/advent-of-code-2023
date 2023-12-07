package io.raulgut.adventofcode2023.day7p1;

import java.util.Arrays;
import java.lang.Comparable;

public class Hand implements Comparable<Hand> {
    private final String hand;
    private final int bid;
    private Play play;

    public Hand(String inHand, int inBid) {
        this.hand = inHand;
        this.bid = inBid;
        int[] cards = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.play = Play.HIGHCARD;
        for (Character c : hand.toCharArray()) {
            switch (c) {
                case 'A':
                    cards[0]++;
                    break;
                case 'K':
                    cards[1]++;
                    break;
                case 'Q':
                    cards[2]++;
                    break;
                case 'J':
                    cards[3]++;
                    break;
                case 'T':
                    cards[4]++;
                    break;
                case '9':
                    cards[5]++;
                    break;
                case '8':
                    cards[6]++;
                    break;
                case '7':
                    cards[7]++;
                    break;
                case '6':
                    cards[8]++;
                    break;
                case '5':
                    cards[9]++;
                    break;
                case '4':
                    cards[10]++;
                    break;
                case '3':
                    cards[11]++;
                    break;
                case '2':
                    cards[12]++;
                    break;
                default:
                    System.err.println("Wrong card!");
            }
        }
        int max = Arrays.stream(cards).max().getAsInt();
        long quantity = Arrays.stream(cards).filter(a -> a == max).count();
        int secondMax = Arrays.stream(cards).filter(a -> a < max).max().getAsInt();
        switch (max) {
            case 5 :
                play = Play.FIVEOFAKIND;
                break;
            case 4 :
                play = Play.FOUROFAKIND;
                break;
            case 3 :
                if (secondMax == 2)
                    play = Play.FULLHOUSE;
                else
                    play = Play.THREEOFAKIND;
                break;
            case 2 :
                if (quantity == 2L)
                    play = Play.TWOPAIR;
                else
                    play = Play.ONEPAIR;
                break;
            case 1:
                play = Play.HIGHCARD;
                break;
            default:
                System.err.println("Weird hand!");
        }
    }

    @Override
    public String toString() {
        return "Hand{" +
                "hand='" + hand + '\'' +
                ", bid=" + bid +
                ", play=" + play +
                '}';
    }

    public Play getPlay() {
        return play;
    }

    public String getHand() {
        return hand;
    }

    public int getBid() {
        return bid;
    }

    public static int getValue(Character c) {
        switch (c) {
            case 'A' :
                return 12;
            case 'K' :
                return 11;
            case 'Q' :
                return 10;
            case 'J' :
                return 9;
            case 'T' :
                return 8;
            case '9' :
                return 7;
            case '8' :
                return 6;
            case '7' :
                return 5;
            case '6' :
                return 4;
            case '5' :
                return 3;
            case '4' :
                return 2;
            case '3' :
                return 1;
            case '2' :
                return 0;
            default:
                System.err.println("Card not valid!");
        }
        return -1;
    }
    @Override
    public int compareTo(Hand h) {
        int comparison =  play.compareTo(h.getPlay());
        if (comparison == 0) {
            for (int i = 0; i < hand.length(); i++)
                if (getValue(hand.charAt(i)) > getValue(h.getHand().charAt(i)))
                    return -1;
                else if (getValue(hand.charAt(i)) < getValue(h.getHand().charAt(i)))
                  return 1;
            return 0;
        } else
            return comparison;
    }
}
