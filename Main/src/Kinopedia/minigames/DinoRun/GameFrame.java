/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.DinoRun;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;

public class GameFrame extends JFrame {

    public static final int WIDTH  = 470;
    public static final int HEIGHT = 844;

    public static final String MENU_SCREEN     = "MENU";
    public static final String GAME_SCREEN     = "GAME";
    public static final String PAUSE_SCREEN    = "PAUSE";
    public static final String GAMEOVER_SCREEN = "GAMEOVER";
    public static final String VICTORY_SCREEN  = "VICTORY";

    private CardLayout cardLayout;
    private JPanel     mainPanel;

    private MenuPanel     menuPanel;
    private GamePanel     gamePanel;
    private PausePanel    pausePanel;
    private GameOverPanel gameOverPanel;
    private VictoryPanel  victoryPanel;

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

    public void showScreen(String screen) {
        cardLayout.show(mainPanel, screen);
        if (screen.equals(GAME_SCREEN)) {
            gamePanel.requestFocusInWindow();
        }
    }

    public void startNewGame() {
        gamePanel.resetGame();
        showScreen(GAME_SCREEN);
        gamePanel.startGame();
    }

    public void resumeGame() {
        showScreen(GAME_SCREEN);
        gamePanel.resumeGame();
    }

    public void triggerGameOver(int score) {
        gameOverPanel.setScore(score);
        showScreen(GAMEOVER_SCREEN);
    }

    public void triggerVictory(int score) {
        victoryPanel.setScore(score);
        showScreen(VICTORY_SCREEN);
    }

    public void triggerPause(int score) {
        pausePanel.setScore(score);
        gamePanel.pauseGame();
        showScreen(PAUSE_SCREEN);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}

