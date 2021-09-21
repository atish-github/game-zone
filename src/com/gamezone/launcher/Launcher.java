package com.gamezone.launcher;

import com.gamezone.snake.SnakeGameStartPanel;
import com.gamezone.tictactoe.TicTacToeGamePanel;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Launcher extends JPanel {

  JFrame parentFrame;
  ImageIcon titleImage;

  public Launcher(JFrame parentFrame) {
    this.parentFrame = parentFrame;
    setLayout(null);
    setBackground(Color.BLACK);
    titleImage = new ImageIcon("resources/titleImage.jpg");
    // Positioning of elements
    int baseX = parentFrame.getWidth() / 2 - 200;
    int baseY = parentFrame.getHeight() / 2 - 300;
    // Add descriptive text
    JLabel label = new JLabel("Select one of the following games to play");
    label.setForeground(Color.white);
    label.setBounds(baseX, baseY, 300, 30);
    //label.setHorizontalAlignment(SwingConstants.CENTER);
    add(label);
    // Add Buttons for Games
    JButton button = new JButton(new ImageIcon("resources/snakeLogo.jpg"));
    button.setText("Snake");
    //button.setIcon(new ImageIcon("resources/snakeLogo.jpg"));
    button.setBounds(baseX, baseY + 50, 250, 200);
    button.addActionListener((event) -> {
      parentFrame.getContentPane().removeAll();
      parentFrame.setTitle("Snake Game");
      parentFrame.getContentPane().add(new SnakeGameStartPanel(parentFrame));
      parentFrame.repaint();
      parentFrame.setVisible(true);
    });
    add(button);

    // Add Buttons for Games
    JButton ticButton = new JButton(new ImageIcon("resources/tictactoe.png"));
    ticButton.setText("Tic Tac Toe");
    //button.setIcon(new ImageIcon("resources/snakeLogo.jpg"));
    ticButton.setBounds(baseX, baseY + 270, 270, 200);
    ticButton.addActionListener((event) -> {
      parentFrame.getContentPane().removeAll();
      parentFrame.setTitle("Tic Tac Toe");
      parentFrame.getContentPane().add(new TicTacToeGamePanel(parentFrame));
      parentFrame.repaint();
      parentFrame.setVisible(true);
    });
    add(ticButton);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    titleImage.paintIcon(this, g, 600, 100);
  }
}
