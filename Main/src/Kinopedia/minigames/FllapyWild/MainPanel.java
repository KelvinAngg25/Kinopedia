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
    private String selectedCharacter = "PLUPPY";
    private String[] characterNames  = {"BOYO", "PLUPPY", "KWEK"};

    // Posisi & ukuran kartu (dihitung manual, bukan JPanel)
    private Rectangle[] cardBounds = new Rectangle[3];

    private float[] cloudX = {50, 300, 450, 150, 380};
    private float[] cloudY = {80, 120,  60, 200, 170};
    private Timer animationTimer;

    public MainPanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setLayout(null);
        setPreferredSize(new Dimension(GameWindow.WIDTH, GameWindow.HEIGHT));

        // Hitung posisi kartu
        int cardW    = 120, cardH = 150;
        int gap      = 15;
        int totalW   = 3 * cardW + 2 * gap;
        int startX   = (GameWindow.WIDTH - totalW) / 2;
        int cardY    = 435;
        for (int i = 0; i < 3; i++) {
            cardBounds[i] = new Rectangle(startX + i * (cardW + gap), cardY, cardW, cardH);
        }

        // Animasi awan
        animationTimer = new Timer(30, e -> {
            for (int i = 0; i < cloudX.length; i++) {
                cloudX[i] += 0.5f;
                if (cloudX[i] > GameWindow.WIDTH + 100) cloudX[i] = -150;
            }
            repaint();
        });
        animationTimer.start();

        // Deteksi klik kartu via MouseListener langsung di panel ini
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < cardBounds.length; i++) {
                    if (cardBounds[i].contains(e.getPoint())) {
                        selectedCharacter = characterNames[i];
                        repaint();
                        return;
                    }
                }
            }
        });

        setupButtons();
    }

    private void setupButtons() {
        JButton btnMulai = createStyledButton("MULAI", new Color(220, 50, 50), Color.WHITE);
        btnMulai.setBounds(85, 620, 300, 70);
        btnMulai.addActionListener(e -> gameWindow.startGame(selectedCharacter));
        add(btnMulai);

        JButton btnExit = createStyledButton("EXIT", new Color(80, 80, 80), Color.WHITE);
        btnExit.setBounds(85, 710, 300, 70);
        btnExit.addActionListener(e -> System.exit(0));
        add(btnExit);
    }

    public void resetMenu() {
        selectedCharacter = "PLUPPY";
        repaint();
    }

    private JButton createStyledButton(String text, Color bgColor, Color textColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(bgColor.darker());
                g2.setStroke(new BasicStroke(4));
                g2.drawRoundRect(2, 2, getWidth()-4, getHeight()-4, 20, 20);
                g2.setColor(textColor);
                g2.setFont(new Font("Arial Black", Font.BOLD, 28));
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(text,
                    (getWidth() - fm.stringWidth(text)) / 2,
                    (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
                g2.dispose();
            }
            @Override protected void paintBorder(Graphics g) {}
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

        // Background langit
        GradientPaint skyGrad = new GradientPaint(
            0, 0, new Color(135, 206, 250),
            0, GameWindow.HEIGHT, new Color(200, 235, 255));
        g2.setPaint(skyGrad);
        g2.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);

        drawClouds(g2);

        // Panel putih bawah
        g2.setColor(new Color(255, 255, 255, 180));
        g2.fillRoundRect(20, 390, GameWindow.WIDTH - 40, GameWindow.HEIGHT - 390, 30, 30);

        // Judul
        g2.setFont(new Font("Arial Black", Font.BOLD, 72));
        drawOutlineText(g2, "FLAPPY", 30, 180, new Color(255, 165, 0), Color.BLACK);
        drawOutlineText(g2, "WILD",  130, 260, new Color(220,  50, 50), Color.BLACK);

        // Teks pilih karakter
        g2.setFont(new Font("Arial Black", Font.BOLD, 22));
        g2.setColor(new Color(60, 60, 60));
        drawCenteredText(g2, "PILIH KARAKTER", 418);

        g2.setColor(new Color(200, 200, 200));
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(50, 425, GameWindow.WIDTH - 50, 425);

        // Gambar kartu karakter
        for (int i = 0; i < 3; i++) {
            drawCard(g2, i);
        }
    }

    private void drawCard(Graphics2D g2, int i) {
        Rectangle r     = cardBounds[i];
        String    name  = characterNames[i];
        boolean   sel   = name.equals(selectedCharacter);

        // Background kartu
        if (sel) {
            g2.setColor(new Color(255, 200, 0));
            g2.fillRoundRect(r.x, r.y, r.width, r.height, 20, 20);
            g2.setColor(new Color(255, 140, 0));
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(r.x+2, r.y+2, r.width-4, r.height-4, 20, 20);
        } else {
            g2.setColor(new Color(220, 220, 220, 230));
            g2.fillRoundRect(r.x, r.y, r.width, r.height, 20, 20);
            g2.setColor(new Color(180, 180, 180));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(r.x+1, r.y+1, r.width-2, r.height-2, 20, 20);
        }

        // Gambar preview karakter (aset atau fallback)
        String previewKey = name.equals("BOYO")   ? "buaya_idle"   :
                            name.equals("PLUPPY") ? "pinguin_idle" :
                                                    "bebek_idle";
        int imgPad = 10;
        int imgSize = r.width - imgPad * 2;
        int imgY    = r.y + imgPad;

        if (AssetManager.has(previewKey)) {
            g2.drawImage(AssetManager.get(previewKey),
                r.x + imgPad, imgY, imgSize, imgSize, null);
        } else {
            // Fallback: gambar sederhana sesuai karakter
            drawCharacterFallback(g2, name, r.x + r.width/2, imgY + imgSize/2, imgSize/2);
        }

        // Nama karakter
        g2.setFont(new Font("Arial Black", Font.BOLD, 13));
        g2.setColor(sel ? new Color(100, 60, 0) : new Color(60, 60, 60));
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(name,
            r.x + (r.width - fm.stringWidth(name)) / 2,
            r.y + r.height - 8);
    }

    private void drawCharacterFallback(Graphics2D g2, String name, int cx, int cy, int r) {
        switch (name) {
            case "PLUPPY":
                g2.setColor(Color.BLACK);      g2.fillOval(cx-r, cy-r, r*2, r*2);
                g2.setColor(Color.WHITE);       g2.fillOval(cx-r/2, cy-r/3, r, r+r/3);
                g2.setColor(new Color(255,165,0));
                g2.fillPolygon(new int[]{cx-r/4,cx+r/4,cx+r/2},
                               new int[]{cy-r/3,cy-r/3,cy-r/6}, 3);
                break;
            case "BOYO":
                g2.setColor(new Color(80,160,230)); g2.fillOval(cx-r, cy-r, r*2, r*2);
                g2.setColor(new Color(180,220,255)); g2.fillOval(cx-r/2, cy-r/3, r, r+r/3);
                break;
            case "KWEK":
                g2.setColor(new Color(255,210,50)); g2.fillOval(cx-r, cy-r, r*2, r*2);
                g2.setColor(new Color(255,130,0));
                g2.fillPolygon(new int[]{cx-r/4,cx+r/4,cx+r/2},
                               new int[]{cy-r/3,cy-r/3,cy-r/6}, 3);
                break;
        }
    }

    private void drawClouds(Graphics2D g2) {
        g2.setColor(new Color(255, 255, 255, 200));
        for (int i = 0; i < cloudX.length; i++) {
            int x = (int) cloudX[i], y = (int) cloudY[i];
            g2.fillOval(x, y, 120, 60);
            g2.fillOval(x+30, y-30, 90, 60);
            g2.fillOval(x+70, y, 80, 50);
        }
    }

    private void drawOutlineText(Graphics2D g2, String text, int x, int y, Color fill, Color outline) {
        g2.setColor(outline);
        for (int dx = -4; dx <= 4; dx++)
            for (int dy = -4; dy <= 4; dy++)
                if (dx != 0 || dy != 0) g2.drawString(text, x+dx, y+dy);
        g2.setColor(fill);
        g2.drawString(text, x, y);
    }

    private void drawCenteredText(Graphics2D g2, String text, int y) {
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(text, (GameWindow.WIDTH - fm.stringWidth(text)) / 2, y);
    }
}