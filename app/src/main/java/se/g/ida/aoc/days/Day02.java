package se.g.ida.aoc.days;

import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.common.mapping.ReportMapper;
import se.g.ida.aoc.days.utils.day02.Report;

import java.util.List;

public class Day02 extends DefaultDay<Long> {

    List<Report> input;

    public Day02(String inputFilename) {
        super(inputFilename);
        this.input = FileReader.readInputFile(inputFilename, new ReportMapper());
    }

    @Override
    public Long runPart1() {
        return input.stream()
                .filter(report -> report.getViolations(false).isEmpty())
                .count();
    }

    @Override
    public Long runPart2() {
        return input.stream()
                .filter(report -> report.getViolations(true).isEmpty())
                .count();
    }

}
