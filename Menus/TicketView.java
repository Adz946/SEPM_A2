package Menus;
import Classes.*;
import Functions.*;
import java.util.ArrayList;

public class TicketView {
    private ArrayList<String> ticketIDs;
    // ---------------------------------------------------------------------------------------------------- //
    public int Menu() {
        ticketIDs = new ArrayList<>();
        boolean techy = false;
        if (Data.Get().GetActiveStaff() instanceof Technician) { techy = true; }

        ArrayList<Ticket> tickets = Data.Get().GetStaffTickets();
        if (!tickets.isEmpty()) { 
            System.out.printf("| %-5s | %-45s | %-45s | %-10s | %-10s | %-20s | %-20s | %n", "ID", "Staff Sent By", "Technician", "Severity", "Status", "Opened Date", "Archived Date");
            for (Ticket ticket : tickets) { ticket.View(); ticketIDs.add(ticket.GetID()); }
            System.out.println();

            String option1 = techy ? "[1] Modify Ticket" : "[1] Cancel Ticket";
            System.out.println(option1 + "\n[2] Create Ticket \n[3] Go Back");
            System.out.print(" >> ");
            String input = InputReader.Get().nextLine();

            if (input.equals("1")) { 
                Ticket ticket = Data.Get().GetTicket(TicketSelect());
                if (ticket != null) { 
                    if (techy) {
                        TicketModify modify = new TicketModify(ticket);
                        modify.Menu();
                    }
                    else ticket.SetStatus("CANCELLED"); 
                }
             }
            else if (input.equals("2")) { return 5; }
            else if (input.equals("3")) { return 3; }
            else { App.WriteError("Only Select Between the Available Options"); }
        }
        else App.WriteError("No OPEN Tickets Found");      
        return 3;
    }
    // ---------------------------------------------------------------------------------------------------- //
    private String TicketSelect() {
        while (true) {
            System.out.print("Ticket ID: ");
            String input = InputReader.Get().nextLine();

            if (input.equals("exit")) return null;
            else if (InputReader.IDValidation(input)) {
                for (String ID : ticketIDs) { if (ID.equals(input)) { return ID; } }
                App.WriteError("ID Cannot Be Found or is Incorrect");
            }
            else App.WriteError("Follow Format: 'T-XXX'");
            System.out.println("Enter 'exit' to Cancel\n");
        }
    }
}
