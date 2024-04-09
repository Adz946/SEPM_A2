package Menus;
import Functions.*;
import Classes.Staff;
import Classes.Technician;
import Classes.Ticket;
import java.util.ArrayList;

public class TicketOpen {
    public int Menu() {
<<<<<<< Updated upstream
=======
        Staff loggedInStaff = Data.Get().GetActiveStaff();
        Boolean valid = false;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
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
=======
            String statusCheck = InputReader.Get().nextLine();
            if (statusCheck.equals("Y")) {
                if (loggedInStaff instanceof Technician) {
                }
                 else {
                    System.out.println("You are not a technician, you may not change the status of a ticket.");
                    return 3;
                }
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
                                "[2] RESOLVED  \n" +
                                "[3] UNRESOLVED  \n" +
                                "[4] Exit \n", currentStatus);
                        System.out.print(" >> ");
                        String newStatus = "";
                        while (newStatus.equals("")) {
                            String input = InputReader.Get().nextLine();

                            if (input.equals("1")) { 
                                newStatus = "OPEN"; 
                            } else if (input.equals("2")) { 
                                newStatus = "RESOLVED"; 
                            } else if (input.equals("3")) {
                                newStatus = "UNRESOLVED"; 
                            }
                            else if (input.equals("4")) {
                                return 3;
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
>>>>>>> Stashed changes
            }
            
        }

        System.out.println("Enter 'exit' to Go Back");
        System.out.print(" >> ");
        String input = InputReader.Get().nextLine();

        if (input.equals("exit")) return 3;
        else return 4;
    }
}
