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

public class PersonalDetail extends JPanel {
    public JLabel Intro, fname, lname, e_mail, reg_date, fanswer, lanswer, eanswer, ranswer;
    public String ffname, llname, ee_mail, rreg_date;
    public JPanel home_personal_content;
    public UserDAO udao;
    public User u;

    PersonalDetail(int ID) throws SQLException {
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));

        u = null;
        udao = new UserDAOImplementation();
        u = udao.get(ID);
        ffname = u.getFirstName();
        llname = u.getLastName();
        ee_mail = u.getEmail();
        rreg_date = u.getRegistrationDate();

        Intro = new JLabel("User Details");
        Intro.setForeground(new Color(0, 0, 255));
        Intro.setFont(new Font("Arial", Font.PLAIN, 25));
        Intro.setBackground(new Color(35, 59, 97));
        Intro.setOpaque(true);
        Intro.setForeground(Color.white);
        Intro.setHorizontalAlignment(JLabel.CENTER);
        Intro.setBorder(glassyBorder);
        Intro.setBounds(100, 50, 200, 50);
        add(Intro);
        setLayout(null);
        setBounds(0, 0, 1060, 564);

        fname = new JLabel("First Name");
        fname.setBounds(50, 100, 100, 50);
        add(fname);

        fanswer = new JLabel();
        fanswer.setText(ffname);
        fanswer.setBounds(300, 100, 100, 50);
        add(fanswer);

        lname = new JLabel("Last Name");
        lname.setBounds(50, 150, 100, 50);
        add(lname);

        lanswer = new JLabel();
        lanswer.setText(llname);
        lanswer.setBounds(300, 150, 100, 50);
        add(lanswer);

        e_mail = new JLabel("Email");
        e_mail.setBounds(50, 200, 100, 50);
        add(e_mail);

        eanswer = new JLabel(ee_mail);
        eanswer.setText(ee_mail);
        eanswer.setBounds(300, 200, 100, 50);
        add(eanswer);

        reg_date = new JLabel("Registration Date");
        reg_date.setBounds(50, 250, 100, 50);
        add(reg_date);

        ranswer = new JLabel();
        ranswer.setText(rreg_date);
        ranswer.setBounds(300, 250, 100, 50);
        add(ranswer);

        setVisible(true);
    }

    // public static void main(String args[]) throws Exception {
    // JFrame test = new JFrame("test");
    // test.setSize(500, 500);
    // JPanel pan = new PersonalDetail(1);
    // test.add(pan);
    // test.setVisible(true);
    // }
}
