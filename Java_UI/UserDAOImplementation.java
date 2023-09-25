import java.sql.*;
import java.util.List;

// this class extends UserDAO and implements different CRUD operations
// this one is only for the User table, 
// that means any CRUD operation u want to run on any user will be covered by this 

public class UserDAOImplementation implements UserDAO {

    @Override
    public int delete(User t) throws SQLException{
      Connection con = Database.getConnection();
      String sql = "delete from User where UserID = ?";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setInt(1, t.getUserID());
      int result = ps.executeUpdate();
      Database.closePreparedStatement(ps);
      Database.closeConnection(con);
      return result;
    }

    // CRUD RETRIEVAL
    @Override
    public User get(int ID) throws SQLException {
        Connection con = Database.getConnection();
        User user = null;
        String sql = "select UserID ,FirstName ,LastName ,Email ,Password ,RegistrationDate ,BankName, AccountHolderName, AccountNumber from User where UserID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int UserID = rs.getInt("UserID");
            String FirstName = rs.getString("FirstName");
            String LastName = rs.getString("LastName");
            String Email = rs.getString("Email");
            String Password = rs.getString("Password");
            String RegistrationDate = rs.getString("RegistrationDate");
            String BankName = rs.getString("BankName");
            String AccountHolderName  = rs.getString("AccountHolderName");
            int AccountNumber = rs.getInt("AccountNumber");
            user = new User(UserID, FirstName, LastName, Email, Password, RegistrationDate, BankName,AccountHolderName, AccountNumber);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insert(User t) throws SQLException {
         Connection con = Database.getConnection();
        String sql = "insert into User(FirstName ,LastName ,Email ,Password ,RegistrationDate ,BankName, AccountHolderName, AccountNumber ) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, t.getFirstName());
        ps.setString(2, t.getLastName());
        ps.setString(3, t.getEmail());
        ps.setString(4, t.getPassword());
        ps.setString(5, t.getRegistrationDate());
        ps.setString(6, t.getBankName());
        ps.setString(7, t.getAccountHolderName());
        ps.setInt(8, t.getAccountNumber());
        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return result;
    }

    @Override
    public int save(User t) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(User t) throws SQLException {
         Connection con = Database.getConnection();
         String sql = "UPDATE User  set FirstName = ? ,LastName = ? ,Email = ? ,Password = ? ,RegistrationDate = ? ,BankName = ?, AccountHolderName = ?, AccountNumber = ? where UserID = ?";
         PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, t.getFirstName());
        ps.setString(2, t.getLastName());
        ps.setString(3, t.getEmail());
        ps.setString(4, t.getPassword());
        ps.setString(5, t.getRegistrationDate());
        ps.setString(6, t.getBankName());
        ps.setString(7, t.getAccountHolderName());
        ps.setInt(8, t.getAccountNumber());
        ps.setInt(9, t.getUserID());
        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return result;
    }

}
