package Functions;
import Menus.App;
import Classes.*;

import java.io.*;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.regex.Pattern;
// ---------------------------------------------------------------------------------------------------- //
public class FileHandling {
	private String basePath;

	public FileHandling(String path) {
		if (path != null) { basePath = path; }
		else { basePath = "./Data/"; }
	}
    // ---------------------------------------------------------------------------------------------------- //  
    public void SetUpEntry() {     
        try {
            UserReader();
            TicketReader();
        }
        catch (Exception e) { 
            // Debugging
            System.out.println("Error: " + e.getMessage());
            for (StackTraceElement element : e.getStackTrace()) { System.out.println(element); }

            // For User
            System.out.println("A Major Error Has Occured! Application Failing");
            System.exit(1);
        }
    }

    private void UserReader() throws IOException {
        String normalizedPath = Paths.get(basePath + "/users.csv").normalize().toString();
        File file = new File(normalizedPath);

        if (file.exists()) { 
            try (Scanner reader = new Scanner(file)) {
                reader.nextLine(); // HEADER

                while (reader.hasNextLine()) {
                    String[] user = reader.nextLine().split(",");
                    Staff staff = null;

                    if (user.length == 5) { 
                        staff = new Technician(user[0], user[1], user[2], user[3], Integer.parseInt(user[4]));
                        StaffData.Get().AddTechy((Technician)staff); 
                    }    
                    else if (user.length == 4) { staff = new Staff(user[0], user[1], user[2], user[3]); }  
                    else App.WriteError("User Reading Error");
                    
                    StaffData.Get().AddStaff(staff);  
                }
            }
        } else throw new FileNotFoundException("Users CSV was NOT Found");
    }

    private void TicketReader() throws IOException {
        Pattern LINE_PATTERN = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String normalizedPath = Paths.get(basePath + "/tickets.csv").normalize().toString();
        File file = new File(normalizedPath);

        if (file.exists()) {
            try (Scanner reader = new Scanner(file)) {
                reader.nextLine(); // HEADER

                while (reader.hasNextLine()) {
                    String[] ticket = LINE_PATTERN.split(reader.nextLine(), -1);
                    String status = ticket[5];
                    if ((status.equals("RESOLVED") || status.equals("UNRESOLVED")) && App.DateCheck(ticket[6])) status = "ARCHIVED";

                    if (ticket.length == 8) { 
                        TicketData.Get().AddTicket(new Ticket(ticket[0], ticket[1], ticket[2], ticket[3], ticket[4], status, ticket[6], ticket[7]));
                    } else if (ticket.length == 7) {
                        TicketData.Get().AddTicket(new Ticket(ticket[0], ticket[1], ticket[2], ticket[3], ticket[4], status, ticket[6], null));
                    }
                    else App.WriteError("Ticket Reading Error");
                }
            }
        } else throw new FileNotFoundException("Tickets CSV was NOT Found");
    }
    // ---------------------------------------------------------------------------------------------------- //   
    public void SetUpExit() {
        try {
            UserWriter();
            TicketWriter();
            App.WriteSuccess("All Changes Saved");
        }
        catch (Exception e) { App.WriteError("Error: " + e.getMessage()); }
    }

    private void UserWriter() throws IOException {
        String normalizedPath = Paths.get(basePath + "/users.csv").normalize().toString();
        File file = new File(normalizedPath);

        if (file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file, false))) {
                writer.println("Name,Email,Mobile,Password,Level");
                for (Staff staff : StaffData.Get().GetAllStaff()) { writer.println(staff.toString()); }
            }
        } else throw new FileNotFoundException("Users CSV was NOT Found");
   }
    
   private void TicketWriter() throws IOException {
		String normalizedPath = Paths.get(basePath + "/tickets.csv").normalize().toString();
		File file = new File(normalizedPath);

        if (file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file, false))) {
                writer.println("ID,SMail,TMail,Description,Severity,Status,OpenedDT,ArchivedDT");
                for (Ticket ticket : TicketData.Get().GetAllTickets()) { writer.println(ticket.toString()); }
            }
        } else throw new FileNotFoundException("Tickets CSV was NOT Found");
	}
}
