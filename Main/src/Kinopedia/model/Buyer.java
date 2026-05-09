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
import java.awt.geom.RoundRectangle2D;

public class Buyer extends JFrame {

    // Make sure the file exists exactly here:
    // src/Kinopedia/model/IMAGESS/LogoKinopedia.png
    private static final String LOGO_PATH = "/Kinopedia/model/IMAGESS/LogoKinopedia.png";

    public Buyer() {
        setTitle("Halaman Utama (Buyer)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 650);
        setLocationRelativeTo(null);

        JPanel root = new JPanel();
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(35, 40, 35, 40));
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        setContentPane(root);

        // Logo
        JLabel logo = new JLabel();
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = loadLogo();
        if (icon != null) {
            Image scaled = icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(scaled));
        } else {
            logo.setText("LOGO NOT FOUND");
            logo.setForeground(Color.RED);
        }
//
//        JLabel title = new JLabel("Kinopedia");
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//        title.setFont(new Font("SansSerif", Font.BOLD, 28));
//        title.setForeground(Color.BLACK);

        root.add(Box.createVerticalStrut(10));
        root.add(logo);
        root.add(Box.createVerticalStrut(10));
//        root.add(title);
        root.add(Box.createVerticalStrut(35));

        root.add(menuButton("Top Up", this::onTopUp));
        root.add(Box.createVerticalStrut(14));
        root.add(menuButton("Mini Games", this::onMiniGames));
        root.add(Box.createVerticalStrut(14));
        root.add(menuButton("Riwayat Pembelian", this::onRiwayatPembelian));
        root.add(Box.createVerticalStrut(14));
        root.add(menuButton("Log Out", this::onLogout));
    }

    private ImageIcon loadLogo() {
    java.net.URL url = getClass().getResource("/Kinopedia/model/IMAGESS/LogoKinopedia.png");
    if (url == null) {
        url = Thread.currentThread().getContextClassLoader()
           .getResource("Kinopedia/model/IMAGESS/LogoKinopedia.png");
    }
    System.out.println("logo url = " + url);
    if (url == null) {
        return null;
    } else {
        return new ImageIcon(url);
    }
}

    private JButton menuButton(String text, java.awt.event.ActionListener listener) {
        RoundedButton btn = new RoundedButton(text, 18);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.setBackground(new Color(0xFF8C1A));
        btn.setForeground(Color.BLACK);
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

    private void onTopUp(ActionEvent e) {
    new Game(this).setVisible(true); // open Game page
    setVisible(false);               // hide Buyer page
    }

    private void onMiniGames(ActionEvent e) {
//        new Game(this).setVisible(true);
        setVisible(true);
        JOptionPane.showMessageDialog(this,"you clicked Mini Games !!");
    }

    private void onRiwayatPembelian(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Riwayat Pembelian clicked");
    }

    private void onLogout(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(this, "Log out?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Buyer().setVisible(true));
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
        protected void paintComponent(Graphics g) {
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