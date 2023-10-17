package Login;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.TextField;

import Commonclasses.*;

public class Signup extends JPanel {
    public boolean N;
    public String First_name, Last_name, Email, password;
    public JLabel firstlb, lastlb, emaillb, passwordlb, re_enter;
    public JTextField getfirst, getlast, get_email;
    public TextField get_pass;
    public JTextField getre_enter;
    public JButton next, sign_up,back;
    public Dimension screen_Size;
    public JPanel Bank_info;
    public Signin signin;

    Signup() {
        screen_Size = Toolkit.getDefaultToolkit().getScreenSize();
        setForeground(new Color(154, 205, 50));
        setBackground(new Color(167, 192, 232));
        setBounds(screen_Size.width / 2 - 297, 171, 594, 411);
        setLayout(null);

        firstlb = new JLabel("First Name:");
        firstlb.setBounds(50, 50, 100, 30);
        add(firstlb);
        getfirst = new JTextField();
        getfirst.setBounds(150, 50, 250, 30);
        add(getfirst);

        lastlb = new JLabel("Last Name: ");
        lastlb.setBounds(50, 100, 100, 30);
        add(lastlb);
        getlast = new JTextField();
        getlast.setBounds(150, 100, 250, 30);
        add(getlast);

        emaillb = new JLabel("Email: ");
        emaillb.setBounds(50, 150, 100, 30);
        add(emaillb);
        get_email = new JTextField();
        get_email.setBounds(150, 150, 250, 30);
        add(get_email);

        passwordlb = new JLabel("Password: ");
        passwordlb.setBounds(50, 200, 100, 30);
        add(passwordlb);
        get_pass = new TextField();
        get_pass.setBounds(150, 200, 250, 30);
        add(get_pass);

        re_enter = new JLabel("Confirim:");
        re_enter.setBounds(50, 250, 100, 30);
        add(re_enter);
        getre_enter = new JTextField();
        getre_enter.setBounds(150, 250, 250, 30);
        add(getre_enter);

        next = Button.CustomButton("Sign Up");
        next.setBounds(20, 320, 350, 30);
        add(next);
        back = Button.CustomButton("Go Back");
        back.setBounds(390, 320, 180, 30);
        add(back);
        setVisible(true);
    }

}
