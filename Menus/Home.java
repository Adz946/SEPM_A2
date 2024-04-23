package Menus;
import Functions.*;
import Classes.Staff;
import Classes.Technician;

public class Home {
    public int Menu() {  
        Staff currentStaff = Data.Get().GetActiveStaff(); 
        System.out.printf("---------- %s ---------- \n" +
           "[1] View Tickets \n" +
           "[2] Open Ticket \n" +
           (currentStaff instanceof Technician ? "[3] Generate Report \n" : "") +
           "[4] Log Out \n" +
           "[5] Exit \n", Data.Get().GetActiveStaff().GetName());
        System.out.print(" >> ");
        String input = InputReader.Get().nextLine();

        if (input.equals("1")) { return 4; }
        else if (input.equals("2")) { return 5; }
        else if (input.equals("3") && currentStaff instanceof Technician) { return 6; }
        else if (input.equals("4")) { return 0; }
        else if (input.equals("5")) { App.ExitProgram(); }
        else App.WriteError("Only Select Between the Available Options");

        return 3;
    }
}
