package se.g.ida.aoc.days;

import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.common.Pair;
import se.g.ida.aoc.common.spatial.Coordinate;
import se.g.ida.aoc.common.spatial.Direction;
import se.g.ida.aoc.common.spatial.Matrix;
import se.g.ida.aoc.days.utils.day06.Guard;
import se.g.ida.aoc.days.utils.day06.MapPosition;
import se.g.ida.aoc.days.utils.day06.PuzzleMap;

import java.util.*;
import java.util.stream.Stream;

public class Day06 extends DefaultDay<Long>{
    PuzzleMap puzzleMap;
    Guard guard;
    private static final int GRID_SIZE = 130 * 130;

    protected Day06(String inputFilename) {
        super(inputFilename);
        List<String> inputLines = FileReader.readInputFile(inputFilename);
        List<List<String>> splitLines = inputLines.stream()
                .map(s -> Arrays.stream(s.split("")).toList())
                .toList();
        var mapPositions = splitLines.stream().map(line -> line.stream().map(MapPosition::new).toList()).toList();
        this.puzzleMap = new PuzzleMap(mapPositions);
        this.guard = new Guard(puzzleMap) ;
    }

    @Override
    public Long runPart1() {
        while (guard.isInsideGrid()){
            guard.move();
        }
        return this.puzzleMap.countVisited();
    }


    @Override
    public Long runPart2() {
        int stepCounter = 0;
        Set<Coordinate> obstacleCoordinates = new HashSet<>();
        while (guard.isInsideGrid()){
            stepCounter++;
            Pair<Object> pair =  guard.moveAndPlaceObstacleInPath();
            PuzzleMap m = (PuzzleMap) pair.getFirst();
            Coordinate c = (Coordinate) pair.getSecond();
            if (obstacleCoordinates.contains(c)){
                continue;
            }
            if (tryForLoop(m)) {
                obstacleCoordinates.add(c);
            }

        }
        return (long) obstacleCoordinates.size();
    }

    public boolean tryForLoop(PuzzleMap puzzleMap){
        Guard loopingGuard = new Guard(puzzleMap, guard.getStartPosition(), guard.getStartDirection());
        while( loopingGuard.isInsideGrid() ){
            loopingGuard.move();
            if (loopingGuard.isLooping()){
                return true;
            }
        }
        return false;
    }
}
