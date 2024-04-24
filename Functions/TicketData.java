package Functions;
import Classes.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.time.LocalDateTime;

public class TicketData {
    private static TicketData INSTANCE;
    private static int ticketID;
    private static HashMap<String, Ticket> Tickets;

    private TicketData() {
        ticketID = 0;
        Tickets = new HashMap<>();
    }

    public static synchronized TicketData Get() {
        if (INSTANCE == null) { INSTANCE = new TicketData(); }
        return INSTANCE;
    }

    public int GetNewTicketID() { 
        ticketID += 5;
        return ticketID;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public Ticket GetTicket(String ID) { return Tickets.get(ID); }
    public Collection<Ticket> GetAllTickets() { return Tickets.values(); }

    public void AddTicket(Ticket t) { 
        Tickets.put(t.GetID(), t); 

        int id = Integer.parseInt(t.GetID().split("-")[1]);
        if (id > ticketID) { ticketID = id; }
    }
    // ---------------------------------------------------------------------------------------------------- //
    public ArrayList<Ticket> GetStaffTickets() {
        ArrayList<Ticket> returnTickets = new ArrayList<>();
        Staff ActiveStaff = StaffData.Get().GetActiveStaff();

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
    // ---------------------------------------------------------------------------------------------------- //
    public HashMap<String, Integer> GetTechyOpenTickets(int level) {
        HashMap<String, Integer> ticketsByTechy = new HashMap<>();
        for (Technician techy : StaffData.Get().GetAllTechies()) { 
            if (techy.TechLevel() == level) { ticketsByTechy.put(techy.GetEmail(), 0); } 
        }

        for (Ticket ticket : Tickets.values()) {
            String techy = ticket.GetTechy();
            if (ticket.GetStatus().equals("OPEN") && ticketsByTechy.containsKey(techy)) {
                ticketsByTechy.put(techy, ticketsByTechy.get(techy) + 1);
            }
        }

        return ticketsByTechy;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public ArrayList<Ticket> GetReportTickets(LocalDateTime start, LocalDateTime end) {
        ArrayList<Ticket> returnTickets = new ArrayList<>();

        for (Ticket ticket : Tickets.values()) {
            if (ticket.GetDT_Opened().isAfter(start) && ticket.GetDT_Opened().isBefore(end)) { returnTickets.add(ticket); }
        }

        returnTickets.sort((t1, t2) -> t1.GetOpenedDT().compareTo(t2.GetOpenedDT()));
        return returnTickets;
    }
    // ---------------------------------------------------------------------------------------------------- //
}
