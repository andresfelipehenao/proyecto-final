/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Usuario
 */
public class Dado {
   
    public Icon dado;
    public int number;
    private Random randomNo = new Random();
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
   
   
    public Icon getDado() {

        return dado;
    }

    public Dado() {

        playerPassNumber();
    }

    public Icon playerPassNumber() {
        String str;
        number = randomNo.nextInt(7);

        str = Integer.toString(number);
        dado = new ImageIcon("images/Dado/" + str + ".jpg");

        return dado;

    }
}
