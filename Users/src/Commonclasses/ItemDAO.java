package Commonclasses;

import java.sql.Timestamp;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements DAO<Item> {

    @Override
    public Item get(int ID) throws SQLException {
        Connection con = Database.getConnection();
        Item item = null;
        String sql = "select ItemID ,Title ,Description ,ImagePath ,ItemState, Category ,StartPrice,AuctionStatus ,StartDate ,soldDate,UserID from Item where ItemId = ?";
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
            String AuctionStatus = rs.getString("AuctionStatus");
            Timestamp StartDate = rs.getTimestamp("StartDate");
            Timestamp soldDate = rs.getTimestamp("soldDate");
            int UserID = rs.getInt("ItemID");
            item = new Item(ItemID, Title, Description, imagePath, Category, Startprice,
                    AuctionStatus, StartDate, soldDate, UserID, ItemState);
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
            String AuctionStatus = rs.getString("AuctionStatus");
            Timestamp StartDate = rs.getTimestamp("StartDate");
            Timestamp soldDate = rs.getTimestamp("soldDate");
            int UserID = rs.getInt("UserID");
            item = new Item(ItemID, Title, Description, imagePath, Category, Startprice,
                    AuctionStatus, StartDate, soldDate, UserID, ItemState);
            items.add(item);
        }
        return items;
    }

    @Override
    public int insert(Item t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "insert into Item(Title, Description, ImagePath, Category, StartPrice, AuctionStatus,UserID) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, t.getTitle());
        ps.setString(2, t.getDescription());
        ps.setString(3, t.getImagePath());
        ps.setString(4, t.getCategory());
        ps.setDouble(5, t.getStartPrice());
        ps.setString(6, t.getAuctionStatus());
        ps.setInt(7, t.getUserID());
        int result = ps.executeUpdate();

        ResultSet generatedKeys = ps.getGeneratedKeys();// Statement.RETURN_GENERATED_KEYS to Statement.executeUpdate()
        int itemID = -1;
        if (generatedKeys.next()) {
            itemID = generatedKeys.getInt(1);
        }

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
        String sql = "UPDATE Item set ItemID = ? Title = ?, Description = ?, ImagePath = ?,ItemState = ?, Category = ?, Startpice, = ?, AuctionStatus, StartDate = ?, soldDate = ?, UserID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, t.getItemID());
        ps.setString(2, t.getTitle());
        ps.setString(3, t.getDescription());
        ps.setString(4, t.getImagePath());
        ps.setString(5, t.getCategory());
        ps.setDouble(6, t.getStartPrice());
        ps.setString(7, t.getAuctionStatus());
        ps.setInt(8, t.getUserID());
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