import java.sql.Timestamp;

public class Item {
    private int ItemID;
    private int SellerID;
    private String Title;
    private String Description;
    private byte[] Image;
    private String Category;
    private double StartPrice;
    private double CurrentBid;
    private String AuctionStatus;
    private Timestamp StartDate;
    private Timestamp EndDate;

    public Item(int itemID, int sellerID, String title, String description, byte[] image2, String category,
            double startPrice,
            double currentBid, String auctionStatus, Timestamp startDate2, Timestamp endDate2) {
        ItemID = itemID;
        SellerID = sellerID;
        Title = title;
        Description = description;
        Image = image2;
        Category = category;
        StartPrice = startPrice;
        CurrentBid = currentBid;
        AuctionStatus = auctionStatus;
        StartDate = startDate2;
        EndDate = endDate2;
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

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public double getStartPrice() {
        return StartPrice;
    }

    public void setStartPrice(double startPrice) {
        StartPrice = startPrice;
    }

    public double getCurrentBid() {
        return CurrentBid;
    }

    public void setCurrentBid(double currentBid) {
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
