package waterstation.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class calculate_bill extends JFrame implements ActionListener {

    JPanel panel;
    JLabel fullNameLabel, homeAddressLabel, numOfGallonsLabel, nameText, addressText;
    JButton subButton, canButton;
    JTextField numOfGallonsT;
    String username;

    public calculate_bill(String username) {
        this.username = username;

        setTitle("Calculate Bill");

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214, 195, 247));
        add(panel);

        JLabel heading = new JLabel("Calculate Bill");
        heading.setBounds(140, 10, 300, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);


        numOfGallonsLabel = new JLabel("Enter Number of Gallons:");
        numOfGallonsLabel.setBounds(50, 150, 250, 30);
        panel.add(numOfGallonsLabel);

        numOfGallonsT = new JTextField();
        numOfGallonsT.setBounds(200, 150, 150, 30);
        panel.add(numOfGallonsT);

        subButton = new JButton("Submit");
        subButton.setBounds(110, 300, 100, 25);
        subButton.setBackground(Color.green);
        subButton.setForeground(Color.white);
        subButton.addActionListener(this);
        panel.add(subButton);

        canButton = new JButton("Cancel");
        canButton.setBounds(230, 300, 100, 25);
        canButton.setBackground(Color.red);
        canButton.setForeground(Color.white);
        canButton.addActionListener(this);
        panel.add(canButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/Bill.png"));
        Image image = imageIcon.getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon1);
        add(imageLabel, BorderLayout.EAST);

        setSize(700, 500);
        setLocation(400, 200);
        setVisible(true);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == canButton) {
            dispose();
        } else if (e.getSource() == subButton) {
            String numOfGallons = numOfGallonsT.getText();

            // Validate that numOfGallons is numeric
            if (!numOfGallons.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for gallons.");
                return;
            }

            int totalBill = Integer.parseInt(numOfGallons) * 25;

            try {
                database c = new database();
                String insertQuery = "INSERT INTO bill (username, num_of_gallons, total_bill, status) " +
                        "VALUES ('" + username + "', '" + numOfGallons + "', '" + totalBill + "', 'Not Paid')";
                c.statement.executeUpdate(insertQuery);

                JOptionPane.showMessageDialog(null, "Bill Calculated Successfully");

                new pay_bill(numOfGallons, username); // Open pay_bill with numOfGallons and username
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        // Ensure a username is passed here
        new calculate_bill("");
    }
}
