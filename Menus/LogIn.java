package Menus;
import Functions.*;
import Classes.Staff;

public class LogIn {
    public int Menu() {
        System.out.println("---------- Log In ---------- \n" +
            "[1] Log In \n" +
            "[2] Register \n" +
            "[3] Forgot Password \n" +
            "[4] Exit");
        System.out.print(" >> ");
        String input = InputReader.Get().nextLine();

        if (input.equals("1")) { 
            Staff staff = EmailInput();
            if (staff != null) { return PasswordInput(staff); }
        }
        else if (input.equals("2")) { return 1; }
        else if (input.equals("3")) { return 2; }
        else if (input.equals("4")) { return 6; }
        else { App.WriteError("Only Select Between the Available Options"); }

        return 0;
    }
    
    public Staff EmailInput() {
        while (true) {
            System.out.println("Please enter your email or type 'exit' and press enter to Cancel");
            System.out.print("Enter Email: ");
            String input = InputReader.Get().nextLine();

            if (input.equals("exit")) return null;

            else if (InputReader.EmailValidation(input)) {
                Staff staff = Data.Get().GetStaff(input);

                if (staff == null) App.WriteError("Email NOT Found");
                else return staff;
            }

            else App.WriteError("Follow Format: 'for@example.com'");
        }
    }

    public int PasswordInput(Staff staff) {
        while (true) {
            System.out.println("Please enter your password or type 'exit' and press enter to Cancel");
            System.out.print("Enter Password: ");
            String input = InputReader.Get().nextLine();

            if (input.equals("exit")) return 0;

            else if (InputReader.PasswordValidation(input)) {
                if (input.equals(staff.GetPassword())) {
                    Data.Get().SetActiveStaff(staff);
                    return 3;
                }
                else App.WriteError("Password is Incorrect");
            }

            else App.WriteError("20 Characters: Uppercase, Lowercase, Numbers");
        }
    }
}