package waterstation.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class viewBill extends JFrame implements ActionListener {

    JTextArea area;
    JButton closeButton, billButton;
    String username;

    public viewBill(String username) {
        this.username = username;

        setSize(500, 700);
        setLocationRelativeTo(null);
        setTitle("View Bill");
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        area = new JTextArea(50, 15);
        area.setFont(new Font("Senserif", Font.ITALIC, 15));

        JScrollPane scrollPane = new JScrollPane(area);
        panel.add(scrollPane, BorderLayout.CENTER);

        closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        panel.add(closeButton, BorderLayout.SOUTH);

        billButton = new JButton("View Bill");
        billButton.addActionListener(this);
        panel.add(billButton, BorderLayout.SOUTH); // Add billButton to the panel

        add(panel, BorderLayout.CENTER);

        // Set initial text
        area.setText("\n \n \t ------------------- Click on the ---------------\n \t -------------------   View Bill -----------------\n");

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == billButton) {
            try {
                database c = new database();

                // Get the bill details from the bill table where status is 'Paid'
                String billQuery = "SELECT * FROM bill WHERE username = '" + username + "' AND status = 'Paid'";
                ResultSet billResultSet = c.statement.executeQuery(billQuery);

                boolean foundBills = false;

                area.setText("");


                while (billResultSet.next()) {
                    foundBills = true;
                    String billInfo = "\n\n"+"Number of Gallons: " + billResultSet.getString("num_of_gallons") + "\n"
                            + "Total Bill                 : ₱" + billResultSet.getString("total_bill") + "\n"
                            + "Status                    : " + billResultSet.getString("status") + "\n\n";

                    area.append(billInfo);
                }

                if (!foundBills) {
                    area.append("No paid bills found.");
                }

                billResultSet.close();
                c.statement.close();
                c.connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == closeButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new viewBill("100"); // Provide a valid num_of_gallons here
    }
}
