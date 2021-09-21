package com.gamezone.snake;

import com.gamezone.launcher.Launcher;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SnakeGameStartPanel extends JPanel {

  JFrame parentFrame;
  int selectedDelay = 1000;
  int level = 0;
  private ImageIcon wallpaper;

  public SnakeGameStartPanel(JFrame parentFrame) {
    this.parentFrame = parentFrame;
    //setBackground(Color.DARK_GRAY);
    setLayout(null);
    // Background Image
    wallpaper = new ImageIcon("resources/wallpaper_front.jpg");
    // Options
    JLabel label = new JLabel("Speed: ");
    label.setForeground(Color.WHITE);
    label.setBounds(350, 250, 60, 30);
    this.add(label);
    // Speed Selection Drop Down
    String[] speed = {"Slow", "Medium", "Fast"};
    JComboBox<String> speedSelector = new JComboBox<>(speed);
    speedSelector.setBounds(420, 250, 80, 30);
    speedSelector.addActionListener((event) -> {
      JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
      switch ((String) comboBox.getSelectedItem()) {
        case "Slow":
          this.selectedDelay = 1000;
          break;
        case "Medium":
          this.selectedDelay = 500;
          break;
        case "Fast":
          this.selectedDelay = 100;
          break;
        default:
          this.selectedDelay = 1000;
      }
    });
    this.add(speedSelector);
    // Level Selection Drop down
    JLabel levelLabel = new JLabel("Level: ");
    levelLabel.setForeground(Color.WHITE);
    this.add(levelLabel);
    levelLabel.setBounds(350, 290, 60, 30);
    String[] levelArray = {"level 0", "level 1", "level 2"};
    JComboBox<String> levelSelector = new JComboBox<>(levelArray);
    levelSelector.setBounds(420, 290, 80, 30);
    levelSelector.addActionListener((event) -> {
      JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
      switch ((String) comboBox.getSelectedItem()) {
        case "level 0":
          this.level = 0;
          break;
        case "level 1":
          this.level = 1;
          break;
        case "level 2":
          this.level = 2;
          break;
        default:
          this.level = 0;
      }
    });
    this.add(levelSelector);

    // Start button
    JButton startButton = new JButton("Start");
    startButton.setBounds(350, 330, 80, 30);
    startButton.addActionListener((event) -> {
      parentFrame.getContentPane().removeAll();
      parentFrame.setTitle("Snake Game");
      parentFrame.getContentPane().add(new SnakeGamePanel(parentFrame, selectedDelay, level));
      parentFrame.repaint();
      parentFrame.setVisible(true);
    });
    this.add(startButton);

    // Back button
    JButton backButton = new JButton("Back");
    backButton.setBounds(430, 330, 80, 30);
    backButton.addActionListener((event) -> {
      parentFrame.getContentPane().removeAll();
      parentFrame.setTitle("GameZone");
      parentFrame.getContentPane().add(new Launcher(parentFrame));
      parentFrame.repaint();
      parentFrame.setVisible(true);
    });
    this.add(backButton);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    wallpaper.paintIcon(this, g, 0, 0);
    g.setColor(Color.WHITE);
    g.setFont(new Font("arial", Font.BOLD, 50));
    g.drawString("Options", 320, 200);
  }
}
