package Functions;
import Classes.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;
// ---------------------------------------------------------------------------------------------------- //
public class FileHandling {
    // ---------------------------------------------------------------------------------------------------- //
    public void SetUp() {
        try {
            UserReader();
            TicketReader();
        }
        catch (Exception e) { System.out.println("Error: " + e); }
    }

    private void UserReader() throws Exception {
        String normalizedPath = Paths.get("./Data/users.csv").normalize().toString();
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
    }

    private void TicketReader() throws Exception {
        Pattern LINE_PATTERN = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String normalizedPath = Paths.get("./Data/tickets.csv").normalize().toString();
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
    }
    // ---------------------------------------------------------------------------------------------------- //
}
