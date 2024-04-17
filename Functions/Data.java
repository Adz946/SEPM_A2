package Functions;
import Classes.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

public class Data {
    // ---------------------------------------------------------------------------------------------------- //
    private static Data INSTANCE;
    private static int ticketID;

    private Data() {
        ticketID = 0;
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
    public Ticket GetTicket(String ID) { return Tickets.get(ID); }
    public Collection<Ticket> GetAllTickets() { return Tickets.values(); }

    public int GetNewTicketID() { 
        ticketID += 5;
        return ticketID;
    }

    public void AddTicket(Ticket t) { 
        Tickets.put(t.GetID(), t); 

        int id = Integer.parseInt(t.GetID().split("-")[1]);
        if (id > ticketID) { ticketID = id; }
    }

    public ArrayList<Ticket> GetStaffTickets() {
        ArrayList<Ticket> returnTickets = new ArrayList<>();

        for (Ticket ticket : Tickets.values()) {
            if (ActiveStaff instanceof Technician) {
                if (ticket.GetStatus().equals("OPEN") && ticket.GetTechy().equals(ActiveStaff.GetEmail())) { returnTickets.add(ticket); }
                else if (!ticket.GetStatus().equals("OPEN")) { returnTickets.add(ticket); }
            }
            else if (ticket.GetStaff().equals(ActiveStaff.GetEmail()) && ticket.GetStatus().equals("OPEN")) { returnTickets.add(ticket); }
        }

        returnTickets.sort((t1, t2) -> t2.GetOpenedDT().compareTo(t1.GetOpenedDT()));
        return returnTickets;
    }

    public HashMap<String, Integer> GetTechyOpenTickets(int level) {
        HashMap<String, Integer> ticketsByTechy = new HashMap<>();
        for (Technician techy : GetAllTechies()) { if (techy.TechLevel() == level) { ticketsByTechy.put(techy.GetEmail(), 0); } }

        for (Ticket ticket : Tickets.values()) {
            String techy = ticket.GetTechy();
            if (ticket.GetStatus().equals("OPEN") && ticketsByTechy.containsKey(techy)) {
                ticketsByTechy.put(techy, ticketsByTechy.get(techy) + 1);
            }
        }

        return ticketsByTechy;
    }
    // ---------------------------------------------------------------------------------------------------- //
}
