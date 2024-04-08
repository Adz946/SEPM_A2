package Menus;
import Functions.*;
import Classes.Ticket;
import java.util.ArrayList;

public class TicketOpen {
    public int Menu() {
        Boolean valid = false;
        ArrayList<Ticket> tickets = Data.Get().GetStaffTickets();
        if (tickets.size() > 0) { 
            System.out.printf("%-5s | %-30s | %-30s | %-10s | %-10s | %-100s %n", "ID", "Staff Sent By", "Technician", "Severity", "Status", "Description");
            for (Ticket ticket : tickets) { 
                ticket.View(); 
            }
        } else {
            System.out.println("No OPEN Tickets Found");
        }

        while (!valid) {
            System.out.println("\nWould you like to change the status of a ticket? Enter Y or N: \n");
            System.out.print(" >> ");
            String statusCheck = InputReader.Get().nextLine();
            if (statusCheck.equals("Y")) {
                System.out.println("Enter the ticket ID that you would like to change the status of: \n");
                System.out.print(" >> ");
                String ticketID = InputReader.Get().nextLine();
                boolean found = false;
                for (Ticket ticket : tickets) {
                    if (ticketID.equals(ticket.GetID())) {
                        found = true;
                        String currentStatus = ticket.GetStatus();
                        System.out.printf("The current status of ticket: "+ ticketID + " is " + currentStatus + " Enter the new status: \n" +
                                "---------- %s ---------- \n" +
                                "[1] OPEN \n" +
                                "[2] CLOSED  \n" +
                                "[3] RESOLVED \n" +
                                "[4] UNRESOLVED \n" +
                                "[5] Exit \n", currentStatus);
                        System.out.print(" >> ");

                        String input = InputReader.Get().nextLine();
                        String newStatus = "";
                        while (newStatus.equals("")) {
                            
                            if (input.equals("1")) { 
                                newStatus = "OPEN"; 
                            } else if (input.equals("2")) { 
                                newStatus = "CLOSED"; 
                            } else if (input.equals("3")) { 
                                newStatus = "RESOLVED"; 
                            } else if (input.equals("4")) { 
                                newStatus = "UNRESOLVED"; 
                            } 

                            if (currentStatus.equals(newStatus)) { 
                                System.out.println("The new ticket status must be different than the old status.");
                            } else {
                                ticket.SetStatus(newStatus);
                                System.out.println("Status of ticket " + ticketID + " was " + currentStatus + ", now it has been changed to " + newStatus +".");
                                valid = true;

                            }
                        }
                    }
                }
                if (!found) {
                    System.out.println("No ticket found with that ID. \n");
                }
            } else if (statusCheck.equals("N")) {
                valid = true;
                return 3;
            }
        }
        return 3; 
    }
}
    