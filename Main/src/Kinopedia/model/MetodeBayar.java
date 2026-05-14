
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.model;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MetodeBayar extends JFrame{
    
    private String pilihanMetodePembayaran;
    
    public MetodeBayar() {
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
        
        JButton btnMasuk = new JButton("Lanjut ke Detail Pembayaran");
        btnMasuk.setBounds(40, 660, 370, 45);
        btnMasuk.setBackground(new Color(255, 140, 0));
        btnMasuk.setForeground(Color.WHITE);
        btnMasuk.setBorder(new RoundedBorder(15));
        btnMasuk.setFont(new Font("Arial", Font.BOLD, 14));
        btnMasuk.setBorder(BorderFactory.createEmptyBorder());
        btnMasuk.setFocusPainted(false);
        add(btnMasuk);
        
//=========================================================================

        JPanel panelPayPal = new JPanel();
        panelPayPal.setLayout(null);
        panelPayPal.setBackground(new Color(255, 140, 0));
        panelPayPal.setBounds(290, 260, 100, 120);
        add(panelPayPal);
        
        JLabel textPayPal = new JLabel("PayPal");
        textPayPal.setFont(new Font("Poppins", Font.BOLD, 14));
        textPayPal.setBounds(28, 54, 100, 100);
        textPayPal.setForeground(Color.BLACK);
        panelPayPal.add(textPayPal);
        
        ImageIcon iconAwalPayPal = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/paypal.jpg"));
        Image ambilGambarPayPal = iconAwalPayPal.getImage();
        Image gambarPayPalFix = ambilGambarPayPal.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel PayPal = new JLabel(new ImageIcon(gambarPayPalFix));
        PayPal.setBounds(0, 0, 100, 100);
        panelPayPal.add(PayPal);
        
//=========================================================================

        JPanel panelDana = new JPanel();
        panelDana.setLayout(null);
        panelDana.setBackground(new Color(255, 140, 0));
        panelDana.setBounds(178, 260, 100, 120);
        add(panelDana);
        
        JLabel textDana = new JLabel("Dana");
        textDana.setFont(new Font("Poppins", Font.BOLD, 14));
        textDana.setBounds(33, 54, 100, 100);
        textDana.setForeground(Color.BLACK);
        panelDana.add(textDana);
        
        ImageIcon iconAwalDana = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/dana.png"));
        Image ambilGambarDana = iconAwalDana.getImage();
        Image gambarDanaFix = ambilGambarDana.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel Dana = new JLabel(new ImageIcon(gambarDanaFix));
        Dana.setBounds(0, 0, 100, 100);
        panelDana.add(Dana);
        
//=========================================================================

        JPanel panelMandiri = new JPanel();
        panelMandiri.setLayout(null);
        panelMandiri.setBackground(new Color(255, 140, 0));
        panelMandiri.setBounds(65, 260, 100, 120);
        add(panelMandiri);
        
        JLabel textMandiri = new JLabel("Mandiri");
        textMandiri.setFont(new Font("Poppins", Font.BOLD, 14));
        textMandiri.setBounds(24, 54, 100, 100);
        textMandiri.setForeground(Color.BLACK);
        panelMandiri.add(textMandiri);
        
        ImageIcon iconAwalMandiri = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/livin mandiri.png"));
        Image ambilGambarMandiri = iconAwalMandiri.getImage();
        Image gambarMandiriFix = ambilGambarMandiri.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel Mandiri = new JLabel(new ImageIcon(gambarMandiriFix));
        Mandiri.setBounds(0, 0, 100, 100);
        panelMandiri.add(Mandiri);
        
        
        
//=========================================================================
        
        JPanel panelBCA = new JPanel();
        panelBCA.setLayout(null);
        panelBCA.setBackground(new Color(255, 140, 0));
        panelBCA.setBounds(290, 140, 100, 120);
        add(panelBCA);

        JLabel textBCA = new JLabel("BCA");
        textBCA.setFont(new Font("Poppins", Font.BOLD, 14));
        textBCA.setBounds(35, 53, 100, 100);
        textBCA.setForeground(Color.BLACK);
        panelBCA.add(textBCA);
        
        ImageIcon iconAwalBCA = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/mybca.png"));
        Image ambilGambarBCA = iconAwalBCA.getImage();
        Image gambarBCAFix = ambilGambarBCA.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel BCA = new JLabel(new ImageIcon(gambarBCAFix));
        BCA.setBounds(0, 0, 100, 100);
        panelBCA.add(BCA);
        
//=========================================================================
        
        JPanel panelOVO = new JPanel();
        panelOVO.setLayout(null);
        panelOVO.setBackground(new Color(255, 140, 0));
        panelOVO.setBounds(178, 140, 100, 120);
        add(panelOVO);

        JLabel textOVO = new JLabel("OVO");
        textOVO.setFont(new Font("Poppins", Font.BOLD, 14));
        textOVO.setBounds(35, 53, 100, 100);
        textOVO.setForeground(Color.BLACK);
        panelOVO.add(textOVO);
        
        ImageIcon iconAwalOVO = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/ovo.png"));
        Image ambilGambarOVO = iconAwalOVO.getImage();
        Image gambarOVOFix = ambilGambarOVO.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel OVO = new JLabel(new ImageIcon(gambarOVOFix));
        OVO.setBounds(0, 0, 100, 100);
        panelOVO.add(OVO);
        
        
        
//=========================================================================

        JPanel panelGopay = new JPanel();
        panelGopay.setLayout(null);
        panelGopay.setBackground(new Color(255, 140, 0));
        panelGopay.setBounds(65, 140, 100, 120);
        add(panelGopay);
        
        JLabel textGopay = new JLabel("Gopay");
        textGopay.setFont(new Font("Poppins", Font.BOLD, 14));
        textGopay.setBounds(29, 53, 100, 100);
        textGopay.setForeground(Color.BLACK);
        panelGopay.add(textGopay);
        
        ImageIcon iconAwal = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/gopay.png"));
        Image ambilGambarGopay = iconAwal.getImage();
        Image gambarGopayFix = ambilGambarGopay.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel gopay = new JLabel(new ImageIcon(gambarGopayFix));
        gopay.setBounds(0, 0, 100, 100);
        panelGopay.add(gopay);
        
//=========================================================================
        
        JLabel textHeader = new JLabel("2. Metode Pembayaran");
        textHeader.setFont(new Font("Poppins", Font.BOLD, 16));
        textHeader.setBounds(73, 90, 200, 35);
        textHeader.setForeground(Color.BLACK);
        add(textHeader);
        
        JPanel backgroundTextHeader = new JPanel();
        backgroundTextHeader.setBackground(Color.WHITE);
        backgroundTextHeader.setBounds(60, 90, 200, 35);
        backgroundTextHeader.setLayout(null);
        backgroundTextHeader.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2));
        add(backgroundTextHeader);
        
        JPanel backgroundPilihanBayar = new JPanel();
        backgroundPilihanBayar.setBackground(new Color(255, 140, 0));
        backgroundPilihanBayar.setBounds(40, 110, 372, 290);
        backgroundPilihanBayar.setLayout(null);
        add(backgroundPilihanBayar);
        
        ImageIcon iconKinoepedia = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/logoKinopediaKecil.png"));
        JLabel iconKinopediaKecil = new JLabel(iconKinoepedia);
        iconKinopediaKecil.setBounds(178, 700, 100, 100);
        add(iconKinopediaKecil);
        
        ArrayList<JPanel> daftarSemuaPanel = new ArrayList();
        daftarSemuaPanel.add(panelOVO);
        daftarSemuaPanel.add(panelGopay);
        daftarSemuaPanel.add(panelDana);
        daftarSemuaPanel.add(panelMandiri);
        daftarSemuaPanel.add(panelPayPal);
        daftarSemuaPanel.add(panelBCA);
        
        for (JPanel jPanel : daftarSemuaPanel) {
            jPanel.addMouseListener(new MouseAdapter() { 
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JPanel p : daftarSemuaPanel) {
                        p.setBorder(null);
                    }
                    
                    if (jPanel == panelGopay) {
                        setPilihanMetodePembayaran("Gopay");
                    }
                    else if (jPanel == panelOVO) {
                        setPilihanMetodePembayaran("OVO");
                    }
                    else if (jPanel == panelDana) {
                        setPilihanMetodePembayaran("Dana");
                    }
                    else if (jPanel == panelMandiri) {
                        setPilihanMetodePembayaran("Mandiri");
                    }
                    else if (jPanel == panelBCA) {
                        setPilihanMetodePembayaran("BCA");
                    }
                    else if (jPanel == panelPayPal) {
                        setPilihanMetodePembayaran("PayPal");
                    }
                    
                    jPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2, true));
                }
            });
        }
    }

    public void setPilihanMetodePembayaran(String pilihanMetodePembayaran) {
        this.pilihanMetodePembayaran = pilihanMetodePembayaran;
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


