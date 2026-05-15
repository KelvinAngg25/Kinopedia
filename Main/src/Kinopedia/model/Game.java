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

import Kinopedia.PilihanBundle.BundleValorant;
import Kinopedia.PilihanBundle.BundlePUBG;
import Kinopedia.PilihanBundle.BundleCODM;
import Kinopedia.PilihanBundle.BundleFF;
import Kinopedia.PilihanBundle.BundleML;
import Kinopedia.PilihanBundle.BundleEfootball;
import Kinopedia.PilihanBundle.BundleSteam;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Game extends JFrame {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame dummyBack = new JFrame();
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
        setSize(470, 844);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(root);

        // ===== TOP BAR =====
        JButton backBtn = new JButton("< Kembali");
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
        backBtn.setHorizontalAlignment(SwingConstants.LEFT);
        backBtn.addActionListener(e -> goBack());

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);
        top.add(backBtn, BorderLayout.WEST);
        root.add(top, BorderLayout.NORTH);

        // ===== CONTENT =====
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(5, 0, 5, 0));
        root.add(content, BorderLayout.CENTER);

        content.add(makeTitle("First Person Shooter (FPS)"));
         content.add(Box.createVerticalStrut(20));
        content.add(makeIconRow(new String[]{"Valorant", "PUBG Mobile", "CODM", "Free Fire"}));

        content.add(Box.createVerticalStrut(8));
        content.add(makeTitle("Mobile Arena"));
        content.add(makeIconRow(new String[]{"Mobile Legend"}));

        content.add(Box.createVerticalStrut(8));
        content.add(makeTitle("Sports"));
        content.add(makeIconRow(new String[]{"eFootball"}));

        content.add(Box.createVerticalStrut(8));
        content.add(makeTitle("Application"));
        content.add(makeIconRow(new String[]{"Steam"}));

        // ===== FOOTER LOGO =====
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(Color.WHITE);
        footer.setBorder(new EmptyBorder(8, 0, 0, 0));

        ImageIcon logoImg = loadIcon("LogoKinopedia.png", 50, 50);
        JLabel logoLabel = (logoImg != null) ? new JLabel(logoImg) : new JLabel("Kinopedia");
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        footer.add(logoLabel, BorderLayout.CENTER);
        root.add(footer, BorderLayout.SOUTH);
    }

    private JLabel makeTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(new EmptyBorder(6, 0, 4, 0));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        return label;
    }

    private JPanel iconRow(String[] names) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row.setBackground(Color.WHITE);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        row.setPreferredSize(new Dimension(430, 110));

        for (String name : names) {
            row.add(makeIconCard(name));
        }
        return row;
    }
    private JPanel makeIconCard(String name) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(100, 105));
        card.setMaximumSize(new Dimension(100, 105));
        card.setMinimumSize(new Dimension(100, 105));

        String fileName = getFileName(name);
        ImageIcon img = loadIcon(fileName, 70, 70);

        JLabel iconLabel = new JLabel();
        iconLabel.setPreferredSize(new Dimension(70, 70));
        iconLabel.setMaximumSize(new Dimension(70, 70));
        iconLabel.setMinimumSize(new Dimension(70, 70));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        if (img != null) {
            iconLabel.setIcon(img);
        } else {
            iconLabel.setOpaque(true);
            iconLabel.setBackground(new Color(230, 230, 230));
            iconLabel.setText("NO IMG");
        }

        iconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        iconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                openBundlePage(name);
            }
        });

        String fileName = "";
        if (name.equals("Valorant")) fileName = "Valorantt.png";
        if (name.equals("PUBG Mobile")) fileName = "PUBG.png";
        if (name.equals("CODM")) fileName = "CODMmobile.jpg";
        if (name.equals("Free Fire")) fileName = "FreeFire.jpg";
        if (name.equals("eFootball")) fileName = "efootball.jpg";
        if (name.equals("Mobile Legend")) fileName = "ML.png";
        if (name.equals("Steam")) fileName = "Steam.png";

        JLabel textLabel = new JLabel(name, SwingConstants.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel.setMaximumSize(new Dimension(100, 18));
        textLabel.setPreferredSize(new Dimension(100, 18));

        card.add(iconWrapper);
        card.add(Box.createVerticalStrut(4));
        card.add(textLabel);

        return card;
    }

    private String getFileName(String name) {
        if (name.equals("Valorant")) return "Valorantt.png";
        if (name.equals("PUBG Mobile")) return "PUBG.png";
        if (name.equals("CODM")) return "CODMmobile.jpg";
        if (name.equals("Free Fire")) return "FreeFire.jpg";
        if (name.equals("Mobile Legend")) return "ML.png";
        if (name.equals("eFootball")) return "efootball.png";
        if (name.equals("Steam")) return "Steam.png";

        System.out.println("Game tidak dikenali: " + name);
        return "";
    }

    private void openBundlePage(String name) {
        if (name.equals("Valorant")) {
            new BundleValorant(this).setVisible(true);
            setVisible(false);
        } else if (name.equals("PUBG Mobile")) {
            new BundlePUBG(this).setVisible(true);
            setVisible(false);
        } else if (name.equals("CODM")) {
            new BundleCODM(this).setVisible(true);
            setVisible(false);
        } else if (name.equals("Free Fire")) {
            new BundleFF(this).setVisible(true);
            setVisible(false);
        } else if (name.equals("Mobile Legend")) {
            new BundleML(this).setVisible(true);
            setVisible(false);
        } else if (name.equals("eFootball")) {
            new BundleEfootball(this).setVisible(true);
            setVisible(false);
        } else if (name.equals("Steam")) {
            new BundleSteam(this).setVisible(true);
            setVisible(false);
        }
    }

    private ImageIcon loadIcon(String fileName, int w, int h) {
        if (fileName == null || fileName.equals("")) return null;

        java.net.URL url = getClass().getResource("/Kinopedia/model/IMAGESS/" + fileName);
        if (url == null) return null;

        Image scaled = new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private void goBack() {
        dispose();
        if (backTo != null) {
            backTo.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game(null).setVisible(true));
    }
}
