package by.dima.model.client.parser;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ForSerializableObject<T> {


    public byte[] serial(T t) {
        byte[] buffer = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(t);
            buffer = bos.toByteArray();
        } catch (IOException e) {
            //TODO: сдедать логирование
        }
        return buffer;
    }
}
