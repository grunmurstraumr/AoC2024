package se.g.ida.aoc.days;

import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.common.HeterogenousPair;
import se.g.ida.aoc.common.Pair;
import se.g.ida.aoc.common.spatial.*;
import se.g.ida.aoc.days.utils.day08.Antenna;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class Day08 extends DefaultDay<Long>{

    CoordinateSpace<Antenna> antennaMap;
    Map<String, List<Pair<Coordinate, Antenna>>> frequencyMap;

    private static final String EMPTY = ".";
    public Day08(String inputFilename) {
        super(inputFilename);
        List<String> inputLines = FileReader.readInputFile(inputFilename);
        this.antennaMap = parseInput(inputLines);

    }

    CoordinateSpace<Antenna> parseInput(List<String> inputLines){
        CoordinateSpace<Antenna> space = new CoordinateSpace<>(inputLines.size(), inputLines.size());
        this.frequencyMap = new HashMap<>();
        for (int line = 0; line < inputLines.size(); line++){
            List<String> splitLine = Arrays.stream(inputLines.get(line).split("")).filter(not(String::isBlank)).toList();
            for (int column = 0; column < splitLine.size(); column++){
                String locationItem = splitLine.get(column);
                if (!EMPTY.equals(locationItem)) {
                    Antenna antenna = new Antenna(locationItem);
                    Coordinate coordinate = Coordinate.of(column, line);
                    space.setCoordinate(coordinate, antenna);
                    frequencyMap.computeIfAbsent(locationItem, (key) -> new ArrayList<>());
                    frequencyMap.get(locationItem).add(new HeterogenousPair<>(coordinate, antenna));
                }
            }
        }
        return space;
    }

    @Override
    public Long runPart1() {
        Set<Coordinate> antinodeCoordinates = new HashSet<>();

        for (var entry : frequencyMap.values()) {
            for (int i = 1; i < entry.size(); i++) {
                for (int j = 0; j < i; j++) {
                    Coordinate coordinateA = entry.get(j).getFirst();
                    Coordinate coordinateB = entry.get(i).getFirst();
                    Coordinate antinodeA = coordinateA.add(coordinateA.diff(coordinateB));
                    if (antennaMap.isWithinBounds(antinodeA)) {
                        antinodeCoordinates.add(antinodeA);
                    }
                    Coordinate antinodeB = coordinateB.add(coordinateB.diff(coordinateA));
                    if (antennaMap.isWithinBounds(antinodeB)) {
                        antinodeCoordinates.add(antinodeB);
                    }
                }
            }
        }
        return (long) antinodeCoordinates.size();
    }


    @Override
    public Long runPart2() {
        Set<Coordinate> antinodeCoordinates = new HashSet<>();

        for (var entry : frequencyMap.values()) {
            for (int i = 1; i < entry.size(); i++) {
                for (int j = 0; j < i; j++) {
                    Coordinate coordinateA = entry.get(j).getFirst();
                    Coordinate coordinateB = entry.get(i).getFirst();
                    Coordinate diffA = coordinateA.diff(coordinateB);
                    Coordinate diffB = coordinateA.reverseDiff(coordinateB);

                    Coordinate nextAntinodeA = coordinateA.add(diffA);
                    while(antennaMap.isWithinBounds(nextAntinodeA)) {
                        antinodeCoordinates.add(nextAntinodeA);
                        nextAntinodeA = nextAntinodeA.add(diffA);
                    }

                    nextAntinodeA = coordinateB.add(diffA);
                    while(antennaMap.isWithinBounds(nextAntinodeA)) {
                        antinodeCoordinates.add(nextAntinodeA);
                        nextAntinodeA = nextAntinodeA.add(diffA);
                    }

                    Coordinate nextAntinodeB = coordinateB.add(diffB);
                    while (antennaMap.isWithinBounds(nextAntinodeB)) {
                        antinodeCoordinates.add(nextAntinodeB);
                        nextAntinodeB = nextAntinodeB.add(diffB);
                    }

                    nextAntinodeB = coordinateA.add(diffB);
                    while (antennaMap.isWithinBounds(nextAntinodeB)) {
                        antinodeCoordinates.add(nextAntinodeB);
                        nextAntinodeB = nextAntinodeB.add(diffB);
                    }
                }
            }
        }
        return (long) antinodeCoordinates.size();
    }
}
