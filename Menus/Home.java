package Menus;
import Functions.*;

public class Home {
    public int Menu() {
        System.out.println("""
            ---------- %s ----------
            [1] View Tickets
            [2] Open Ticket
            [3] Log Out
            [4] Exit
            """.formatted(Data.Get().GetActiveStaff().GetName()));
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
