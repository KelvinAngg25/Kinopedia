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

/**
 *
 * @author Vincent
 */
public class PenukaranKoin extends JFrame {
    Kinopedia.DataUser user = Session.getInstance().getCurrentUser();
    ArrayList<Bundle> bundle = new ArrayList<>();
    private ImageIcon logoFooter;
    int index = 0;
    JPanel selected = null;
    Bundle diPilih = null;

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
        
        add(btnTukar);
        add(bgBundle);
        
        
        
        
    }
    public static void main(String[] args) {
            PenukaranKoin frame = new PenukaranKoin();
            frame.setVisible(true);
    }
}
