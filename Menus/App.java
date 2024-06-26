package Menus;
import Functions.*;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;
// ---------------------------------------------------------------------------------------------------- //
public class App {
    private static Random random = new Random();
    private static FileHandling fileHandling;
    public App(FileHandling fh) { fileHandling = fh; }
    
    public void Run() {
        LogIn login = new LogIn();                  // MENU 0 : LogIn
        Register register = new Register();         // MENU 1 : Register
        ForgotPass forgotPass = new ForgotPass();   // MENU 2 : Forgot Pass
        Home home = new Home();                     // MENU 3 : Home
        TicketView view = new TicketView();         // MENU 4 : View Tickets
        TicketCreate create = new TicketCreate();   // MENU 5 : Open Ticket
        TicketReport report = new TicketReport();   // MENU 6 : Ticket Report       

        int menuNum = 0;

        while (true) {
            if (menuNum == 0) {  menuNum = login.Menu(); }
            else if (menuNum == 1) { menuNum = register.Menu(); }
            else if (menuNum == 2) { menuNum = forgotPass.Menu(); }
            else if (menuNum == 3) { menuNum = home.Menu(); }
            else if (menuNum == 4) { menuNum = view.Menu(); }
            else if (menuNum == 5) { menuNum = create.Menu(); }
            else if (menuNum == 6) { menuNum = report.Menu(); }
            else { 
                App.WriteError("An Error Has Occured. Opening LogIn Page");
                menuNum = 0; 
            }

            for (int i = 0; i < 75; i++) { System.out.print("-"); }
            System.out.println();
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    public static String ticketAssignment(String severity) {
        int severityLevel = severity.equals("HIGH") ? 2 : 1;
        HashMap<String, Integer> openTickets = TicketData.Get().GetTechyOpenTickets(severityLevel);

        Optional<Integer> minValue = openTickets.values().stream().min(Integer::compare);
        if (minValue.isPresent()) {
            List<String> techsWithMinTickets = openTickets.entrySet().stream().filter(entry -> entry.getValue().equals(minValue.get()))
                .map(Map.Entry::getKey).collect(Collectors.toList());
            
            if (techsWithMinTickets.size() == 1) { return techsWithMinTickets.get(0); }
            else {
                int randomIndex = random.nextInt(techsWithMinTickets.size());
                return techsWithMinTickets.get(randomIndex);
            }
        }
        else return null;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public static boolean DateCheck(String dateTime) {
        LocalDateTime now = LocalDateTime.now(), dt = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a", Locale.US));
        long hoursBetween = Duration.between(dt, now).toHours();
        return hoursBetween > 24;
    }

    public static void WriteError(String msg) { System.out.println("\033[1;31m [" + msg + "] \033[0m \n"); }
    // RED TEXT: \033[1:31m | RESET: \033[0;0m

    public static void WriteSuccess(String msg) { System.out.println("\033[1;32m [" + msg + "] \033[0m \n"); }
    // GREEN TEXT: \033[1:32m | RESET: \033[0;0m

    public static void ExitProgram() {
        System.out.println("Terminating Program. Please Wait A Moment...");     
        if (fileHandling != null) { fileHandling.SetUpExit(); }
        
        InputReader.Close();
        System.exit(0);
    }
    // ---------------------------------------------------------------------------------------------------- //
}
