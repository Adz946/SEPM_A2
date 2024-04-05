package Menus;
import Functions.*;

public class App {
    public void Run() {
        LogIn login = new LogIn(); // MENU 0 : LogIn
        Register register = new Register(); // MENU 1 : Register
        ForgotPass forgotPass = new ForgotPass(); // MENU 2 : Forgot Pass
        Home home = new Home(); // MENU 3 : Home
        TicketView view = new TicketView(); // MENU 4 : View Tickets
        // MENU 5 : Create Tickets
        int menuNum = 0;

        while (true) {
            if (menuNum == 0) {  menuNum = login.Menu(); }
            else if (menuNum == 1) { menuNum = register.Menu(); }
            else if (menuNum == 2) { menuNum = forgotPass.Menu(); }
            else if (menuNum == 3) { menuNum = home.Menu(); }
            else if (menuNum == 4) { menuNum = view.Menu(); }
            else if (menuNum == 6) { ExitProgram(); }
            else { menuNum = 0; }

            for (int i = 0; i < 75; i++) { System.out.print("-"); }
            System.out.println();
        }
    }

    public static void WriteError(String msg) {
        System.out.println("\033[1;31m [" + msg + "] \033[0m");
        // RED TEXT: \033[1:31m | RESET: \033[0;0m
    }

    public static void ExitProgram() {
        System.out.println("Terminating Program. Please Wait A Moment...");
        InputReader.Close();
        System.exit(0);
    }
}