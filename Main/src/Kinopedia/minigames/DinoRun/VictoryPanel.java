/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.DinoRun;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import Kinopedia.DataUser;
import Kinopedia.Session;
import javax.swing.JOptionPane;

public class VictoryPanel extends JPanel {

    private GameFrame frame;
    private JLabel    scoreLabel;
    private Image dinoImg;
    private Image cactusImg;
    private Image birdImg;
    
    private JLabel earnedCoinsLabel;

    public VictoryPanel(GameFrame frame) {

        dinoImg = new ImageIcon(
            getClass().getResource(
                "/Kinopedia/minigames/DinoRun/Asset/Dino.png"
            )
        ).getImage();

        cactusImg = new ImageIcon(
            getClass().getResource(
                "/Kinopedia/minigames/DinoRun/Asset/KaktusBesar.png"
            )
        ).getImage();

        birdImg = new ImageIcon(
            getClass().getResource(
                "/Kinopedia/minigames/DinoRun/Asset/Burung.png"
            )
        ).getImage();

        this.frame = frame;
        setPreferredSize(new Dimension(GameFrame.WIDTH, GameFrame.HEIGHT));
        setBackground(Color.WHITE);
        setLayout(null);

        JLabel victoryLbl = new JLabel("VICTORY!", SwingConstants.CENTER);
        victoryLbl.setFont(new Font("Courier New", Font.BOLD, 54));
        victoryLbl.setForeground(Color.BLACK);
        victoryLbl.setBounds(0, 55, GameFrame.WIDTH, 80);
        add(victoryLbl);

        scoreLabel = new JLabel("SCORE: 100", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Courier New", Font.BOLD, 24));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setBounds(0, 460, GameFrame.WIDTH, 40);
        add(scoreLabel);

        JLabel tryLbl = new JLabel("TRY AGAIN?", SwingConstants.CENTER);
        tryLbl.setFont(new Font("Courier New", Font.BOLD, 22));
        tryLbl.setForeground(Color.BLACK);
        tryLbl.setBounds(0, 555, GameFrame.WIDTH, 35);
        add(tryLbl);
        
        earnedCoinsLabel = new JLabel("COINS EARNED: 0", SwingConstants.CENTER);
        earnedCoinsLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        earnedCoinsLabel.setForeground(Color.BLACK);
        earnedCoinsLabel.setBounds(0, 505, GameFrame.WIDTH, 35);
        add(earnedCoinsLabel);

        JButton retryBtn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.BLACK);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND));
                int cx = getWidth() / 2;
                int cy = getHeight() / 2;
                g2d.drawArc(cx - 12, cy - 12, 24, 24, 30, 300);
                int[] ax = {cx + 10, cx + 15, cx + 15};
                int[] ay = {cy - 13, cy - 8, cy - 18};
                g2d.fillPolygon(ax, ay, 3);
            }
        };
        retryBtn.setBounds(GameFrame.WIDTH / 2 - 28, 592, 56, 56);
        retryBtn.setBorderPainted(false);
        retryBtn.setFocusPainted(false);
        retryBtn.setContentAreaFilled(false);
        retryBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        retryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DataUser user =
                        Session.getInstance().getCurrentUser();

                // Cek kredit
                if (user.getKredit() >= 1) {

                    // Kurangi kredit
                    user.setKredit(
                            user.getKredit() - 1
                    );

                    frame.startNewGame();

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Kredit Tidak Cukup"
                    );
                }
            }
        });
        add(retryBtn);

        JButton exitBtn = createOutlineButton("EXIT");
        exitBtn.setBounds(GameFrame.WIDTH / 2 - 100, 665, 200, 50);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.exitToMenu();
            }
        });
        add(exitBtn);
    }

    public void setScore(int score) {
        scoreLabel.setText(String.format("SCORE: %03d", score));
    }
    
    public void setEarnedCoins(int coins) {
        earnedCoinsLabel.setText("COINS  : +" + coins);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(0, 460, GameFrame.WIDTH, 460);

        drawCactus(g2d, GameFrame.WIDTH / 2 + 10, 378);
        drawDino(g2d, GameFrame.WIDTH / 2 - 45, 360);
    }

    private void drawDino(Graphics2D g, int x, int y) {

        g.drawImage(dinoImg, x, y, 80, 80, null);

    }   
    private void drawCactus(Graphics2D g, int x, int y) {

        g.drawImage(cactusImg, x, y, 45, 55, null);

    }

    private JButton createOutlineButton(final String text) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Courier New", Font.BOLD, 20));
                FontMetrics fm = g2d.getFontMetrics();
                int tx = (getWidth() - fm.stringWidth(text)) / 2;
                int ty = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2d.drawString(text, tx, ty);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
            }
        };
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}

