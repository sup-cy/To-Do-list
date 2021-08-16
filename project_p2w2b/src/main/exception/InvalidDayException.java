package exception;

public class InvalidDayException extends InvalidDataException {
    public void showMessage() {
        System.out.println("Invalid day");
    }

    @Override
    public String getMessage() {
        return "Invalid day";
    }
}
