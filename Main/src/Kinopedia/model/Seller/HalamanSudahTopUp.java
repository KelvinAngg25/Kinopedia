/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.model.Seller;

import Kinopedia.DataUser;
import Kinopedia.Main;
import Kinopedia.model.DetailTransaksiSeller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HalamanSudahTopUp extends JFrame {

    DataUser user = Kinopedia.Session.getInstance().getCurrentUser();

    public HalamanSudahTopUp() {

        try {
            setTitle("Tagihan Sudah di Top-Up");
            setSize(470, 844);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);

            JPanel main = new JPanel();
            main.setLayout(null);
            main.setBackground(new Color(242, 242, 242));

            ImageIcon iconBack = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/back.png"));
            JLabel btnBack = new JLabel(iconBack);
            btnBack.setBounds(35, 1, 100, 100);
            add(btnBack);

            btnBack.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dispose();
                    new Seller().setVisible(true);
                }
            });

            main.add(btnBack);

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
                if (tr.isKonfirmasi()) {
                    JPanel wrap = new JPanel();

                    wrap.setOpaque(false);

                    wrap.setLayout(new FlowLayout(
                            FlowLayout.CENTER,
                            0,
                            0
                    ));

                    wrap.setPreferredSize(new Dimension(450, 110));

                    wrap.add(createCard(tr));

                    content.add(wrap);
                }
            }
            // ================= SCROLL =================
            JScrollPane scroll = new JScrollPane(content);

            scroll.setBounds(0, 65, 470, 620);

            scroll.setBorder(null);

            scroll.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
            );

            scroll.getVerticalScrollBar().setBorder(null);

            scroll.getViewport().setBackground(
                    new Color(242, 242, 242)
            );

            scroll.setOpaque(false);

            scroll.getViewport().setOpaque(false);

            scroll.getVerticalScrollBar().setUnitIncrement(16);

            main.add(scroll);

            // ================= LOGO =================
            ImageIcon icon = new ImageIcon(
                    getClass().getResource("/Kinopedia/model/IMAGESS/LogoKinopedia.png")
            );

            Image scaledImage = icon.getImage().getScaledInstance(
                    50,
                    50,
                    Image.SCALE_SMOOTH
            );

            JLabel logoKinopedia = new JLabel(
                    new ImageIcon(scaledImage)
            );

            logoKinopedia.setBounds(210, 740, 50, 50);

            main.add(logoKinopedia);

            add(main);

            setVisible(true);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= CARD =================
    private JPanel createCard(Kinopedia.DataTransaksi tr) {

        Color start;
        Color end;

        start = new Color(45, 170, 35);
        end = new Color(5, 60, 15);

        GradientPanel card = new GradientPanel(start, end);

        card.setPreferredSize(new Dimension(418, 96));

        card.setLayout(null);

        // ================= TITLE =================
        JLabel title = new JLabel("ID Transaksi");

        title.setFont(new Font("SansSerif", Font.PLAIN, 11));

        title.setForeground(new Color(220, 220, 220));

        title.setBounds(16, 12, 100, 15);

        // ================= ID =================
        JLabel id = new JLabel("#" + tr.getIdTransaksi());

        id.setFont(new Font("SansSerif", Font.BOLD, 13));

        id.setForeground(Color.WHITE);

        id.setBounds(16, 40, 250, 18);

        // ================= DATE =================
        JLabel date = new JLabel(
                tr.getTanggal() + " · " + tr.getWaktu()
        );

        date.setFont(new Font("SansSerif", Font.PLAIN, 11));

        date.setForeground(new Color(230, 230, 230));

        date.setBounds(16, 62, 180, 15);

        // ================= BUTTON DETAIL =================
        JButton arrow = new JButton(">");

        arrow.setFont(new Font("SansSerif", Font.BOLD, 20));

        arrow.setForeground(Color.WHITE);

        arrow.setBounds(385, 28, 30, 30);

        arrow.setBorderPainted(false);

        arrow.setContentAreaFilled(false);

        arrow.setFocusPainted(false);

        arrow.setMargin(new Insets(0, 0, 0, 0));

        arrow.setCursor(new Cursor(Cursor.HAND_CURSOR));

        arrow.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

                DetailTransaksiSeller detail = new DetailTransaksiSeller(
                        true,
                        tr.getIdTransaksi(),
                        tr.getTanggal() + " " + tr.getWaktu(),
                        tr.getNamaAkun(),
                        tr.getIdGame(),
                        tr.getJenisGame(),
                        tr.getPembayaran(),
                        tr.getNominal(),
                        true,
                        tr.getUsername(),
                        tr.getTanggalKonfirmasi() + " " + tr.getWaktuKonfirmasi()
                );

                detail.setVisible(true);
            }
        });

        // ================= ADD =================
        card.add(title);

        card.add(id);

        card.add(date);

        card.add(arrow);
        
        // ================= STATUS =================
        JLabel detailText = new JLabel(
                "Top up telah diproses pada "
                + tr.getTanggalKonfirmasi()
                + " • "
                + tr.getWaktuKonfirmasi()
        );

        detailText.setFont(new Font("SansSerif", Font.PLAIN, 10));

        detailText.setForeground(new Color(220, 220, 220));

        detailText.setBounds(16, 78, 350, 15);

        card.add(detailText);

        return card;
    }

    // ================= GRADIENT PANEL =================
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

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            // shadow
            g2.setColor(new Color(0, 0, 0, 25));

            g2.fillRoundRect(
                    3,
                    3,
                    getWidth() - 3,
                    getHeight() - 3,
                    18,
                    18
            );

            // gradient
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
                    18,
                    18
            );

            g2.dispose();
        }
    }
}