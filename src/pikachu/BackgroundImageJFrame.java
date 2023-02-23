/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pikachu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 *
 * @author HienNhu
 */
public class BackgroundImageJFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private int Height = 600;
    private int Width = 750;

    PikachuFrame mainFrame;
    JButton btnNewGame;
    JButton btnHelp;
    JButton btnExit;
    JLabel ImgBackground;
    JPanel PikachuPanel;
    MyHelp Help;

    public BackgroundImageJFrame() {
        this.setTitle("Game Pikachu");
        this.setSize(Width, Height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL iconURL = this.getClass().getResource("/Icon/nen1.jpg");
        this.setIconImage(new ImageIcon(iconURL).getImage());
        Image image = new ImageIcon(getClass().getResource(
                "/Icon/nen6.jpg")).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(Width, Height,
                Image.SCALE_SMOOTH));
        this.setLayout(new BorderLayout());
        ImgBackground = new JLabel(icon);
        this.add(ImgBackground);
        JPanel button = new JPanel(new GridLayout(3, 1, 5, 5));
        button.add(btnNewGame = CreateButton("Start Game"));
        button.add(btnHelp = CreateButton("Help"));
        button.add(btnExit = CreateButton("Exit Game"));
        ImgBackground.setLayout(new FlowLayout(5, 300, 260));
        ImgBackground.add(button, BorderLayout.CENTER);

        this.setVisible(true);

    }

    private JButton CreateButton(String ButtonName) {
        JButton btn = new JButton(ButtonName);
        btn.setFont(new Font("Tahoma", Font.BOLD, 25));
        btn.setBackground(java.awt.Color.YELLOW);
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNewGame) {
            dispose();
            mainFrame = new PikachuFrame();
        }
        if (e.getSource() == btnHelp) {
            Help = new MyHelp();
        }

        if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

}
