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

public class BundleEfootball extends JFrame {

    private JFrame menuSebelumnya;
    private ImageIcon ikonMataUang;
    private ImageIcon logoBawah;

    private ArrayList<PanelBulat> daftarSemuaKartu = new ArrayList<>();
    private String bundleTerpilih = "";

    // === KOMPONEN INPUT ===
    private PanelBulat bungkusId;
    private PanelBulat bungkusNama;
    private PanelBulat kotakOranye;
    private JTextField kolomId;
    private JTextField kolomNama;

    public BundleEfootball(JFrame menuSebelumnya) {
        this.menuSebelumnya = menuSebelumnya;

        setTitle("eFootball - Bundling");
        setSize(470, 844);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color warnaOranye = new Color(0xFF8C1A);
        Color warnaAbuAbu = new Color(0xBDBDBD);
        Color warnaMerah = new Color(0xFF3B30);

        ikonMataUang = muatGambar("/Kinopedia/model/IMAGESS/efootballgolds.png", 25, 25);
        logoBawah = muatGambar("/Kinopedia/model/IMAGESS/LogoKinopedia.png", 50, 50);

        // --- Panel Utama Dasar (Background) ---
        JPanel panelAkar = new JPanel();
        panelAkar.setLayout(new BorderLayout());
        panelAkar.setBackground(Color.WHITE);
        panelAkar.setBorder(new EmptyBorder(14, 16, 14, 16));
        setContentPane(panelAkar);

        // --- Bagian Atas (Tombol Kembali) ---
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

        JPanel barAtas = new JPanel();
        barAtas.setLayout(new BorderLayout());
        barAtas.setBackground(Color.WHITE);
        barAtas.add(tombolKembali, BorderLayout.WEST);
        panelAkar.add(barAtas, BorderLayout.NORTH);

        // --- Bagian Tengah ---
        JPanel panelIsi = new JPanel();
        panelIsi.setBackground(Color.WHITE);
        panelIsi.setLayout(new BoxLayout(panelIsi, BoxLayout.Y_AXIS));
        panelIsi.setBorder(new EmptyBorder(8, 30, 0, 30));
        panelAkar.add(panelIsi, BorderLayout.CENTER);

        // --- Label & Input NUMBER ID ---
        JLabel teksId = new JLabel("NUMBER ID");
        teksId.setFont(new Font("SansSerif", Font.BOLD, 11));
        teksId.setForeground(Color.DARK_GRAY);
        teksId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelIsi.add(teksId);
        panelIsi.add(Box.createVerticalStrut(4));

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

        // --- Label & Input ACCOUNT NAME ---
        JLabel teksAkun = new JLabel("ACCOUNT NAME");
        teksAkun.setFont(new Font("SansSerif", Font.BOLD, 11));
        teksAkun.setForeground(Color.DARK_GRAY);
        teksAkun.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelIsi.add(teksAkun);
        panelIsi.add(Box.createVerticalStrut(8));

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

        // === VALIDASI ID SAAT USER SELESAI MENGETIK ===
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
        pilBundling.setBorder(new EmptyBorder(8, 20, 8, 20));
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

        String nama = " Coins";
        buatKartu(panelGrid, "137" + nama,   "16200");
        buatKartu(panelGrid, "578" + nama,   "66700");
        buatKartu(panelGrid, "1092" + nama,   "124300");
        buatKartu(panelGrid, "2237" + nama,  "253000");
        buatKartu(panelGrid, "3413" + nama,  "378300");
        buatKartu(panelGrid, "5985" + nama,  "630600");
        buatKartu(panelGrid, "13440" + nama,  "1351000");
        buatKartu(panelGrid, "32200" + nama, "3153100");

        kotakOranye = new PanelBulat(25, warnaOranye, null, 0);
        kotakOranye.setLayout(new BorderLayout());
        kotakOranye.setBorder(new EmptyBorder(30, 10, 20, 10));
        kotakOranye.setAlignmentX(Component.LEFT_ALIGNMENT);
        kotakOranye.add(panelGrid, BorderLayout.CENTER);
        areaBundle.add(kotakOranye);

        panelIsi.add(areaBundle);
        panelIsi.add(Box.createVerticalStrut(20));

        // --- Panel Bawah ---
        JPanel panelBawah = new JPanel();
        panelBawah.setBackground(Color.WHITE);
        panelBawah.setLayout(new BoxLayout(panelBawah, BoxLayout.Y_AXIS));
        panelBawah.setBorder(new EmptyBorder(10, 30, 0, 30));
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

        // === LOGIKA TOMBOL PILIH METODE PEMBAYARAN ===
        bungkusTombolBayar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                boolean akunSudahTerisi = !kolomNama.getText().isEmpty()
                        && !kolomNama.getText().equals("Akun Tidak Ditemukan");

                boolean bundleSudahDipilih = !bundleTerpilih.isEmpty();

                if (!akunSudahTerisi) {
                    bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 1);
                }

                if (!bundleSudahDipilih) {
                    kotakOranye.ubahTampilan(warnaOranye, warnaMerah, 2);
                }

                if (akunSudahTerisi && bundleSudahDipilih) {
                    dispose();
                    new Kinopedia.model.MetodeBayar(kolomNama.getText(),kolomId.getText(), Integer.parseInt(bundleTerpilih), "efootball", Kinopedia.Session.getInstance().getCurrentUser().getNama()).setVisible(true);
                }
            }
        });

        panelBawah.add(bungkusTombolBayar);
        panelBawah.add(Box.createVerticalStrut(14));

        JPanel panelLogo = new JPanel();
        panelLogo.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelLogo.setBackground(Color.WHITE);
        panelLogo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel labelLogo = new JLabel("Kinopedia");
        if (this.logoBawah != null) {
            labelLogo = new JLabel(this.logoBawah);
        }
        panelLogo.add(labelLogo);
        panelBawah.add(panelLogo);
    }

    // === METHOD: CEK ID AKUN EFOOTBALL ===
    // Format eFootball: 4 huruf + tanda "-" + 6 angka. Contoh: ASAA-845389
    private void cekIdAkun(Color warnaOranye, Color warnaMerah) {
        String inputId = kolomId.getText().trim();

        // Langkah 1: Cek panjang totalnya harus 11 karakter (4 huruf + 1 strip + 6 angka)
        boolean panjangYangBenar = inputId.length() == 11;

        // Langkah 2: Cek karakter ke-5 harus tanda "-"
        boolean adaTandaStrip = false;
        if (panjangYangBenar) {
            adaTandaStrip = inputId.charAt(4) == '-';
        }

        // Langkah 3: Cek 4 karakter pertama harus semua huruf
        boolean empatHurufDepan = true;
        if (panjangYangBenar) {
            for (int i = 0; i < 4; i++) {
                if (!Character.isLetter(inputId.charAt(i))) {
                    empatHurufDepan = false;
                }
            }
        }

        // Langkah 4: Cek 6 karakter terakhir harus semua angka
        boolean enamAngkaBelakang = true;
        if (panjangYangBenar) {
            for (int i = 5; i < 11; i++) {
                if (!Character.isDigit(inputId.charAt(i))) {
                    enamAngkaBelakang = false;
                }
            }
        }

        // Format benar hanya jika semua langkah terpenuhi
        boolean formatBenar = panjangYangBenar && adaTandaStrip && empatHurufDepan && enamAngkaBelakang;

        if (!formatBenar) {
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

            boolean idSama   = idDiDatabase.equals(inputId);
            boolean gameSama = gameDiDatabase.equals("efootball");

            if (idSama && gameSama) {
                kolomNama.setText(namaDiDatabase);
                kolomNama.setForeground(Color.BLACK);
                bungkusId.ubahTampilan(Color.WHITE, warnaOranye, 1);
                akunDitemukan = true;
                break;
            }
        }

        if (!akunDitemukan) {
            kolomNama.setText("Akun Tidak Ditemukan");
            kolomNama.setForeground(warnaMerah);
            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
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
            public void mouseClicked(MouseEvent e) {
                // Reset semua kartu ke putih
                for (PanelBulat k : daftarSemuaKartu) {
                    k.ubahTampilan(Color.WHITE, null, 0);
                }
                // Tandai kartu yang dipilih dengan warna abu-abu
                kartu.ubahTampilan(new Color(220, 220, 220),null, 2);
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
        Image gambar = new ImageIcon(lokasi).getImage();
        Image gambarPas = gambar.getScaledInstance(lebar, tinggi, Image.SCALE_SMOOTH);
        return new ImageIcon(gambarPas);
    }
    
    // --- KELAS PANEL CUSTOM UNTUK MEMBUAT SUDUT MEMBULAT (ROUNDED CORNER) ---
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

        // METHOD BARU: Mengubah Warna Latar Belakang DAN Garis sekaligus
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

            // Calculate a single set of coordinates so the fill and border align perfectly
            int posisiX = this.tebalGaris / 2;
            int posisiY = this.tebalGaris / 2;
            int lebarPanel = getWidth() - this.tebalGaris - 1;
            int tinggiPanel = getHeight() - this.tebalGaris - 1;

            // Render Latar Belakang
            if (this.warnaLatar != null) {
                g2.setColor(this.warnaLatar);
                g2.fillRoundRect(posisiX, posisiY, lebarPanel, tinggiPanel, this.radiusLengkungan, this.radiusLengkungan);
            }

            // Render Garis/Border
            if (this.warnaGaris != null && this.tebalGaris > 0) {
                g2.setColor(this.warnaGaris);
                g2.setStroke(new BasicStroke(this.tebalGaris));
                g2.drawRoundRect(posisiX, posisiY, lebarPanel, tinggiPanel, this.radiusLengkungan, this.radiusLengkungan);
            }

            g2.dispose();
        }
    }
}
