
public class User {
    private int UserID;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private String RegistrationDate;

    public User(int userID, String firstName, String lastName, String email, String password, String registrationDate) {
        UserID = userID;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Password = password;
        RegistrationDate = registrationDate;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
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

    @Override
    public String toString() {
        return "User [UserID=" + UserID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Email=" + Email
                + ", Password=" + Password + ", RegistrationDate=" + RegistrationDate + "]";
    }

}