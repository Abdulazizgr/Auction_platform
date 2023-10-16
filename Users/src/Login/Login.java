package Login;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.Border;
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

	public static void main(String[] args) throws Exception {
		Login login = new Login();
		login.setVisible(true);
	}

	public JLabel label3;
	public JPanel panel;
	public Dimension screenSize;
	public ImageIcon icon, icon2;
	public JLabel Locklabel, label2, lblNewLabel, lblNewLabel_1, label, lblPleaseFillAll, lblPleaseFillValid,
			lblAdminPortal;

	public JTextPane emil_text;
	public JPasswordField password;
	public JButton btnNewButton_1, btnNewButton, btnNewUser;

	public Login() throws UnknownHostException, IOException {

		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setTitle("Login Portal");
		icon = new ImageIcon("images/user-128.png");
		setIconImage(icon.getImage());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(113, 163, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblAdminPortal = new JLabel("Customer Portal");
		lblAdminPortal.setBounds(screenSize.width / 2 - 70, 140, 200, 26);
		lblAdminPortal.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(lblAdminPortal);

		label2 = new JLabel("AUCTION BASKET LOGIN");
		label2.setFont(new Font("Dialog", Font.BOLD, 25));
		label2.setBounds(screenSize.width / 2 - 150, 83, 354, 60);
		contentPane.add(label2);

		icon2 = new ImageIcon("images/Login2.png");

		panel = new JPanel();
		panel.setForeground(new Color(154, 205, 50));
		panel.setBackground(new Color(167, 192, 232));
		panel.setBounds(screenSize.width / 2 - 297, 171, 594, 411);
		panel.setLayout(null);
		contentPane.add(panel);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images/user-icon.png"));
		lblNewLabel.setBounds(250, 12, 100, 100);
		panel.add(lblNewLabel);

		emil_text = new JTextPane();
		emil_text.setBounds(92, 124, 414, 37);
		emil_text.setFont(new Font("Arial", Font.PLAIN, 25));
		panel.add(emil_text);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("images/user-128.png"));
		lblNewLabel_1.setBounds(49, 124, 37, 37);
		panel.add(lblNewLabel_1);

		Locklabel = new JLabel("");
		Locklabel.setIcon(new ImageIcon("images/lock-icon.png"));
		Locklabel.setBounds(49, 173, 37, 37);
		panel.add(Locklabel);

		password = new JPasswordField();
		password.setBounds(92, 173, 414, 37);
		password.setFont(new Font("Arial", Font.PLAIN, 25));
		panel.add(password);

		label = new JLabel("");
		label.setIcon(new ImageIcon(""));
		label.setBounds(49, 173, 37, 37);
		panel.add(label);

		btnNewButton_1 = new JButton("Forgot Password?");
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 16));

		btnNewButton_1.setForeground(new Color(0, 100, 0));
		btnNewButton_1.setBackground(new Color(0, 255, 127));
		btnNewButton_1.setFocusable(false);
		panel.add(btnNewButton_1);

		thisframe = this;
		btnNewUser = Button.CustomButton("New User");
		btnNewUser.setBounds(410, 320, 180, 50);
		panel.add(btnNewUser);

		label3 = new JLabel(icon2);
		label3.setBounds(0, 0, screenSize.width, screenSize.height);
		contentPane.add(label3);

		lblPleaseFillAll = new JLabel("Please fill all the fields");
		lblPleaseFillAll.setForeground(new Color(178, 34, 34));
		lblPleaseFillAll.setBounds(223, 234, 161, 15);
		lblPleaseFillAll.setVisible(false);
		panel.add(lblPleaseFillAll);

		lblPleaseFillValid = new JLabel("Please fill valid credentials");
		lblPleaseFillValid.setForeground(new Color(139, 0, 0));
		lblPleaseFillValid.setBounds(199, 234, 196, 15);
		lblPleaseFillValid.setBackground(Color.RED);
		lblPleaseFillValid.setOpaque(true);
		lblPleaseFillValid.setVisible(false);
		panel.add(lblPleaseFillValid);

		btnNewButton = Button.CustomButton("LOGIN");
		btnNewButton.setBounds(100, 255, 350, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pass = "";
				for (char i : password.getPassword()) {
					pass += ("" + i);
				}

				if (emil_text.getText().isEmpty() || pass.isEmpty()) {
					lblPleaseFillAll.setVisible(true);
					Timer t = new Timer(2000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							lblPleaseFillAll.setVisible(false);
						}
					});
					t.start();
					t.setRepeats(false);
					return;
				}

				Firstname = emil_text.getText();
				emil_text.setText("");
				password.setText("");
				try {
					userdao = new UserDAO();
					us_er = userdao.get(Firstname);
				} catch (Exception e) {

				}
				if (!(us_er == null)) {
					int IDstore = us_er.getUserID();
					UHome = new UserHome(IDstore);
					UHome.setVisible(true);
					dispose();
				}
			}
		});

		panel.add(btnNewButton);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnNewButton.setBorder(emptyBorder);
	}
}