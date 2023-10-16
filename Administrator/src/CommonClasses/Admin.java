package CommonClasses;

public class Admin {
    private int AdminID;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private String RegistrationDate;
    public Admin(int adminID, String firstName, String lastName, String email, String password,String RegistrationDate) {
        this.AdminID = adminID;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.Password = password;
        this.RegistrationDate = RegistrationDate;
    }

      public Admin(String firstName, String lastName, String email, String password) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.Password = password;
    }
    public int getAdminID() {
        return AdminID;
    }
    public void setAdminID(int adminID) {
        AdminID = adminID;
    }
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public String getRegistrationDate() {
        return RegistrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        RegistrationDate = registrationDate;
    }

}
