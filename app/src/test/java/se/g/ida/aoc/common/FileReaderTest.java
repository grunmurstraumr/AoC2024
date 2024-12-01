package se.g.ida.aoc.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileReaderTest {
    @Test
    void canReadFile(){
        assertNotNull(FileReader.readInputFile("01example.txt"));
    }
}
