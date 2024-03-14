import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_Page extends JFrame implements ActionListener {

    private JLabel titleLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, createProfileButton; // New button added
    private ImageIcon loginImage;
    String username,password;
    
    public Login_Page() {
        setTitle("Login Page");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Load custom font
        Font customFont = new Font("Arial", Font.BOLD, 18);

        // Create and style components
        titleLabel = new JLabel("Sudoku Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLUE);
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(customFont);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(customFont);
        usernameField = new JTextField(10);
        usernameField.setFont(customFont);
        passwordField = new JPasswordField(10);
        passwordField.setFont(customFont);
        loginButton = new JButton("Login");
        loginButton.setFont(customFont);
        loginButton.setBackground(Color.GREEN);
        loginButton.setForeground(Color.WHITE);

        // Add image
        loginImage = new ImageIcon("login.png"); // replace "login.png" with your image file
        loginButton.setIcon(loginImage);

        // Create "Create Profile" button
        createProfileButton = new JButton("Create Profile");
        createProfileButton.setFont(customFont);

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
        gbc.gridwidth = 1;
        add(loginButton, gbc);

        // Add "Create Profile" button
        gbc.gridx = 1;
        add(createProfileButton, gbc);

        // Add action listeners to buttons
        loginButton.addActionListener(this);
        createProfileButton.addActionListener(this);

        // Make the frame visible
        setVisible(true);
    }

    // Action performed when buttons are clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            username = usernameField.getText();
            password = new String(passwordField.getPassword());
            if (login(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose(); // Close the login window
                new Menu_Page(username , password);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
            }
        } else if (e.getSource() == createProfileButton) {
            // Implement create profile functionality here
        	new Create_Profile_Page();
        	dispose();
        }
    }

    // Dummy login method
    private boolean login(String username, String password) {
        // Implement your login logic here
        // For demonstration purposes, always return true
        boolean flag = false;
        MainSudoku ms = new MainSudoku();
        
        if (username.isEmpty() || password.isEmpty()) {
            flag = false;
        } else {
            if (ms.check_credentials(username, password)) {
                flag = true;
            } else {
                flag = false;
            }
        }
        
        return flag;
    }

    public static void main(String[] args) {
        new Login_Page();
    }
}
