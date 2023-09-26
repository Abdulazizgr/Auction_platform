import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.BorderUIResource;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class Frame extends JFrame {
    JPanel panel3;
    JLabel label2;

    Frame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screensize = toolkit.getScreenSize();
        JPanel Panel1 = new JPanel(new BorderLayout());
        Panel1.setBounds(0, 0, screensize.width, 80);
        Panel1.setBackground(Color.white);
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));
ImageIcon icon = new ImageIcon("auction2.jpg");
        JLabel label1 = new JLabel();
        label1.setBounds(5, 5, 990, 80);
        label1.setBackground(Color.white);
        label1.setOpaque(true);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setText("AUCTION DAILY");
        label1.setFont(new Font(Font.SERIF, Font.BOLD, 35));
       

        label2 = new JLabel();
        label2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        label2.setBounds(5, 85, screensize.width - 10, 25);
        label2.setBorder(glassyBorder);
        label2.setBackground(new Color(35, 59, 97));
        label2.setForeground(Color.white);
        label2.setOpaque(true);
        label2.setHorizontalAlignment(JLabel.CENTER);

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(167, 192, 232));
        panel2.setLayout(null);

        panel3 = new JPanel();
        panel3.setBackground(new Color(167, 192, 232));

        this.setLayout(new BorderLayout(5, 5));
        this.setMinimumSize(new Dimension(1000, 600));
        this.setSize(screensize.width, screensize.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(113, 163, 240));
        this.add(Panel1, BorderLayout.NORTH);
        Panel1.setPreferredSize(new Dimension(screensize.width, 80));
        Panel1.add(label2, BorderLayout.SOUTH);
        Panel1.add(label1,BorderLayout.NORTH);
        label2.setPreferredSize(new Dimension(screensize.width - 10, 25));
        this.add(panel2, BorderLayout.WEST);
        panel2.setPreferredSize(new Dimension(300, 200));
        this.add(panel3, BorderLayout.EAST);
        panel3.setPreferredSize(new Dimension(1060, 10));
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Frame();
    }
}
