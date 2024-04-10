package Menus;
import java.time.LocalDateTime;

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

            if (input.equals("1")) { 
                if (toModify.GetStatus().equals("ARCHIVED")) {
                    if (!App.DateCheck(toModify.GetDateTime(), 48)) toModify.SetStatus(StatusSelect());
                    else App.WriteError("Cannot Change the Status of Archived Ticket (Over 24 Hrs)");
                }
            }
            else if (input.equals("2")) { break; }
            else { App.WriteError("Only Select Between the Available Options"); }
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    private String StatusSelect() {
        while (true) {
            System.out.println("Select a new Status: \n" +
            "[1] OPEN       [Open Ticket] \n" +
            "[2] RESOLVED   [Close Ticket + Fixed] \n" +
            "[3] UNRESOLVED [Close Ticket + Issues]");
            System.out.print(" >> ");
            String input = InputReader.Get().nextLine();

            String newStat = "";
            if (input.equals("1")) { newStat = "OPEN"; }
            else if (input.equals("2") || input.equals("3")) { 
                if (toModify.GetStatus().equals("ARCHIVED")) App.WriteError("Cannot Close an Archived Ticket");
                else {
                    if (input.equals("2")) { newStat = "RESOLVED";  }
                    else if (input.equals("3")) { newStat = "UNRESOLVED"; }
                }
            }
            else { App.WriteError("Only Select Between the Available Options"); }

            if (newStat != "") {
                if (newStat.equals(toModify.GetStatus())) { App.WriteError("Status '" + newStat + "' In Use"); }
                else {
                    toModify.SetDateTime(LocalDateTime.now()); 
                    return newStat;
                }
            }
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
}
