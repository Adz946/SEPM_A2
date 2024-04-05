package Classes;
// ---------------------------------------------------------------------------------------------------- //
public class Staff {
    private String name;
    private String email;
    private String mobile;
    private String password;
    // ---------------------------------------------------------------------------------------------------- //
    public Staff(String name, String email, String mobile, String password) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public void SetName(String name) { this.name = name; }
    public void SetEmail(String email) { this.email = email; }
    public void SetMobile(String mobile) { this.mobile = mobile; }
    public void SetPassword(String password) { this.password = password; }
    // ---------------------------------------------------------------------------------------------------- //
    public String GetName() { return this.name; }
    public String GetEmail() { return this.email; }
    public String GetMobile() { return this.mobile; }
    public String GetPassword() { return this.password; }
    // ---------------------------------------------------------------------------------------------------- //
    @Override
    public String toString() {
        return this.GetName() + "," + this.GetEmail() + "," + this.GetMobile() + "," + this.GetPassword();
    }
    // ---------------------------------------------------------------------------------------------------- //
}
