package by.dima.model.service.iterator;

import javax.sound.sampled.Line;
import java.util.Iterator;

public class TextIterable implements Iterable<String> {
    private final Iterator<String> iterator;

    public TextIterable(Iterator<String> iterator) {
        this.iterator = iterator;
    }

    @Override
    public Iterator<String> iterator() {
        return iterator;
    }
}
