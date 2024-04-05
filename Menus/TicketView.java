package Menus;
import Functions.*;
import Classes.Ticket;
import java.util.ArrayList;

public class TicketView {
    public int Menu() {
        ArrayList<Ticket> tickets = Data.Get().GetStaffTickets();
        if (tickets.size() > 0) { 
            System.out.printf("%-5s | %-30s | %-30s | %-10s | %-10s | %-100s %n", "ID", "Staff Sent By", "Technician", "Severity", "Status", "Description");
            for (Ticket ticket : tickets) { ticket.View(); }
        }
        else System.out.println("No OPEN Tickets Found");

        System.out.println("Enter 'exit' to Go Back");
        System.out.print(" >> ");
        String input = InputReader.Get().nextLine();

        if (input.equals("exit")) return 3;
        else return 4;
    }
}
