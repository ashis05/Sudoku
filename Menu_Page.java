import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu_Page extends JFrame implements ActionListener {
    private JButton startButton, exitButton;
    private JLabel playerNameLabel, levelLabel;
    MainSudoku ms = new MainSudoku();
    int level ;
    String Username, Password;

    public Menu_Page(String username, String password) {
        setTitle("Sudoku Menu");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        int level = ms.get_level(username, password);
        Username = username;
        Password = password;

        setContentPane(new JLabel(new ImageIcon("C:\\Users\\91908\\OneDrive - Shri Vile Parle Kelavani Mandal\\Desktop\\thisIsAJavaFolderN\\SudokuTheNewOnefromAshsi\\src\\main\\java\\background_1.png"))); 

        Font titleFont = new Font("Arial", Font.BOLD, 36);
        Font buttonFont = new Font("Arial", Font.BOLD, 24);

        JLabel titleLabel = new JLabel("Sudoku Game");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);

        playerNameLabel = new JLabel("Player:"+ username); 
        playerNameLabel.setForeground(Color.WHITE);

        levelLabel = new JLabel("Level:"+ level); 
        levelLabel.setForeground(Color.WHITE);

        startButton = new JButton("Start Game");
        startButton.setFont(buttonFont);
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false); 

        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false); 

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(levelLabel, gbc); 
        gbc.gridx = 1;
        add(playerNameLabel, gbc); 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(startButton, gbc);
        gbc.gridx = 1;
        add(exitButton, gbc);

        // Add action listeners to buttons
        startButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Make the frame visible
        setVisible(true);
    }

    // Action performed when buttons are clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // Code to start the game goes here
            //JOptionPane.showMessageDialog(this, "Starting Sudoku Game...");
        	Sudoku_Page sp = new Sudoku_Page(Username, Password);
        	sp.Sudoku();
        	dispose();
        } else if (e.getSource() == exitButton) {
            // Code to exit the application goes here
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        //new Menu_Page();
    }
}
