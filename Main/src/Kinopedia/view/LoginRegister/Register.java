/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.view.LoginRegister;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author William
 */
public class Register extends JFrame {
    private JLabel ErrorMessage1;
    private JLabel ErrorMessage2;
    private JPanel backgroundErrorMessage;

    public void setErrorMessage1(JLabel errorMessage1) {
        this.ErrorMessage1 = errorMessage1;
    }

    public void setErrorMessage2(JLabel errorMessage2) {
        this.ErrorMessage2 = errorMessage2;
    }

    public void setBackgroundErrorMessage(JPanel backgroundErrorMessage) {
        this.backgroundErrorMessage = backgroundErrorMessage;
    }
    
    private void CetakError(String isian1, String isian2) {
        ErrorMessage1.setText(isian1);
        ErrorMessage2.setText(isian2);
        
        ErrorMessage1.setVisible(true);
        ErrorMessage2.setVisible(true);
        backgroundErrorMessage.setVisible(true);
    }
    
    public Register () {
        setTitle("Kinopedia");
        setSize(470, 844);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // tengah layar
        
        setLayout(null);
        setBackground(Color.YELLOW);
        
        JLabel userLabel = new JLabel("USERNAME");
        userLabel.setFont(new Font("Poppins", Font.PLAIN, 10));
        userLabel.setBounds(50, 230, 200, 20);
        add(userLabel);
        
        JTextField inputUser = new JTextField(20);
        inputUser.setBounds(50, 250, 350, 45);
        inputUser.setBorder(new RoundedBorder(15));
        inputUser.setBackground(new Color(200, 200, 200)); // Warna abu-abu
        add(inputUser);
        
        JLabel passLabel = new JLabel("PASSWORD");
        passLabel.setFont(new Font("Poppins", Font.PLAIN, 10));
        passLabel.setBounds(50, 300, 200, 20);
        add(passLabel);
        
        JTextField inputPass = new JTextField(20);
        inputPass.setBounds(50, 320, 350, 45);
        inputPass.setBorder(new RoundedBorder(15));
        inputPass.setBackground(new Color(200, 200, 200)); // Warna abu-abu
        add(inputPass);
        
        JButton btnDaftar = new JButton("Daftar");
        btnDaftar.setBounds(50, 400, 350, 45);
        btnDaftar.setBackground(new Color(255, 140, 0)); // oranye
        btnDaftar.setForeground(Color.WHITE);
        btnDaftar.setBorder(new RoundedBorder(15));
        btnDaftar.setFont(new Font("Arial", Font.BOLD, 14));
        btnDaftar.setBorder(BorderFactory.createEmptyBorder());
        btnDaftar.setFocusPainted(false);
        add(btnDaftar);
        
        ErrorMessage1 = new JLabel("");
        ErrorMessage1.setFont(new Font("Poppins", Font.PLAIN, 15));
        ErrorMessage1.setBounds(0, 670, 455, 20);
        ErrorMessage1.setHorizontalAlignment(JLabel.CENTER);
        ErrorMessage1.setForeground(Color.WHITE);
        ErrorMessage1.setVisible(false);
        add(ErrorMessage1);
        
        ErrorMessage2 = new JLabel("");
        ErrorMessage2.setFont(new Font("Poppins", Font.PLAIN, 15));
        ErrorMessage2.setBounds(0, 690, 455, 20);
        ErrorMessage2.setHorizontalAlignment(JLabel.CENTER);
        ErrorMessage2.setForeground(Color.WHITE);
        ErrorMessage2.setVisible(false);
        add(ErrorMessage2);
        
        backgroundErrorMessage = new JPanel();
        backgroundErrorMessage.setBackground(Color.DARK_GRAY);
        backgroundErrorMessage.setBorder(new RoundedBorder(15));
        backgroundErrorMessage.setBounds(50, 660, 350, 60);
        backgroundErrorMessage.setVisible(false);
        backgroundErrorMessage.setLayout(null);
        add(backgroundErrorMessage);
        
        JLabel pindahKeUser = new JLabel("Sudah Pernah Daftar?");
        pindahKeUser.setFont(new Font("Poppins", Font.PLAIN, 16));
        pindahKeUser.setBounds(150, 460, 200, 20);
        add(pindahKeUser);
        
        btnDaftar.addActionListener(e -> {
            String username = inputUser.getText();
            String pass = inputPass.getText();
            
            if (username.isEmpty() || pass.isEmpty()) {
                CetakError("Masih ada inputan yang kosong", "Tolong Pastikan inputan terisi semua!");
            }
            else {
                CetakError("Berhasil Masuk", "William!");
            } 
        }); 
        
        pindahKeUser.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked (java.awt.event.MouseEvent e) {
                new Login();
                dispose();
            }
        });
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new Register();
    }
}
