package Menus;
import Classes.Ticket;
import Functions.InputReader;

public class TicketModify {
    private Ticket toModify;
    private String status;
    private String severity;
    public TicketModify(Ticket ticket) { toModify = ticket; }

    private void Update() {
        status = toModify.GetStatus();
        severity = toModify.GetSeverity();
    }
    // ---------------------------------------------------------------------------------------------------- //
    public void Menu() {
        while (true) {
            Update();
            toModify.View();
            System.out.println();

            System.out.println("[1] Update Status \n[2] Update Severity \n[3] Go Back");
            System.out.print(" >> ");
            String input = InputReader.Get().nextLine();

            switch (input) {
                case "1": 
                    StatusMenu(); 
                    break;
                case "2": 
                    SeverityMenu(); 
                    break;
                case "3": return;
                default: App.WriteError("Only Select Between the Available Options");
            }
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    private void StatusMenu() {
        if (status.equals("CANCELLED")) App.WriteError("Ticket Cancelled! Cannot Modify");
        else if (status.equals("ARCHIVED") && App.DateCheck(toModify.GetArchivedDT())) App.WriteError("Cannot Change the Status of Archived Ticket After 24 Hrs");
        else {
            String newStatus = StatusSelect();
            if (newStatus == null) App.WriteError("Cannot Close an Archived Ticket");
            else if (status.equals(newStatus)) App.WriteError("Ticket Status Remains Unchanged");
            else { 
                toModify.SetStatus(newStatus);
                App.WriteSuccess("Ticket Status Updated");
            }
        }
    }

    private String StatusSelect() {
        while (true) {
            System.out.println("Select a new Status: \n" +
            "[1] OPEN       [Open Ticket] \n" +
            "[2] RESOLVED   [Close Ticket + Fixed] \n" +
            "[3] UNRESOLVED [Close Ticket + Issues] \n" + 
            "[4] Go Back");
            System.out.print(" >> ");
            String input = InputReader.Get().nextLine();

            switch (input) {
                case "1": return "OPEN";
                case "2": return status.equals("ARCHIVED") ? null : "RESOLVED";
                case "3": return status.equals("ARCHIVED") ? null : "UNRESOLVED";
                case "4": return status;
                default: App.WriteError("Only Select Between the Available Options");
            }
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    private void SeverityMenu() {
        if (status.equals("CANCELLED")) App.WriteError("Ticket Cancelled! Cannot Modify");
        else if (!status.equals("OPEN")) App.WriteError("Cannot Change the Severity of a Closed / Archived Ticket");
        else {
            String newSeverity = SeveritySelect();
            if (newSeverity == null) App.WriteError("Ticket Severity Remains Unchanged");
            else {
                if ((severity.equals("HIGH") && !newSeverity.equals("HIGH")) || (!severity.equals("HIGH") && newSeverity.equals("HIGH"))) 
                    { toModify.SetTechy(App.ticketAssignment(newSeverity)); }

                toModify.SetSeverity(newSeverity);
                App.WriteSuccess("Ticket Severity Updated");
            }
        }
    }

    private String SeveritySelect() {
        while (true) {
            System.out.println("Select a new Severity: \n" +
            "[1] LOW        [Minor Issue] \n" +
            "[2] MEDIUM     [Moderate Issue] \n" +
            "[3] HIGH       [Critical Issue] \n"  + 
            "[4] Go Back"); 
            
            System.out.print(" >> ");
            String input = InputReader.Get().nextLine();

            switch (input) {
                case "1": return severity.equals("LOW") ? null : "LOW";
                case "2": return severity.equals("MEDIUM") ? null : "MEDIUM";
                case "3": return severity.equals("HIGH") ? null : "HIGH";
                case "4": return severity;
                default: App.WriteError("Only Select Between the Available Options");
            }
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
}
