package Commonclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BidDAO implements DAO<Bid> {

    @Override
    public Bid get(int ID) throws SQLException {
        Connection con = Database.getConnection();
        Bid bid = null;
        String sql = "select * from Bid where BidId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int BidID = rs.getInt("BidID");
            int UserID = rs.getInt("UserID");
            int itemID = rs.getInt("ItemID");
            int bidAmount = rs.getInt("BidAmount");
            int minIncrement = rs.getInt("MinIncrement");
            String bidTime = rs.getString("BidTime");
            bid = new Bid(BidID, UserID, itemID, bidAmount, minIncrement, bidTime);
        }
        return bid;
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
        Connection con = Database.getConnection();
        String sql = "insert into Bid(UserID,ItemID,BidAmount,MinIncrement,BidTime) values (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, t.getUserID());
        ps.setInt(2, t.getItemID());
        ps.setInt(3, t.getBidAmount());
        ps.setInt(4, t.getMinIncrement());
        ps.setString(5, t.getBidTime());
        int result = ps.executeUpdate();

        ResultSet generatedKeys = ps.getGeneratedKeys();
        int itemID = -1;
        if (generatedKeys.next()) {
            itemID = generatedKeys.getInt(2);
        }

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        if (itemID != -1) {
            callAddItemToBuyer(itemID, t.getUserID());
        }
        return result;
    }

    private void callAddItemToBuyer(int itemID, int userID) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "CALL AddItemToBuyer(?, ?)";
        java.sql.CallableStatement statement = con.prepareCall(sql);
        statement.setInt(1, itemID);
        statement.setInt(2, userID);
        statement.execute();
        Database.closePreparedStatement(statement);
        Database.closeConnection(con);
    }

    public Bid getBidderforHighestbid(int ItemID) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "select BidID from Bid where BidAmount = (select MAX(BidAmount) from Bid where ItemID = ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ItemID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int BidID = rs.getInt("BidID");
        return get(BidID);
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