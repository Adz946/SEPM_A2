package Menus;
import Functions.*;
import Classes.Ticket;
import java.util.ArrayList;

public class TicketView {
    private ArrayList<String> ticketIDs;

    public int Menu() {
        ticketIDs = new ArrayList<>();

        ArrayList<Ticket> tickets = Data.Get().GetStaffTickets();
        if (tickets.size() > 0) { 
            System.out.printf("| %-10s | %-35s | %-35s | %-15s | %-15s | %n", "ID", "Staff Sent By", "Technician", "Severity", "Status");
            for (Ticket ticket : tickets) { ticket.View(); ticketIDs.add(ticket.GetID()); }
            System.out.println();

            System.out.println("[1] Cancel Ticket \n[2] Create Ticket \n[3] Exit");
            System.out.print(" >> ");
            String input = InputReader.Get().nextLine();

            if (input.equals("1")) { 
                Ticket ticket = Data.Get().GetTicket(CancelTicket());
                if (ticket != null) { ticket.SetStatus("UNRESOLVED"); }
             }
            else if (input.equals("2")) { return 5; }
            else if (input.equals("3")) { return 3; }
            else { App.WriteError("Only Select Between the Available Options"); }
        }
        else System.out.println("No OPEN Tickets Found");       
        return 3;
    }

    private String CancelTicket() {
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
