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

// This is the log in frame with the main method where the program begins. it works by changing the visibility of each panel on and off.
// It mainly has two  panels with in it. the sign in and the sign up panel. it changes the visibility of each depending on the button pressed. 
// Both panels are have their visibility turned off.

public class Login extends JFrame {

	int IDstore; // this is where the program stores the UserID of the user that signs in. it
					// passes this number as an argument for other classes to identify the UserID of
					// each person.
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
			lblUserPortal;

	public JTextPane emil_text;
	public JPasswordField password;
	public JButton btnNewButton_1, btnNewButton, btnNewUser;
	public Signin signin; // sign in object
	private Signup signup; // sign up object

	public Login() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setTitle("Login Portal");
		icon = new ImageIcon("images/user-128.png");
		setIconImage(icon.getImage());
		signin = new Signin(this); // instantiating the sign in class (panel)
		signup = new Signup(); // instantiating the sign up class (panel)
		signup.setVisible(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(113, 163, 240));
		add(contentPane);
		contentPane.setLayout(null);

		lblUserPortal = new JLabel("Customer Portal");
		lblUserPortal.setBounds(screenSize.width / 2 - 70, 140, 200, 26);
		lblUserPortal.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(lblUserPortal);

		label2 = new JLabel("AUCTION DAILY LOGIN");
		label2.setFont(new Font("Dialog", Font.BOLD, 25));
		label2.setBounds(screenSize.width / 2 - 150, 83, 354, 60);
		contentPane.add(signin);
		contentPane.add(signup);
		contentPane.add(label2);

		// this is the button that controls the buttons action. it sets the visibility
		// of signup panel to false the sign in panel to true.
		signin.btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				signin.setVisible(false);
				signup.setVisible(true);
			}
		});

		// this is the button that controls the sing up buttons action. it adds the
		// users input to the database using the UserrDAO and the User class
		// inherited from the common classes package. it handles different erros where
		// if the passwords are not the same or some fields are left empty
		signup.next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.out.println(signup.get_pass);
				if (!(signup.get_pass.getText().equals(signup.getre_enter.getText()))) {
					signup.get_pass.setText("");
					signup.getre_enter.setText("");
					JOptionPane.showMessageDialog(null, "Confirm Again");
				} else {
					User to_add = new User(signup.getfirst.getText(), signup.getlast.getText(),
							signup.get_email.getText(),
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

		// this is a button in the sign up panel that makes it return back to the sign
		// in page. It works by setting the visibility of the sign up panel to false and
		// the log in panel to true
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