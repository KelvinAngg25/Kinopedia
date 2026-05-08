/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Victus
 */
public class DataUser {
    private String username;
    private String password;
    private int koin;
    private int kredit;

    public DataUser(String username, String password, int koin, int kredit) {
        this.username = username;
        this.password = password;
        this.koin = koin;
        this.kredit = kredit;
    }

    public String getNama() {
        return username;
    }

    public void setNama(String nama) {
        this.username = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getKoin() {
        return koin;
    }

    public void setKoin(int koin) {
        this.koin = koin;
    }

    public int getKredit() {
        return kredit;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }
    
    public static void loadSemuaUser(ArrayList<DataUser> dataUser) {
        File folder = new File("save/DataUser");
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
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
    }
}
