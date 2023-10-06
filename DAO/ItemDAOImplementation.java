import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImplementation implements ItemDAO {

    @Override
    public Item get(int ID) throws SQLException {
        Connection con = Database.getConnection();
        Item item = null;
        String sql = "select ItemID ,SellerID ,Title ,Description ,Image ,Category ,StartPrice ,CurretBid ,AuctionStatus ,StartDate ,EndDate from Item where ItemId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int ItemID = rs.getInt("ItemID");
            int SellerID = rs.getInt("SellerID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            Blob Image = rs.getBlob("Image");
            byte[] imageBytes = Image.getBytes(1, (int) Image.length());
            String Category = rs.getString("Category");
            double Startprice = rs.getInt("Startprice");
            double CurrentBid = rs.getInt("CurrentBid");
            String AuctionStatus = rs.getString("AuctionStatus");
            Timestamp StartDate = rs.getTimestamp("StartDate");
            Timestamp EndDate = rs.getTimestamp("EndDate");
            item = new Item(ItemID, SellerID, Title, Description, imageBytes, Category, Startprice, CurrentBid,
                    AuctionStatus, StartDate, EndDate);
        }

        return item;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        List<Item> items = new ArrayList<Item>();
        int ID = 1;
        while (this.get(ID) != null) {
            items.add(get(ID));
            ID++;
        }
        return items;
    }

    @Override
    public int save(Item t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public int insert(Item t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "insert into Item(ItemID, SellerID, Title, Description, Image, Category, Startpice, CUrrectBid, AuctionStatus, StartDate, EndDate) values (?, ? ,?, ?, ?, ?, ? ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, t.getItemID());
        ps.setInt(2, t.getSellerID());
        ps.setString(3, t.getTitle());
        ps.setString(4, t.getDescription());
        ps.setBytes(5, t.getImage());
        ps.setString(6, t.getCategory());
        ps.setDouble(7, t.getStartPrice());
        ps.setDouble(8, t.getCurrentBid());
        ps.setString(9, t.getAuctionStatus());
        ps.setTimestamp(10, t.getStartDate());
        ps.setTimestamp(11, t.getEndDate());
        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return result;
    }

    @Override
    public int update(Item t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "UPDATE Item set ItemID = ? SellerID = ?, Title = ?, Description = ?, Image = ?,Category = ?, Startpice, = ? CUrrectBid = ?, AuctionStatus, StartDate = ?, EndDate = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, t.getItemID());
        ps.setInt(2, t.getSellerID());
        ps.setString(3, t.getTitle());
        ps.setString(4, t.getDescription());
        ps.setBytes(5, t.getImage());
        ps.setString(6, t.getCategory());
        ps.setDouble(7, t.getStartPrice());
        ps.setDouble(8, t.getCurrentBid());
        ps.setString(9, t.getAuctionStatus());
        ps.setTimestamp(10, t.getStartDate());
        ps.setTimestamp(11, t.getEndDate());
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
