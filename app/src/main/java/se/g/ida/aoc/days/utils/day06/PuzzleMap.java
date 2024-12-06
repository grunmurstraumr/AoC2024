package se.g.ida.aoc.days.utils.day06;

import se.g.ida.aoc.common.spatial.Coordinate;
import se.g.ida.aoc.common.spatial.Direction;
import se.g.ida.aoc.common.spatial.MatrixImpl;

import java.util.List;
import java.util.NoSuchElementException;

public class PuzzleMap extends MatrixImpl<MapPosition>{

    public PuzzleMap(List<List<MapPosition>> grid) {
        super(grid);
    }

    Coordinate findFirst(String content){
        for(int y = 0; y < grid.size(); y++){
            List<MapPosition> row = grid.get(y);
            for (int x=0; x < row.size(); x++){
                if (row.get(x).peek().equals(content)) {
                    return Coordinate.of(x, y);
                }

            }
        }
        throw new NoSuchElementException("Puzzle does not contain content " + content);
    }

    public long countVisited(){
        return grid.stream().mapToLong(row -> row.stream().filter(MapPosition::isVisited).count()).sum();
    }
}
