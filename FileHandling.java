import Classes.*;
import java.io.File;
import java.util.Scanner;
// ---------------------------------------------------------------------------------------------------- //
public class FileHandling {
    public void SetUp_Techs() {
        try {
            File file = new File("Data/techs.csv");
            Scanner reader = new Scanner(file);
            reader.nextLine();

            while (reader.hasNextLine()) {
                String[] user = reader.nextLine().split(",");
                Data.Get().AddTechy(new Technician(user[0], user[1], user[2], user[3], Integer.parseInt(user[4])));
            }

            reader.close();
        }
        catch (Exception e) { System.out.println("Error: " + e); }
    }
    // ---------------------------------------------------------------------------------------------------- //
}
