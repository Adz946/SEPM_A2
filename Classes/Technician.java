package Classes;
// ---------------------------------------------------------------------------------------------------- //
public class Technician extends Staff {
    private final int level;
    // ---------------------------------------------------------------------------------------------------- //
    public Technician(String name, String email, String mobile, String password, int level) {
        super(name, email, mobile, password);
        this.level = level;
    }

    public int TechLevel() { return this.level; }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public String toString() {
        return this.GetName() + "," + this.GetEmail() + "," + this.GetMobile() + "," + this.GetPassword() + "," + this.TechLevel();
    }
    // ---------------------------------------------------------------------------------------------------- //
}
