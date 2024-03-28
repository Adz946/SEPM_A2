public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        FileHandling fileHandling = new FileHandling();
        
        fileHandling.SetUp_Techs();
        boolean menuCheck = false;
        
        while (true) {
            if (menuCheck) { menuCheck = menu.MainMenu(); }
            else { menuCheck = menu.LogInMenu(); }

            for (int i = 0; i < 50; i++) { System.out.print("-"); }
            System.out.println();
        }
    }
}