package Kinopedia.minigames.DinoRun;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Frame utama DinoRun.
 *
 * Perubahan dari versi sebelumnya:
 *   - triggerGameOver() dan triggerVictory() sekarang memanggil
 *     CoinManager.awardCoins() sebelum menampilkan layar hasil.
 *   - showScreen(MENU_SCREEN) yang dipanggil dari tombol EXIT
 *     memanggil CoinManager.exitWithoutReward() (tidak ada koin).
 */
public class GameFrame extends JFrame {

    public static final int WIDTH  = 470;
    public static final int HEIGHT = 844;

    public static final String MENU_SCREEN     = "MENU";
    public static final String GAME_SCREEN     = "GAME";
    public static final String PAUSE_SCREEN    = "PAUSE";
    public static final String GAMEOVER_SCREEN = "GAMEOVER";
    public static final String VICTORY_SCREEN  = "VICTORY";

    private CardLayout    cardLayout;
    private JPanel        mainPanel;

    private MenuPanel     menuPanel;
    private GamePanel     gamePanel;
    private PausePanel    pausePanel;
    private GameOverPanel gameOverPanel;
    private VictoryPanel  victoryPanel;

    // Simpan skor sesi aktif agar EXIT dari pause tidak memberi koin
    private int  currentSessionScore = 0;
    // Apakah sesi ini berakhir dengan kemenangan atau kekalahan resmi
    private boolean sessionEnded = false;

    public GameFrame() {
        setTitle("DINORUN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel  = new JPanel(cardLayout);

        menuPanel     = new MenuPanel(this);
        gamePanel     = new GamePanel(this);
        pausePanel    = new PausePanel(this);
        gameOverPanel = new GameOverPanel(this);
        victoryPanel  = new VictoryPanel(this);

        mainPanel.add(menuPanel,     MENU_SCREEN);
        mainPanel.add(gamePanel,     GAME_SCREEN);
        mainPanel.add(pausePanel,    PAUSE_SCREEN);
        mainPanel.add(gameOverPanel, GAMEOVER_SCREEN);
        mainPanel.add(victoryPanel,  VICTORY_SCREEN);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        showScreen(MENU_SCREEN);
    }

    // ===== Navigasi layar =====

    public void showScreen(String screen) {
        if (screen.equals(MENU_SCREEN)) {
            menuPanel.refreshCoins();
        }

        cardLayout.show(mainPanel, screen);

        if (screen.equals(GAME_SCREEN)) {
            gamePanel.requestFocusInWindow();
        }
    }

    /**
     * Dipanggil oleh tombol EXIT di semua layar.
     * Tidak memberi koin — langsung kembali ke menu.
     */
    public void exitToMenu() {
        CoinManager.getInstance().exitWithoutReward();
        sessionEnded = true;
        showScreen(MENU_SCREEN);
    }

    // ===== Kontrol permainan =====

    public void startNewGame() {
        sessionEnded          = false;
        currentSessionScore   = 0;
        gamePanel.resetGame();
        showScreen(GAME_SCREEN);
        gamePanel.startGame();
    }

    public void resumeGame() {
        showScreen(GAME_SCREEN);
        gamePanel.resumeGame();
    }

    public void triggerPause(int score) {
        currentSessionScore = score;
        pausePanel.setScore(score);
        gamePanel.pauseGame();
        showScreen(PAUSE_SCREEN);
    }

    /**
     * Dipanggil saat dino menabrak obstacle.
     * Memberi koin = score (1:1).
     */
    public void triggerGameOver(int score) {
        currentSessionScore = score;
        sessionEnded        = true;

        int earned = CoinManager.awardCoins(score, false);

        gameOverPanel.setScore(score);
        gameOverPanel.setEarnedCoins(earned);
        showScreen(GAMEOVER_SCREEN);
    }

    /**
     * Dipanggil saat skor mencapai WIN_SCORE (100).
     * Memberi koin = 100 + 20 bonus = 120.
     */
    public void triggerVictory(int score) {
        currentSessionScore = score;
        sessionEnded        = true;

        int earned = CoinManager.awardCoins(score, true);

        victoryPanel.setScore(score);
        victoryPanel.setEarnedCoins(earned);
        showScreen(VICTORY_SCREEN);
    }

    // ===== Getter =====

    public int getCurrentSessionScore() {
        return currentSessionScore;
    }

    public boolean isSessionEnded() {
        return sessionEnded;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}