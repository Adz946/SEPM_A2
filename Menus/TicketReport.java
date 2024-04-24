package Menus;
import Classes.*;
import java.time.*;
import Functions.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.time.format.DateTimeFormatter;

public class TicketReport {
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static LocalDateTime start, end;
    public TicketReport() { Reset(); }

    private void Reset() {
        start = null;
        end = null;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public int Menu() {
        String startDT = "Not Set", endDT = "Not Set";
        if (start != null) { startDT = format.format(start); }
        if (end != null) { endDT = format.format(end); }

        System.out.printf("---------- Ticket Report ---------- %n" +
            "[1] Start Date : %s %n" +
            "[2] End Date : %s %n" +
            "[3] Print Report %n" +
            "[4] Go Back %n", startDT, endDT);
        System.out.print(" >> ");
        String input = InputReader.Get().nextLine();

        if (input.equals("1")) { start = StartInput(); }
        else if (input.equals("2")) { end = EndInput(); }
        else if (input.equals("3")) { printReport(); }
        else if (input.equals("4")) { return 3; }
        else { App.WriteError("Only Select Between the Available Options"); }

        return 6;
    }

    public void printReport() {
        if (start != null && end != null) {
            ArrayList<Ticket> tickets = TicketData.Get().GetReportTickets(start, end);

            if (!tickets.isEmpty()) {
                App.WriteSuccess("Printing Report");
                System.out.printf("| %-5s | %-45s | %-45s | %-10s | %-10s | %-20s | %-30s | %n", "ID", "Staff Sent By", "Technician", "Severity", "Status", "Opened Date", "Time Taken To Close");
                for (Ticket ticket : tickets) {
                    String timeToClose = "Ticket " + ticket.GetID() + " is Still Open";
                    if (!ticket.GetArchivedDT().equals("-")) { timeToClose = TimeToClose(ticket); }
                    ticket.Report(timeToClose);
                }   
            }
            else {
                App.WriteError("No Tickets Found Between " + format.format(start) + " and " + format.format(end)); 
            }
        }
        else { App.WriteError("Enter ALL Fields"); }
    }
    // ---------------------------------------------------------------------------------------------------- //
    public LocalDateTime StartInput() {
        while (true) {
            System.out.println("Use the Correct Format: [dd/mm/yyyy]");
            System.out.print("Enter Start Date: ");
            String input = InputReader.Get().nextLine();

            if (InputReader.DateValidation(input)) {
                try {
                    LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    return date.atTime(00, 00, 00);
                }
                catch (Exception e) { App.WriteError("Invalid Format"); }
            }
            else App.WriteError("Invalid Format");
        }
    }

    public LocalDateTime EndInput() {
        while (true) {
            System.out.println("Use the Correct Format: [dd/mm/yyyy]");
            System.out.print("Enter End Date: ");
            String input = InputReader.Get().nextLine();

            if (InputReader.DateValidation(input)) {
                try {
                    LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    return date.atTime(23, 59, 59);
                }
                catch (Exception e) { App.WriteError("Invalid Format"); }
            }
            else App.WriteError("Invalid Format");
        }
    }

    public String TimeToClose(Ticket ticket) {
        Instant opened = ticket.GetDT_Opened().atZone(ZoneId.systemDefault()).toInstant();
        Instant archived = ticket.GetDT_Archived().atZone(ZoneId.systemDefault()).toInstant();

        long minutesBetween = Duration.between(opened, archived).toMinutes();
        long days = TimeUnit.MINUTES.toDays(minutesBetween);
        long hours = TimeUnit.MINUTES.toHours(minutesBetween) % 24;
        long minutes = minutesBetween % 60;

        return days + " days, " + hours + " hours, " + minutes + " minutes";
    }
    // ---------------------------------------------------------------------------------------------------- //
}
