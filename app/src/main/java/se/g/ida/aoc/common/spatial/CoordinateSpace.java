package se.g.ida.aoc.common.spatial;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CoordinateSpace<E> {
    Map<Coordinate, E> space;

    int xBound;
    int yBound;
    public CoordinateSpace(int xBound, int yBound) {
        this.xBound = xBound;
        this.yBound = yBound;
        space = new HashMap<>();
    }

    public void setCoordinate(Coordinate coordinate, E item){
        if (coordinate.getX() > xBound || coordinate.getY() > yBound){
            throw new IllegalArgumentException("Coordinate %S is outside of this spaces bounds (x=%s, y=%s)"
                    .formatted(coordinate, xBound, yBound));
        }
        space.put(coordinate, item);
    }

    public Optional<E> getCoordinate(Coordinate coordinate){
        if (space.containsKey(coordinate)){
            return Optional.of(space.get(coordinate));
        }
        return Optional.empty();
    }

    public boolean isWithinBounds(Coordinate coordinate){
        return coordinate.getX() < xBound && coordinate.getX() >= 0
                && coordinate.getY() < yBound  && coordinate.getY() >= 0;
    }
}
