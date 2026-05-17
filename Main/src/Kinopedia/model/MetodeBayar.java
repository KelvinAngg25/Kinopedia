package Kinopedia.model;

import Kinopedia.minigames.MainMiniGames;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
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
        
        JButton btnMasuk = new JButton("Lanjut ke Detail Pembayaran") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(-1, -1, getWidth() + 2, getHeight() + 2, 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnMasuk.setBounds(40, 660, 370, 45);
        btnMasuk.setBackground(new Color(255, 140, 0));
        btnMasuk.setForeground(Color.BLACK);
        btnMasuk.setBorder(new RoundedBorder(20, new Color(255, 140, 0)));
        btnMasuk.setFont(new Font("Arial", Font.BOLD, 14));
        btnMasuk.setMargin(new Insets(0, 0, 0, 0));
        btnMasuk.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnMasuk.setOpaque(false);
        btnMasuk.setContentAreaFilled(false);
        btnMasuk.setFocusable(false); 
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
        RoundedImageLabel PayPal = new RoundedImageLabel(18);
        PayPal.setIcon(new ImageIcon(gambarPayPalFix));
        PayPal.setBounds(10, 5, 80, 80);
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
        RoundedImageLabel Dana = new RoundedImageLabel(18);
        Dana.setIcon(new ImageIcon(gambarDanaFix));
        Dana.setBounds(10, 5, 80, 80);
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
        RoundedImageLabel Mandiri = new RoundedImageLabel(18);
        Mandiri.setIcon(new ImageIcon(gambarMandiriFix));
        Mandiri.setBounds(10, 5, 80, 80);
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
        RoundedImageLabel BCA = new RoundedImageLabel(18);
        BCA.setIcon(new ImageIcon(gambarBCAFix));
        BCA.setBounds(10, 5, 80, 80);
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
        RoundedImageLabel OVO = new RoundedImageLabel(18);
        OVO.setIcon(new ImageIcon(gambarOVOFix));
        OVO.setBounds(10, 5, 80, 80);
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
        RoundedImageLabel gopay = new RoundedImageLabel(18);
        gopay.setIcon(new ImageIcon(gambarGopayFix));
        gopay.setBounds(10, 5, 80, 80); 
        panelGopay.add(gopay);
        
//=========================================================================
        
        JLabel textHeader = new JLabel("2. Metode Pembayaran");
        textHeader.setFont(new Font("Poppins", Font.BOLD, 16));
        textHeader.setBounds(73, 90, 200, 35);
        textHeader.setForeground(Color.BLACK);
        add(textHeader);
        
        JPanel backgroundTextHeader = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
                super.paintComponent(g);
            }
            
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 140, 0));
                g2.setStroke(new BasicStroke(1)); 
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight(), 40, 40);
                g2.dispose();
            }
        };
        backgroundTextHeader.setBackground(Color.WHITE);
        backgroundTextHeader.setBounds(60, 90, 200, 35);
        backgroundTextHeader.setLayout(null);
        backgroundTextHeader.setOpaque(false);
        add(backgroundTextHeader);
        
        JPanel backgroundPilihanBayar = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        backgroundPilihanBayar.setBackground(new Color(255, 140, 0));
        backgroundPilihanBayar.setBounds(40, 110, 372, 290);
        backgroundPilihanBayar.setLayout(null);
        backgroundPilihanBayar.setOpaque(false);
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

    public String getPilihanMetodePembayaran() {
        return pilihanMetodePembayaran;
    }

    public void setPilihanMetodePembayaran(String pilihanMetodePembayaran) {
        this.pilihanMetodePembayaran = pilihanMetodePembayaran;
    }
    
    public static void main(String[] args) {
        MetodeBayar frame = new MetodeBayar();
        frame.setVisible(true);
    }
    
    private static class RoundedImageLabel extends JLabel {

        private int radius;

        public RoundedImageLabel(int radius) {
            this.radius = radius;
            setOpaque(false);
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            // 1) copy graphics
            Graphics2D g2 = (Graphics2D) g.create();

            // 2) make a rounded shape and clip (everything outside becomes hidden)
            Shape round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setClip(round);

            // 3) draw the normal JLabel (icon) but clipped
            super.paintComponent(g2);

            // 4) cleanup
            g2.dispose();
        }
    }
    
}

class RoundedBorder implements javax.swing.border.Border {
    private int radius;
    private Color color;

    public RoundedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 140, 0));
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public java.awt.Insets getBorderInsets(Component c) {
        return new java.awt.Insets(radius / 2, radius / 2, radius / 2, radius / 2);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}


