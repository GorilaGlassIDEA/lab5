package by.dima.model.client.parser;


import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class ForDeserializable<T>{
    public T deserial(ByteBuffer buffer) {
        try (
                ByteArrayInputStream bis = new ByteArrayInputStream(buffer.array());
                ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (T) ois.readObject();
        } catch (Exception e) {
            System.out.println("Буффер пуст!");
        }
        return null;
    }
}
