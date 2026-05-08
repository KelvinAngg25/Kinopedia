/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.Sudoku;

/**
 *
 * @author William
 */
public class Logic implements Runnable{
    private int menit,detik;
    boolean pause;
    private View view;

    public Logic(int menit, int detik, boolean pause,View view) {
        this.menit = menit;
        this.detik = detik;
        this.pause = pause;
        this.view = view;
    }
    
    public void timer(){
        if(detik == 0){
            menit-=1;
            detik =59;
        }else{
            detik-=1;
        }
        view.updateTimer(menit,detik);
    }
    
    public static int[][] generatePuzzle() {
        return new int[][] {
            {5, 2, 3, 0, 0, 0, 6, 8, 7},
            {0, 0, 0, 0, 4, 0, 0, 0, 9},
            {1, 0, 0, 0, 0, 0, 3, 0, 0},
            {4, 0, 0, 5, 0, 0, 8, 0, 0},
            {0, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 9, 0, 6, 0, 0, 0, 1},
            {0, 0, 0, 7, 0, 0, 0, 0, 2},
            {7, 4, 0, 0, 8, 0, 0, 0, 0},
            {8, 3, 1, 0, 0, 0, 6, 7, 0}
        };
        
    }

    @Override
    public void run() {
        while (!pause){
            try{
                Thread.sleep(1000);
                timer();
            }catch(InterruptedException e){

            }
        }
    }

    
    
    
}
