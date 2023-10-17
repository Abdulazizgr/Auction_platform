package Commonclasses;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

// this class extends UserDAO and implements different CRUD operations
// this one is only for the User table, 
// that means any CRUD operation u want to run on any user will be covered by this 

public class UserDAO implements DAO<User> {
    @Override

    public int delete(User t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "delete from Users where UserID = ?";
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
        String sql = "select UserID ,FirstName ,LastName ,Email ,Password ,RegistrationDate from Users where UserID = ?";
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
            user = new User(UserID, FirstName, LastName, Email, Password, RegistrationDate);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<User>();
        Connection con = Database.getConnection();
        String sql = "select * from Users";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User user = null;
            int UserID = rs.getInt("UserID");
            String FirstName = rs.getString("FirstName");
            String LastName = rs.getString("LastName");
            String Email = rs.getString("Email");
            String Password = rs.getString("Password");
            String RegistrationDate = rs.getString("RegistrationDate");
            user = new User(UserID, FirstName, LastName, Email, Password, RegistrationDate);
            users.add(user);
        }
        return users;
    }

    public User get(String name) throws SQLException {
        UserDAO entire = new UserDAO();
        List<User> store = entire.getAll();
        for (User i : store) {
            if (i.getFirstName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public int insert(User t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "insert into Users (FirstName ,LastName ,Email ,Password) values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, t.getFirstName());
        ps.setString(2, t.getLastName());
        ps.setString(3, t.getEmail());
        ps.setString(4, t.getPassword());
        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return result;
    }

    @Override
    public int update(User t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "UPDATE Users  set FirstName = ? ,LastName = ? ,Email = ? ,Password = ? ,RegistrationDate = ? where UserID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, t.getFirstName());
        ps.setString(2, t.getLastName());
        ps.setString(3, t.getEmail());
        ps.setString(4, t.getPassword());
        ps.setString(5, t.getRegistrationDate());
        ps.setInt(9, t.getUserID());
        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return result;
    }

}