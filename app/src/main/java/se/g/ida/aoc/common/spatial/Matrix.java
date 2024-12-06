package se.g.ida.aoc.common.spatial;

import java.util.Optional;

public interface Matrix<E extends MatrixItem<?>> {

    Optional<E> getLocation(int x, int y);
    default Optional<E> getLocation(Coordinate coordinate) {
        return getLocation(coordinate.getX(), coordinate.getY());
    }
}
