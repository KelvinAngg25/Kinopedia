
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.model;

/**
 *
 * @author William
 */

import Kinopedia.PilihanBundle.BundleValorant;
import Kinopedia.PilihanBundle.BundlePUBG;
import Kinopedia.PilihanBundle.BundleCODM;
import Kinopedia.PilihanBundle.BundleFF;
import Kinopedia.PilihanBundle.BundleML;
import Kinopedia.PilihanBundle.BundleEfootball;
import Kinopedia.PilihanBundle.BundleSteam;
import Kinopedia.view.LoginRegister.Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.Border;

public class MetodeBayar extends JFrame{
    
    public MetodeBayar() {
        setTitle("Kinopedia");
        setSize(470, 844);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        ImageIcon iconBack = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/gopay.png"));
        Image ambilGambarbtnBack = iconBack.getImage();
        Image gambarbtnBack = ambilGambarbtnBack.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel btnBack = new JLabel(new ImageIcon(gambarbtnBack));
        btnBack.setBounds(65, 110, 100, 100);
        add(btnBack);
        
        JButton btnMasuk = new JButton("Masuk");
        btnMasuk.setBounds(40, 400, 370, 45);
        btnMasuk.setBackground(new Color(255, 140, 0));
        btnMasuk.setForeground(Color.WHITE);
        btnMasuk.setBorder(new RoundedBorder(15));
        btnMasuk.setFont(new Font("Arial", Font.BOLD, 14));
        btnMasuk.setBorder(BorderFactory.createEmptyBorder());
        btnMasuk.setFocusPainted(false);
        add(btnMasuk);
        
//=========================================================================
        
        JLabel textPayPal = new JLabel("PayPal");
        textPayPal.setFont(new Font("Poppins", Font.BOLD, 14));
        textPayPal.setBounds(317, 295, 80, 80);
        textPayPal.setForeground(Color.BLACK);
        add(textPayPal);
        
        ImageIcon iconAwalPayPal = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/paypal.jpg"));
        Image ambilGambarPayPal = iconAwalPayPal.getImage();
        Image gambarPayPalFix = ambilGambarPayPal.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel PayPal = new JLabel(new ImageIcon(gambarPayPalFix));
        PayPal.setBounds(300, 240, 80, 80);
        add(PayPal);
        
//=========================================================================
        
        JLabel textDana = new JLabel("Dana");
        textDana.setFont(new Font("Poppins", Font.BOLD, 14));
        textDana.setBounds(210, 295, 80, 80);
        textDana.setForeground(Color.BLACK);
        add(textDana);
        
        ImageIcon iconAwalDana = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/dana.png"));
        Image ambilGambarDana = iconAwalDana.getImage();
        Image gambarDanaFix = ambilGambarDana.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel Dana = new JLabel(new ImageIcon(gambarDanaFix));
        Dana.setBounds(188, 240, 80, 80);
        add(Dana);
        
//=========================================================================
        
        JLabel textMandiri = new JLabel("Mandiri");
        textMandiri.setFont(new Font("Poppins", Font.BOLD, 14));
        textMandiri.setBounds(90, 295, 80, 80);
        textMandiri.setForeground(Color.BLACK);
        add(textMandiri);
        
        ImageIcon iconAwalMandiri = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/livin mandiri.png"));
        Image ambilGambarMandiri = iconAwalMandiri.getImage();
        Image gambarMandiriFix = ambilGambarMandiri.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel Mandiri = new JLabel(new ImageIcon(gambarMandiriFix));
        Mandiri.setBounds(75, 240, 80, 80);
        add(Mandiri);
        
//=========================================================================
        
        JLabel textBCA = new JLabel("BCA");
        textBCA.setFont(new Font("Poppins", Font.BOLD, 14));
        textBCA.setBounds(325, 173, 80, 80);
        textBCA.setForeground(Color.BLACK);
        add(textBCA);
        
        ImageIcon iconAwalBCA = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/mybca.png"));
        Image ambilGambarBCA = iconAwalBCA.getImage();
        Image gambarBCAFix = ambilGambarBCA.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel BCA = new JLabel(new ImageIcon(gambarBCAFix));
        BCA.setBounds(300, 120, 80, 80);
        add(BCA);
        
//=========================================================================
        
        JLabel textOVO = new JLabel("OVO");
        textOVO.setFont(new Font("Poppins", Font.BOLD, 14));
        textOVO.setBounds(213, 173, 80, 80);
        textOVO.setForeground(Color.BLACK);
        add(textOVO);
        
        ImageIcon iconAwalOVO = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/ovo.png"));
        Image ambilGambarOVO = iconAwalOVO.getImage();
        Image gambarOVOFix = ambilGambarOVO.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel OVO = new JLabel(new ImageIcon(gambarOVOFix));
        OVO.setBounds(188, 120, 80, 80);
        add(OVO);
        
//=========================================================================
        
        JLabel textGopay = new JLabel("Gopay");
        textGopay.setFont(new Font("Poppins", Font.BOLD, 14));
        textGopay.setBounds(92, 173, 80, 80);
        textGopay.setForeground(Color.BLACK);
        add(textGopay);
        
        ImageIcon iconAwal = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/gopay.png"));
        Image ambilGambarGopay = iconAwal.getImage();
        Image gambarGopayFix = ambilGambarGopay.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel gopay = new JLabel(new ImageIcon(gambarGopayFix));
        gopay.setBounds(65, 110, 100, 100);
        add(gopay);
        
//=========================================================================
        
        JLabel textHeader = new JLabel("2. Metode Pembayaran");
        textHeader.setFont(new Font("Poppins", Font.BOLD, 16));
        textHeader.setBounds(73, 60, 200, 35);
        textHeader.setForeground(Color.BLACK);
        add(textHeader);
        
        JPanel backgroundTextHeader = new JPanel();
        backgroundTextHeader.setBackground(Color.WHITE);
        backgroundTextHeader.setBounds(60, 60, 200, 35);
        backgroundTextHeader.setLayout(null);
        add(backgroundTextHeader);
        
        JPanel backgroundPilihanBayar = new JPanel();
        backgroundPilihanBayar.setBackground(Color.ORANGE);
        backgroundPilihanBayar.setBounds(40, 80, 372, 290);
        backgroundPilihanBayar.setLayout(null);
        add(backgroundPilihanBayar);
       
        
    }
    
    public static void main(String[] args) {
        MetodeBayar frame = new MetodeBayar();
        frame.setVisible(true);
    }
    
}

class RoundedBorder implements Border {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.GRAY);
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius / 2, radius / 2, radius / 2, radius / 2);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}


