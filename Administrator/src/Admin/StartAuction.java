package Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StartAuction extends JPanel {
    public JButton btnStartAuction;
    private Start start;

    public StartAuction() {
        setLayout(null);
        setBounds(0, 0, 1060, 564);

        start = new Start();
        add(start);
        start.setVisible(false);

        btnStartAuction = CustomButton("Start Auction");
        btnStartAuction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnStartAuction.setVisible(false);
                start.setVisible(true);

            }
        });
        btnStartAuction.setBounds(355, 230, 266, 57);
        add(btnStartAuction);
    }

    public JButton CustomButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setBackground(new Color(35, 59, 97));
        button.setForeground(Color.white);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusable(false);
        button.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    button.setBackground(new Color(60, 123, 240));
                } else {
                    button.setBackground(new Color(35, 59, 97));
                }
            }
        });
        return button;
    }

}