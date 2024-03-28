import Classes.*;
import java.util.Scanner;
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
            [2] Forgot Password
            [3] Exit
            """);
        System.out.print(" >> ");
        String input = scanner.nextLine();

        if (input.equals("1")) {
            User user = GetUser();
            if (user != null) { return GetPassword(user); }
        }
        else if (input.equals("2")) {
            User user = GetUser();
            // RUN PasswordCreate() METHOD: String password = SetPassword();
        }

        else if (input.equals("3")) { ExitProgram(); }
        else { WriteError("Error: Choose Between Options 1 To 3"); }

        return false;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public boolean MainMenu() {
        System.out.println("""
            [1] Stuff... 1
            [2] Stuff... 2
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
                User user = Data.Get().GetTechy(input);

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
}
