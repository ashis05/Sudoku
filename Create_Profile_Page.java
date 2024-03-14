import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Create_Profile_Page extends JFrame implements ActionListener {

    private JLabel titleLabel, usernameLabel, confirm_passwordLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField,confirm_passwordField;
    private JButton createButton,backButton;

    public Create_Profile_Page() {
        setTitle("Create Profile");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Load custom font
        Font customFont = new Font("Arial", Font.BOLD, 18);

        // Create and style components
        titleLabel = new JLabel("Create a New Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLUE);
        usernameLabel = new JLabel("User Name:");
        usernameLabel.setFont(customFont);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(customFont);
        confirm_passwordLabel = new JLabel("Confirm Password:");
        confirm_passwordLabel.setFont(customFont);
        usernameField = new JTextField(10);
        usernameField.setFont(customFont);
        passwordField = new JPasswordField(10);
        passwordField.setFont(customFont);
        confirm_passwordField = new JPasswordField(10);
        confirm_passwordField.setFont(customFont);
        createButton = new JButton("Create Profile");
        createButton.setFont(customFont);
        createButton.setBackground(Color.GREEN);
        createButton.setForeground(Color.WHITE);
        backButton = new JButton("Back");
        backButton.setFont(customFont);
        backButton.setBackground(Color.GREEN);
        backButton.setForeground(Color.WHITE);

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(confirm_passwordLabel, gbc);
        gbc.gridx = 1;
        add(confirm_passwordField, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(createButton, gbc);
        //gbc.gridy = 4;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(backButton, gbc);

        // Add action listener to the create button
        createButton.addActionListener(this);
        backButton.addActionListener(this);

        // Make the frame visible
        setVisible(true);
    }

    // Action performed when create button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String username = usernameField.getText();
            String password = new String(confirm_passwordField.getPassword());
            String confirm_password = new String(passwordField.getPassword());
            if(!(username.isEmpty()) && password.equals(confirm_password))
            {	
            	MainSudoku ms = new MainSudoku();
            	ms.create_profile(username,password);
            	JOptionPane.showMessageDialog(this, "Username: " + username + "\nPassword: " + password);
            	dispose();
            	new Login_Page();
            }
            else
            {
            	JOptionPane.showMessageDialog(this, "Invalid info");
            }
            
            // Implement profile creation logic here
            // For demonstration purposes, simply display the entered information
            
        }
        else if(e.getSource() == backButton)
        {
        	dispose();
        	new Login_Page();
        }
    }

    public static void main(String[] args) {
        new Create_Profile_Page();
    }
}
