/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.view.LoginRegister;

import Kinopedia.Main;
import Kinopedia.DataTransaksi;
import Kinopedia.DataUser;
import static Kinopedia.Main.dataTransaksi;
import static Kinopedia.Main.dataUser;
import Kinopedia.Session;
import Kinopedia.model.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.ArrayList;
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
    
    public Login(ArrayList<DataTransaksi> dataTransaksi ,ArrayList<DataUser> dataUser) {
        
        setTitle("Kinopedia");
        setSize(470, 844);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // tengah layar
        
        setLayout(null);
        setBackground(Color.YELLOW);
        
        JLabel userLabel = new JLabel("USERNAME");
        userLabel.setFont(new Font("Poppins", Font.PLAIN, 10));
        userLabel.setBounds(60, 230, 200, 20);
        add(userLabel);
        
        JTextField inputUser = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // ✅ Background ikut radius
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 140, 0)); // ✅ Warna border oranye
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                g2.dispose();
            }
        };
        inputUser.setBorder(BorderFactory.createCompoundBorder(
            inputUser.getBorder(),                           
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        inputUser.setOpaque(false);
        inputUser.setBackground(new Color(200, 200, 200));
        inputUser.setBounds(50, 250, 350, 45);
        add(inputUser);
        
        JLabel passLabel = new JLabel("PASSWORD");
        passLabel.setFont(new Font("Poppins", Font.PLAIN, 10));
        passLabel.setBounds(60, 300, 200, 20);
        add(passLabel);
        
        JTextField inputPass = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // ✅ Background ikut radius
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 140, 0)); // ✅ Warna border oranye
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                g2.dispose();
            }
        };
        inputPass.setBorder(BorderFactory.createCompoundBorder(
            inputUser.getBorder(),                           
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        inputPass.setOpaque(false);
        inputPass.setBackground(new Color(200, 200, 200));
        inputPass.setBounds(50, 320, 350, 45);
        add(inputPass);
        
        JButton btnMasuk = new JButton("Masuk") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(-1, -1, getWidth() + 2, getHeight() + 2, 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnMasuk.setBounds(50, 400, 350, 45);
        btnMasuk.setBackground(new Color(255, 140, 0)); // oranye
        btnMasuk.setForeground(Color.WHITE);
        btnMasuk.setBorder(new RoundedBorder(20));
        btnMasuk.setFont(new Font("Arial", Font.BOLD, 14));
        btnMasuk.setMargin(new Insets(0, 0, 0, 0));
        btnMasuk.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnMasuk.setOpaque(false);
        btnMasuk.setContentAreaFilled(false);
        btnMasuk.setFocusable(false); 
        btnMasuk.setFocusPainted(false);
        add(btnMasuk);
        
        JLabel pindahKeUser = new JLabel("Belum Pernah Daftar?");
        pindahKeUser.setFont(new Font("Poppins", Font.PLAIN, 16));
        pindahKeUser.setBounds(150, 460, 200, 20);
        add(pindahKeUser);
                
        ErrorMessage1 = new JLabel("Username Tidak Terdaftar!");
        ErrorMessage1.setFont(new Font("Poppins", Font.PLAIN, 15));
        ErrorMessage1.setBounds(0, 670, 455, 20);
        ErrorMessage1.setHorizontalAlignment(JLabel.CENTER);
        ErrorMessage1.setForeground(Color.WHITE);
        ErrorMessage1.setVisible(false);
        add(ErrorMessage1);
        
        ErrorMessage2 = new JLabel("Silahkan untuk mendaftar terlebih dahulu!");
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
        
        
        btnMasuk.addActionListener(e -> {
            String username = inputUser.getText();
            String pass = inputPass.getText();
            
            if (username.equals(Main.admin.getNama()) && pass.equals(Main.admin.getPassword())) {
                JOptionPane.showMessageDialog(null, "Berhasil disimpan!");
            }
            else if (username.isEmpty() || pass.isEmpty()) {
                CetakError("Masih ada inputan yang kosong", "Tolong Pastikan inputan terisi semua!");
            } 
            else {
                boolean userDitemukan = false;
                for (int i = 0; i < dataUser.size(); i++) {
                    if (username.equals(dataUser.get(i).getNama())) {
                        if (pass.equals(dataUser.get(i).getPassword())) {
                            userDitemukan = true;
                            Session.getInstance().login(dataUser.get(i));
                            dispose();
                            Buyer frame = new Buyer();
                            frame.setVisible(true);
                            
                            break;
                        } else {
                            userDitemukan = true;
                            CetakError("Password Salah! ", "Silahkan Coba Lagi!");
                            break;
                        }
                    } 
                }
                if (!userDitemukan) {
                    CetakError("Username Belum Didaftarkan! ", "Silahkan Melakukan Daftar Akun Terlebih Dahulu!");
                }
            }
        }); 
        
        pindahKeUser.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked (java.awt.event.MouseEvent e) {
                new Register(dataTransaksi,dataUser);
                dispose();
            }
        });
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Login(dataTransaksi,dataUser);
    }
}

class RoundedBorder implements javax.swing.border.Border {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 140, 0));
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public java.awt.Insets getBorderInsets(Component c) {
        return new java.awt.Insets(radius / 2, radius / 2, radius / 2, radius / 2);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
