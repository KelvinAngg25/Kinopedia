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

public class PausePanel extends JPanel {

    private GameFrame frame;
    private JLabel    scoreLabel;
    private Image dinoImg;
    private Image cactusImg;
    private Image birdImg;

    public PausePanel(GameFrame frame) {
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

        scoreLabel = new JLabel("000", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Courier New", Font.BOLD, 44));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setBounds(0, 55, GameFrame.WIDTH, 60);
        add(scoreLabel);

        // Panel popup
        JPanel popup = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(3));
                g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 20, 20);
            }
        };
        popup.setOpaque(false);
        popup.setBounds(90, 290, 290, 185);

        JButton continueBtn = createPixelButton("CONTINUE", new Color(34, 197, 94));
        continueBtn.setBounds(20, 20, 250, 55);
        continueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.resumeGame();
            }
        });
        popup.add(continueBtn);

        JButton exitBtn = createPixelButton("EXIT", new Color(239, 68, 68));
        exitBtn.setBounds(20, 95, 250, 55);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.showScreen(GameFrame.MENU_SCREEN);
            }
        });
        popup.add(exitBtn);

        add(popup);
    }

    public void setScore(int score) {
        scoreLabel.setText(String.format("%03d", score));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(0, 600, GameFrame.WIDTH, 600);

        drawDino(g2d, 15, 510);
        drawBird(g2d, 290, 540);
    }

    private void drawDino(Graphics2D g, int x, int y) {

        g.drawImage(dinoImg, x, y, 80, 80, null);

    }   

    private void drawBird(Graphics2D g, int x, int y) {

        g.drawImage(birdImg, x, y, 60, 40, null);

    }
    
    private JButton createPixelButton(final String text, final Color bg) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(bg);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Courier New", Font.BOLD, 20));
                FontMetrics fm = g2d.getFontMetrics();
                int tx = (getWidth() - fm.stringWidth(text)) / 2;
                int ty = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2d.drawString(text, tx, ty);
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(3));
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
