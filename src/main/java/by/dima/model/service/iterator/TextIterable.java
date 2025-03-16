package by.dima.model.service.iterator;

import java.util.Iterator;

public class TextIterable implements Iterable<String> {
    private final LineStringIterator iterator;

    public TextIterable(String text) {
        iterator = new LineStringIterator(text);
    }

    @Override
    public Iterator<String> iterator() {
        return iterator;
    }
}
