package se.g.ida.aoc.days.utils.day06;


import lombok.Getter;
import se.g.ida.aoc.common.HomogenousPair;
import se.g.ida.aoc.common.spatial.Coordinate;
import se.g.ida.aoc.common.spatial.Direction;
import se.g.ida.aoc.common.spatial.DirectionNavigator;

import java.util.Optional;

public class Guard {
    Coordinate position;
    private Direction facing;

    @Getter
    private Direction startDirection;
    @Getter
    Coordinate startPosition;
    PuzzleMap space;

    boolean looping;
    private static final String startSymbol = "^";

    public Guard(PuzzleMap space) {
        this(space, space.findFirst(startSymbol), Direction.UP);
    }

    public Guard(PuzzleMap space, Coordinate startPosition, Direction startDirection) {
        this.startDirection = startDirection;
        this.startPosition = startPosition;
        this.position = startPosition;
        this.facing = Direction.UP;
        this.space = space;
        this.looping = false;
        space.getLocation(position).ifPresent(mapPosition -> mapPosition.visit(facing));
    }

    public boolean canMove(){
        Coordinate nextPosition = peekMove();
        // ALlow all movements outside of matrix
        Optional<MapPosition> nextLocation = space.getLocation(nextPosition);
        return nextLocation.isEmpty() || !nextLocation.get().isObstacle();
    }

    public boolean isLooping(){
        return looping;
    }

    public boolean isInsideGrid(){
        return space.getLocation(position.getX(), position.getY()).isPresent();
    }

    public Coordinate peekMove(){
        return position.add(facing.getMovement());
    }

    public void rotateClockwise(){
        this.facing = DirectionNavigator.nextClockwise(facing);
    }

    public boolean move(){
        for(int i = 0; i < 4; i++){
            if (canMove())
            {
                this.position = peekMove();
                Optional<MapPosition> loc = space.getLocation(this.position);
                loc.ifPresent(mapPosition -> this.looping = mapPosition.isVisited(facing));
                loc.ifPresent(mapPosition -> mapPosition.visit(facing));
                return true;
            }
            rotateClockwise();
        }
        throw new IllegalStateException("Guard is stuck.");
    }

    public HomogenousPair<Object> moveAndPlaceObstacleInPath(){
        for(int i = 0; i < 4; i++){
            if (canMove())
            {
                Coordinate newPosition = peekMove();
                var spaceCopyWithNewObstacle = this.space.copy();
                spaceCopyWithNewObstacle.placeObstacle(newPosition);
                this.position = newPosition;
                Optional<MapPosition> loc = space.getLocation(this.position);
                loc.ifPresent(mapPosition -> this.looping = mapPosition.isVisited(facing));
                loc.ifPresent(mapPosition -> mapPosition.visit(facing));
                return HomogenousPair.of(spaceCopyWithNewObstacle, newPosition);
            }
            rotateClockwise();
        }
        throw new IllegalStateException("Guard is stuck.");
    }
}
