package se.g.ida.aoc.days.utils.day06;

import se.g.ida.aoc.common.spatial.AbstractMatrixItem;
import se.g.ida.aoc.common.spatial.Direction;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class MapPosition extends AbstractMatrixItem<String> {
    Set<Direction> visitedFromDirections = new HashSet<>();
    private static final Set<String> obstructions = Set.of("#");
    public static final String OBSTACLE = "#";

    public MapPosition(String content) {
        super(content);
    }

    public boolean isObstacle(){
        return OBSTACLE.equals(content);
    }

    public String peek(){
        return super.getContent();
    }

    public void visit(Direction inDirection){
        visitedFromDirections.add(inDirection);
    }

    public boolean isVisited(){
        return !visitedFromDirections.isEmpty();
    }
    public boolean isVisited(Direction inDirection){
        return visitedFromDirections.contains(inDirection);
    }
}
