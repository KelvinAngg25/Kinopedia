/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class History extends JFrame {

    ArrayList<Transaction> list = new ArrayList<>();

    public History() {

        setTitle("History");
        setSize(390, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        dataTransaction();

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

        for (Transaction tr : list) {

            JPanel wrap = new JPanel();

            wrap.setOpaque(false);

            wrap.setLayout(new FlowLayout(
                    FlowLayout.CENTER,
                    0,
                    0
            ));

            wrap.add(createCard(tr));

            content.add(wrap);
            content.add(Box.createVerticalStrut(14));
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

    // ================= DATA =================
    private void dataTransaction() {

        list.add(new Transaction(
                "INV-20240521-005",
                "21-05-2024 · 17:30",
                false
        ));

        list.add(new Transaction(
                "INV-20240521-005",
                "21-05-2024 · 17:30",
                false
        ));

        list.add(new Transaction(
                "INV-20240521-005",
                "21-05-2024 · 17:30",
                true
        ));

        list.add(new Transaction(
                "INV-20240521-005",
                "21-05-2024 · 17:30",
                true
        ));

        list.add(new Transaction(
                "INV-20240521-005",
                "21-05-2024 · 17:30",
                true
        ));

        list.add(new Transaction(
                "INV-20240521-005",
                "21-05-2024 · 17:30",
                false
        ));
    }

    // ================= CARD =================
    private JPanel createCard(Transaction tr) {

        Color start;
        Color end;

        if (tr.success) {

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

        JLabel id = new JLabel("#" + tr.id);
        id.setFont(new Font("SansSerif", Font.BOLD, 13));
        id.setForeground(Color.WHITE);
        id.setBounds(16, 40, 180, 16);

        JLabel date = new JLabel(tr.date);
        date.setFont(new Font("SansSerif", Font.PLAIN, 11));
        date.setForeground(new Color(225, 225, 225));
        date.setBounds(16, 60, 150, 15);

        // ================= ARROW =================
        JButton arrow = new JButton("❯");

        arrow.setFont(new Font("SansSerif", Font.BOLD, 20));
        arrow.setForeground(Color.WHITE);

        arrow.setBounds(295, 28, 22, 22);

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

    // ================= TRANSACTION =================
    class Transaction {

        String id;
        String date;
        boolean success;

        public Transaction(
                String id,
                String date,
                boolean success
        ) {

            this.id = id;
            this.date = date;
            this.success = success;
        }
    }

    // ================= DETAIL =================
    class DetailTransaction extends JFrame {

        public DetailTransaction(Transaction tr) {

            setTitle("Detail");
            setSize(320, 300);
            setLocationRelativeTo(null);
            setResizable(false);

            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBackground(Color.WHITE);

            JLabel title = new JLabel("Detail Transaksi");
            title.setFont(new Font("SansSerif", Font.BOLD, 20));
            title.setBounds(20, 20, 200, 25);

            JLabel id = new JLabel("ID : " + tr.id);
            id.setBounds(20, 80, 250, 20);

            JLabel date = new JLabel("Tanggal : " + tr.date);
            date.setBounds(20, 120, 250, 20);

            JLabel status = new JLabel(
                    tr.success
                            ? "Status : Success"
                            : "Status : Failed"
            );

            status.setBounds(20, 160, 200, 20);

            panel.add(title);
            panel.add(id);
            panel.add(date);
            panel.add(status);

            add(panel);

            setVisible(true);
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