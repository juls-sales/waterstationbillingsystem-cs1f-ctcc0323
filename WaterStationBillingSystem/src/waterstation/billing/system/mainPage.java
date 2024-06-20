package waterstation.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainPage extends JFrame implements ActionListener {

    JMenuItem calculateBill, depositDet, viewInformation, viewBill, payBill, eexit;

    public mainPage() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Menu
        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("Serif", Font.PLAIN, 15));
        menuBar.add(menu);

        calculateBill = new JMenuItem("Calculate Bill");
        calculateBill.setFont(new Font("Monospaced", Font.PLAIN, 14));
        calculateBill.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icon/Bill.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        calculateBill.addActionListener(this);
        menu.add(calculateBill);



        // Bill
        JMenu bill = new JMenu("Bill");
        bill.setFont(new Font("Serif", Font.PLAIN, 15));
        menuBar.add(bill);

        viewBill = new JMenuItem("View Bill");
        viewBill.setFont(new Font("Monospaced", Font.PLAIN, 14));
        viewBill.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icon/Bill.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        viewBill.addActionListener(this);
        bill.add(viewBill);

        payBill = new JMenuItem("Pay Bill");
        payBill.setFont(new Font("Monospaced", Font.PLAIN, 14));
        payBill.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icon/pay.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        payBill.addActionListener(this);
        bill.add(payBill);

        // Exit
        JMenu exitMenu = new JMenu("Exit");
        exitMenu.setFont(new Font("Serif", Font.PLAIN, 15));
        menuBar.add(exitMenu);

        eexit = new JMenuItem("Exit");
        eexit.setFont(new Font("Monospaced", Font.PLAIN, 14));
        eexit.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("icon/exit.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        eexit.addActionListener(this);
        exitMenu.add(eexit);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/water/Water.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(1500, 830, Image.SCALE_DEFAULT);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        add(imageLabel);

        setLayout(new FlowLayout());
        setVisible(true); // Make the frame visible
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateBill) {
            new calculate_bill("");
        } else if (e.getSource() == viewBill) {
            new viewBill("");
        } else if (e.getSource() == payBill) {
            new pay_bill("", "");
        } else if (e.getSource() == eexit) {
            setVisible(false); // Hide the current frame
            new LoginFrame(); // Open the LoginFrame
        }
    }

    public static void main(String[] args) {
        new mainPage();
    }
}
