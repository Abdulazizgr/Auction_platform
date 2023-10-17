package Login;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.TextField;

import Commonclasses.*;

public class Signup extends JPanel {
    private JPanel content_pane;
    public boolean N;
    public String First_name, Last_name, Email, password;
    public JLabel firstlb, lastlb, emaillb, passwordlb, re_enter;
    public JTextField getfirst, getlast, get_email;
    public TextField get_pass;
    public JTextField getre_enter;
    public JButton next, sign_up;
    public Dimension screen_Size;
    public JPanel Bank_info;

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
        getfirst.setBounds(150, 50, 200, 30);
        add(getfirst);

        lastlb = new JLabel("Last Name: ");
        lastlb.setBounds(50, 100, 100, 30);
        add(lastlb);
        getlast = new JTextField();
        getlast.setBounds(150, 100, 200, 30);
        add(getlast);

        emaillb = new JLabel("Email: ");
        emaillb.setBounds(50, 150, 100, 30);
        add(emaillb);
        get_email = new JTextField();
        get_email.setBounds(150, 150, 200, 30);
        add(get_email);

        passwordlb = new JLabel("Password: ");
        passwordlb.setBounds(50, 200, 100, 30);
        add(passwordlb);
        get_pass = new TextField();
        get_pass.setBounds(150, 200, 200, 30);
        add(get_pass);

        re_enter = new JLabel("Confirim:");
        re_enter.setBounds(50, 250, 100, 30);
        add(re_enter);
        getre_enter = new JTextField();
        getre_enter.setBounds(150, 250, 200, 30);
        add(getre_enter);

        next = Button.CustomButton("Sign Up");
        next.setBounds(200, 300, 300, 50);
        add(next);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (!(get_pass.getText().equals(getre_enter.getText()))) {
                    get_pass.setText("");
                    getre_enter.setText("");
                    JOptionPane.showMessageDialog(null, "Confirm Again");
                } else {
                    User to_add = new User(getfirst.getText(), getlast.getText(), get_email.getText(),
                            get_pass.getText());
                    UserDAO in = new UserDAO();
                    try {
                        in.insert(to_add);
                        getfirst.setText("");
                        getlast.setText("");
                        get_email.setText("");
                        get_pass.setText("");
                        getre_enter.setText("");
                    } catch (SQLException e) {
                        String message = e.getMessage();
                        JOptionPane.showMessageDialog(null, message);
                        getfirst.setText("");
                        getlast.setText("");
                        get_email.setText("");
                        get_pass.setText("");
                        getre_enter.setText("");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "please enter the fields again");
                    }
                    try {
                        Login log = new Login();
                        log.setVisible(true);
                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        });
        setVisible(true);
    }

}
