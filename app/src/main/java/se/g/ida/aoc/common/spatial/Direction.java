package se.g.ida.aoc.common.spatial;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direction {
    UP(Coordinate.of(0,-1)),
    DOWN(Coordinate.of(0,1)),
    LEFT(Coordinate.of(-1, 0)),
    RIGHT(Coordinate.of(1,0));
    private final Coordinate movement;
}
