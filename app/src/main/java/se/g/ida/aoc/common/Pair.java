package se.g.ida.aoc.common;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Pair<E> extends AbstractCollection<E> {
    private static final int SIZE = 2;
    List<E> items;

    public Pair(E itemA, E itemB) {
        items = List.of(itemA, itemB);
    }

    public Pair(Collection<E> collection) {
        if (collection.size() != 2) {
            String errorMessage = "A Pair can only contain 2 items, no more no less. Provided: %s".formatted(collection.size());
            throw new IllegalArgumentException(errorMessage);
        }
        items = List.copyOf(collection);
    }

    public static <E> Pair<E> of(E itemA, E itemB){
        return new Pair<>(itemA, itemB);
    }

    public E getFirst(){
        return items.get(0);
    }

    public E getSecond(){
        return items.get(1);
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }
}
