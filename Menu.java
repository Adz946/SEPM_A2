import Classes.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// ---------------------------------------------------------------------------------------------------- //
public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    // ---------------------------------------------------------------------------------------------------- //
    public static void ExitProgram() {
        System.out.println("Terminating Program. Please Wait A Moment...");
        
        scanner.close();
        System.exit(0);
    }
    // ---------------------------------------------------------------------------------------------------- //
    public boolean LogInMenu() {
        System.out.println("""
            [1] Log In
            [2] Register
            [3] Forgot Password
            [4] Exit
            """);
        System.out.print(" >> ");
        String input = scanner.nextLine();

        if (input.equals("1")) {
            User user = GetUser();
            if (user != null) { return GetPassword(user); }
        }
        else if (input.equals("2")) {
            Registration();
            System.out.println("REGISTER");
        }
        else if (input.equals("3")) {
            // RUN PasswordCreate() METHOD: String password = SetPassword();
            System.out.println("FOROGT PASSWORD");
        }

        else if (input.equals("4")) { ExitProgram(); }
        else { WriteError("Error: Choose Between Options 1 To 4"); }

        return false;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public boolean MainMenu() {
        System.out.println("""
            [1] View Open Tickets
            [2] Create New Ticket
            [3] Stuff... 3
            [4] Log Out
            [5] Exit
            """);
        System.out.print(" >> ");
        String input = scanner.next();
        
        if (input.equals("1")) {

        }
        else if (input.equals("2")) {
            
        }
        else if (input.equals("3")) {
            
        }
        else if (input.equals("4")) { return false; }
        else if (input.equals("5")) { ExitProgram(); }
        else { WriteError("Error: Choose Between Options 1 To 5"); }

        return true;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public static User GetUser() {
        while (true) {
            System.out.print("Email: ");
            String input = scanner.next();

            if (input.equals("exit")) { return null; }
            else {
                User user = Data.Get().GetUser(input);

                if (user == null) {
                    WriteError("Email Not Found");
                    System.out.println("Enter 'exit' to Quit");
                }
                else { return user; }
            }
        }
    }

    public static boolean GetPassword(User user) {
        while (true) {
            System.out.print("Password: ");
            String input = scanner.next();

            if (input.equals("exit")) { return false; }
            else if (input.equals(user.GetPassword())) { 
                Data.Get().SetUser(user);
                return true;
            }
            else { 
                WriteError("Password Is Incorrect"); 
                System.out.println("Enter 'exit' to Quit");
            }
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    public static void WriteError(String msg) {
        System.out.println("\u001B[41m\u001B[37m [" + msg + "] \u001B[0m");
        // RED BACKGROUND: \u001B[41m | WHITE TEXT: \u001B[37m | RESET: \u001B[0m
    }
    // ---------------------------------------------------------------------------------------------------- //

    public static void Registration() {
        System.out.println("Welcome to User Registration");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        String email;
        while (true) {
            System.out.print("Enter your email: ");
            email = scanner.nextLine();
            if (validateEmail(email)) {
                break;
            } else {
                System.out.println("Invalid email. Please try again.");
            }
        }

        String mobile;
        while (true) {
            System.out.print("Enter your mobile (must start with 04 and be 10 digits): ");
            mobile = scanner.nextLine();
            if (validateMobile(mobile)) {
                break;
            } else {
                System.out.println("Invalid mobile number. Please try again.");
            }
        }

        String password;
        while (true) {
            System.out.print("Enter your password (min 20 characters, mix of uppercase and lowercase letters and numbers): ");
            password = scanner.nextLine();
            if (validatePassword(password)) {
                break;
            } else {
                System.out.println("Invalid password. Please try again.");
            }
        }

        try {
            registerUser(name, email, mobile, password);
            System.out.println("Registration successful.");
        } catch (IOException e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    }

    private static boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    private static boolean validateMobile(String mobile) {
        return mobile.matches("^04\\d{8}$");
    }

    private static boolean validatePassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{20,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    private static void registerUser(String name, String email, String mobile, String password) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter("Data/users.csv", true))) {
            pw.println(name + "," + email + "," + mobile + "," + password);
        }
    }
}
