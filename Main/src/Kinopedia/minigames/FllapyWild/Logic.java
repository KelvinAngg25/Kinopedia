/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.FllapyWild;

/**
 *
 * @author William
 */

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Logic {

    // Posisi karakter di layar
    private float x, y;

    // Kecepatan vertikal (positif = jatuh, negatif = naik)
    private float velocityY;

    // Ukuran hitbox karakter
    private int width  = 70;
    private int height = 60;

    // Konstanta fisika
    private static final float GRAVITY    = 0.5f;
    private static final float JUMP_FORCE = -10.0f;
    private static final float MAX_FALL   = 14.0f;

    // Nama karakter: "PLUPPY", "BOYO", atau "KWEK"
    private String characterName;

    // Rotasi visual karakter
    private float angle = 0;

    // Kunci aset untuk tiap frame animasi
    // frameKeys[0] = frame saat naik/jump (_1)
    // frameKeys[1] = frame saat jatuh (_2)
    private String[] frameKeys;

    public Logic(float x, float y, String characterName) {
        this.x             = x;
        this.y             = y;
        this.characterName = characterName;
        this.velocityY     = 0;

        // Siapkan nama kunci aset berdasarkan nama karakter
        String prefix = characterName.toLowerCase();
        frameKeys = new String[]{
            prefix + "_1",  // frame 0: saat naik / jump
            prefix + "_2"   // frame 1: saat jatuh
        };
    }

    // Update fisika karakter setiap frame
    public void update() {
        // Gravitasi menarik karakter ke bawah
        velocityY += GRAVITY;
        if (velocityY > MAX_FALL) {
            velocityY = MAX_FALL;
        }

        // Gerakkan karakter
        y += velocityY;

        // Rotasi visual: miring ke atas saat naik, ke bawah saat jatuh
        angle = velocityY * 3.2f;
        if (angle > 65) {
            angle = 65;
        }
        if (angle < -25) {
            angle = -25;
        }
    }

    // Karakter melompat ke atas
    public void jump() {
        velocityY = JUMP_FORCE;
    }

    // Gambar karakter di layar
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,    RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,   RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Simpan transformasi sebelum rotasi
        AffineTransform originalTransform = g2.getTransform();

        // Rotasi di titik tengah karakter
        double cx = x + width  / 2.0;
        double cy = y + height / 2.0;
        g2.rotate(Math.toRadians(angle), cx, cy);

        // Pilih frame animasi berdasarkan arah gerakan:
        // velocityY < 0 → karakter sedang naik → pakai _1
        // velocityY >= 0 → karakter sedang jatuh → pakai _2
        String currentFrameKey;
        if (velocityY < 0) {
            currentFrameKey = frameKeys[0]; // _1: naik / jump
        } else {
            currentFrameKey = frameKeys[1]; // _2: jatuh
        }

        if (AssetManager.has(currentFrameKey)) {
            // ===== Gambar dari aset PNG =====
            BufferedImage frame = AssetManager.get(currentFrameKey);

            // Gambar bayangan di bawah karakter
            g2.setColor(new Color(0, 0, 0, 35));
            g2.fillOval((int)x + 10, (int)y + height - 5, width - 20, 14);

            // Gambar karakter
            g2.drawImage(frame, (int)x, (int)y, width, height, null);

        } else {
            // ===== Fallback gambar sederhana =====
            drawFallback(g2);
        }

        // Kembalikan transformasi
        g2.setTransform(originalTransform);
    }

    // Gambar sederhana sebagai fallback jika aset tidak ada
    private void drawFallback(Graphics2D g2) {
        int cx = (int)x + width  / 2;
        int cy = (int)y + height / 2;

        // Bayangan
        g2.setColor(new Color(0, 0, 0, 40));
        g2.fillOval(cx - 25, cy + 22, 50, 12);

        // Animasi sayap fallback: ke atas saat naik, ke bawah saat jatuh
        int wingOffset;
        if (velocityY < 0) {
            wingOffset = -6; // sayap ke atas saat naik
        } else {
            wingOffset = 6;  // sayap ke bawah saat jatuh
        }

        if (characterName.equals("PLUPPY")) {
            g2.setColor(Color.BLACK);
            g2.fillOval(cx - 28, cy - 26, 56, 54);

            g2.setColor(Color.WHITE);
            g2.fillOval(cx - 14, cy - 10, 28, 36);

            g2.setColor(new Color(255, 165, 0));
            g2.fillPolygon(
                new int[]{cx - 8, cx + 8, cx + 15},
                new int[]{cy - 14, cy - 14, cy - 6},
                3
            );

            g2.setColor(Color.BLACK);
            g2.fillOval(cx - 42, cy - 6 + wingOffset, 16, 26);
            g2.fillOval(cx + 26, cy - 6 - wingOffset, 16, 26);

            g2.setColor(Color.WHITE);
            g2.fillOval(cx - 16, cy - 24, 12, 12);

            g2.setColor(Color.BLACK);
            g2.fillOval(cx - 13, cy - 21, 7, 7);

            g2.setColor(Color.WHITE);
            g2.fillOval(cx + 4, cy - 24, 12, 12);

            g2.setColor(Color.BLACK);
            g2.fillOval(cx + 7, cy - 21, 7, 7);

        } else if (characterName.equals("BOYO")) {
            g2.setColor(new Color(80, 160, 230));
            g2.fillOval(cx - 27, cy - 24, 54, 52);

            g2.setColor(new Color(180, 220, 255));
            g2.fillOval(cx - 13, cy - 8, 26, 34);

            g2.setColor(new Color(60, 130, 200));
            g2.fillOval(cx - 42, cy - 8 + wingOffset, 16, 24);
            g2.fillOval(cx + 26, cy - 8 - wingOffset, 16, 24);

            g2.setColor(Color.WHITE);
            g2.fillOval(cx - 15, cy - 22, 12, 12);

            g2.setColor(Color.BLACK);
            g2.fillOval(cx - 12, cy - 19, 7, 7);

            g2.setColor(Color.WHITE);
            g2.fillOval(cx + 3, cy - 22, 12, 12);

            g2.setColor(Color.BLACK);
            g2.fillOval(cx + 6, cy - 19, 7, 7);

        } else if (characterName.equals("KWEK")) {
            g2.setColor(new Color(255, 210, 50));
            g2.fillOval(cx - 26, cy - 22, 52, 50);

            g2.setColor(new Color(255, 235, 150));
            g2.fillOval(cx - 13, cy - 6, 26, 32);

            g2.setColor(new Color(230, 175, 0));
            g2.fillOval(cx - 40, cy - 6 + wingOffset, 16, 26);
            g2.fillOval(cx + 24, cy - 6 - wingOffset, 16, 26);

            g2.setColor(new Color(255, 130, 0));
            g2.fillPolygon(
                new int[]{cx - 9, cx + 9, cx + 16},
                new int[]{cy - 10, cy - 10, cy - 4},
                3
            );

            g2.setColor(Color.WHITE);
            g2.fillOval(cx - 14, cy - 20, 12, 12);

            g2.setColor(Color.BLACK);
            g2.fillOval(cx - 11, cy - 17, 7, 7);

            g2.setColor(Color.WHITE);
            g2.fillOval(cx + 2, cy - 20, 12, 12);

            g2.setColor(Color.BLACK);
            g2.fillOval(cx + 5, cy - 17, 7, 7);
        }
    }

    // Hitbox untuk deteksi tabrakan
    public Rectangle getHitbox() {
        return new Rectangle((int)x + 10, (int)y + 10, width - 20, height - 20);
    }

    // Getter & Setter
    public float getX()        { return x; }
    public float getY()        { return y; }
    public void  setY(float y) { this.y = y; }
    public int   getWidth()    { return width; }
    public int   getHeight()   { return height; }
}