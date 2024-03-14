
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//import javafx.stage.WindowEvent
public class Sudoku_Page {
    // MongoClient mongo = new MongoClient("localhost", 27017);
    String Name;
    int[][] solvedBoard, board ;
    JFrame f = new JFrame("Sudoku");
    JTextField t = new JTextField();
    Font font1 = new Font("SansSerif", Font.BOLD, 20);
    Label l = new Label();
    String Username, Password;
    public Sudoku_Page(String username, String password)
    {
    	Username = username;
    	Password = password;
    	solvedBoard = board_gen.generateSolvedGrid();
        board = board_gen.createRandomizedGrid(solvedBoard,username,password);
    }

    public void Sudoku() {
        PrintBoard();
        JButton submit = new JButton("SUBMIT");
        // submit.setHorizontalAlignment(JButton.CENTER);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Valid();
            }
        });
        l = new Label("Play Sudoku");
        // submit.setBounds(250, 400, 400, 50);
        f.setFocusable(false);
        f.add(l);
        f.setLayout(new GridLayout(10, 9,2,2));
        //GridLayoutConstraints glc = new GridLayoutConstraints();
        f.setSize(800, 800);
        // f.setResizable(false);
        f.add(submit).setBounds(10, 0, 3, 1);
        f.setVisible(true);
        // f.pack();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                f.dispose();
            }
        });

    }

    public void PrintBoard() {
        String temp;
        int flag = 1;
        for (int i = 0; i < 9; i++) {
            if (i <= 2 || i >= 6) {
                flag = 1;
            } else {
                flag = 0;
            }
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    t = new JTextField();
                    t.setEditable(true);
                } else {
                    temp = Integer.toString(board[i][j]);
                    t = new JTextField(temp);
                    t.setEditable(false);
                }
                if ((i >= 3 && i <= 5) && (j >= 3 && j <= 5)) {
                    t.setBackground(Color.GRAY);
                } else if (flag == 1 && (j <= 2 || j >= 6)) {
                    t.setBackground(Color.GRAY);
                } else {
                    t.setBackground(Color.WHITE);
                }
                t.setBounds(50, 50, 50, 50);
                t.setFont(font1);
                t.setHorizontalAlignment(JTextField.CENTER);
                f.add(t);

            }
        }
    }

    public void ResetBoard() {
        f.getContentPane().removeAll();
        // PrintBoard();
        Sudoku();
    }

    public void Valid() {
        if (!(Validity())) {
            // PrintBoard();
            ResetBoard();
            JOptionPane.showMessageDialog(null,"The answer is wrong, try again; ");
        } else {
        	MainSudoku ms = new MainSudoku();
        	System.out.println(Username);
        	System.out.println(Password);
        	int level = ms.get_level(Username, Password);
        	int count = 0;
        	count = level + 1;
        	ms.Update_Level(count, Username, Password);      	
        	JOptionPane.showMessageDialog(null,"The answer was correct");
        	f.dispose();
        	new Menu_Page(Username , Password);
        }
        f.add(l);
    }

    public boolean Validity() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField t = (JTextField) f.getContentPane().getComponent(i * 9 + j);
                String st = (t.getText()).trim();
                if (st.isEmpty()) {
                    return false;
                }
                if (Integer.parseInt(st) != solvedBoard[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
       // Sudoku_Page sp = new Sudoku_Page();
       // sp.Sudoku();
    }
}