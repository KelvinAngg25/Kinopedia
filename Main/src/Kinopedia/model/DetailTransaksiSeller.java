/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.model;

import Kinopedia.HalamanConfirmation;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.Image;

/**
 *
 * @author William
 */
public class DetailTransaksiSeller extends JFrame{
    
    public DetailTransaksiSeller(boolean penandaBerhasilAtauGagal, String IDTransaksiTampil, String TanggalDanWaktu, String usernameIngame, String IDUsername, String gameyangDipilih, String metodePembayaran, int totalHargaBundle) {
        setTitle("Kinopedia");
        setSize(470, 844);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        ImageIcon iconBack = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/back.png"));
        JLabel btnBack = new JLabel(iconBack);
        btnBack.setBounds(35, 1, 100, 100);
        add(btnBack);

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        
        ImageIcon iconKinoepedia = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/logoKinopediaKecil.png"));
        JLabel iconKinopediaKecil = new JLabel(iconKinoepedia);
        iconKinopediaKecil.setBounds(178, 700, 100, 100);
        add(iconKinopediaKecil);
        
        Color color = null;
        
        if (!penandaBerhasilAtauGagal) {
            color = new Color(75, 105, 135);
        } else {
            color = new Color(80, 180, 80);
        }
        
        final Color warna = color;
        JPanel panelAtas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(warna);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }  
        };
        panelAtas.setBackground(color);
        panelAtas.setBounds(45, 80, 372, 220);
        panelAtas.setLayout(null);
        add(panelAtas);
        
        JLabel headerInformasi = new JLabel("Informasi & Transaksi");
        headerInformasi.setBounds(20, 0, 250, 53);
        headerInformasi.setFont(new Font("Poppins", Font.BOLD, 20));
        headerInformasi.setHorizontalAlignment(JLabel.LEFT);
        headerInformasi.setForeground(Color.BLACK);
        panelAtas.add(headerInformasi);
        
        ImageIcon iconFolder = new ImageIcon(getClass().getResource("/Kinopedia/view/Image/icon.png"));
        JLabel iconFolderFix = new JLabel(iconFolder);
        iconFolderFix.setBounds(218, 0, 250, 53);
        panelAtas.add(iconFolderFix);
        
        JLabel headerIDTransaksi= new JLabel("ID Transaksi");
        headerIDTransaksi.setBounds(20, 52, 230, 83);
        headerIDTransaksi.setFont(new Font("Poppins", Font.PLAIN, 20));
        headerIDTransaksi.setHorizontalAlignment(JLabel.LEFT);
        headerIDTransaksi.setForeground(Color.BLACK);
        panelAtas.add(headerIDTransaksi);
        
        JLabel IDTransaksi = new JLabel(IDTransaksiTampil);
        IDTransaksi.setBounds(20, 72, 250, 83);
        IDTransaksi.setFont(new Font("Poppins", Font.BOLD, 20));
        IDTransaksi.setHorizontalAlignment(JLabel.LEFT);
        IDTransaksi.setForeground(Color.BLACK);
        panelAtas.add(IDTransaksi);
        
        JLabel headerTnW= new JLabel("Tanggal & Waktu");
        headerTnW.setBounds(20, 128, 250, 83);
        headerTnW.setFont(new Font("Poppins", Font.PLAIN, 20));
        headerTnW.setHorizontalAlignment(JLabel.LEFT);
        headerTnW.setForeground(Color.BLACK);
        panelAtas.add(headerTnW);
        
        JLabel TnW = new JLabel(TanggalDanWaktu);
        TnW.setBounds(20, 150, 250, 83);
        TnW.setFont(new Font("Poppins", Font.BOLD, 20));
        TnW.setHorizontalAlignment(JLabel.LEFT);
        TnW.setForeground(Color.BLACK);
        panelAtas.add(TnW);
        
        JLabel detailPembayaran = new JLabel("Detail Pembayaran");
        detailPembayaran.setBounds(45, 235, 200, 200);
        detailPembayaran.setFont(new Font("Poppins", Font.BOLD, 18));
        add(detailPembayaran);
        
        JPanel borderPilihanBayar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }
        };
        borderPilihanBayar.setLayout(null);
        borderPilihanBayar.setBorder(new RoundedBorder(20, color));
        borderPilihanBayar.setBounds(45, 350, 372, 300);
        borderPilihanBayar.setOpaque(true);
        borderPilihanBayar.setBackground(Color.WHITE);
        add(borderPilihanBayar);

        JPanel backgroundPilihanBayar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(warna);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.fillRect(0, getHeight() - 20, getWidth(), 20);
                g2.dispose();
            }
        };
        backgroundPilihanBayar.setBackground(color);
        backgroundPilihanBayar.setBounds(0, 0, 372, 220); 
        backgroundPilihanBayar.setLayout(null);
        borderPilihanBayar.add(backgroundPilihanBayar); 

        JLabel headerName = new JLabel("Nama");
        headerName.setBounds(20, 15, 100, 40);
        headerName.setFont(new Font("Poppins", Font.PLAIN, 18));
        headerName.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(headerName); 

        JLabel valueName = new JLabel(usernameIngame);
        valueName.setBounds(20, 15, 330, 40);
        valueName.setFont(new Font("Poppins", Font.PLAIN, 16));
        valueName.setForeground(Color.WHITE);
        valueName.setHorizontalAlignment(JLabel.RIGHT);
        backgroundPilihanBayar.add(valueName);

        JSeparator garis = new JSeparator();
        garis.setBounds(20, 58, 330, 1);
        garis.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(garis);

        JLabel headerID = new JLabel("ID");
        headerID.setBounds(20, 62, 100, 40);
        headerID.setFont(new Font("Poppins", Font.PLAIN, 18));
        headerID.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(headerID);

        JLabel valueID = new JLabel(IDUsername);
        valueID.setBounds(20, 62, 330, 40);
        valueID.setFont(new Font("Poppins", Font.PLAIN, 16));
        valueID.setForeground(Color.WHITE);
        valueID.setHorizontalAlignment(JLabel.RIGHT);
        backgroundPilihanBayar.add(valueID);

        JSeparator garis1 = new JSeparator();
        garis1.setBounds(20, 108, 330, 1);
        garis1.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(garis1);

        JLabel headerAplikasi = new JLabel("Game / Aplikasi");
        headerAplikasi.setBounds(20, 115, 160, 40);
        headerAplikasi.setFont(new Font("Poppins", Font.PLAIN, 18));
        headerAplikasi.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(headerAplikasi);

        JLabel Aplikasi = new JLabel(gameyangDipilih);
        Aplikasi.setBounds(20, 115, 330, 40);
        Aplikasi.setFont(new Font("Poppins", Font.PLAIN, 18));
        Aplikasi.setForeground(Color.WHITE);
        Aplikasi.setHorizontalAlignment(JLabel.RIGHT);
        backgroundPilihanBayar.add(Aplikasi);
        
        JSeparator garis2 = new JSeparator();
        garis2.setBounds(20, 162, 330, 1);
        garis2.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(garis2);
        
        JLabel headerPembayaran = new JLabel("Pembayaran");
        headerPembayaran.setBounds(20, 167, 160, 40);
        headerPembayaran.setFont(new Font("Poppins", Font.PLAIN, 18));
        headerPembayaran.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(headerPembayaran);

        JLabel valuePembayaran = new JLabel(metodePembayaran);
        valuePembayaran.setBounds(20, 167, 330, 40);
        valuePembayaran.setFont(new Font("Poppins", Font.PLAIN, 18));
        valuePembayaran.setForeground(Color.WHITE);
        valuePembayaran.setHorizontalAlignment(JLabel.RIGHT);
        backgroundPilihanBayar.add(valuePembayaran);

        JLabel totalPembayaran = new JLabel("Total Pembayaran");
        totalPembayaran.setBounds(20, 232, 330, 30);
        totalPembayaran.setFont(new Font("Poppins", Font.PLAIN, 15));
        totalPembayaran.setHorizontalAlignment(JLabel.RIGHT);
        totalPembayaran.setForeground(Color.BLACK);
        borderPilihanBayar.add(totalPembayaran);

        JLabel totalHarga = new JLabel("Rp " + totalHargaBundle);
        totalHarga.setBounds(20, 250, 330, 40);
        totalHarga.setFont(new Font("Poppins", Font.BOLD, 26));
        totalHarga.setHorizontalAlignment(JLabel.RIGHT);
        totalHarga.setForeground(Color.BLACK);
        borderPilihanBayar.add(totalHarga);
        
        final Color warna2 = color;
        JButton btnKonfirmasi = new JButton("Konfirmasi") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(warna2);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            } 
        };
        btnKonfirmasi.setOpaque(false);
        btnKonfirmasi.setBounds(45, 670, 370, 45);
        btnKonfirmasi.setBackground(new Color(68, 98, 128));
        btnKonfirmasi.setForeground(Color.WHITE);
        btnKonfirmasi.setBorder(new RoundedBorder(15, new Color(255, 140, 0)));
        btnKonfirmasi.setFont(new Font("Arial", Font.BOLD, 14));
        btnKonfirmasi.setBorder(BorderFactory.createEmptyBorder());
        btnKonfirmasi.setFocusPainted(false);
        add(btnKonfirmasi);
        
//        Ini untuk nanti saat seller membuka halaman yang udah sukses!
//        
//        JPanel backgroundSuccess = new JPanel();
//        backgroundSuccess.setBounds(45, 670, 370, 48);
//        backgroundSuccess.setBackground(new Color(198, 239, 206));
//        backgroundSuccess.setLayout(null);
//        add(backgroundSuccess);

//        ImageIcon iconSuccess = new ImageIcon(getClass().getResource("/Kinopedia/view/Image/berhasil.png"));
//        Image ambiliconSuccess = iconSuccess.getImage();
//        Image scaledImage = ambiliconSuccess.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon = new ImageIcon(scaledImage);
//        JLabel iconSuccessFix = new JLabel(scaledIcon);
//        iconSuccessFix.setBounds(30, 7, 35, 35);
//        backgroundSuccess.add(iconSuccessFix);
//
//        JLabel text1 = new JLabel("Pembayaran Berhasil");
//        text1.setBounds(80, 4, 280, 25);
//        text1.setFont(new Font("Poppins", Font.BOLD, 13));
//        text1.setForeground(new Color(0, 100, 0)); 
//        backgroundSuccess.add(text1);
//
//        JLabel text2 = new JLabel("Top up telah diproses pada 20-05-2024 · 22:16");
//        text2.setBounds(80, 21, 300, 20);
//        text2.setFont(new Font("Poppins", Font.PLAIN, 13));
//        text2.setForeground(new Color(0, 100, 0)); 
//        backgroundSuccess.add(text2);
        
//        backgroundSuccess.setVisible(false);
        
        btnKonfirmasi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                btnKonfirmasi.setVisible(false);
//                backgroundSuccess.setVisible(true);
                dispose();
                new HalamanConfirmation("Kembali ke halaman sebelumnya", true, "Pembayaran Berhasil", "", "Seller", new Color(75, 105, 135)).setVisible(true);
            }
        });
    }
        
    public static void main(String[] args) {
        new DetailTransaksiSeller(false, "#INV-20240521", "21-05-2025 | 14:30", "KelvinANgajay123", "AWDAWD", "Valorant", "OVO", 100000).setVisible(true);
    }
        
}
