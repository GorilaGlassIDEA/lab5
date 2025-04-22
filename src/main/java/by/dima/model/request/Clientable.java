package by.dima.model.request;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface Clientable {
    default void say() throws IOException {
    }

    void makePost(byte[] data);

    ByteBuffer makeGet();

}
