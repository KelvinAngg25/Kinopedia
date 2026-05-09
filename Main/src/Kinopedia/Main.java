package Kinopedia;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William
 */
import javax.swing.SwingUtilities;
import Kinopedia.model.Buyer;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Buyer().setVisible(true));
    }
}
