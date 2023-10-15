import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BidDAO implements DAO<Bid>{

    @Override
    public Bid get(int ID) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<Bid> getAll() throws SQLException {
          List<Bid> bids = new ArrayList<Bid>();
        Connection con = Database.getConnection();
        String sql = "select * from Bid";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Bid bid = null;
            int BidID = rs.getInt("BidID");
            int UserID = rs.getInt("UserID");
            int ItemID = rs.getInt("ItemID");
            int BidAmount = rs.getInt("BidAmount");
            int MinIncrement = rs.getInt("MinIncrement");
            String BidTime = rs.getString("BidTime");
            bid = new Bid(BidID, UserID, ItemID, BidAmount, MinIncrement, BidTime);
            bids.add(bid);

        }
        return bids;
    }

    @Override
    public int insert(Bid t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(Bid t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(Bid t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
