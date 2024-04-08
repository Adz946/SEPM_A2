package Functions;
import Classes.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
// ---------------------------------------------------------------------------------------------------- //
public class FileHandling {
    // ---------------------------------------------------------------------------------------------------- //
    private String basePath = "./Data/";
    
    public void SetUp(String filePath) {
       
       basePath = filePath;
        try {
            UserReader();
            TicketReader();
        }
        catch (Exception e) { System.out.println("Error: " + e); }
    }

    private void UserReader() throws Exception {
        String normalizedPath = Paths.get(basePath + "users.csv").normalize().toString();
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
                else { 
                    staff = new Staff(user[0], user[1], user[2], user[3]);             
                    Data.Get().AddStaff(staff);    
                }
                
            }

            reader.close();
        } 
        else {
            System.out.println("File does not exist: " + file);    
        }
    }

    private void TicketReader() throws Exception {
        Pattern LINE_PATTERN = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String normalizedPath = Paths.get(basePath + "tickets.csv").normalize().toString();
        File file = new File(normalizedPath);

        if (file.exists()) {
            Scanner reader = new Scanner(file);
            reader.nextLine(); // HEADER

            while (reader.hasNextLine()) {
                String[] ticket = LINE_PATTERN.split(reader.nextLine(), -1);
                Data.Get().AddTicket(new Ticket(ticket[0].trim(), ticket[1].trim(), ticket[2].trim(), ticket[3].trim(), ticket[4].trim(), ticket[5].trim()));
            }

            reader.close();
        }
        else {
            System.out.println("File does not exist: " + file);
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    public static void NewStaff(Staff newStaff) throws IOException {
        String normalizedPath = Paths.get("./Data/users.csv").normalize().toString();
        File file = new File(normalizedPath);

        if (file.exists()) {
            FileWriter fWriter = new FileWriter(file, true);
            PrintWriter writer = new PrintWriter(fWriter);
            writer.println(newStaff.toString());
            writer.close();
        }
        else { System.out.println(".../Changes have been made!"); }
    }
    // ---------------------------------------------------------------------------------------------------- //
    
    public void userWriter() {
       Data data = Data.Get();
       Collection<Staff> allStaff = data.GetAllStaff();
       Collection<Technician> allTechies = data.GetAllTechies();

       try (FileWriter fileWriter = new FileWriter(basePath + "/users.csv", false);
            PrintWriter printWriter = new PrintWriter(fileWriter)) {
           printWriter.println("Name,Email,Mobile,Password,Level");
           
           // Write Staff members
           for (Staff staff : allStaff) {
               String line = String.format("%s,%s,%s,%s", 
                       staff.GetName(),
                       staff.GetEmail(),
                       staff.GetMobile(),
                       staff.GetPassword());
               printWriter.println(line);
           }

           // Write Technicians
           for (Technician tech : allTechies) {
               String line = String.format("%s,%s,%s,%s,%d", 
                       tech.GetName(),
                       tech.GetEmail(),
                       tech.GetMobile(),
                       tech.GetPassword(),
                       tech.TechLevel());
               printWriter.println(line);
           }

       } catch (IOException e) {
           System.out.println("An error occurred while writing to the users.csv file: " + e.getMessage());
       }
   }
    
   public void ticketWriter() {
      Data data = Data.Get();
      Collection<Ticket> allTickets = data.GetAllTickets();
      
      try (FileWriter fileWriter = new FileWriter(basePath + "/tickets.csv", false);
           PrintWriter printWriter = new PrintWriter(fileWriter)) 
      {
              printWriter.println("ID,SMail,TMail,Description,Severity,Status");
              
              for (Ticket ticket : allTickets) {
                  String line = String.format("%s,%s,%s,%s,%s,%s",
                                              ticket.GetID(),
                                              ticket.GetStaff(),
                                              ticket.GetTechy(),
                                              ticket.GetDesc(),// Escape double quotes in issue text
                                              ticket.GetSeverity(),
                                              ticket.GetStatus());
                  printWriter.println(line);
              }
              
          } catch (IOException e) {
                System.out.println("An error occurred while writing to the tickets.csv file: " + e.getMessage());          
          }
   }

}
