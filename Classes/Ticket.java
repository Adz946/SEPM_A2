package Classes;
// ---------------------------------------------------------------------------------------------------- //
public class Ticket {
    enum Severity { LOW, MEDIUM, HIGH }
    enum Status { OPEN, RESOLVED, UNRESOLVED, ARCHIVED }
    // ---------------------------------------------------------------------------------------------------- //
    private final String ID;
    private final String user;

    private String techy;
    private Status status;    
    private Severity severity;     
    // ---------------------------------------------------------------------------------------------------- //
    public Ticket(int ID, String umail, String tmail, String severity) {
        this.ID = "T-" + ID;
        this.user = umail;
        this.techy = tmail;
        this.status = Status.OPEN;

        if (severity.equals("low")) { LowSeverity(); }
        else if (severity.equals("high")) { HighSeverity(); }
        else if (severity.equals("medium")) { MediumSeverity(); }    
    }
    // ---------------------------------------------------------------------------------------------------- //
    public String GetID() { return this.ID; }
    public String Getuser() { return this.user; }
    public String GetTechy() { return this.techy; }

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
    public void SetTechy(String tmail) { this.techy = tmail; }

    public void LowSeverity() { this.severity = Severity.LOW; }
    public void HighSeverity() { this.severity = Severity.HIGH; }
    public void MediumSeverity() { this.severity = Severity.MEDIUM; }

    public void ReopenTicket() { this.status = Status.OPEN; }
    public void ArchiveTicket() { this.status = Status.ARCHIVED; }
    public void TicketResolved() { this.status = Status.RESOLVED; }
    public void TicketUnresolved() { this.status = Status.UNRESOLVED; }
    // ---------------------------------------------------------------------------------------------------- //
}
