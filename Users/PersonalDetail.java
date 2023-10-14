package Users;

import javax.swing.*;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import DAO.*;
import DAO.User;
import DAO.UserDAO;

public class PersonalDetail extends JPanel {
    public JLabel Intro, fname, lname, e_mail, reg_date, fanswer, lanswer, eanswer, ranswer;
    public String ffname, llname, ee_mail, rreg_date;
    public JPanel home_personal_content;
    public UserDAO udao;
    public User u;

    PersonalDetail(int ID) throws Exception {

        home_personal_content = new JPanel();
        home_personal_content.setLayout(null);
        home_personal_content.setBackground(new Color(167, 192, 232));
        home_personal_content.setPreferredSize(new Dimension(1060, 500));

        u = null;
        udao = new UserDAOImplementation();
        u = udao.get(ID);
        ffname = u.getFirstName();
        llname = u.getLastName();
        ee_mail = u.getEmail();
        rreg_date = u.getRegistrationDate();

        Intro = new JLabel("User Details");
        Intro.setBounds(100, 50, 200, 50);
        home_personal_content.add(Intro);

        fname = new JLabel("First Name");
        fname.setBounds(50, 100, 100, 50);
        home_personal_content.add(fname);

        fanswer = new JLabel();
        fanswer.setText(ffname);
        fanswer.setBounds(300, 100, 100, 50);
        home_personal_content.add(fanswer);

        lname = new JLabel("Last Name");
        lname.setBounds(50, 150, 100, 50);
        home_personal_content.add(lname);

        lanswer = new JLabel();
        lanswer.setText(llname);
        lanswer.setBounds(300, 150, 100, 50);
        home_personal_content.add(lanswer);

        e_mail = new JLabel("Email");
        e_mail.setBounds(50, 200, 100, 50);
        home_personal_content.add(e_mail);

        eanswer = new JLabel(ee_mail);
        eanswer.setText(ee_mail);
        eanswer.setBounds(300, 200, 100, 50);
        home_personal_content.add(eanswer);

        reg_date = new JLabel("Registration Date");
        reg_date.setBounds(50, 250, 100, 50);
        home_personal_content.add(reg_date);

        ranswer = new JLabel();
        ranswer.setText(rreg_date);
        ranswer.setBounds(300, 250, 100, 50);
        home_personal_content.add(ranswer);

        home_personal_content.setVisible(true);
    }

    // public static void main(String args[]) throws Exception {
    // JFrame test = new JFrame("test");
    // test.setSize(500, 500);
    // JPanel pan = new PersonalDetail(1);
    // test.add(pan);
    // test.setVisible(true);
    // }
}
