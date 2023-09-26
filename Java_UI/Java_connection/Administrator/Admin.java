import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Admin extends JFrame implements ActionListener {
    JButton button1, button2, button3, button4, button5, button6, button7;
    ArrayList<JButton> buttons;
    JPanel panel2;
    DefaultTableModel model;
    JTable table;

    // a custom button with desired effect
    public JButton CustomButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setBackground(new Color(35, 59, 97));
        button.setForeground(Color.white);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusable(false);
        button.getModel().addChangeListener(new ChangeListener() {
            @Override
            // a state change for what happens to button when the button is clicked
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

    // a function for setting custom column width
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

    public Admin() {
        model = new DefaultTableModel();
        table = new JTable(model);
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));
        ImageIcon icon = new ImageIcon("auction2.jpg");

        table = new JTable(model);
        table.setFont(new Font("Serif", Font.BOLD, 15));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setRowHeight(40);
        table.setFillsViewportHeight(true);

        JLabel label1 = new JLabel();
        label1.setBounds(5, 5, 990, 80);
        label1.setBackground(Color.white);
        label1.setOpaque(true);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setText("AUCTION DAILY");
        label1.setFont(new Font(Font.SERIF, Font.BOLD, 35));
        label1.setIcon(icon);
        label1.setIconTextGap(50);

        JLabel label2 = new JLabel("WELCOME TO ADMINISTRATOR PORTAL!!!");
        label2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        label2.setBounds(5, 90, 990, 25);
        label2.setBorder(glassyBorder);
        label2.setBackground(new Color(35, 59, 97));
        label2.setForeground(Color.white);
        label2.setOpaque(true);
        label2.setHorizontalAlignment(JLabel.CENTER);

        JPanel panel1 = new JPanel();
        panel1.setBounds(5, 120, 250, 445);
        panel1.setBackground(new Color(167, 192, 232));
        panel1.setLayout(null);

        panel2 = new JPanel(new BorderLayout());
        panel2.setBounds(260, 120, 735, 445);
        panel2.setBackground(new Color(167, 192, 232));
        panel2.add((new JScrollPane(table)), BorderLayout.CENTER);

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

        // adding action listner to buttons
        for (int i = 0; i < 7; i++) {
            buttons.get(i).addActionListener(this);
        }
        for (int i = 0; i < 7; i++) {
            buttons.get(i).setBounds(0, i * 55, 250, 50);
            panel1.add(buttons.get(i));
        }

        // JFrame configurations
        super.setSize(1000, 600);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.getContentPane().setBackground(new Color(113, 163, 240));
        super.setResizable(false);
        super.setLayout(null);
        super.add(label1);
        super.add(label2);
        super.add(panel1);
        super.add(panel2);
        super.setVisible(true);
    }

    // a function for fetching the User table from the auction database
    public void fetchtable() throws Exception {
        String[] columnNames = { "User_ID", "User_Name", "Email", "Password", "Registration_Date", "Role" };
        model.setColumnIdentifiers(columnNames);
        String[][] data = new String[6][6];// temporary array for storing table result
        String url = "jdbc:mysql://localhost:3306/auction?useSSL=false";
        String uname = "root";
        String pass = "enterthepassword"; // change this
        String query = "select * from User";

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(url, uname, pass);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            int row = 0;
            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    data[row][i] = rs.getString(i + 1);
                }
                row++;
            }
        }

        model.setRowCount(data.length);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                table.setValueAt(data[i][j], i, j);
            }
        }
    }

    // a function implemented when a button is clicked, for the moment only for
    // button5;
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickButton = (JButton) e.getSource();
        if (clickButton == button5) {
            try {
                fetchtable();
                // colomun width with
                setColumnsWidth(table, 700, 10, 20, 25, 15, 20, 10);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Admin();
        // DONT FORGET TO POPULATE USER TABLE IN THE DATABASE
        // ALSO START THE MYSQL SERVER
    }
}
