package Admin;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AuctionRunning extends JPanel {
        public AuctionRunning() {

                setLayout(null);
                setBounds(0, 0, 1060, 564);

                JLabel NewLabel = new JLabel("Auction is Running now,");
                NewLabel.setForeground(Color.RED);
                NewLabel.setBounds(229, 202, 560, 54);
                NewLabel.setFont(new Font("Serif", Font.BOLD, 40));
                add(NewLabel);

                JLabel uploadlater = new JLabel("Please Upload the Items Later.");
                uploadlater.setForeground(Color.RED);
                uploadlater.setFont(new Font("Serif", Font.BOLD, 40));
                uploadlater.setBounds(151, 256, 700, 54);
                add(uploadlater);
        }
}
