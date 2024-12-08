package se.g.ida.aoc.common;

import java.util.*;

public class MutablePair<E> extends AbstractCollection<E> implements Pair<E, E> {
    private static final int SIZE = 2;
    List<E> items;

    public MutablePair (){
        items = new ArrayList<>(SIZE);
        items.add(null);
        items.add(null);
    }
    public MutablePair(E itemA, E itemB) {
        items = new ArrayList<>(SIZE);
        items.add(itemA);
        items.add(itemB);
    }

    public MutablePair(Collection<E> collection) {
        this();
        if (collection.size() != 2) {
            String errorMessage = "A Pair can only contain 2 items, no more no less. Provided: %s".formatted(collection.size());
            throw new IllegalArgumentException(errorMessage);
        }
        E itemA = null;
        E itemB = null;
        for (E item : collection){
            if (itemA == null){
                itemA = item;
            }
            else{
                itemB = item;
            }

        }
        this.items.add(itemB);
        this.items.add(itemB);
    }

    public static <E> HomogenousPair<E> of(E itemA, E itemB) {
        return new HomogenousPair<>(itemA, itemB);
    }

    public E getFirst() {
        return items.getFirst();
    }

    public MutablePair<E> setFirst(E itemA){
        this.items.set(0, itemA);
        return this;
    }

    public E getSecond() {
        return items.get(1);
    }

    public MutablePair<E> setSecond(E itemB){
        this.items.set(1, itemB);
        return this;
    }
    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
