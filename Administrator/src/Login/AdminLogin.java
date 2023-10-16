package Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import Admin.AdminFrame;
import CommonClasses.*;

public class AdminLogin extends JFrame{

	private JPanel contentPane;
	public boolean n;
	public String Firstname,pass;
    public ImageIcon icon,icon2;
    public JLabel label3;
	public JPanel panel;
	public JLabel usericon,Usernaicon,locklabel,PleaseFillAll,PleaseFillValid;
	public JTextPane Firstnamefield;
	public JPasswordField password;
	public JButton LoginButton_1,LoginButton;
    public Dimension screenSize;
	private JLabel AdminPortal;
	private JLabel label_1;
	public AdminDAO adminDAO;
	public Admin admin;

	public AdminLogin(){
		
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setTitle("Login Portal");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(113, 163, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		AdminPortal = new JLabel("Admin Portal");
		AdminPortal.setBounds(screenSize.width/2 -70, 140, 174, 26);
		AdminPortal.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(AdminPortal);
		
		icon2=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/login.jpg");
		
		panel = new JPanel();
		panel.setForeground(new Color(154, 205, 50));
		panel.setBackground(new Color(167, 192, 232));
		panel.setBounds(screenSize.width/2 - 297, 171, 594, 411);
		panel.setLayout(null);
		contentPane.add(panel);
		
		label_1 = new JLabel("AUCTION DAILY LOGIN");
		label_1.setFont(new Font("Dialog", Font.BOLD, 25));
		label_1.setBounds(screenSize.width/2 -150, 83, 354, 60);
		contentPane.add(label_1);
		
		usericon = new JLabel("");
		usericon.setIcon(new ImageIcon("Images/user-icon.png"));
		usericon.setBounds(250, 12, 100, 100);
		panel.add(usericon);
		
		Firstnamefield = new JTextPane();
		Firstnamefield.setBounds(92, 124, 414, 37);
		Firstnamefield.setFont(new Font("Arial", Font.PLAIN, 25));
		panel.add(Firstnamefield);
		
		Usernaicon = new JLabel("");
		Usernaicon.setIcon(new ImageIcon("Images/user-128.png"));
		Usernaicon.setBounds(49, 124, 37, 37);
		panel.add(Usernaicon);
		
		password = new JPasswordField();
		password.setBounds(92, 173, 414, 37);
		password.setFont(new Font("Arial", Font.PLAIN, 25));
		panel.add(password);
		
		locklabel = new JLabel("");
		locklabel.setIcon(new ImageIcon("Images/lock-icon.png"));
		locklabel.setBounds(49, 173, 37, 37);
		panel.add(locklabel);
		
		label3=new JLabel(icon2);
		label3.setBounds(0, 0, screenSize.width, screenSize.height);
		contentPane.add(label3);
		
		PleaseFillAll = new JLabel("Please fill all the fields");
		PleaseFillAll.setForeground(new Color(178, 34, 34));
		PleaseFillAll.setBounds(223, 234, 161, 15);
		PleaseFillAll.setVisible(false);		
		panel.add(PleaseFillAll);
		
		PleaseFillValid = new JLabel("Please fill valid credentials");
		PleaseFillValid.setForeground(new Color(139, 0, 0));
		PleaseFillValid.setBounds(199, 234, 196, 25);
		PleaseFillValid.setBackground(Color.RED);
		PleaseFillValid.setOpaque(true);
		PleaseFillValid.setVisible(false);		
		panel.add(PleaseFillValid);

		LoginButton = Button.CustomButton("LOGIN");
		LoginButton.setBounds(92, 261, 414, 37);
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pass = "";
				for (char i : password.getPassword()) {
					pass+=(""+i);
				}
				
				if(Firstnamefield.getText().isEmpty() || pass.isEmpty())
				{
					   PleaseFillAll.setVisible(true);
					
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							PleaseFillAll.setVisible(false);
						}
					});
					t.start();
					t.setRepeats(false);
					return;
				}
				Firstname=Firstnamefield.getText();
				Firstnamefield.setText("");
				password.setText("");
				try 
				{
					adminDAO = new AdminDAO();
					admin = adminDAO.get(1);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				if(admin.getFirstName().equals(Firstname)&&admin.getPassword().equals(pass))
				{
					AdminFrame frame;
					try {
						frame = new AdminFrame();
						frame.setVisible(true);
						dispose();
						} catch (Exception e)
					    {
							e.printStackTrace();
						}
				}
				else
				{
					PleaseFillValid.setVisible(true);
					
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							PleaseFillValid.setVisible(false);
						}
					});
					t.start();
					t.setRepeats(false);
					return;
				}
			}
		});
		panel.add(LoginButton);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		LoginButton.setBorder(emptyBorder);
        setVisible(true);
	}
    public static void main(String[] args) {
        new AdminLogin();
    }
}