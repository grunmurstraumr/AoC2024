package se.g.ida.aoc.common;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
public class HeterogenousPair<E1, E2> implements Pair<E1, E2> {
    private final E1 itemA;
    private final E2 itemB;

    @Override
    public E1 getFirst() {
        return itemA;
    }

    @Override
    public E2 getSecond() {
        return itemB;
    }
}
