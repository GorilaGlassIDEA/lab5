package by.dima.model.service.files.worker.write;

import lombok.Data;


@Data
abstract class WriteFileModel implements WriteableFile {
    private String pathTo;


    protected WriteFileModel(String pathTo) {
        this.pathTo = pathTo;
    }

    @Override
    public abstract void write(String content);
}
