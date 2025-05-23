package by.dima.model.util.io;

import java.io.IOException;

public interface ReadableFile {
    String getContent(String path) throws IOException;
}
