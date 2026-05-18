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
import Kinopedia.DataTransaksi;
import Kinopedia.DataUser;
import Kinopedia.Main;
import Kinopedia.Session;
import Kinopedia.view.LoginRegister.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class Buyer extends JFrame {
    public Buyer() {
        DataUser userLogin = Main.dataUser.get(0);
        System.out.println(userLogin.getNama());
        
        setTitle("Kinopedia | Halaman Utama ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 844);
        
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        logoPanel.add(Box.createVerticalStrut(30));

        ImageIcon icon = new ImageIcon(getClass().getResource("IMAGESS/LogoKinopedia.png"));
        Image scaledImage = icon.getImage().getScaledInstance(
            170, 170, 
            Image.SCALE_SMOOTH
        );
        JLabel logoKinopedia = new JLabel (new ImageIcon(scaledImage));
        logoKinopedia.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logoKinopedia);
        
        
        
        JLabel titleLabel = new JLabel("Kinopedia");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(titleLabel);
        
    
        JPanel root = new JPanel();
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(35, 40, 35, 40));
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.add(logoPanel);
        root.add(Box.createVerticalStrut(80));
        root.add(menuButton("Top Up", this::onTopUp));
        root.add(Box.createVerticalStrut(14));
        root.add(menuButton("Mini Games", this::onMiniGames));
        root.add(Box.createVerticalStrut(14));
        root.add(menuButton("Riwayat Pembelian", this::onRiwayatPembelian));
        root.add(Box.createVerticalStrut(14));
        root.add(menuButton("Log Out", this::onLogout));
        root.add(Box.createVerticalGlue());
        root.add(Box.createVerticalGlue());
        add(root);
        
        setLocationRelativeTo(null);
    }

    private JButton menuButton(String text, java.awt.event.ActionListener listener) {
        RoundedButton btn = new RoundedButton(text, 18);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.setBackground(new Color(0xFF8C1A));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);

        Dimension size = new Dimension(260, 48);
        btn.setPreferredSize(size);
        btn.setMaximumSize(size);
        btn.setMinimumSize(size);

        btn.addActionListener(listener);
        return btn;
    }

    public void onTopUp(ActionEvent e) {
        dispose();
        new Game(this).setVisible(true);
    }

    private void onMiniGames(ActionEvent e) {
        dispose();
        new Kinopedia.minigames.MainMiniGames().setVisible(true);
    }

    public void onRiwayatPembelian(ActionEvent e) {
        dispose();
        History frame = new History();
        frame.setVisible(true);
    }

    public void onLogout(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(this, "Log out?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Main.saveSemuaData(Main.dataTransaksi, Main.dataUser, Main.admin);
            Session.getInstance().logout();
            dispose();
            Login frame = new Login(Main.dataTransaksi,Main.dataUser);
            frame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        Buyer frame = new Buyer();
        frame.setVisible(true);
    }

    static class RoundedButton extends JButton {

        private final int radius;
        private Shape shape;

        RoundedButton(String text, int radius) {
            super(text);
            this.radius = radius;
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setMargin(new Insets(10, 18, 10, 18));
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getModel().isArmed() ? getBackground().darker() : getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

            g2.setColor(getForeground());
            g2.drawString(getText(), x, y);
            g2.dispose();
        }

        @Override
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
            }
            return shape.contains(x, y);
        }
    }
}
