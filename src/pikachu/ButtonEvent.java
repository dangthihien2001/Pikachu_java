/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pikachu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author HienNhu
 */
public class ButtonEvent extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private int row;
    private int col;
    private int bound = 2;
    private int size = 50;
    private int Score = 0;
    private int item;
    private Point p1 = null;
    private Point p2 = null;
    private Color backgroudColor = Color.yellow;
    private JButton[][] button;
    private Controller controller;
    private MyLine myline;
    PikachuFrame frame;
    PikachuPanel panel;

    public ButtonEvent(PikachuPanel panel, PikachuFrame frame, int row, int col) {
        super();
        this.panel = panel;
        this.frame = frame;
        this.row = row + 2;
        this.col = col + 2;
        this.item = row * col;
        setLayout(new GridLayout(row, col, bound, bound));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension((size + bound) * col, (size + bound)
                * row));
        this.setBackground(backgroudColor);
        setAlignmentY(JPanel.CENTER_ALIGNMENT);

        NewGame();
    }

    private Icon getIcon(int index) {
        int width = 48, height = 48;
        Image image = new ImageIcon(getClass().getResource(
                "/Icon/" + index + ".png")).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(width, height,
                Image.SCALE_SMOOTH));
        return icon;

    }

    public void NewGame() {
        controller = new Controller(this.frame, this.row, this.col);
        this.ArrayButton();
    }

    private JButton CreateButton(String action) {
        JButton button = new JButton();
        button.setActionCommand(action);
        button.setBorder(null);
        button.addActionListener(this);
        return button;
    }

    private void ArrayButton() {
        button = new JButton[row][col];

        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                button[i][j] = CreateButton(i + "," + j);
                Icon icon = getIcon(controller.getMatrix()[i][j]);
                button[i][j].setIcon(icon);
                this.add(button[i][j]);
            }
        }
    }

    public void Delete(Point p1, Point p2) {
        System.out.println("Delete!");
        SetDisable(button[p1.getX()][p1.getY()]);
        SetDisable(button[p2.getX()][p2.getY()]);
    }

    private void SetDisable(JButton button) {
        button.setIcon(null);
        button.setBackground(backgroudColor);
        button.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String btnIndex = ae.getActionCommand();
        int index1 = btnIndex.lastIndexOf(','); 
        int x = Integer.parseInt(btnIndex.substring(0, index1)); //getX
        int y = Integer.parseInt(btnIndex.substring(index1 + 1, btnIndex.length()));

        if (p1 == null) {
            p1 = new Point(x, y);
            button[p1.getX()][p1.getY()].setBorder(new LineBorder(Color.red));
        } else {
            p2 = new Point(x, y);
            System.out.println(p1 + "->" + p2);
            myline = controller.CheckTwoPoint(p1, p2);
            if (myline != null) {
                System.out.println("Line!=null");
                controller.getMatrix()[p1.getX()][p1.getY()] = 0;
                controller.getMatrix()[p2.getX()][p2.getY()] = 0;
                controller.ShowMatrix();
                Delete(p1, p2);
                myline = null;
                Score += 10;
                item -= 2;
                panel.Time++;
                panel.Score.setText(Score + "");
            }
            button[p1.getX()][p1.getY()].setBorder(null);
            p1 = null;
            p2 = null;
            System.out.println("done!");
            if (item == 0) {
                panel.showDialogWinnerGame_Lose("You are winer!\nDo you want play again?", "Win");

            }

        }

    }
}
