package se.g.ida.aoc.days;

import se.g.ida.aoc.common.ColumnSeparator;
import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.common.mapping.LongMapper;

import java.util.ArrayList;
import java.util.List;

public class Day01 extends DefaultDay<Long> {
    private final int rows;
    List<List<Long>> columns;

    public Day01(String inputFilename){
        super(inputFilename);
        this.columns = ColumnSeparator.separate(FileReader.readInputFile(inputFilename), new LongMapper());
        this.rows = columns.getFirst().size();
        if (!columns.stream().allMatch(column -> column.size() == rows)){
            throw new IllegalArgumentException("All columns must have the same number of rows.");
        }
    }

    @Override
    public Long runPart1(){
        return computeDistances().stream().mapToLong(Long::longValue).sum();
    }

    @Override
    public Long runPart2(){
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
}
