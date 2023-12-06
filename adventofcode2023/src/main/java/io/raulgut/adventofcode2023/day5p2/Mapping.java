package io.raulgut.adventofcode2023.day5p2;

import java.util.SortedSet;

public class Mapping implements Comparable<Mapping> {
    private final String from;
    private final String to;
    private final SortedSet<MappingValues> values;

    public Mapping(String fromM, String toM, SortedSet<MappingValues> vs) {
        from = fromM;
        to = toM;
        values = vs;
    }

    @Override
    public String toString() {
        return "Mapping{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", values=" + values +
                '}';
    }

    @Override
    public int compareTo(Mapping o) {
        return from.compareTo(o.getFrom());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Mapping)) {
            return false;
        }
        Mapping other = (Mapping) obj;

        return (from.equals(other.getFrom()));
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public SortedSet<MappingValues> getValues() {
        return values;
    }
}

