package CommonClasses;
public class Seller{
    private int sellerID;
    private int UserID;
    private int ItemID;

    public Seller(int sellerID, int userID,int ItemID) {
        this.sellerID = sellerID;
        this.UserID = userID;
        this.ItemID  = ItemID;
        
    }

    public int getUserID() {
        return UserID;
    }

    public int getSellerID() {
        return sellerID;
    }

     public int getItemID() {
        return ItemID;
    }

}