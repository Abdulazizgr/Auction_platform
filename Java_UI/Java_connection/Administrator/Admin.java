import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin extends JFrame implements ActionListener {
    JButton button1, button2, button3, button4, button5, button6, button7;
    ArrayList<JButton> buttons;
    JPanel panel3;
    JLabel label2;
    JLabel contentLabel;
    JTable table;
    DefaultTableModel model;

    Admin() {
        table = new JTable(2,9);
        panel3 = new JPanel();
        label2 = new JLabel();
        ImageIcon welcome = new ImageIcon(
                "/mnt/91953372-6625-495a-af25-22fae88bb951/Projects/Auction_platform/Java_UI/Java_connection/Administrator/Welcome.jpeg");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screensize = toolkit.getScreenSize();
        JPanel Panel1 = new JPanel(new BorderLayout());table = new JTable(model);
        JLabel label1 = new JLabel();
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));
        ImageIcon icon = new ImageIcon("/mnt/91953372-6625-495a-af25-22fae88bb951/Projects/Auction_platform/Java_UI/Java_connection/Administrator/auction2.jpg");
        JPanel panel2 = new JPanel();
        contentLabel = new JLabel();
        contentLabel.setOpaque(true);
        contentLabel.setBounds(0, 0, 1060, 700);
        contentLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLabel.setVerticalAlignment(JLabel.CENTER);
        contentLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLabel.setIcon(welcome);

        button1 = CustomButton("Home");
        button2 = CustomButton("Add Item");
        button3 = CustomButton("Show Item");
        button4 = CustomButton("Start Auction");
        button5 = CustomButton("Show Customers");
        button6 = CustomButton("Show Sold Items");
        button7 = CustomButton("Logout");

        buttons = new ArrayList<>(7);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);

        for (int i = 0; i < 7; i++) {
            buttons.get(i).addActionListener(this);
        }
        for (int i = 0; i < 7; i++) {
            buttons.get(i).setBounds(0, i * 55, 300, 50);
            panel2.add(buttons.get(i));
        }

        label1.setSize(1000,200);
        label1.setBackground(Color.white);
        label1.setOpaque(true);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setText("AUCTION DAILY");
        label1.setIcon(icon);
        label1.setHorizontalTextPosition(JLabel.RIGHT);
        label1.setVerticalTextPosition(JLabel.CENTER);
        label1.setIconTextGap(100);
        label1.setFont(new Font(Font.SERIF, Font.BOLD, 40));

        label2 = new JLabel();
        label2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        label2.setText("WELCOME TO ADMINISTRATOR PORTAL");
        label2.setBounds(5, 85, screensize.width - 10, 25);
        label2.setBorder(glassyBorder);
        label2.setBackground(new Color(35, 59, 97));
        label2.setForeground(Color.white);
        label2.setOpaque(true);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setPreferredSize(new Dimension(screensize.width - 10, 25));

        Panel1.setBounds(0, 0, screensize.width, 80);
        Panel1.setBackground(Color.white);
        Panel1.setPreferredSize(new Dimension(screensize.width, 100));

        panel2.setBackground(new Color(167, 192, 232));
        panel2.setLayout(null);
        panel2.setPreferredSize(new Dimension(300, 200));

        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setBackground(new Color(167, 192, 232));
        panel3.setPreferredSize(new Dimension(1060, 500));
        panel3.add(contentLabel);
        Panel1.add(label2, BorderLayout.SOUTH);
        Panel1.add(label1, BorderLayout.NORTH);

        this.setLayout(new BorderLayout(5, 5));
        this.setMinimumSize(new Dimension(1000, 600));
        this.setSize(screensize.width, screensize.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(113, 163, 240));
        this.add(Panel1, BorderLayout.NORTH);
        //Panel1.setPreferredSize(new Dimension(screensize.width, 80));
        Panel1.add(label2, BorderLayout.SOUTH);
        Panel1.add(label1, BorderLayout.WEST);
        label2.setPreferredSize(new Dimension(screensize.width - 10, 25));
        this.add(panel2, BorderLayout.WEST);
        this.add(panel3, BorderLayout.EAST);
        this.setVisible(true);
    }

    public JButton CustomButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setBackground(new Color(35, 59, 97));
        button.setForeground(Color.white);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusable(false);
        button.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    button.setBackground(new Color(60, 123, 240));
                } else {
                    button.setBackground(new Color(35, 59, 97));
                }
            }
        });
        return button;
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

    public ArrayList<ArrayList<String>> users(){
        UserDAO userDAO = new UserDAOImplementation();
            ArrayList<ArrayList<String>> userslist = new ArrayList<ArrayList<String>>();
            java.util.List<User> users = new ArrayList<>();
        try {
                users = userDAO.getAll();
            } catch (SQLException E) {
                System.out.println(E.getMessage());
            }
            int i = 0;
            for (User user : users) {
                userslist.add(new ArrayList<>());
                userslist.get(i).add((user.getUserID())+"");
                userslist.get(i).add(user.getFirstName());
                userslist.get(i).add(user.getLastName());
                userslist.get(i).add(user.getEmail());
                userslist.get(i).add(user.getPassword());
                userslist.get(i).add(user.getRegistrationDate());
                userslist.get(i).add(user.getBankName());
                userslist.get(i).add(user.getAccountHolderName());
                userslist.get(i).add(user.getAccountNumber()+"");
                i++;
            }
            return userslist;
    };

    public ArrayList<ArrayList<String>> items(){
        UserDAO userDAO = new UserDAOImplementation();
        ItemDAO itemDAO = new ItemDAOImplementation();
            ArrayList<ArrayList<String>> itemlist = new ArrayList<ArrayList<String>>();
            java.util.List<Item> items = new ArrayList<>();
        try {
                items = itemDAO.getAll();
            } catch (SQLException E) {
                System.out.println(E.getMessage());
            }
            int i = 0;
            for (Item item : items) {
                itemlist.add(new ArrayList<>());
                itemlist.get(i).add((item.getItemID())+"");
                itemlist.get(i).add(userDAO.get(item.getSellerID()).getFirstName());
                itemlist.get(i).add(item.getTitle());
                itemlist.get(i).add(item.getDescription());
                itemlist.get(i).add(item.getImage());// to be edited
                itemlist.get(i).add(item.getCategory());
                itemlist.get(i).add(item.getStartDate()+"");
                itemlist.get(i).add(item.getCurrentBid()+"");
                itemlist.get(i).add(item.getAuctionStatus()+"");
                itemlist.get(i).add(item.getStartPrice()+"");
                itemlist.get(i).add(item.getEndDate()+"");
                i++;
            }
            return itemlist;
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String[] column = {"ID" ,"FirstName" ,"LastName" ,"Email" ,"Password" ,"RegistrationDate" ,"BankName", "AccountHolderName", "AccountNumber" };
        
        if (clickedButton == button1) {
            panel3.removeAll(); 
            panel3.add(contentLabel);
            panel3.revalidate();
            panel3.repaint();
        } 
                else if(clickedButton == button2) {}
        else if(clickedButton == button3) {}
        else if(clickedButton == button4) {}
        else if (clickedButton == button5) {
            ArrayList<ArrayList<String>> userslist = new ArrayList<ArrayList<String>>();
            userslist = users();
            int rows = userslist.get(0).size();
            int i=0;
            model = new DefaultTableModel(rows,9);
            model.setColumnIdentifiers(column);
            table = new JTable(model);
            for (ArrayList<String> userdata : userslist) {
                table.setValueAt(userdata.get(0),i,0);
                table.setValueAt(userdata.get(1),i,1);
                table.setValueAt(userdata.get(2),i,2);
                table.setValueAt(userdata.get(3),i,3);
                table.setValueAt(userdata.get(4),i,4);
                table.setValueAt(userdata.get(5),i,5);
                table.setValueAt(userdata.get(6),i,6);
                table.setValueAt(userdata.get(7),i,7);
                table.setValueAt(userdata.get(8),i,8);
                i++;
            }
            table.setRowHeight(30);
            setColumnsWidth(table, 1060, 2,10,10,18,10,10,10,10,20);
            panel3.removeAll(); 
            panel3.add(new JScrollPane(table));
            panel3.revalidate();
            panel3.repaint();
        }
            else if(clickedButton == button6) {}
            else if(clickedButton == button7) {}
    }

    public static void main(String[] args) {
        new Admin();

    }
}
