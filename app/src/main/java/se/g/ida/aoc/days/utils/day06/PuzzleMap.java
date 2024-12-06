package se.g.ida.aoc.days.utils.day06;

import se.g.ida.aoc.common.spatial.Coordinate;
import se.g.ida.aoc.common.spatial.Direction;
import se.g.ida.aoc.common.spatial.MatrixImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PuzzleMap extends MatrixImpl<MapPosition>{

    public PuzzleMap(List<List<MapPosition>> grid) {
        super(grid);
    }

    public PuzzleMap copy(){
        List<List<MapPosition>> newGrid = new ArrayList<>(grid.size());
        for (List<MapPosition> row : grid){
            List<MapPosition> newRow = new ArrayList<>(row.size());
            for (MapPosition position : row){
                newRow.add(new MapPosition(position.getContent()));
            }
            newGrid.add(newRow);
        }
        return new PuzzleMap(newGrid);
    }

    public void placeObstacle(Coordinate atCoordinate){
        this.getLocation(atCoordinate).ifPresent(location -> location.setContent("#"));
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
