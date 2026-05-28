package Kinopedia.model;
import Kinopedia.DataTransaksi;
import Kinopedia.HalamanConfirmation;
import Kinopedia.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.border.Border;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DetailPembayaran extends JFrame{
    
    public DetailPembayaran(String usernameGame, String idGame, String jenisPembayaran, int totalHargaBundle, String pilihanGame, String username) {
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
                new MetodeBayar("KelvinAngjaya123", "2254012", 432000, "Valorant", "Kelvin").setVisible(true);
            }
        });
        
        final Color warna2 = new Color(255, 140, 0);
        JButton btnMasuk = new JButton("Konfirmasi Pembayaran") {
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
        btnMasuk.setOpaque(false);
        btnMasuk.setContentAreaFilled(false);
        btnMasuk.setBounds(40, 660, 370, 45);
        btnMasuk.setBackground(new Color(255, 140, 0));
        btnMasuk.setForeground(Color.WHITE);
        btnMasuk.setBorder(new RoundedBorder(15, new Color(255, 140, 0)));
        btnMasuk.setFont(new Font("Arial", Font.BOLD, 14));
        btnMasuk.setBorder(BorderFactory.createEmptyBorder());
        btnMasuk.setFocusPainted(false);
        add(btnMasuk);
        
        btnMasuk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//              Untuk load ke-arrayList dari transaksi barusan!

                String invoice;
                boolean ketemu;

                do {
                    ketemu = false;
                    int angkaRandom = (int)(Math.random() * 90000000) + 10000000;
                    invoice = "INV-" + angkaRandom;
                    
                    for (DataTransaksi dt  : Main.dataTransaksi) {
                        if (dt.getIdTransaksi().equals(invoice)) {
                            ketemu = true;
                            break;
                        }
                    }

                    File folder = new File("save/DataTransaksi");
                    File[] files = folder.listFiles();

                    if (files != null){
                        for (File file : files) {
                            
                            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                                String baris;
                                while ((baris = br.readLine()) != null) {
                                    String[] data = baris.split(",");
                                    if (data[0].equals(invoice)) {
                                        ketemu = true;
                                        break;
                                    }
                                }
                                br.close();

                            } catch (IOException f) {}
                            
                            if (ketemu) {
                                break;
                            }
                        }
                    } 
                    
                } while (ketemu);
                
                LocalDate tanggal = LocalDate.now();
                DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String tanggalSekarang = tanggal.format(formatTanggal);

                LocalTime waktu = LocalTime.now();
                DateTimeFormatter formatWaktu = DateTimeFormatter.ofPattern("HH:mm");
                String waktuSekarang = waktu.format(formatWaktu);
                
                Main.dataTransaksi.add(new DataTransaksi(invoice, tanggalSekarang, waktuSekarang, username, idGame, pilihanGame, jenisPembayaran, totalHargaBundle, usernameGame, false, "", ""));
                dispose();
                new HalamanConfirmation("Kembali ke Halaman Awal", true, "Pembayaran Sukses", "Silahkan menunggu konfirmasi penjual", "Buyer",  new Color(255, 140, 0)).setVisible(true);
            }
        });
        
        ImageIcon iconKinoepedia = new ImageIcon(getClass().getResource("/Kinopedia/model/ImageMetodeBayar/logoKinopediaKecil.png"));
        JLabel iconKinopediaKecil = new JLabel(iconKinoepedia);
        iconKinopediaKecil.setBounds(178, 700, 100, 100);
        add(iconKinopediaKecil);
        
        JLabel detailPembayaran = new JLabel("Detail Pembayaran");
        detailPembayaran.setBounds(40, 5, 200, 200);
        detailPembayaran.setFont(new Font("Poppins", Font.BOLD, 18));
        add(detailPembayaran);
        
        final Color warna1 = new Color(255, 255, 255);
        JPanel borderPilihanBayar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(warna1);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }  
        };
        borderPilihanBayar.setLayout(null);
        borderPilihanBayar.setBorder(new RoundedBorder(20, new Color(255, 140, 0)));
        borderPilihanBayar.setBounds(40, 125, 372, 480);
        borderPilihanBayar.setOpaque(true);
        borderPilihanBayar.setBackground(Color.WHITE);
        add(borderPilihanBayar);

        final Color warna = new Color(255, 140, 0);
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
        backgroundPilihanBayar.setBackground(new Color(255, 140, 0));
        backgroundPilihanBayar.setBounds(0, 0, 372, 255); 
        backgroundPilihanBayar.setLayout(null);
        borderPilihanBayar.add(backgroundPilihanBayar); 

        JLabel headerName = new JLabel("Nama");
        headerName.setBounds(20, 15, 100, 40);
        headerName.setFont(new Font("Poppins", Font.PLAIN, 18));
        headerName.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(headerName); 

        JLabel valueName = new JLabel(usernameGame);
        valueName.setBounds(20, 15, 330, 40);
        valueName.setFont(new Font("Poppins", Font.PLAIN, 16));
        valueName.setForeground(Color.WHITE);
        valueName.setHorizontalAlignment(JLabel.RIGHT);
        backgroundPilihanBayar.add(valueName);

        JSeparator garis = new JSeparator();
        garis.setBounds(20, 70, 330, 1);
        garis.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(garis);

        JLabel headerID = new JLabel("ID");
        headerID.setBounds(20, 85, 100, 40);
        headerID.setFont(new Font("Poppins", Font.PLAIN, 18));
        headerID.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(headerID);

        JLabel valueID = new JLabel(idGame);
        valueID.setBounds(20, 85, 330, 40);
        valueID.setFont(new Font("Poppins", Font.PLAIN, 16));
        valueID.setForeground(Color.WHITE);
        valueID.setHorizontalAlignment(JLabel.RIGHT);
        backgroundPilihanBayar.add(valueID);

        JSeparator garis1 = new JSeparator();
        garis1.setBounds(20, 140, 330, 1);
        garis1.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(garis1);

        JLabel headerPembayaran = new JLabel("Pembayaran");
        headerPembayaran.setBounds(20, 155, 160, 40);
        headerPembayaran.setFont(new Font("Poppins", Font.PLAIN, 18));
        headerPembayaran.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(headerPembayaran);

        JLabel valuePembayaran = new JLabel(jenisPembayaran);
        valuePembayaran.setBounds(20, 155, 330, 40);
        valuePembayaran.setFont(new Font("Poppins", Font.PLAIN, 18));
        valuePembayaran.setForeground(Color.WHITE);
        valuePembayaran.setHorizontalAlignment(JLabel.RIGHT);
        backgroundPilihanBayar.add(valuePembayaran);
        
        JSeparator garis2 = new JSeparator();
        garis2.setBounds(20, 210, 330, 1);
        garis2.setForeground(Color.BLACK);
        backgroundPilihanBayar.add(garis2);

        JLabel totalPembayaran = new JLabel("Total Pembayaran");
        totalPembayaran.setBounds(20, 405, 330, 30);
        totalPembayaran.setFont(new Font("Poppins", Font.PLAIN, 15));
        totalPembayaran.setHorizontalAlignment(JLabel.RIGHT);
        borderPilihanBayar.add(totalPembayaran);

        JLabel totalHarga = new JLabel("Rp " + totalHargaBundle);
        totalHarga.setBounds(20, 425, 330, 40);
        totalHarga.setFont(new Font("Poppins", Font.BOLD, 26));
        totalHarga.setHorizontalAlignment(JLabel.RIGHT);
        borderPilihanBayar.add(totalHarga);
    }
    
    public static void main(String[] args) {
        DetailPembayaran frame = new DetailPembayaran("", "", "", 0, "", "");
        frame.setVisible(true);
    }
}