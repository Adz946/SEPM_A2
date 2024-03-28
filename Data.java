import Classes.*;
import java.util.ArrayList;

public class Data {
    // ---------------------------------------------------------------------------------------------------- //
    private static Data INSTANCE;
    private Data() {
        Users = new ArrayList<>();
        Techies = new ArrayList<>();
    }

    public static synchronized Data Get() {
        if (INSTANCE == null) { INSTANCE = new Data(); }
        return INSTANCE;
    }

    private User ActiveUser;
    public void SetUser(User u) { ActiveUser = u; }
    public User GetUser(User u) { return ActiveUser; }
    // ---------------------------------------------------------------------------------------------------- //
    private static ArrayList<User> Users;
    public void AddUser(User u) { Users.add(u); }
    public void RemoveUser(User u) { Users.remove(u); }
    public ArrayList<User> GetUsers() { return Users; }

    public User GetUser(String email) {
        for (User user : GetUsers()) { if (user.GetEmail().equals(email)) { return user; } }
        return null;
    }
    // ---------------------------------------------------------------------------------------------------- //
    private static ArrayList<Technician> Techies;
    public void AddTechy(Technician t) { Techies.add(t); }
    public void RemoveTechy(Technician t) { Techies.remove(t); }
    public ArrayList<Technician> GetTechies() { return Techies; }

    public Technician GetTechy(String email) {
        for (Technician techy : GetTechies()) { if (techy.GetEmail().equals(email)) { return techy; } }
        return null;
    }
    // ---------------------------------------------------------------------------------------------------- //
}
