package Menus;
import Functions.*;
import Classes.Ticket;
import java.util.ArrayList;

public class TicketOpen {
    public int Menu() {
        ArrayList<Ticket> tickets = Data.Get().GetStaffTickets();
        if (tickets.size() > 0) { 
            System.out.printf("%-5s | %-30s | %-30s | %-10s | %-10s | %-100s %n", "ID", "Staff Sent By", "Technician", "Severity", "Status", "Description");
            for (Ticket ticket : tickets) { ticket.View(); }
        }
        else System.out.println("No OPEN Tickets Found");

        System.out.println("\n\nWould you like to change the statys of a ticket? Enter Y or N: \n");
        System.out.print(" >> ");
        String statusCheck = InputReader.Get().nextLine();
        if (statusCheck.equals("Y")) {
            System.out.println("Enter the ticket ID that you would like to change the status of: \n");
            System.out.print(" >> ");
            String ticketID = InputReader.Get().nextLine();
            boolean found = false;
            for (Ticket ticket : tickets){
                if (ticketID.equals(ticket.GetID())) {
                    found = true;
                    System.out.println("Enter the new status (OPEN, RESOLVED, UNRESOLVED, ARCHIVED): ");
                    String newStatus = InputReader.Get().nextLine();
                    ticket.SetStatus(newStatus);
                    System.out.println("Status of ticket " + ticketID + " changed to " + newStatus);
                    

                } else if (!found) {System.out.println("Ticket ID not found. Please re-enter, or enter 'exit': \n"); }
            }
            
        }

        System.out.println("Enter 'exit' to Go Back");
        System.out.print(" >> ");
        String input = InputReader.Get().nextLine();

        if (input.equals("exit")) return 3;
        else return 4;
    }
}
