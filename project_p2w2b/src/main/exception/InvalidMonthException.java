package exception;

public class InvalidMonthException extends InvalidDataException {
    public void showMessage() {
        System.out.println("Invalid month");
    }

    @Override
    public String getMessage() {
        return "Invalid Month";
    }
}
