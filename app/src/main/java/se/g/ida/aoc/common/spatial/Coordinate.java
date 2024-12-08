package se.g.ida.aoc.common.spatial;

import lombok.Value;

@Value
public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate add(Coordinate other) {
        return of(this.x + other.x, this.y + other.y);
    }

    public Coordinate add(Coordinate other, int steps) {
        return of(this.x + (steps * other.x), this.y + (steps * other.y));
    }

    public Coordinate diff(Coordinate other){
        return of(this.x - other.x, this.y - other.y);
    }

    public Coordinate reverseDiff(Coordinate other){
        return of(-1 *(this.x - other.x), -1 *(this.y - other.y));
    }

    public static Coordinate of(int x, int y) {
        return new Coordinate(x, y);
    }
}
