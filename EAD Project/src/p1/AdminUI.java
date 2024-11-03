package p1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI {
    private JTextField tukTukChargeField;
    private JTextField carChargeField;
    private static double tukTukCharge;
    private static double carCharge;

    public AdminUI() {
        JFrame frame = new JFrame("Admin Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(224, 186, 1)); // Background color

        // Admin title label
        JLabel titleLabel = new JLabel("ADMIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(30, 20, 200, 50);
        frame.add(titleLabel);

        // TUK TUK Charge label and text field
        JLabel tukTukLabel = new JLabel("• Change TUK TUK Charge for 1KM :");
        tukTukLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        tukTukLabel.setBounds(30, 100, 300, 30);
        frame.add(tukTukLabel);

        tukTukChargeField = new JTextField();
        tukTukChargeField.setBounds(350, 100, 150, 30);
        frame.add(tukTukChargeField);

        // CAR Charge label and text field
        JLabel carLabel = new JLabel("• Change CAR Charge for 1KM :");
        carLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        carLabel.setBounds(30, 150, 300, 30);
        frame.add(carLabel);

        carChargeField = new JTextField();
        carChargeField.setBounds(350, 150, 150, 30);
        frame.add(carChargeField);

        // Change button
        JButton changeButton = new JButton("CHANGE");
        changeButton.setBounds(350, 200, 100, 40);
        changeButton.setBackground(Color.YELLOW);
        frame.add(changeButton);

        // View new bookings button
        JButton viewBookingsButton = new JButton("View New Bookings");
        viewBookingsButton.setBounds(180, 300, 200, 40);
        viewBookingsButton.setBackground(Color.GREEN);
        frame.add(viewBookingsButton);

        // Change button action listener
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tukTukCharge = Double.parseDouble(tukTukChargeField.getText());
                    carCharge = Double.parseDouble(carChargeField.getText());
                    JOptionPane.showMessageDialog(frame, "Charges updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers for charges.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // View bookings button action listener
        viewBookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaxiSystemAdmin nextPage = new TaxiSystemAdmin();
                nextPage.setVisible(true);
                frame.dispose(); // Close the AdminUI window
            }
        });

        frame.setVisible(true);
    }
}
