package Menus;
import Functions.*;
import Classes.Ticket;

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
        else if (input.equals("3")) { return CreateTicket(); }
        else if (input.equals("4")) { Reset(); return 3; }
        else { App.WriteError("Only Select Between the Available Options"); }

        return 5;
    }
    // ---------------------------------------------------------------------------------------------------- //
    private int CreateTicket() {
        if (!description.equals("") && !severity.equals("")) {
            String id = "T-" + TicketData.Get().GetNewTicketID();
            String sMail = StaffData.Get().GetActiveStaff().GetEmail();     
            String tMail = App.ticketAssignment(severity);

            TicketData.Get().AddTicket(new Ticket(id, sMail, tMail, description, severity));
            System.out.println("Ticket Added Successfully!");
            System.out.println("Ticket ID: " + id + " | Assigned Technician: " + tMail);

            App.WriteSuccess("Ticket Created Successfully");
            Reset();
            return 3;
        }
        else App.WriteError("Fill ALL Fields");
        return 5;
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
    // ---------------------------------------------------------------------------------------------------- //
}
