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
        System.out.print("---------- Register ---------- \n");
        System.out.print("Please prepare to enter your user information. \nType 'exit' at any time to return to the previous menu.\n");
        name = NameInput();
        email = EmailInput();
        mobile = MobileInput();
        password = PassInput();
        if (ValidateRegister() == 1) {
            System.out.print("Your user information could not be validated. Please follow to prompts to re-enter your information.");
            Reset();
            Menu();
            }
        else if (ValidateRegister() == 3){
            return 3;
        }
        return 1;
    }

    private int ValidateRegister() {
        if (!name.equals("") && !email.equals("") && !mobile.equals("") && !password.equals("")) {
            try { 
                Staff newStaff = new Staff(name, email, mobile, password);
                Data.Get().AddStaff(newStaff);
                Data.Get().SetActiveStaff(newStaff);
    
                Reset();
                App.WriteSuccess("Registered Successfully");
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
            if (input == "exit"){
                Reset();  
            }
            if (InputReader.NameValidation(input)) return input;
            else App.WriteError("Please enter your First, Middle (optional) and Last Name, each capitalised with a space between.");
        }
    }

    private String EmailInput() {
        while (true) {
            System.out.print("Enter Email: ");
            String input = InputReader.Get().nextLine();
            if (input == "exit"){
                Reset();  
            }
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
            if (input == "exit"){
                Reset();  
            }

            if (InputReader.MobileValidation(input)) return input;
            else App.WriteError("Follow Format: '04XX XXX XXX'");
        }
    }

    private String PassInput() {
        while (true) {
            System.out.print("Enter Password: ");
            String input = InputReader.Get().nextLine();
            if (input == "exit"){
                Reset();  
            }

            if (InputReader.PasswordValidation(input)) return input;
            else App.WriteError("20 Characters: Uppercase, Lowercase, Numbers");
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
}