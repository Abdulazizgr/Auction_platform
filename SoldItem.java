package j1;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SoldItem extends JFrame {
    private List<Item> soldItems;
    private JTable soldItemsTable;

    public SoldItem() {
        soldItems = new ArrayList<>();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Sold Items");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        String[] columnNames = {"Item ID", "Sold Date", "Sold Price", "Buyer", "Seller", "Title"};
        Object[][] data = new Object[soldItems.size()][6];

        for (int i = 0; i < soldItems.size(); i++) {
            Item item = soldItems.get(i);
            data[i][0] = item.getId();
            data[i][1] = item.getSoldDate();
            data[i][2] = item.getSoldPrice();
            data[i][3] = item.getBuyer();
            data[i][4] = item.getSeller();
            data[i][5] = item.getTitle();
        }

        soldItemsTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(soldItemsTable);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addSoldItem(Item item) {
        soldItems.add(item);
    }

    public static void main(String[] args) {
        SoldItem soldItemPanel = new SoldItem();

        Item item1 = new Item(1, "2022-01-01", 100.0, "John", "Alice", "Item 1");
        Item item2 = new Item(2, "2022-01-02", 200.0, "Bob", "Charlie", "Item 2");

        soldItemPanel.addSoldItem(item1);
        soldItemPanel.addSoldItem(item2);

        SwingUtilities.invokeLater(() -> soldItemPanel.setVisible(true));
    }
}

class Item {
    private int id;
    private String soldDate;
    private double soldPrice;
    private String buyer;
    private String seller;
    private String title;

    public Item(int id, String soldDate, double soldPrice, String buyer, String seller, String title) {
        this.id = id;
        this.soldDate = soldDate;
        this.soldPrice = soldPrice;
        this.buyer = buyer;
        this.seller = seller;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getSoldDate() {
        return soldDate;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getSeller() {
        return seller;
    }

    public String getTitle() {
        return title;
    }

}
