package se.g.ida.aoc.days;

public abstract class DefaultDay<E> implements Day<E> {
    protected String inputFilename;
    protected DefaultDay(String inputFilename) {
        this.inputFilename = inputFilename;
    }
}
