package Menus;
import Functions.*;
import Classes.Staff;
import Functions.InputReader;

public class Register {
    private static String name, email, mobile, password;
    public Register() { Reset(); }

    private void Reset() {
        name = "";
        email = "";
        mobile = "";
        password = "";
    }
    // ---------------------------------------------------------------------------------------------------- //
    public int Menu() {
        System.out.printf("---------- Register ---------- %n" +
            "[1] Name : %s %n" +
            "[2] Email : %s %n" +
            "[3] Mobile : %s %n" +
            "[4] Password : %s %n" +
            "[5] Register %n" +
            "[6] Go Back %n", name, email, mobile, password);
        System.out.print(" >> ");
        String input = InputReader.Get().nextLine();

        if (input.equals("1")) { name = NameInput(); }
        else if (input.equals("2")) { email = EmailInput(); }
        else if (input.equals("3")) { mobile = MobileInput(); }
        else if (input.equals("4")) { password = PassInput(); }
        else if (input.equals("5")) { return ValidateRegister(); }
        else if (input.equals("6")) { Reset(); return 0; }
        else { App.WriteError("Only Select Between the Available Options"); }

        return 1;
    }

    private int ValidateRegister() {
        if (name != "" && email != "" && mobile != "" && password != "") {
            try { 
                Staff newStaff = new Staff(name, email, mobile, password);
                Data.Get().AddStaff(newStaff);
                Data.Get().SetActiveStaff(newStaff);
                
                Reset();
                return 3;
            }
            catch (Exception e) { 
                App.WriteError("Error: " + e.getMessage()); 
                return 1;
            }   
        }
        else App.WriteError("Enter ALL Fields");
        return 1;
    }
    // ---------------------------------------------------------------------------------------------------- //
    private String NameInput() {
        while (true) {
            System.out.print("Enter Full Name: ");
            String input = InputReader.Get().nextLine();

            if (InputReader.NameValidation(input)) return input;
            else App.WriteError("Please enter your First, Middle (optional) and Last Name, each capitalised with a space between.");
        }
    }

    private String EmailInput() {
        while (true) {
            System.out.print("Enter Email: ");
            String input = InputReader.Get().nextLine();

            if (Data.Get().GetStaff(input) == null) {
                if (InputReader.EmailValidation(input)) return input;
                else App.WriteError("Follow Format: 'for@example.com'");
            }
            else App.WriteError("Email In Use");
        }
    }

    private String MobileInput() {
        while (true) {
            System.out.print("Enter Mobile: ");
            String input = InputReader.Get().nextLine();

            if (InputReader.MobileValidation(input)) return input;
            else App.WriteError("Follow Format: '04XX XXX XXX'");
        }
    }

    private String PassInput() {
        while (true) {
            System.out.print("Enter Password: ");
            String input = InputReader.Get().nextLine();

            if (InputReader.PasswordValidation(input)) return input;
            else App.WriteError("20 Characters: Uppercase, Lowercase, Numbers");
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
}