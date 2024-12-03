package se.g.ida.aoc.common.mapping;

import se.g.ida.aoc.days.utils.day02.Report;

public class ReportMapper implements LineMapper<Report> {
    private final IntegerListMapper mapper;

    public ReportMapper() {
        mapper = new IntegerListMapper();
    }

    @Override
    public Report mapFromString(String input, int line) {
        return new Report(mapper.mapFromString(input, line));
    }
}
