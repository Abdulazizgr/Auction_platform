package Login;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Commonclasses.*;
import User.*;

public class Login extends JFrame {

	int IDstore;
	private JPanel contentPane;
	public boolean n;
	public String Firstname, pass;
	public Login thisframe;
	public static Container c;
	public UserDAO userdao;
	public User us_er;

	public UserHome UHome;

	public JLabel label3;
	public JPanel panel;
	public Dimension screenSize;
	public ImageIcon icon, icon2;
	public JLabel Locklabel, label2, lblNewLabel, lblNewLabel_1, label, lblPleaseFillAll, lblPleaseFillValid,
			lblAdminPortal;

	public JTextPane emil_text;
	public JPasswordField password;
	public JButton btnNewButton_1, btnNewButton, btnNewUser;
	public Signin signin;
	private Signup signup;

	public Login(){
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setTitle("Login Portal");
		icon = new ImageIcon("images/user-128.png");
		setIconImage(icon.getImage());
		signin = new Signin(this);
		signup = new Signup();
		signup.setVisible(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(113, 163, 240));
		add(contentPane);
		contentPane.setLayout(null);

		lblAdminPortal = new JLabel("Customer Portal");
		lblAdminPortal.setBounds(screenSize.width / 2 - 70, 140, 200, 26);
		lblAdminPortal.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(lblAdminPortal);

		label2 = new JLabel("AUCTION DAILY LOGIN");
		label2.setFont(new Font("Dialog", Font.BOLD, 25));
		label2.setBounds(screenSize.width / 2 - 150, 83, 354, 60);
		contentPane.add(signin);
		contentPane.add(signup);
		contentPane.add(label2);

		signin.btnNewUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg) {
				signin.setVisible(false);
				signup.setVisible(true);
			}
		});

		 signup.next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
				System.out.println(signup.get_pass);
                if (!(signup.get_pass.getText().equals(signup.getre_enter.getText()))) {
                    signup.get_pass.setText("");
                    signup.getre_enter.setText("");
                    JOptionPane.showMessageDialog(null, "Confirm Again");
                } else {
                    User to_add = new User(signup.getfirst.getText(), signup.getlast.getText(), signup.get_email.getText(),
                            signup.get_pass.getText());
                    UserDAO in = new UserDAO();
                    try {
                        in.insert(to_add);
                        signup.getfirst.setText("");
                        signup.getlast.setText("");
                        signup.get_email.setText("");
                        signup.get_pass.setText("");
                        signup.getre_enter.setText("");
                        signup.setVisible(false);
                        signin.setVisible(true);

                    } catch (SQLException e) {
                        String message = e.getMessage();
                        JOptionPane.showMessageDialog(null, message);
                        signup.getfirst.setText("");
                        signup.getlast.setText("");
                        signup.get_email.setText("");
                        signup.get_pass.setText("");
                        signup.getre_enter.setText("");
                    }

                }
            }
        });

		signup.back.addActionListener(new ActionListener() {

	@Override
			public void actionPerformed(ActionEvent e) {
				 signup.setVisible(false);
                 signin.setVisible(true);
			}
			
		});
			}

	public static void main(String[] args) throws Exception {
		Login login = new Login();
		login.setVisible(true);
	}
}