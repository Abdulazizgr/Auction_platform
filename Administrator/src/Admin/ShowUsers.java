package Admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import CommonClasses.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ShowUsers extends JPanel {
    public DefaultTableModel model;
    public JTable table;
    public JScrollPane j1;
    public JLabel title;
    private JButton refresh;
    private JButton delUser;

    public ShowUsers() {
        Border glassyBorder = new BorderUIResource(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.WHITE, new Color(180, 180, 180)));

        title = new JLabel("Customer List");
        title.setForeground(new Color(0, 0, 255));
        title.setBounds(450, 12, 182, 30);
        title.setFont(new Font("Arial", Font.PLAIN, 25));
        title.setBackground(new Color(35, 59, 97));
        title.setOpaque(true);
        title.setForeground(Color.white);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(glassyBorder);
        add(title);
        setLayout(null);
        setBounds(0, 0, 1060, 564);
        ArrayList<Object[]> userslist = new ArrayList<Object[]>();
        String[] column = { "ID", "FirstName", "LastName", "Email", "RegistrationDate" };
        userslist = users();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        for (Object[] userdata : userslist) {
            model.addRow(userdata);
        }
        table = new JTable(model);
        setColumnsWidth(table, 1060, 10, 20, 20, 30, 20);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        Dimension d = table.getPreferredSize();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(50, 50, 50, 50));
        scrollPane.setBounds(0, 10, d.width, 500);
        refresh = Button.CustomButton("Refresh");
        refresh.setBounds(0, 510, 182, 30);
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("success");
                refreshTableData();
            }
        });
        delUser = Button.CustomButton("Delete");
        delUser.setBounds(900, 510, 182, 30);
        delUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteUser();
                 JDialog dialog = new JDialog(); // Modal dialog
            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(null);
            JLabel label = new JLabel("You Have Succesfully Deleted A user");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBackground(new Color(113, 163, 240));
            dialog.getContentPane().add(label);

            dialog.setVisible(true);
            }
        });
        add(delUser);
        add(refresh);
        add(scrollPane);
    }

    private void deleteUser(){
       int ID = (int) table.getValueAt(table.getSelectedRow(),0);
       UserDAO userdao = new UserDAO();
       try {
        userdao.delete(userdao.get(ID));
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       model.removeRow(table.getSelectedRow());
    }
    private void refreshTableData() {
        ArrayList<Object[]> userslist = users();
        model.setRowCount(0); // Clear the existing table data
        for (Object[] userdata : userslist) {
            model.addRow(userdata); // Add the refreshed data to the table model
        }
    }

    public ArrayList<Object[]> users() {
        UserDAO userdao = new UserDAO();
        ArrayList<Object[]> userslist = new ArrayList<Object[]>();
        java.util.List<User> users = new ArrayList<>();
        try {
            users = userdao.getAll();
        } catch (SQLException E) {
            E.printStackTrace();
        }
        int i = 0;
        for (User user : users) {
            userslist.add(new Object[6]);
            userslist.get(i)[0] = (user.getUserID());
            userslist.get(i)[1] = (user.getFirstName());
            userslist.get(i)[2] = (user.getLastName());
            userslist.get(i)[3] = (user.getEmail());
            userslist.get(i)[4] = (user.getRegistrationDate());
            i++;
        }
        return userslist;
    }

    public static void setColumnsWidth(JTable table, int tablePreferredWidth,
            double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
        }
    }
}