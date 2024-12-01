package se.g.ida.aoc.common;

import org.junit.jupiter.api.Test;
import se.g.ida.aoc.common.mapping.StringMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColumnSeparatorTest {

    @Test
    void separateColumns(){
        String input = """
                1   3
                4   5
                8   1
                123 567
                """;
        List<List<String>> columns = ColumnSeparator.separate(input);
        assertEquals(2, columns.size());
        assertEquals(List.of("1","4","8","123"), columns.get(0));
        assertEquals(List.of("3","5","1","567"), columns.get(1));
    }
}
