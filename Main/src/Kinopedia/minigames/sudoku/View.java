/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kinopedia.minigames.sudoku;

import Kinopedia.DataUser;
import javax.swing.SwingUtilities;


/**
 *
 * @author William.l
 */
public class View{
    String  p ;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Logic c = new Logic();
            c.menuGame();
            c.setVisible(true);
        });
    }
}
