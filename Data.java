import Classes.*;
import java.util.HashMap;
import java.util.Collection;

public class Data {
    // ---------------------------------------------------------------------------------------------------- //
    private static Data INSTANCE;
    private Data() {
        Users = new HashMap<String, User>();
        Techies = new HashMap<String, Technician>();
    }

    public static synchronized Data Get() {
        if (INSTANCE == null) { INSTANCE = new Data(); }
        return INSTANCE;
    }

    private User ActiveUser;
    public void SetUser(User u) { ActiveUser = u; }
    public User GetUser(User u) { return ActiveUser; }
    // ---------------------------------------------------------------------------------------------------- //
    private static HashMap<String, User> Users;
    public void AddUser(User u) { Users.put(u.GetEmail(), u); }
    public void RemoveUser(String email) { Users.remove(email); }  

    public User GetUser(String email) { return Users.get(email); }
    public Collection<User> GetAllUsers() { return Users.values(); }
    // ---------------------------------------------------------------------------------------------------- //
    private static HashMap<String, Technician> Techies;
    public void AddTechy(Technician t) { Techies.put(t.GetEmail(), t); }
    public void RemoveTechy(String email) { Techies.remove(email); }

    public Technician GetTechy(String email) { return Techies.get(email); }
    public Collection<Technician> GetAllTechies() { return Techies.values(); }
    // ---------------------------------------------------------------------------------------------------- //
}
