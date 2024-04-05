package Functions;
import Classes.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

public class Data {
    // ---------------------------------------------------------------------------------------------------- //
    private static Data INSTANCE;
    private Data() {
        Staff = new HashMap<>();
        Techies = new HashMap<>();
        Tickets = new HashMap<>();
    }

    public static synchronized Data Get() {
        if (INSTANCE == null) { INSTANCE = new Data(); }
        return INSTANCE;
    }

    private Staff ActiveStaff;
    public Staff GetActiveStaff() { return ActiveStaff; }
    public void SetActiveStaff(Staff u) { ActiveStaff = u; }   
    // ---------------------------------------------------------------------------------------------------- //
    private static HashMap<String, Staff> Staff;
    public void AddStaff(Staff u) { Staff.put(u.GetEmail(), u); }
    public void RemoveStaff(String email) { Staff.remove(email); }  

    public Staff GetStaff(String email) { return Staff.get(email); }
    public Collection<Staff> GetAllStaff() { return Staff.values(); }
    // ---------------------------------------------------------------------------------------------------- //
    private static HashMap<String, Technician> Techies;
    public void AddTechy(Technician t) { Techies.put(t.GetEmail(), t); }
    public void RemoveTechy(String email) { Techies.remove(email); }

    public Technician GetTechy(String email) { return Techies.get(email); }
    public Collection<Technician> GetAllTechies() { return Techies.values(); }
    // ---------------------------------------------------------------------------------------------------- //
    private static HashMap<String, Ticket> Tickets;
    public void AddTicket(Ticket t) { Tickets.put(t.GetID(), t); }
    public void RemoveTicket(String ID) { Tickets.remove(ID); }

    public Ticket GetTicket(String ID) { return Tickets.get(ID); }
    public Collection<Ticket> GetAllTickets() { return Tickets.values(); }

    public ArrayList<Ticket> GetStaffTickets() {
        ArrayList<Ticket> ReturnTickets = new ArrayList<>();

        for (Ticket ticket : Tickets.values()) {
            if (ActiveStaff instanceof Technician) {
                if (ticket.GetStatus().equals("OPEN") && ticket.GetTechy().equals(ActiveStaff.GetEmail())) { ReturnTickets.add(ticket); }
                else if (!ticket.GetStatus().equals("OPEN")) { ReturnTickets.add(ticket); }
            }
            else if (ticket.GetStaff().equals(ActiveStaff.GetEmail()) && ticket.GetStatus().equals("OPEN")) { ReturnTickets.add(ticket); }
        }

        return ReturnTickets;
    }
    // ---------------------------------------------------------------------------------------------------- //
}
