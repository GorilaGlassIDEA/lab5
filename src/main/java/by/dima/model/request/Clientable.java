package by.dima.model.request;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface Clientable {
    default Long getUserId() {
        return -1L;
    }

    void makePost(byte[] data);

    ByteBuffer makeGet();

}
