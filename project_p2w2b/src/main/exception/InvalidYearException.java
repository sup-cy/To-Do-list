package exception;

public class InvalidYearException extends InvalidDataException {
    public void showMessage() {
        System.out.println("Invalid Year");
    }

    @Override
    public String getMessage() {
        return "Invalid Year";
    }
}
