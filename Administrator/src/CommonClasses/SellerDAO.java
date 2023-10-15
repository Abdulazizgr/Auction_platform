import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDAO implements DAO<Seller> {
    private ItemDAO itemDAO;
    private UserDAO userDAO;

    @Override
    public Seller get(int ID) throws SQLException {
      Connection con = Database.getConnection();
        Seller seller = null;
        String sql = "select sellerID ,ItemID,UserID from sellers where sellerID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int sellerID = rs.getInt("sellerID");
            int ItemID = rs.getInt("ItemID");
            int UserID = rs.getInt("UserID");
            seller = new Seller();
            seller.setItem(itemDAO.get(ItemID));
            seller.setSellerID(sellerID);
            seller.setUser(userDAO.get(UserID));
        }
        return seller;
    }

    @Override
    public List<Seller> getAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public int insert(Seller t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(Seller t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(Seller t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
