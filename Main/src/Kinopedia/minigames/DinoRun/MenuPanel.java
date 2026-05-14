/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.DinoRun;

import javax.swing.JButton;
import javax.swing.JPanel;
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

public class MenuPanel extends JPanel {

    private GameFrame frame;
    private Image dinoImg;
    private Image cactusImg;
    private Image birdImg;

    public MenuPanel(GameFrame frame) {
        dinoImg = new ImageIcon(
            getClass().getResource(
                "/Kinopedia/minigames/DinoRun/Asset/Dino.png"
            )
        ).getImage();
        
        this.frame = frame;
        setPreferredSize(new Dimension(GameFrame.WIDTH, GameFrame.HEIGHT));
        setBackground(Color.WHITE);
        setLayout(null);

        JButton startBtn = createPixelButton("START", new Color(34, 197, 94));
        startBtn.setBounds(115, 570, 240, 55);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.startNewGame();
            }
        });
        add(startBtn);

        JButton exitBtn = createPixelButton("EXIT", new Color(239, 68, 68));
        exitBtn.setBounds(115, 640, 240, 55);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Kinopedia.minigames.MainMiniGames().setVisible(true);
                frame.dispose();
            }
        });
        add(exitBtn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        drawDino(g2d, GameFrame.WIDTH / 2 - 55, 190);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Courier New", Font.BOLD, 36));
        FontMetrics fm = g2d.getFontMetrics();
        String title = "DINORUN";
        g2d.drawString(title, (GameFrame.WIDTH - fm.stringWidth(title)) / 2, 490);
    }

    private void drawDino(Graphics2D g, int x, int y) {

        g.drawImage(dinoImg, x, y, 80, 80, null);

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

