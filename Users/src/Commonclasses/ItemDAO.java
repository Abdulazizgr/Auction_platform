import java.sql.Timestamp;
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
        String sql = "select ItemID ,Title ,Description ,ImagePath ,Category ,StartPrice ,CurrentBid ,AuctionStatus ,StartDate ,EndDate from Item where ItemId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int ItemID = rs.getInt("ItemID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String imagePath = rs.getString("ImagePath");
            String Category = rs.getString("Category");
            double Startprice = rs.getInt("Startprice");
            double CurrentBid = rs.getInt("CurrentBid");
            String AuctionStatus = rs.getString("AuctionStatus");
            Timestamp StartDate = rs.getTimestamp("StartDate");
            Timestamp EndDate = rs.getTimestamp("EndDate");
            int SellerID = rs.getInt("SellerID");
            item = new Item(ItemID, Title, Description, imagePath, Category, Startprice, CurrentBid,
                    AuctionStatus, StartDate, EndDate, SellerID);
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
    public int insert(Item t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "insert into Item(Title, Description, Image, Category, Startpice, CUrrectBid, AuctionStatus, StartDate, EndDate) values (?, ?, ?, ?, ? ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, t.getItemID());
        ps.setString(2, t.getTitle());
        ps.setString(3, t.getDescription());
        ps.setString(4, t.getImagePath());
        ps.setString(5, t.getCategory());
        ps.setDouble(6, t.getStartPrice());
        ps.setDouble(7, t.getCurrentBid());
        ps.setString(8, t.getAuctionStatus());
        ps.setTimestamp(9, t.getStartDate());
        ps.setTimestamp(10, t.getEndDate());
        int result = ps.executeUpdate();
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return result;
    }

    @Override
    public int update(Item t) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "UPDATE Item set ItemID = ? Title = ?, Description = ?, Image = ?,Category = ?, Startpice, = ? CUrrectBid = ?, AuctionStatus, StartDate = ?, EndDate = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, t.getItemID());
        ps.setString(2, t.getTitle());
        ps.setString(3, t.getDescription());
        ps.setString(4, t.getImagePath());
        ps.setString(5, t.getCategory());
        ps.setDouble(6, t.getStartPrice());
        ps.setDouble(7, t.getCurrentBid());
        ps.setString(8, t.getAuctionStatus());
        ps.setTimestamp(9, t.getStartDate());
        ps.setTimestamp(10, t.getEndDate());
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
