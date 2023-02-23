/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pikachu;

import javax.swing.JFrame;

import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 *
 * @author HienNhu
 */
public class PikachuFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    PikachuPanel mainPanel;
    ButtonEvent btnEvent;
    Controller controller;

    private int row = 10;
    private int col = 10;
    private int Height = 750;
    private int Width = 850;

    public PikachuFrame() {
        this.setTitle("Game Pikachu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(Width, Height);
        this.setLocationRelativeTo(null);

        URL iconURL = this.getClass().getResource("/Icon/nen1.jpg");
        this.setIconImage(new ImageIcon(iconURL).getImage());
        this.Init();
//        this.setVisible(true);
    }

    private void Init() {
        mainPanel = new PikachuPanel();

        this.add(mainPanel);
        this.setVisible(true);
//           mainPanel.setVisible(true);
    }

}
