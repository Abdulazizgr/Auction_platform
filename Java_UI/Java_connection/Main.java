import java.sql.SQLException;

// test ur results here
public class Main {
    public static void main(String[] args) throws SQLException {
        User user = new User(1, "samson", "bekumsa", "hjg@gm.com", "123445", "2023-4-25");
        UserDAO userDAO = new UserDAOImplementation();
        int result = userDAO.insert(user);
        System.out.println(result);
    }
}
