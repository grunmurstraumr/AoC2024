package se.g.ida.aoc.day01;

import se.g.ida.aoc.common.ColumnSeparator;
import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.common.mapping.LongMapper;

import java.util.ArrayList;
import java.util.List;

public class Day01 {
    private final int rows;
    List<List<Long>> columns;

    private Day01(List<List<Long>> columns) {
        this.rows = columns.getFirst().size();
        if (!columns.stream().allMatch(column -> column.size() == rows)){
            throw new IllegalArgumentException("All columns must have the same number of rows.");
        }
        this.columns = columns;
    }

    private List<Long>computeDistances(){
        columns.forEach(column -> column.sort(Long::compareTo));
        List<Long> distances = new ArrayList<>(rows);
        for (int row = 0; row < rows; row++) {
            distances.add(computeDistance(columns, row));
        }
        return distances;
    }

    private long computeDistance(List<List<Long>> columns, int rowIndex){
        long distance = 0;
        for (var column : columns) {
            long columnValue = column.get(rowIndex);
            distance = distance > columnValue ? distance - columnValue : columnValue - distance;
        }
        return distance;
    }

    public long getTotalDistance(){
        return computeDistances().stream().mapToLong(Long::longValue).sum();
    }

    public long getTotalSimilarityScore(){
        long totalSimilarity = 0;
        for (int row = 0; row < rows; row++) {
            Long value = columns.get(0).get(row);
            long repetitionCount = 0;
            for (int columnIndex = 1; columnIndex < columns.size(); columnIndex++){
                repetitionCount += columns.get(columnIndex).stream().filter(value::equals).count();
            }
            totalSimilarity += repetitionCount * value;
        }
        return totalSimilarity;
    }

    public static Day01 fromFile(String filename) {
        List<List<Long>> columns = ColumnSeparator.separate(FileReader.readInputFile(filename), new LongMapper());
        return new Day01(columns);
    }
}
