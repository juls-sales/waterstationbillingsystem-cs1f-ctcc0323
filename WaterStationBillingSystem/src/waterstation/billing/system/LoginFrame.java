package waterstation.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class LoginFrame extends JFrame implements ActionListener {
    JFrame frame;
    JLabel userNameLabel, passWordL;
    JPasswordField passWord;
    JTextField userNameT;
    JButton logIn, cancelButton, clearButton, signUp;


    public LoginFrame() {
        frame = new JFrame("Log In");

        userNameLabel = new JLabel("UserName:");
        userNameLabel.setBounds(700, 300, 100, 20);
        userNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        frame.add(userNameLabel);

        passWordL = new JLabel("Password:");
        passWordL.setBounds(700, 370, 100, 20);
        passWordL.setFont(new Font("Tahoma", Font.BOLD, 15));
        frame.add(passWordL);

        userNameT = new JTextField();
        userNameT.setBounds(850, 300, 150, 20);
        frame.add(userNameT);

        passWord = new JPasswordField();
        passWord.setBounds(850, 370, 150, 20);
        frame.add(passWord);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(750, 500, 100, 25);
        cancelButton.setBackground(Color.red);
        cancelButton.setForeground(Color.white);
        cancelButton.addActionListener(this);
        frame.add(cancelButton);

        signUp = new JButton("Sign Up");
        signUp.setBounds(880, 500, 100, 25);
        signUp.setBackground(new Color(76, 187, 23));
        signUp.setForeground(Color.white);
        signUp.addActionListener(this);
        frame.add(signUp);

        logIn = new JButton("Log in");
        logIn.setBounds(750, 450, 100, 25);
        logIn.setBackground(new Color(76, 187, 23));
        logIn.setForeground(Color.white);
        logIn.addActionListener(this);
        frame.add(logIn);

        clearButton = new JButton("Clear");
        clearButton.setBounds(880, 450, 100, 25);
        clearButton.setBackground(new Color(255, 192, 0));
        clearButton.setForeground(Color.white);
        clearButton.addActionListener(this);
        frame.add(clearButton);

        ImageIcon logInIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/login.jpg"));
        Image loginImage = logInIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon flogIn = new ImageIcon(loginImage);
        JLabel logInLabel = new JLabel(logInIcon);
        logInLabel.setBounds(0, 0, 200, 200); // Set bounds for the image label
        frame.add(logInLabel); // Add imageLabel to the JFrame

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logIn) {
            String userName = userNameT.getText();
            String password = new String(passWord.getPassword());
            try {
                database d = new database();
                String queryy = "select * from Signup WHERE username = '" + userName + "' AND password = '" + password + "'";
                ResultSet rs = d.statement.executeQuery(queryy);
                if (rs.next()) {
                    String pass = rs.getString("password");
                    frame.setVisible(false);
                    new mainPage();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == cancelButton) {
            frame.dispose();
        } else if (e.getSource() == clearButton) {
            userNameT.setText("");
            passWord.setText("");
        }else if (e.getSource() == signUp) {
            new Signup();
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
