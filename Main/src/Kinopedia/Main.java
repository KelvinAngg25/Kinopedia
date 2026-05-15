package Kinopedia;


import Kinopedia.view.LoginRegister.Login;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William
 */
public class Main {
    public static ArrayList<DataTransaksi> dataTransaksi = new ArrayList<DataTransaksi>();
    public static ArrayList<DataUser> dataUser = new ArrayList<DataUser>();
    public static ArrayList<DataAkun> dataAkun = new ArrayList<DataAkun>();
    public static DataUser admin; // yg dipake hanya idx 0-2, 0 utk user admin, 1 utk pass admin, 2 utk saldo admin.
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        loadSemuaData(dataTransaksi,dataUser);
        
        new Login(dataTransaksi,dataUser);
    }
    
    public static void loadSemuaData(ArrayList<DataTransaksi> dataTransaksi,ArrayList<DataUser> dataUser) {
        File folder = new File("save/DataTransaksi");
        File[] files = folder.listFiles();

        if (files != null){
            for (File file : files) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String baris;
                    while ((baris = br.readLine()) != null) {
                        String[] data = baris.split(",");
                        dataTransaksi.add(new DataTransaksi(data[0],data[1], data[2], data[3], data[4], data[5], data[6], Integer.parseInt(data[7]), Boolean.parseBoolean(data[8]), data[9], data[10]));
                    }
                    br.close();
                    
                } catch (IOException e) {
                    System.out.println("Gagal baca: " + file.getName());
                }
            }
        } 
        System.out.println("Total Data Transaksi : " + dataTransaksi.size());
        
        folder = new File("save/DataUser");
        files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                System.out.println(file);
                if (file.getName().equals("Admin.txt")) {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String baris;
                        while ((baris = br.readLine()) != null) {
                            String[] data = baris.split(",");
                            Main.admin = new DataUser(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                        }
                        System.out.println("Berhasil Baca Admin");
                        br.close();
                    } catch (IOException e) {
                        System.out.println("Gagal baca: " + file.getName());
                    }
                } else {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String baris;
                        while ((baris = br.readLine()) != null) {
                            String[] data = baris.split(",");
                            dataUser.add(new DataUser(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
                        }
                        br.close();
                        
                    } catch (IOException e) {
                        System.out.println("Gagal baca: " + file.getName());
                    }
                }
            }
            System.out.println("Total Data User : " + dataUser.size());
        }
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("save/DataAkun.txt"));
            String baris;
            System.out.println();
            while ((baris = br.readLine()) != null) {
                String[] data = baris.split(",");
                String idAkun = data[0];
                String namaAkun = data[1];
                String jenisGame = data[2];
                dataAkun.add(new DataAkun(idAkun, namaAkun, jenisGame));
            }
            System.out.println("Berhasil Memuat data DataAkun.txt");
            br.close();
            
        } catch (IOException e) {
            System.out.println("  Gagal membaca data DataAkun.txt!");
        }
        System.out.println("Total Data Akun : " + dataAkun.size());
    }
    
    public static void saveSemuaData(ArrayList<DataTransaksi> dataTransaksi,ArrayList<DataUser> dataUser, DataUser admin) {
        for (int i = 0; i < dataTransaksi.size() ; i++) {
            try {
                String namaFile = "save/DataTransaksi/" + i + ".txt";
                FileWriter fw = new FileWriter(namaFile);
                fw.write(dataTransaksi.get(i).getIdTransaksi() + "," + dataTransaksi.get(i).getTanggal() + ","+ dataTransaksi.get(i).getWaktu() + ","+ dataTransaksi.get(i).getUsername() + ","+ dataTransaksi.get(i).getIdGame() + ","+ dataTransaksi.get(i).getJenisGame() + ","+ dataTransaksi.get(i).getPembayaran() +  ","+ dataTransaksi.get(i).getNominal() +  "," + dataTransaksi.get(i).isKonfirmasi() + "," + dataTransaksi.get(i).getTanggalKonfirmasi() + "," + dataTransaksi.get(i).getWaktuKonfirmasi() +  "\n");
                System.out.println("  Menyimpan "+ i + ".txt...   OK");
                fw.close();
                
            } catch (IOException e) {
                System.out.println("  Gagal menyimpan "+ i +".txt!");
            }
        }
        System.out.println("Total Data Transaksi : " + dataTransaksi.size());
        
        
        for (int i = 0; i < dataUser.size(); i++) {
            try {
                String namaFile = "save/DataUser/" + dataUser.get(i).getNama() + ".txt";
                FileWriter fw = new FileWriter(namaFile);
                fw.write( dataUser.get(i).getNama() + ","+ dataUser.get(i).getPassword() + "," + dataUser.get(i).getKoin() + "," + dataUser.get(i).getKredit() +  "\n");
                System.out.println("  Menyimpan "+ dataUser.get(i).getNama() + ".txt...   OK");
                fw.close();
            } catch (IOException e) {
                System.out.println("  Gagal menyimpan "+ dataUser.get(i).getNama() +".txt!");
            }
        }
        System.out.println("Total Data User : " + dataUser.size());
        
        try {
            String namaFile = "save/DataUser/Admin.txt";
            FileWriter fw = new FileWriter(namaFile);
            fw.write( admin.getNama() + ","+ admin.getPassword() + "," + admin.getKoin() + "," + admin.getKredit() +  "\n");
            System.out.println("  Menyimpan Admin.txt...   OK");
            fw.close();
        } catch (IOException e) {
            System.out.println("  Gagal menyimpan Admin.txt!");
        }
        System.out.println("Saldo Admin : " + admin.getKoin());
    }
}
