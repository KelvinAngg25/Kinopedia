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

public class GameWindow extends JFrame {
    
    // Ukuran layar game
    public static final int WIDTH  = 470;
    public static final int HEIGHT = 844;

    // CardLayout untuk berpindah panel
    private CardLayout cardLayout;
    private JPanel mainContainer;

    // Panel-panel utama
    private MainPanel mainPanel;
    private FlappyWild flappyWild;
    
    private Kinopedia.minigames.MainMiniGames mainMiniGames;

    public GameWindow(Kinopedia.minigames.MainMiniGames mainMiniGames) {
        this.mainMiniGames = mainMiniGames;
        AssetManager.loadAll();
        setTitle("Flappy Wild");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // Tampilkan di tengah layar

        // Setup CardLayout
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // Buat panel menu dan panel game
        mainPanel = new MainPanel(this);
        flappyWild = new FlappyWild(this);

        // Tambahkan ke container dengan nama kartu
        mainContainer.add(mainPanel, "MENU");
        mainContainer.add(flappyWild, "GAME");

        add(mainContainer);
        pack();
        setLocationRelativeTo(null);

        // Tampilkan halaman menu dulu
        showMenu();
    }

    // Tampilkan halaman menu pilih karakter
    public void showMenu() {
        mainPanel.resetMenu(); // Reset agar pilihan karakter bersih
        cardLayout.show(mainContainer, "MENU");
    }

    // Mulai game dengan karakter yang dipilih
    public void startGame(String character) {
        if (!KoinManager.bisaBermain()) {
            JOptionPane.showMessageDialog(
                this,
                "Kredit tidak cukup untuk bermain!\n", // isi alert
                "Alert", // judul alert
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Kurangi kredit
        boolean berhasil = KoinManager.kurangiKredit();
        if (!berhasil) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal memproses kredit. Silahkan coba lagi.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        
        // Mulai Game
        flappyWild.startGame(character);
        cardLayout.show(mainContainer, "GAME");
        flappyWild.requestFocusInWindow(); // Agar bisa menerima input keyboard
    }
    
    public void refreshMainMiniGames() {
        if (mainMiniGames != null) {
            mainMiniGames.refreshInfo();
        }
    }
}