import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDAOImplementation implements BankAccountDAO {

    @Override
    public BanckAccount get(int ID) throws SQLException {
        Connection con = Database.getConnection();
        BanckAccount banckAccount = null;
        String sql = "select BankAccountId, UserID, BankName, AccountHolderName, AccountNUmber, Balance from BankAccount where BankAccountId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int BankAccountID = rs.getInt("BankAccountID");
            int UserID = rs.getInt("UserID");
            String BankName = rs.getString("BankName");
            String AccountHolderName = rs.getString("AccountHolderName");
            int AccountNumber = rs.getInt("AccountNumber");
            double Balance = rs.getDouble("Balance");
            banckAccount = new BanckAccount(BankAccountID, UserID, BankName, AccountHolderName, AccountNumber, Balance);
        }

        return banckAccount;
    }

    @Override
    public List<BanckAccount> getAll() throws SQLException {
        List<BanckAccount> banckAccounts = new ArrayList<BanckAccount>();
        int ID = 1;
        while (this.get(ID) != null) {
            banckAccounts.add(get(ID));
            ID++;
        }

        return banckAccounts;
    }

    @Override
    public int insert(BanckAccount t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "inset into BankAccount(BankAccountID, UserID, BankName, AccountHolderName, AccountNumber, Balance) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, t.getBankAccountID());
        ps.setInt(2, t.getUserID());
        ps.setString(3, t.getBankName());
        ps.setString(4, t.getAccountHolderName());
        ps.setInt(5, t.getAccountNumber());
        ps.setDouble(6, t.getBalance());
        int result = ps.executeUpdate();
        Database.closeConnection(con);
        Database.closeConnection(con);
        return result;
    }

    @Override
    public int update(BanckAccount t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "UPDATE BanKAccount set BankAccountID = ?, UserID = ?, BankName = ?, AccountHolderName = ?, AccountNumber = ?, Balance = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, t.getBankAccountID());
        ps.setInt(2, t.getUserID());
        ps.setString(3, t.getBankName());
        ps.setString(4, t.getAccountHolderName());
        ps.setInt(5, t.getAccountNumber());
        ps.setDouble(6, t.getBalance());
        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return result;
    }

    @Override
    public int delete(BanckAccount t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
