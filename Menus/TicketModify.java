package Menus;
import Classes.Ticket;
import Functions.InputReader;

public class TicketModify {
    private Ticket toModify;
    public TicketModify(Ticket ticket) { toModify = ticket; }
    // ---------------------------------------------------------------------------------------------------- //
    public void Menu() {
        while (true) {
            toModify.View();
            System.out.println();

            System.out.println("[1] Update Status \n[2] Go Back");
            System.out.print(" >> ");
            String input = InputReader.Get().nextLine();

            if (input.equals("1")) { toModify.SetStatus(StatusSelect()); }
            else if (input.equals("2")) { break; }
            else { App.WriteError("Only Select Between the Available Options"); }
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    private String StatusSelect() {
        while (true) {
            System.out.println("Select a new Status: \n" +
            "[1] OPEN       [Open Ticket] \n" +
            "[2] RESOLVED   [Close Ticket] \n" +
            "[3] UNRESOLVED [Close Ticket]");
            System.out.print(" >> ");
            String input = InputReader.Get().nextLine();

            String newStat = "";
            if (input.equals("1")) { newStat = "OPEN"; }
            else if (input.equals("2")) { newStat = "RESOLVED"; }
            else if (input.equals("3")) { newStat = "UNRESOLVED"; }
            else { App.WriteError("Only Select Between the Available Options"); }

            if (newStat != "") {
                if (newStat.equals(toModify.GetStatus())) { App.WriteError("Status '" + newStat + "' In Use"); }
                else return newStat;
            }
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
}
