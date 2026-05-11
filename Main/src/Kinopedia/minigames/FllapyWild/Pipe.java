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

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Pipe {
 
    private float x;
    private int   pipeWidth = 90;
 
    // gapY = posisi Y TENGAH celah antara dua pipa
    private int gapY;
    // gapSize = tinggi celah (ruang untuk karakter lewat)
    private int gapSize = 235;
 
    // Kecepatan gerak ke kiri
    private float speed = 4.0f;
 
    // Sudah dilewati karakter? (untuk skor)
    private boolean passed = false;
 
    public Pipe(float startX) {
        this.x = startX;
        // Posisi celah dibuat random
        int minGapY = 280;
        int maxGapY = GameWindow.HEIGHT - 380;
        this.gapY   = new Random().nextInt(maxGapY - minGapY) + minGapY;
    }
 
    // Gerakkan pipa ke kiri setiap frame
    public void update() {
        x -= speed;
    }
 
    // Gambar pipa di layar
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
 
        int px = (int) x;
 
        // Batas atas dan bawah pipa
        int topPipeBottom    = gapY - gapSize / 2;   // Tepi bawah pipa atas
        int bottomPipeTop    = gapY + gapSize / 2;   // Tepi atas pipa bawah
 
        if (AssetManager.has("pipe_top") && AssetManager.has("pipe_bottom")) {
            // ===== Gambar dari aset PNG =====
 
            // Pipa ATAS: gambar dari Y=0 sampai topPipeBottom
            // Aset pipe_top.png harus punya kepala di bagian bawah
            g2.drawImage(
                AssetManager.get("pipe_bottom"),
                px, 0,                    // Posisi tujuan (x, y)
                pipeWidth, topPipeBottom, // Ukuran tujuan
                null
            );
 
            // Pipa BAWAH: gambar dari bottomPipeTop sampai bawah layar
            int bottomHeight = GameWindow.HEIGHT - bottomPipeTop;
            g2.drawImage(
                AssetManager.get("pipe_bottom"),
                px, bottomPipeTop,
                pipeWidth, bottomHeight,
                null
            );
 
        } else {
            // ===== Fallback gambar sederhana =====
            drawFallback(g2, px, topPipeBottom, bottomPipeTop);
        }
    }
 
    // Fallback gambar pipa sederhana (kotak merah)
    private void drawFallback(Graphics2D g2, int px, int topBottom, int bottomTop) {
        Color pipeRed   = new Color(200, 50, 50);
        Color pipeLight = new Color(230, 80, 80);
        Color pipeDark  = new Color(150, 30, 30);
 
        // ===== Pipa atas =====
        // Badan
        g2.setColor(pipeRed);
        g2.fillRect(px + 5, 0, pipeWidth - 10, topBottom - 25);
        g2.setColor(pipeLight);
        g2.fillRect(px + 5, 0, 12, topBottom - 25);
        g2.setColor(pipeDark);
        g2.fillRect(px + pipeWidth - 15, 0, 10, topBottom - 25);
        // Kepala pipa atas
        g2.setColor(pipeRed);
        g2.fillRoundRect(px, topBottom - 28, pipeWidth, 30, 8, 8);
        g2.setColor(pipeLight);
        g2.fillRoundRect(px + 4, topBottom - 25, 14, 24, 4, 4);
        g2.setColor(pipeDark);
        g2.fillRoundRect(px + pipeWidth - 18, topBottom - 25, 12, 24, 4, 4);
        // Border
        g2.setColor(new Color(120, 20, 20));
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(px + 5, 0, pipeWidth - 10, topBottom - 25);
        g2.drawRoundRect(px, topBottom - 28, pipeWidth, 30, 8, 8);
 
        // ===== Pipa bawah =====
        int bh = GameWindow.HEIGHT - bottomTop;
        // Kepala pipa bawah
        g2.setColor(pipeRed);
        g2.fillRoundRect(px, bottomTop, pipeWidth, 30, 8, 8);
        g2.setColor(pipeLight);
        g2.fillRoundRect(px + 4, bottomTop + 4, 14, 24, 4, 4);
        g2.setColor(pipeDark);
        g2.fillRoundRect(px + pipeWidth - 18, bottomTop + 4, 12, 24, 4, 4);
        // Badan
        g2.setColor(pipeRed);
        g2.fillRect(px + 5, bottomTop + 28, pipeWidth - 10, bh);
        g2.setColor(pipeLight);
        g2.fillRect(px + 5, bottomTop + 28, 12, bh);
        g2.setColor(pipeDark);
        g2.fillRect(px + pipeWidth - 15, bottomTop + 28, 10, bh);
        // Border
        g2.setColor(new Color(120, 20, 20));
        g2.drawRoundRect(px, bottomTop, pipeWidth, 30, 8, 8);
        g2.drawRect(px + 5, bottomTop + 28, pipeWidth - 10, bh);
    }
 
    // Cek apakah karakter menabrak pipa ini
    public boolean collidesWith(Rectangle birdHitbox) {
        int topPipeBottom = gapY - gapSize / 2;
        int bottomPipeTop = gapY + gapSize / 2;
 
        Rectangle topRect    = new Rectangle((int)x + 5, 0, pipeWidth - 10, topPipeBottom);
        Rectangle bottomRect = new Rectangle((int)x + 5, bottomPipeTop, pipeWidth - 10, GameWindow.HEIGHT);
 
        return birdHitbox.intersects(topRect) || birdHitbox.intersects(bottomRect);
    }
 
    // Apakah pipa sudah di luar layar?
    public boolean isOffScreen() {
        return x + pipeWidth < 0;
    }

    public boolean isPassed(float birdRightEdge) {
        if (!passed && birdRightEdge > x + pipeWidth) {
            passed = true;
            return true;
        }
        return false;
    }
 
    public float getX()        { return x; }
    public int   getPipeWidth(){ return pipeWidth; }
}