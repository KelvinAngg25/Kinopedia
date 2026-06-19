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

public class FlappyWildReward extends GameReward {
    public static final int BONUS_MENANG = 20;
    
    private boolean menang;
    
    public FlappyWildReward(int score, boolean menang) {
        super(score);
        this.menang = menang;
    }
    
    // Hitung Koin
    @Override
    public int hitungKoin() {
        if (menang) {
            return getScore() + BONUS_MENANG;
        } else {
            return getScore();
        }
    }

    @Override
    public String namaGame() {
        return "Flappy Wild";
    }
    
    public boolean isMenang() {
        return menang;
    }
}
