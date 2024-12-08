package se.g.ida.aoc.common;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class HomogenousPair<E> extends AbstractCollection<E> implements Pair<E, E> {
    private static final int SIZE = 2;
    List<E> items;

    public HomogenousPair(E itemA, E itemB) {
        items = List.of(itemA, itemB);
    }

    public HomogenousPair(Collection<E> collection) {
        if (collection.size() != 2) {
            String errorMessage = "A Pair can only contain 2 items, no more no less. Provided: %s".formatted(collection.size());
            throw new IllegalArgumentException(errorMessage);
        }
        items = List.copyOf(collection);
    }

    public static <E> HomogenousPair<E> of(E itemA, E itemB) {
        return new HomogenousPair<>(itemA, itemB);
    }

    @Override
    public E getFirst() {
        return items.getFirst();
    }

    @Override
    public E getSecond() {
        return items.get(1);
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }
}
