package Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import CommonClasses.Button;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartAuction extends JPanel {
    public JButton btnStartAuction;
    private Start start;

    public StartAuction() {
        setLayout(null);
        setBounds(0, 0, 1060, 564);

        start = new Start();
        add(start);
        start.setVisible(false);

        btnStartAuction = Button.CustomButton("Start Auction");
        btnStartAuction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnStartAuction.setVisible(false);
                start.setVisible(true);

            }
        });
        btnStartAuction.setBounds(355, 230, 266, 57);
        add(btnStartAuction);
    }

}