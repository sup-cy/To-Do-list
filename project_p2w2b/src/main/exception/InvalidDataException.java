package exception;

public abstract class InvalidDataException extends Exception {
    public abstract void showMessage();

    public abstract String getMessage();
}
