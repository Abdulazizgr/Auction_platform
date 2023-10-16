package User;

import javax.swing.*;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserHome extends JFrame {

    JButton butt_homee, butt_homepersonalDet, butt_chase, butt_wItems, butt_rder, butt_dItems, butt_Out, butt_add_item;
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
    public Cantpurchase cant_purchase;
    public AddItem add_item;

    public UserHome(int ID) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screensize = toolkit.getScreenSize();
        ImageIcon homepage = new ImageIcon("images/Welcome.jpeg");
        ImageIcon icon = new ImageIcon("images/auction2.jpg");
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
        try {
            per_detail = new PersonalDetail(ID);
        } catch (Exception e) {

            e.printStackTrace();
        }
        try {
            pur_chase = new Purchase();
        } catch (Exception e) {

            e.printStackTrace();
        }
        try {
            show_items = new ShowItems();
        } catch (SQLException e) {

            e.printStackTrace();
        }

        try {
            cant_purchase = new Cantpurchase();
        } catch (Exception e) {

            e.printStackTrace();
        }
        try {
            add_item = new AddItem(ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        content_panel.add(cant_purchase);
        cant_purchase.setVisible(false);
        content_panel.add(add_item);
        add_item.setVisible(false);

        firstlabel = new JLabel();
        JLabel label_1 = new JLabel();
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));

        // showItems, myOrder, soldItems, logOut;
        butt_homee = Custom_Button("Home");
        butt_homepersonalDet = Custom_Button("Personal Detail");
        butt_chase = Custom_Button("Purchase");
        butt_wItems = Custom_Button("Show Items");
        butt_rder = Custom_Button("My Order");
        butt_dItems = Custom_Button("Sold Items");
        butt_Out = Custom_Button("Log Out");
        butt_add_item = Custom_Button("Add Item");

        butt_ons = new ArrayList<>(7);
        butt_ons.add(butt_homee);
        butt_homee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home_Panel.setVisible(true);
                per_detail.setVisible(false);
                pur_chase.setVisible(false);
                show_items.setVisible(false);
                my_order.setVisible(false);
                sold_items.setVisible(false);
                add_item.setVisible(false);
            }
        });

        butt_ons.add(butt_homepersonalDet);
        butt_homepersonalDet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home_Panel.setVisible(false);
                per_detail.setVisible(true);
                pur_chase.setVisible(false);
                show_items.setVisible(false);
                my_order.setVisible(false);
                sold_items.setVisible(false);
                cant_purchase.setVisible(false);
                add_item.setVisible(false);
            }
        });

        butt_ons.add(butt_chase);
        butt_chase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home_Panel.setVisible(false);
                per_detail.setVisible(false);
                pur_chase.setVisible(false);
                show_items.setVisible(false);
                my_order.setVisible(false);
                sold_items.setVisible(false);
                cant_purchase.setVisible(true);
                add_item.setVisible(false);
            }
        });
        butt_ons.add(butt_wItems);
        butt_wItems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home_Panel.setVisible(false);
                per_detail.setVisible(false);
                show_items.setVisible(true);
                my_order.setVisible(false);
                sold_items.setVisible(false);
                cant_purchase.setVisible(false);
                add_item.setVisible(false);
            }
        });

        butt_ons.add(butt_rder);
        butt_rder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home_Panel.setVisible(false);
                per_detail.setVisible(false);
                pur_chase.setVisible(false);
                show_items.setVisible(false);
                my_order.setVisible(true);
                sold_items.setVisible(false);
                cant_purchase.setVisible(false);
                add_item.setVisible(false);
            }
        });

        butt_ons.add(butt_dItems);
        butt_dItems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home_Panel.setVisible(false);
                per_detail.setVisible(false);
                pur_chase.setVisible(false);
                show_items.setVisible(false);
                my_order.setVisible(true);
                sold_items.setVisible(false);
                cant_purchase.setVisible(false);
                add_item.setVisible(false);
            }
        });

        butt_ons.add(butt_add_item);
        butt_add_item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home_Panel.setVisible(false);
                per_detail.setVisible(false);
                pur_chase.setVisible(false);
                show_items.setVisible(false);
                my_order.setVisible(false);
                sold_items.setVisible(false);
                cant_purchase.setVisible(false);
                add_item.setVisible(true);
            }
        });
        butt_ons.add(butt_Out);
        butt_Out.addActionListener(new ActionListener() {
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

        for (int i = 0; i < 8; i++) {
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
        add(control_Panel, BorderLayout.WEST);
        add(content_panel, BorderLayout.EAST);

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
}
