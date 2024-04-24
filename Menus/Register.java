package Menus;
import Functions.*;
import Classes.Staff;
import Functions.InputReader;

public class Register {
    public int Menu() {
        System.out.println("---------- Register ---------- \n" + "Follow All Formats or Enter 'exit' to Quit Anytime!");

        String name = NameInput();
        if (name == null) return 0;

        String email = EmailInput();
        if (email == null) return 0;

        String mobile = MobileInput();
        if (mobile == null) return 0;

        String password = PassInput();
        if (password == null) return 0;

        return ValidateRegister(name, email, mobile, password);
    }

    private int ValidateRegister(String name, String email, String mobile, String password) {
        if (!name.equals("") && !email.equals("") && !mobile.equals("") && !password.equals("")) {
            try { 
                Staff newStaff = new Staff(name, email, mobile, password);
                StaffData.Get().AddStaff(newStaff);
                StaffData.Get().SetActiveStaff(newStaff);
    
                App.WriteSuccess("Registered Successfully");
                return 3;
            }
            catch (Exception e) { App.WriteError("Error: " + e.getMessage()); }  
        }
        else App.WriteError("Enter ALL Fields");

        return 1;
    }
    // ---------------------------------------------------------------------------------------------------- //
    private String NameInput() {
        while (true) {
            System.out.println("Format: [First Name, Middle Name (Optional) Last Name (Optional). Capitalize and put a Space Between Each]");
            System.out.print("Enter Full Name: ");
            String input = InputReader.Get().nextLine();

            if (input.equals("exit")) return null;
            else if (InputReader.NameValidation(input)) return input;
            else App.WriteError("Follow Format");
        }
    }

    private String EmailInput() {
        while (true) {
            System.out.println("Format: [for@example.com]");
            System.out.print("Enter Email: ");
            String input = InputReader.Get().nextLine();

            if (input.equals("exit")) return null;
            else if (StaffData.Get().GetStaff(input) == null) {
                if (InputReader.EmailValidation(input)) return input;
                else App.WriteError("Follow Format");
            }
            else App.WriteError("Email In Use");
        }
    }

    private String MobileInput() {
        while (true) {
            System.out.println("Format: [04XX XXX XXX]");
            System.out.print("Enter Mobile: ");
            String input = InputReader.Get().nextLine();

            if (input.equals("exit")) return null;
            else if (InputReader.MobileValidation(input)) return input;
            else App.WriteError("Follow Format");
        }
    }

    private String PassInput() {
        while (true) {
            System.out.println("20 Characters Minimum: Uppercase, Lowercase, and Numbers");
            System.out.print("Enter Password: ");
            String input = InputReader.Get().nextLine();

            if (input.equals("exit")) return null;
            else if (InputReader.PasswordValidation(input)) return input;
            else App.WriteError("Follow Format");
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
}