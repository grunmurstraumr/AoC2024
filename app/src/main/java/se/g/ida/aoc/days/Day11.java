package se.g.ida.aoc.days;

import se.g.ida.aoc.common.FileReader;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day11 extends DefaultDay<Long> {
    List<String> initialStones;



    private static final int BLINKS_PT1 = 25;
    private static final int BLINK_PT2 = 75;
    protected Day11(String inputFilename) {
        super(inputFilename);
        initialStones = Arrays.asList(FileReader.readInputFile(inputFilename).getFirst().split(" "));
    }

    @Override
    public Long runPart1() {
        List<String> stones = computeStones(initialStones, BLINKS_PT1);
        return (long) stones.size();
    }

    List<String> computeStones(List<String> initialStones, int blinks){
        List<String> stones = initialStones;
        for (int blink = 0; blink < blinks; blink++) {
            List<String> tempStones = new LinkedList<>();
            for (int stoneIndex=0; stoneIndex < stones.size(); stoneIndex++) {
                String stone = stones.get(stoneIndex);
                if (stone.equals("0")){
                    tempStones.add("1");
                }
                else if(stone.length() % 2 == 0){
                    tempStones.add(stone.substring(0, stone.length()/2));
                    tempStones.add(Long.valueOf(stone.substring(stone.length()/2)).toString());
                }
                else {
                    long stoneValue = Long.valueOf(stone);
                    long newStoneValue = stoneValue * 2024;
                    tempStones.add(Long.toString(newStoneValue) );
                }
            }
            stones = tempStones;
        }
        return stones;
    }


    private List<String> nextStones(String stone) {
        List<String> nextStones = new ArrayList<>();
        if (stone.equals("0")) {
            nextStones.add("1");
        } else if (stone.length() % 2 == 0) {
            nextStones.add(stone.substring(0, stone.length() / 2));
            nextStones.add(Long.valueOf(stone.substring(stone.length() / 2)).toString());
        } else {
            long stoneValue = Long.valueOf(stone);
            long newStoneValue = stoneValue * 2024;
            nextStones.add(Long.toString(newStoneValue));
        }
        return nextStones;
    }

    @Override
    public Long runPart2() {
        Map<String, Long> stoneCounts = new HashMap<>();
        Map<String, Long> finalStoneCounts = stoneCounts;
        initialStones.forEach(s -> finalStoneCounts.put(s, 1L));
        for (int blink = 0; blink < BLINK_PT2; blink++){
            Map<String, Long> nextStoneCounts = new HashMap<>();
            for (var entry : stoneCounts.entrySet()){
                List<String> newStones = nextStones(entry.getKey());
                for (String newStone : newStones){
                    nextStoneCounts.putIfAbsent(newStone, 0L);
                    nextStoneCounts.put(newStone, nextStoneCounts.get(newStone) + entry.getValue() );
                }
            }
            stoneCounts = nextStoneCounts;
        }
        return stoneCounts.values().stream().mapToLong(Long::longValue).sum();
        }
}
