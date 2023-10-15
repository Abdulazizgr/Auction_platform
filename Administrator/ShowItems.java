import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class ShowItems extends JPanel {
    public DefaultTableModel model;
    public JTable table;
    public JScrollPane j1;
    public JLabel title;

    public ShowItems() throws SQLException {
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));

        title = new JLabel("Item List");
        title.setForeground(new Color(0, 0, 255));
        title.setBounds(450, 12, 182, 30);
        title.setFont(new Font("Arial", Font.PLAIN, 25));
        title.setBackground(new Color(35, 59, 97));
        title.setOpaque(true);
        title.setForeground(Color.white);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(glassyBorder);
        add(title);
        setLayout(null);
        setBounds(0, 0, 1060, 564);
        ArrayList<String[]> itemslist = new ArrayList<String[]>();
        String[] column = { "ID", "Item Name", "Description", "Image", "SellerName", "StartPrice" };
        itemslist = items();
        model = new DefaultTableModel(itemslist.size(), 6);
        model.setColumnIdentifiers(column);
        table = new JTable(model);
        TableColumnModel columnModel = table.getColumnModel();
        int i = 0;
        for (Object[] userdata : itemslist) {
            table.setValueAt(userdata[0], i, 0);
            table.setValueAt(userdata[1], i, 1);
            table.setValueAt(userdata[2], i, 2);
            table.setValueAt(userdata[3], i, 3);
            columnModel.getColumn(3).setCellRenderer(new ImageTableCellRenderer());
            table.setValueAt(userdata[4], i, 4);
            table.setValueAt(userdata[5], i, 5);
            i++;
        }
        setColumnsWidth(table, 1060, 5, 15, 20, 30, 20, 10);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        Dimension d = table.getPreferredSize();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(50, 50, 50, 50));
        scrollPane.setBounds(0, 10, d.width, 500);
        add(scrollPane);

    }

    // ===================================== a 2 dimensional array of items =========================
    public ArrayList<String[]> items() throws SQLException {
        ItemDAO itemdao = new ItemDAO();
        UserDAO userdao = new UserDAO();
        ArrayList<String[]> itemslist = new ArrayList<String[]>();
        java.util.List<Item> items = new ArrayList<>();
        try {
            items = itemdao.getAll();
        } catch (SQLException E) {
            E.printStackTrace();
        }
        int i = 0;
        for (Item item : items) {
            if(item.getAuctionStatus().equals("Active")){ 
            itemslist.add(new String[6]);
            itemslist.get(i)[0] = (item.getItemID() + "");
            itemslist.get(i)[1] = (item.getTitle());
            itemslist.get(i)[2] = (item.getDescription());
            itemslist.get(i)[3] = (item.getImagePath());
            itemslist.get(i)[4] = userdao.get(item.getSellerID())
                    .getFirstName();
            itemslist.get(i)[5] = (item.getStartPrice()) + "";
            i++;}
        }
        return itemslist;
    }

    // ====================================================================================================
    // ====================================table image renderer================================================================
    class ImageTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value instanceof File) {
                String imagePath = (String) value;
                try {
                    // Read the image file and create an ImageIcon
                    Image image = ImageIO.read(new File(imagePath));
                    ImageIcon icon = new ImageIcon(image);

                    // Scale the image to fit the cell dimensions
                    int cellWidth = table.getColumnModel().getColumn(column).getWidth();
                    int cellHeight = table.getRowHeight(row);
                    Image scaledImage = icon.getImage().getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(scaledImage);

                    // Set the ImageIcon as the cell value
                    setIcon(icon);
                    setText(null);
                } catch (Exception e) {
                    // Handle any exceptions when reading or scaling the image
                    e.printStackTrace();
                }
            } else {
                // Reset the cell content if the value is not an image file
                setIcon(null);
                setText(value != null ? value.toString() : "");
            }

            return component;
        }
    }
    // ==========================================================================================================//

    // ========================================table column configuration==================================//
    public static void setColumnsWidth(JTable table, int tablePreferredWidth,
            double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
        }
    }
    // =====================================================================================================//

}
