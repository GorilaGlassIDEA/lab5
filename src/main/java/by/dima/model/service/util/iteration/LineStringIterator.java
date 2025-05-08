package by.dima.model.service.util.iteration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LineStringIterator implements Iterator<String> {
    private final String[] textArr;
    private int count = 0;

    public LineStringIterator(String text) {
        if (text == null) {
            System.err.println("Не найдены команды для исполнения!");
            this.textArr = new String[0];
        } else {
            this.textArr = text.split("\n");
        }
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