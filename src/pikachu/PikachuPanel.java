/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pikachu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author HienNhu
 */
public class PikachuPanel extends JPanel implements ActionListener, Runnable {

    private static final long serialVersionUID = 1L;
    private int row = 10;
    private int col = 10;
    private JButton btnNewGame;
    private JButton btnExit;
    private ButtonEvent btnEvent;
    private JPanel CenterPanel;
    private JPanel northPanel;
    private Color BackgroundColor = Color.YELLOW;
    Controller controller;
    PikachuFrame frame;

    public JLabel Score;
    private int maxTime = 300;
    public int Time = maxTime;
    private JProgressBar pgTime;
    private JPanel mainPanel;

    public PikachuPanel() {
        super();
        mainPanel = CreateMainPanel();
        add(mainPanel);
        MyTimeCount timeCount = new MyTimeCount();
        setBackground(BackgroundColor);
        timeCount.start();
        new Thread(this).start();
//        this.setVisible(true);
    }

    private JPanel CreateMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(CreateCenterPanel(), BorderLayout.CENTER);
        panel.add(CreateNorthPanel(), BorderLayout.NORTH);
        panel.add(CreateSouthPanel(), BorderLayout.SOUTH);
        return panel;

    }

    //Create Center Panel
    private JPanel CreateCenterPanel() {
        btnEvent = new ButtonEvent(this, this.frame, this.row, this.col);
        CenterPanel = new JPanel(new GridBagLayout());
        CenterPanel.add(btnEvent);
        return CenterPanel;
    }

    //Create South Panel
    private JPanel CreateSouthPanel() {
        JPanel panelExit = new JPanel(new FlowLayout());
        panelExit.add(btnExit = createButton("Exit Game"));
        panelExit.setBackground(BackgroundColor);
        return panelExit;
    }

    //Create North Panel
    private JPanel CreateNorthPanel() {
        Score = new JLabel("0");
        pgTime = new JProgressBar(0, 100);
        pgTime.setValue(100);

        // create panel container score and time
        JPanel panelLeft = new JPanel(new GridLayout(2, 1, 5, 5));
        panelLeft.add(new JLabel("Score:"));
        panelLeft.add(new JLabel("Time:"));
        panelLeft.setBackground(BackgroundColor);

        JPanel panelCenter = new JPanel(new GridLayout(2, 1, 5, 5));
        panelCenter.add(Score);
        panelCenter.add(pgTime);
        panelCenter.setBackground(BackgroundColor);

        JPanel panelScoreAndTime = new JPanel(new BorderLayout(5, 0));
        panelScoreAndTime.add(panelLeft, BorderLayout.WEST);
        panelScoreAndTime.add(panelCenter, BorderLayout.CENTER);
        panelScoreAndTime.setBackground(Color.yellow);

        // create panel container panelScoreAndTime and button new game
        northPanel = new JPanel(new BorderLayout(10, 10));
        northPanel.setBorder(new EmptyBorder(10, 3, 5, 3));
        northPanel.setBackground(BackgroundColor);
        northPanel.add(panelScoreAndTime, BorderLayout.CENTER);
        northPanel.add(btnNewGame = createButton("New Game"), BorderLayout.PAGE_END);
        return northPanel;
    }

    //Create Button
    private JButton createButton(String ButtonName) {
        JButton btn = new JButton(ButtonName);
        btn.addActionListener(this);
        return btn;
    }

    //New Game
    public void NewGame() {
        Time = maxTime;
        CenterPanel.removeAll();
        mainPanel.add(CreateCenterPanel(), BorderLayout.CENTER);
        mainPanel.validate();
        mainPanel.setVisible(true);
        Score.setText("0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNewGame) {
            showDialogNewGame("Your game hasn't done. Do you want to create a new game?", "Warning");
        }
        if (e.getSource() == btnExit) {
            showDialogExitGame("Your game hasn't done. Do you want to create  exit game?", "Warning");
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            pgTime.setValue((int) ((double) Time / maxTime * 100));
        }
    }

    public void setScore(JLabel Score) {
        this.Score = Score;
    }

    public void setTime(int Time) {
        this.Time = Time;
    }

    public JLabel getScore() {
        return Score;
    }

    public int getTime() {
        return Time;
    }

    public JProgressBar getPgTime() {
        return pgTime;
    }

    public void setPgTime(JProgressBar pgTime) {
        this.pgTime = pgTime;
    }

    public void showDialogNewGame(String message, String title) {
        int select = JOptionPane.showOptionDialog(null, message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                null, null);
        if (select == 0) {
            this.NewGame();
        } else {

        }
    }

    public void showDialogExitGame(String message, String title) {
        int select = JOptionPane.showOptionDialog(null, message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                null, null);
        if (select == 0) {
            System.exit(0);
        } else {

        }
    }

    public void showDialogWinnerGame_Lose(String message, String title) {
        int select = JOptionPane.showOptionDialog(null, message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                null, null);
        if (select == 0) {
            NewGame();
        } else {
            System.exit(0);
        }
    }

    public class MyTimeCount extends Thread {

        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setTime(Time - 1);
                if (Time == 0) {
                    showDialogWinnerGame_Lose("Full Time\n Do you want play a game?", "Lose!");
                }
            }
        }

    }

}
