package by.dima.model.service.files.io.read;

import java.io.IOException;

abstract class ReadModel implements ReadableFile {

    @Override
    public abstract String getContent() ;
}
