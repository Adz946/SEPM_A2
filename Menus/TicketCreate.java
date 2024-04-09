package Menus;
import Classes.Ticket;
import Functions.Data;
import Functions.InputReader;

public class TicketCreate {
    private static String description, severity;
    public TicketCreate() { Reset(); }

    private void Reset() {
        description = "";
        severity = "";
    }
    // ---------------------------------------------------------------------------------------------------- //
    public int Menu() {
        System.out.printf("---------- Create Ticket ---------- %n" +
        "[1] Severity : %s %n" +
        "[2] Description : %s %n" +
        "[3] Create %n" +
        "[4] Go Back %n", severity, description);
        System.out.print(" >> ");
        String input = InputReader.Get().nextLine();

        if (input.equals("1")) { severity = SeveritySelect(); }
        else if (input.equals("2")) { description = DescriptionInput(); }
        else if (input.equals("3")) { CreateTicket(); return 3; }
        else if (input.equals("4")) { return 3; }
        else { App.WriteError("Only Select Between the Available Options"); }

        return 5;
    }
    // ---------------------------------------------------------------------------------------------------- //
    private void CreateTicket() {
        String id = "T-" + Data.Get().GetNewTicketID();
        String sMail = Data.Get().GetActiveStaff().GetEmail();

        String tMail = "n.horan@company.com";
        if (severity.equals("HIGH")) { tMail = "z.malik@company.com"; }

        Data.Get().AddTicket(new Ticket(id, sMail, tMail, description, severity));
        System.out.println("Ticket Added Successfully");

        Reset();
    }

    private String SeveritySelect() {
        while (true) {
            System.out.println("[1] LOW \n[2] MEDIUM \n[3] HIGH");
            System.out.print(" >> ");
            String input = InputReader.Get().nextLine();

            if (input.equals("1")) { return "LOW"; }
            else if (input.equals("2")) { return "MEDIUM"; }
            else if (input.equals("3")) { return "HIGH"; }
            else { App.WriteError("Only Select Between the Available Options"); }
        }
    }

    private String DescriptionInput() {
        while (true) {
            System.out.print("Ticket Description: ");
            String input = InputReader.Get().nextLine();

            if (input.length() > 145) { App.WriteError("Description is too Long"); }
            else return "\"" + input + "\"";
        }
    }
}
