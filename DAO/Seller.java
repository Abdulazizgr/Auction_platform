import java.sql.SQLException;

public class Seller{
    private int sellerID;
    private int UserID;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    private User user;
    private Item item;


    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void addItem(Item item) throws SQLException {
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.insert(item);
    }
}