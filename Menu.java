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
            [2] Register
            [3] Forgot Password
            [4] Exit
            """);
        System.out.print(" >> ");
        String input = scanner.nextLine();

        if (input.equals("1")) {
            Staff staff = GetStaff();
            if (staff != null) { return GetPassword(staff); }
        }
        else if (input.equals("2")) {
            // RUN Registration() Methods
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
    public static Staff GetStaff() {
        while (true) {
            System.out.print("Email: ");
            String input = scanner.next();

            if (input.equals("exit")) { return null; }
            else {
                Staff staff = Data.Get().GetStaff(input);

                if (staff == null) {
                    WriteError("Email Not Found");
                    System.out.println("Enter 'exit' to Quit");
                }
                else { return staff; }
            }
        }
    }

    public static boolean GetPassword(Staff staff) {
        while (true) {
            System.out.print("Password: ");
            String input = scanner.next();

            if (input.equals("exit")) { return false; }
            else if (input.equals(staff.GetPassword())) { 
                Data.Get().SetStaff(staff);
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
