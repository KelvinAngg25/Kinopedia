/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.FllapyWild;

/**
 *
 * @author Fabiola
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class MainPanel extends JPanel {

    private GameWindow gameWindow;
    private String selectedCharacter = "PLUPPY"; // Karakter default

    // Panel karakter agar bisa di-klik
    private CharacterCard[] characterCards;
    private String[] characterNames = {"BOYO", "PLUPPY", "KWEK"};

    // Warna karakter (representasi visual sederhana)
//    private Color[] characterColors = {
//        new Color(100, 180, 255),  // BOYO - biru
//        new Color(255, 255, 255),  // PLUPPY - putih (penguin)
//        new Color(255, 200, 50)    // KWEK - kuning
//    };

    // Animasi awan bergerak
    private float[] cloudX = {50, 300, 550, 150, 450};
    private float[] cloudY = {80, 120, 60, 200, 170};
    private Timer animationTimer;

    public MainPanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setLayout(null); // Kita atur posisi manual
        setPreferredSize(new Dimension(GameWindow.WIDTH, GameWindow.HEIGHT));

        // Animasi awan bergerak pelan
        animationTimer = new Timer(30, e -> {
            for (int i = 0; i < cloudX.length; i++) {
                cloudX[i] += 0.5f;
                if (cloudX[i] > GameWindow.WIDTH + 100) {
                    cloudX[i] = -150;
                }
            }
            repaint();
        });
        animationTimer.start();

        setupUI();
    }

    private void setupUI() {
        // Buat kartu karakter
        characterCards = new CharacterCard[3];
        int cardWidth  = 170;
        int cardHeight = 200;
        int startX     = 50;
        int cardY      = 500;
        int gap        = 30;

//        for (int i = 0; i < 3; i++) {
//            final int idx = i;
//            characterCards[i] = new CharacterCard(
//                characterNames[i],
////                characterColors[i],
//                characterNames[i].equals(selectedCharacter)
//            );
//            int xPos = startX + i * (cardWidth + gap);
//            characterCards[i].setBounds(xPos, cardY, cardWidth, cardHeight);
//
//            // Klik kartu = pilih karakter
//            characterCards[i].addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    selectCharacter(idx);
//                }
//            });
//            add(characterCards[i]);
//        }

        // Tombol MULAI
        JButton btnMulai = createStyledButton("MULAI", new Color(220, 50, 50), Color.WHITE);
        btnMulai.setBounds(210, 750, 300, 70);
        btnMulai.addActionListener(e -> gameWindow.startGame(selectedCharacter));
        add(btnMulai);

        // Tombol EXIT
        JButton btnExit = createStyledButton("EXIT", new Color(80, 80, 80), Color.WHITE);
        btnExit.setBounds(210, 840, 300, 70);
        btnExit.addActionListener(e -> System.exit(0));
        add(btnExit);
    }

    // Pilih karakter dan update tampilan kartu
    private void selectCharacter(int idx) {
        selectedCharacter = characterNames[idx];
        for (int i = 0; i < characterCards.length; i++) {
            characterCards[i].setSelected(i == idx);
            characterCards[i].repaint();
        }
    }

    // Reset pilihan karakter ke default saat kembali ke menu
    public void resetMenu() {
        selectedCharacter = "PLUPPY";
        if (characterCards != null) {
            for (int i = 0; i < characterCards.length; i++) {
                characterCards[i].setSelected(characterNames[i].equals(selectedCharacter));
                characterCards[i].repaint();
            }
        }
    }

    // Buat tombol dengan style custom
    private JButton createStyledButton(String text, Color bgColor, Color textColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Background tombol
                g2.setColor(bgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                // Border gelap
                g2.setColor(bgColor.darker());
                g2.setStroke(new BasicStroke(4));
                g2.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 20, 20);
                // Teks
                g2.setColor(textColor);
                g2.setFont(new Font("Arial Black", Font.BOLD, 28));
                FontMetrics fm = g2.getFontMetrics();
                int tx = (getWidth() - fm.stringWidth(text)) / 2;
                int ty = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(text, tx, ty);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) { /* Jangan gambar border default */ }
        };
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background langit gradien
        GradientPaint skyGrad = new GradientPaint(
            0, 0, new Color(135, 206, 250),
            0, GameWindow.HEIGHT, new Color(200, 235, 255)
        );
        g2.setPaint(skyGrad);
        g2.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);

        // Gambar awan
        drawClouds(g2);

        // Panel kartu putih di bagian bawah (area menu)
        g2.setColor(new Color(255, 255, 255, 180));
        g2.fillRoundRect(30, 440, GameWindow.WIDTH - 60, 550, 30, 30);

        // Judul "FLAPPY"
        g2.setFont(new Font("Arial Black", Font.BOLD, 90));
        drawOutlineText(g2, "FLAPPY", 75, 200, new Color(255, 165, 0), Color.BLACK);

        // Judul "WILD"
        g2.setFont(new Font("Arial Black", Font.BOLD, 90));
        drawOutlineText(g2, "WILD", 190, 300, new Color(220, 50, 50), Color.BLACK);

        // Teks "PILIH KARAKTER"
        g2.setFont(new Font("Arial Black", Font.BOLD, 28));
        g2.setColor(new Color(60, 60, 60));
        drawCenteredText(g2, "PILIH KARAKTER", 490);

        // Garis pemisah di bawah teks pilih karakter
        g2.setColor(new Color(200, 200, 200));
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(80, 505, GameWindow.WIDTH - 80, 505);
    }

    // Gambar awan-awan
    private void drawClouds(Graphics2D g2) {
        g2.setColor(new Color(255, 255, 255, 200));
        for (int i = 0; i < cloudX.length; i++) {
            drawCloud(g2, (int) cloudX[i], (int) cloudY[i]);
        }
    }

    // Gambar satu awan
    private void drawCloud(Graphics2D g2, int x, int y) {
        g2.fillOval(x, y, 120, 60);
        g2.fillOval(x + 30, y - 30, 90, 60);
        g2.fillOval(x + 70, y, 80, 50);
    }

    // Gambar teks dengan outline
    private void drawOutlineText(Graphics2D g2, String text, int x, int y, Color fill, Color outline) {
        g2.setColor(outline);
        // Gambar outline dengan offset
        for (int dx = -4; dx <= 4; dx++) {
            for (int dy = -4; dy <= 4; dy++) {
                if (dx != 0 || dy != 0) {
                    g2.drawString(text, x + dx, y + dy);
                }
            }
        }
        g2.setColor(fill);
        g2.drawString(text, x, y);
    }

    // Gambar teks di tengah horizontal
    private void drawCenteredText(Graphics2D g2, String text, int y) {
        FontMetrics fm = g2.getFontMetrics();
        int x = (GameWindow.WIDTH - fm.stringWidth(text)) / 2;
        g2.drawString(text, x, y);
    }

    // =========================================================
    // Inner class: Kartu karakter yang bisa diklik
    // =========================================================
    class CharacterCard extends JPanel {
        private String name;
        private Color  bodyColor;
        private boolean selected;

        public CharacterCard(String name, Color bodyColor, boolean selected) {
            this.name      = name;
            this.bodyColor = bodyColor;
            this.selected  = selected;
            setOpaque(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Border kartu - kuning jika dipilih, abu jika tidak
            if (selected) {
                g2.setColor(new Color(255, 200, 0));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(255, 140, 0));
                g2.setStroke(new BasicStroke(4));
                g2.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 20, 20);
            } else {
                g2.setColor(new Color(220, 220, 220, 200));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(180, 180, 180));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 20, 20);
            }

            // Gambar karakter (representasi sederhana dengan bentuk)
//            drawCharacter(g2, name, bodyColor);

            // Nama karakter
            g2.setFont(new Font("Arial Black", Font.BOLD, 16));
            g2.setColor(new Color(50, 50, 50));
            FontMetrics fm = g2.getFontMetrics();
            int tx = (getWidth() - fm.stringWidth(name)) / 2;
            g2.drawString(name, tx, getHeight() - 15);

            g2.dispose();
        }

        // Gambar karakter pixel art sederhana
//        private void drawCharacter(Graphics2D g2, String charName, Color color) {
//            int cx = getWidth() / 2;
//            int cy = 90;
//
//            switch (charName) {
//                case "PLUPPY": // Penguin hitam-putih
//                    // Badan hitam
//                    g2.setColor(Color.BLACK);
//                    g2.fillOval(cx - 35, cy - 50, 70, 80);
//                    // Perut putih
//                    g2.setColor(Color.WHITE);
//                    g2.fillOval(cx - 20, cy - 30, 40, 50);
//                    // Paruh
//                    g2.setColor(new Color(255, 165, 0));
//                    int[] bx = {cx - 10, cx + 10, cx};
//                    int[] by = {cy - 28, cy - 28, cy - 18};
//                    g2.fillPolygon(bx, by, 3);
//                    // Mata
//                    g2.setColor(Color.WHITE);
//                    g2.fillOval(cx - 18, cy - 48, 14, 14);
//                    g2.fillOval(cx + 4, cy - 48, 14, 14);
//                    g2.setColor(Color.BLACK);
//                    g2.fillOval(cx - 14, cy - 45, 8, 8);
//                    g2.fillOval(cx + 8, cy - 45, 8, 8);
//                    // Sayap
//                    g2.setColor(Color.BLACK);
//                    g2.fillOval(cx - 50, cy - 20, 20, 40);
//                    g2.fillOval(cx + 30, cy - 20, 20, 40);
//                    break;
//
//                case "BOYO": // Karakter biru (mirip pokemon air)
//                    // Badan biru
//                    g2.setColor(new Color(80, 160, 230));
//                    g2.fillOval(cx - 32, cy - 45, 64, 70);
//                    // Perut terang
//                    g2.setColor(new Color(180, 220, 255));
//                    g2.fillOval(cx - 18, cy - 25, 36, 42);
//                    // Mata
//                    g2.setColor(Color.WHITE);
//                    g2.fillOval(cx - 18, cy - 42, 14, 14);
//                    g2.fillOval(cx + 4, cy - 42, 14, 14);
//                    g2.setColor(Color.BLACK);
//                    g2.fillOval(cx - 14, cy - 39, 8, 8);
//                    g2.fillOval(cx + 8, cy - 39, 8, 8);
//                    // Ekor
//                    g2.setColor(new Color(60, 130, 200));
//                    g2.fillOval(cx - 42, cy - 10, 20, 30);
//                    break;
//
//                case "KWEK": // Bebek kuning
//                    // Badan kuning
//                    g2.setColor(new Color(255, 210, 50));
//                    g2.fillOval(cx - 30, cy - 40, 60, 65);
//                    // Paruh oranye
//                    g2.setColor(new Color(255, 130, 0));
//                    int[] kbx = {cx - 12, cx + 12, cx};
//                    int[] kby = {cy - 22, cy - 22, cy - 10};
//                    g2.fillPolygon(kbx, kby, 3);
//                    // Mata
//                    g2.setColor(Color.WHITE);
//                    g2.fillOval(cx - 16, cy - 40, 12, 12);
//                    g2.fillOval(cx + 4, cy - 40, 12, 12);
//                    g2.setColor(Color.BLACK);
//                    g2.fillOval(cx - 13, cy - 37, 7, 7);
//                    g2.fillOval(cx + 7, cy - 37, 7, 7);
//                    // Jambul
//                    g2.setColor(new Color(255, 180, 0));
//                    g2.fillOval(cx - 8, cy - 55, 16, 20);
//                    break;
//            }
//        }
    }
}