/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class History extends JFrame {

    public History() {

        setTitle("History");
        setSize(420, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // ================= MAIN PANEL =================
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setLayout(new BorderLayout());

        // ================= HEADER =================
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(245, 245, 245));
        headerPanel.setBorder(new EmptyBorder(15, 15, 10, 15));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));

        JLabel backLabel = new JLabel("‹");
        backLabel.setFont(new Font("SansSerif", Font.PLAIN, 42));
        backLabel.setForeground(Color.BLACK);

        JLabel kembaliLabel = new JLabel("Kembali");
        kembaliLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
        kembaliLabel.setForeground(Color.BLACK);

        // supaya sejajar tengah
        backLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        kembaliLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        headerPanel.add(backLabel);
        headerPanel.add(Box.createHorizontalStrut(8));
        headerPanel.add(kembaliLabel);

        // ================= CONTENT =================
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(245, 245, 245));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 20, 20, 20));

        contentPanel.add(createCard(false));
        contentPanel.add(Box.createVerticalStrut(18));

        contentPanel.add(createCard(false));
        contentPanel.add(Box.createVerticalStrut(18));

        contentPanel.add(createCard(true));
        contentPanel.add(Box.createVerticalStrut(18));

        contentPanel.add(createCard(true));
        contentPanel.add(Box.createVerticalStrut(18));

        contentPanel.add(createCard(true));
        contentPanel.add(Box.createVerticalStrut(18));

        contentPanel.add(createCard(false));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    // ================= CARD =================
    private JPanel createCard(boolean success) {

        Color startColor;
        Color endColor;

        if (success) {
            startColor = new Color(50, 190, 45);
            endColor = new Color(0, 70, 0);
        } else {
            startColor = new Color(215, 35, 35);
            endColor = new Color(120, 0, 0);
        }

        GradientPanel card = new GradientPanel(startColor, endColor);

        card.setPreferredSize(new Dimension(350, 140));
        card.setMaximumSize(new Dimension(350, 140));
        card.setMinimumSize(new Dimension(350, 140));

        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(20, 22, 20, 22));

        // ================= TEXT PANEL =================
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("ID Transaksi");
        title.setForeground(new Color(220, 220, 220));
        title.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JLabel id = new JLabel("#INV-20240521-005");
        id.setForeground(Color.WHITE);
        id.setFont(new Font("SansSerif", Font.BOLD, 17));

        JLabel date = new JLabel("21-05-2024 · 17:30");
        date.setForeground(new Color(220, 220, 220));
        date.setFont(new Font("SansSerif", Font.PLAIN, 14));

        textPanel.add(title);
        textPanel.add(Box.createVerticalStrut(22));
        textPanel.add(id);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(date);

        // ================= ARROW =================
        JLabel arrow = new JLabel("›");
        arrow.setForeground(Color.WHITE);
        arrow.setFont(new Font("SansSerif", Font.BOLD, 40));

        JPanel arrowPanel = new JPanel();
        arrowPanel.setOpaque(false);
        arrowPanel.setLayout(new GridBagLayout());
        arrowPanel.setPreferredSize(new Dimension(40, 40));

        arrowPanel.add(arrow);

        // ================= ADD =================
        card.add(textPanel, BorderLayout.CENTER);
        card.add(arrowPanel, BorderLayout.EAST);

        return card;
    }

    // ================= GRADIENT PANEL =================
    class GradientPanel extends JPanel {

        private final Color startColor;
        private final Color endColor;

        public GradientPanel(Color startColor, Color endColor) {
            this.startColor = startColor;
            this.endColor = endColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            GradientPaint gradient = new GradientPaint(
                    0,
                    0,
                    startColor,
                    getWidth(),
                    0,
                    endColor
            );

            g2.setPaint(gradient);

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    35,
                    35
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new History();
            }
        });
    }
}