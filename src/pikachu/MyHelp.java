/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pikachu;

import java.awt.BorderLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author HienNhu
 */
public class MyHelp extends JFrame {

    private static final long serialVersionUID = 1L;
    private int Height = 500;
    private int Width = 600;
    JLabel label;

    public MyHelp() {
        this.setTitle("Help");
        this.setResizable(false);
        this.setSize(Width, Height);
        this.setLocationRelativeTo(null);
        this.add(CreateJLabel());
        this.setVisible(true);
    }

    private JLabel CreateJLabel() {
        URL iconURL = this.getClass().getResource("/Icon/nen1.jpg");
        this.setIconImage(new ImageIcon(iconURL).getImage());
        Image image = new ImageIcon(getClass().getResource(
                "/Icon/myHelp.PNG")).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(Width, Height,
                Image.SCALE_SMOOTH));
        label = new JLabel(icon);
        return label;
    }

}
