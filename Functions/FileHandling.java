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
        else {
            // Techies Hard-coded
            Data.Get().AddTechy(new Technician("Harry Styles", "h.styles@company.com", "0411 222 333", "01234_xX_StyleS_Xx_56789", 1));
            Data.Get().AddTechy(new Technician("Niall Horan", "n.horan@company.com", "0422 333 444", "0_Xx_xX__HORAN__Xx_xX_0", 1));
            Data.Get().AddTechy(new Technician("Liam Payne", "l.payne@company.com", "0433 444 555", "XxXx_xXxX__PaYnE__XxXx_xXxX", 1));
            Data.Get().AddTechy(new Technician("Louis Tomlinson", "l.tomlinson@company.com", "0444 555 666", "21!_Jumbo_Junior_!12", 2));
            Data.Get().AddTechy(new Technician("Zayn Malik", "z.malik@company.com", "0455 666 777", "_Malik-Al-Igbeer_45?", 2));

            // Staff Hard-coded
            Data.Get().AddStaff(new Staff("Harry Styles", "h.styles@company.com", "0411 222 333", "01234_xX_StyleS_Xx_56789"));
            Data.Get().AddStaff(new Staff("Niall Horan", "n.horan@company.com", "0422 333 444", "0_Xx_xX__HORAN__Xx_xX_0"));
            Data.Get().AddStaff(new Staff("Liam Payne", "l.payne@company.com", "0433 444 555", "XxXx_xXxX__PaYnE__XxXx_xXxX"));
            Data.Get().AddStaff(new Staff("Louis Tomlinson", "l.tomlinson@company.com", "0444 555 666", "21!_Jumbo_Junior_!12"));
            Data.Get().AddStaff(new Staff("Zayn Malik", "z.malik@company.com", "0455 666 777", "_Malik-Al-Igbeer_45?"));
            Data.Get().AddStaff(new Staff("Sam Syne", "s.syne@company.com", "0466 777 888", "01234ABCDE56789fghij"));
            Data.Get().AddStaff(new Staff("Juliet Jameson", "j.jameson@company.com", "0477 888 999", "abcde01234FGHIJ56789"));
            Data.Get().AddStaff(new Staff("Jacob Heru", "j.heru@company.com", "0488 999 000", "SecretPassword_No0neCanGuess_THIS"));
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
        else {
            // Tickets Hard-coded
            Data.Get().AddTicket(new Ticket("T-100", "s.syne@company.com", "h.styles@company.com", 
            "\"Hey, I'm trying to use the new Editing Software but am running into some issues with the updated features, how do I use those?\"", 
            "LOW", "OPEN"));

            Data.Get().AddTicket(new Ticket("T-105", "s.syne@company.com", "z.malik@company.com", 
            "\"I've lost access to the 'Project T' Team's shared resources. I need to get major changes done by tonight!\"", 
            "HIGH", "ARCHIVED"));

            Data.Get().AddTicket(new Ticket("T-110", "j.jameson@company.com", "l.payne@company.com", 
            "\"The Cloud is slow today, took me 15 minutes to load my dashboard.\"", 
            "MEDIUM", "RESOLVED"));

            Data.Get().AddTicket(new Ticket("T-115", "j.jameson@company.com", "l.tomlinson@company.com", 
            "\"I've lost it! All my data!! I need a back-up recovery of 'Project L' Version 13.42!\"", 
            "HIGH", "OPEN"));
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
}
