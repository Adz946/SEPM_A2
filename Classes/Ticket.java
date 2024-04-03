package Classes;
// ---------------------------------------------------------------------------------------------------- //
public class Ticket {
    enum Severity { LOW, MEDIUM, HIGH }
    enum Status { OPEN, RESOLVED, UNRESOLVED, ARCHIVED }
    // ---------------------------------------------------------------------------------------------------- //
    private final String ID;
    private final String sMail;

    private String tMail;
    private Status status;    
    private Severity severity;     
    // sMail == Staff Email | tMail == Technician Email
    // ---------------------------------------------------------------------------------------------------- //
    public Ticket(int ID, String sMail, String tMail, String severity) {
        this.ID = "T-" + ID;
        this.sMail = sMail;
        this.tMail = tMail;
        this.status = Status.OPEN;

        if (severity.equals("low")) { LowSeverity(); }
        else if (severity.equals("high")) { HighSeverity(); }
        else if (severity.equals("medium")) { MediumSeverity(); }    
    }
    // ---------------------------------------------------------------------------------------------------- //
    public String GetID() { return this.ID; }
    public String GetStaff() { return this.sMail; }
    public String GetTechy() { return this.tMail; }

    public String GetSeverity() {
        switch (this.severity) {
            case LOW: return "LOW";
            case HIGH: return "HIGH";
            case MEDIUM: return "MEDIUM";
        }
        return null;
    }
    
    public String GetStatus() {
        switch (this.status) {
            case OPEN: return "OPEN";
            case ARCHIVED: return "ARCHIVED";
            case RESOLVED: return "RESOLVED";
            case UNRESOLVED: return "UNRESOLVED";
        }
        return null;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public void SetTechy(String tMail) { this.tMail = tMail; }

    public void LowSeverity() { this.severity = Severity.LOW; }
    public void HighSeverity() { this.severity = Severity.HIGH; }
    public void MediumSeverity() { this.severity = Severity.MEDIUM; }

    public void ReopenTicket() { this.status = Status.OPEN; }
    public void ArchiveTicket() { this.status = Status.ARCHIVED; }
    public void TicketResolved() { this.status = Status.RESOLVED; }
    public void TicketUnresolved() { this.status = Status.UNRESOLVED; }
    // ---------------------------------------------------------------------------------------------------- //
}
