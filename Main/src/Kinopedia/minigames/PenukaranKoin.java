/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames;
import Kinopedia.DataAkun;
import Kinopedia.HalamanConfirmation;
import Kinopedia.Session;
import Kinopedia.Main;
import Kinopedia.model.DetailPembayaran;
import Kinopedia.model.MetodeBayar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import Kinopedia.PilihanBundle.BundleCODM;
import java.awt.BasicStroke;

/**
 *
 * @author Vincent
 */
public class PenukaranKoin extends JFrame {
    Kinopedia.DataUser user = Session.getInstance().getCurrentUser();
    ArrayList<Bundle> bundle = new ArrayList<>();
    private ArrayList<PanelBulat> daftarSemuaKartu = new ArrayList<>();
    private ImageIcon logoFooter;
    int index = 0;
    JPanel selected = null;
    Bundle diPilih = null;
    private PanelBulat bungkusId;
    private JTextField kolomId;
    private PanelBulat bungkusNama;
    private PanelBulat kotakOranye;
    private JTextField kolomNama;
    
    Color warnaOranye = new Color(0xFF8C1A);
    Color warnaAbuAbu = new Color(0xBDBDBD);
    Color warnaMerah = new Color(0xFF3B30);

    MouseAdapter bundleClick = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JPanel clicked = (JPanel) e.getSource();

            // Reset panel sebelumnya
            if (selected != null) {
                JPanel prev = selected;
                prev.putClientProperty("selected", false);
                prev.repaint();
            }

            selected = clicked;
            clicked.putClientProperty("selected", true);
            clicked.repaint();
        }
    };
    
    public PenukaranKoin(){
//        Main.dataAkun.get(0);

//        ambil gambar
//        URL url = getClass().getResource("/Kinopedia/model/IMAGESS/Diamond.png");
//        Image img = new ImageIcon(url).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
//        currencyIcon = new ImageIcon(url);
        bundle.add(new Bundle("Diamonds", "ML",index,100,"10"));
        index++;
        bundle.add(new Bundle("Diamonds", "FF", index, 180,"70"));
        index++;
        bundle.add(new Bundle("UC","PUBGM", index, 344,"60"));
        index++;
        bundle.add(new Bundle("CP","CODM", index, 900,"321"));
        index++;
        bundle.add(new Bundle("VP","Valorant", index, 1200,"475"));
        index++;
        bundle.add(new Bundle("Coin","Efootball", index, 324,"137"));
        index++;
        bundle.add(new Bundle("Steam","Steam", index, 440,"12.000"));
        index++;
        bundle.add(new Bundle("Diamonds","ML", index, 560,"2.850"));
        
        
        setSize(470, 844);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        
        ImageIcon iconBack = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/back.png"));
        JLabel btnBack = new JLabel(iconBack);
        btnBack.setBounds(35, -20, 100, 100);
        add(btnBack);
        
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new MainMiniGames().setVisible(true);
            }
        });

        Color ORANGE = new Color(0xFF8C1A);

        URL url = getClass().getResource("/Kinopedia/model/IMAGESS/LogoKinopedia.png");
        Image img = new ImageIcon(url).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logoFooter = new ImageIcon(url);

        
        JPanel borderTitle = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        borderTitle.setLayout(null);
        borderTitle.setOpaque(false);
        borderTitle.setBorder(new RoundedBorder(40, ORANGE));
        borderTitle.setBounds(50, 50, 180, 40);
        
        JLabel title = new JLabel("Penukaran Koin");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(13, 0, 155, 40);
        title.setHorizontalAlignment(title.CENTER);
        
        borderTitle.add(title);
        add(borderTitle);
        
        JPanel bgBundle = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(ORANGE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        bgBundle.setLayout(null);
        bgBundle.setBounds(33,70,400,500);
        bgBundle.setOpaque(false);
        
        int x= 14;
        int y = 35;
        for (int i = 0 ; i<8;i++){
            final int index = i;
            Bundle cetakBundle = bundle.get(i);
            JPanel bgBundle1 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                boolean isSelected = Boolean.TRUE.equals(getClientProperty("selected"));
                Color color = Color.WHITE;
                if(isSelected ==true){
                    color = Color.GRAY;
                    diPilih = bundle.get(index);
                }
                g2d.setColor(color);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
                super.paintComponent(g);
            }
            };
            if(i>0){
                if(i%2==1){
                    x= 206;
                    
                }else{
                    x= 14;
                    y+=115;
                }
            }
            bgBundle1.setLayout(null);
            bgBundle1.setOpaque(false);
            Image currencyImg = null;
            if(bundle.get(i).getCurrencyName().equals("UC")){
                currencyImg = new ImageIcon(getClass().getResource("/Kinopedia/model/IMAGESS/UC-PUBGs.png")).getImage().getScaledInstance(35,35,Image.SCALE_SMOOTH);
            }else if(bundle.get(i).getCurrencyName().equals("VP")){
                currencyImg = new ImageIcon(getClass().getResource("/Kinopedia/model/IMAGESS/Valorant-pointss.png")).getImage().getScaledInstance(60,35,Image.SCALE_SMOOTH);
            }else if(bundle.get(i).getCurrencyName().equals("CP")){
                currencyImg = new ImageIcon(getClass().getResource("/Kinopedia/model/IMAGESS/CPs-CODM.png")).getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
            }else if(bundle.get(i).getCurrencyName().equals("Coin")){
                currencyImg = new ImageIcon(getClass().getResource("/Kinopedia/model/IMAGESS/efootballgolds.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
            }else if(bundle.get(i).getCurrencyName().equals("Steam")){
                currencyImg = new ImageIcon(getClass().getResource("/Kinopedia/model/IMAGESS/Steam-credits.png")).getImage().getScaledInstance(50,35,Image.SCALE_SMOOTH);
            }else{
                currencyImg = new ImageIcon(getClass().getResource("/Kinopedia/model/IMAGESS/Diamond.png")).getImage().getScaledInstance(35,35,Image.SCALE_SMOOTH);
            }
            JLabel iconCurrency = new JLabel(new ImageIcon(currencyImg));
            if(bundle.get(i).getCurrencyName().equals("Steam")){
                iconCurrency.setBounds(17, 10, 35, 35);
            }else if(bundle.get(i).getCurrencyName().equals("Coin")){
                iconCurrency.setBounds(15, 10, 35, 35);
            }else if(bundle.get(i).getCurrencyName().equals("CP")){
                iconCurrency.setBounds(15, 13, 35, 35);
            }else{
                iconCurrency.setBounds(10, 10, 35, 35);
            }
            bgBundle1.add(iconCurrency);
            
            JLabel judulBundle = new JLabel (bundle.get(i).getJumlah()+" "+bundle.get(i).getCurrencyName()+" ("+bundle.get(i).getNamaGame()+")");
            judulBundle.setFont(new Font("Arial", Font.BOLD, 15));
            judulBundle.setBounds(15, 35, 170, 40);
            bgBundle1.add(judulBundle);
            
            String harga = ""+bundle.get(i).getHarga()+" Koin";
            JLabel hargaBundle = new JLabel (harga);
            hargaBundle.setFont(new Font("Arial", Font.BOLD, 15));
            hargaBundle.setBounds(15, 55, 170, 40);
            bgBundle1.add(hargaBundle);
            
            bgBundle1.setBounds(x,y,180,100);
            bgBundle1.addMouseListener(bundleClick);
            
            bgBundle.add(bgBundle1);
        }
        
        JButton btnTukar = new JButton ("Tukar");
        btnTukar.setBounds(35, 730, 400, 45);
        btnTukar.setFont(new Font("Arial", Font.BOLD, 20));
        btnTukar.setForeground(Color.WHITE);
        btnTukar.setFocusPainted(false);
        btnTukar.setBorderPainted(false);
        btnTukar.setBackground(ORANGE);
        btnTukar.addActionListener(e -> {
            if(diPilih!=null){
                dispose();
                if(user.getKoin()>= diPilih.getHarga()){
                    user.setKoin(user.getKoin()-diPilih.getHarga());
                    new HalamanConfirmation("Kembali ke Halaman Minigames", true, "Penukaran Sukses", "", "Coin",  new Color(255, 140, 0)).setVisible(true);
                }else{
                    new HalamanConfirmation("Kembali ke Halaman Minigames", false, "Penukaran Gagal", "Cek koin anda terlebih dahulu", "Coin",  new Color(255, 140, 0)).setVisible(true);
                }
            }
        });
        
        // --- Label & Input NUMBER ID ---
        JLabel teksId = new JLabel("NUMBER ID");
        teksId.setFont(new Font("SansSerif", Font.BOLD, 11));
        teksId.setForeground(Color.DARK_GRAY);
        teksId.setBounds(40, 575, 100,30 );
        add(teksId);
        
        bungkusId = new PanelBulat(20, Color.WHITE, warnaOranye, 1);
        bungkusId.setLayout(null);
        bungkusId.setBorder(new EmptyBorder(8, 15, 8, 15));
        bungkusId.setMaximumSize(new Dimension(9999, 45));
        bungkusId.setAlignmentX(Component.LEFT_ALIGNMENT);

        kolomId = new JTextField("");
        kolomId.setFont(new Font("SansSerif", Font.PLAIN, 14));
        kolomId.setOpaque(false);
        kolomId.setBorder(null);
        kolomId.setBounds(15, 5, 370, 34);
        bungkusId.add(kolomId);
        bungkusId.setBounds(33, 600, 400, 50);
        add(bungkusId);
        
        
        add(btnTukar);
        add(bgBundle);
        
        
        
        
    }
    public static void main(String[] args) {
            PenukaranKoin frame = new PenukaranKoin();
            frame.setVisible(true);
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
    
//    // === METHOD: CEK ID AKUN CODM ===
//    // Format CODM: tepat 10 angka. Contoh: 1345678935
//    private void cekIdAkun(Color warnaOranye, Color warnaMerah) {
//        String inputId = kolomId.getText().trim();
//
//        // Langkah 1: Cek apakah panjangnya tepat 10 karakter
//        boolean panjangBenar = inputId.length() == 10;
//
//        // Langkah 2: Cek apakah semua karakternya adalah angka
//        boolean semuaAngka = true;
//        for (int i = 0; i < inputId.length(); i++) {
//            if (!Character.isDigit(inputId.charAt(i))) {
//                semuaAngka = false;
//            }
//        }
//
//        // Format benar hanya jika keduanya terpenuhi
//        boolean formatBenar = panjangBenar && semuaAngka;
//
//        if (!formatBenar) {
//            // Format salah — border merah, kolom nama dikosongkan
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//            kolomNama.setText("");
//            kolomNama.setForeground(Color.BLACK);
//            return;
//        }
//
//        // Format benar — cari ID di Main.dataAkun
//        boolean akunDitemukan = false;
//
//        for (int i = 0; i < Main.dataAkun.size(); i++) {
//            String idDiDatabase   = Main.dataAkun.get(i).getIdAkun();
//            String namaDiDatabase = Main.dataAkun.get(i).getNamaAkun();
//            String gameDiDatabase = Main.dataAkun.get(i).getJenisGame();
//
//            // Cocokkan ID dan pastikan jenisGame-nya "codm"
//            boolean idSama    = idDiDatabase.equals(inputId);
//            boolean gameSama  = gameDiDatabase.equals("codm");
//
//            if (idSama && gameSama) {
//                kolomNama.setText(namaDiDatabase);
//                kolomNama.setForeground(Color.BLACK);
//                bungkusId.ubahTampilan(Color.WHITE, warnaOranye, 1);
//                akunDitemukan = true;
//                break;
//            }
//        }
//
//        if (!akunDitemukan) {
//            // ID tidak ada di database
//            kolomNama.setText("Akun Tidak Ditemukan");
//            kolomNama.setForeground(warnaMerah);
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//        }
//    }
    
    
    
//    // === METHOD: CEK ID AKUN EFOOTBALL ===
//    // Format eFootball: 4 huruf + tanda "-" + 6 angka. Contoh: ASAA-845389
//    private void cekIdAkun(Color warnaOranye, Color warnaMerah) {
//        String inputId = kolomId.getText().trim();
//
//        // Langkah 1: Cek panjang totalnya harus 11 karakter (4 huruf + 1 strip + 6 angka)
//        boolean panjangYangBenar = inputId.length() == 11;
//
//        // Langkah 2: Cek karakter ke-5 harus tanda "-"
//        boolean adaTandaStrip = false;
//        if (panjangYangBenar) {
//            adaTandaStrip = inputId.charAt(4) == '-';
//        }
//
//        // Langkah 3: Cek 4 karakter pertama harus semua huruf
//        boolean empatHurufDepan = true;
//        if (panjangYangBenar) {
//            for (int i = 0; i < 4; i++) {
//                if (!Character.isLetter(inputId.charAt(i))) {
//                    empatHurufDepan = false;
//                }
//            }
//        }
//
//        // Langkah 4: Cek 6 karakter terakhir harus semua angka
//        boolean enamAngkaBelakang = true;
//        if (panjangYangBenar) {
//            for (int i = 5; i < 11; i++) {
//                if (!Character.isDigit(inputId.charAt(i))) {
//                    enamAngkaBelakang = false;
//                }
//            }
//        }
//
//        // Format benar hanya jika semua langkah terpenuhi
//        boolean formatBenar = panjangYangBenar && adaTandaStrip && empatHurufDepan && enamAngkaBelakang;
//
//        if (!formatBenar) {
//            // Format salah — border merah, kolom nama dikosongkan
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//            kolomNama.setText("");
//            kolomNama.setForeground(Color.BLACK);
//            return;
//        }
//
//        // Format benar — cari ID di Main.dataAkun
//        boolean akunDitemukan = false;
//
//        for (int i = 0; i < Main.dataAkun.size(); i++) {
//            String idDiDatabase   = Main.dataAkun.get(i).getIdAkun();
//            String namaDiDatabase = Main.dataAkun.get(i).getNamaAkun();
//            String gameDiDatabase = Main.dataAkun.get(i).getJenisGame();
//
//            boolean idSama   = idDiDatabase.equals(inputId);
//            boolean gameSama = gameDiDatabase.equals("efootball");
//
//            if (idSama && gameSama) {
//                kolomNama.setText(namaDiDatabase);
//                kolomNama.setForeground(Color.BLACK);
//                bungkusId.ubahTampilan(Color.WHITE, warnaOranye, 1);
//                akunDitemukan = true;
//                break;
//            }
//        }
//
//        if (!akunDitemukan) {
//            kolomNama.setText("Akun Tidak Ditemukan");
//            kolomNama.setForeground(warnaMerah);
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//        }
//    }
    
    
    
//    // === METHOD: CEK ID AKUN FREE FIRE ===
//    // Format FF: tepat 9 angka. Contoh: 736452836
//    private void cekIdAkun(Color warnaOranye, Color warnaMerah) {
//        String inputId = kolomId.getText().trim();
// 
//        // Langkah 1: Cek panjangnya harus tepat 9 karakter
//        boolean panjangBenar = inputId.length() == 9;
// 
//        // Langkah 2: Cek semua karakternya harus angka
//        boolean semuaAngka = true;
//        if (panjangBenar) {
//            for (int i = 0; i < inputId.length(); i++) {
//                if (!Character.isDigit(inputId.charAt(i))) {
//                    semuaAngka = false;
//                }
//            }
//        }
// 
//        // Format benar hanya jika keduanya terpenuhi
//        boolean formatBenar = panjangBenar && semuaAngka;
// 
//        if (!formatBenar) {
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//            kolomNama.setText("");
//            kolomNama.setForeground(Color.BLACK);
//            return;
//        }
// 
//        // Format benar — cari ID di Main.dataAkun
//        boolean akunDitemukan = false;
// 
//        for (int i = 0; i < Main.dataAkun.size(); i++) {
//            String idDiDatabase   = Main.dataAkun.get(i).getIdAkun();
//            String namaDiDatabase = Main.dataAkun.get(i).getNamaAkun();
//            String gameDiDatabase = Main.dataAkun.get(i).getJenisGame();
// 
//            boolean idSama   = idDiDatabase.equals(inputId);
//            boolean gameSama = gameDiDatabase.equals("ff");
// 
//            if (idSama && gameSama) {
//                kolomNama.setText(namaDiDatabase);
//                kolomNama.setForeground(Color.BLACK);
//                bungkusId.ubahTampilan(Color.WHITE, warnaOranye, 1);
//                akunDitemukan = true;
//                break;
//            }
//        }
// 
//        if (!akunDitemukan) {
//            kolomNama.setText("Akun Tidak Ditemukan");
//            kolomNama.setForeground(warnaMerah);
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//        }
//    }
    
    
//    // === METHOD: CEK ID AKUN MOBILE LEGENDS ===
//    // Format ML: 8 angka + (4 angka Zone ID). Contoh: 72895647(2515)
//    private void cekIdAkun(Color warnaOranye, Color warnaMerah) {
//        String inputId = kolomId.getText().trim();
//
//        // Langkah 1: Cek panjang total harus 14 karakter
//        // Contoh: 72895647(2515) = 8 angka + "(" + 4 angka + ")" = 14 karakter
//        boolean panjangBenar = inputId.length() == 14;
//
//        // Langkah 2: Cek 8 karakter pertama harus angka
//        boolean delapanAngkaDepan = true;
//        if (panjangBenar) {
//            for (int i = 0; i < 8; i++) {
//                if (!Character.isDigit(inputId.charAt(i))) {
//                    delapanAngkaDepan = false;
//                }
//            }
//        }
//
//        // Langkah 3: Cek karakter ke-9 harus "(" dan karakter terakhir harus ")"
//        boolean adaKurung = false;
//        if (panjangBenar) {
//            boolean adaKurungBuka  = inputId.charAt(8) == '(';
//            boolean adaKurungTutup = inputId.charAt(13) == ')';
//            adaKurung = adaKurungBuka && adaKurungTutup;
//        }
//
//        // Langkah 4: Cek 4 karakter Zone ID (posisi 9-12) harus angka
//        boolean empatAngkaZoneId = true;
//        if (panjangBenar) {
//            for (int i = 9; i < 13; i++) {
//                if (!Character.isDigit(inputId.charAt(i))) {
//                    empatAngkaZoneId = false;
//                }
//            }
//        }
//
//        // Format benar hanya jika semua langkah terpenuhi
//        boolean formatBenar = panjangBenar && delapanAngkaDepan && adaKurung && empatAngkaZoneId;
//
//        if (!formatBenar) {
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//            kolomNama.setText("");
//            kolomNama.setForeground(Color.BLACK);
//            return;
//        }
//
//        // Format benar — cari ID di Main.dataAkun
//        boolean akunDitemukan = false;
//
//        for (int i = 0; i < Main.dataAkun.size(); i++) {
//            String idDiDatabase   = Main.dataAkun.get(i).getIdAkun();
//            String namaDiDatabase = Main.dataAkun.get(i).getNamaAkun();
//            String gameDiDatabase = Main.dataAkun.get(i).getJenisGame();
//
//            boolean idSama   = idDiDatabase.equals(inputId);
//            boolean gameSama = gameDiDatabase.equals("ml");
//
//            if (idSama && gameSama) {
//                kolomNama.setText(namaDiDatabase);
//                kolomNama.setForeground(Color.BLACK);
//                bungkusId.ubahTampilan(Color.WHITE, warnaOranye, 1);
//                akunDitemukan = true;
//                break;
//            }
//        }
//
//        if (!akunDitemukan) {
//            kolomNama.setText("Akun Tidak Ditemukan");
//            kolomNama.setForeground(warnaMerah);
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//        }
//    }
    
    
//    // === METHOD: CEK ID AKUN PUBG MOBILE ===
//    // Format PUBGM: tepat 12 angka. Contoh: 736452836472
//    private void cekIdAkun(Color warnaOranye, Color warnaMerah) {
//        String inputId = kolomId.getText().trim();
// 
//        // Langkah 1: Cek panjangnya harus tepat 12 karakter
//        boolean panjangBenar = inputId.length() == 12;
// 
//        // Langkah 2: Cek semua karakternya harus angka
//        boolean semuaAngka = true;
//        if (panjangBenar) {
//            for (int i = 0; i < inputId.length(); i++) {
//                if (!Character.isDigit(inputId.charAt(i))) {
//                    semuaAngka = false;
//                }
//            }
//        }
// 
//        // Format benar hanya jika keduanya terpenuhi
//        boolean formatBenar = panjangBenar && semuaAngka;
// 
//        if (!formatBenar) {
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//            kolomNama.setText("");
//            kolomNama.setForeground(Color.BLACK);
//            return;
//        }
// 
//        // Format benar — cari ID di Main.dataAkun
//        boolean akunDitemukan = false;
// 
//        for (int i = 0; i < Main.dataAkun.size(); i++) {
//            String idDiDatabase   = Main.dataAkun.get(i).getIdAkun();
//            String namaDiDatabase = Main.dataAkun.get(i).getNamaAkun();
//            String gameDiDatabase = Main.dataAkun.get(i).getJenisGame();
// 
//            boolean idSama   = idDiDatabase.equals(inputId);
//            boolean gameSama = gameDiDatabase.equals("pubgm");
// 
//            if (idSama && gameSama) {
//                kolomNama.setText(namaDiDatabase);
//                kolomNama.setForeground(Color.BLACK);
//                bungkusId.ubahTampilan(Color.WHITE, warnaOranye, 1);
//                akunDitemukan = true;
//                break;
//            }
//        }
// 
//        if (!akunDitemukan) {
//            kolomNama.setText("Akun Tidak Ditemukan");
//            kolomNama.setForeground(warnaMerah);
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//        }
//    }
    
    
//    // === METHOD: CEK EMAIL AKUN STEAM ===
//    // Format Steam: Alamat Email. Wajib ada "@gmail.com" di bagian belakang
//    // Contoh: NichoDev123@gmail.com
//    private void cekIdAkun(Color warnaOranye, Color warnaMerah) {
//        String inputEmail = kolomId.getText().trim();
// 
//        // Langkah 1: Cek apakah ada karakter "@" di dalam email
//        boolean adaAt = false;
//        int posisiAt = -1;
//        for (int i = 0; i < inputEmail.length(); i++) {
//            if (inputEmail.charAt(i) == '@') {
//                adaAt = true;
//                posisiAt = i;
//                break;
//            }
//        }
// 
//        // Langkah 2: Cek apakah bagian belakangnya adalah "@gmail.com"
//        boolean emailGmail = false;
//        if (adaAt) {
//            String bagianBelakang = inputEmail.substring(posisiAt);
//            emailGmail = bagianBelakang.equals("@gmail.com");
//        }
// 
//        // Langkah 3: Cek apakah ada nama email di depan "@" (tidak boleh kosong)
//        boolean adaNamaDepan = false;
//        if (adaAt) {
//            adaNamaDepan = posisiAt > 0;
//        }
// 
//        // Format benar hanya jika semua langkah terpenuhi
//        boolean formatBenar = adaAt && emailGmail && adaNamaDepan;
// 
//        if (!formatBenar) {
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//            kolomNama.setText("");
//            kolomNama.setForeground(Color.BLACK);
//            return;
//        }
// 
//        // Format benar — cari email di Main.dataAkun
//        boolean akunDitemukan = false;
// 
//        for (int i = 0; i < Main.dataAkun.size(); i++) {
//            String idDiDatabase   = Main.dataAkun.get(i).getIdAkun();
//            String namaDiDatabase = Main.dataAkun.get(i).getNamaAkun();
//            String gameDiDatabase = Main.dataAkun.get(i).getJenisGame();
// 
//            boolean idSama   = idDiDatabase.equals(inputEmail);
//            boolean gameSama = gameDiDatabase.equals("steam");
// 
//            if (idSama && gameSama) {
//                kolomNama.setText(namaDiDatabase);
//                kolomNama.setForeground(Color.BLACK);
//                bungkusId.ubahTampilan(Color.WHITE, warnaOranye, 1);
//                akunDitemukan = true;
//                break;
//            }
//        }
// 
//        if (!akunDitemukan) {
//            kolomNama.setText("Akun Tidak Ditemukan");
//            kolomNama.setForeground(warnaMerah);
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//        }
//    }
    
    
//    // === METHOD: CEK ID AKUN VALORANT ===
//    // Format Valorant: 6 karakter (huruf/angka) + "#" + 4 karakter TAG
//    // Contoh: Will067#WILL
//    private void cekIdAkun(Color warnaOranye, Color warnaMerah) {
//        String inputId = kolomId.getText().trim();
//
//        // Langkah 1: Cek panjang total harus 11 karakter
//        // 6 karakter nama + 1 tanda "#" + 4 karakter TAG = 11
//        boolean panjangBenar = inputId.length() == 11;
//
//        // Langkah 2: Cek karakter ke-7 harus tanda "#"
//        boolean adaTandaPagar = false;
//        if (panjangBenar) {
//            adaTandaPagar = inputId.charAt(6) == '#';
//        }
//
//        // Langkah 3: Cek 6 karakter pertama harus huruf atau angka
//        boolean enamKarakterDepan = true;
//        if (panjangBenar) {
//            for (int i = 0; i < 6; i++) {
//                boolean huruf = Character.isLetter(inputId.charAt(i));
//                boolean angka = Character.isDigit(inputId.charAt(i));
//
//                if (!huruf && !angka) {
//                    enamKarakterDepan = false;
//                }
//            }
//        }
//
//        // Langkah 4: Cek 4 karakter TAG di belakang "#" harus huruf atau angka
//        boolean empatKarakterTag = true;
//        if (panjangBenar) {
//            for (int i = 7; i < 11; i++) {
//                boolean huruf = Character.isLetter(inputId.charAt(i));
//                boolean angka = Character.isDigit(inputId.charAt(i));
//
//                if (!huruf && !angka) {
//                    empatKarakterTag = false;
//                }
//            }
//        }
//
//        // Format benar hanya jika semua langkah terpenuhi
//        boolean formatBenar = panjangBenar && adaTandaPagar && enamKarakterDepan && empatKarakterTag;
//
//        if (!formatBenar) {
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//            kolomNama.setText("");
//            kolomNama.setForeground(Color.BLACK);
//            return;
//        }
//
//        // Format benar — cari ID di Main.dataAkun
//        boolean akunDitemukan = false;
//
//        for (int i = 0; i < Main.dataAkun.size(); i++) {
//            String idDiDatabase   = Main.dataAkun.get(i).getIdAkun();
//            String namaDiDatabase = Main.dataAkun.get(i).getNamaAkun();
//            String gameDiDatabase = Main.dataAkun.get(i).getJenisGame();
//
//            boolean idSama   = idDiDatabase.equals(inputId);
//            boolean gameSama = gameDiDatabase.equals("valorant");
//
//            if (idSama && gameSama) {
//                kolomNama.setText(namaDiDatabase);
//                kolomNama.setForeground(Color.BLACK);
//                bungkusId.ubahTampilan(Color.WHITE, warnaOranye, 1);
//                akunDitemukan = true;
//                break;
//            }
//        }
//
//        if (!akunDitemukan) {
//            kolomNama.setText("Akun Tidak Ditemukan");
//            kolomNama.setForeground(warnaMerah);
//            bungkusId.ubahTampilan(Color.WHITE, warnaMerah, 2);
//        }
//    }
    
}