import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminFrame extends JFrame {
    public JButton butt_home, butt_add_item, butt_show_item, butt_start_auction, butt_cust, butt_sold_items,
            butt_logout;
    public ArrayList<JButton> buttons;
    public JPanel home;
    private JPanel contentpanel;
    public JLabel label2;
    public JLabel labelh;
    public ShowUsers users;
    public ShowItems items;
    public SoldItems slitems;
    public AddItem additem;
    public StartAuction start;

    AdminFrame() throws SQLException {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screensize = toolkit.getScreenSize();
        ImageIcon welcome = new ImageIcon("Administrator/AdminImages/Welcome.jpeg");
        ImageIcon icon = new ImageIcon("Administrator/AdminImages/auction2.jpg");
        JPanel titlebar = new JPanel(new BorderLayout());

        titlebar.setBounds(0, 0, screensize.width, 80);
        titlebar.setBackground(Color.white);
        titlebar.setPreferredSize(new Dimension(screensize.width, 100));

        labelh = new JLabel();
        labelh.setOpaque(true);
        labelh.setBounds(50, 30, 950, 500);
        labelh.setHorizontalAlignment(JLabel.CENTER);
        labelh.setVerticalAlignment(JLabel.CENTER);
        labelh.setIcon(welcome);

        contentpanel = new JPanel();
        contentpanel.setLayout(null);
        contentpanel.setBackground(new Color(167, 192, 232));
        contentpanel.setPreferredSize(new Dimension(1060, 500));

        JPanel controlpanel = new JPanel();
        controlpanel.setBackground(new Color(167, 192, 232));
        controlpanel.setLayout(null);
        controlpanel.setPreferredSize(new Dimension(300, 200));

        users = new ShowUsers();
        items = new ShowItems();
        slitems = new SoldItems();
        additem = new AddItem();
        start = new StartAuction();

        home = new JPanel();
        home.setBounds(25, 5, 1000, 600);
        home.add(labelh);
        contentpanel.add(home);
        home.setVisible(true);
        contentpanel.add(additem);
        additem.setVisible(false);
        contentpanel.add(items);
        items.setVisible(false);
        contentpanel.add(start);
        start.setVisible(false);
        contentpanel.add(users);
        users.setVisible(false);
        contentpanel.add(slitems);
        slitems.setVisible(false);

        label2 = new JLabel();
        JLabel label1 = new JLabel();
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));

        butt_home = CustomButton("Home");
        butt_add_item = CustomButton("Add Item");
        butt_show_item = CustomButton("Show Item");
        butt_start_auction = CustomButton("Start Auction");
        butt_cust = CustomButton("Show Customers");
        butt_sold_items = CustomButton("Show Sold Items");
        butt_logout = CustomButton("Logout");

        buttons = new ArrayList<>(7);
        buttons.add(butt_home);
        butt_home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home.setVisible(true);
                additem.setVisible(false);
                start.setVisible(false);
                users.setVisible(false);
                slitems.setVisible(false);
                items.setVisible(false);
            }
        });

        buttons.add(butt_add_item);
        butt_add_item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home.setVisible(false);
                additem.setVisible(true);
                start.setVisible(false);
                users.setVisible(false);
                slitems.setVisible(false);
                items.setVisible(false);
            }
        });

        buttons.add(butt_show_item);
        butt_show_item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home.setVisible(false);
                additem.setVisible(false);
                start.setVisible(false);
                users.setVisible(false);
                slitems.setVisible(false);
                items.setVisible(true);
            }
        });

        buttons.add(butt_start_auction);
        butt_start_auction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home.setVisible(false);
                additem.setVisible(false);
                start.setVisible(true);
                users.setVisible(false);
                slitems.setVisible(false);
                items.setVisible(false);
            }
        });

        buttons.add(butt_cust);
        butt_cust.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home.setVisible(false);
                additem.setVisible(false);
                start.setVisible(false);
                users.setVisible(true);
                slitems.setVisible(false);
                items.setVisible(false);
            }
        });

        buttons.add(butt_sold_items);
        butt_sold_items.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home.setVisible(false);
                additem.setVisible(false);
                start.setVisible(false);
                users.setVisible(false);
                slitems.setVisible(true);
                items.setVisible(false);
            }
        });

        buttons.add(butt_logout);
        butt_logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AdminLogin login;
                try {
                    login = new AdminLogin();
                    login.setVisible(true);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                dispose();
            }
        });

        for (int i = 0; i < 7; i++) {
            buttons.get(i).setBounds(0, i * 55, 300, 50);
            controlpanel.add(buttons.get(i));
        }

        label1.setSize(1000, 200);
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

        titlebar.add(label2, BorderLayout.SOUTH);
        titlebar.add(label1, BorderLayout.NORTH);

        setLayout(new BorderLayout(5, 5));
        setMinimumSize(new Dimension(1000, 600));
        setSize(screensize.width, screensize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(113, 163, 240));
        add(titlebar, BorderLayout.NORTH);
        titlebar.add(label2, BorderLayout.SOUTH);
        titlebar.add(label1, BorderLayout.WEST);
        label2.setPreferredSize(new Dimension(screensize.width - 10, 25));
        add(controlpanel, BorderLayout.WEST);
        add(contentpanel, BorderLayout.EAST);
        setVisible(true);
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

    public static void main(String[] args) throws SQLException {
        new AdminFrame();
    }
}
