package Menus;
import Functions.*;
import Classes.Staff;
import Classes.Technician;

public class Home {
    public int Menu() {  
        Staff currentStaff = StaffData.Get().GetActiveStaff(); 
        String option2 = "[2] ";

        if (currentStaff instanceof Technician) option2 += "Generate Report";
        else option2 += "Open Ticket";

        System.out.printf("---------- %s ---------- \n" +
           "[1] View Tickets \n" +
           option2 + " \n" +
           "[3] Log Out \n" +
           "[4] Exit \n", currentStaff.GetName());
        System.out.print(" >> ");
        String input = InputReader.Get().nextLine();

        if (input.equals("1")) { return 4; }
        else if (input.equals("2")) { 
            if (currentStaff instanceof Technician) return 6;
            else return 5; 
        }
        else if (input.equals("3")) { return 0; }
        else if (input.equals("4")) { App.ExitProgram(); }
        else App.WriteError("Only Select Between the Available Options");

        return 3;
    }
}
