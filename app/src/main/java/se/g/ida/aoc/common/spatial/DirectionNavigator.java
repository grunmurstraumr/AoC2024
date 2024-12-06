package se.g.ida.aoc.common.spatial;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import se.g.ida.aoc.common.Pair;

import java.util.Map;

import static se.g.ida.aoc.common.spatial.Direction.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DirectionNavigator {
    private static final Map<Direction, Pair<Direction>> directions = Map.of(
            UP, Pair.of(RIGHT, LEFT),
            RIGHT, Pair.of(DOWN, UP),
            DOWN, Pair.of(LEFT, RIGHT),
            LEFT, Pair.of(UP, DOWN)
            );
    public static Direction nextClockwise(Direction fromDirection){
        return directions.get(fromDirection).getFirst();
    }
    public static Direction nextCounterClockwise(Direction fromDirection){
        return directions.get(fromDirection).getSecond();
    }
}
