
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItem extends JPanel {

    private JTextField itemIdField;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextField imagePathField;
    private JTextField categoryField;
    private JTextField startPriceField;
    private JButton addButton;

    public AddItem() {
        setLayout(new GridLayout(8, 2));

        JLabel itemIdLabel = new JLabel("Item ID:");
        itemIdField = new JTextField();
        add(itemIdLabel);
        add(itemIdField);

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        add(titleLabel);
        add(titleField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionArea = new JTextArea();
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        add(descriptionLabel);
        add(descriptionScrollPane);

        JLabel imagePathLabel = new JLabel("Image Path:");
        imagePathField = new JTextField();
        add(imagePathLabel);
        add(imagePathField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField();
        add(categoryLabel);
        add(categoryField);

        JLabel startPriceLabel = new JLabel("Start Price:");
        startPriceField = new JTextField();
        add(startPriceLabel);
        add(startPriceField);

        addButton = new JButton("Add Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToAuctionList();
            }
        });
        add(addButton);
    }

    private void addItemToAuctionList() {
        int itemId = Integer.parseInt(itemIdField.getText());
        String title = titleField.getText();
        String description = descriptionArea.getText();
        String imagePath = imagePathField.getText();
        String category = categoryField.getText();
        double startPrice = Double.parseDouble(startPriceField.getText());

        itemIdField.setText("");
        titleField.setText("");
        descriptionArea.setText("");
        imagePathField.setText("");
        categoryField.setText("");
        startPriceField.setText("");

        JOptionPane.showMessageDialog(this, "Item added successfully!");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Item Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        AddItem panel = new AddItem();
        frame.add(panel);

        frame.setVisible(true);
    }
}
