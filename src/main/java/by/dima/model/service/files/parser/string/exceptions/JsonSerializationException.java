package by.dima.model.service.files.parser.string.exceptions;

// cause - исключение, ставшее причиной выброса данного
public class JsonSerializationException extends RuntimeException {
    public JsonSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
