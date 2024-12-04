package se.g.ida.aoc.days;

import se.g.ida.aoc.common.ColumnSeparator;
import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.common.mapping.LineMapper;
import se.g.ida.aoc.common.mapping.StringMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day04 extends DefaultDay<Long> {

    private final List<String> inputAsLines;

    protected Day04(String inputFilename) {
        super(inputFilename);
        inputAsLines = FileReader.readInputFile(inputFilename);
    }

    @Override
    public Long runPart1() {
        List<List<String>> lettermatrix = inputAsLines.stream().map(line -> Stream.of(line.split("")).map(String::valueOf).toList()).toList();

        long matchCount = 0;
        for (int lineIndex = 0; lineIndex < lettermatrix.size(); lineIndex++) {
            List<String> line = lettermatrix.get(lineIndex);
            for (int columnIndex = 0; columnIndex < line.size(); columnIndex++) {
                if (line.get(columnIndex).equals("X")) {
                    // check up
                    if (lineIndex - 3 >= 0) {
                        if (lettermatrix.get(lineIndex - 1).get(columnIndex).equals("M") &&
                                lettermatrix.get(lineIndex - 2).get(columnIndex).equals("A") &&
                                lettermatrix.get(lineIndex - 3).get(columnIndex).equals("S")) {
                            matchCount++;
                        }
                    }
                    // Check ahead
                    if (columnIndex + 3 < line.size()) {
                        if (line.get(columnIndex + 1).equals("M") && line.get(columnIndex + 2).equals("A") && line.get(columnIndex + 3).equals("S")) {
                            matchCount++;
                        }
                    }
                    // check down
                    if (lineIndex + 3 < lettermatrix.size()) {
                        if (lettermatrix.get(lineIndex + 1).get(columnIndex).equals("M") &&
                                lettermatrix.get(lineIndex + 2).get(columnIndex).equals("A") &&
                                lettermatrix.get(lineIndex + 3).get(columnIndex).equals("S")) {
                            matchCount++;
                        }
                    }
                    // Check behind
                    if (columnIndex - 3 >= 0) {
                        if (line.get(columnIndex - 1).equals("M") && line.get(columnIndex - 2).equals("A") && line.get(columnIndex - 3).equals("S")) {
                            matchCount++;
                        }
                    }
                    // Check diagonals up-right
                    if (columnIndex + 3 < line.size() && lineIndex - 3 >= 0) {
                        if (lettermatrix.get(lineIndex - 1).get(columnIndex + 1).equals("M") &&
                                lettermatrix.get(lineIndex - 2).get(columnIndex + 2).equals("A") &&
                                lettermatrix.get(lineIndex - 3).get(columnIndex + 3).equals("S")) {
                            matchCount++;
                        }
                    }
                    // Check diagonal down-right
                    if (columnIndex + 3 < line.size() && lineIndex + 3 < lettermatrix.size()) {
                        if (lettermatrix.get(lineIndex + 1).get(columnIndex + 1).equals("M") &&
                                lettermatrix.get(lineIndex + 2).get(columnIndex + 2).equals("A") &&
                                lettermatrix.get(lineIndex + 3).get(columnIndex + 3).equals("S")) {
                            matchCount++;
                        }
                    }
                    // Check diagonal down-left
                    if (columnIndex - 3 >= 0 && lineIndex + 3 < lettermatrix.size()) {
                        if (lettermatrix.get(lineIndex + 1).get(columnIndex - 1).equals("M") &&
                                lettermatrix.get(lineIndex + 2).get(columnIndex - 2).equals("A") &&
                                lettermatrix.get(lineIndex + 3).get(columnIndex - 3).equals("S")) {
                            matchCount++;
                        }
                    }
                    // Check diagonal up left
                    if (columnIndex - 3 >= 0 && lineIndex - 3 >= 0) {
                        if (lettermatrix.get(lineIndex - 1).get(columnIndex - 1).equals("M") &&
                                lettermatrix.get(lineIndex - 2).get(columnIndex - 2).equals("A") &&
                                lettermatrix.get(lineIndex - 3).get(columnIndex - 3).equals("S")) {
                            matchCount++;
                        }
                    }
                }
            }
        }
        return matchCount;
    }

    @Override
    public Long runPart2() {
        List<List<String>> lettermatrix = inputAsLines.stream().map(line -> Stream.of(line.split("")).map(String::valueOf).toList()).toList();

        long matchCount = 0;
        for (int lineIndex = 0; lineIndex < lettermatrix.size(); lineIndex++) {
            List<String> line = lettermatrix.get(lineIndex);
            for (int columnIndex = 0; columnIndex < line.size(); columnIndex++) {
                if (line.get(columnIndex).equals("M")) {
                    /*
                        [M] M
                           A
                         S  S
                     */
                    if (lineIndex + 2 < lettermatrix.size() && columnIndex + 2 < line.size() &&
                            line.get(columnIndex + 2).equals("M") &&
                            lettermatrix.get(lineIndex + 2).get(columnIndex).equals("S") &&
                            lettermatrix.get(lineIndex + 2).get(columnIndex + 2).equals("S") &&
                            lettermatrix.get(lineIndex + 1).get(columnIndex + 1).equals("A")) {
                        matchCount++;
                    }
                    /*
                         S   S
                           A
                        [M]  M
                     */
                    if (lineIndex - 2 >= 0 && columnIndex + 2 < line.size() &&
                            line.get(columnIndex + 2).equals("M") &&
                            lettermatrix.get(lineIndex - 2).get(columnIndex).equals("S") &&
                            lettermatrix.get(lineIndex - 2).get(columnIndex + 2).equals("S") &&
                            lettermatrix.get(lineIndex - 1).get(columnIndex + 1).equals("A")) {
                        matchCount++;

                    }
                    /*
                        [M] S
                           A
                         M  S
                     */
                    if (lineIndex + 2 < lettermatrix.size() && columnIndex + 2 < line.size() &&
                            line.get(columnIndex + 2).equals("S") &&
                            lettermatrix.get(lineIndex + 2).get(columnIndex).equals("M") &&
                            lettermatrix.get(lineIndex + 2).get(columnIndex + 2).equals("S") &&
                            lettermatrix.get(lineIndex + 1).get(columnIndex + 1).equals("A")) {
                        matchCount++;
                    }
                    /*
                         S   M
                           A
                         S  [M]
                     */
                    if (lineIndex - 2 >= 0 && columnIndex - 2 >= 0 &&
                            line.get(columnIndex - 2).equals("S") &&
                            lettermatrix.get(lineIndex - 2).get(columnIndex).equals("M") &&
                            lettermatrix.get(lineIndex - 2).get(columnIndex - 2).equals("S") &&
                            lettermatrix.get(lineIndex - 1).get(columnIndex - 1).equals("A")) {
                        matchCount++;

                    }

                }
            }
        }
        return matchCount;
    }
}
