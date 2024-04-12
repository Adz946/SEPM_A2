package Menus;
import Classes.Ticket;
import Classes.Technician;
import Functions.Data;
import Functions.InputReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class TicketCreate {
    private static String description, severity;
    public TicketCreate() { Reset(); }

    private void Reset() {
        description = "";
        severity = "";
    }
    // ---------------------------------------------------------------------------------------------------- //
    public int Menu() {
        System.out.printf("---------- Create Ticket ---------- %n" +
        "[1] Severity : %s %n" +
        "[2] Description : %s %n" +
        "[3] Create %n" +
        "[4] Go Back %n", severity, description);
        System.out.print(" >> ");
        String input = InputReader.Get().nextLine();

        if (input.equals("1")) { severity = SeveritySelect(); }
        else if (input.equals("2")) { description = DescriptionInput(); }
        else if (input.equals("3")) { CreateTicket(); return 3; }
        else if (input.equals("4")) { Reset(); return 3; }
        else { App.WriteError("Only Select Between the Available Options"); }

        return 5;
    }
    // ---------------------------------------------------------------------------------------------------- //
    private void CreateTicket() {
        if (description != "" && severity != "") {
            String id = "T-" + Data.Get().GetNewTicketID();
            String sMail = Data.Get().GetActiveStaff().GetEmail();
            
            String tMail = assignTicketToTechnician(severity);

            Data.Get().AddTicket(new Ticket(id, sMail, tMail, description, severity));
            System.out.println("Ticket Added Successfully!");
            System.out.println("Ticket ID: " + id + " - Assigned Technician: " + tMail );

            Reset();
        }
        else App.WriteError("Fill ALL Fields");
    }

    private String SeveritySelect() {
        while (true) {
            System.out.println("[1] LOW \n[2] MEDIUM \n[3] HIGH");
            System.out.print(" >> ");
            String input = InputReader.Get().nextLine();

            if (input.equals("1")) { return "LOW"; }
            else if (input.equals("2")) { return "MEDIUM"; }
            else if (input.equals("3")) { return "HIGH"; }
            else { App.WriteError("Only Select Between the Available Options"); }
        }
    }

    private String DescriptionInput() {
        while (true) {
            System.out.print("Ticket Description: ");
            String input = InputReader.Get().nextLine();

            if (input.length() > 145) { App.WriteError("Description is too Long"); }
            else return "\"" + input + "\"";
        }
    }
    
    private String assignTicketToTechnician(String severity) {
        int severityLevel = severity.equals("HIGH") ? 2 : 1;

        List<Technician> filteredTechies = Data.Get().GetAllTechies().stream()
            .filter(t -> t.TechLevel() == severityLevel)
            .collect(Collectors.toList());

        if (filteredTechies.isEmpty()) {
            return "z.malik@company.com"; // Default technician if none returned. 
        }

        Map<Technician, Integer> techiesWithTicketCounts = new HashMap<>();

        for (Technician tech : filteredTechies) {
            techiesWithTicketCounts.put(tech, Data.Get().GetTechOpenTickets(tech.GetEmail()).size());
        }

       int minTickets = Collections.min(techiesWithTicketCounts.values());

       List<Technician> leastLoadedTechies = techiesWithTicketCounts.entrySet().stream()
           .filter(entry -> entry.getValue() == minTickets)
           .map(Map.Entry::getKey)
           .collect(Collectors.toList());

       Random rand = new Random();
       Technician assignedTech = leastLoadedTechies.get(rand.nextInt(leastLoadedTechies.size()));

       return assignedTech.GetEmail();
   }
    // ---------------------------------------------------------------------------------------------------- //
    
}
