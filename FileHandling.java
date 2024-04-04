import Classes.*;
import java.io.File;
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
        File file = new File("Data/users.csv");
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

    private void TicketReader() throws Exception {
        Pattern LINE_PATTERN = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        File file = new File("Data/tickets.csv");
        Scanner reader = new Scanner(file);
        reader.nextLine(); // HEADER

        while (reader.hasNextLine()) {
            String[] ticket = LINE_PATTERN.split(reader.nextLine(), -1);
            Data.Get().AddTicket(new Ticket(ticket[0].trim(), ticket[1].trim(), ticket[2].trim(), ticket[3].trim(), ticket[4].trim(), ticket[5].trim()));
        }

        reader.close();
    }
    // ---------------------------------------------------------------------------------------------------- //
}
