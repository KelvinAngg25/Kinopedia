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

public class KoinManager {
    private static final int BIAYA_KREDIT_PER_GAME = 1;
    
    public static boolean bisaBermain() {
        DataUser user = Session.getInstance().getCurrentUser();
        
        if (user == null) {
            return false;
        }
        
        if (user.getKredit() >= BIAYA_KREDIT_PER_GAME) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean kurangiKredit() {
        DataUser user = Session.getInstance().getCurrentUser();
        
        if (user == null) {
            System.out.println("[KoinManager] Gagal kurangi kredit: user null");
            return false;
        }
        
        int kreditLama = user.getKredit();
        if (kreditLama < BIAYA_KREDIT_PER_GAME) {
            System.out.println("[KoinManager] Kredit tidak cukup: " + kreditLama);
            return false;
        }
        
        int kreditBaru = kreditLama - BIAYA_KREDIT_PER_GAME;
        user.setKredit(kreditBaru);
        
        System.out.println("[KoinManager] Kredit dikurangi 1" + "Kredit lama: " + kreditLama + " | Kredit baru: " + kreditBaru);
        
        saveUserToFile(user);
        
        return true;
    }
    
    private static void saveUserToFile(DataUser user) {
        try {
            String namaFile = "save/DataUser/" + user.getNama() + ".txt";
            java.io.FileWriter fw = new java.io.FileWriter(namaFile);
            fw.write(user.getNama() + "," + user.getPassword() + "," + user.getKoin() + "," + user.getKredit() + "\n");
            fw.close();
            System.out.println("[KoinManager] Data user berhasil disimpan: " + namaFile);
        } catch (java.io.IOException e) {
            System.out.println("[KoinManager] Gagal menyimpan data user: " + e.getMessage());
        }
    }
    
    public static void prosesReward(GameReward reward) {
        String hasil = reward.prosesReward();
        System.out.println("[KoinManager] Hasil reward: " + hasil);
    }
    
    public static int getKreditSekarang() {
        DataUser user = Session.getInstance().getCurrentUser();
        
        if (user == null) {
            return 0;
        }
        return user.getKredit();
    }
    
    public static int getKoinSekarang() {
        DataUser user = Session.getInstance().getCurrentUser();
        
        if (user == null) {
            return 0;
        }
        return user.getKoin();
    }
}
