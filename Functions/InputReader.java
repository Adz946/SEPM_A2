package Functions;
import java.util.Scanner;
import java.util.regex.*;
// ---------------------------------------------------------------------------------------------------- //
public class InputReader {
    private static Scanner scanner;
    private InputReader() { }

    public static synchronized Scanner Get() {
        if (scanner == null) { scanner = new Scanner(System.in); }
        return scanner;
    }

    public static synchronized void Close() { 
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    public static synchronized boolean Validation(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static synchronized boolean IDValidation(String input) {
        String regex = "T-[0-9]{3}";
        return Validation(input, regex);
    }

    public static synchronized boolean NameValidation(String input) {
        String regex = "^[A-Z][a-z]{1,29}( [A-Z][a-z]{1,29}){0,2}$";
        return Validation(input, regex);
    }

    public static synchronized boolean EmailValidation(String input) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Validation(input, regex);
    }

    public static synchronized boolean MobileValidation(String input) {
        String regex = "^04\\d{2} \\d{3} \\d{3}$";
        return Validation(input, regex);
    }

    public static synchronized boolean PasswordValidation(String input) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{20,}$";
        return Validation(input, regex);
    }

    public static synchronized boolean DateValidation(String input) {
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        return Validation(input, regex);
    }
    // ---------------------------------------------------------------------------------------------------- //
}
