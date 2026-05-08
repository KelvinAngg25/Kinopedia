/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.Sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

/**
 *
 * @author William
 */
public class View extends JFrame {

    JLabel timerLabel;
    JLabel livesLabel;
    JLabel sudokuLabel;

    public View() {
        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Poppins", Font.BOLD, 50));
        setTitle("Sudoku");
        setSize(1000, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        livesLabel = new JLabel("Lives : 3/3");
        livesLabel.setFont(new Font("Poppins", Font.BOLD, 50));
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(timerLabel, BorderLayout.WEST);
        topPanel.add(livesLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        Timer();

        JPanel sudokuPanel = new JPanel(new java.awt.GridLayout(9, 9));
        sudokuPanel.setPreferredSize(new Dimension(800, 800));
        sudokuPanel.setMaximumSize(new Dimension(800, 800));
        sudokuPanel.setMinimumSize(new Dimension(800, 800));
        sudokuPanel.setBackground(Color.WHITE);
        sudokuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,20));
        centerPanel.add(sudokuPanel);
        add(centerPanel, BorderLayout.CENTER);
        
        
        int[][] puzzle = Logic.generatePuzzle();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Poppins", Font.BOLD, 20));
                cell.setPreferredSize(new Dimension(55, 55));
                int top    = (row % 3 == 0) ? 3 : 1;
                int left   = (col % 3 == 0) ? 3 : 1;
                int bottom = (row == 8)     ? 3 : 1;
                int right  = (col == 8)     ? 3 : 1;
                cell.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                if (puzzle[row][col] != 0) {
                    cell.setText(String.valueOf(puzzle[row][col]));
                    cell.setEditable(false);
                    cell.setBackground(Color.LIGHT_GRAY);
                }
                sudokuPanel.add(cell);
            }
        }   

    }

    public void Timer() {
        Logic timer = new Logic(5, 0, false, this);
        Thread t = new Thread(timer);
        t.setDaemon(true);
        t.start();
    }

    public void updateTimer(int menit, int detik) {
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(String.format("%02d : %02d", menit, detik));
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View v = new View();
            v.setVisible(true);
        });
    }

}
