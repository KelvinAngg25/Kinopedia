/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.model.Seller;

/**
 *
 * @author Fabiola
 */

import Kinopedia.DataTransaksi;
import Kinopedia.Main;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class DaftarTagihanBelumTop extends JFrame {

    // Warna tema merah
    private static final Color MERAH_GELAP   = new Color(139, 0, 0);
    private static final Color MERAH_CARD    = new Color(160, 30, 30);
    private static final Color MERAH_HOVER   = new Color(180, 50, 50);
    private static final Color PUTIH         = Color.WHITE;
    private static final Color ABU_TERANG    = new Color(245, 245, 245);

    private JPanel mainPanel;
    private JPanel listPanel;

    public DaftarTagihanBelumTop() {
        setTitle("Tagihan Belum Di Top-Up");
        setSize(470, 844);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initComponent();
        setVisible(true);
    }

    private void initComponent() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(ABU_TERANG);

        buatHeader();
        buatListTagihan();
        buatLogo();

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBounds(0, 100, 470, 690);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(ABU_TERANG);
        scrollPane.getViewport().setBackground(ABU_TERANG);

        mainPanel.add(scrollPane);
        add(mainPanel);
    }

    // ------------------------------------------------------------------ header

    private void buatHeader() {
        // Tombol kembali
        JLabel btnBack = new JLabel("‹");
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 34));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBounds(20, 18, 40, 40);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        JLabel labelKembali = new JLabel("Kembali");
        labelKembali.setFont(new Font("SansSerif", Font.PLAIN, 16));
        labelKembali.setForeground(Color.BLACK);
        labelKembali.setBounds(50, 25, 100, 28);
        labelKembali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelKembali.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Kinopedia.model.Seller.Seller();
                dispose();
            }
        });

        mainPanel.add(btnBack);
        mainPanel.add(labelKembali);
    }

    // --------------------------------------------------------------- list card

    private void buatListTagihan() {
        // Ambil transaksi yang belum diproses dari data global
        List<DataTransaksi> daftarBelum = ambilTransaksiBelumTopUp();

        // Hitung tinggi panel dinamis (min 690)
        int tinggiPanel = Math.max(690, daftarBelum.size() * 90 + 20);

        listPanel = new JPanel();
        listPanel.setLayout(null);
        listPanel.setBackground(ABU_TERANG);
        listPanel.setPreferredSize(new Dimension(470, tinggiPanel));

        int y = 15;
        for (DataTransaksi t : daftarBelum) {
            JPanel card = buatCard(t);
            card.setBounds(20, y, 425, 75);
            listPanel.add(card);
            y += 90;
        }

        if (daftarBelum.isEmpty()) {
            JLabel kosong = new JLabel("Tidak ada tagihan yang belum diproses");
            kosong.setFont(new Font("SansSerif", Font.PLAIN, 14));
            kosong.setForeground(Color.GRAY);
            kosong.setBounds(60, 280, 350, 30);
            kosong.setHorizontalAlignment(SwingConstants.CENTER);
            listPanel.add(kosong);
        }
    }

    private JPanel buatCard(DataTransaksi transaksi) {
        RoundedPanel card = new RoundedPanel(MERAH_CARD, null, 16);
        card.setLayout(null);
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // ID Transaksi — label kecil
        JLabel labelID = new JLabel("ID Transaksi");
        labelID.setFont(new Font("SansSerif", Font.PLAIN, 11));
        labelID.setForeground(new Color(255, 200, 200));
        labelID.setBounds(16, 10, 200, 16);

        // Nilai ID Transaksi
        JLabel nilaiID = new JLabel(transaksi.getIdTransaksi());
        nilaiID.setFont(new Font("SansSerif", Font.BOLD, 15));
        nilaiID.setForeground(PUTIH);
        nilaiID.setBounds(16, 26, 280, 20);

        // Tanggal & waktu
        JLabel waktu = new JLabel(transaksi.getTanggal());
        waktu.setFont(new Font("SansSerif", Font.PLAIN, 12));
        waktu.setForeground(new Color(255, 200, 200));
        waktu.setBounds(16, 48, 250, 16);
        
        nilaiID.setText(transaksi.getIdTransaksi());      // bukan getIDTransaksi()
        waktu.setText(transaksi.getTanggal() + " · " + transaksi.getWaktu());

        // Panah kanan
        JLabel panah = new JLabel("›");
        panah.setFont(new Font("SansSerif", Font.BOLD, 30));
        panah.setForeground(PUTIH);
        panah.setBounds(390, 18, 25, 40);

        card.add(labelID);
        card.add(nilaiID);
        card.add(waktu);
        card.add(panah);

        // Hover effect
        card.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                ((RoundedPanel) card).setBg(MERAH_HOVER);
//                card.repaint();
//            }
//            @Override
//            public void mouseExited(MouseEvent e) {
//                ((RoundedPanel) card).setBg(MERAH_CARD);
//                card.repaint();
//            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Buka halaman detail transaksi seller
                new TransaksiBelumTop(
                        transaksi.getIdTransaksi(),
                        transaksi.getTanggal() + " · " + transaksi.getWaktu(),
                        transaksi.getNamaAkun(),
                        transaksi.getIdGame(),
                        transaksi.getJenisGame(),
                        transaksi.getPembayaran(),
                        transaksi.getNominal(),
                        transaksi
                );
                dispose();
            }
        });
        return card;
    }

    // ----------------------------------------------------------- data provider

    private List<DataTransaksi> ambilTransaksiBelumTopUp() {
        List<DataTransaksi> hasil = new ArrayList<>();
        if (Main.dataTransaksi != null && !Main.dataTransaksi.isEmpty()) {
            for (DataTransaksi t : Main.dataTransaksi) {
                if (t != null && !t.isKonfirmasi()) {
                    hasil.add(t);
                }
            }
        }
        return hasil;
    }

    // -------------------------------------------------------------------- logo

    private void buatLogo() {
        JLabel logo = new JLabel("K");
        logo.setFont(new Font("SansSerif", Font.BOLD, 34));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setBounds(215, 795, 40, 40);
        mainPanel.add(logo);
    }

    // ============================================================ inner classes

    class RoundedPanel extends JPanel {
        private Color bg;
        private final Color border;
        private final int radius;

        public RoundedPanel(Color bg, Color border, int radius) {
            this.bg = bg;
            this.border = border;
            this.radius = radius;
            setOpaque(false);
        }

        public void setBg(Color bg) {
            this.bg = bg;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            if (border != null) {
                g2.setColor(border);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            }
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ---------------------------------------------------------- main (preview)

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DaftarTagihanBelumTop::new);
    }
}