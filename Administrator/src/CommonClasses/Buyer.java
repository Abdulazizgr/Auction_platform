package CommonClasses;

public class Buyer {
    private int BuyerID;
    private int ItemID;
    private int UserID;
    
    public Buyer(int buyerID, int ItemID, int userID) {
        this.BuyerID = buyerID;
        this.ItemID = ItemID;
        this.UserID = userID;
    }
    public int getUserID() {
        return UserID;
    }
    public void setUserID(int userID) {
        UserID = userID;
    }
    public int getBuyerID() {
        return BuyerID;
    }
    public void setBuyerID(int buyerID) {
        BuyerID = buyerID;
    }
    public int getItemID() {
        return ItemID;
    }
    public void setItemID(int itemID) {
        ItemID = itemID;
    }
    
    
}
