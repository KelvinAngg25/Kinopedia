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
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AssetManager {

    private static Map<String, BufferedImage> images = new HashMap<>();
    private static boolean loaded = false;

    public static void loadAll() {
        if (loaded) {
            return;
        }

        System.out.println("Memuat aset game...");

        // BACKGROUND
//        load("sky",    "background/sky.jpg");
        // load("ground", "background/ground.png");

        // KARAKTER — hanya 2 frame per karakter
        // frame _1 = dipakai saat naik/jump
        // frame _2 = dipakai saat jatuh
        for (String name : new String[]{"pluppy", "boyo", "kwek"}) {
            load(name + "_1", "character/" + name + "_1.png");
            load(name + "_2", "character/" + name + "_2.png");
        }

        // PREVIEW KARAKTER di halaman menu
        // ===== PERBAIKAN: nama file di resource adalah *_iddle.png (dua d) =====
        load("pluppy_idle", "character_preview/pluppy_iddle.png");
        load("boyo_idle",   "character_preview/boyo_iddle.png");
        load("kwek_idle",   "character_preview/kwek_iddle.png");

        // PIPA
        load("pipe_top",    "pipes/pipe_top.png");
        load("pipe_bottom", "pipes/pipe_bottom.png");

        // UI
        load("logo",           "ui/logo.png");
        load("btn_mulai",      "ui/btn_mulai.png");
        load("btn_exit",       "ui/btn_exit.png");
        load("btn_pause",      "ui/btn_pause.png");
        load("btn_continue",   "ui/btn_continue.png");
        load("btn_kembali",    "ui/btn_kembali.png");
        load("panel_pause",    "ui/panel_pause.png");
        load("panel_gameover", "ui/panel_gameover.png");
        load("panel_victory",  "ui/panel_victory.png");

        // AWAN
        // ===== PERBAIKAN: key "cloud" harus cocok dengan yang dicari di FlappyWild & MainPanel =====
        load("cloud", "clouds/cloud.png");

        loaded = true;
        System.out.println("Selesai memuat aset!");
    }

    private static void load(String key, String filePath) {
        String fullPath = "/" + filePath;
        try {
            InputStream is = AssetManager.class.getResourceAsStream(fullPath);
            if (is == null) {
                System.out.println("  [MISS] " + fullPath);
                images.put(key, null);
                return;
            }
            BufferedImage img = ImageIO.read(is);
            images.put(key, img);
            System.out.println("  [OK]  " + filePath);
        } catch (IOException e) {
            System.out.println("  [ERROR] " + filePath + " → " + e.getMessage());
            images.put(key, null);
        }
    }

    public static BufferedImage get(String key) {
        return images.get(key);
    }

    public static boolean has(String key) {
        if (images.containsKey(key) && images.get(key) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static Image getScaled(String key, int w, int h) {
        BufferedImage img = images.get(key);
        if (img == null) {
            return null;
        }
        return img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
    }
}