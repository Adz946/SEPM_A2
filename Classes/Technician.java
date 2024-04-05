package Classes;
import java.util.HashMap;
import java.util.Collection;
// ---------------------------------------------------------------------------------------------------- //
public class Technician extends Staff {
    private final int level;
    private HashMap<String, Ticket> Tickets;
    // ---------------------------------------------------------------------------------------------------- //
    public Technician(String name, String email, String mobile, String password, int level) {
        super(name, email, mobile, password);
        this.level = level;
        Tickets = new HashMap<String, Ticket>();
    }

    public int TechLevel() { return this.level; }
    // ---------------------------------------------------------------------------------------------------- //   
    public void AddTicket(Ticket t) { Tickets.put(t.GetID(), t); }
    public void RemoveTicket(String ID) { Tickets.remove(ID); }

    public Ticket GetTicket(String ID) { return Tickets.get(ID); }
    public Collection<Ticket> GetOpenTickets() { return Tickets.values(); }
    // ---------------------------------------------------------------------------------------------------- //
    public void ModifyTicket(String ID, String severity) {
        Ticket ticket = GetTicket(ID);
        if (ticket != null) { ticket.SetSeverity(severity); }
    }

    public void CloseTicket(String ID) {
        Ticket ticket = GetTicket(ID);

        if (ticket != null) { 
            ticket.SetStatus("RESOLVED");
            RemoveTicket(ID);
        }
    }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public String toString() {
        return this.GetName() + "," + this.GetEmail() + "," + this.GetMobile() + "," + this.GetPassword() + "," + this.TechLevel();
    }
    // ---------------------------------------------------------------------------------------------------- //
}
