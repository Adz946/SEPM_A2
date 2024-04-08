import Menus.*;
import Functions.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "./Data/"; 

        if (args.length > 0) {
           filePath = args[0];
        } 
        
        System.out.println("Using data path: " + filePath);
        
        FileHandling fileHandling = new FileHandling();
        fileHandling.SetUp(filePath); // Seeds Data
        App.setFileHandling(fileHandling);
        
        App app = new App();
        app.Run(); // Runs the App Loop
    }
}