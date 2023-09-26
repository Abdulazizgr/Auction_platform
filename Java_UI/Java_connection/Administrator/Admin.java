import javax.swing.*;
import javax.swing.border.Border;
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
    //DefaultTableModel model;

    Admin() {
        table = new JTable(2,9);
        panel3 = new JPanel();
        label2 = new JLabel();
        ImageIcon welcome = new ImageIcon(
                "/mnt/91953372-6625-495a-af25-22fae88bb951/Projects/Auction_platform/Java_UI/Java_connection/Administrator/Welcome.jpeg");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screensize = toolkit.getScreenSize();
        JPanel Panel1 = new JPanel(new BorderLayout());
        JLabel label1 = new JLabel();
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));
        ImageIcon icon = new ImageIcon("auction2.jpg");
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

        label1.setBounds(5, 5, 990, 80);
        label1.setBackground(Color.white);
        label1.setOpaque(true);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setText("AUCTION DAILY");
        label1.setFont(new Font(Font.SERIF, Font.BOLD, 35));

        label2 = new JLabel();
        label2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        label2.setBounds(5, 85, screensize.width - 10, 25);
        label2.setBorder(glassyBorder);
        label2.setBackground(new Color(35, 59, 97));
        label2.setForeground(Color.white);
        label2.setOpaque(true);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setPreferredSize(new Dimension(screensize.width - 10, 25));

        Panel1.setBounds(0, 0, screensize.width, 80);
        Panel1.setBackground(Color.white);
        Panel1.setPreferredSize(new Dimension(screensize.width, 80));

        panel2.setBackground(new Color(167, 192, 232));
        panel2.setLayout(null);
        panel2.setPreferredSize(new Dimension(300, 200));

        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setBackground(new Color(167, 192, 232));
        panel3.setPreferredSize(new Dimension(1060, 700));
        panel2.add((new JScrollPane(table)), BorderLayout.CENTER);

        Panel1.add(label2, BorderLayout.SOUTH);
        Panel1.add(label1, BorderLayout.NORTH);

        this.setLayout(new BorderLayout(5, 5));
        this.setMinimumSize(new Dimension(1000, 600));
        this.setSize(screensize.width, screensize.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(113, 163, 240));
        this.add(Panel1, BorderLayout.NORTH);
        Panel1.setPreferredSize(new Dimension(screensize.width, 80));
        Panel1.add(label2, BorderLayout.SOUTH);
        Panel1.add(label1, BorderLayout.NORTH);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton == button1) {
            System.out.println("clicked");
            panel3.add(contentLabel);
            panel3.revalidate();
            panel3.repaint();
            System.out.println("finished");
        } else if (clickedButton == button5) {
            UserDAO userDAO = new UserDAOImplementation();
            java.util.List<User> users = new ArrayList<>();
            try {
                users = userDAO.getAll();
            } catch (SQLException E) {
                System.out.println(E.getMessage());
            }
            System.out.println(users.get(0).getFirstName());
            table.setValueAt(users.get(0).getFirstName(), 1, 1);

        }
    }

    public static void main(String[] args) {
        new Admin();

    }
}
