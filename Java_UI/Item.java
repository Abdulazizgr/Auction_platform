import java.security.Timestamp;

public class Item {
    private int ItemID;
    private int SellerID;
    private String Title;
    private String Description;
    private String Image;
    private String Category;
    private int StartPrice;
    private int CurrentBid;
    private String AuctionStatus;
    private Timestamp StartDate;
    private Timestamp EndDate;

    public Item(int itemID, int sellerID, String title, String description, String image, String category,
            int startPrice,
            int currentBid, String auctionStatus, Timestamp startDate, Timestamp endDate) {
        ItemID = itemID;
        SellerID = sellerID;
        Title = title;
        Description = description;
        Image = image;
        Category = category;
        StartPrice = startPrice;
        CurrentBid = currentBid;
        AuctionStatus = auctionStatus;
        StartDate = startDate;
        EndDate = endDate;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public int getSellerID() {
        return SellerID;
    }

    public void setSellerID(int sellerID) {
        SellerID = sellerID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getStartPrice() {
        return StartPrice;
    }

    public void setStartPrice(int startPrice) {
        StartPrice = startPrice;
    }

    public int getCurrentBid() {
        return CurrentBid;
    }

    public void setCurrentBid(int currentBid) {
        CurrentBid = currentBid;
    }

    public String getAuctionStatus() {
        return AuctionStatus;
    }

    public void setAuctionStatus(String auctionStatus) {
        AuctionStatus = auctionStatus;
    }

    public Timestamp getStartDate() {
        return StartDate;
    }

    public void setStartDate(Timestamp startDate) {
        StartDate = startDate;
    }

    public Timestamp getEndDate() {
        return EndDate;
    }

    public void setEndDate(Timestamp endDate) {
        EndDate = endDate;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    @Override
    public String toString() {
        return "Item [ItemID=" + ItemID + ", SellerID=" + SellerID + ", Title=" + Title + ", Description=" + Description
                +
                ", Image=" + Image + ", Category=" + Category + ", StartProe=" + StartPrice + ", CurrentBid="
                + CurrentBid + ", AuctionStatus="
                + AuctionStatus + ", StartDate=" + StartDate + ", EndDate=" + EndDate + "]";
    }

}
