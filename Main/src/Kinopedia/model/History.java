/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.model;

import Kinopedia.DataUser;
import Kinopedia.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class History extends JFrame {

    DataUser user = Kinopedia.Session.getInstance().getCurrentUser();

    public History() {

        setTitle("History");
        setSize(470, 844);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel main = new JPanel();
        main.setLayout(null);
        main.setBackground(new Color(242, 242, 242));

        // ================= BACK =================
        JLabel backArrow = new JLabel("‹");
        backArrow.setFont(new Font("SansSerif", Font.BOLD, 28));
        backArrow.setBounds(12, 22, 20, 30);
        backArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel backText = new JLabel("Kembali");
        backText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        backText.setBounds(28, 29, 80, 18);
        backText.setCursor(new Cursor(Cursor.HAND_CURSOR));

        MouseAdapter backEvent = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Buyer().setVisible(true);
            }
        };

        backArrow.addMouseListener(backEvent);
        backText.addMouseListener(backEvent);

        main.add(backArrow);
        main.add(backText);

        // ================= CONTENT =================
        JPanel content = new JPanel();
        content.setBackground(new Color(242, 242, 242));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.setBorder(BorderFactory.createEmptyBorder(
                8,
                0,
                0,
                0
        ));

        for (Kinopedia.DataTransaksi tr : Main.dataTransaksi) {

            if (tr.getUsername().equals(user.getNama())) {

                JPanel wrap = new JPanel();

                wrap.setOpaque(false);

                FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
                flow.setHgap(18);

                wrap.setLayout(flow);

                wrap.add(createCard(tr));

                content.add(wrap);
                content.add(Box.createVerticalStrut(14));
            }
        }

        content.add(Box.createVerticalStrut(25));

        JLabel logo = new JLabel("K");
        logo.setFont(new Font("SansSerif", Font.BOLD, 42));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(logo);

        JScrollPane scroll = new JScrollPane(content);

        scroll.setBounds(0, 65, 390, 730);

        scroll.setBorder(null);

        scroll.getViewport().setBackground(
                new Color(242, 242, 242)
        );

        scroll.getVerticalScrollBar().setUnitIncrement(16);

        main.add(scroll);

        add(main);

        setVisible(true);
    }

    // ================= CARD =================
    private JPanel createCard(Kinopedia.DataTransaksi tr) {

        Color start;
        Color end;

        if (tr.isKonfirmasi()) {

            start = new Color(45, 170, 35);
            end = new Color(5, 60, 15);

        } else {

            start = new Color(210, 40, 40);
            end = new Color(110, 10, 10);
        }

        GradientPanel card = new GradientPanel(start, end);

        card.setPreferredSize(new Dimension(340, 86));
        card.setLayout(null);

        JLabel title = new JLabel("ID Transaksi");
        title.setFont(new Font("SansSerif", Font.PLAIN, 11));
        title.setForeground(new Color(220, 220, 220));
        title.setBounds(16, 12, 100, 15);

        JLabel id = new JLabel("#" + tr.getIdTransaksi());
        id.setFont(new Font("SansSerif", Font.BOLD, 13));
        id.setForeground(Color.WHITE);
        id.setBounds(16, 40, 180, 16);

        JLabel date = new JLabel(
                tr.getTanggal() + " · " + tr.getWaktu()
        );

        date.setFont(new Font("SansSerif", Font.PLAIN, 11));
        date.setForeground(new Color(225, 225, 225));
        date.setBounds(16, 60, 150, 15);

        // ================= ARROW =================
        JButton arrow = new JButton("❯");

        arrow.setFont(new Font("SansSerif", Font.BOLD, 20));
        arrow.setForeground(Color.WHITE);

        arrow.setBounds(295, 24, 30, 30);

        arrow.setBorderPainted(false);
        arrow.setContentAreaFilled(false);
        arrow.setFocusPainted(false);

        arrow.setMargin(new Insets(0, 0, 0, 0));

        arrow.setCursor(new Cursor(Cursor.HAND_CURSOR));

        arrow.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new DetailTransaction(tr);
            }
        });

        card.add(title);
        card.add(id);
        card.add(date);
        card.add(arrow);

        return card;
    }

    // ================= DETAIL =================
    class DetailTransaction extends JFrame {

        public DetailTransaction(Kinopedia.DataTransaksi tr) {

            setTitle("Detail");
            setSize(470, 844);
            setLocationRelativeTo(null);
            setResizable(false);

            DetailTransaksi frame = new DetailTransaksi(
                    tr.isKonfirmasi(),
                    tr.getIdTransaksi(),
                    tr.getTanggal() + " " + tr.getWaktu(),
                    tr.getNamaAkun(),
                    tr.getIdGame(),
                    tr.getJenisGame(),
                    tr.getPembayaran(),
                    tr.getNominal()
            );

            frame.setVisible(true);

            dispose();
        }
    }

    // ================= GRADIENT =================
    class GradientPanel extends JPanel {

        Color start;
        Color end;

        public GradientPanel(Color start, Color end) {

            this.start = start;
            this.end = end;

            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            GradientPaint gp = new GradientPaint(
                    0,
                    0,
                    start,
                    getWidth(),
                    0,
                    end
            );

            g2.setPaint(gp);

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    14,
                    14
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