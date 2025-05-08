package by.dima.model.parser;

import by.dima.model.common.CommandDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializableCommandDTO {


    public byte[] serial(CommandDTO commandDTO) {
        byte[] buffer = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(commandDTO);
            buffer = bos.toByteArray();
        } catch (IOException e) {
            //TODO: сдедать логирование
        }
        return buffer;
    }
}
