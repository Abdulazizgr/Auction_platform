package CommonClasses;

import java.sql.Timestamp;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import com.mysql.cj.jdbc.CallableStatement;

public class ItemDAO implements DAO<Item> {

    @Override
    public Item get(int ID) throws SQLException {
        Connection con = Database.getConnection();
        Item item = null;
        String sql = "select ItemID ,Title ,Description ,ImagePath ,ItemState, Category ,StartPrice ,CurrentBid ,AuctionStatus ,StartDate ,EndDate,UserID from Item where ItemId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int ItemID = rs.getInt("ItemID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String imagePath = rs.getString("ImagePath");
            String ItemState = rs.getString("ItemState");
            String Category = rs.getString("Category");
            double Startprice = rs.getInt("Startprice");
            double CurrentBid = rs.getInt("CurrentBid");
            String AuctionStatus = rs.getString("AuctionStatus");
            Timestamp StartDate = rs.getTimestamp("StartDate");
            Timestamp EndDate = rs.getTimestamp("EndDate");
            int UserID = rs.getInt("ItemID");
            item = new Item(ItemID, Title, Description, imagePath, Category, Startprice, CurrentBid,
                    AuctionStatus, StartDate, EndDate, UserID, ItemState);
        }

        return item;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        List<Item> items = new ArrayList<Item>();
        Connection con = Database.getConnection();
        String sql = "select * from Item";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Item item = null;
            int ItemID = rs.getInt("ItemID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String imagePath = rs.getString("ImagePath");
            String ItemState = rs.getString("ItemState");
            String Category = rs.getString("Category");
            double Startprice = rs.getInt("Startprice");
            double CurrentBid = rs.getInt("CurrentBid");
            String AuctionStatus = rs.getString("AuctionStatus");
            Timestamp StartDate = rs.getTimestamp("StartDate");
            Timestamp EndDate = rs.getTimestamp("EndDate");
            int UserID = rs.getInt("ItemID");
            item = new Item(ItemID, Title, Description, imagePath, Category, Startprice, CurrentBid,
                    AuctionStatus, StartDate, EndDate, UserID, ItemState);
            items.add(item);
        }
        return items;
    }

    @Override
    public int insert(Item t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "insert into Item(Title, Description, ImagePath, ItemState, Category, Startpice, CUrrentBid, AuctionStatus, StartDate, EndDate,UserID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, t.getTitle());
        ps.setString(2, t.getDescription());
        ps.setString(3, t.getImagePath());
        ps.setString(4, t.getItemState());
        ps.setString(5, t.getCategory());
        ps.setDouble(6, t.getStartPrice());
        ps.setDouble(7, t.getCurrentBid());
        ps.setString(8, t.getAuctionStatus());
        ps.setTimestamp(9, t.getStartDate());
        ps.setTimestamp(10, t.getEndDate());
        ps.setInt(11, t.getUserID());
        int result = ps.executeUpdate();

        ResultSet generatedKeys = ps.getGeneratedKeys();
        int itemID = -1;
        if (generatedKeys.next()) {
            itemID = generatedKeys.getInt(1);
        }

        // Database.closeResultSet(generatedKeys);
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        if (itemID != -1) {
            callAddItemToSeller(itemID, t.getUserID());
        }
        return result;
    }

    private void callAddItemToSeller(int itemID, int userID) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "CALL AddItemToSeller(?, ?)";
        java.sql.CallableStatement statement = con.prepareCall(sql);
        statement.setInt(1, itemID);
        statement.setInt(2, userID);
        statement.execute();
        Database.closePreparedStatement(statement);
        Database.closeConnection(con);
    }

    @Override
    public int update(Item t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "UPDATE Item set ItemID = ? Title = ?, Description = ?, ImagePath = ?,ItemState = ?, Category = ?, Startpice, = ? CUrrectBid = ?, AuctionStatus, StartDate = ?, EndDate = ?, UserID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, t.getItemID());
        ps.setString(2, t.getTitle());
        ps.setString(3, t.getDescription());
        ps.setString(4, t.getImagePath());
        ps.setString(5, t.getItemState());
        ps.setString(6, t.getCategory());
        ps.setDouble(7, t.getStartPrice());
        ps.setDouble(8, t.getCurrentBid());
        ps.setString(9, t.getAuctionStatus());
        ps.setTimestamp(10, t.getStartDate());
        ps.setTimestamp(11, t.getEndDate());
        ps.setInt(12, t.getUserID());
        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return result;
    }

    @Override
    public int delete(Item t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
