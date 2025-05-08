package by.dima.model.io;

import java.io.IOException;

public interface ReadableFile {
    String getContent(String path) throws IOException;
}
