package se.g.ida.aoc.days;

import se.g.ida.aoc.common.ColumnSeparator;
import se.g.ida.aoc.common.FileReader;
import se.g.ida.aoc.common.mapping.LongMapper;
import se.g.ida.aoc.days.utils.day09.MemoryCell;

import java.util.ArrayList;
import java.util.List;

public class Day09 extends DefaultDay<Long>{
    List<Long> input;
    public Day09(String inputFilename) {
        super(inputFilename);
        input = ColumnSeparator.separate(FileReader.readInputFile( inputFilename ), new LongMapper(), "").stream().flatMap(List::stream).toList();
    }

    @Override
    public Long runPart1() {
        List<MemoryCell> memory = buildMemoryModel();
        memory = compactMemory(memory);
        return computeChecksum(memory);
    }

    List<MemoryCell> buildMemoryModel(){
        List<MemoryCell> memory = new ArrayList<>(input.size() * 4);
        long fileId = 0;
        boolean isFile = true;
        for (int i = 0 ; i < input.size(); i++){
            for (int cells = 0; cells < input.get(i); cells++){
                memory.add(isFile ? new MemoryCell(fileId) : null);
            }
            if(isFile){
                fileId++;
            }
            isFile = !isFile;
        }
        return memory;
    }

    List<MemoryCell> compactMemory(List<MemoryCell> memory){
        List<MemoryCell> compactedMemory = new ArrayList<>(memory.size());
        int currentCellToMove = memory.size();
        for (int iterator = 0; iterator < currentCellToMove; iterator++){
            MemoryCell cell = memory.get(iterator);
            if (cell == null){
                MemoryCell cellToMove;
                do {
                    currentCellToMove--;
                    cellToMove = memory.get(currentCellToMove);
                } while(cellToMove == null && currentCellToMove > iterator);

                if (cellToMove != null){
                    memory.set(currentCellToMove, null);
                    cell = cellToMove;
                }
            }
            compactedMemory.add(cell);
        }
        return compactedMemory;
    }

    Long computeChecksum(List<MemoryCell> memory){
        long checksum = 0;

        for (int position = 0; position < memory.size(); position++){
            MemoryCell cell = memory.get(position);
            if (cell == null){
                continue;
            }

            checksum += position * cell.getFileId();
        }
        return checksum;
    }

    @Override
    public Long runPart2() {
        List<MemoryCell> memory = buildMemoryModel();
        memory = compactWithoutFragmentation(memory);
        return computeChecksum(memory);
    }

    List<MemoryCell> compactWithoutFragmentation(List<MemoryCell> memory){
        for (int reverseIterator = memory.size() - 1; reverseIterator > 0; reverseIterator--){
            if (memory.get(reverseIterator) == null){
                continue;
            }
            long currentFileId = memory.get(reverseIterator).getFileId();
            int filenEndIndex = reverseIterator;
            int fileStartIndex = reverseIterator;
            int nextIndex = fileStartIndex;
            do {
                fileStartIndex = nextIndex;
                nextIndex--;

            } while (nextIndex > 0 && memory.get(nextIndex) != null && memory.get(nextIndex).getFileId() == currentFileId);

            reverseIterator = fileStartIndex;
            int fileSize = filenEndIndex - fileStartIndex + 1;
            int freeSpaceStartIndex = findFreeSpaceStartIndex(memory, fileSize);
            if (freeSpaceStartIndex > fileStartIndex || freeSpaceStartIndex == -1){
                continue;
            }

            int fileIterator = fileStartIndex;

            for (int i = freeSpaceStartIndex; i < freeSpaceStartIndex + fileSize && fileIterator <= filenEndIndex; i++, fileIterator++){
                // move file to new location
                memory.set(i, memory.get(fileIterator));
                // remove file from old location
                memory.set(fileIterator, null);
            }
        }
        return memory;
    }

    int findFreeSpaceStartIndex(List<MemoryCell> memory, int fileSize ){
        int startIndex = 0;

        for (; startIndex < memory.size() - fileSize ; startIndex++){
            boolean sufficientFreeSpace = true;
            for (int j = startIndex; j < startIndex + fileSize; j++ ){
                if (memory.get(j) != null){
                    sufficientFreeSpace = false;
                    break;
                }

            }
            if (sufficientFreeSpace) {
                return startIndex;
            }
        }
        return -1;
    }
}
