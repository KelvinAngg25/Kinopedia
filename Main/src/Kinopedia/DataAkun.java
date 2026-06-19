/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia;

/**
 *
 * @author Victus
 */
public class DataAkun {
    String idAkun;
    String namaAkun;
    String jenisGame;

    public DataAkun(String idAkun, String namaAkun, String jenisGame) {
        this.idAkun = idAkun;
        this.namaAkun = namaAkun;
        this.jenisGame = jenisGame;
    }

    public String getIdAkun() {
        return idAkun;
    }

    public void setIdAkun(String idAkun) {
        this.idAkun = idAkun;
    }

    public String getNamaAkun() {
        return namaAkun;
    }

    public void setNamaAkun(String namaAkun) {
        this.namaAkun = namaAkun;
    }

    public String getJenisGame() {
        return jenisGame;
    }

    public void setJenisGame(String jenisGame) {
        this.jenisGame = jenisGame;
    }
    
    
}
