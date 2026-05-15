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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class FlappyWild extends JPanel implements KeyListener, MouseListener {

    private GameWindow gameWindow;
    private String     selectedCharacter;

    // ===== Objek game =====
    private Logic bird;
    private List<Pipe> pipes = new ArrayList<>();

    // ===== State game =====
    public enum GameState { COUNTDOWN, PLAYING, PAUSED, GAME_OVER, VICTORY }
    private GameState gameState = GameState.COUNTDOWN;

    // ===== Skor =====
    private int score    = 0;
    private int maxScore = 100;

    // ===== Timer game loop (±60fps) =====
    private Timer gameTimer;

    // ===== Spawn pipa =====
    private int pipeSpawnCounter  = 0;
    private int pipeSpawnInterval = 120; // Frame antar pipa

    // ===== Scroll background =====
    private float bgScrollX = 0;

    // ===== Awan =====
    private float[] cloudX = {100f, 370f, 600f, 200f, 500f};
    private float[] cloudY = { 80f, 155f,  55f, 220f, 110f};

    // ===== Countdown =====
    private int countdownNumber = 3;

    // ===== Tombol UI =====
    private ImageButton btnPause;
    private ImageButton btnContinue;
    private ImageButton btnExit;
    private ImageButton btnBack;

    // Panel transparan penampung tombol overlay
    private JPanel overlayButtons;

    public FlappyWild(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setLayout(null);
        setPreferredSize(new Dimension(GameWindow.WIDTH, GameWindow.HEIGHT));
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);

        setupButtons();

        // Game loop: dipanggil setiap 16ms (~60fps)
        gameTimer = new Timer(16, e -> {
            if (gameState == GameState.PLAYING) updateGame();
            repaint();
        });
    }

    // =========================================================
    //  Setup tombol-tombol UI
    // =========================================================
    private void setupButtons() {
        // ===== Tombol PAUSE (pojok kiri atas, tampil saat playing) =====
        btnPause = new ImageButton("btn_pause", "PAUSE",
            new Color(240, 240, 240), new Color(50, 50, 50));
        btnPause.setBounds(10, 10, 120, 42);
        btnPause.addActionListener(e -> pauseGame());
        btnPause.setVisible(false);
        add(btnPause);

        // ===== Panel transparan untuk tombol overlay =====
        overlayButtons = new JPanel(null);
        overlayButtons.setOpaque(false);
        overlayButtons.setBounds(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
        overlayButtons.setVisible(false);
        add(overlayButtons);

        int btnW = 300, btnH = 60;
        int btnX = (GameWindow.WIDTH - btnW) / 2;

        // ===== Tombol CONTINUE (saat pause) =====
        btnContinue = new ImageButton("btn_continue", "CONTINUE",
            new Color(50, 180, 50), Color.WHITE);
        btnContinue.setBounds(btnX, GameWindow.HEIGHT / 2 + 10, btnW, btnH);
        btnContinue.addActionListener(e -> resumeGame());
        overlayButtons.add(btnContinue);

        // ===== Tombol EXIT (saat pause) =====
        btnExit = new ImageButton("btn_exit", "EXIT",
            new Color(210, 50, 50), Color.WHITE);
        btnExit.setBounds(btnX, GameWindow.HEIGHT / 2 + 80, btnW, btnH);
        btnExit.addActionListener(e -> exitToMenu());
        overlayButtons.add(btnExit);

        // ===== Tombol KEMBALI (game over & victory) =====
        btnBack = new ImageButton("btn_kembali", "KEMBALI",
            new Color(210, 50, 50), Color.WHITE);
        btnBack.setBounds((GameWindow.WIDTH - 260) / 2, GameWindow.HEIGHT / 2 + 130, 260, 60);
        btnBack.addActionListener(e -> exitToMenu());
        overlayButtons.add(btnBack);
    }

    // =========================================================
    //  Kontrol game
    // =========================================================

    // Mulai game baru
    public void startGame(String character) {
        this.selectedCharacter = character;
        resetGame();
        if (!gameTimer.isRunning()) gameTimer.start();
        requestFocusInWindow();
    }

    // Reset semua state ke awal
    private void resetGame() {
        bird  = new Logic(200, GameWindow.HEIGHT / 2 - 30, selectedCharacter);
        pipes.clear();
        score            = 0;
        pipeSpawnCounter = 0;
        bgScrollX        = 0;
        overlayButtons.setVisible(false);
        btnPause.setVisible(false);
        startCountdown();
    }

    // Mulai countdown 3,2,1 di Thread terpisah
    private void startCountdown() {
        gameState       = GameState.COUNTDOWN;
        countdownNumber = 3;

        // Sembunyikan semua tombol overlay
        overlayButtons.setVisible(false);
        btnPause.setVisible(false);

        Thread t = new Thread(() -> {
            try {
                for (int i = 3; i >= 1; i--) {
                    final int n = i;
                    SwingUtilities.invokeLater(() -> {
                        countdownNumber = n;
                        repaint();
                    });
                    Thread.sleep(1000);
                }
                SwingUtilities.invokeLater(() -> {
                    gameState = GameState.PLAYING;
                    btnPause.setVisible(true);
                    repaint();
                });
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });
        t.setDaemon(true);
        t.start();
    }

    // Pause game
    private void pauseGame() {
        if (gameState != GameState.PLAYING) return;
        gameState = GameState.PAUSED;
        // Tampilkan Continue dan Exit, sembunyikan Pause
        btnContinue.setVisible(true);
        btnExit.setVisible(true);
        btnBack.setVisible(false);
        overlayButtons.setVisible(true);
        btnPause.setVisible(false);
        repaint();
    }

    // Lanjutkan game setelah pause (countdown dulu)
    private void resumeGame() {
        overlayButtons.setVisible(false);
        startCountdown();
    }

    // Kembali ke menu
    private void exitToMenu() {
        gameTimer.stop();
        overlayButtons.setVisible(false);
        btnPause.setVisible(false);
        gameWindow.showMenu();
    }

    // =========================================================
    //  Logika update game
    // =========================================================
    private void updateGame() {
        bird.update();

        // Scroll background dan awan
        bgScrollX += 2f;
        if (bgScrollX > GameWindow.WIDTH) bgScrollX = 0;

        for (int i = 0; i < cloudX.length; i++) {
            cloudX[i] -= 0.9f;
            if (cloudX[i] < -160) cloudX[i] = GameWindow.WIDTH + 60f;
        }

        // Spawn pipa
        pipeSpawnCounter++;
        if (pipeSpawnCounter >= pipeSpawnInterval) {
            pipes.add(new Pipe(GameWindow.WIDTH + 10));
            pipeSpawnCounter = 0;
        }

        // Update pipa dan cek interaksi
        List<Pipe> toRemove = new ArrayList<>();
        for (Pipe pipe : pipes) {
            pipe.update();

            // Cek apakah karakter melewati pipa → tambah skor
            if (pipe.isPassed(bird.getX() + bird.getWidth())) {
                score++;
                if (score >= maxScore) { triggerVictory(); return; }
            }

            if (pipe.isOffScreen()) toRemove.add(pipe);

            // Cek tabrakan
            if (pipe.collidesWith(bird.getHitbox())) {
                triggerGameOver(); return;
            }
        }
        pipes.removeAll(toRemove);

        // Karakter jatuh ke bawah layar → game over
        if (bird.getY() + bird.getHeight() > GameWindow.HEIGHT - 80) {
            triggerGameOver();
        }
        // Cegah karakter keluar atas layar
//        if (bird.getY() < 0) {
//            triggerGameOver();
//            return;
//        }
        if (bird.getY() < -bird.getHeight()) bird.setY(-bird.getHeight() + 1);
    }

    private void triggerGameOver() {
        gameState = GameState.GAME_OVER;
        btnContinue.setVisible(false);
        btnExit.setVisible(false);
        btnBack.setVisible(true);
        overlayButtons.setVisible(true);
        btnPause.setVisible(false);
        repaint();
    }

    private void triggerVictory() {
        gameState = GameState.VICTORY;
        btnContinue.setVisible(false);
        btnExit.setVisible(false);
        btnBack.setVisible(true);
        overlayButtons.setVisible(true);
        btnPause.setVisible(false);
        repaint();
    }

    // =========================================================
    //  Rendering (menggambar semua elemen)
    // =========================================================
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        drawBackground(g2);
        drawClouds(g2);

        // Gambar semua pipa
        for (Pipe pipe : pipes) pipe.draw(g2);

        // Gambar tanah di atas pipa agar pipa "tersembunyi" di balik tanah
        drawGround(g2);

        // Gambar karakter
        if (bird != null) bird.draw(g2);

        // Gambar skor
        drawScore(g2);

        // Overlay sesuai state
        switch (gameState) {
            case COUNTDOWN: drawCountdown(g2); break;
            case PAUSED:    drawPauseOverlay(g2); break;
            case GAME_OVER: drawGameOver(g2); break;
            case VICTORY:   drawVictory(g2); break;
        }
    }

    // Background langit
    private void drawBackground(Graphics2D g2) {
        if (AssetManager.has("sky")) {
            g2.drawImage(AssetManager.get("sky"), 0, 0, GameWindow.WIDTH, GameWindow.HEIGHT, null);
        } else {
            GradientPaint gp = new GradientPaint(
                0, 0, new Color(140, 200, 240), // atas
                0, GameWindow.HEIGHT, new Color(180, 230, 255) // bawah
            );
            g2.setPaint(gp);
            g2.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
        }
    }

    // Awan bergerak
    private void drawClouds(Graphics2D g2) {
        for (int i = 0; i < cloudX.length; i++) {
            if (AssetManager.has("cloud")) {
                g2.drawImage(AssetManager.get("cloud"),
                    (int)cloudX[i], (int)cloudY[i], 150, 80, null);
            } else {
                g2.setColor(new Color(255, 255, 255, 190));
                g2.fillOval((int)cloudX[i], (int)cloudY[i], 130, 55);
                g2.fillOval((int)cloudX[i] + 35, (int)cloudY[i] - 28,  80,  55);
                g2.fillOval((int)cloudX[i] + 75, (int)cloudY[i], 75,  45);
            }
        }
    }

    // Tanah di bawah
    private void drawGround(Graphics2D g2) {
        int groundY = GameWindow.HEIGHT - 80;
        if (AssetManager.has("ground")) {
            g2.drawImage(AssetManager.get("ground"), 0, groundY, GameWindow.WIDTH, 80, null);
        } else {
            g2.setColor(new Color(100, 180, 80));
            g2.fillRect(0, groundY, GameWindow.WIDTH, 80);
            g2.setColor(new Color(160, 120, 60));
            g2.fillRect(0, groundY, GameWindow.WIDTH, 20);
            g2.setColor(new Color(140, 100, 50));
            g2.setStroke(new BasicStroke(3));
            for (int i = 0; i < GameWindow.WIDTH + 60; i += 55) {
                int lx = (int)((i - bgScrollX * 2) % (GameWindow.WIDTH + 60));
                g2.drawLine(lx, groundY + 5, lx + 28, groundY + 16);
            }
        }
    }

    // Skor di kiri atas (di sebelah tombol pause)
    private void drawScore(Graphics2D g2) {
        String txt = "SCORE: " + score;
        g2.setFont(new Font("Arial Black", Font.BOLD, 26));
        FontMetrics fm = g2.getFontMetrics();
        int sw = fm.stringWidth(txt);
        // Background putih
        g2.setColor(new Color(255, 255, 255, 210));
        g2.fillRoundRect(160, 10, sw + 22, 46, 12, 12);
        g2.setColor(new Color(180, 180, 180));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(160, 10, sw + 22, 46, 12, 12);
        // Teks
        g2.setColor(new Color(40, 40, 40));
        g2.drawString(txt, 171, 44);
    }

    // Countdown 3, 2, 1
    private void drawCountdown(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 90));
        g2.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);

        int cx = GameWindow.WIDTH / 2;
        int cy = GameWindow.HEIGHT / 2 - 80;

        // Lingkaran latar
        g2.setColor(new Color(255, 255, 255, 200));
        g2.fillOval(cx - 85, cy - 85, 170, 170);
        g2.setColor(new Color(255, 200, 0));
        g2.setStroke(new BasicStroke(7));
        g2.drawOval(cx - 85, cy - 85, 170, 170);

        // Angka
        String num = String.valueOf(countdownNumber);
        g2.setFont(new Font("Arial Black", Font.BOLD, 110));
        FontMetrics fm = g2.getFontMetrics();
        int tx = cx - fm.stringWidth(num) / 2;
        int ty = cy + fm.getAscent() / 2 - 18;
        // Shadow
        g2.setColor(new Color(0, 0, 0, 120));
        g2.drawString(num, tx + 5, ty + 5);
        // Teks
        g2.setColor(new Color(220, 50, 50));
        g2.drawString(num, tx, ty);
    }

    // Overlay saat PAUSE
    private void drawPauseOverlay(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 110));
        g2.fillRect(2, 2, GameWindow.WIDTH, GameWindow.HEIGHT);

        int pw = 420, ph = 230;
        int px = (GameWindow.WIDTH - pw) / 2;
        int py = GameWindow.HEIGHT / 2 - 140;

        if (AssetManager.has("panel_pause")) {
            // Gambar panel dari aset
            g2.drawImage(AssetManager.get("panel_pause"), px, py, pw, ph, null);
        } else {
            // Fallback: kotak putih
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(px, py, pw, ph, 22, 22);
            g2.setColor(new Color(200, 200, 200));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(px, py, pw, ph, 22, 22);
            // Teks
            g2.setFont(new Font("Arial Black", Font.BOLD, 38));
            g2.setColor(new Color(50, 50, 50));
            String t = "GAME DIJEDA";
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(t, px + (pw - fm.stringWidth(t)) / 2, py + 75);
        }
    }

    // Layar GAME OVER
    private void drawGameOver(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 140));
        g2.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);

        int pw = 430, ph = 380;
        int px = (GameWindow.WIDTH - pw) / 2;
        int py = GameWindow.HEIGHT / 2 - 215;
        int cx = GameWindow.WIDTH / 2;

        if (AssetManager.has("panel_gameover")) {
            g2.drawImage(AssetManager.get("panel_gameover"), px, py, pw, ph, null);
            // Teks skor di atas panel aset
            drawScoreOnPanel(g2, cx, py, ph);
        } else {
            // Fallback kotak putih
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(px, py, pw, ph, 22, 22);
            g2.setColor(new Color(200, 200, 200));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(px, py, pw, ph, 22, 22);

            g2.setFont(new Font("Arial Black", Font.BOLD, 60));
            drawCenteredOutline(g2, "GAME",  cx, py + 95,  new Color(220, 50, 50), Color.BLACK);
            drawCenteredOutline(g2, "OVER!", cx, py + 162, new Color(220, 50, 50), Color.BLACK);

            g2.setFont(new Font("Arial Black", Font.BOLD, 26));
            g2.setColor(new Color(80, 80, 80));
            drawCentered(g2, "SKOR:", py + 215);

            g2.setFont(new Font("Arial Black", Font.BOLD, 75));
            drawCenteredOutline(g2, String.valueOf(score), cx, py + 300, new Color(220, 50, 50), Color.BLACK);
        }
    }

    // Layar VICTORY
    private void drawVictory(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 140));
        g2.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);

        int pw = 430, ph = 380;
        int px = (GameWindow.WIDTH - pw) / 2;
        int py = GameWindow.HEIGHT / 2 - 215;
        int cx = GameWindow.WIDTH / 2;

        if (AssetManager.has("panel_victory")) {
            g2.drawImage(AssetManager.get("panel_victory"), px, py, pw, ph, null);
            drawScoreOnPanel(g2, cx, py, ph);
        } else {
            // Fallback kotak putih
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(px, py, pw, ph, 22, 22);
            g2.setColor(new Color(200, 180, 0));
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(px, py, pw, ph, 22, 22);

            g2.setFont(new Font("Arial Black", Font.BOLD, 70));
            drawCenteredOutline(g2, "VICTORY!", cx, py + 115, new Color(255, 180, 0), Color.BLACK);

            g2.setFont(new Font("Arial Black", Font.BOLD, 26));
            g2.setColor(new Color(80, 80, 80));
            drawCentered(g2, "SKOR:", py + 198);

            g2.setFont(new Font("Arial Black", Font.BOLD, 85));
            drawCenteredOutline(g2, String.valueOf(score), cx, py + 300, new Color(255, 180, 0), Color.BLACK);
        }
    }

    private void drawScoreOnPanel(Graphics2D g2, int cx, int panelY, int panelH) {
        g2.setFont(new Font("Arial Black", Font.BOLD, 28));
        g2.setColor(new Color(80, 80, 80));
        drawCentered(g2, "SKOR:", panelY + panelH / 2 + 10);

        g2.setFont(new Font("Arial Black", Font.BOLD, 80));
        drawCenteredOutline(g2, String.valueOf(score),
            cx, panelY + panelH / 2 + 100, new Color(220, 50, 50), Color.BLACK);
    }

    // ===== Helper teks =====
    private void drawCenteredOutline(Graphics2D g2, String text, int cx, int y, Color fill, Color outline) {
        FontMetrics fm = g2.getFontMetrics();
        int tx = cx - fm.stringWidth(text) / 2;
        g2.setColor(outline);
        for (int dx = -3; dx <= 3; dx++)
            for (int dy = -3; dy <= 3; dy++)
                if (dx != 0 || dy != 0) g2.drawString(text, tx + dx, y + dy);
        g2.setColor(fill);
        g2.drawString(text, tx, y);
    }

    private void drawCentered(Graphics2D g2, String text, int y) {
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(text, (GameWindow.WIDTH - fm.stringWidth(text)) / 2, y);
    }

    // =========================================================
    //  Input keyboard & mouse
    // =========================================================
    @Override public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && gameState == GameState.PLAYING)
            bird.jump();
    }
    @Override public void mouseClicked(MouseEvent e) {
        if (gameState == GameState.PLAYING) bird.jump();
    }
    @Override public void keyReleased(KeyEvent e)  {}
    @Override public void keyTyped(KeyEvent e)     {}
    @Override public void mousePressed(MouseEvent e)  {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e)  {}
    @Override public void mouseExited(MouseEvent e)   {}

    // =========================================================
    //  Inner class: ImageButton
    //  Tombol yang menampilkan gambar PNG, fallback ke teks warna
    // =========================================================
    class ImageButton extends JButton {
        private String assetKey;
        private String label;
        private Color  bgColor;
        private Color  textColor;

        public ImageButton(String assetKey, String label, Color bgColor, Color textColor) {
            this.assetKey  = assetKey;
            this.label     = label;
            this.bgColor   = bgColor;
            this.textColor = textColor;
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            if (AssetManager.has(assetKey)) {
                // Gambar aset PNG
                g2.drawImage(AssetManager.get(assetKey), 0, 0, getWidth(), getHeight(), null);
            } else {
                // Fallback: tombol warna solid
                g2.setColor(bgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.setColor(bgColor.darker());
                g2.setStroke(new BasicStroke(4));
                g2.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 16, 16);
                g2.setFont(new Font("Arial Black", Font.BOLD, 24));
                g2.setColor(textColor);
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(label,
                    (getWidth()  - fm.stringWidth(label)) / 2,
                    (getHeight() + fm.getAscent() - fm.getDescent()) / 2
                );
            }
            g2.dispose();
        }
        @Override protected void paintBorder(Graphics g) {}
    }
}