package by.dima.model.service.files.io.write;

// переписать с возможностью дописывания, а не переписывания текста в файле
public interface WriteableFile {
    void write(String content);
}
