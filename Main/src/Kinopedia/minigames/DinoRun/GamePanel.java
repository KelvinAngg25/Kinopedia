/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.DinoRun;

import Kinopedia.Session;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener {
    Kinopedia.DataUser user = Session.getInstance().getCurrentUser();
    private Image dinoImg;
    private Image cactusImg;
    private Image birdImg;

    // ===== Tipe obstacle =====
    enum ObstacleType { CACTUS_SMALL, CACTUS_BIG, BIRD }

    // ===== Kelas obstacle =====
    static class Obstacle {

        int x, y, width, height;

        ObstacleType type;

        boolean passed;

        Obstacle(int x, int y, int w, int h, ObstacleType t) {

            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
            this.type = t;

            passed = false;
        }

        Rectangle getBounds() {

            return new Rectangle(
                x + 4,
                y + 4,
                width - 8,
                height - 8
            );
        }
    }

    // ===== Konstanta =====
    private static final int GROUND_Y   = 600;
    private static final int DINO_X     = 60;
    private static final int GRAVITY    = 1;
    private static final int JUMP_FORCE = -22;
    public  static final int WIN_SCORE  = 100;
    private static final int INIT_SPEED = 6;

    // ===== Field =====
    private GameFrame      frame;
    private int            dinoY, dinoVY;
    private boolean        onGround;
    private int            dinoLeg, legTick;
    private List<Obstacle> obstacles;
    private Random         random;
    private int            obstacleTimer, obstacleInterval;
    private int            score, speed;
    private boolean        running, paused;
    private Timer          gameTimer;
    private int            tickCount;
    private int            challengeLevel;

    public GamePanel(GameFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(GameFrame.WIDTH, GameFrame.HEIGHT));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        random    = new Random();
        obstacles = new ArrayList<Obstacle>();

        try {
            dinoImg = new ImageIcon(
                getClass().getResource("/Kinopedia/minigames/DinoRun/Asset/Dino.png")
            ).getImage();
            cactusImg = new ImageIcon(
                getClass().getResource("/Kinopedia/minigames/DinoRun/Asset/KaktusBesar.png")
            ).getImage();
            birdImg = new ImageIcon(
                getClass().getResource("/Kinopedia/minigames/DinoRun/Asset/Burung.png")
            ).getImage();
        } catch (Exception e) {
            System.out.println("Gagal memuat aset: " + e);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getX() >= 360 && e.getX() <= 450
                        && e.getY() >= 30  && e.getY() <= 70) {
                    frame.triggerPause(score);
                } else {
                    jump();
                }
            }
        });

        gameTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (running && !paused) {
                    update();
                    repaint();
                }
            }
        });
    }

    // ===== Kontrol game =====

    public void resetGame() {
        dinoY            = GROUND_Y - 80;
        dinoVY           = 0;
        onGround         = true;
        dinoLeg          = 0;
        legTick          = 0;
        score            = 0;
        speed            = INIT_SPEED;
        tickCount        = 0;
        obstacles.clear();
        obstacleTimer    = 0;
        obstacleInterval = 80;
        running          = false;
        paused           = false;
        challengeLevel   = 1;
    }

    public void startGame() {
        running = true;
        paused  = false;
        gameTimer.start();
        requestFocusInWindow();
    }

    public void pauseGame() {
        paused = true;
        gameTimer.stop();
    }

    public void resumeGame() {
        paused  = false;
        running = true;
        gameTimer.start();
        requestFocusInWindow();
    }

    private void jump() {
        if (onGround && running && !paused) {
            dinoVY   = JUMP_FORCE;
            onGround = false;
        }
    }

    // ===== Update loop =====

    private void update() {
        tickCount++;

        // Fisika dino
        dinoVY += GRAVITY;
        dinoY  += dinoVY;
        if (dinoY >= GROUND_Y - 80) {
            dinoY    = GROUND_Y - 80;
            dinoVY   = 0;
            onGround = true;
        }

        // Animasi kaki
        legTick++;
        if (legTick >= 8) {
            legTick = 0;
            dinoLeg = 1 - dinoLeg;
        }

        // Naikkan kecepatan setiap 20 poin
        // ===== Speed naik setiap 5 score =====
        challengeLevel = (score / 5) + 1;

        if (challengeLevel > 100) {
            challengeLevel = 100;
        }

        // Speed bertambah tiap 5 score
        speed = INIT_SPEED + challengeLevel;

        // Spawn obstacle makin cepat
        obstacleInterval = 90 - challengeLevel;

        if (obstacleInterval < 35) {
            obstacleInterval = 35;
        }

        // Spawn obstacle
        obstacleTimer++;

        if (obstacleTimer >= obstacleInterval) {

            obstacleTimer = 0;

            spawnObstacle();
        }

        // Gerakkan & hapus obstacle keluar layar
        List<Obstacle> toRemove = new ArrayList<Obstacle>();

        for (Obstacle ob : obstacles) {

            ob.x -= speed;

            // Jika obstacle berhasil dilewati
            if (!ob.passed && ob.x + ob.width < DINO_X) {

                ob.passed = true;

                score++;

                // Level challenge mengikuti score
                challengeLevel = score;

                if (challengeLevel > 100) {
                    challengeLevel = 100;
                }

                // Menang setelah melewati 100 obstacle
                if (score >= WIN_SCORE) {

                    running = false;
                    gameTimer.stop();

                    frame.triggerVictory(score);

                    return;
                }
            }

            // Hapus obstacle keluar layar
            if (ob.x + ob.width < 0) {
                toRemove.add(ob);
            }
        }

        obstacles.removeAll(toRemove);

        // Deteksi tabrakan
        Rectangle dinoRect = new Rectangle(DINO_X + 8, dinoY + 4, 60, 68);
        for (Obstacle ob : obstacles) {
            if (dinoRect.intersects(ob.getBounds())) {
                running = false;
                gameTimer.stop();
                frame.triggerGameOver(score);
                return;
            }
        }
    }

    private void spawnObstacle() {

        int chance = random.nextInt(100);

        // ===== 25% KAKTUS KECIL =====
        if (chance < 25) {

            int h;

            // Tinggi cactus random
            if (random.nextBoolean()) {
                h = 55;
            } else {
                h = 75;
            }

            obstacles.add(
                new Obstacle(
                    GameFrame.WIDTH,
                    GROUND_Y - h,
                    30,
                    h,
                    ObstacleType.CACTUS_SMALL
                )
            );
        }

        // ===== 25% KAKTUS BESAR =====
        else if (chance < 50) {

            obstacles.add(
                new Obstacle(
                    GameFrame.WIDTH,
                    GROUND_Y - 90,
                    40,
                    90,
                    ObstacleType.CACTUS_BIG
                )
            );
        }

        // ===== 50% BURUNG =====
        else {

            int flyY =
                    GROUND_Y - 250 + random.nextInt(120);

            obstacles.add(
                new Obstacle(
                    GameFrame.WIDTH,
                    flyY,
                    60,
                    40,
                    ObstacleType.BIRD
                )
            );
        }
    }

    // ===== Render =====

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        // Skor tengah atas
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Courier New", Font.BOLD, 38));
        String sc = String.format("%03d", score);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(sc, (GameFrame.WIDTH - fm.stringWidth(sc)) / 2, 90);

        // Tombol PAUSE
        g2d.setColor(Color.WHITE);
        g2d.fillRect(360, 32, 88, 38);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(360, 32, 88, 38);
        g2d.setFont(new Font("Courier New", Font.BOLD, 14));
        g2d.drawString("PAUSE", 375, 57);

        // Garis tanah
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(0, GROUND_Y, GameFrame.WIDTH, GROUND_Y);

        // Dino
        drawDino(g2d, DINO_X, dinoY);

        // Obstacles
        for (Obstacle ob : obstacles) {
            if (ob.type == ObstacleType.BIRD) {
                drawBird(g2d, ob.x, ob.y);
            } else {
                drawCactus(g2d, ob.x, ob.y, ob.height, ob.type == ObstacleType.CACTUS_BIG);
            }
        }
    }

    private void drawDino(Graphics2D g, int x, int y) {
        g.drawImage(dinoImg, x, y, 80, 80, null);
    }

    private void drawCactus(Graphics2D g, int x, int y, int h, boolean big) {
        g.drawImage(cactusImg, x, y, 45, h, null);
    }

    private void drawBird(Graphics2D g, int x, int y) {
        g.drawImage(birdImg, x, y, 60, 40, null);
    }

    // ===== KeyListener =====

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_SPACE || k == KeyEvent.VK_UP) {
            jump();
        }
        if (k == KeyEvent.VK_ESCAPE) {
            frame.triggerPause(score);
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e)    {}
}