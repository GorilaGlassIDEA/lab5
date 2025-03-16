package by.dima.model.service.iterator;

import java.util.Iterator;

public class LineStringIterator implements Iterator<String> {
    private final String[] textArr;
    private int count = 0;

    public LineStringIterator(String text) {
        this.textArr = text.split("\n");
    }

    @Override
    public boolean hasNext() {
        return count < textArr.length;
    }

    @Override
    public String next() {
        String thisLine = textArr[count];
        count++;
        return thisLine;
    }
}
