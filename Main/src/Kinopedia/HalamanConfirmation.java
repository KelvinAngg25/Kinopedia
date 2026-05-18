package Kinopedia;

import Kinopedia.minigames.MainMiniGames;
import Kinopedia.model.Buyer;
import Kinopedia.model.Game;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author William
 */
public class HalamanConfirmation extends JFrame{
    
    public HalamanConfirmation(String isiTeksButton, boolean statusKonfirmasi, String header, String subHeader, String halamanTerakhir, Color color) {
        String letakGambar = "";
        
        setTitle("Kinopedia");
        setSize(470, 844);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        if (statusKonfirmasi) {
            letakGambar = "/Kinopedia/view/Image/berhasil.png";
        } else {
            letakGambar = "/Kinopedia/view/Image/gagal.png";
        }
        
        ImageIcon iconPenanda = new ImageIcon(getClass().getResource(letakGambar));
        JLabel gambarPenanda = new JLabel(iconPenanda);
        gambarPenanda.setBounds(0, 90, 450, 300);
        gambarPenanda.setHorizontalAlignment(JLabel.CENTER);
        add(gambarPenanda);
        
        JLabel headerText = new JLabel(header);
        headerText.setBounds(0, 320, 450, 200);
        headerText.setFont(new Font("Poppins", Font.BOLD, 30));
        headerText.setHorizontalAlignment(JLabel.CENTER);
        add(headerText);
        
        JLabel subHeaderText = new JLabel(subHeader);
        subHeaderText.setBounds(0, 350, 450, 200);
        subHeaderText.setFont(new Font("Poppins", Font.BOLD, 15));
        subHeaderText.setHorizontalAlignment(JLabel.CENTER);
        add(subHeaderText);
        
        final Color warna2 = color;
        JButton btnExit = new JButton(isiTeksButton){
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
        btnExit.setOpaque(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setBounds(40, 660, 370, 45);
        btnExit.setBackground(color);
        btnExit.setForeground(Color.WHITE);
        btnExit.setBorder(new RoundedBorder(15));
        btnExit.setFont(new Font("Arial", Font.BOLD, 14));
        btnExit.setBorder(BorderFactory.createEmptyBorder());
        btnExit.setFocusPainted(false);
        add(btnExit);
        
        btnExit.addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseClicked (MouseEvent e) {
                if (halamanTerakhir.equals("Coin")) {
                    dispose();
                    new MainMiniGames().setVisible(true);
                } else if (halamanTerakhir.equals("Seller")) {
                    dispose();
//                    Diisi sama halaman utama seller
//                    new MainMiniGames().setVisible(true);
                } else if (halamanTerakhir.equals("Buyer")) {
                    dispose();
//                    Diisi sama halaman utama buyer
                    new Game(new Buyer()).setVisible(true);
                }
            }
        });
        
        ImageIcon iconKinoepedia = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/logoKinopediaKecil.png"));
        JLabel iconKinopediaKecil = new JLabel(iconKinoepedia);
        iconKinopediaKecil.setBounds(178, 700, 100, 100);
        add(iconKinopediaKecil);
    }
}


class RoundedBorder implements javax.swing.border.Border {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
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