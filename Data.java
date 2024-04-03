import Classes.*;
import java.util.HashMap;
import java.util.Collection;

public class Data {
    // ---------------------------------------------------------------------------------------------------- //
    private static Data INSTANCE;
    private Data() {
        Staff = new HashMap<String, Staff>();
        Techies = new HashMap<String, Technician>();
    }

    public static synchronized Data Get() {
        if (INSTANCE == null) { INSTANCE = new Data(); }
        return INSTANCE;
    }

    private Staff ActiveStaff;
    public void SetStaff(Staff u) { ActiveStaff = u; }
    public Staff GetStaff(Staff u) { return ActiveStaff; }
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
