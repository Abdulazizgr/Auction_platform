package Commonclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerDAO implements DAO<Buyer> {

    @Override
    public Buyer get(int ID) throws SQLException {
        Connection con = Database.getConnection();
        Buyer buyer = null;
        String sql = "select BidID ,Title ,Description ,ImagePath ,Category ,StartPrice ,CurrentBid ,AuctionStatus ,StartDate ,EndDate from Bid where BidId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int BuyerID = rs.getInt("BuyerID");
            int BidID = rs.getInt("BidID");
            buyer = new Buyer();
            buyer.setBidID(BidID);
            buyer.setBuyerID(BuyerID);
        }
        return buyer;
    }

    @Override
    public List<Buyer> getAll() throws SQLException {
        List<Buyer> buyers = new ArrayList<Buyer>();
        Connection con = Database.getConnection();
        String sql = "select * from Buyers";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Buyer buyer = null;
            int BuyerID = rs.getInt("BuyerID");
            int BidID = rs.getInt("BidID");
            buyer = new Buyer();
            buyer.setBidID(BidID);
            buyer.setBuyerID(BuyerID);
            buyers.add(buyer);

        }
        return buyers;
    }

    public List<Item> getItemfromBuyer(int ID) throws SQLException {
        System.out.println("user ID = " + ID);
        List<Item> it = new ArrayList<Item>();
        Connection con = Database.getConnection();
        String sql = "select * from Buyer where Buyer.UserID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        System.out.println("debug 3");
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        ;
        while (rs.next()) {
            System.out.println(rs.getInt("ItemID") + "debug 2");
            int itemID = rs.getInt("ItemID");
            System.out.println(itemID);
            ItemDAO it_dao = new ItemDAO();
            Item store = it_dao.get(itemID);
            it.add(store);
            System.out.println(store.getTitle());
        }
        return it;
    }

    @Override
    public int insert(Buyer t) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(Buyer t) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(Buyer t) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
