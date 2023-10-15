package CommonClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDAO implements DAO<Seller> {
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
            int UserID = rs.getInt("UserID");
            int ItemId = rs.getInt("ItemID");
            seller = new Seller(sellerID, UserID,ItemId);
        }
        return seller;
    }

    @Override
    public List<Seller> getAll() throws SQLException {
       List<Seller> sellers = new ArrayList<Seller>();
        Connection con = Database.getConnection();
        String sql = "select * from Sellers";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Seller seller = null;
            int sellerID = rs.getInt("SellerID");
            int UserID = rs.getInt("UserID");
            int ItemId = rs.getInt("ItemID");
            seller = new Seller(sellerID, UserID,ItemId);
            sellers.add(seller);

        }
        return sellers;
    }
//==========================================not required==========================================
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
