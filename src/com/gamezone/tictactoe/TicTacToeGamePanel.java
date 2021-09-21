package com.gamezone.tictactoe;

import com.gamezone.launcher.Launcher;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TicTacToeGamePanel extends JPanel {

  private final String text = "X or O";
  private JFrame parentFrame;
  ImageIcon cross;
  ImageIcon zero;
  Integer[] values = new Integer[9];
  List<JButton> allButtons = new ArrayList<>();
  String winner = "";

  int currentVal = 0;

  public TicTacToeGamePanel(JFrame parentFrame) {
    this.parentFrame = parentFrame;
    setBackground(Color.DARK_GRAY);
    cross = new ImageIcon("resources/x.png");
    zero = new ImageIcon("resources/o.png");
    setLayout(null);
    // Restart and back buttons
    JButton back = new JButton("Back");
    back.setBounds(270, 10, 100, 25);
    back.addActionListener((event) -> {
      parentFrame.getContentPane().removeAll();
      parentFrame.setTitle("GameZone");
      parentFrame.getContentPane().add(new Launcher(parentFrame));
      parentFrame.repaint();
      parentFrame.setVisible(true);
    });
    this.add(back);

    JButton restart = new JButton("Restart");
    restart.setBounds(460, 10, 100, 25);
    restart.addActionListener((event) -> {
      parentFrame.getContentPane().removeAll();
      parentFrame.setTitle("Tic Tac Toe");
      parentFrame.getContentPane().add(new TicTacToeGamePanel(parentFrame));
      parentFrame.repaint();
      parentFrame.setVisible(true);
    });
    this.add(restart);
    // Playing Buttons
    JButton zero = new JButton(text);
    zero.setBounds(270, 200, 100, 100);
    zero.addActionListener((event) -> {
      this.remove(zero);
      this.values[0] = currentVal;
      toggleCurrentVal();
      repaint();
    });
    this.add(zero);

    JButton first = new JButton(text);
    first.setBounds(370, 200, 100, 100);
    first.addActionListener((event) -> {
      this.remove(first);
      this.values[1] = currentVal;
      toggleCurrentVal();
      repaint();
    });
    this.add(first);
    JButton second = new JButton(text);
    second.setBounds(470, 200, 100, 100);
    second.addActionListener((event) -> {
      this.remove(second);
      this.values[2] = currentVal;
      toggleCurrentVal();
      repaint();
    });
    this.add(second);

    JButton third = new JButton(text);
    third.setBounds(270, 300, 100, 100);
    third.addActionListener((event) -> {
      this.remove(third);
      this.values[3] = currentVal;
      toggleCurrentVal();
      repaint();
    });
    this.add(third);

    JButton fourth = new JButton(text);
    fourth.setBounds(370, 300, 100, 100);
    fourth.addActionListener((event) -> {
      this.remove(fourth);
      this.values[4] = currentVal;
      toggleCurrentVal();
      repaint();
    });
    this.add(fourth);

    JButton fifth = new JButton(text);
    fifth.setBounds(470, 300, 100, 100);
    fifth.addActionListener((event) -> {
      this.remove(fifth);
      this.values[5] = currentVal;
      toggleCurrentVal();
      repaint();
    });
    this.add(fifth);

    JButton sixth = new JButton(text);
    sixth.setBounds(270, 400, 100, 100);
    sixth.addActionListener((event) -> {
      this.remove(sixth);
      this.values[6] = currentVal;
      toggleCurrentVal();
      repaint();
    });
    this.add(sixth);

    JButton seventh = new JButton(text);
    seventh.setBounds(370, 400, 100, 100);
    seventh.addActionListener((event) -> {
      this.remove(seventh);
      this.values[7] = currentVal;
      toggleCurrentVal();
      repaint();
    });
    this.add(seventh);

    JButton eight = new JButton(text);
    eight.setBounds(470, 400, 100, 100);
    eight.addActionListener((event) -> {
      this.remove(eight);
      this.values[8] = currentVal;
      toggleCurrentVal();
      repaint();
    });
    this.add(eight);

    allButtons.add(zero);
    allButtons.add(first);
    allButtons.add(second);
    allButtons.add(third);
    allButtons.add(fourth);
    allButtons.add(fifth);
    allButtons.add(sixth);
    allButtons.add(seventh);
    allButtons.add(eight);
  }

  private void toggleCurrentVal() {
    currentVal = currentVal == 0 ? 1 : 0;
  }

  private void populateTestVal() {
    values[0] = 0;
    values[1] = 0;
    values[2] = 0;
    values[3] = 0;
    values[4] = 0;
    values[5] = 0;
    values[6] = 0;
    values[7] = 0;
    values[8] = 0;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.WHITE);

    //g.drawRect(270, 200, 300, 300);
    // Horizontal Lines
    g.drawLine(270, 300, 570, 300);
    g.drawLine(270, 400, 570, 400);
    // Vertical Lines
    g.drawLine(370, 200, 370, 500);
    g.drawLine(470, 200, 470, 500);

    for (int i = 0; i < 9; i++) {
      if (this.values[i] != null) {
        switch (i) {
          case 0:
            paintImage(g, getZeroCross(this.values[i]), 275, 205);
            //g.drawString(getZeroCross(this.values[i]), 320, 250);
            break;
          case 1:
            paintImage(g, getZeroCross(this.values[i]), 375, 205);
            //g.drawString(getZeroCross(this.values[i]), 420, 250);
            break;
          case 2:
            paintImage(g, getZeroCross(this.values[i]), 475, 205);
            //g.drawString(getZeroCross(this.values[i]), 520, 250);
            break;
          case 3:
            paintImage(g, getZeroCross(this.values[i]), 275, 305);
            //g.drawString(getZeroCross(this.values[i]), 320, 350);
            break;
          case 4:
            paintImage(g, getZeroCross(this.values[i]), 375, 305);
            //g.drawString(getZeroCross(this.values[i]), 420, 350);
            break;
          case 5:
            paintImage(g, getZeroCross(this.values[i]), 475, 305);
            //g.drawString(getZeroCross(this.values[i]), 520, 350);
            break;
          case 6:
            paintImage(g, getZeroCross(this.values[i]), 275, 405);
            //g.drawString(getZeroCross(this.values[i]), 320, 450);
            break;
          case 7:
            paintImage(g, getZeroCross(this.values[i]), 375, 405);
            //g.drawString(getZeroCross(this.values[i]), 420, 450);
            break;
          case 8:
            paintImage(g, getZeroCross(this.values[i]), 475, 405);
            //g.drawString(getZeroCross(this.values[i]), 520, 450);
            break;
          default:
            break;
        }
      }
    }
    if (anyoneWins()) {
      allButtons.forEach((button) -> this.remove(button));
      g.setFont(new Font("arial", Font.BOLD, 50));
      if (winner.equals("O")) {
        g.setColor(Color.YELLOW);
      } else {
        g.setColor(Color.GREEN);
      }
      g.drawString(winner + " Wins", 270, 100);
    } else {
      if (noMovesLeft()) {
        g.setFont(new Font("arial", Font.BOLD, 50));
        g.setColor(Color.RED);
        g.drawString("Game Over!", 270, 100);
      }
    }
  }

  private boolean noMovesLeft() {
    for (Integer i : values) {
      if (i == null) {
        return false;
      }
    }
    return true;
  }

  private boolean anyoneWins() {
    //012
    //345
    //678
    //048
    //642
    //036
    //147
    //258
    if (checkIfWon(0, 1, 2) || checkIfWon(3, 4, 5) || checkIfWon(6, 7, 8) ||
        checkIfWon(0, 4, 8) || checkIfWon(6, 4, 2) || checkIfWon(0, 3, 6) ||
        checkIfWon(1, 4, 7) || checkIfWon(2, 5, 8)) {
      return true;
    }
    return false;
  }

  private boolean checkIfWon(int x, int y, int z) {
    if (values[x] == null) {
      return false;
    }
    if (values[x] == values[y] && values[y] == values[z]) {
      if (values[x] == 0) {
        winner = "O";
      } else {
        winner = "X";
      }
      return true;
    }
    return false;
  }

  private String getZeroCross(int val) {
    if (val == 0) {
      return "O";
    } else if (val == 1) {
      return "X";
    } else {
      return "Invalid";
    }
  }

  private void paintImage(Graphics g, String val, int x, int y) {
    switch (val) {
      case "O":
        g.setFont(new Font("arial", Font.BOLD, 40));
        g.setColor(Color.YELLOW);
        g.drawString("O", x + 50, y + 50);
        //zero.paintIcon(this, g, x, y);
        break;
      case "X":
        g.setFont(new Font("arial", Font.BOLD, 40));
        g.setColor(Color.GREEN);
        g.drawString("X", x + 50, y + 50);
        //cross.paintIcon(this, g, x, y);
        break;
    }
  }
}
