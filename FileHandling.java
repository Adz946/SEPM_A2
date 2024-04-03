import Classes.*;
import java.io.File;
import java.util.Scanner;
// ---------------------------------------------------------------------------------------------------- //
public class FileHandling {
    // ---------------------------------------------------------------------------------------------------- //
    public void SetUp() {
        try {
            File file = new File("Data/users.csv");
            Scanner reader = new Scanner(file);
            reader.nextLine(); // HEADER

            while (reader.hasNextLine()) {
                String[] user = reader.nextLine().split(",");
                Staff staff = new Staff(user[0], user[1], user[2], user[3]);
                Data.Get().AddStaff(staff);
                
                if (user.length == 5) { Data.Get().AddTechy(new Technician(user[0], user[1], user[2], user[3], Integer.parseInt(user[4]))); }           
            }

            reader.close();
        }
        catch (Exception e) { System.out.println("Error: " + e); }
    }
    // ---------------------------------------------------------------------------------------------------- //
}
