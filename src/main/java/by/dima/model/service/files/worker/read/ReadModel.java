package by.dima.model.service.files.worker.read;

abstract class ReadModel implements ReadableFile {

    @Override
    public abstract String getContent(String filePath);
}
