/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.model;

/**
 *
 * @author William
 */

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Model data untuk satu transaksi top-up di Kinopedia. Implements Serializable agar bisa disimpan ke file.

public class Transaksi implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    // ID unik transaksi, contoh: #INV-20240521-001
    private String idTransaksi;

    // Tanggal & waktu transaksi, contoh: 21-05-2024 · 17:30
    private String tanggalWaktu;

    // Username in-game milik buyer
    private String usernameIngame;

    // ID user / UID in-game milik buyer
    private String idUser;

    // Nama game atau aplikasi yang di-top-up
    private String game;

    // Metode pembayaran yang dipilih, contoh: OVO, GoPay, DANA
    private String metodePembayaran;

    // Total harga dalam rupiah
    private int totalHarga;

    // Status pemrosesan oleh seller. false = belum diproses (merah), true  = sudah diproses  (hijau)
    
    private boolean sudahDiproses;

    // Username buyer yang membuat transaksi ini
    private String usernameBuyer;

    // --------------------------------------------------------------- konstruktor

    // Konstruktor lengkap — ID dan tanggal digenerate otomatis.
    
    public Transaksi(
            String usernameIngame,
            String idUser,
            String game,
            String metodePembayaran,
            int totalHarga,
            String usernameBuyer
    ) {
        this.idTransaksi      = generateID();
        this.tanggalWaktu     = generateTanggalWaktu();
        this.usernameIngame   = usernameIngame;
        this.idUser           = idUser;
        this.game             = game;
        this.metodePembayaran = metodePembayaran;
        this.totalHarga       = totalHarga;
        this.usernameBuyer    = usernameBuyer;
        this.sudahDiproses    = false; // default: belum diproses
    }

    // Konstruktor penuh — untuk load dari file / restore data.
    
    public Transaksi(
            String idTransaksi,
            String tanggalWaktu,
            String usernameIngame,
            String idUser,
            String game,
            String metodePembayaran,
            int totalHarga,
            boolean sudahDiproses,
            String usernameBuyer
    ) {
        this.idTransaksi      = idTransaksi;
        this.tanggalWaktu     = tanggalWaktu;
        this.usernameIngame   = usernameIngame;
        this.idUser           = idUser;
        this.game             = game;
        this.metodePembayaran = metodePembayaran;
        this.totalHarga       = totalHarga;
        this.sudahDiproses    = sudahDiproses;
        this.usernameBuyer    = usernameBuyer;
    }

    // generator

    // Generate ID transaksi unik berdasarkan waktu saat ini. Format: #INV-YYYYMMDD-HHmmss
    
    private String generateID() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return "#INV-" + LocalDateTime.now().format(fmt);
    }

    // Generate string tanggal & waktu untuk ditampilkan. Format: dd-MM-yyyy · HH:mm
    
    private String generateTanggalWaktu() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy · HH:mm");
        return LocalDateTime.now().format(fmt);
    }

    // getters

    public String getIDTransaksi() {
        return idTransaksi;
    }

    public String getTanggalWaktu() {
        return tanggalWaktu;
    }

    public String getUsernameIngame() {
        return usernameIngame;
    }

    public String getIDUser() {
        return idUser;
    }

    public String getGame() {
        return game;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public boolean isSudahDiproses() {
        return sudahDiproses;
    }

    public String getUsernameBuyer() {
        return usernameBuyer;
    }

    // setters

    public void setIDTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public void setTanggalWaktu(String tanggalWaktu) {
        this.tanggalWaktu = tanggalWaktu;
    }

    public void setUsernameIngame(String usernameIngame) {
        this.usernameIngame = usernameIngame;
    }

    public void setIDUser(String idUser) {
        this.idUser = idUser;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }

    // Dipanggil seller saat mengkonfirmasi top-up. true  → transaksi berubah jadi hijau (sudah diproses), false → transaksi kembali ke merah  (belum diproses)
    
    public void setSudahDiproses(boolean sudahDiproses) {
        this.sudahDiproses = sudahDiproses;
    }

    public void setUsernameBuyer(String usernameBuyer) {
        this.usernameBuyer = usernameBuyer;
    }

    // ---------------------------------------------------------------- utilitas

    // Total harga dalam format rupiah yang mudah dibaca. Contoh: 100000 → "Rp 100.000"

    public String getTotalHargaFormatted() {
        return "Rp " + String.format("%,d", totalHarga).replace(',', '.');
    }

    // Nilai kredit yang akan ditambahkan ke seller setelah konfirmasi. 1 kredit per Rp 50.000.

    public int getKreditDiperoleh() {
        return totalHarga / 50000;
    }

    @Override
    public String toString() {
        return "Transaksi{" +
                "id='"              + idTransaksi      + '\'' +
                ", waktu='"         + tanggalWaktu      + '\'' +
                ", usernameIngame='"+ usernameIngame    + '\'' +
                ", idUser='"        + idUser            + '\'' +
                ", game='"          + game              + '\'' +
                ", metode='"        + metodePembayaran  + '\'' +
                ", total="          + totalHarga        +
                ", diproses="       + sudahDiproses     +
                ", buyer='"         + usernameBuyer     + '\'' +
                '}';
    }
}