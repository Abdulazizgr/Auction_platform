package Commonclasses;

import java.sql.Timestamp;

public class Item {
    private int ItemID;
    private String Title;
    private String Description;
    private String ImagePath;
    private String ItemState;
    private String Category;
    private double StartPrice;
    private double CurrentBid;
    private String AuctionStatus;
    private Timestamp StartDate;
    private Timestamp EndDate;
    private int UserID;

    public Item(int itemID, String title, String description, String Path, String category,
            double startPrice,
            double currentBid, String auctionStatus, Timestamp startDate2, Timestamp endDate2, int UserID,
            String ItemState) {
        this.ItemID = itemID;
        this.Title = title;
        this.Description = description;
        this.ImagePath = Path;
        this.ItemState = ItemState;
        this.Category = category;
        this.StartPrice = startPrice;
        this.CurrentBid = currentBid;
        this.AuctionStatus = auctionStatus;
        this.StartDate = startDate2;
        this.EndDate = endDate2;
        this.UserID = UserID;
    }

    public Item(String title, String description, String Path, String category,
            double startPrice,
            double currentBid, String auctionStatus, Timestamp startDate2, Timestamp endDate2, int UserID,
            String ItemState) {
        this.Title = title;
        this.Description = description;
        this.ImagePath = Path;
        this.ItemState = ItemState;
        this.Category = category;
        this.StartPrice = startPrice;
        this.CurrentBid = currentBid;
        this.AuctionStatus = auctionStatus;
        this.StartDate = startDate2;
        this.EndDate = endDate2;
        this.UserID = UserID;
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

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public int getUserID() {
        return UserID;
    }

    public String getItemState() {
        return ItemState;
    }

    @Override
    public String toString() {
        return "Item [ItemID=" + ItemID + " , Title=" + Title + ", Description=" + Description
                +
                ", Image=" + ImagePath + ", Category=" + Category + ", StartProe=" + StartPrice + ", CurrentBid="
                + CurrentBid + ", AuctionStatus="
                + AuctionStatus + ", StartDate=" + StartDate + ", EndDate=" + EndDate + "]";
    }

}
