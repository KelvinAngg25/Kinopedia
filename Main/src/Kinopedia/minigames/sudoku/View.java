/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.sudoku;

import javax.swing.SwingUtilities;


public class View{
    public static void main(String[] args) {
        Logic c = new Logic(5, 1, false);
        c.menuGame();
        c.setVisible(true);
    }
}
