package User;

import Commonclasses.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ShowItems extends JPanel {
    public DefaultTableModel model;
    public JTable table;
    public JScrollPane j1;
    public JLabel title;
    public JButton refresh;
    public ImageIcon ic_on;

    ShowItems() throws SQLException {
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
        model = new DefaultTableModel(itemslist.size(), 6) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 3:
                        return ImageIcon.class;
                    default:
                        return String.class;

                }

            }
        };

        model.setColumnIdentifiers(column);
        table = new JTable(model);
        int i = 0;
        for (Object[] items : itemslist) {
            table.setValueAt(items[0], i, 0);
            table.setValueAt(items[1], i, 1);
            table.setValueAt(items[2], i, 2);
            ic_on = new ImageIcon((String) items[3]);
            table.setValueAt(ic_on, i, 3);
            table.setValueAt(items[4], i, 4);
            table.setValueAt(items[5], i, 5);
            i++;
        }
        setColumnsWidth(table, 1060, 5, 15, 20, 30, 20, 10);
        table.setRowHeight(100);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        Dimension d = table.getPreferredSize();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(50, 50, 50, 50));
        scrollPane.setBounds(0, 10, d.width, 500);
        add(scrollPane);

        refresh = Button.CustomButton("Refresh");
        refresh.setBounds(0, 510, 182, 30);
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTableData();
            }
        });
        add(refresh);
        setVisible(true);

    }
    private void refreshTableData() {
        ArrayList<String[]> itemslist = items();
        model.setRowCount(0); // Clear the existing table data
        int i = 0;
        for (Object[] items : itemslist) {
            table.setValueAt(items[0], i, 0);
            table.setValueAt(items[1], i, 1);
            table.setValueAt(items[2], i, 2);
            ic_on = new ImageIcon((String) items[3]);
            table.setValueAt(ic_on, i, 3);
            table.setValueAt(items[4], i, 4);
            table.setValueAt(items[5], i, 5);
            i++;
        }
    }

    // ===================================== a 2 dimensional array of items
    // =========================
    public ArrayList<String[]> items() {
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
            if (item.getAuctionStatus().equals("Active")&&(!(item.getUserID()==0))) {
                itemslist.add(new String[6]);
                itemslist.get(i)[0] = (item.getItemID() + "");
                itemslist.get(i)[1] = (item.getTitle());
                itemslist.get(i)[2] = (item.getDescription());
                itemslist.get(i)[3] = (item.getImagePath());
                try {
                    itemslist.get(i)[4] = userdao.get(item.getUserID())
                            .getFirstName();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                itemslist.get(i)[5] = (item.getStartPrice()) + "";
                i++;
            }
        }
        return itemslist;
    }

    // ==========================================================================================================//

    // ========================================table column
    // configuration==================================//
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