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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataUser cekUser = new DataUser("", "", 0, 0);
        DataTransaksi cekTrans = new DataTransaksi("", "", "", "", "", "", "", 0, true, "", "");
        
        // TODO code application logic here
        System.out.println("Halo");
        cekUser.loadSemuaUser(dataUser);
        cekTrans.loadSemuaTransaksi(dataTransaksi);
        
        new Login();
    }
}
