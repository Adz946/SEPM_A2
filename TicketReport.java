package Menus;

import Classes.*;
import Functions.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;

public class TicketReport {
    public int Menu() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); 

        Date startDate = null, endDate = null;

        while (startDate == null) {
            System.out.println("Enter start date (dd/mm/yyyy): ");
            String startDateStr = scanner.nextLine();
            try {
                startDate = sdf.parse(startDateStr);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd/mm/yyyy format.");
            }
        }

        while (endDate == null) {
            System.out.println("Enter end date (dd/mm/yyyy): ");
            String endDateStr = scanner.nextLine();
            try {
                endDate = sdf.parse(endDateStr);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd/mm/yyyy format.");
            }
        }

        printReport(startDate, endDate);
        return 3;  
    }

    private void printReport(Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ticket Report from " + sdf.format(startDate) + " to " + sdf.format(endDate));
        System.out.printf("| %-5s | %-45s | %-45s | %-10s | %-10s | %-20s | %-20s | %n", 
            "ID", "Submitter", "Technician", "Severity", "Status", "Opened Date", "Time to Close");

        for (Ticket ticket : Data.Get().GetAllTickets()) {
            try {
                Date openedDate = sdf.parse(ticket.GetOpenedDT());
                if (!openedDate.before(startDate) && !openedDate.after(endDate)) {
                    String timeToClose = ticket.GetArchivedDT().equals("-") ? "N/A" : getTimeToClose(openedDate, sdf.parse(ticket.GetArchivedDT())) + " days";
                    ticket.View(); 
                }
            } catch (ParseException e) {
                System.out.println("Error parsing date for ticket " + ticket.GetID());
            }
        }
    }

    private String getTimeToClose(Date open, Date close) {
        long days = (close.getTime() - open.getTime()) / (1000 * 60 * 60 * 24);
        return String.valueOf(days);
    }
}
