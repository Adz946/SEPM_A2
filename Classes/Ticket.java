package Classes;
// ---------------------------------------------------------------------------------------------------- //
public class Ticket {
    enum Status { OPEN, CLOSED }
    enum Severity { LOW, MEDIUM, HIGH }
    // ---------------------------------------------------------------------------------------------------- //
    private final int ID;
    private Status status;
    private Severity severity;
    // ---------------------------------------------------------------------------------------------------- //
    public Ticket(int ID, String severity) {
        this.ID = ID;
        this.status = Status.OPEN;

        if (severity.equals("low")) { this.severity = Severity.LOW; }
        else if (severity.equals("medium")) { this.severity = Severity.MEDIUM; }
        else if (severity.equals("high")) { this.severity = Severity.HIGH; }
    }
    // ---------------------------------------------------------------------------------------------------- //
    public int GetID() { return this.ID; }
    public void CloseTicket() { this.status = Status.CLOSED; }

    public void SetSeverity(String severity) {
        if (severity.equals("low")) { this.severity = Severity.LOW; }
        else if (severity.equals("medium")) { this.severity = Severity.MEDIUM; }
        else if (severity.equals("high")) { this.severity = Severity.HIGH; }
    }
    // ---------------------------------------------------------------------------------------------------- //
}
