/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.view.LoginRegister;

import Kinopedia.model.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author William
 */
public class Login extends JFrame {
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
    
    public Login() {
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
        
        JButton btnMasuk = new JButton("Masuk");
        btnMasuk.setBounds(50, 400, 350, 45);
        btnMasuk.setBackground(new Color(255, 140, 0)); // oranye
        btnMasuk.setForeground(Color.WHITE);
        btnMasuk.setBorder(new RoundedBorder(15));
        btnMasuk.setFont(new Font("Arial", Font.BOLD, 14));
        btnMasuk.setBorder(BorderFactory.createEmptyBorder());
        btnMasuk.setFocusPainted(false);
        add(btnMasuk);
        
        JLabel pindahKeUser = new JLabel("Belum Pernah Daftar?");
        pindahKeUser.setFont(new Font("Poppins", Font.PLAIN, 16));
        pindahKeUser.setBounds(150, 460, 200, 20);
        add(pindahKeUser);
                
        JLabel ErrorMessage1 = new JLabel("Username Tidak Terdaftar!");
        ErrorMessage1.setFont(new Font("Poppins", Font.PLAIN, 15));
        ErrorMessage1.setBounds(0, 670, 455, 20);
        ErrorMessage1.setHorizontalAlignment(JLabel.CENTER);
        ErrorMessage1.setForeground(Color.WHITE);
        ErrorMessage1.setVisible(false);
        add(ErrorMessage1);
        
        JLabel ErrorMessage2 = new JLabel("Silahkan untuk mendaftar terlebih dahulu!");
        ErrorMessage2.setFont(new Font("Poppins", Font.PLAIN, 15));
        ErrorMessage2.setBounds(0, 690, 455, 20);
        ErrorMessage2.setHorizontalAlignment(JLabel.CENTER);
        ErrorMessage2.setForeground(Color.WHITE);
        ErrorMessage2.setVisible(false);
        add(ErrorMessage2);
        
        JPanel backgroundErrorMessage = new JPanel();
        backgroundErrorMessage.setBackground(Color.DARK_GRAY);
        backgroundErrorMessage.setBorder(new RoundedBorder(15));
        backgroundErrorMessage.setBounds(50, 660, 350, 60);
        backgroundErrorMessage.setVisible(false);
        backgroundErrorMessage.setLayout(null);
        add(backgroundErrorMessage);
        
        
        btnMasuk.addActionListener(e -> {
            String username = inputUser.getText();
            String pass = inputPass.getText();
            
            if (username.equals("Admin") && pass.equals("Admin123")) {
                JOptionPane.showMessageDialog(null, "Berhasil disimpan!");
            }
            else if (username.isEmpty() || username.isEmpty()) {
                CetakError("Masih ada inputan yang kosong", "Tolong Pastikan inputan terisi semua!");
            } 
            else {
//                if (username.equals("will") && pass.equals("123")) {
                dispose();
                Buyer frame = new Buyer(username);
                frame.setVisible(true);
//                }
            }
        }); 
        
        pindahKeUser.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked (java.awt.event.MouseEvent e) {
                new Register();
                dispose();
            }
        });
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Login();
    }
}

class RoundedBorder implements Border {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.GRAY);
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius / 2, radius / 2, radius / 2, radius / 2);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
