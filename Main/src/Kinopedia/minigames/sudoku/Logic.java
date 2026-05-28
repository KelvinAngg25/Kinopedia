/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.sudoku;

/**
 *
 * @author William
 */

import Kinopedia.DataUser;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
public class Logic extends JFrame implements Runnable{
    String p3;
    private int menit,detik;
    boolean pause;
    private View view;
    private JLabel timerLabel;
    private JLabel livesLabel;
    private boolean gameRunning = true;
    private JButton angkadiPilih = null;
    private int live;
    private JButton angka,tombolPause;
    private int p = 0;
    private int pilihAngka = 0;
    private int cellKosong = 0;
    private int dapetKoin = 0;
    Random rand = new Random();
    ArrayList<JButton> daftarAngka = new ArrayList<>(); 
    ArrayList<JButton> daftarCell = new ArrayList<>();
    DataUser user = Kinopedia.Session.getInstance().getCurrentUser();
    public Logic() {
        this.menit = 5;
        this.detik = 0;
        this.pause = false;
        this.live = 3;
    }
    
    
    public void live(){
        SwingUtilities.invokeLater(() -> {
            livesLabel.setText("Lives : "+live);
        });
        if(live ==0){
            pause=true;
            user.setKoin(user.getKoin()+dapetKoin);
            gameOver();
        }
        
    }
    
    public void timer(){
        if(detik == 0){
            if(menit==0){
                pause =true;
                user.setKoin(user.getKoin()+dapetKoin);
                gameOver();
            }else {
                menit-=1;
                detik =59;
            }
        }else{
            detik-=1;
        }
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(String.format("%02d : %02d", menit, detik));
        });
    }
    
    private int[][] Puzzle1() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{9, 0, 6, 0, 1, 0, 0, 7, 0};
        puzzle[1] = new int[]{0, 5, 0, 7, 0, 3, 4, 0, 9};
        puzzle[2] = new int[]{3, 0, 0, 9, 0, 8, 0, 6, 0};
        puzzle[3] = new int[]{0, 1, 0, 0, 7, 2, 0, 8, 0};
        puzzle[4] = new int[]{6, 0, 4, 0, 3, 0, 2, 0, 7};
        puzzle[5] = new int[]{0, 7, 0, 5, 0, 9, 0, 1, 0};
        puzzle[6] = new int[]{0, 6, 0, 1, 0, 0, 8, 0, 2};
        puzzle[7] = new int[]{2, 0, 1, 0, 9, 6, 0, 4, 0};
        puzzle[8] = new int[]{0, 3, 0, 0, 8, 0, 1, 0, 6};
        return puzzle;
    }
    
    private int [][] jawabanPuzzle1(){
        int [][] puzzle = new int [9][9];
        puzzle[0] = new int[]{9,2,6,4,1,5,3,7,8};
        puzzle[1] = new int[]{1,5,8,7,6,3,4,2,9};
        puzzle[2] = new int[]{3,4,7,9,2,8,5,6,1};
        puzzle[3] = new int[]{5,1,3,6,7,2,9,8,4};
        puzzle[4] = new int[]{6,9,4,8,3,1,2,5,7};
        puzzle[5] = new int[]{8,7,2,5,4,9,6,1,3};
        puzzle[6] = new int[]{4,6,9,1,5,7,8,3,2};
        puzzle[7] = new int[]{2,8,1,3,9,6,7,4,5};
        puzzle[8] = new int[]{7,3,5,2,8,4,1,9,6};
        return puzzle;
    }
    
    private int[][] Puzzle2() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{7, 0, 0, 5, 0, 4, 0, 6, 0};
        puzzle[1] = new int[]{0, 4, 9, 0, 6, 0, 1, 0, 5};
        puzzle[2] = new int[]{6, 0, 0, 8, 0, 9, 0, 7, 0};
        puzzle[3] = new int[]{3, 0, 2, 0, 5, 0, 4, 0, 6};
        puzzle[4] = new int[]{0, 1, 0, 4, 0, 2, 0, 5, 0};
        puzzle[5] = new int[]{4, 0, 5, 0, 9, 0, 2, 0, 8};
        puzzle[6] = new int[]{0, 9, 0, 2, 0, 6, 0, 0, 1};
        puzzle[7] = new int[]{5, 0, 7, 0, 3, 0, 6, 4, 0};
        puzzle[8] = new int[]{0, 6, 0, 9, 0, 5, 0, 2, 0};
        return puzzle;
    }
    
    private int [][] jawabanPuzzle2(){
        int [][] puzzle = new int [9][9];
        puzzle[0] = new int[]{7,3,8,5,1,4,9,6,2};
        puzzle[1] = new int[]{2,4,9,3,6,7,1,8,5};
        puzzle[2] = new int[]{6,5,1,8,2,9,3,7,4};
        puzzle[3] = new int[]{3,8,2,7,5,1,4,9,6};
        puzzle[4] = new int[]{9,1,6,4,8,2,7,5,3};
        puzzle[5] = new int[]{4,7,5,6,9,3,2,1,8};
        puzzle[6] = new int[]{8,9,4,2,7,6,5,3,1};
        puzzle[7] = new int[]{5,2,7,1,3,8,6,4,9};
        puzzle[8] = new int[]{1,6,3,9,4,5,8,2,7};
        return puzzle;
    }
    
    private int[][] Puzzle3() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{6, 0, 5, 0, 3, 0, 4, 0, 9};
        puzzle[1] = new int[]{0, 3, 0, 5, 0, 8, 0, 6, 0};
        puzzle[2] = new int[]{2, 0, 9, 0, 6, 0, 5, 0, 8};
        puzzle[3] = new int[]{8, 0, 0, 3, 0, 9, 0, 2, 0};
        puzzle[4] = new int[]{0, 2, 6, 0, 7, 0, 3, 4, 0};
        puzzle[5] = new int[]{0, 7, 0, 2, 0, 6, 0, 0, 1};
        puzzle[6] = new int[]{1, 0, 7, 0, 8, 0, 0, 5, 0};
        puzzle[7] = new int[]{0, 4, 0, 9, 0, 5, 6, 0, 7};
        puzzle[8] = new int[]{5, 0, 8, 0, 2, 0, 9, 0, 0};
        return puzzle;
    }
    
    private int [][] jawabanPuzzle3(){
        int [][] puzzle = new int [9][9];
        puzzle[0] = new int[]{6,8,5,1,3,2,4,7,9};
        puzzle[1] = new int[]{7,3,4,5,9,8,1,6,2};
        puzzle[2] = new int[]{2,1,9,7,6,4,5,3,8};
        puzzle[3] = new int[]{8,5,1,3,4,9,7,2,6};
        puzzle[4] = new int[]{9,2,6,8,7,1,3,4,5};
        puzzle[5] = new int[]{4,7,3,2,5,6,8,9,1};
        puzzle[6] = new int[]{1,9,7,6,8,3,2,5,4};
        puzzle[7] = new int[]{3,4,2,9,1,5,6,8,7};
        puzzle[8] = new int[]{5,6,8,4,2,7,9,1,3};
        return puzzle;
    }
    
    private int[][] Puzzle4() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{3, 0, 1, 0, 7, 0, 9, 0, 4};
        puzzle[1] = new int[]{0, 8, 0, 5, 0, 9, 0, 1, 0};
        puzzle[2] = new int[]{4, 0, 9, 0, 3, 0, 2, 0, 7};
        puzzle[3] = new int[]{9, 0, 6, 0, 5, 0, 8, 0, 2};
        puzzle[4] = new int[]{0, 2, 0, 3, 0, 6, 0, 4, 0};
        puzzle[5] = new int[]{5, 0, 3, 0, 8, 0, 1, 0, 6};
        puzzle[6] = new int[]{0, 3, 0, 9, 0, 8, 0, 6, 0};
        puzzle[7] = new int[]{8, 0, 5, 0, 6, 0, 4, 2, 0};
        puzzle[8] = new int[]{0, 9, 0, 4, 0, 5, 0, 0, 8};
        return puzzle;
    }
    
    private int[][] jawabanPuzzle4() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{3, 5, 1, 6, 7, 2, 9, 8, 4};
        puzzle[1] = new int[]{2, 8, 7, 5, 4, 9, 6, 1, 3};
        puzzle[2] = new int[]{4, 6, 9, 8, 3, 1, 2, 5, 7};
        puzzle[3] = new int[]{9, 4, 6, 1, 5, 7, 8, 3, 2};
        puzzle[4] = new int[]{1, 2, 8, 3, 9, 6, 7, 4, 5};
        puzzle[5] = new int[]{5, 7, 3, 2, 8, 4, 1, 9, 6};
        puzzle[6] = new int[]{7, 3, 4, 9, 2, 8, 5, 6, 1};
        puzzle[7] = new int[]{8, 1, 5, 7, 6, 3, 4, 2, 9};
        puzzle[8] = new int[]{6, 9, 2, 4, 1, 5, 3, 7, 8};
        return puzzle;
    }
    
    private int[][] Puzzle5() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{4, 0, 7, 0, 1, 0, 6, 0, 5};
        puzzle[1] = new int[]{0, 2, 0, 9, 0, 8, 0, 3, 0};
        puzzle[2] = new int[]{5, 0, 3, 0, 7, 0, 2, 0, 9};
        puzzle[3] = new int[]{0, 6, 0, 4, 0, 9, 0, 5, 0};
        puzzle[4] = new int[]{3, 0, 4, 0, 8, 0, 9, 0, 6};
        puzzle[5] = new int[]{0, 1, 0, 5, 0, 6, 0, 7, 0};
        puzzle[6] = new int[]{9, 0, 1, 0, 4, 0, 5, 0, 8};
        puzzle[7] = new int[]{0, 7, 0, 1, 0, 5, 0, 4, 0};
        puzzle[8] = new int[]{2, 0, 5, 0, 6, 0, 1, 0, 7};
        return puzzle;
    }
    
    private int[][] jawabanPuzzle5() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{4, 9, 7, 3, 1, 2, 6, 8, 5};
        puzzle[1] = new int[]{1, 2, 6, 9, 5, 8, 7, 3, 4};
        puzzle[2] = new int[]{5, 8, 3, 6, 7, 4, 2, 1, 9};
        puzzle[3] = new int[]{7, 6, 2, 4, 3, 9, 8, 5, 1};
        puzzle[4] = new int[]{3, 5, 4, 7, 8, 1, 9, 2, 6};
        puzzle[5] = new int[]{8, 1, 9, 5, 2, 6, 4, 7, 3};
        puzzle[6] = new int[]{9, 3, 1, 2, 4, 7, 5, 6, 8};
        puzzle[7] = new int[]{6, 7, 8, 1, 9, 5, 3, 4, 2};
        puzzle[8] = new int[]{2, 4, 5, 8, 6, 3, 1, 9, 7};
        return puzzle;
    }
    
    private int[][] Puzzle6() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{6, 0, 2, 0, 1, 0, 8, 0, 9};
        puzzle[1] = new int[]{0, 3, 0, 6, 0, 4, 0, 7, 0};
        puzzle[2] = new int[]{5, 0, 9, 0, 7, 0, 1, 0, 6};
        puzzle[3] = new int[]{0, 9, 0, 2, 0, 1, 0, 5, 0};
        puzzle[4] = new int[]{1, 0, 7, 0, 6, 0, 3, 0, 8};
        puzzle[5] = new int[]{0, 8, 0, 7, 0, 5, 0, 6, 0};
        puzzle[6] = new int[]{4, 0, 5, 0, 2, 0, 7, 0, 3};
        puzzle[7] = new int[]{0, 6, 0, 1, 0, 8, 0, 9, 0};
        puzzle[8] = new int[]{9, 0, 8, 0, 4, 0, 6, 0, 5};
        return puzzle;
    }
    
    private int[][] jawabanPuzzle6() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{6, 7, 2, 5, 1, 3, 8, 4, 9};
        puzzle[1] = new int[]{8, 3, 1, 6, 9, 4, 5, 7, 2};
        puzzle[2] = new int[]{5, 4, 9, 8, 7, 2, 1, 3, 6};
        puzzle[3] = new int[]{3, 9, 6, 2, 8, 1, 4, 5, 7};
        puzzle[4] = new int[]{1, 5, 7, 4, 6, 9, 3, 2, 8};
        puzzle[5] = new int[]{2, 8, 4, 7, 3, 5, 9, 6, 1};
        puzzle[6] = new int[]{4, 1, 5, 9, 2, 6, 7, 8, 3};
        puzzle[7] = new int[]{7, 6, 3, 1, 5, 8, 2, 9, 4};
        puzzle[8] = new int[]{9, 2, 8, 3, 4, 7, 6, 1, 5};
        return puzzle;
    }
    
    private int[][] Puzzle7() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{3, 0, 5, 0, 1, 0, 6, 0, 9};
        puzzle[1] = new int[]{0, 2, 0, 3, 0, 4, 0, 5, 0};
        puzzle[2] = new int[]{8, 0, 1, 0, 6, 0, 3, 0, 4};
        puzzle[3] = new int[]{0, 7, 0, 1, 0, 3, 0, 8, 0};
        puzzle[4] = new int[]{1, 0, 2, 0, 8, 0, 4, 0, 7};
        puzzle[5] = new int[]{0, 3, 0, 7, 0, 6, 0, 1, 0};
        puzzle[6] = new int[]{9, 0, 3, 0, 7, 0, 8, 0, 5};
        puzzle[7] = new int[]{0, 8, 0, 9, 0, 1, 0, 4, 0};
        puzzle[8] = new int[]{2, 0, 4, 0, 3, 0, 7, 0, 1};
        return puzzle;
    }
    
    
    private int[][] jawabanPuzzle7() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{3, 4, 5, 8, 1, 7, 6, 2, 9};
        puzzle[1] = new int[]{7, 2, 6, 3, 9, 4, 1, 5, 8};
        puzzle[2] = new int[]{8, 9, 1, 2, 6, 5, 3, 7, 4};
        puzzle[3] = new int[]{4, 7, 9, 1, 2, 3, 5, 8, 6};
        puzzle[4] = new int[]{1, 6, 2, 5, 8, 9, 4, 3, 7};
        puzzle[5] = new int[]{5, 3, 8, 7, 4, 6, 9, 1, 2};
        puzzle[6] = new int[]{9, 1, 3, 4, 7, 2, 8, 6, 5};
        puzzle[7] = new int[]{6, 8, 7, 9, 5, 1, 2, 4, 3};
        puzzle[8] = new int[]{2, 5, 4, 6, 3, 8, 7, 9, 1};
        return puzzle;
    }
    
    private int[][] Puzzle8() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{9, 0, 4, 0, 1, 0, 7, 0, 6};
        puzzle[1] = new int[]{0, 1, 0, 2, 0, 8, 0, 9, 0};
        puzzle[2] = new int[]{2, 0, 7, 0, 9, 0, 3, 0, 8};
        puzzle[3] = new int[]{0, 3, 0, 9, 0, 4, 0, 7, 0};
        puzzle[4] = new int[]{7, 0, 5, 0, 8, 0, 9, 0, 3};
        puzzle[5] = new int[]{0, 9, 0, 5, 0, 7, 0, 4, 0};
        puzzle[6] = new int[]{3, 0, 8, 0, 2, 0, 1, 0, 4};
        puzzle[7] = new int[]{0, 2, 0, 8, 0, 1, 0, 3, 0};
        puzzle[8] = new int[]{5, 0, 1, 0, 4, 0, 2, 0, 9};
        return puzzle;
    }
 
    private int[][] jawabanPuzzle8() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{9, 8, 4, 3, 1, 5, 7, 2, 6};
        puzzle[1] = new int[]{6, 1, 3, 2, 7, 8, 4, 9, 5};
        puzzle[2] = new int[]{2, 5, 7, 4, 9, 6, 3, 1, 8};
        puzzle[3] = new int[]{8, 3, 2, 9, 6, 4, 5, 7, 1};
        puzzle[4] = new int[]{7, 4, 5, 1, 8, 2, 9, 6, 3};
        puzzle[5] = new int[]{1, 9, 6, 5, 3, 7, 8, 4, 2};
        puzzle[6] = new int[]{3, 7, 8, 6, 2, 9, 1, 5, 4};
        puzzle[7] = new int[]{4, 2, 9, 8, 5, 1, 6, 3, 7};
        puzzle[8] = new int[]{5, 6, 1, 7, 4, 3, 2, 8, 9};
        return puzzle;
    }
    
    private int[][] Puzzle9() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{2, 0, 9, 0, 1, 0, 7, 0, 8};
        puzzle[1] = new int[]{0, 8, 0, 7, 0, 3, 0, 4, 0};
        puzzle[2] = new int[]{4, 0, 3, 0, 2, 0, 6, 0, 1};
        puzzle[3] = new int[]{0, 2, 0, 5, 0, 9, 0, 6, 0};
        puzzle[4] = new int[]{9, 0, 6, 0, 3, 0, 5, 0, 7};
        puzzle[5] = new int[]{0, 3, 0, 6, 0, 2, 0, 9, 0};
        puzzle[6] = new int[]{6, 0, 4, 0, 5, 0, 3, 0, 2};
        puzzle[7] = new int[]{0, 1, 0, 3, 0, 6, 0, 7, 0};
        puzzle[8] = new int[]{3, 0, 7, 0, 8, 0, 9, 0, 6};
        return puzzle;
    }
    
    private int[][] jawabanPuzzle9() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{2, 6, 9, 4, 1, 5, 7, 3, 8};
        puzzle[1] = new int[]{5, 8, 1, 7, 6, 3, 2, 4, 9};
        puzzle[2] = new int[]{4, 7, 3, 9, 2, 8, 6, 5, 1};
        puzzle[3] = new int[]{7, 2, 8, 5, 4, 9, 1, 6, 3};
        puzzle[4] = new int[]{9, 4, 6, 8, 3, 1, 5, 2, 7};
        puzzle[5] = new int[]{1, 3, 5, 6, 7, 2, 8, 9, 4};
        puzzle[6] = new int[]{6, 9, 4, 1, 5, 7, 3, 8, 2};
        puzzle[7] = new int[]{8, 1, 2, 3, 9, 6, 4, 7, 5};
        puzzle[8] = new int[]{3, 5, 7, 2, 8, 4, 9, 1, 6};
        return puzzle;
    }
    
    private int[][] Puzzle10() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{9, 0, 8, 0, 6, 0, 3, 0, 7};
        puzzle[1] = new int[]{0, 6, 0, 4, 0, 9, 0, 5, 0};
        puzzle[2] = new int[]{4, 0, 5, 0, 7, 0, 9, 0, 6};
        puzzle[3] = new int[]{0, 5, 0, 8, 0, 2, 0, 6, 0};
        puzzle[4] = new int[]{3, 0, 6, 0, 4, 0, 2, 0, 1};
        puzzle[5] = new int[]{0, 8, 0, 1, 0, 6, 0, 3, 0};
        puzzle[6] = new int[]{6, 0, 2, 0, 8, 0, 5, 0, 3};
        puzzle[7] = new int[]{0, 3, 0, 2, 0, 7, 0, 9, 0};
        puzzle[8] = new int[]{5, 0, 9, 0, 1, 0, 8, 0, 2};
        return puzzle;
    }
    
    
    private int[][] jawabanPuzzle10() {
        int[][] puzzle = new int[9][9];
        puzzle[0] = new int[]{9, 2, 8, 5, 6, 1, 3, 4, 7};
        puzzle[1] = new int[]{7, 6, 3, 4, 2, 9, 1, 5, 8};
        puzzle[2] = new int[]{4, 1, 5, 3, 7, 8, 9, 2, 6};
        puzzle[3] = new int[]{1, 5, 7, 8, 3, 2, 4, 6, 9};
        puzzle[4] = new int[]{3, 9, 6, 7, 4, 5, 2, 8, 1};
        puzzle[5] = new int[]{2, 8, 4, 1, 9, 6, 7, 3, 5};
        puzzle[6] = new int[]{6, 7, 2, 9, 8, 4, 5, 1, 3};
        puzzle[7] = new int[]{8, 3, 1, 2, 5, 7, 6, 9, 4};
        puzzle[8] = new int[]{5, 4, 9, 6, 1, 3, 8, 7, 2};
        return puzzle;
    }

    @Override
    public void run() {
        while (!pause){
            timer();
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
            }
        }
    }
    
    
    public boolean cekKredit(){
        if(user.getKredit()>0){
            return true;
        }else{
            return false;
        }
    }
    
    
    
    
    public void cekInputan(boolean cek, JButton cell){
        if(cek==true){
            dapetKoin+=2;
            cell.setText(String.valueOf(pilihAngka));
            cell.setForeground(new Color(0, 0, 180));
            cell.setBackground(Color.GREEN);
            cell.setEnabled(false);
            cellKosong--;
            cekMenang();
        }else{
            cell.setBackground(Color.RED);
            cell.setOpaque(true);
            Thread t = new Thread(new Runnable(){
                @Override
                public void run() {
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){}
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                            cell.setBackground(Color.WHITE);
                        }
                        
                    })
                            ;
                }
                
            });
            t.setDaemon(true);
            t.start();
            
        }
    }
    
    public void cekMenang(){
        if(cellKosong<=0){
            pause = true;
            user.setKoin(user.getKoin()+ dapetKoin + 50);
            gameVictory();
        }
    }
    
    public void game(){
        setSize(470, 844);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        tombolPause = new JButton("| |");
        tombolPause.setFont(new Font("Poppins", Font.BOLD, 30));
        tombolPause.setContentAreaFilled(false);
        tombolPause.setBorderPainted(false);
        tombolPause.setFocusPainted(false);
        tombolPause.setBounds(170,-20,100,100);
        tombolPause.addActionListener(e -> {
            if(p==0){
                p=1;
                pause = true;
                pause();
            }
           
        });
        add(tombolPause);

        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Poppins", Font.BOLD, 40));
        timerLabel.setBounds(10,20,150,50);
        add(timerLabel);

        livesLabel = new JLabel();
        livesLabel.setFont(new Font("Poppins", Font.BOLD, 30));
        livesLabel.setBounds(280,20,150,50);
        
        add(livesLabel);
        if(live<=0){
            pause= true;
            gameOver();
        }else{
            live();
        }
       
        Thread timerThread = new Thread(this);
        timerThread.setDaemon(true);
        timerThread.start();

        
        for (int i = 0;i<9;i++){
            final int valueAngka = i+1;
            String a = ""+(i+1);
            int kanan = i*60+90;
            JButton angka = new JButton(a);
            angka.setFont(new Font("Poppins", Font.BOLD, 40));
            angka.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            angka.setFocusPainted(false);
            angka.setBackground(Color.LIGHT_GRAY);
            
            
            if(i>4 && i!=8){
                kanan = (i%4)*60+60;
                angka.setBounds(kanan,640,50,50);
            }else if(i==8){
                kanan = 4*60+60;
                angka.setBounds(kanan,640,50,50);
            }else{
                angka.setBounds(kanan,570,50,50);
            }
            daftarAngka.add(angka);
            add(angka);
            angka.addActionListener(e ->{
                if(angkadiPilih != null){
                    angkadiPilih.setBackground(Color.LIGHT_GRAY);
                }
                pilihAngka = valueAngka;
                angkadiPilih = angka;
                angka.setBackground(Color.WHITE);
            });
        }
        
        JPanel sudokuPanel = new JPanel(new java.awt.GridLayout(9, 9));
        sudokuPanel.setBounds(30,100,400,400);
        sudokuPanel.setBackground(Color.WHITE);
        sudokuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        int random = rand.nextInt(10+1);
        int[][] puzzleTemp = null;
        int[][] jawabanTemp = null;
        
        if(random ==1){
            puzzleTemp = Puzzle1();
            jawabanTemp = jawabanPuzzle1();
        }else if(random ==2){
            puzzleTemp = Puzzle2();
            jawabanTemp = jawabanPuzzle2();
        }else if(random ==3){
            puzzleTemp = Puzzle3();
            jawabanTemp = jawabanPuzzle3();
        }else if(random ==4){
            puzzleTemp = Puzzle4();
            jawabanTemp = jawabanPuzzle4();
        }else if(random ==5){
            puzzleTemp = Puzzle5();
            jawabanTemp = jawabanPuzzle5();
        }else if(random ==6){
            puzzleTemp = Puzzle6();
            jawabanTemp = jawabanPuzzle6();
        }else if(random ==7){
            puzzleTemp = Puzzle7();
            jawabanTemp = jawabanPuzzle7();
        }else if(random ==8){
            puzzleTemp = Puzzle8();
            jawabanTemp = jawabanPuzzle8();
        }else if(random ==9){
            puzzleTemp = Puzzle9();
            jawabanTemp = jawabanPuzzle9();
        }else if(random ==10){
            puzzleTemp = Puzzle10();
            jawabanTemp = jawabanPuzzle10();
        }
        
        final int[][] puzzle = puzzleTemp;
        final int[][] jawabanPuzzle = jawabanTemp;
        
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                final int row2 = row;
                final int col2 = col;
                JButton cell = new JButton();
                cell.setHorizontalAlignment(JButton.CENTER);
                cell.setFont(new Font("Poppins", Font.BOLD, 20));
                cell.setPreferredSize(new Dimension(55, 55));
                if((row ==2 && col==2) || (row==2 && col ==5) || (row ==5 && col ==2)|| (row==5 && col==5)){
                    cell.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
                }else if(row ==2 || row==5){
                    cell.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK));
                }else if(col==2 || col==5){
                    cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK));
                }else{
                    cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                }
                

                if (puzzle[row][col] != 0) {
                    cell.setText(String.valueOf(puzzle[row][col]));
                    cell.setEnabled(false);
                    cell.setBackground(Color.LIGHT_GRAY);
                    cell.setDisabledIcon(null);
                }else{
                    cellKosong++;
                    cell.setBackground(Color.WHITE);
                    cell.setOpaque(true);
                    cell.addActionListener(e -> {
                        if (pilihAngka != 0) {
                            if(jawabanPuzzle[row2][col2]==pilihAngka){
                                cekInputan(true, cell);
                            }else{
                                cekInputan(false, cell);
                                live -=1;
                                live();
                            }
                        }
                    });
                    daftarCell.add(cell);
                }
                sudokuPanel.add(cell);
            }
        }
        add(sudokuPanel);
        
        
    }
    
    public void gameVictory(){
        SwingUtilities.invokeLater(() -> {
            tombolPause.setEnabled(false);
            JPanel overlay = new JPanel(null);
            overlay.setOpaque(false);
            
            for(int i = 0;i<daftarAngka.size();i++){
                daftarAngka.get(i).setEnabled(false);
            }
            
            for(int i = 0;i<daftarCell.size();i++){
                daftarCell.get(i).setEnabled(false);
            }
            
            JPanel kotak = new JPanel(new GridLayout(3,1,10,10));
            kotak.setBackground(Color.WHITE);
            kotak.setBounds(0, 470, 445, 280);
            
            JLabel resultLabel = new JLabel ("VICTORY!",JLabel.CENTER);
            resultLabel.setFont(new Font("Poppins", Font.BOLD, 60));
            resultLabel.setBounds(0, 10, 360, 70);
            kotak.add(resultLabel);
            
            JButton playAgain = new JButton("PLAY AGAIN");
            playAgain.setFont(new Font("Poppins", Font.BOLD, 40));
            playAgain.setForeground(Color.BLACK);
            playAgain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
            playAgain.setFocusPainted(false);
            playAgain.setContentAreaFilled(false);
            playAgain.setBounds(30, 100, 300, 60);
            
            playAgain.addActionListener(e -> {
                boolean cek = cekKredit();
                if(cek==true){
                    user.setKredit(user.getKredit()-1);
                    dispose();
                    Logic game = new Logic();
                    game.game();
                    game.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(
                        this,
                        "Kredit tidak cukup untuk bermain!\n",
                        "",
                        JOptionPane.WARNING_MESSAGE
                    );
                    dispose();
                    Logic menu = new Logic();
                    menu.menuGame();
                    menu.setVisible(true);
                }
            });
            kotak.add(playAgain);
            
            JButton exit = new JButton("EXIT");
            exit.setFont(new Font("Poppins", Font.BOLD, 50));
            exit.setForeground(Color.BLACK);
            exit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
            exit.setFocusPainted(false);
            exit.setContentAreaFilled(false);
            exit.setBounds(30, 190, 300, 60);
            exit.addActionListener(e -> {
                getContentPane().removeAll();
                menuGame();
                revalidate();
                repaint();
                overlay.setVisible(false);
            });
            kotak.add(exit);
            
            
            overlay.add(kotak);
            
            setGlassPane(overlay);
            overlay.setVisible(true);
        });
    }
    
    public void gameOver(){
        SwingUtilities.invokeLater(() -> {
            tombolPause.setEnabled(false);
            JPanel overlay = new JPanel(null);
            overlay.setOpaque(false);
            
            for(int i = 0;i<daftarAngka.size();i++){
                daftarAngka.get(i).setEnabled(false);
            }
            
            for(int i = 0;i<daftarCell.size();i++){
                daftarCell.get(i).setEnabled(false);
            }
            
            JPanel kotak = new JPanel(new GridLayout(3,1,10,10));
            kotak.setBackground(Color.WHITE);
            kotak.setBounds(0, 470, 445, 280);
            
            JLabel resultLabel = new JLabel ("GAME OVER",JLabel.CENTER);
            resultLabel.setFont(new Font("Poppins", Font.BOLD, 60));
            resultLabel.setBounds(0, 10, 360, 70);
            kotak.add(resultLabel);
            
            JButton playAgain = new JButton("PLAY AGAIN");
            playAgain.setFont(new Font("Poppins", Font.BOLD, 40));
            playAgain.setForeground(Color.BLACK);
            playAgain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
            playAgain.setFocusPainted(false);
            playAgain.setContentAreaFilled(false);
            playAgain.setBounds(30, 100, 300, 60);
            
            playAgain.addActionListener(e -> {
                boolean cek = cekKredit();
                if(cek==true){
                    user.setKredit(user.getKredit()-1);
                    dispose();
                    Logic game = new Logic();
                    game.game();
                    game.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(
                        this,
                        "Kredit tidak cukup untuk bermain!\n",
                        "",
                        JOptionPane.WARNING_MESSAGE
                    );
                    dispose();
                    Logic menu = new Logic();
                    menu.menuGame();
                    menu.setVisible(true);
                    
                }
            });
            kotak.add(playAgain);
            
            JButton exit = new JButton("EXIT");
            exit.setFont(new Font("Poppins", Font.BOLD, 50));
            exit.setForeground(Color.BLACK);
            exit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
            exit.setFocusPainted(false);
            exit.setContentAreaFilled(false);
            exit.setBounds(30, 190, 300, 60);
            exit.addActionListener(e -> {
                getContentPane().removeAll();
                menuGame();
                revalidate();
                repaint();
                overlay.setVisible(false);
            });
            kotak.add(exit);
            
            
            overlay.add(kotak);
            
            setGlassPane(overlay);
            overlay.setVisible(true);
        });
    }
    
    public void pause(){
        SwingUtilities.invokeLater(() -> {
            JPanel overlay = new JPanel(null);
            overlay.setOpaque(false);
            
            for(int i = 0;i<daftarAngka.size();i++){
                daftarAngka.get(i).setEnabled(false);
            }
            
            for(int i = 0;i<daftarCell.size();i++){
                daftarCell.get(i).setEnabled(false);
            }
            
            JPanel kotak = new JPanel(new GridLayout(2,1,10,10));
            kotak.setBackground(Color.WHITE);
            kotak.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK,2),BorderFactory.createEmptyBorder(20, 40, 20, 40)));
            kotak.setBounds(55,272,350,200);
            
            JButton btnContinue = new JButton("CONTINUE");
            btnContinue.setFont(new Font("Poppins", Font.BOLD, 40));
            btnContinue.setBackground(new Color(0, 180, 0));
            btnContinue.setForeground(Color.WHITE);
            btnContinue.setFocusPainted(false);
            btnContinue.addActionListener(e -> {
                
                for(int i = 0;i<daftarAngka.size();i++){
                    daftarAngka.get(i).setEnabled(true);
                }
                for(int i = 0;i<daftarCell.size();i++){
                    daftarCell.get(i).setEnabled(true);
                }
                pause=false;
                setGlassPane(new JPanel());
                getGlassPane().setVisible(false);
                Thread t = new Thread(this);
                t.setDaemon(true);
                t.start();
                p=0;
                
                
                
            });
            kotak.add(btnContinue);
            
            JButton btnExit = new JButton("EXIT");
            btnExit.setFont(new Font("Poppins", Font.BOLD, 40));
            btnExit.setBackground(new Color(180,0, 0));
            btnExit.setForeground(Color.WHITE);
            btnExit.setFocusPainted(false);
            btnExit.addActionListener(e-> {
                getContentPane().removeAll();
                menuGame();
                revalidate();
                repaint();
                overlay.setVisible(false);
            });
            kotak.add(btnExit);
            overlay.add(kotak, BorderLayout.CENTER);
            setGlassPane(overlay);
            overlay.setVisible(true);
        });
    }
    
    
    public void menuGame(){
        setSize(470,844);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel("SUDOKU",JLabel.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 100));
        title.setBorder(BorderFactory.createEmptyBorder(30,0,10,0));
        add(title,BorderLayout.NORTH);
        
        JLabel gambar;
        ImageIcon file = new ImageIcon(getClass().getResource("sudoku.png"));
        Image scale = file.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        gambar = new JLabel(new ImageIcon(scale), JLabel.CENTER);
        add(gambar, BorderLayout.CENTER);
        
        JPanel button = new JPanel(new GridLayout(2, 1, 10, 10));
        button.setBorder(BorderFactory.createEmptyBorder(10, 60, 90, 60));
        
        JButton btnStart = new JButton("START");
        btnStart.setFont(new Font("Poppins", Font.BOLD,60));
        btnStart.setBackground(new Color(0,180,0));
        btnStart.setForeground(Color.BLACK);
        btnStart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
        btnStart.setFocusPainted(false);
        btnStart.addActionListener(e -> {
            boolean cek = cekKredit();
            if(cek==true){   
                dispose();
                user.setKredit(user.getKredit()-1);
                SwingUtilities.invokeLater(() -> {
                    Logic game = new Logic();
                    game.game();
                    game.setVisible(true);
                });
            }else{
                JOptionPane.showMessageDialog(
                    this,
                    "Kredit tidak cukup untuk bermain!\n",
                    "",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        });
        button.add(btnStart,BorderLayout.NORTH);
        
        JButton btnExit = new JButton("EXIT");
        btnExit.setFont(new Font("Poppins",Font.BOLD,60));
        btnExit.setBackground(Color.RED);
        btnExit.setForeground(Color.BLACK);
        btnExit.setFocusPainted(false);
        btnExit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
        btnExit.addActionListener(e ->{
            new Kinopedia.minigames.MainMiniGames().setVisible(true);
            dispose();
        });
        button.add(btnExit, BorderLayout.SOUTH);
        
        add(button, BorderLayout.SOUTH);
    }
}
