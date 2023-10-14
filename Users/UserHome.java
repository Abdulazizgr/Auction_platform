package Users;

import javax.swing.*;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.*;
import java.awt.*;
import java.awt.Font.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserHome extends JFrame {
    JButton home, personalDet, purchase, showItems, myOrder, soldItems, logOut;
    public MyOrder my_order;
    public PersonalDetail per_detail;
    public Purchase pur_chase;
    public ShowItems show_items;
    public soldItems sold_items;
    public ArrayList<JButton> butt_ons;
    public JPanel home_Panel;
    private JPanel content_panel;
    public JLabel firstlabel;
    public JLabel flabel;

    UserHome() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screensize = toolkit.getScreenSize();
        ImageIcon homepage = new ImageIcon("Users/userImages/welcome.jpg");
        ImageIcon icon = new ImageIcon("Users/userImages/law.png");
        JPanel title_bar = new JPanel(new BorderLayout());

        title_bar.setBounds(0, 0, screensize.width, 80);
        title_bar.setBackground(Color.white);
        title_bar.setPreferredSize(new Dimension(screensize.width, 100));

        flabel = new JLabel();
        flabel.setOpaque(true);
        flabel.setBounds(50, 30, 950, 500);
        flabel.setHorizontalAlignment(JLabel.CENTER);
        flabel.setVerticalAlignment(JLabel.CENTER);
        flabel.setIcon(homepage);

        content_panel = new JPanel();
        content_panel.setLayout(null);
        content_panel.setBackground(new Color(167, 192, 232));
        content_panel.setPreferredSize(new Dimension(1060, 500));

        JPanel control_Panel = new JPanel();
        control_Panel.setBackground(new Color(167, 192, 232));
        control_Panel.setLayout(null);
        control_Panel.setPreferredSize(new Dimension(300, 200));

        my_order = new MyOrder();
        per_detail = new PersonalDetail();
        pur_chase = new Purchase();
        show_items = new ShowItems();
        sold_items = new soldItems();

        home_Panel = new JPanel();
        home_Panel.setBounds(25, 5, 1000, 600);
        home_Panel.add(flabel);
        content_panel.add(home_Panel);
        home_Panel.setVisible(true);
        content_panel.add(my_order);
        my_order.setVisible(false);
        content_panel.add(per_detail);
        per_detail.setVisible(false);
        content_panel.add(pur_chase);
        pur_chase.setVisible(false);
        content_panel.add(show_items);
        show_items.setVisible(false);
        content_panel.add(sold_items);
        sold_items.setVisible(false);

        firstlabel = new JLabel();
        JLabel label_1 = new JLabel();
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));

        // showItems, myOrder, soldItems, logOut;
        home = Custom_Button("Home");
        personalDet = Custom_Button("Personal Detail");
        purchase = Custom_Button("Purchase");
        showItems = Custom_Button("Show Items");
        myOrder = Custom_Button("My Order");
        soldItems = Custom_Button("Sold Items");
        logOut = Custom_Button("Log Out");

        butt_ons = new ArrayList<>(7);
        butt_ons.add(home);
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home.setVisible(true);
                personalDet.setVisible(true);
                purchase.setVisible(true);
                showItems.setVisible(true);
                myOrder.setVisible(true);
                soldItems.setVisible(true);
                logOut.setVisible(true);
            }
        });

        butt_ons.add(personalDet);
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home.setVisible(false);
                personalDet.setVisible(true);
                purchase.setVisible(false);
                showItems.setVisible(false);
                myOrder.setVisible(false);
                soldItems.setVisible(false);
                logOut.setVisible(false);
            }
        });

        butt_ons.add(purchase);
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home.setVisible(false);
                personalDet.setVisible(false);
                purchase.setVisible(true);
                showItems.setVisible(false);
                myOrder.setVisible(false);
                soldItems.setVisible(false);
                logOut.setVisible(false);
            }
        });
        butt_ons.add(showItems);
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home.setVisible(false);
                personalDet.setVisible(false);
                purchase.setVisible(false);
                showItems.setVisible(true);
                myOrder.setVisible(false);
                soldItems.setVisible(false);
                logOut.setVisible(false);
            }
        });

        butt_ons.add(myOrder);
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home.setVisible(false);
                personalDet.setVisible(false);
                purchase.setVisible(false);
                showItems.setVisible(false);
                myOrder.setVisible(true);
                soldItems.setVisible(false);
                logOut.setVisible(false);
            }
        });

        butt_ons.add(soldItems);
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home.setVisible(false);
                personalDet.setVisible(false);
                purchase.setVisible(false);
                showItems.setVisible(false);
                myOrder.setVisible(true);
                soldItems.setVisible(false);
                logOut.setVisible(false);
            }
        });

        butt_ons.add(logOut);
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                // UserLogin Login ;
                // try {
                // login = new AdminLogin();
                // login.setVisible(true);
                // } catch (Exception e2) {
                // e2.printStackTrace();
                // }
                // dispose();
            }
        });

        for (int i = 0; i < 7; i++) {
            butt_ons.get(i).setBounds(0, i * 55, 300, 50);
            control_Panel.add(butt_ons.get(i));
        }

        label_1.setSize(1000, 200);
        label_1.setBackground(Color.white);
        label_1.setOpaque(true);
        label_1.setHorizontalAlignment(JLabel.CENTER);
        label_1.setText("AUCTION DAILY");
        label_1.setIcon(icon);
        label_1.setHorizontalTextPosition(JLabel.RIGHT);
        label_1.setVerticalTextPosition(JLabel.CENTER);
        label_1.setIconTextGap(100);
        label_1.setFont(new Font(Font.SERIF, Font.BOLD, 40));

        firstlabel = new JLabel();
        firstlabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        firstlabel.setText("WELCOME TO THE USER PORTAL");
        firstlabel.setBounds(5, 85, screensize.width - 10, 25);
        firstlabel.setBorder(glassyBorder);
        firstlabel.setBackground(new Color(35, 59, 97));
        firstlabel.setForeground(Color.white);
        firstlabel.setOpaque(true);
        firstlabel.setHorizontalAlignment(JLabel.CENTER);
        firstlabel.setPreferredSize(new Dimension(screensize.width - 10, 25));

        title_bar.add(firstlabel, BorderLayout.SOUTH);
        title_bar.add(label_1, BorderLayout.NORTH);

        setLayout(new BorderLayout(5, 5));
        setMinimumSize(new Dimension(1000, 600));
        setSize(screensize.width, screensize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(113, 163, 240));
        add(title_bar, BorderLayout.NORTH);
        title_bar.add(firstlabel, BorderLayout.SOUTH);
        title_bar.add(label_1, BorderLayout.WEST);
        firstlabel.setPreferredSize(new Dimension(screensize.width - 10, 25));
        add(control_Panel, BorderLayout.EAST);
        add(control_Panel, BorderLayout.WEST);
        setVisible(true);

    }

    public JButton Custom_Button(String text) {
        JButton cbuttons = new JButton(text);
        cbuttons.setFont(new Font("Arial", Font.BOLD, 25));
        cbuttons.setBackground(new Color(35, 59, 97));
        cbuttons.setForeground(Color.white);
        cbuttons.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        cbuttons.setFocusable(false);
        cbuttons.getModel().addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    cbuttons.setBackground(new Color(60, 123, 240));
                } else {
                    cbuttons.setBackground(new Color(35, 59, 97));
                }
            }
        });
        return cbuttons;
    }

    public static void main(String args[]) {
        new UserHome();

    }

}
