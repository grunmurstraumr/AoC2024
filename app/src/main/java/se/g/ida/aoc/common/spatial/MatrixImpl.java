package se.g.ida.aoc.common.spatial;

import lombok.AllArgsConstructor;

import java.text.CollationKey;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class MatrixImpl<E extends MatrixItem<?>> implements Matrix<E> {

    protected final List<List<E>> grid;
    @Override
    public Optional<E> getLocation(int x, int y) {
        if (grid.size() > y && grid.get(y).size() > x) {
            return Optional.of(grid.get(y).get(x));
        }
        return Optional.empty();
    }
}
