package io.raulgut.adventofcode2023.day5p2;

public class MappingValues implements Comparable<MappingValues> {
    private final long destination;
    private final long source;
    private final long length;

    public MappingValues(long ds, long sc, long len) {
        destination = ds;
        source = sc;
        length = len;
    }

    @Override
    public String toString() {
        return "MappingValues{" +
                "destination=" + destination +
                ", source=" + source +
                ", length=" + length +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MappingValues)) {
            return false;
        }
        MappingValues other = (MappingValues) obj;
        return ((source == other.getSource())
                && (destination == other.getDestination())
                && (length == other.getLength()));
    }


    public long getSource() {
        return source;
    }

    public long getDestination() {
        return destination;
    }

    public long getLength() {
        return length;
    }

    @Override
    public int compareTo(MappingValues o) {
        if (source > o.getSource()) {
            return 1;
        } else if (source < o.getSource()) {
            return -1;
        } else if (destination > o.getDestination()) {
            return 1;
        } else if (destination < o.getDestination()) {
            return -1;
        } else return Long.compare(length, o.length);
    }
}
