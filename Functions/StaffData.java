package Functions;
import Classes.*;
import java.util.HashMap;
import java.util.Collection;

public class StaffData {
    // ---------------------------------------------------------------------------------------------------- //
    private static StaffData INSTANCE;

    private StaffData() {
        Staff = new HashMap<>();
        Techies = new HashMap<>();
    }

    public static synchronized StaffData Get() {
        if (INSTANCE == null) { INSTANCE = new StaffData(); }
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
}
