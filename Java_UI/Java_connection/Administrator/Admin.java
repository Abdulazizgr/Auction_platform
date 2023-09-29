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
    JButton startbutton;
    JButton deleteuser;
    UserDAO userDAO;

    Admin() {
        table = new JTable(2,9);
        panel3 = new JPanel();
        label2 = new JLabel();
        startbutton = CustomButton("START AUCTION");
        deleteuser = CustomButton("DELETE USER");
        ImageIcon welcome = new ImageIcon(
                "Java_UI/Java_connection/Administrator/Welcome.jpeg");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screensize = toolkit.getScreenSize();
        JPanel Panel1 = new JPanel(new BorderLayout());table = new JTable(model);
        JLabel label1 = new JLabel();
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));
        ImageIcon icon = new ImageIcon("Java_UI/Java_connection/Administrator/auction2.jpg");
        JPanel panel2 = new JPanel();
        userDAO = new UserDAOImplementation();

        contentLabel = new JLabel();
        contentLabel.setOpaque(true);
        contentLabel.setBounds(50, 30, 950, 570);
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
        panel3.setLayout(null);
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

    public ArrayList<Object[]> users(){
            ArrayList<Object[]> userslist = new ArrayList<Object[]>();
            java.util.List<User> users = new ArrayList<>();
        try {
                users = userDAO.getAll();
            } catch (SQLException E) {
                System.out.println(E.getMessage());
            }
            int i = 0;
            for (User user : users) {
                userslist.add(new Object[6]);
                userslist.get(i)[0] = (user.getUserID());
                userslist.get(i)[1] = (user.getFirstName());
                userslist.get(i)[2] = (user.getLastName());
                userslist.get(i)[3] = (user.getEmail());
                userslist.get(i)[4] = (user.getRegistrationDate());
                i++;
            }
            return userslist;
    };

    public ArrayList<Object[]> items()throws SQLException{
        UserDAO userDAO = new UserDAOImplementation();
        ItemDAO itemDAO = new ItemDAOImplementation();
            ArrayList<Object[]> itemlist = new ArrayList<Object[]>();
            java.util.List<Item> items = new ArrayList<>();
            items = itemDAO.getAll();
            int i = 0;
            for (Item item : items) {
                itemlist.add(new Object[11]);
                itemlist.get(i)[0] = ((item.getItemID()));
                itemlist.get(i)[1] = (userDAO.get(item.getSellerID()).getFirstName());
                itemlist.get(i)[2] = (item.getTitle());
                itemlist.get(i)[3] = (item.getDescription());
                itemlist.get(i)[4] = (item.getImage());
                itemlist.get(i)[5] = (item.getCategory());
                itemlist.get(i)[6] = (item.getStartDate());
                itemlist.get(i)[7] = (item.getCurrentBid());
                itemlist.get(i)[8] = (item.getAuctionStatus());
                itemlist.get(i)[9] = (item.getStartPrice());
                itemlist.get(i)[10] = (item.getEndDate());
                i++;
            }
            return itemlist;
    };

    public void removeSelectedRow(){
        deleteuser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(table.getSelectedRow()!=-1){
                    try {
                        userDAO.delete(userDAO.get((int) table.getValueAt(table.getSelectedRow(),0)));
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    model.removeRow(table.getSelectedRow());
                    int selectedOption =  JOptionPane.showOptionDialog(null, "User Deleted Succesfully!!!", null,JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, 0);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
       // clickedButton.setBackground(new Color(60, 123, 240));
        String[] column = {"ID" ,"FirstName" ,"LastName" ,"Email" ,"RegistrationDate"};
        
        if (clickedButton == button1) {
            button1.setBackground(new Color(60, 123, 240));
            panel3.removeAll(); 
            panel3.add(contentLabel);
            panel3.revalidate();
            panel3.repaint();
        } 
                else if(clickedButton == button2) {}
        else if(clickedButton == button3) {}
        else if(clickedButton == button4) {
            startbutton.setBounds((panel3.getWidth()/2)-200, (panel3.getHeight()/2)-100, 400, 100);
            panel3.removeAll(); 
            panel3.add(startbutton);
            panel3.revalidate();
            panel3.repaint();
        }
        else if (clickedButton == button5) {
            ArrayList<Object[]> userslist = new ArrayList<Object[]>();
            userslist = users();
            int rows = userslist.size();
            int i=0;
            model = new DefaultTableModel(rows,5);
            model.setColumnIdentifiers(column);
            table = new JTable(model);
            for (Object[] userdata : userslist) {
                table.setValueAt(userdata[0],i,0);
                table.setValueAt(userdata[1],i,1);
                table.setValueAt(userdata[2],i,2);
                table.setValueAt(userdata[3],i,3);
                table.setValueAt(userdata[4],i,4);
                i++;
            }
            table.setRowHeight(30);
            setColumnsWidth(table, 1060, 10,20,20,30,20);
            table.setFont(new Font("Arial", Font.PLAIN, 15));
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
            Dimension d = table.getPreferredSize();
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(new EmptyBorder(50,50,50,50));
            scrollPane.setBounds(0, 0, d.width,500);
            deleteuser.setBounds((panel3.getWidth()/2)+50, (panel3.getHeight()/2)+200, 300, 50);
            panel3.removeAll(); 
            panel3.add(scrollPane);
            panel3.add(deleteuser);
            panel3.revalidate();
            panel3.repaint();
            removeSelectedRow();
        }
            else if(clickedButton == button6) {}
            else if(clickedButton == button7) {}
    }

    public static void main(String[] args) {
        new Admin();

    }
}
