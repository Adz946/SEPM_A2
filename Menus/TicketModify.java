package Menus;
import java.time.LocalDateTime;

import Classes.Ticket;
import Menus.TicketCreate;
import Functions.InputReader;

public class TicketModify {
    private Ticket toModify;
    public TicketModify(Ticket ticket) { toModify = ticket; }
    // ---------------------------------------------------------------------------------------------------- //
    public void Menu() {
        while (true) {
            toModify.View();
            System.out.println();

            System.out.println("[1] Update Status \n[2] Update Severity \n[3] Go Back");
            System.out.print(" >> ");
            String input = InputReader.Get().nextLine();

            if (input.equals("1")) { 
                if (toModify.GetStatus().equals("ARCHIVED")) {
                    if (!App.DateCheck(toModify.GetDateTime(), 48)) toModify.SetStatus(StatusSelect());
                    else App.WriteError("Cannot Change the Status of Archived Ticket (Over 24 Hrs)");
                } else {
                    toModify.SetStatus(StatusSelect());
                }
            }
            else if (input.equals("2")) { 
                if (!toModify.GetStatus().equals("OPEN")) 
                    App.WriteError("Cannot Change the Severity of Closed or Archived tickets.");
                else toModify.SetSeverity(SeveritySelect()); 
            }
            else if (input.equals("3")) { break; }
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
    
    private String SeveritySelect() {
        String currentSeverity = toModify.GetSeverity();
        
        while (true) {
            System.out.println("Select a new Severity: \n" +
            "[1] LOW        [Minor Issue] \n" +
            "[2] MEDIUM     [Moderate Issue] \n" +
            "[3] HIGH       [Critical Issue] \n"  + 
            "[4] Go Back"); 
            
            System.out.print(" >> ");
            String input = InputReader.Get().nextLine();

            if (input.equals("1") && !currentSeverity.equals("LOW")) {
                updateTechnicianIfNeeded(currentSeverity, "LOW");
                return "LOW";
            } else if (input.equals("2") && !currentSeverity.equals("MEDIUM")) {
                updateTechnicianIfNeeded(currentSeverity, "MEDIUM");
                return "MEDIUM";
            } else if (input.equals("3") && !currentSeverity.equals("HIGH")) {
                updateTechnicianIfNeeded(currentSeverity, "HIGH");
                return "HIGH";
            } else if (input.equals("4")) {
                return currentSeverity; 
            } else {
                App.WriteError("Only Select Between the Available Options or Change Required");
            }
        }
    }
    
    private void updateTechnicianIfNeeded(String currentSeverity, String newSeverity) {
        if ((currentSeverity.equals("HIGH") && (newSeverity.equals("LOW") || newSeverity.equals("MEDIUM"))) ||
            ((currentSeverity.equals("LOW") || currentSeverity.equals("MEDIUM")) && newSeverity.equals("HIGH"))) {
            
            String newTechnicianEmail = TicketCreate.assignTicketToTechnician(newSeverity);
            toModify.SetTechy(newTechnicianEmail); 
            System.out.println("Severity updated. New Technician assigned: " + newTechnicianEmail);
        }
    }
    
    // ---------------------------------------------------------------------------------------------------- //
}
