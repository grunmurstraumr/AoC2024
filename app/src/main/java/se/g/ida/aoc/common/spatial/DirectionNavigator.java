package se.g.ida.aoc.common.spatial;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import se.g.ida.aoc.common.HomogenousPair;

import java.util.Map;

import static se.g.ida.aoc.common.spatial.Direction.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DirectionNavigator {
    private static final Map<Direction, HomogenousPair<Direction>> directions = Map.of(
            UP, HomogenousPair.of(RIGHT, LEFT),
            RIGHT, HomogenousPair.of(DOWN, UP),
            DOWN, HomogenousPair.of(LEFT, RIGHT),
            LEFT, HomogenousPair.of(UP, DOWN)
    );

    public static Direction nextClockwise(Direction fromDirection) {
        return directions.get(fromDirection).getFirst();
    }

    public static Direction nextCounterClockwise(Direction fromDirection) {
        return directions.get(fromDirection).getSecond();
    }
}
