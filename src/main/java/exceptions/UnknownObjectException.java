package exceptions;

public class UnknownObjectException extends Exception {
    public UnknownObjectException() {
        super("Unknown object type during deserialization");
    }
    public UnknownObjectException(Throwable cause) {
        super("Unknown object type during deserialization", cause);
    }
}
