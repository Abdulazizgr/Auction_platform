

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;

//import CommonClasses.*;

public class NewUserr extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public boolean n,flag=false;
	public String c;char t;
	public JTextPane textPane,textPane2,textPane3,textPane_1,textPane_3,textPane_4;
	public JLabel lblFillTheFollowing,lblCollegeName,lblCollegeName2,lblCollegeName3,lblPleaseEnterThe,lblPleaseEnterThe2,lblPleaseEnterThe3,lblPleaseEnterThe4,lblPleaseEnterThe6,lblPleaseEnterThe7,
	lblMobileNumber,lblNickName,lblPleaseFillValid,lblNewLabel;
	public JButton btnPutChoice,button;
	public NewUser thispanel;
	//public CustomerInfo customerinfo;
	public JLabel lblPassword;
	public DateFormat formatter;
	public NewUserr() {

		setBackground(new Color(0, 250, 154));
		setLayout(null);
        
		setBounds(280,170,715,550);
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		
		lblFillTheFollowing = new JLabel("New User Creation");
		lblFillTheFollowing.setBounds(241, 31, 263, 30);
		lblFillTheFollowing.setForeground(new Color(0, 0, 139));
		lblFillTheFollowing.setFont(new Font("Arial", Font.BOLD, 25));
		add(lblFillTheFollowing);
	
		lblCollegeName = new JLabel("User_Name");
		lblCollegeName.setForeground(new Color(0, 0, 139));
		lblCollegeName.setBounds(44, 93, 153, 25);
		lblCollegeName.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(lblCollegeName);
		
		textPane = new JTextPane();
		textPane.setBounds(215, 93, 439, 36);
		textPane.setFont(new Font("Arial", Font.PLAIN, 22));
		add(textPane);
		
		lblCollegeName2 = new JLabel("Address");
		lblCollegeName2.setForeground(new Color(0, 0, 139));
		lblCollegeName2.setBounds(44, 153, 153, 25);
		lblCollegeName2.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(lblCollegeName2);
		
		textPane2 = new JTextPane();
		textPane2.setBounds(215, 153, 439, 36);
		textPane2.setFont(new Font("Arial", Font.PLAIN, 22));
		add(textPane2);
		
		lblCollegeName3 = new JLabel("Date of Birth");
		lblCollegeName3.setForeground(new Color(0, 0, 139));
		lblCollegeName3.setBounds(44, 213, 153, 25);
		lblCollegeName3.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(lblCollegeName3);
		
		textPane3 = new JTextPane();
		textPane3.setBounds(215, 213, 439, 36);
		textPane3.setFont(new Font("Arial", Font.PLAIN, 22));
		add(textPane3);
		
		lblPleaseEnterThe = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe.setForeground(new Color(178, 34, 34));
		lblPleaseEnterThe.setVisible(false);
		lblPleaseEnterThe.setBounds(215, 128, 225, 15);
		add(lblPleaseEnterThe);
		
		lblPleaseEnterThe2 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe2.setForeground(new Color(178, 34, 34));
		lblPleaseEnterThe2.setVisible(false);
		lblPleaseEnterThe2.setBounds(215, 188, 225, 15);
		add(lblPleaseEnterThe2);
		
		lblPleaseEnterThe3 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe3.setForeground(new Color(178, 34, 34));
		lblPleaseEnterThe3.setVisible(false);
		lblPleaseEnterThe3.setBounds(215, 248, 225, 15);
		add(lblPleaseEnterThe3);
		
		lblPleaseEnterThe4 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe4.setForeground(new Color(178, 34, 34));
		lblPleaseEnterThe4.setVisible(false);
		lblPleaseEnterThe4.setBounds(215, 308, 225, 15);
		add(lblPleaseEnterThe4);
		
		lblPleaseEnterThe6 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe6.setForeground(new Color(178, 34, 34));
		lblPleaseEnterThe6.setVisible(false);
		lblPleaseEnterThe6.setBounds(215, 371, 225, 15);
		add(lblPleaseEnterThe6);
		
		lblPleaseEnterThe7 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe7.setForeground(new Color(178, 34, 34));
		lblPleaseEnterThe7.setVisible(false);
		lblPleaseEnterThe7.setBounds(215, 435, 225, 15);
		add(lblPleaseEnterThe7);
		
		textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Arial", Font.PLAIN, 22));
		textPane_1.setBounds(215, 273, 439, 36);
		add(textPane_1);
		
		lblMobileNumber = new JLabel("Mobile No");
		lblMobileNumber.setForeground(new Color(0, 0, 139));
		lblMobileNumber.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblMobileNumber.setBounds(44, 273, 153, 25);
		add(lblMobileNumber);
		
		textPane_3 = new JTextPane();
		textPane_3.setFont(new Font("Arial", Font.PLAIN, 22));
		textPane_3.setBounds(215, 335, 439, 36);
		add(textPane_3);
		
		lblNickName = new JLabel("Nick Name");
		lblNickName.setForeground(new Color(0, 0, 139));
		lblNickName.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNickName.setBounds(44, 333, 153, 25);
		add(lblNickName);
		
		textPane_4 = new JTextPane();
		textPane_4.setFont(new Font("Arial", Font.PLAIN, 22));
		textPane_4.setBounds(215, 398, 439, 36);
		add(textPane_4);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(0, 0, 139));
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblPassword.setBounds(44, 398, 153, 25);
		add(lblPassword);
		
		lblPleaseFillValid = new JLabel("Customer already Exists");
		lblPleaseFillValid.setForeground(new Color(178, 34, 34));
		lblPleaseFillValid.setBounds(275, 460, 198, 15);
		lblPleaseFillValid.setVisible(false);		
		//add(lblPleaseFillValid);
		
		btnPutChoice = new JButton("Submit");
		btnPutChoice.setBounds(265, 480, 198, 36);
		btnPutChoice.setFont(new Font("Arial",Font.BOLD, 20));
		btnPutChoice.setFocusable(true);
		btnPutChoice.setBackground(new Color(0, 128, 0));
		btnPutChoice.setForeground(Color.white);
		btnPutChoice.setFocusable(false);
        add(btnPutChoice);
		//thispanel=this;

			    textPane.setText("");
			    textPane2.setText("");
			    textPane3.setText("");
			    textPane_1.setText("");
			    textPane_3.setText("");
			    textPane_4.setText("");
			    if(!n)
			    {
			    	lblPleaseFillValid.setVisible(true);
					
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblPleaseFillValid.setVisible(false);
						}
					});
					t.start();
					t.setRepeats(false);
					return;
			    }
		
        

		add(btnPutChoice);
		
		button = new JButton("");
		button.setBackground(new Color(0, 128, 0));
		button.setFocusable(false);
		button.setIcon(new ImageIcon(""));
		button.setBounds(13, 16, 50, 37);
		add(button);
		
		lblNewLabel = new JLabel("( yyyy-mm-dd)");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setBounds(44, 234, 111, 15);
		add(lblNewLabel);
	}

    public static void main(String [] args){
        JFrame frame = new JFrame();
        //JFrame frame = new JFrame();
        NewUserr newuser = new NewUserr();
        frame.setSize(1300, 700);
        frame.add(newuser);
        frame.setVisible(true);

    }
}

