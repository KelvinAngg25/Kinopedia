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
import java.awt.geom.RoundRectangle2D;

public class Game extends JFrame {
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        JFrame dummyBack = new JFrame();   // fake back screen
        dummyBack.setSize(470, 844);
        dummyBack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dummyBack.setVisible(true);

        new Game(dummyBack).setVisible(true);
        dummyBack.setVisible(false);
    });
}

    private final JFrame backTo;

    public Game(JFrame backTo) {
        this.backTo = backTo;

        setTitle("Kinopedia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(470, 844);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(root);

        // Top bar
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

        // Content (scrollable)
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(10, 10, 30, 10)); // phone-like padding

        JScrollPane scroll = new JScrollPane(
                content,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scroll.setBorder(null);
        root.add(scroll, BorderLayout.CENTER);

        // Sections
        content.add(sectionTitle("First Person Shooter (FPS)"));
        content.add(Box.createVerticalStrut(10));   
        content.add(iconRow(new String[]{"Valorant", "PUBG Mobile", "CODM", "Free Fire"}));

        content.add(sectionTitle("Mobile Arena"));
        content.add(iconRow(new String[]{"Mobile Legend"}));
        content.add(Box.createVerticalStrut(14));

        content.add(sectionTitle("Sports"));
        content.add(iconRow(new String[]{"eFootball"}));
        content.add(Box.createVerticalStrut(14));

        content.add(sectionTitle("Application"));
        content.add(iconRow(new String[]{"Steam"}));
    }

    private JLabel sectionTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(new EmptyBorder(10, 0, 6, 0));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
        return label;
    }

    private JPanel iconRow(String[] names) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        row.setBackground(Color.WHITE);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        for (String n : names) {
            row.add(iconCard(n));
        }
        return row;
    }

    private JPanel iconCard(String name) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        // fixed size for clean alignment
        card.setPreferredSize(new Dimension(92, 108));
        card.setMinimumSize(new Dimension(92, 108));
        card.setMaximumSize(new Dimension(92, 108));

        JLabel icon = new RoundedImageLabel(18);
        icon.setPreferredSize(new Dimension(70, 70));
        icon.setMinimumSize(new Dimension(70, 70));
        icon.setMaximumSize(new Dimension(70, 70));
        icon.setHorizontalAlignment(SwingConstants.CENTER);
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        String fileName = "";
        if (name.equals("Valorant")) fileName = "Valorantt.png";
        if (name.equals("PUBG Mobile")) fileName = "PUBG.png";
        if (name.equals("CODM")) fileName = "CODMmobile.jpg";
        if (name.equals("Free Fire")) fileName = "FreeFire.jpg";
        if (name.equals("Mobile Legend")) fileName = "ML.png";
        if (name.equals("eFootball")) fileName = "efootball.png";
        if (name.equals("Steam")) fileName = "Steam.png";

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

    private ImageIcon loadIcon(String fileName, int w, int h) {
        if (fileName == null || fileName.equals("")) return null;

        java.net.URL url = getClass().getResource("/Kinopedia/model/IMAGESS/" + fileName);
        System.out.println("img url = " + url);

        if (url == null) return null;

        Image scaled = new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private void goBack() {
        dispose();
        backTo.setVisible(true);
    }

    private static class RoundedImageLabel extends JLabel {
        private final int radius;

        public RoundedImageLabel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        public void paintComponent(Graphics g) { // public (no protected)
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Shape clip = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setClip(clip);

            super.paintComponent(g2);
            g2.dispose();
        }
    }
}