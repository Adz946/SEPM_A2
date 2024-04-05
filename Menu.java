import Classes.*;

import java.io.FileReader;
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
            Staff staff = GetStaff();
            if (staff != null) { return GetPassword(staff); }
        }
        else if (input.equals("2")) {
            Registration();
        }
        else if (input.equals("3")) {
            String password = forgotPassword();
            if (password != null) {
                System.out.println("Password found: " + password);
            } else {
                System.out.println("Mobile number not found or an error occurred.");
            }}
       
        else if (input.equals("4")) { ExitProgram(); }
        else { WriteError("Error: Choose Between Options 1 To 4"); }

        return false;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public boolean MainMenu() {
        System.out.println("""
            ---------- %s ----------
            [1] View Open Tickets
            [2] Create New Ticket
            [3] Stuff... 3
            [4] Log Out
            [5] Exit
            """.formatted(Data.Get().GetActiveStaff().GetName()));
        System.out.print(" >> ");
        String input = scanner.next();
        
        if (input.equals("1")) {
            for (Ticket ticket : Data.Get().GetStaffTickets()) { ticket.ToString(); }
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
                Data.Get().SetActiveStaff(staff);
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
    System.out.println("\n---------- Registration ----------");
    System.out.println("Name    : " + name);
    System.out.println("Email   : " + email);
    System.out.println("Mobile  : " + formatMobile(mobile));  // Assumes a method to format the mobile number
    System.out.println("Password: " + "*".repeat(password.length()));
    System.out.println("[1] Register User");
    System.out.println("[2] Cancel");

    System.out.print(" >> ");
    String choice = scanner.nextLine();

    if ("1".equals(choice)) {
        try {
            registerUser(name, email, mobile, password);  
            System.out.println("Registration successful.");
        } catch (IOException e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    } else if ("2".equals(choice)) {
        System.out.println("Registration cancelled.");
    } else {
        System.out.println("Invalid option selected. Registration cancelled.");
    }
    }
    
    public static String formatMobile(String mobile) {
        String digitsOnly = mobile.replaceAll("[^\\d]", ""); 
        return digitsOnly.replaceFirst("(\\d{4})(\\d{3})(\\d+)", "$1 $2 $3");
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
        String formattedMobile = formatMobile(mobile); 
        String relativePath = "Data/users.csv"; 
        try (PrintWriter pw = new PrintWriter(new FileWriter(relativePath, true))) {
            pw.println(name + "," + email + "," + formattedMobile + "," + password);
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    public static String forgotPassword() {
        System.out.print("Enter your mobile number to retrieve your password: ");
        String mobileInput = scanner.nextLine();
    
        String sanitizedMobileInput = mobileInput.replaceAll("[^\\d]", "");
    
        String relativePath = "Data/users.csv"; 
        String line;
        String[] data;
    
        try (Scanner fileScanner = new Scanner(new FileReader(relativePath))) {
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                data = line.split(",");
    
                if (data.length > 2 && data[2].replaceAll("[^\\d]", "").equals(sanitizedMobileInput)) {
                    return data[3];
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to retrieve the password: " + e.getMessage());
        }
    
        return null;
    }
}
