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
import Kinopedia.HalamanConfirmation;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.AbstractBorder;

public class TransaksiBelumTop extends JFrame {

    // Tema warna merah
    private static final Color MERAH_UTAMA  = new Color(139, 0, 0);
    private static final Color MERAH_PANEL  = new Color(155, 28, 28);
    private static final Color MERAH_CARD   = new Color(175, 40, 40);
    private static final Color MERAH_BTN    = new Color(200, 30, 30);
    private static final Color PUTIH        = Color.WHITE;
    private static final Color HITAM        = Color.BLACK;
    private static final Color ABU_BG       = new Color(245, 245, 245);

    private final DataTransaksi transaksiRef;

    public TransaksiBelumTop(
            String idTransaksi,
            String tanggalWaktu,
            String usernameIngame,
            String idUser,
            String game,
            String metodePembayaran,
            int totalHarga,
            DataTransaksi transaksiRef
    ) {
        this.transaksiRef = transaksiRef;

        setTitle("Kinopedia – Detail Tagihan");
        setSize(470, 844);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(ABU_BG);

        buatTombolKembali();
        buatPanelAtas(idTransaksi, tanggalWaktu);
        buatPanelDetailPembayaran(usernameIngame, idUser, game, metodePembayaran, totalHarga);
        buatTombolKonfirmasi(totalHarga);
        buatLogo();

        setVisible(true);
    }

    // ----------------------------------------------------------------- kembali

    private void buatTombolKembali() {
        JLabel btnBack = new JLabel("‹");
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 34));
        btnBack.setForeground(Color.black);
        btnBack.setBounds(20, 18, 40, 40);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel labelKembali = new JLabel("Kembali");
        labelKembali.setFont(new Font("SansSerif", Font.PLAIN, 16));
        labelKembali.setForeground(Color.black);
        labelKembali.setBounds(50, 25, 100, 28);
        labelKembali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        MouseAdapter kembali = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new DaftarTagihanBelumTop();
                dispose();
            }
        };
        btnBack.addMouseListener(kembali);
        labelKembali.addMouseListener(kembali);

        add(btnBack);
        add(labelKembali);
    }

    // --------------------------------------------------------------- panel atas

    private void buatPanelAtas(String idTransaksi, String tanggalWaktu) {
        JPanel panelAtas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(MERAH_PANEL);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }
        };
        panelAtas.setBounds(25, 75, 415, 220);
        panelAtas.setLayout(null);
        panelAtas.setOpaque(false);
        add(panelAtas);

        // Header
        JLabel headerLabel = new JLabel("Informasi & Transaksi");
        headerLabel.setBounds(20, 0, 280, 55);
        headerLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        headerLabel.setForeground(PUTIH);
        panelAtas.add(headerLabel);

        // Badge status "Belum Di Proses"
        JPanel badgePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(220, 50, 50));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
            }
        };
        badgePanel.setLayout(new GridBagLayout());
        badgePanel.setOpaque(false);
        badgePanel.setBounds(265, 13, 130, 28);

        JLabel badgeText = new JLabel("● Belum Di Proses");
        badgeText.setFont(new Font("SansSerif", Font.BOLD, 10));
        badgeText.setForeground(new Color(255, 200, 200));
        badgePanel.add(badgeText);
        panelAtas.add(badgePanel);

        // ID Transaksi
        JLabel lblHeaderID = new JLabel("ID Transaksi");
        lblHeaderID.setBounds(20, 55, 300, 30);
        lblHeaderID.setFont(new Font("Poppins", Font.PLAIN, 14));
        lblHeaderID.setForeground(new Color(255, 200, 200));
        panelAtas.add(lblHeaderID);

        JLabel lblNilaiID = new JLabel(idTransaksi);
        lblNilaiID.setBounds(20, 78, 370, 30);
        lblNilaiID.setFont(new Font("Poppins", Font.BOLD, 18));
        lblNilaiID.setForeground(PUTIH);
        panelAtas.add(lblNilaiID);

        // Garis pemisah
        JSeparator garis = new JSeparator();
        garis.setBounds(20, 115, 375, 1);
        garis.setForeground(new Color(200, 100, 100));
        panelAtas.add(garis);

        // Tanggal & Waktu
        JLabel lblHeaderTnW = new JLabel("Tanggal & Waktu");
        lblHeaderTnW.setBounds(20, 122, 300, 30);
        lblHeaderTnW.setFont(new Font("Poppins", Font.PLAIN, 14));
        lblHeaderTnW.setForeground(new Color(255, 200, 200));
        panelAtas.add(lblHeaderTnW);

        JLabel lblNilaiTnW = new JLabel(tanggalWaktu);
        lblNilaiTnW.setBounds(20, 147, 370, 30);
        lblNilaiTnW.setFont(new Font("Poppins", Font.BOLD, 18));
        lblNilaiTnW.setForeground(PUTIH);
        panelAtas.add(lblNilaiTnW);
    }

    // --------------------------------------------------------- panel pembayaran

    private void buatPanelDetailPembayaran(
            String usernameIngame,
            String idUser,
            String game,
            String metodePembayaran,
            int totalHarga
    ) {
        JLabel lblDetail = new JLabel("Detail Pembayaran");
        lblDetail.setBounds(25, 315, 250, 35);
        lblDetail.setFont(new Font("Poppins", Font.BOLD, 16));
        lblDetail.setForeground(HITAM);
        add(lblDetail);

        // Container utama
        JPanel containerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(PUTIH);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }
        };
        containerPanel.setBounds(25, 358, 415, 310);
        containerPanel.setLayout(null);
        containerPanel.setOpaque(false);
        containerPanel.setBorder(new RoundedBorder(20, MERAH_PANEL));
        add(containerPanel);

        // Background merah untuk baris data
        JPanel bgMerah = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(MERAH_CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
                // Isi pojok bawah agar tidak ada radius di bawah
                g2.fillRect(0, getHeight() - 20, getWidth(), 20);
                g2.dispose();
            }
        };
        bgMerah.setBounds(0, 0, 415, 230);
        bgMerah.setLayout(null);
        bgMerah.setOpaque(false);
        containerPanel.add(bgMerah);

        // Baris: Nama
        tambahBaris(bgMerah, "Nama", usernameIngame, 15);
        tambahGaris(bgMerah, 58);

        // Baris: ID
        tambahBaris(bgMerah, "ID", idUser, 62);
        tambahGaris(bgMerah, 108);

        // Baris: Game / Aplikasi
        tambahBaris(bgMerah, "Game / Aplikasi", game, 112);
        tambahGaris(bgMerah, 158);

        // Baris: Metode Pembayaran
        tambahBaris(bgMerah, "Pembayaran", metodePembayaran, 163);

        // Total pembayaran (di luar bgMerah)
        JLabel lblTotalHeader = new JLabel("Total Pembayaran");
        lblTotalHeader.setBounds(20, 240, 375, 25);
        lblTotalHeader.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblTotalHeader.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalHeader.setForeground(Color.GRAY);
        containerPanel.add(lblTotalHeader);

        JLabel lblTotalNilai = new JLabel("Rp " + String.format("%,d", totalHarga).replace(',', '.'));
        lblTotalNilai.setBounds(20, 260, 375, 40);
        lblTotalNilai.setFont(new Font("Poppins", Font.BOLD, 26));
        lblTotalNilai.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalNilai.setForeground(HITAM);
        containerPanel.add(lblTotalNilai);
    }

    private void tambahBaris(JPanel parent, String header, String nilai, int y) {
        JLabel lblHeader = new JLabel(header);
        lblHeader.setBounds(20, y, 160, 40);
        lblHeader.setFont(new Font("Poppins", Font.PLAIN, 15));
        lblHeader.setForeground(new Color(255, 200, 200));
        parent.add(lblHeader);

        JLabel lblNilai = new JLabel(nilai);
        lblNilai.setBounds(20, y, 375, 40);
        lblNilai.setFont(new Font("Poppins", Font.PLAIN, 14));
        lblNilai.setForeground(PUTIH);
        lblNilai.setHorizontalAlignment(SwingConstants.RIGHT);
        parent.add(lblNilai);
    }

    private void tambahGaris(JPanel parent, int y) {
        JSeparator garis = new JSeparator();
        garis.setBounds(20, y, 375, 1);
        garis.setForeground(new Color(180, 60, 60));
        parent.add(garis);
    }

    // --------------------------------------------------------------- tombol

    private void buatTombolKonfirmasi(int totalHarga) {
        // ---- Tombol Konfirmasi (Setujui top-up) ----
        JButton btnKonfirmasi = new JButton("Konfirmasi Top-Up") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(MERAH_BTN);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnKonfirmasi.setBounds(25, 690, 415, 48);
        btnKonfirmasi.setContentAreaFilled(false);
        btnKonfirmasi.setOpaque(false);
        btnKonfirmasi.setForeground(PUTIH);
        btnKonfirmasi.setFont(new Font("Poppins", Font.BOLD, 15));
        btnKonfirmasi.setFocusPainted(false);
        btnKonfirmasi.setBorder(BorderFactory.createEmptyBorder());
        btnKonfirmasi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(btnKonfirmasi);

        // ---- Tombol Tolak (merah outline) ----
//        JButton btnTolak = new JButton("Tolak / Abaikan") {
//            @Override
//            protected void paintComponent(Graphics g) {
//                Graphics2D g2 = (Graphics2D) g.create();
//                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                g2.setColor(PUTIH);
//                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
//                g2.dispose();
//                super.paintComponent(g);
//            }
//        };
//        btnTolak.setBounds(25, 745, 415, 48);
//        btnTolak.setContentAreaFilled(false);
//        btnTolak.setOpaque(false);
//        btnTolak.setForeground(MERAH_BTN);
//        btnTolak.setFont(new Font("Poppins", Font.BOLD, 15));
//        btnTolak.setFocusPainted(false);
//        btnTolak.setBorder(new RoundedBorder(16, MERAH_BTN));
//        btnTolak.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        add(btnTolak);

        // ---- Listener Konfirmasi ----
        btnKonfirmasi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Tandai transaksi sebagai sudah diproses
                if (transaksiRef != null) {
                    transaksiRef.setKonfirmasi(true);
                }

                // Tambah kredit ke seller (konversi harga → koin)
                int tambahKredit = totalHarga / 50000;
                if (Kinopedia.Session.getInstance().getCurrentUser() != null) {
                    Kinopedia.Session.getInstance().getCurrentUser()
                            .setKredit(Kinopedia.Session.getInstance().getCurrentUser().getKredit() + tambahKredit);
                }

                // Simpan data
                Kinopedia.Main.saveSemuaData(
                        Kinopedia.Main.dataTransaksi,
                        Kinopedia.Main.dataUser,
                        Kinopedia.Main.admin
                );

                dispose();
                new HalamanConfirmation(
                        "Kembali ke Menu Seller",
                        true,
                        "Pembayaran Dikonfirmasi",
                        "Top-up berhasil diproses!",
                        "Seller",
                        new Color(139, 0, 0)
                ).setVisible(true);
            }
        });

        // ---- Listener Tolak ----
//        btnTolak.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int pilihan = JOptionPane.showConfirmDialog(
//                        TransaksiBelumTop.this,
//                        "Yakin ingin menolak/mengabaikan tagihan ini?",
//                        "Konfirmasi Tolak",
//                        JOptionPane.YES_NO_OPTION,
//                        JOptionPane.WARNING_MESSAGE
//                );
//                if (pilihan == JOptionPane.YES_OPTION) {
//                    dispose();
//                    new DaftarTagihanBelumTop();
//                }
//            }
//        });
    }

    // -------------------------------------------------------------------- logo

    private void buatLogo() {
        JLabel logo = new JLabel("K");
        logo.setFont(new Font("SansSerif", Font.BOLD, 34));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setBounds(215, 800, 40, 40);
        add(logo);
    }

    // ============================================================ RoundedBorder

    static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color color;

        public RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x, y, w - 1, h - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius / 2, radius / 2, radius / 2, radius / 2);
        }
    }

    // ---------------------------------------------------------- main (preview)

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new TransaksiBelumTop(
                        "#INV-20240521-005",
                        "21-05-2024 · 17:30",
                        "KelvinANgajay123",
                        "UID-98765",
                        "Valorant",
                        "OVO",
                        100000,
                        null
                )
        );
    }
}