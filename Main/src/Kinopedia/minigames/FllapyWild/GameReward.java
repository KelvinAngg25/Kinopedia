/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.FllapyWild;

/**
 *
 * @author Fabiola
 */

import Kinopedia.DataUser;
import Kinopedia.Session;

public abstract class GameReward {
    private int score;
    
    public GameReward(int score) {
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }
    
    public abstract int hitungKoin();
    
    public abstract String namaGame();
    
    public final String prosesReward() {
        int koinDidapat = hitungKoin();
        String hasil = simpanKoin(koinDidapat);
        return hasil;
    }
    
    private String simpanKoin(int jumlahKoin) {
        DataUser user = Session.getInstance().getCurrentUser();
        
        if (user == null) {
            return "Gagal: user tidak ditemukan di session";
        }
        user.setKoin(user.getKoin() + jumlahKoin);
        
        System.out.println("[" + namaGame() + "] Koin didapat: " + jumlahKoin + " | Total koin baru: " + user.getKoin());
        
        saveUserToFile(user);
        return "Berhasil menambah " + jumlahKoin + " koin.";
    }
    
    public void saveUserToFile(DataUser user) {
        try {
            String namaFile = "save/DataUser/" + user.getNama() + ".txt";
            java.io.FileWriter fw = new java.io.FileWriter(namaFile);
            fw.write(user.getNama() + "," + user.getPassword() + "," + user.getKoin() + "," + user.getKredit() + "\n");
            fw.close();
            System.out.println("[GameReward] Data user berhasil disimpan: " + namaFile);
        } catch (java.io.IOException e) {
            System.out.println("[GameReward] Gagal menyimpan data user: " + e.getMessage());
        }
    }
}
