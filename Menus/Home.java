package Menus;
import Functions.*;

public class Home {
    public int Menu() {
        System.out.printf("---------- %s ---------- \n" +
           "[1] View Tickets \n" +
           "[2] Open Ticket \n" +
           "[3] Log Out \n" +
           "[4] Exit \n", Data.Get().GetActiveStaff().GetName());
        System.out.print(" >> ");
        String input = InputReader.Get().nextLine();

        if (input.equals("1")) { return 4; }
        else if (input.equals("2")) { return 5; }
        else if (input.equals("3")) { return 0; }
        else if (input.equals("4")) { App.ExitProgram(); }
        else App.WriteError("Only Select Between the Available Options");

        return 3;
    }
}
