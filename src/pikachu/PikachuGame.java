/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pikachu;

/**
 *
 * @author HienNhu
 */
public class PikachuGame {
    BackgroundImageJFrame background;
    
    public PikachuGame(){
        background = new BackgroundImageJFrame();
        background.setVisible(true);
    }
    
    public static void main(String[] args) {
        new PikachuGame();
    }
}
