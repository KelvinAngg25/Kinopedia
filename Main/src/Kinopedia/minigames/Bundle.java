/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames;

import javax.swing.ImageIcon;

/**
 *
 * @author Vincent
 */
public class Bundle {
    private String currencyName;
    private String NamaGame, jumlah;
    private int index,harga;

    public Bundle(String currencyName, String NamaGame,int index,int harga,String jumlah) {
        this.currencyName = currencyName;
        this.NamaGame = NamaGame;
        this.index = index;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
    
    

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getNamaGame() {
        return NamaGame;
    }

    public void setNamaGame(String NamaGame) {
        this.NamaGame = NamaGame;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    
    
    
}
