/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.PilihanBundle;

/**
 *
 * @author Victus
 */
import Kinopedia.Main;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;

public class BundleValorant extends JFrame {

    private JFrame menuSebelumnya;
    private ImageIcon ikonMataUang;
    private ImageIcon logoBawah;

    private ArrayList<PanelBulat> daftarSemuaKartu = new ArrayList<>();
    private String bundleTerpilih = "";

    // === KOMPONEN INPUT (Dibuat class-level agar bisa diakses method validasi) ===
    private PanelBulat bungkusId;
    private PanelBulat bungkusNama;
    private PanelBulat kotakOranye;
    private JTextField kolomId;
    private JTextField kolomNama;

    public BundleValorant(JFrame menuSebelumnya) {
        this.menuSebelumnya = menuSebelumnya;

        setTitle("Valorant - Bundling");
        setSize(470, 844);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color warnaOranye = new Color(0xFF8C1A);
        Color warnaAbuAbu = new Color(0xBDBDBD);
        Color warnaMerah  = new Color(0xFF3B30); // Tambahkan warna merah

        ikonMataUang = muatGambar("/Kinopedia/model/IMAGESS/Valorant-pointss.png", 25, 25);
        logoBawah    = muatGambar("/Kinopedia/model/IMAGESS/LogoKinopedia.png", 50, 50);

        // --- Panel Utama ---
        JPanel panelAkar = new JPanel();
        panelAkar.setLayout(new BorderLayout());
        panelAkar.setBackground(Color.WHITE);
        panelAkar.setBorder(new EmptyBorder(14, 16, 14, 16));
        setContentPane(panelAkar);

        // --- Tombol Kembali ---
        JButton tombolKembali = new JButton("< Kembali");
        tombolKembali.setFocusPainted(false);
        tombolKembali.setBorderPainted(false);
        tombolKembali.setContentAreaFilled(false);
        tombolKembali.setFont(new Font("SansSerif", Font.PLAIN, 15));
        tombolKembali.setHorizontalAlignment(SwingConstants.LEFT);
        tombolKembali.addActionListener(e -> {
            dispose();
            if (this.menuSebelumnya != null) {
                this.menuSebelumnya.setVisible(true);
            }
        });

        JPanel barAtas = new JPanel(new BorderLayout());
        barAtas.setBackground(Color.WHITE);
        barAtas.add(tombolKembali, BorderLayout.WEST);
        panelAkar.add(barAtas, BorderLayout.NORTH);

        // --- Panel Isi ---
        JPanel panelIsi = new JPanel();
        panelIsi.setBackground(Color.WHITE);
        panelIsi.setLayout(new BoxLayout(panelIsi, BoxLayout.Y_AXIS));
        panelIsi.setBorder(new EmptyBorder(8, 30, 0, 30));
        panelAkar.add(panelIsi, BorderLayout.CENTER);

        // --- Input ID ---
        bungkusId = new PanelBulat(20, Color.WHITE, warnaOranye, 1);
        bungkusId.setLayout(new BorderLayout());
        bungkusId.setBorder(new EmptyBorder(8, 15, 8, 15));
        bungkusId.setMaximumSize(new Dimension(9999, 45));
        bungkusId.setAlignmentX(Component.LEFT_ALIGNMENT);

        kolomId = new JTextField("");
        kolomId.setFont(new Font("SansSerif", Font.PLAIN, 14));
        kolomId.setOpaque(false);
        kolomId.setBorder(null);
        bungkusId.add(kolomId, BorderLayout.CENTER);

        panelIsi.add(bungkusId);
        panelIsi.add(Box.createVerticalStrut(14));

        // --- Label ACCOUNT NAME ---
        JLabel teksAkun = new JLabel("ACCOUNT NAME");
        teksAkun.setFont(new Font("SansSerif", Font.BOLD, 11));
        teksAkun.setForeground(Color.DARK_GRAY);
        teksAkun.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelIsi.add(teksAkun);
        panelIsi.add(Box.createVerticalStrut(8));

        // --- Input Nama Akun (read-only) ---
        bungkusNama = new PanelBulat(20, warnaAbuAbu, warnaOranye, 1);
        bungkusNama.setLayout(new BorderLayout());
        bungkusNama.setBorder(new EmptyBorder(8, 15, 8, 15));
        bungkusNama.setMaximumSize(new Dimension(9999, 45));
        bungkusNama.setAlignmentX(Component.LEFT_ALIGNMENT);

        kolomNama = new JTextField("");
        kolomNama.setEditable(false);
        kolomNama.setFont(new Font("SansSerif", Font.PLAIN, 14));
        kolomNama.setOpaque(false);
        kolomNama.setBorder(null);
        bungkusNama.add(kolomNama, BorderLayout.CENTER);

        panelIsi.add(bungkusNama);
        panelIsi.add(Box.createVerticalStrut(20));

        // === VALIDASI ID SAAT USER SELESAI MENGETIK (Gaya CODM) ===
        kolomId.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                cekIdAkun(warnaOranye, warnaMerah);
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                cekIdAkun(warnaOranye, warnaMerah);
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                cekIdAkun(warnaOranye, warnaMerah);
            }
        });

        // --- AREA BUNDLE ---
        JPanel areaBundle = new JPanel();
        areaBundle.setLayout(new BoxLayout(areaBundle, BoxLayout.Y_AXIS));
        areaBundle.setBackground(Color.WHITE);
        areaBundle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel teksLabel = new JLabel("1. Bundling");
        teksLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        PanelBulat pilBundling = new PanelBulat(25, Color.WHITE, warnaOranye, 1);
        pilBundling.setLayout(new BorderLayout());
        pilBundling.setBorder(new EmptyBorder(8, 20, 10, 22));
        pilBundling.add(teksLabel, BorderLayout.CENTER);

        JPanel tempatLabel = new JPanel();
        tempatLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        tempatLabel.setOpaque(false);
        tempatLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        tempatLabel.setMaximumSize(new Dimension(9999, 45));
        tempatLabel.add(pilBundling);

        areaBundle.add(tempatLabel);
        areaBundle.add(Box.createVerticalStrut(-20));

        JPanel panelGrid = new JPanel();
        panelGrid.setLayout(new GridLayout(4, 2, 10, 10));
        panelGrid.setOpaque(false);

        String nama = " VP";
        buatKartu(panelGrid, "125"   + nama, "15000");
        buatKartu(panelGrid, "420"   + nama, "50000");
        buatKartu(panelGrid, "700"   + nama, "79000");
        buatKartu(panelGrid, "1375"  + nama, "150000");
        buatKartu(panelGrid, "2400"  + nama, "250000");
        buatKartu(panelGrid, "4000"  + nama, "400000");
        buatKartu(panelGrid, "8150"  + nama, "800000");
        buatKartu(panelGrid, "10500" + nama, "1000000");

        // Jadikan kotakOranye sebagai class-level
        kotakOranye = new PanelBulat(25, warnaOranye, null, 0);
        kotakOranye.setLayout(new BorderLayout());
        kotakOranye.setBorder(new EmptyBorder(30, 10, 20, 10));
        kotakOranye.setAlignmentX(Component.LEFT_ALIGNMENT);
        kotakOranye.add(panelGrid, BorderLayout.CENTER);

        areaBundle.add(kotakOranye);
        panelIsi.add(areaBundle);
        panelIsi.add(Box.createVerticalStrut(20));

        // --- PANEL BAWAH ---
        JPanel panelBawah = new JPanel();
        panelBawah.setBackground(Color.WHITE);
        panelBawah.setLayout(new BoxLayout(panelBawah, BoxLayout.Y_AXIS));
        panelBawah.setBorder(new EmptyBorder(10, 36, 0, 36));
        panelAkar.add(panelBawah, BorderLayout.SOUTH);

        PanelBulat bungkusTombolBayar = new PanelBulat(15, warnaOranye, null, 0);
        bungkusTombolBayar.setLayout(new BorderLayout());
        bungkusTombolBayar.setMaximumSize(new Dimension(9999, 50));
        bungkusTombolBayar.setAlignmentX(Component.LEFT_ALIGNMENT);
        bungkusTombolBayar.setBorder(new EmptyBorder(14, 0, 14, 0));

        JLabel teksTombolBayar = new JLabel("Pilih Metode Pembayaran", SwingConstants.CENTER);
        teksTombolBayar.setFont(new Font("SansSerif", Font.BOLD, 15));
        teksTombolBayar.setForeground(Color.WHITE);
        bungkusTombolBayar.add(teksTombolBayar, BorderLayout.CENTER);

        // === LOGIKA TOMBOL PILIH METODE PEMBAYARAN (Gaya CODM) ===
        bungkusTombolBayar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Cek apakah kolom nama sudah terisi dan bukan pesan error
                boolean akunSudahTerisi = !kolomNama.getText().isEmpty()
                                          && !kolomNama.getText().equals("Akun Tidak Ditemukan");

                // Cek apakah user sudah memilih bundle
                boolean bundleSudahDipilih = !bundleTerpilih.isEmpty();

                // Jika akun belum terisi, border ID jadi merah
                if (!akunSudahTerisi) {
                    bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 1);
                }

                // Jika bundle belum dipilih, border kotak oranye jadi merah
                if (!bundleSudahDipilih) {
                    kotakOranye.ubahTampilan(warnaOranye, warnaMerah, 2);
                }

                // Jika keduanya sudah benar, lanjut ke halaman pembayaran
                if (akunSudahTerisi && bundleSudahDipilih) {
                    dispose();
                    new Kinopedia.model.MetodeBayar(kolomNama.getText(),kolomId.getText(), Integer.parseInt(bundleTerpilih), "valorant", Kinopedia.Session.getInstance().getCurrentUser().getNama()).setVisible(true);
                    // TODO: lanjut ke halaman metode pembayaran
                }
            }
        });

        panelBawah.add(bungkusTombolBayar);
        panelBawah.add(Box.createVerticalStrut(14));

        JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLogo.setBackground(Color.WHITE);
        panelLogo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel labelLogo = new JLabel("Kinopedia");
        if (this.logoBawah != null) {
            labelLogo = new JLabel(this.logoBawah);
        }
        panelLogo.add(labelLogo);
        panelBawah.add(panelLogo);
    }

    // === METHOD: CEK ID AKUN VALORANT (Gaya CODM) ===
    private void cekIdAkun(Color warnaOranye, Color warnaMerah) {
        String idDiInput = kolomId.getText().trim();

        // Langkah 1: Cek format (harus ada "#" di posisi index ke-6)
        int posisiPagar = idDiInput.indexOf("#");

        if (posisiPagar == -1) {
            // Format salah — border merah, kolom nama dikosongkan
            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
            kolomNama.setText("");
            kolomNama.setForeground(Color.BLACK);
            return;
        }

        String bagianDepan    = idDiInput.substring(0, posisiPagar);
        String bagianBelakang = idDiInput.substring(posisiPagar + 1);

        if (bagianDepan.length() != 6 || bagianBelakang.length() != 4) {
            // Format salah — border merah, kolom nama dikosongkan
            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
            kolomNama.setText("");
            kolomNama.setForeground(Color.BLACK);
            return;
        }

        // Format benar — cari ID di Main.dataAkun
        boolean akunDitemukan = false;

        for (int i = 0; i < Main.dataAkun.size(); i++) {
            String idDiDatabase   = Main.dataAkun.get(i).getIdAkun();
            String namaDiDatabase = Main.dataAkun.get(i).getNamaAkun();
            String gameDiDatabase = Main.dataAkun.get(i).getJenisGame();

            // Cocokkan ID dan pastikan jenisGame-nya "valorant"
            boolean idSama    = idDiDatabase.equals(idDiInput);
            boolean gameSama  = gameDiDatabase.equals("valorant");

            if (idSama && gameSama) {
                kolomNama.setText(namaDiDatabase);
                kolomNama.setForeground(Color.BLACK);
                bungkusId.ubahTampilan(Color.WHITE, warnaOranye, 1);
                akunDitemukan = true;
                break;
            }
        }
    }

    public void buatKartu(JPanel grid, String judul, String harga) {
        PanelBulat kartu = new PanelBulat(15, Color.WHITE, null, 0);
        kartu.setLayout(new BoxLayout(kartu, BoxLayout.Y_AXIS));
        kartu.setBorder(new EmptyBorder(8, 16, 8, 4));

        JLabel gambarIkon = new JLabel("?");
        if (this.ikonMataUang != null) {
            gambarIkon = new JLabel(this.ikonMataUang);
        }
        gambarIkon.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel teksJudul = new JLabel(judul);
        teksJudul.setFont(new Font("SansSerif", Font.BOLD, 15));
        teksJudul.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel teksHarga = new JLabel("Rp "+harga);
        teksHarga.setFont(new Font("SansSerif", Font.PLAIN, 18));
        teksHarga.setAlignmentX(Component.LEFT_ALIGNMENT);

        kartu.add(gambarIkon);
        kartu.add(Box.createVerticalStrut(6));
        kartu.add(teksJudul);
        kartu.add(Box.createVerticalStrut(2));
        kartu.add(teksHarga);

        grid.add(kartu);
        daftarSemuaKartu.add(kartu);

        kartu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Reset kotak oranye jika sebelumnya merah karena error
                if (kotakOranye != null) {
                    kotakOranye.ubahTampilan(new Color(0xFF8C1A), null, 0);
                }
                
                for (PanelBulat k : daftarSemuaKartu) {
                    k.ubahTampilan(Color.WHITE, null, 0);
                }
                kartu.ubahTampilan(new Color(220, 220, 220), null, 2);
                bundleTerpilih = harga;
                System.out.println("User memilih: " + bundleTerpilih);
            }
        });
    }

    public ImageIcon muatGambar(String path, int lebar, int tinggi) {
        URL lokasi = getClass().getResource(path);
        if (lokasi == null) {
            System.out.println("Gambar tidak ditemukan: " + path);
            return null;
        }
        Image gambar    = new ImageIcon(lokasi).getImage();
        Image gambarPas = gambar.getScaledInstance(lebar, tinggi, Image.SCALE_SMOOTH);
        return new ImageIcon(gambarPas);
    }

    class PanelBulat extends JPanel {

        private int radiusLengkungan;
        private Color warnaLatar;
        private Color warnaGaris;
        private int tebalGaris;

        public PanelBulat(int radius, Color warnaLatar, Color warnaGaris, int tebalGaris) {
            super();
            this.radiusLengkungan = radius;
            this.warnaLatar = warnaLatar;
            this.warnaGaris = warnaGaris;
            this.tebalGaris = tebalGaris;
            setOpaque(false);
        }

        public void ubahTampilan(Color warnaLatarBaru, Color warnaGarisBaru, int tebalGarisBaru) {
            this.warnaLatar = warnaLatarBaru;
            this.warnaGaris = warnaGarisBaru;
            this.tebalGaris = tebalGarisBaru;
            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int posisiX = this.tebalGaris / 2;
            int posisiY = this.tebalGaris / 2;
            int lebarPanel = getWidth() - this.tebalGaris - 1;
            int tinggiPanel = getHeight() - this.tebalGaris - 1;

            if (this.warnaLatar != null) {
                g2.setColor(this.warnaLatar);
                g2.fillRoundRect(posisiX, posisiY, lebarPanel, tinggiPanel, this.radiusLengkungan, this.radiusLengkungan);
            }

            if (this.warnaGaris != null && this.tebalGaris > 0) {
                g2.setColor(this.warnaGaris);
                g2.setStroke(new BasicStroke(this.tebalGaris));
                g2.drawRoundRect(posisiX, posisiY, lebarPanel, tinggiPanel, this.radiusLengkungan, this.radiusLengkungan);
            }

            g2.dispose();
        }
    }
}