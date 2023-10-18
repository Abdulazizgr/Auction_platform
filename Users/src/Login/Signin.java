package Login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.Border;
import Commonclasses.Button;
import Commonclasses.User;
import Commonclasses.UserDAO;
import User.UserHome;

//Sign in panel. It takes the Login frame as a parameter and disposes the frame if  the user logs in successfully.

public class Signin extends JPanel {
	private JLabel lblNewLabel;
	private Dimension screenSize;
	private JTextPane emil_text;
	private JPasswordField password;
	private JLabel label;
	private JButton btnNewButton, btnNewButton_1;
	public JButton btnNewUser;

	public Signin(Login login) {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setForeground(new Color(154, 205, 50));
		setBackground(new Color(167, 192, 232));
		setBounds(screenSize.width / 2 - 297, 171, 594, 411);
		setLayout(null);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images/user-icon.png"));
		lblNewLabel.setBounds(250, 12, 100, 100);
		add(lblNewLabel);

		emil_text = new JTextPane();
		emil_text.setBounds(92, 124, 414, 37);
		emil_text.setFont(new Font("Arial", Font.PLAIN, 25));
		add(emil_text);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("images/user-128.png"));
		lblNewLabel_1.setBounds(49, 124, 37, 37);
		add(lblNewLabel_1);

		JLabel Locklabel = new JLabel("");
		Locklabel.setIcon(new ImageIcon("images/lock-icon.png"));
		Locklabel.setBounds(49, 173, 37, 37);
		add(Locklabel);

		password = new JPasswordField();
		password.setBounds(92, 173, 414, 37);
		password.setFont(new Font("Arial", Font.PLAIN, 25));
		add(password);

		label = new JLabel("");
		label.setIcon(new ImageIcon(""));
		label.setBounds(49, 173, 37, 37);
		add(label);

		btnNewUser = Button.CustomButton("New User");
		btnNewUser.setBounds(390, 320, 180, 30);
		add(btnNewUser);

		// This is the log in button that handles user log in. It is a custom button
		// inherited from the Button class in the Common classes directory.
		// This button has an action listner class with action performer method. when
		// the user logs in it disposes the frame that the panel accepted as a
		// parameter. It also handels different conditions such as when the password is
		// not right or when the user tries to sign in with empty fields. It shows a
		// dialogue box if one of the above conditions occur.

		btnNewButton = Button.CustomButton("LOGIN");
		btnNewButton.setBounds(20, 320, 350, 30);
		btnNewButton.addActionListener(new ActionListener() {
			private String pass;
			private String Firstname;
			private UserDAO userdao;
			private User us_er;
			private UserHome UHome;

			public void actionPerformed(ActionEvent arg0) {
				pass = "";
				for (char i : password.getPassword()) {
					pass += ("" + i);
				}

				if (emil_text.getText().isEmpty() || pass.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter each field");
				} else {
					Firstname = emil_text.getText();
					emil_text.setText("");
					password.setText("");
					try {
						userdao = new UserDAO();
						us_er = userdao.get(Firstname);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					if (!(us_er == null) && us_er.getPassword().equals(pass)) {
						int IDstore = us_er.getUserID();
						login.dispose();
						try {
							UHome = new UserHome(IDstore);
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
						UHome.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Password Not Right");
					}
				}
			}
		});

		add(btnNewButton);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnNewButton.setBorder(emptyBorder);

	}
}
