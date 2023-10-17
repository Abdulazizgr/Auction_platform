package CommonClasses;

import java.sql.Timestamp;

public class Item {
    private int ItemID;
    private String Title;
    private String Description;
    private String ImagePath;
    private String ItemState;
    private String Category;
    private double StartPrice;
    private String AuctionStatus;
    private Timestamp StartDate;
    private Timestamp soldDate;
    private int UserID;

    public Item(int itemID, String title, String description, String Path, String category,
            double startPrice, String auctionStatus, Timestamp startDate2, Timestamp soldDate2, int UserID,
            String ItemState) {
        this.ItemID = itemID;
        this.Title = title;
        this.Description = description;
        this.ImagePath = Path;
        this.ItemState = ItemState;
        this.Category = category;
        this.StartPrice = startPrice;
        this.AuctionStatus = auctionStatus;
        this.StartDate = startDate2;
        this.soldDate = soldDate2;
        this.UserID = UserID;
    }

    public Item(String title, String description, String Path, String category,
            double startPrice, String auctionStatus, int UserID) {
        this.Title = title;
        this.Description = description;
        this.ImagePath = Path;
        this.Category = category;
        this.StartPrice = startPrice;
        this.AuctionStatus = auctionStatus;
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

    public Timestamp getsoldDate() {
        return soldDate;
    }

    public void setsoldDate(Timestamp soldDate) {
        soldDate = soldDate;
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
                ", Image=" + ImagePath + ", Category=" + Category + ", StartProe=" + StartPrice + ", AuctionStatus="
                + AuctionStatus + ", StartDate=" + StartDate + ", soldDate=" + soldDate + "]";
    }

}
