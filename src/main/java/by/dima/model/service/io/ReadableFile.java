package by.dima.model.service.io;

import java.io.IOException;

public interface ReadableFile {
    String getContent(String path) throws IOException;
}
