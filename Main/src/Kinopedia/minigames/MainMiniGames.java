/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames;

/**
 *
 * @author William
 */

import Kinopedia.DataUser;
import Kinopedia.Main;
import Kinopedia.Session;
import Kinopedia.model.Buyer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MainMiniGames extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
//            JFrame dummyBack = new JFrame();
//            dummyBack.setSize(470, 844);
//            dummyBack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            dummyBack.setVisible(true);

            new MainMiniGames().setVisible(true);
//            dummyBack.setVisible(false);
        });
    }

//    private final JFrame backTo;

    public MainMiniGames() {
        DataUser userLogin = Session.getInstance().getCurrentUser();
        
        setTitle("Kinopedia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(470, 844);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(root);

        // ===== TOP BAR =====
        JButton backBtn = new JButton("< Kembali");
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setHorizontalAlignment(SwingConstants.LEFT);
        backBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
        backBtn.addActionListener(e -> goBack());

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);
        top.add(backBtn, BorderLayout.WEST);
        root.add(top, BorderLayout.NORTH);

        JLabel koinLabel = new JLabel("Koin: " + userLogin.getKoin());
        koinLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        koinLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        koinLabel.setBorder(BorderFactory.createEmptyBorder(20, 15, 0, 0)); 
        
        JLabel kreditLabel = new JLabel("Kredit: " + userLogin.getKredit());
        kreditLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        kreditLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        kreditLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); 
        
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.setBorder(new EmptyBorder(10, 0, 10, 0));
        root.add(content, BorderLayout.CENTER);
        content.add(koinLabel);
        content.add(Box.createVerticalStrut(5));
        content.add(kreditLabel);
        content.add(Box.createVerticalStrut(10));
        content.add(Box.createVerticalStrut(20));
        content.add(iconRow(new String[]{"Flappy Wild", "DinoRun", "Sudoku"}));
        
        
        JButton btnTukarKoin = new JButton("Tukar Koin") {
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
        btnTukarKoin.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnTukarKoin.setMargin(new Insets(0, 0, 0, 0));
        btnTukarKoin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnTukarKoin.setBackground(new Color(255, 140, 0));
        btnTukarKoin.setForeground(Color.BLACK);
        btnTukarKoin.setBorder(new RoundedBorder(20)); 
        btnTukarKoin.setOpaque(false);
        btnTukarKoin.setContentAreaFilled(false);
        btnTukarKoin.setFocusPainted(false);
        btnTukarKoin.setFocusable(false); 
        btnTukarKoin.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnTukarKoin.addActionListener(e -> {
            System.out.println("Bisa");
        });
        // ===== FOOTER LOGO (CENTERED) =====
        JPanel footer = new JPanel(new BorderLayout());
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setPreferredSize(new Dimension(Integer.MAX_VALUE, 120));
        footer.setBackground(Color.WHITE);
        footer.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        btnTukarKoin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnTukarKoin.setAlignmentX(Component.CENTER_ALIGNMENT);
        footer.add(btnTukarKoin);

        footer.add(Box.createVerticalStrut(10));

        ImageIcon logoImg = loadIcon("LogoKinopedia.png", 50, 50);
        JLabel logoLabel = (logoImg != null) ? new JLabel(logoImg) : new JLabel("Kinopedia");
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 

        footer.add(logoLabel, BorderLayout.CENTER);
        root.add(footer, BorderLayout.SOUTH);
    }

    private JLabel sectionTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        // remove left padding so it sits more left
        label.setBorder(new EmptyBorder(10, 0, 6, 0));

        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
        return label;
    }

    private JPanel iconRow(String[] names) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row.setBackground(Color.WHITE);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        for (String n : names) row.add(iconCard(n));
        return row;
    }

    private JPanel iconCard(String name) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        card.setPreferredSize(new Dimension(92, 108));
        card.setMinimumSize(new Dimension(92, 108));
        card.setMaximumSize(new Dimension(92, 108));

        JLabel icon = new RoundedImageLabel(18);
        icon.setPreferredSize(new Dimension(70, 70));
        icon.setMinimumSize(new Dimension(70, 70));
        icon.setMaximumSize(new Dimension(70, 70));
        icon.setHorizontalAlignment(SwingConstants.CENTER);
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        icon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        icon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                openBundlePage(name);
            }
        });

        String fileName = "";
        if (name.equals("DinoRun")) fileName = "logoDinoRun.png";
        if (name.equals("Sudoku")) fileName = "logoSudoku.jpg";
        if (name.equals("Flappy Wild")) fileName = "LogoFlappyWild.png";

        ImageIcon img = loadIcon(fileName, 70, 70);
        if (img != null) {
            icon.setIcon(img);
            icon.setOpaque(false);
            icon.setText(null);
        } else {
            icon.setOpaque(true);
            icon.setBackground(new Color(230, 230, 230));
            icon.setText("NO IMG");
        }

        JLabel text = new JLabel(name);
        text.setFont(new Font("SansSerif", Font.PLAIN, 11));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(icon);
        card.add(Box.createVerticalStrut(6));
        card.add(text);

        return card;
    }

    private void openBundlePage(String name) {
        if (name.equals("Flappy Wild")) {
            new Kinopedia.minigames.FllapyWild.GameWindow().setVisible(true);
        }

        if (name.equals("DinoRun")) {
            new Kinopedia.minigames.DinoRun.GameFrame().setVisible(true);
        }

        if (name.equals("Sudoku")) {
            Kinopedia.minigames.sudoku.Logic frame = new Kinopedia.minigames.sudoku.Logic();
            frame.menuGame();
            frame.setVisible(true);

        }
        dispose();
    }

    private ImageIcon loadIcon(String fileName, int w, int h) {
        if (fileName == null || fileName.equals("")) return null;

        java.net.URL url = getClass().getResource("/Kinopedia/minigames/gambarMain/" + fileName);
        if (url == null) return null;

        Image scaled = new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
    
    private void goBack() {
        new Buyer().setVisible(true);
        dispose();
    }

    private static class RoundedImageLabel extends JLabel {

        private int radius;

        public RoundedImageLabel(int radius) {
            this.radius = radius;
            setOpaque(false);
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            // 1) copy graphics
            Graphics2D g2 = (Graphics2D) g.create();

            // 2) make a rounded shape and clip (everything outside becomes hidden)
            Shape round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setClip(round);

            // 3) draw the normal JLabel (icon) but clipped
            super.paintComponent(g2);

            // 4) cleanup
            g2.dispose();
        }
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

