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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;

public class BundleCODM extends JFrame {

    private JFrame menuSebelumnya;
    private ImageIcon ikonMataUang;
    private ImageIcon logoBawah;
    
    // === VARIABEL UNTUK FITUR KLIK ===
    private ArrayList<PanelBulat> daftarSemuaKartu = new ArrayList<>();
    private String bundleTerpilih = "";

    public BundleCODM(JFrame menuSebelumnya) {
        this.menuSebelumnya = menuSebelumnya;

        setTitle("CODM - Bundling");
        setSize(470, 844);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color warnaOranye = new Color(0xFF8C1A);
        Color warnaAbuAbu = new Color(0xBDBDBD);

        // Memuat ikon CP CODM dan Logo Kinopedia
        ikonMataUang = muatGambar("/Kinopedia/model/IMAGESS/CPs-CODM.png", 25, 25);
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

        JPanel panelIsi = new JPanel();
        panelIsi.setBackground(Color.WHITE);
        panelIsi.setLayout(new BoxLayout(panelIsi, BoxLayout.Y_AXIS));
        // Padding 36 agar UI memendek ke tengah, selaras dengan Kotak Oranye
        panelIsi.setBorder(new EmptyBorder(8, 36, 0, 36));

//        JScrollPane guliran = new JScrollPane(panelIsi);
//        guliran.setBorder(null);
//        guliran.getViewport().setBackground(Color.WHITE);
//        guliran.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelAkar.add(panelIsi, BorderLayout.CENTER);

        // --- Label & Input ID Game ---
        JLabel teksId = new JLabel("NUMBER ID");
        teksId.setFont(new Font("SansSerif", Font.BOLD, 11));
        teksId.setForeground(Color.DARK_GRAY);
        teksId.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelIsi.add(teksId);
        panelIsi.add(Box.createVerticalStrut(4));

        PanelBulat bungkusId = new PanelBulat(20, Color.WHITE, warnaOranye, 1);
        bungkusId.setLayout(new BorderLayout());
        bungkusId.setBorder(new EmptyBorder(8, 20, 8, 20));
        bungkusId.setMaximumSize(new Dimension(9999, 45));
        bungkusId.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField kolomId = new JTextField("");
        kolomId.setFont(new Font("SansSerif", Font.PLAIN, 14));
        kolomId.setOpaque(false); 
        kolomId.setBorder(null);
        bungkusId.add(kolomId, BorderLayout.CENTER);
        
        panelIsi.add(bungkusId);
        panelIsi.add(Box.createVerticalStrut(14));

        // --- Label & Input Nama Akun ---
        JLabel teksAkun = new JLabel("ACCOUNT NAME");
        teksAkun.setFont(new Font("SansSerif", Font.BOLD, 11));
        teksAkun.setForeground(Color.DARK_GRAY);
        teksAkun.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelIsi.add(teksAkun);
        panelIsi.add(Box.createVerticalStrut(8));

        PanelBulat bungkusNama = new PanelBulat(20, warnaAbuAbu, warnaOranye, 1);
        bungkusNama.setLayout(new BorderLayout());
        bungkusNama.setBorder(new EmptyBorder(8, 15, 8, 15));
        bungkusNama.setMaximumSize(new Dimension(9999, 45));
        bungkusNama.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField kolomNama = new JTextField("");
        kolomNama.setEditable(false);
        kolomNama.setFont(new Font("SansSerif", Font.PLAIN, 14));
        kolomNama.setOpaque(false); 
        kolomNama.setBorder(null);
        bungkusNama.add(kolomNama, BorderLayout.CENTER);
        
        panelIsi.add(bungkusNama);
        panelIsi.add(Box.createVerticalStrut(20));

        // --- AREA BUNDLE ---
        JPanel areaBundle = new JPanel();
        areaBundle.setLayout(new BoxLayout(areaBundle, BoxLayout.Y_AXIS));
        areaBundle.setBackground(Color.WHITE);
        areaBundle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Label "1. Bundling" (Bentuk Pil)
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
        areaBundle.add(Box.createVerticalStrut(-20)); // Efek overlap seperti di Figma

        // Grid Kartu 4 Baris 2 Kolom
        JPanel panelGrid = new JPanel();
        panelGrid.setLayout(new GridLayout(4, 2, 10, 10)); 
        panelGrid.setOpaque(false);

        // Memasukkan daftar harga khusus CODM
        String nama = " CP";
        buatKartu(panelGrid, "31" + nama, "Rp 5.000");
        buatKartu(panelGrid, "62" + nama, "Rp 10.000");
        buatKartu(panelGrid, "127" + nama, "Rp 20.000");
        buatKartu(panelGrid, "317" + nama, "Rp 50.000");
        buatKartu(panelGrid, "634" + nama, "Rp 100.000");
        buatKartu(panelGrid, "1373" + nama, "Rp 200.000");
        buatKartu(panelGrid, "2059" + nama, "Rp 300.000");
        buatKartu(panelGrid, "3564" + nama, "Rp 500.000");

        // Kotak Oranye Besar
        PanelBulat kotakOranye = new PanelBulat(25, warnaOranye, null, 0);
        kotakOranye.setLayout(new BorderLayout());
        kotakOranye.setBorder(new EmptyBorder(30, 10, 20, 10));
        kotakOranye.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        kotakOranye.add(panelGrid, BorderLayout.CENTER);
        areaBundle.add(kotakOranye);

        panelIsi.add(areaBundle);
        panelIsi.add(Box.createVerticalStrut(20));

        // --- PANEL BAWAH (FIXED FOOTER) ---
        JPanel panelBawah = new JPanel();
        panelBawah.setBackground(Color.WHITE);
        panelBawah.setLayout(new BoxLayout(panelBawah, BoxLayout.Y_AXIS));
        // Padding 36 di kiri dan kanan agar ukuran tombol sama persis
        panelBawah.setBorder(new EmptyBorder(10, 30, 0, 30));
        panelAkar.add(panelBawah, BorderLayout.SOUTH);

        // Tombol Pilih Metode Pembayaran
        PanelBulat bungkusTombolBayar = new PanelBulat(15, warnaOranye, null, 0);
        bungkusTombolBayar.setLayout(new BorderLayout());
        bungkusTombolBayar.setMaximumSize(new Dimension(9999, 50)); 
        bungkusTombolBayar.setAlignmentX(Component.LEFT_ALIGNMENT);
        bungkusTombolBayar.setBorder(new EmptyBorder(14, 0, 14, 0));

        JLabel teksTombolBayar = new JLabel("Pilih Metode Pembayaran", SwingConstants.CENTER);
        teksTombolBayar.setFont(new Font("SansSerif", Font.BOLD, 15));
        teksTombolBayar.setForeground(Color.BLACK);
        bungkusTombolBayar.add(teksTombolBayar, BorderLayout.CENTER);

        panelBawah.add(bungkusTombolBayar);
        panelBawah.add(Box.createVerticalStrut(14));

        // Area Logo Kinopedia
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

    public void buatKartu(JPanel grid, String judul, String harga) {
        // Mode Awal: Background PUTIH murni, tanpa border
        PanelBulat kartu = new PanelBulat(15, Color.WHITE, null, 0);
        kartu.setLayout(new BoxLayout(kartu, BoxLayout.Y_AXIS));
        // Padding kiri 16 agar tulisan dan ikon bergeser rapi ke sebelah kanan
        kartu.setBorder(new EmptyBorder(8, 16, 8, 4)); 

        JLabel gambarIkon = new JLabel("?");
        if (this.ikonMataUang != null) {
            gambarIkon = new JLabel(this.ikonMataUang);
        }
        gambarIkon.setAlignmentX(Component.LEFT_ALIGNMENT); 

        JLabel teksJudul = new JLabel(judul);
        teksJudul.setFont(new Font("SansSerif", Font.BOLD, 15)); 
        teksJudul.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel teksHarga = new JLabel(harga);
        teksHarga.setFont(new Font("SansSerif", Font.PLAIN, 18)); 
        teksHarga.setAlignmentX(Component.LEFT_ALIGNMENT);

        kartu.add(gambarIkon);
        kartu.add(Box.createVerticalStrut(6));
        kartu.add(teksJudul);
        kartu.add(Box.createVerticalStrut(2));
        kartu.add(teksHarga);

        grid.add(kartu);
        daftarSemuaKartu.add(kartu);

        // Fitur seleksi kartu (MENGUBAH BACKGROUND SAAT DIKLIK)
        kartu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 1. Reset semua kartu ke background putih tanpa border
                for (PanelBulat k : daftarSemuaKartu) {
                    k.ubahTampilan(Color.WHITE, null, 0); 
                }
                // 2. Ubah kartu yang diklik menjadi abu-abu terang (termasuk diberi border tipis agar lebih tegas)
                kartu.ubahTampilan(new Color(220, 220, 220), new Color(150, 150, 150), 2); 
                
                bundleTerpilih = judul;
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

        // Render Latar Belakang (Dapat berubah warna sekarang)
        if (this.warnaLatar != null) {
            g2.setColor(this.warnaLatar);
            g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, this.radiusLengkungan, this.radiusLengkungan);
        }

        // Render Garis/Border
        if (this.warnaGaris != null && this.tebalGaris > 0) {
            g2.setColor(this.warnaGaris);
            g2.setStroke(new BasicStroke(this.tebalGaris));
            
            int jarak = this.tebalGaris / 2;
            int lebarGambar = getWidth() - this.tebalGaris - 1;
            int tinggiGambar = getHeight() - this.tebalGaris - 1;
            
            g2.drawRoundRect(jarak, jarak, lebarGambar, tinggiGambar, this.radiusLengkungan, this.radiusLengkungan);
        }
        
        g2.dispose();
    }
}