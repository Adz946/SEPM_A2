package Menus;
import Functions.*;
import Classes.Staff;

public class ForgotPass {
    private Staff staff;
    public ForgotPass() { staff = null; }
    // ---------------------------------------------------------------------------------------------------- //
    private Staff EmailInput() {
        while (true) {
            System.out.print("Enter Your Email: ");
            String input = InputReader.Get().nextLine();

            if (InputReader.EmailValidation(input)) {
                Staff staff = Data.Get().GetStaff(input);

                if (staff == null) App.WriteError("Email NOT Found");
                else return staff;
            }

            else App.WriteError("Follow Format: 'for@example.com'");
        }
    }

    private void MobileInput(Staff s) {
        while (true) {
            System.out.print("Enter Your Mobile: ");
            String input = InputReader.Get().nextLine();

            if (InputReader.MobileValidation(input)) {
                if (s.GetMobile().equals(input)) {
                    staff = s;
                    break;
                }
                else App.WriteError("Mobile is Incorrect");
            }
            else App.WriteError("Follow Format: '04XX XXX XXX'");
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    public int Menu() {
        if (staff == null) { MobileInput(EmailInput()); }

        System.out.println("""
            ---------- Forgot Password ----------
            [1] Reset Password
            [2] Go Back
            """);
        System.out.print(" >> ");
        String input = InputReader.Get().nextLine();

        if (input.equals("1")) { staff.SetPassword(ResetPassword()); }
        else if (input.equals("2")) { staff = null; return 0; }
        else { App.WriteError("Only Select Between the Available Options"); }

        return 2;
    }

    private String ResetPassword() {
        while (true) {
            System.out.print("Enter New Password: ");
            String input = InputReader.Get().nextLine();

            if (InputReader.PasswordValidation(input)) return input;
            else App.WriteError("20 Characters: Uppercase, Lowercase, Numbers");
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
}
