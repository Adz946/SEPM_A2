import Menus.*;
import Functions.*;

public class Main {
    public static void main(String[] args) {
        String filePath; 

        if (args.length > 0) { filePath = args[0]; } 
        else { filePath = "./Data/";  }

        System.out.println("Using data path: " + filePath);
        
        FileHandling fileHandling = new FileHandling(filePath);
        fileHandling.SetUpEntry(); // Seeds Data
        
        App app = new App(fileHandling);
        app.Run(); // Runs the App Loop
    }
}