
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



public class Login extends JFrame {


	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public boolean n;
	public String username,pass;
	public Login thisframe;
	public static Container c;

	
	
	public static void main(String[] args) throws Exception
	{	
		Login login = new Login();
		login.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public JLabel label3;
	public JPanel panel;
	public Dimension screenSize;
	public ImageIcon icon,icon2;
	public JLabel label2,lblNewLabel,lblNewLabel_1,label,lblPleaseFillAll,lblPleaseFillValid,lblAdminPortal;

	public JTextPane textPane;
	public JPasswordField password;
	public JButton btnNewButton_1,btnNewUser,btnNewButton;
	
	public Login() throws UnknownHostException, IOException {
		
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setTitle("Login Portal");
		icon=new ImageIcon("");
		setIconImage(icon.getImage());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAdminPortal = new JLabel("Customer Portal");
		lblAdminPortal.setBounds(538, 133, 211, 26);
		lblAdminPortal.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(lblAdminPortal);
		lblAdminPortal.setForeground(Color.WHITE);
		
		label2=new JLabel("AUCTION BASKET LOGIN");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Dialog", Font.BOLD, 25));
		label2.setBounds(469,79,354,60);
		contentPane.add(label2);
		
		icon2=new ImageIcon("");
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 255, 127));
		panel.setBounds(338, 171, 594, 411);
		panel.setLayout(null);
		contentPane.add(panel);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(""));
		lblNewLabel.setBounds(250, 12, 100, 100);
		panel.add(lblNewLabel);
		
		textPane = new JTextPane();
		textPane.setBounds(92, 124, 414, 37);
		textPane.setFont(new Font("Arial", Font.PLAIN, 25));
		panel.add(textPane);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(""));
		lblNewLabel_1.setBounds(49, 124, 37, 37);
		panel.add(lblNewLabel_1);
		
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
		
		thisframe=this;
		btnNewUser = new JButton("New User");
		btnNewUser.setFont(new Font("Arial", Font.BOLD, 16));
		
		btnNewUser.setFocusable(false);
		btnNewUser.setBorder(null);
		btnNewUser.setBounds(413, 310, 94, 15);
		
		btnNewUser.setForeground(new Color(0, 100, 0));
		btnNewUser.setBackground(new Color(0, 255, 127));
		panel.add(btnNewUser);
		
		label3=new JLabel(icon2);
		label3.setBounds(0, 0, screenSize.width, screenSize.height);
		contentPane.add(label3);
		
		lblPleaseFillAll = new JLabel("Please fill all the fields");
		lblPleaseFillAll.setForeground(new Color(178, 34, 34));
		lblPleaseFillAll.setBounds(223, 234, 161, 15);
		lblPleaseFillAll.setVisible(false);	
		panel.add(lblPleaseFillAll);
		
		lblPleaseFillValid = new JLabel("Please fill valid credentials");
		lblPleaseFillValid.setForeground(new Color(178, 34, 34));
		lblPleaseFillValid.setBounds(199, 234, 196, 15);
		lblPleaseFillValid.setVisible(false);		
		panel.add(lblPleaseFillValid);
		
		btnNewButton = new JButton("LOGIN");
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(92, 261, 414, 37);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 17));
		btnNewButton.setFocusable(false);
		
		
		panel.add(btnNewButton);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnNewButton.setBorder(emptyBorder);
	}
}