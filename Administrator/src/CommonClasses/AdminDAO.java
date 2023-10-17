package CommonClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements DAO<Admin>{

    @Override
    public Admin get(int ID) throws SQLException {
       Connection con = Database.getConnection();
       Admin admin = null;
        String sql = "select AdminID ,FirstName ,LastName ,Email ,Password ,RegistrationDate from Admins where AdminID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int AdminID = rs.getInt("AdminID");
            String FirstName = rs.getString("FirstName");
            String LastName = rs.getString("LastName");
            String Email = rs.getString("Email");
            String Password = rs.getString("Password");
            String RegistrationDate = rs.getString("RegistrationDate");
            admin = new Admin(AdminID, FirstName, LastName, Email, Password,RegistrationDate);
        }
        return admin;
    }

    @Override
    public List<Admin> getAll() throws SQLException {
        List<Admin> admins = new ArrayList<Admin>();
        Connection con = Database.getConnection();
        String sql = "select * from Admins";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Admin admin = null;
            int UserID = rs.getInt("AdminID");
            String FirstName = rs.getString("FirstName");
            String LastName = rs.getString("LastName");
            String Email = rs.getString("Email");
            String Password = rs.getString("Password");
            String RegistrationDate = rs.getString("RegistrationDate");
            admin = new Admin(UserID, FirstName, LastName, Email, Password, RegistrationDate);
           admins.add(admin);
        }
        return admins;
    }

    public Admin get(String name) throws SQLException {
        AdminDAO entire = new AdminDAO();
        List<Admin> store = entire.getAll();
        for (Admin i : store) {
            if (i.getFirstName().equals(name)) {
                return i;
            }
        }
        return null;
    }


    @Override
    public int insert(Admin t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "insert into Admins (FirstName ,LastName ,Email ,Password) values(?,?,?,?)";
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
    public int update(Admin t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(Admin t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "delete from Admins where AdminID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, t.getAdminID());
        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return result;
    }
    
}
