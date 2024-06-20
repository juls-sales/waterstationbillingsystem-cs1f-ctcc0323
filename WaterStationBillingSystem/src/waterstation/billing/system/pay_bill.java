package waterstation.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class pay_bill extends JFrame implements ActionListener {

    String num_of_gallons, username;
    JButton payButton, backButton;
    JLabel title, numofGallonsLabel, nameLabel;
    JPanel panel;
    JTextField numofGallonsT, nameText;

    public pay_bill(String num_of_gallons, String username) {
        this.num_of_gallons = num_of_gallons;
        this.username = username;

        setTitle("Pay Bill");
        setSize(700, 500);
        setLocation(400, 200);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214, 195, 247));
        add(panel, BorderLayout.CENTER);

        title = new JLabel("Pay Bill");
        title.setBounds(170, 10, 300, 20);
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(title);

        numofGallonsLabel = new JLabel("Number of Gallons:");
        numofGallonsLabel.setBounds(70, 160, 130, 20);
        panel.add(numofGallonsLabel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/Bill.png"));
        Image image = imageIcon.getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon1);
        add(imageLabel, BorderLayout.EAST);

        numofGallonsT = new JTextField();
        numofGallonsT.setBounds(200, 160, 150, 20);
        numofGallonsT.setEditable(false);
        panel.add(numofGallonsT);

        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bill WHERE num_of_gallons = '" + num_of_gallons + "'");
            if (resultSet.next()) {
                numofGallonsT.setText(resultSet.getString("num_of_gallons"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        payButton = new JButton("Pay");
        payButton.setBounds(100, 220, 100, 25);
        payButton.setBackground(Color.black);
        payButton.setForeground(Color.white);
        payButton.addActionListener(this);
        panel.add(payButton);

        backButton = new JButton("Back");
        backButton.setBounds(220, 220, 100, 25);
        backButton.setBackground(Color.red);
        backButton.setForeground(Color.white);
        backButton.addActionListener(this);
        panel.add(backButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {
            try {
                database c = new database();
                String updateQuery = "UPDATE bill SET status = 'Paid' WHERE num_of_gallons = '" + num_of_gallons + "'";
                c.statement.executeUpdate(updateQuery);

                JOptionPane.showMessageDialog(null, "Bill Paid Successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            setVisible(false);
            new viewBill(username);
        } else if (e.getSource() == backButton) {
            new calculate_bill("");
            dispose();
        }
    }

    public static void main(String[] args) {
        new pay_bill("", "");
    }
}
