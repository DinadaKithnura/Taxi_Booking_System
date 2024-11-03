package register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class model {
    
    private String name;
    private String email;
    private String city;
    private String password;
    private String confirmPassword;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    String url = "jdbc:mysql://localhost:3306/taximanagementsystem";
    String dbUsername = "root"; 
    String dbPassword = ""; 

    public boolean registerUser() {
        if (!validateInputs()) {
            return false;
        }
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "INSERT INTO customer (name, email, city, password) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, city);
            statement.setString(4, password);

            int rowAffected = statement.executeUpdate();
            if (rowAffected > 0) {
                JOptionPane.showMessageDialog(null, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                sendEmailNotification(); // Send the email after successful registration
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
    }
    
    private boolean validateInputs() {           
        if (name.isEmpty() || email.isEmpty() || city.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!email.contains("@") || !email.endsWith(".com")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address", "Error", JOptionPane.WARNING_MESSAGE);
            return false;            
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match", "Error", JOptionPane.WARNING_MESSAGE);
            return false;            
        }
        return true;
    }

    private void sendEmailNotification() {
        String host = "smtp.gmail.com";
        final String user = "taxibookingsystem2@gmail.com";
        final String password = "pldd irww mgnu hcmb"; // Consider using an environment variable for security

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); // Sends to registered email
            message.setSubject("Registration Successful");
            message.setText("Hello " + name + ",\n\n Thank you for join with us!");

            Transport.send(message);
            System.out.println("Email Sent Successfully!");

        } catch (MessagingException e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
}
