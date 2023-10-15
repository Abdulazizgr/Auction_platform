import java.sql.Timestamp;

public class Item {
    private int ItemID;
    private int SellerID;
    private int BuyerID;
    private String Title;
    private String Description;
    private String ImagePath;
    private String Category;
    private double StartPrice;
    private double CurrentBid;
    private String AuctionStatus;
    private Timestamp StartDate;
    private Timestamp EndDate;

    public Item(int itemID,String title, String description, String Path, String category,
            double startPrice,
            double currentBid, String auctionStatus, Timestamp startDate2, Timestamp endDate2, int SellerID) {
        ItemID = itemID;
        Title = title;
        Description = description;
        ImagePath = Path;
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

    public String getImagePath() {
        return ImagePath;
    }

    public void setImage(String imagepath) {
        ImagePath = imagepath;
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
     public int getSellerID() {
        return SellerID;
    }

    public void setSellerID(int sellerID) {
        SellerID = sellerID;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
     public int getBuyerID() {
        return BuyerID;
    }

    public void setBuyerID(int buyerID) {
        BuyerID = buyerID;
    }




    @Override
    public String toString() {
        return "Item [ItemID=" + ItemID + " , SellerID="+ SellerID + " , BuyerID="+BuyerID+" , Title=" + Title + ", Description=" + Description
                +
                ", Image=" + ImagePath + ", Category=" + Category + ", StartProe=" + StartPrice + ", CurrentBid="
                + CurrentBid + ", AuctionStatus="
                + AuctionStatus + ", StartDate=" + StartDate + ", EndDate=" + EndDate + "]";
    }

}
