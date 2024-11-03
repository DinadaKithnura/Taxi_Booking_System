package p1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TaxiSystemAdmin extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public TaxiSystemAdmin() {
        // Window properties
        setTitle("View New Bookings");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel with golden yellow background
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 215, 0));

        // Title label
        JLabel titleLabel = new JLabel("View New Bookings", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Table setup for displaying booking data
        model = new DefaultTableModel();
        model.addColumn("Vehicle");
        model.addColumn("Date/Time");
        model.addColumn("Email");

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Right button panel with Load, Back, and Exit buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(new Color(255, 215, 0));

        // Button styles
        JButton loadButton = createStyledButton("LOAD");
        JButton backButton = createStyledButton("BACK");
        JButton exitButton = createStyledButton("EXIT");

        // Adding buttons to panel
        buttonPanel.add(loadButton);
        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // Button action listeners
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate back to AdminUI
                new AdminUI(); // Assuming AdminUI is another JFrame class
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(mainPanel);
        pack();
        setLocationRelativeTo(null); // Center window
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.YELLOW);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return button;
    }

    // Method to load data from the database into the table
    private void loadData() {
        model.setRowCount(0); // Clear existing rows

        String url = "jdbc:mysql://localhost:3306/taximanagementsystem";
        String username = "root"; // Your DB username
        String password = ""; // Your DB password

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            String query = "SELECT vehical, datetime, email FROM booking";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String vehicle = rs.getString("vehical");
                String datetime = rs.getString("datetime");
                String email = rs.getString("email");
                model.addRow(new Object[]{vehicle, datetime, email});
            }
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
