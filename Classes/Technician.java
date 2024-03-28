package Classes;
import java.util.ArrayList;
// ---------------------------------------------------------------------------------------------------- //
public class Technician extends User {
    private final int level;
    private ArrayList<Ticket> assigned;
    // ---------------------------------------------------------------------------------------------------- //
    public Technician(String name, String email, String mobile, String password, int level) {
        super(name, email, mobile, password);
        this.level = level;
        assigned = new ArrayList<Ticket>();
    }
    // ---------------------------------------------------------------------------------------------------- //
    public int TechLevel() { return this.level; }
    public ArrayList<Ticket> AssignedTickets() { return this.assigned; }

    public void AddTicket(Ticket ticket) { this.assigned.add(ticket); }
    public void RemoveTicket(Ticket ticket) { this.assigned.remove(ticket); }
    // ---------------------------------------------------------------------------------------------------- //
    public Ticket FindTicket(int ID) {
        for (Ticket ticket : this.assigned) { if (ticket.GetID() == ID) { return ticket; } }
        return null;
    }
    
    public void ModifyTicket(int ID, String severity) {
        Ticket ticket = FindTicket(ID);
        if (ticket != null) { ticket.SetSeverity(severity); }
    }

    public void CloseTicket(int ID) {
        Ticket ticket = FindTicket(ID);
        if (ticket != null) { 
            ticket.CloseTicket(); 
            RemoveTicket(ticket);
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
}
