package com.gamezone;

import com.gamezone.launcher.Launcher;
import java.awt.Color;
import javax.swing.JFrame;

public class GameApplication {

  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setTitle("GameZone");
    f.setBounds(10, 10, 905, 700);
    f.setResizable(false);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBackground(Color.BLACK);
    f.getContentPane().add(new Launcher(f));
    //f.add(new SnakeGamePanel(f, 500, 2));
    //f.add(new TicTacToeGamePanel(f));
    f.setVisible(true);
  }
}
