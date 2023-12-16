package io.raulgut.adventofcode2023.day12p1;

import java.util.ArrayList;
import java.util.List;

public class Spring {
    private final String springLine;
    private final List<Integer> arrangement;

    public Spring(String springLine, List<Integer> arrangement) {
        this.springLine = springLine;
        this.arrangement = arrangement;
    }

    @Override
    public String toString() {
        return "Spring{" +
                "springLine=" + springLine +
                ", arrangement=" + arrangement +
                '}';
    }

    private boolean validCombination(String str, List<Integer> rest) {
        int pos = 0;
        for (int quantity : rest) {
            while ((pos < str.length()) && (str.charAt(pos) == '.'))
                pos++;
            for (int j = 0; j < quantity; j++) {
                if (pos + j == str.length())
                    return false;
                if (str.charAt(pos + j) == '.')
                    return false;
            }
            pos += quantity;
            if ((pos < str.length()) && (str.charAt(pos) == '#'))
                return false;
        }
        while ((pos < str.length()) && (str.charAt(pos) == '.')) {
            pos++;
        }
        return pos >= str.length();
    }
    public int getPossibleArrangements() {
        List<String> combinations = new ArrayList<>();
        String base = "";
        for (int i=0; i < springLine.length(); i++)
            if (springLine.charAt(i) == '#') {
                base += '#';
            } else {
                base += '.';
            }
        combinations.add(base);
        System.out.println(arrangement);
        System.out.println(base);
        for (int i=0; i < springLine.length(); i++) {
            if (springLine.charAt(i) == '?') {
                int limit = combinations.size();
                for (int j = 0; j < limit; j++) {
                    String str = combinations.get(j);
                    if (i==0) {
                        combinations.add("#" + str.substring(i+1));
                        //System.out.println("0: #" + str.substring(i+1));
                    } else {
                        if (i == springLine.length() - 1) {
                            combinations.add(str.substring(0, i) + "#");
                            //System.out.println(i + ": " + str.substring(0, i) + "#");
                        } else {
                            combinations.add(str.substring(0, i) + "#" + str.substring(i+1));
                            //System.out.println(i + ": " + str.substring(0, i) + "#" + str.substring(i+1));

                        }
                    }
                }
            }
        }
        int alternatives = 0;
        for(String str : combinations) {
            List<Integer> formula = new ArrayList<>(arrangement);
            if (validCombination(str,formula)) {
                System.out.println(arrangement + " " + str);
                alternatives++;
            }
        }
        System.out.println("Alternatives: " + alternatives);
        return alternatives;
    }
}
