package waterstation.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {

    JFrame frame;
    JLabel fullNameLabel, phoneNumLabel, homeAddressLabel, passWordLabel, userNameLabel;
    JPasswordField passwordField;
    JTextField fullNameField, homeAddressField, phoneNumField, userNameField;
    JButton createButton, exitButton;

    public Signup() {
        frame = new JFrame("Sign Up");

        fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setBounds(700, 300, 100, 20);
        frame.add(fullNameLabel);

        phoneNumLabel = new JLabel("Phone Number:");
        phoneNumLabel.setBounds(700, 350, 100, 20);
        frame.add(phoneNumLabel);

        homeAddressLabel = new JLabel("Home Address:");
        homeAddressLabel.setBounds(700, 400, 100, 20);
        frame.add(homeAddressLabel);

        passWordLabel = new JLabel("Password:");
        passWordLabel.setBounds(700, 500, 100, 20);
        frame.add(passWordLabel);

        userNameLabel = new JLabel("Username:");
        userNameLabel.setBounds(700, 450, 100, 20);
        frame.add(userNameLabel);

        fullNameField = new JTextField();
        fullNameField.setBounds(850, 300, 150, 20);
        frame.add(fullNameField);

        phoneNumField = new JTextField();
        phoneNumField.setBounds(850, 350, 150, 20);
        frame.add(phoneNumField);

        homeAddressField = new JTextField();
        homeAddressField.setBounds(850, 400, 150, 20);
        frame.add(homeAddressField);

        userNameField = new JTextField();
        userNameField.setBounds(850, 450, 150, 20);
        frame.add(userNameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(850, 500, 150, 20);
        frame.add(passwordField);

        createButton = new JButton("Create");
        createButton.setBounds(730, 550, 100, 20);
        createButton.setBackground(new Color(76, 187, 23));
        createButton.setForeground(Color.white);
        createButton.addActionListener(this);
        frame.add(createButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(860, 550, 100, 20);
        exitButton.setBackground(Color.red);
        exitButton.setForeground(Color.white);
        exitButton.addActionListener(this);
        frame.add(exitButton);

        ImageIcon signUpIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/login.jpg"));
        Image signUpImage = signUpIcon.getImage().getScaledInstance(1200, 700, Image.SCALE_DEFAULT);
        ImageIcon signUpImageIcon = new ImageIcon(signUpImage);
        JLabel signUpLabel = new JLabel(signUpImageIcon);
        signUpLabel.setBounds(0, 0, 1200, 700);
        frame.add(signUpLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String fullName = fullNameField.getText();
            String phoneNumber = phoneNumField.getText();
            String homeAddress = homeAddressField.getText();
            String userName = userNameField.getText();
            String password = new String(passwordField.getPassword());

            if (!fullName.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(null, "Full Name must contain only letters.");
                return;
            }

            if (fullName.isEmpty() || phoneNumber.isEmpty() || homeAddress.isEmpty() || userName.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all the details.");
                return;
            }
            if (!phoneNumber.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid phone number.");
                return;
            }

            if (isUsernameTaken(userName)) {
                JOptionPane.showMessageDialog(null, "Username already taken. Please choose a different username.");
                return;
            }

            try {
                database c = new database();
                String query = "INSERT INTO Signup (username, name, password, address, phonenum) " +
                        "VALUES ('" + userName + "', '" + fullName + "', '" + password + "', '" + homeAddress + "', '" + phoneNumber + "')";
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Account Created");
                frame.setVisible(false);

                new LoginFrame();
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == exitButton) {
            frame.dispose();
        }
    }

    private boolean isUsernameTaken(String username) {
        try {
            database c = new database();
            String query = "SELECT * FROM Signup WHERE username = '" + username + "'";
            ResultSet resultSet = c.statement.executeQuery(query);
            boolean isTaken = resultSet.next();
            resultSet.close();
            c.statement.close();
            c.connection.close();
            return isTaken;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
