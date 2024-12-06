package se.g.ida.aoc.days.utils.day06;

import lombok.Getter;
import se.g.ida.aoc.common.spatial.AbstractMatrixItem;

import java.util.Set;

@Getter
public class MapPosition extends AbstractMatrixItem<String> {
    boolean visited;
    private static final Set<String> obstructions = Set.of("#");

    public MapPosition(String content) {
        super(content);
        visited = false;
    }

    public boolean isObstruction(){
        return obstructions.contains(content);
    }

    public String peek(){
        return super.getContent();
    }

    public void visit(){
        visited=true;
    }
}
