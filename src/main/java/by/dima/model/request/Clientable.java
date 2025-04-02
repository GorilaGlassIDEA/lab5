package by.dima.model.request;

import java.io.IOException;

public interface Clientable {
    void say() throws IOException;

    void makePost(byte[] data);

    byte[] makeGet();

}
