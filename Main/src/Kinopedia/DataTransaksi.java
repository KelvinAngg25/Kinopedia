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
public class DataTransaksi {
    private String idTransaksi;
    private String tanggal;
    private String waktu;
    private String username;
    private String idGame;
    private String jenisGame;
    private String pembayaran;
    private int nominal;
    
    private boolean konfirmasi;
    private String tanggalKonfirmasi;
    private String waktuKonfirmasi;

    public DataTransaksi(String idTransaksi, String tanggal, String waktu, String username, String idGame, String jenisGame, String pembayaran, int nominal, boolean konfirmasi, String tanggalKonfirmasi, String waktuKonfirmasi) {
        this.idTransaksi = idTransaksi;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.username = username;
        this.idGame = idGame;
        this.jenisGame = jenisGame;
        this.pembayaran = pembayaran;
        this.nominal = nominal;
        this.konfirmasi = konfirmasi;
        this.tanggalKonfirmasi = tanggalKonfirmasi;
        this.waktuKonfirmasi = waktuKonfirmasi;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getUsername() {
        return username;
    }

    public String getIdGame() {
        return idGame;
    }

    public String getJenisGame() {
        return jenisGame;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public int getNominal() {
        return nominal;
    }

    public boolean isKonfirmasi() {
        return konfirmasi;
    }

    public String getTanggalKonfirmasi() {
        return tanggalKonfirmasi;
    }

    public String getWaktuKonfirmasi() {
        return waktuKonfirmasi;
    }
    
    

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public void setNamaUser(String username) {
        this.username = username;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public void setJenisGame(String jenisGame) {
        this.jenisGame = jenisGame;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
    
}
