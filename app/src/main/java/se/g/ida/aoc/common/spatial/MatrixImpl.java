package se.g.ida.aoc.common.spatial;

import lombok.AllArgsConstructor;

import java.text.CollationKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class MatrixImpl<E extends MatrixItem<?>> implements Matrix<E> {

    protected final List<List<E>> grid;

    @Override
    public Optional<E> getLocation(int x, int y) {
        if (grid.size() > y && y >= 0 && grid.get(y).size() > x && x >= 0) {
            return Optional.of(grid.get(y).get(x));
        }
        return Optional.empty();
    }
}
