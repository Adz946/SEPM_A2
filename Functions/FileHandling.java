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
            System.out.println("Error: " + e.getMessage()); 
            for (StackTraceElement element : e.getStackTrace()) { System.out.println(element); }
        }
    }

    private void UserReader() throws Exception {
        String normalizedPath = Paths.get(basePath + "/users.csv").normalize().toString();
        File file = new File(normalizedPath);

        if (file.exists()) { 
            Scanner reader = new Scanner(file);
            reader.nextLine(); // HEADER

            while (reader.hasNextLine()) {
                String[] user = reader.nextLine().split(",");
                Staff staff = null;

                if (user.length == 5) { 
                    staff = new Technician(user[0], user[1], user[2], user[3], Integer.parseInt(user[4]));
                    Data.Get().AddTechy((Technician)staff); 
                }    
                else { staff = new Staff(user[0], user[1], user[2], user[3]); }  
                
                Data.Get().AddStaff(staff);  
            }

            reader.close();
        } 
        else { System.out.println("File does not exist: " + file); }
    }

    private void TicketReader() throws Exception {
        Pattern LINE_PATTERN = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String normalizedPath = Paths.get(basePath + "/tickets.csv").normalize().toString();
        File file = new File(normalizedPath);

        if (file.exists()) {
            Scanner reader = new Scanner(file);
            reader.nextLine(); // HEADER

            while (reader.hasNextLine()) {
                String[] ticket = LINE_PATTERN.split(reader.nextLine(), -1);
                String status = ticket[5].trim();

                if (status == "RESOLVED" || status == "UNRESOLVED") {
                    if (App.DateCheck(ticket[6].trim(), 24)) { status = "ARCHIVED"; }
                }
                Data.Get().AddTicket(new Ticket(ticket[0].trim(), ticket[1].trim(), ticket[2].trim(), ticket[3].trim(), ticket[4].trim(), status, ticket[6].trim()));
            }

            reader.close();
        }
    }
    // ---------------------------------------------------------------------------------------------------- //   
    public void SetUpExit() {
        try {
            UserWriter();
            TicketWriter();
            System.out.println("All changes have been saved");
        }
        catch (Exception e) { App.WriteError("Error: " + e.getMessage()); }
    }

    private void UserWriter() throws Exception {
        String normalizedPath = Paths.get(basePath + "/users.csv").normalize().toString();
        File file = new File(normalizedPath);

        if (file.exists()) {
            PrintWriter writer = new PrintWriter(new FileWriter(file, false));
            writer.println("Name,Email,Mobile,Password,Level");

            for (Staff staff : Data.Get().GetAllStaff()) { writer.println(staff.toString()); }
            writer.close();
        }
   }
    
   private void TicketWriter() throws Exception {
		String normalizedPath = Paths.get(basePath + "/tickets.csv").normalize().toString();
		File file = new File(normalizedPath);

		if (file.exists()) {
			PrintWriter writer = new PrintWriter(new FileWriter(file, false));
			writer.println("ID,SMail,TMail,Description,Severity,Status");

			for (Ticket ticket : Data.Get().GetAllTickets()) { writer.println(ticket.toString()); }
			writer.close();
		}
	}
}
