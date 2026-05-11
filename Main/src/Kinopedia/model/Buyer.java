/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.model;

/**
 *
 * @author William
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
 
public class Buyer extends JFrame {
 
    // Sesuaikan jika path resource kamu berbeda
    private static String LOGO_PATH = "/Kinopedia/model/IMAGESS/LogoKinopedia.png";
 
    public Buyer() {
        setTitle("Halaman Utama (Buyer)");
        setSize(470, 844);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        Color ORANGE = new Color(0xFF8C1A);
 
        // Panel utama
        JPanel root = new JPanel();
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(35, 40, 35, 40));
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        setContentPane(root);
 
        // ===== Logo =====
        JLabel logoLabel = new JLabel();
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        ImageIcon logoIcon = loadIcon(LOGO_PATH, 260, 260);
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } else {
            logoLabel.setText("LOGO NOT FOUND");
            logoLabel.setForeground(Color.RED);
        }
 
        root.add(logoLabel);
        root.add(Box.createVerticalStrut(35));
 
        // ===== Tombol Menu =====
        root.add(createMenuButton("Top Up", ORANGE, this::onTopUp));
        root.add(Box.createVerticalStrut(14));
 
        root.add(createMenuButton("Mini Games", ORANGE, this::onMiniGames));
        root.add(Box.createVerticalStrut(14));
 
        root.add(createMenuButton("Riwayat Pembelian", ORANGE, this::onRiwayatPembelian));
        root.add(Box.createVerticalStrut(14));
 
        root.add(createMenuButton("Log Out", ORANGE, this::onLogout));
 
        // Supaya konten tetap di atas, ada sisa ruang di bawah
        root.add(Box.createVerticalGlue());
    }
 
    // Load gambar dari resources + resize
    private ImageIcon loadIcon(String path, int w, int h) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.out.println("Icon not found: " + path);
            return null;
        }
        Image img = new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
 
    // Bikin tombol menu biar konsisten dan gampang dibaca
    private JButton createMenuButton(String text, Color orange, java.awt.event.ActionListener listener) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        btn.setBackground(orange);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
 
        btn.setFocusPainted(false);
        btn.setOpaque(true);
 
        Dimension size = new Dimension(260, 48);
        btn.setPreferredSize(size);
        btn.setMaximumSize(size);
        btn.setMinimumSize(size);
 
        btn.addActionListener(listener);
        return btn;
    }
 
    // ===== Action tombol =====
 
    private void onTopUp(ActionEvent e) {
        // Buka halaman Game, lalu sembunyikan halaman Buyer
        new Game(this).setVisible(true);
        setVisible(false);
    }
 
    private void onMiniGames(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "you clicked Mini Games !!");
    }
 
    private void onRiwayatPembelian(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Riwayat Pembelian clicked");
    }
 
    private void onLogout(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(this, "Log out?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
 
    public static void main(String[] args) {
        new Buyer().setVisible(true);
    }
}
