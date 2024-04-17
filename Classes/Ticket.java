package Classes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
// ---------------------------------------------------------------------------------------------------- //
public class Ticket {
    enum Severity { LOW, MEDIUM, HIGH }
    enum Status { OPEN, CANCELLED, RESOLVED, UNRESOLVED, ARCHIVED }
    // ---------------------------------------------------------------------------------------------------- //
    private final String ID;
    private final String sMail;
    private final String description;

    private String tMail;
    private Status status;    
    private Severity severity;     
    // sMail == Staff Email | tMail == Technician Email

    private final LocalDateTime openedDT;
    private LocalDateTime archivedDT;
    // ---------------------------------------------------------------------------------------------------- //
    public Ticket(String ID, String sMail, String tMail, String description, String sev) {
        this.ID = ID;
        this.sMail = sMail;
        this.tMail = tMail;
        this.description = description;
        
        SetSeverity(sev);  
        this.status = Status.OPEN;

        this.archivedDT = null;
        this.openedDT = LocalDateTime.now();
    }

    public Ticket(String ID, String sMail, String tMail,String description, String sev, String stat, String openedDT, String archivedDT) {
        this.ID = ID;
        this.sMail = sMail;
        this.tMail = tMail;
        this.description = description;

        SetStatus(stat);
        SetSeverity(sev);  

        this.openedDT = LocalDateTime.parse(openedDT, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a", Locale.US));
        if (archivedDT != null) this.archivedDT = LocalDateTime.parse(archivedDT, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a", Locale.US));
        else this.archivedDT = null;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public String GetID() { return this.ID; }
    public String GetStaff() { return this.sMail; }
    public String GetTechy() { return this.tMail; }
    public String GetDesc() { return this.description; }

    public String GetSeverity() {
        switch (this.severity) {
            case LOW: return "LOW";
            case HIGH: return "HIGH";
            case MEDIUM: return "MEDIUM";
            default: return null;
        }
    }
    
    public String GetStatus() {
        switch (this.status) {
            case OPEN: return "OPEN";
            case CANCELLED: return "CANCELLED";
            case ARCHIVED: return "ARCHIVED";
            case RESOLVED: return "RESOLVED";
            case UNRESOLVED: return "UNRESOLVED";
            default: return null;
        }
    }

    public String GetOpenedDT() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a", Locale.US);
        return formatter.format(this.openedDT);
    }

    public String GetArchivedDT() {
        if (this.archivedDT == null) return "-";
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a", Locale.US);
            return formatter.format(this.archivedDT);
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    public void SetTechy(String tMail) { this.tMail = tMail; }
    public void ArchiveTicket() { this.archivedDT = LocalDateTime.now(); }

    public void SetSeverity(String sev) {
        switch (sev) {
            case "LOW": 
                this.severity = Severity.LOW; 
                break;
            case "HIGH": 
                this.severity = Severity.HIGH; 
                break;
            case "MEDIUM": 
                this.severity = Severity.MEDIUM; 
                break;
        }
    }

    public void SetStatus(String stat) {
        switch (stat) {
            case "OPEN": 
                this.status = Status.OPEN; 
                break;
            case "CANCELLED" : 
                this.status = Status.CANCELLED; 
                break;
            case "ARCHIVED": 
                this.status = Status.ARCHIVED; 
                break;
            case "RESOLVED": 
                this.status = Status.RESOLVED; 
                break;
            case "UNRESOLVED": 
                this.status = Status.UNRESOLVED; 
                break;
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    public void View() {
        System.out.printf("| %-5s | %-45s | %-45s | %-10s | %-10s | %-20s | %-20s | %n", this.GetID(), this.GetStaff(), this.GetTechy(), this.GetSeverity(), this.GetStatus(), this.GetOpenedDT(), this.GetArchivedDT());
        System.out.printf("| %-173s | %n", this.GetDesc());
    }

    @Override
    public String toString() {
        String ticket = this.GetID() + "," + this.GetStaff() + "," + this.GetTechy() + "," + this.GetDesc() + "," + this.GetSeverity() + "," + this.GetStatus() + "," + this.GetOpenedDT();
        if (this.archivedDT != null) ticket += "," + this.GetArchivedDT();
        return ticket;
    }
    // ---------------------------------------------------------------------------------------------------- //
}
