/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.model;

import Kinopedia.Main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 *
 * @author William
 */
public class DetailTransaksi extends JFrame{
    
    public DetailTransaksi(boolean penandaBerhasilAtauGagal, String IDTransaksiTampil, String TanggalDanWaktu, String usernameIngame, String IDUsername, String gameyangDipilih, String metodePembayaran, int totalHargaBundle) {
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
                new History().setVisible(true);
                dispose();
            }
        });
        
        ImageIcon iconKinoepedia = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/logoKinopediaKecil.png"));
        JLabel iconKinopediaKecil = new JLabel(iconKinoepedia);
        iconKinopediaKecil.setBounds(178, 700, 100, 100);
        add(iconKinopediaKecil);
        
        Color color = null;
        
        if (!penandaBerhasilAtauGagal) {
            color = new Color(220, 80, 70);
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
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // ← 20 sesuaikan dengan radius border kamu
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
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // ← 20 sesuaikan dengan radius border kamu
                g2.dispose();
            }
        };
        borderPilihanBayar.setLayout(null);
        borderPilihanBayar.setBorder(new RoundedBorder(20, color));
        borderPilihanBayar.setBounds(45, 350, 372, 350);
        borderPilihanBayar.setOpaque(false);
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
        backgroundPilihanBayar.setOpaque(false);
        backgroundPilihanBayar.setBounds(0, 0, 372, 275); 
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
        garis.setBounds(20, 65, 330, 1);
        garis.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(garis);

        JLabel headerID = new JLabel("ID");
        headerID.setBounds(20, 80, 100, 40);
        headerID.setFont(new Font("Poppins", Font.PLAIN, 18));
        headerID.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(headerID);

        JLabel valueID = new JLabel(IDUsername);
        valueID.setBounds(20, 80, 330, 40);
        valueID.setFont(new Font("Poppins", Font.PLAIN, 16));
        valueID.setForeground(Color.WHITE);
        valueID.setHorizontalAlignment(JLabel.RIGHT);
        backgroundPilihanBayar.add(valueID);

        JSeparator garis1 = new JSeparator();
        garis1.setBounds(20, 135, 330, 1);
        garis1.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(garis1);

        JLabel headerAplikasi = new JLabel("Game / Aplikasi");
        headerAplikasi.setBounds(20, 150, 160, 40);
        headerAplikasi.setFont(new Font("Poppins", Font.PLAIN, 18));
        headerAplikasi.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(headerAplikasi);

        JLabel Aplikasi = new JLabel(gameyangDipilih);
        Aplikasi.setBounds(20, 150, 330, 40);
        Aplikasi.setFont(new Font("Poppins", Font.PLAIN, 18));
        Aplikasi.setForeground(Color.WHITE);
        Aplikasi.setHorizontalAlignment(JLabel.RIGHT);
        backgroundPilihanBayar.add(Aplikasi);
        
        JSeparator garis2 = new JSeparator();
        garis2.setBounds(20, 205, 330, 1);
        garis2.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(garis2);
        
        JLabel headerPembayaran = new JLabel("Pembayaran");
        headerPembayaran.setBounds(20, 217, 160, 40);
        headerPembayaran.setFont(new Font("Poppins", Font.PLAIN, 18));
        headerPembayaran.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(headerPembayaran);

        JLabel valuePembayaran = new JLabel(metodePembayaran);
        valuePembayaran.setBounds(20, 217, 330, 40);
        valuePembayaran.setFont(new Font("Poppins", Font.PLAIN, 18));
        valuePembayaran.setForeground(Color.WHITE);
        valuePembayaran.setHorizontalAlignment(JLabel.RIGHT);
        backgroundPilihanBayar.add(valuePembayaran);

        JLabel totalPembayaran = new JLabel("Total Pembayaran");
        totalPembayaran.setBounds(20, 282, 330, 30);
        totalPembayaran.setFont(new Font("Poppins", Font.PLAIN, 15));
        totalPembayaran.setHorizontalAlignment(JLabel.RIGHT);
        totalPembayaran.setForeground(Color.BLACK);
        borderPilihanBayar.add(totalPembayaran);

        JLabel totalHarga = new JLabel("Rp " + totalHargaBundle);
        totalHarga.setBounds(20, 300, 330, 40);
        totalHarga.setFont(new Font("Poppins", Font.BOLD, 26));
        totalHarga.setHorizontalAlignment(JLabel.RIGHT);
        totalHarga.setForeground(Color.BLACK);
        borderPilihanBayar.add(totalHarga);
    }
        
    public static void main(String[] args) {
        new DetailTransaksi(Main.dataTransaksi.get(1).isKonfirmasi(), Main.dataTransaksi.get(1).getIdTransaksi(), Main.dataTransaksi.get(1).getTanggal(), Main.dataTransaksi.get(1).getUsername(), Main.dataTransaksi.get(1).getUsername(), Main.dataTransaksi.get(1).getJenisGame(), Main.dataTransaksi.get(1).getPembayaran(), 100000).setVisible(true);
    }
        
}
