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
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Game extends JFrame{
     private final JFrame backTo;

    public Game(JFrame backTo) {
        this.backTo = backTo;

        setTitle("Kinopedia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(380, 650);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(root);

        JButton backBtn = new JButton("< Kembali");
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setHorizontalAlignment(SwingConstants.LEFT);
        backBtn.addActionListener(e -> goBack());

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);
        top.add(backBtn, BorderLayout.WEST);
        root.add(top, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(
                content,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scroll.setBorder(null);
        root.add(scroll, BorderLayout.CENTER);

        content.add(sectionTitle("First Person Shooter (FPS)"));
        content.add(iconRow(new String[]{"Valorant", "PUBG Mobile", "CODM", "Free Fire"}));
        content.add(Box.createVerticalStrut(18));

        content.add(sectionTitle("Mobile Arena"));
        content.add(iconRow(new String[]{"Mobile Legend"}));
        content.add(Box.createVerticalStrut(18));

        content.add(sectionTitle("Sports"));
        content.add(iconRow(new String[]{"eFootball"}));
        content.add(Box.createVerticalStrut(18));

        content.add(sectionTitle("Application"));
        content.add(iconRow(new String[]{"Steam"}));
    }

    // (Optional) test Bundle directly
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame dummyBack = new JFrame();
            dummyBack.setSize(380, 650);
            dummyBack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dummyBack.setVisible(true);

            new Game(dummyBack).setVisible(true);   // <-- NOT new Game()
            dummyBack.setVisible(false);
        });
    }

    private JLabel sectionTitle(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setBorder(new EmptyBorder(10, 0, 10, 0));
        return label;
    }

    private JPanel iconRow(String[] names) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 18, 0));
        row.setBackground(Color.WHITE);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (String n : names) {
            row.add(iconCard(n));
        }
        return row;
    }

    private JPanel iconCard(String name) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel icon = new RoundedImageLabel(10);
        icon.setPreferredSize(new Dimension(55, 55));
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

        ImageIcon img = null;
        if (!fileName.equals("")) {
            java.net.URL imgUrl = getClass().getResource("IMAGESS/" + fileName);
            System.out.println("URL = " + imgUrl); // print the real URL, not img

            if (imgUrl != null) {
                Image scaled = new ImageIcon(imgUrl).getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
                img = new ImageIcon(scaled);
            }
        }

        if (img != null) {
            icon.setIcon(img);
            icon.setOpaque(false);
        } else {
            icon.setOpaque(true);
            icon.setBackground(new Color(230, 230, 230));
            icon.setText("NO IMG");
        }

        JLabel text = new JLabel(name);
        text.setFont(new Font("SansSerif", Font.PLAIN, 10));
        text.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(icon);
        card.add(Box.createVerticalStrut(6));
        card.add(text);

        return card;
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
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape clip = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setClip(clip);

        super.paintComponent(g2);

        g2.dispose();
    }
}
}
