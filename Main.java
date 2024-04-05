import Menus.*;
import Functions.*;

public class Main {
    public static void main(String[] args) {
        FileHandling fileHandling = new FileHandling();
        fileHandling.SetUp(); // Seeds Data

        App app = new App();
        app.Run(); // Runs the App Loop
    }
}