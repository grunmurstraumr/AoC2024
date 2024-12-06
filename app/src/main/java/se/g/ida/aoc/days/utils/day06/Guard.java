package se.g.ida.aoc.days.utils.day06;


import lombok.AllArgsConstructor;
import se.g.ida.aoc.common.spatial.Coordinate;
import se.g.ida.aoc.common.spatial.Direction;
import se.g.ida.aoc.common.spatial.DirectionNavigator;
import se.g.ida.aoc.common.spatial.Matrix;

import java.util.Map;
import java.util.Optional;

import static java.nio.file.Files.move;

public class Guard {
    Coordinate position;
    private Direction facing;
    Matrix<MapPosition> space;
    private static final String startSymbol = "^";

    public Guard(PuzzleMap space) {
        this.position = space.findFirst(startSymbol);
        this.facing = Direction.UP;
        this.space = space;
    }

    public boolean canMove(){
        Coordinate nextPosition = peekMove();
        // ALlow all movements outside of matrix
        Optional<MapPosition> nextLocation = space.getLocation(nextPosition);
        return nextLocation.isEmpty() || !nextLocation.get().isObstruction();
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

    public void move(){
        for(int i = 0; i < 4; i++){
            if (canMove())
            {
                this.position = position.add(peekMove());
                space.getLocation(this.position).ifPresent(MapPosition::visit);
                return;
            }
            rotateClockwise();
        }
        throw new IllegalStateException("Guard is stuck.");
    }

}
