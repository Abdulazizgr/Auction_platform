package User;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.BorderUIResource;

public class Cantpurchase extends JPanel {

    public JLabel message;

    Cantpurchase() throws Exception {
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));

        message = new JLabel("AUCTION HAS NOT STARTED PLEASE TRY AGAIN LATER!!!!");
        message.setBounds(100, 200, 800, 50);
        message.setFont(new Font("Arial", Font.BOLD, 24));
        message.setForeground(Color.RED);

        add(message);
        setLayout(null);
        setBounds(0, 0, 1060, 564);
        setVisible(true);
    }
}
