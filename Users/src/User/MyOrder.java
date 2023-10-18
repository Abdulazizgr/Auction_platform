package User;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Commonclasses.Button;
import Commonclasses.BuyerDAO;
import Commonclasses.Item;
import Commonclasses.UserDAO;

public class MyOrder extends JPanel {
    public UserDAO us_dao;
    public Item item;
    public DefaultTableModel model;
    public JTable table;
    public JLabel top, label_1;
    public ImageIcon image;
    public BuyerDAO buy_dao;
    public JScrollPane scrol_1;
    public int ID;
    private ImageIcon ic_on;
    private JButton refresh;

    MyOrder(int ID) {
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));

        top = new JLabel("List of Item Purchased");
        top.setForeground(new Color(0, 0, 255));
        top.setBounds(450, 12, 400, 30);
        top.setFont(new Font("Arial", Font.PLAIN, 25));
        top.setBackground(new Color(35, 59, 97));
        top.setOpaque(true);
        top.setForeground(Color.white);
        top.setHorizontalAlignment(JLabel.CENTER);
        top.setBorder(glassyBorder);
        add(top);
        setLayout(null);
        setBounds(0, 0, 1060, 564);
        ArrayList<String[]> itemslist = new ArrayList<String[]>();
        String[] column = { "ID", "Item Name", "Description", "Image" };
        itemslist = items(ID);
        model = new DefaultTableModel(itemslist.size(), 4) {
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
        ImageIcon ic_on;
        int i = 0;
        for (Object[] userdata : itemslist) {
            System.out.println(userdata[0]);
            System.out.println(i);
            table.setValueAt(userdata[0], i, 0);
            table.setValueAt(userdata[1], i, 1);
            table.setValueAt(userdata[2], i, 2);
            ic_on = new ImageIcon((String) userdata[3]);
            table.setValueAt(ic_on, i, 3);
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
                System.out.println("success");
                refreshTableData();
            }
        });
        add(refresh);
        setVisible(true);
        setVisible(true);
    }

    private void refreshTableData() {
        ArrayList<String[]> itemslist = items(ID);
        model.setRowCount(0); // Clear the existing table data
        int i = 0;
        for (Object[] userdata : itemslist) {
            table.setValueAt(userdata[0], i, 0);
            table.setValueAt(userdata[1], i, 1);
            table.setValueAt(userdata[2], i, 2);
            ic_on = new ImageIcon((String) userdata[3]);
            table.setValueAt(ic_on, i, 3);
            i++;
        }
    }

    public ArrayList<String[]> items(int ID) {
        BuyerDAO getter = new BuyerDAO();
        UserDAO userdao = new UserDAO();
        ArrayList<String[]> itemslist = new ArrayList<String[]>();
        List<Item> items = new ArrayList<Item>();
        try {
            items = getter.getItemfromBuyer(ID);
            System.out.println("debug");
        } catch (SQLException E) {
            JOptionPane.showMessageDialog(null, E.getMessage());
        }
        int i = 0;
        for (Item item : items) {
            if (item.getAuctionStatus().equals("Active")) {
                itemslist.add(new String[6]);
                System.out.println(item.getTitle());
                itemslist.get(i)[0] = (item.getItemID() + "");
                itemslist.get(i)[1] = (item.getTitle());
                itemslist.get(i)[2] = (item.getDescription());
                itemslist.get(i)[3] = (item.getImagePath());
                try {
                    itemslist.get(i)[4] = userdao.get(item.getUserID())
                            .getFirstName();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                itemslist.get(i)[5] = (item.getStartPrice()) + "";
                i++;
            }
        }
        return itemslist;
    }

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
}