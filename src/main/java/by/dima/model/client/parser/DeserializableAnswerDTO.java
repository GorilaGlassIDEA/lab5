package by.dima.model.client.parser;

import by.dima.model.common.AnswerDTO;


import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class DeserializableAnswerDTO {
    public AnswerDTO deserial(ByteBuffer buffer) {
        try (
                ByteArrayInputStream bis = new ByteArrayInputStream(buffer.array());
                ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (AnswerDTO) ois.readObject();
        } catch (Exception e) {
            System.out.println("Буффер пуст!");
        }
        return null;
    }
}
