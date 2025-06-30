package by.dima.model.auth.read.data;

public interface ReadableData extends ReadablePassword {
    String getString(String message, String incorrectInputMessage);
}
