package se.g.ida.aoc.days.utils.day05;

import se.g.ida.aoc.common.Pair;

import java.util.List;
import java.util.Optional;

public class PrinterRule{
    Pair<Integer> items;


    public PrinterRule(Integer before, Integer after) {
        this.items = new Pair<>(before, after);
    }

    public boolean violates (List<Integer> listOfUpdates){
        return violatingIndexes(listOfUpdates).isPresent();
    }

    public Optional<Pair<Object>> violatingIndexes(List<Integer> listOfUpdates){
        int beforeItemIndex = listOfUpdates.indexOf(items.getFirst());
        int afterItemIndex = listOfUpdates.indexOf(items.getSecond());
        if (beforeItemIndex == -1 || afterItemIndex == -1){
            return Optional.empty();
        }
        return beforeItemIndex > afterItemIndex ? Optional.of(new Pair<>(beforeItemIndex, afterItemIndex)) : Optional.empty();
    }

    public Integer getBefore(){
        return items.getFirst();
    }

    public Integer getAfter(){
        return items.getSecond();
    }

    public boolean appliesTo(Integer item){
        return items.getFirst().equals(item) || items.getSecond().equals(item);
    }

}

