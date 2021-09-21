package com.gamezone.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class SnakeGamePanel extends JPanel implements KeyListener, ActionListener {

  JFrame parentFrame;
  private int[] xSnakeLength = new int[750];
  private int[] ySnakeLength = new int[750];
  private int score = 0;
  private int lengthOfSnake = 3;
  private ImageIcon rightMouth;
  private ImageIcon leftMouth;
  private ImageIcon upMouth;
  private ImageIcon downMouth;
  private ImageIcon bodyImage;
  // Direction
  private boolean right;
  private boolean left;
  private boolean up;
  private boolean down;
  private int moves = 0;
  // pixel size
  private static final int PIXEL_SIZE = 25;

  private Timer timer;
  private int delay = 500;
  // Food
  private ImageIcon foodImage;
  private Random random = new Random();
  private int MIN_X = 1;
  private int xPos = random.nextInt(34) + MIN_X;
  private int MIN_Y = 3;
  private int yPos = random.nextInt(23) + MIN_Y;
  //Walls
  private ImageIcon wallImage;
  private int level;
  //Background
  ImageIcon wallpaper;

  public SnakeGamePanel(JFrame parentFrame, int selectedDelay, int level) {
    this.parentFrame = parentFrame;
    this.delay = selectedDelay;
    this.level = level;
    xPos = xPos < 10 ? xPos + level : xPos - level;
    yPos = yPos < 10 ? yPos + level : yPos - level;
    setBackground(Color.DARK_GRAY);
    JButton backButton = new JButton("Back");
    backButton.addActionListener((event) -> {
      parentFrame.getContentPane().removeAll();
      parentFrame.setTitle("GameZone");
      parentFrame.getContentPane().add(new SnakeGameStartPanel(parentFrame));
      parentFrame.repaint();
      parentFrame.setVisible(true);
    });
    add(backButton);
    //setFocusTraversalKeysEnabled(true);
    timer = new Timer(delay, this);
    // add listeners
    addKeyListener(this);
    setFocusable(true);
    wallpaper = new ImageIcon("resources/wallpaper_front.jpg");
    //backGround = new ImageIcon("resources/forest_wallpaper.jpg");
    SwingUtilities.invokeLater(() -> {
      this.requestFocusInWindow();
    });
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    wallpaper.paintIcon(this, g, 0, 0);
    if (moves == 0) {
      xSnakeLength[0] = PIXEL_SIZE * 4;
      xSnakeLength[1] = PIXEL_SIZE * 3;
      xSnakeLength[2] = PIXEL_SIZE * 2;

      ySnakeLength[0] = PIXEL_SIZE * 4;
      ySnakeLength[1] = PIXEL_SIZE * 4;
      ySnakeLength[2] = PIXEL_SIZE * 4;
    }

    //border of details
    g.setColor(Color.BLACK);
    g.drawRect(24, 10, 851, 55);
    //border of gameplay
    g.setColor(Color.WHITE);
    g.drawRect(24, 74, 851, 577);
    g.setColor(Color.BLACK);
    g.fillRect(25, 75, 850, 575);
    //backGround.paintIcon(this, g, 25, 75);
    //draw the scores
    g.setColor(Color.WHITE);
    g.setFont(new Font("arial", Font.PLAIN, 14));
    g.drawString(("Score : " + score), 780, 30);
    g.drawString(("Length : " + lengthOfSnake), 780, 50);

    // Draw Walls
    // start
    wallImage = new ImageIcon("resources/brownWall.png");
    printOrCheckCollision(0, 0, g, true);
    //end

    rightMouth = new ImageIcon("resources/rightmouth.png");
    rightMouth.paintIcon(this, g, xSnakeLength[0], ySnakeLength[0]);
    for (int index = 0; index < lengthOfSnake; index++) {
      if (index == 0) {
        if (right) {
          rightMouth = new ImageIcon("resources/rightmouth.png");
          rightMouth.paintIcon(this, g, xSnakeLength[index], ySnakeLength[index]);
        } else if (left) {
          leftMouth = new ImageIcon("resources/leftmouth.png");
          leftMouth.paintIcon(this, g, xSnakeLength[index], ySnakeLength[index]);
        } else if (up) {
          upMouth = new ImageIcon("resources/upmouth.png");
          upMouth.paintIcon(this, g, xSnakeLength[index], ySnakeLength[index]);
        } else if (down) {
          downMouth = new ImageIcon("resources/downmouth.png");
          downMouth.paintIcon(this, g, xSnakeLength[index], ySnakeLength[index]);
        }
      } else {
        bodyImage = new ImageIcon("resources/body.png");
        bodyImage.paintIcon(this, g, xSnakeLength[index], ySnakeLength[index]);
      }
    }

    // Food code
    foodImage = new ImageIcon("resources/food.jpg");
    if (xPos * PIXEL_SIZE == xSnakeLength[0] && yPos * PIXEL_SIZE == ySnakeLength[0]) {
      lengthOfSnake++;
      score++;
      xPos = random.nextInt(34) + MIN_X;
      yPos = random.nextInt(23) + MIN_Y;
      xPos = xPos < 10 ? xPos + level : xPos - level;
      yPos = yPos < 10 ? yPos + level : yPos - level;
    }
    relocateFood(xPos, yPos, g);
    foodImage.paintIcon(this, g, xPos * PIXEL_SIZE, yPos * PIXEL_SIZE);

    for (int b = 1; b < lengthOfSnake; b++) {
      if ((xSnakeLength[b] == xSnakeLength[0] && ySnakeLength[b] == ySnakeLength[0])
          || printOrCheckCollision(
          xSnakeLength[0], ySnakeLength[0], g, false)) {
        right = false;
        left = false;
        up = false;
        down = false;

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 50));
        g.drawString("Game Over!", 300, 300);

        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Space to RESTART", 350, 340);
      }
    }
  }

  private void relocateFood(int xPos, int yPos, Graphics g) {
    if (printOrCheckCollision(xPos * PIXEL_SIZE, yPos * PIXEL_SIZE, g, false)) {
      xPos = random.nextInt(34) + MIN_X;
      yPos = random.nextInt(23) + MIN_Y;
      xPos = xPos < 10 ? xPos + 1 : xPos - 1;
      yPos = yPos < 10 ? yPos + 1 : yPos - 1;
      this.xPos = xPos;
      this.yPos = yPos;
      relocateFood(xPos, yPos, g);
    }
  }

  private boolean printOrCheckCollision(int xHead, int yHead, Graphics g, boolean print) {
    int xStart = 25;
    int xEnd = 34 * PIXEL_SIZE;
    int yStart = 75;
    int yEnd = 23 * PIXEL_SIZE;
    switch (level) {
      case 0:
        break;
      case 1:
        for (int row = xStart; row <= 850; ) {
          if (!print && ((xHead == row && yHead == yStart) || (xHead == row && yHead == (yEnd + (2
              * PIXEL_SIZE))))) {
            return true;
          } else if (print) {
            wallImage.paintIcon(this, g, row, yStart);
            wallImage.paintIcon(this, g, row, yEnd + (2 * PIXEL_SIZE));
          }
          row = row + PIXEL_SIZE;
        }
        for (int col = yStart; col <= 600; ) {
          if (!print && ((xHead == xStart && yHead == col) || (xHead == xEnd && yHead == col))) {
            return true;
          } else if (print) {
            wallImage.paintIcon(this, g, xStart, col);
            wallImage.paintIcon(this, g, xEnd, col);
          }
          col = col + PIXEL_SIZE;
        }
        break;
      case 2:
        for (int row = xStart; row <= 850; ) {
          if (!print && ((xHead == row && yHead == yStart) || (xHead == row && yHead == (yEnd + (2
              * PIXEL_SIZE))) || ((row >= 325 && row <= 525) && xHead == row && yHead == (
              (yStart + yEnd) / 2 - 75)) || (
              (row >= 325 && row <= 525) && xHead == row && yHead == ((yStart + yEnd) / 2 + 75)))) {
            return true;
          } else if (print) {
            wallImage.paintIcon(this, g, row, yStart);
            if (row >= 325 && row <= 525) {
              wallImage.paintIcon(this, g, row, ((yStart + yEnd) / 2 - 75));
              wallImage.paintIcon(this, g, row, ((yStart + yEnd) / 2 + 75));
            }
            wallImage.paintIcon(this, g, row, yEnd + (2 * PIXEL_SIZE));
          }
          row = row + PIXEL_SIZE;
        }
        for (int col = yStart; col <= 600; ) {
          if (!print && ((xHead == xStart && yHead == col) || (xHead == xEnd && yHead == col))) {
            return true;
          } else if (print) {
            wallImage.paintIcon(this, g, xStart, col);
            wallImage.paintIcon(this, g, xEnd, col);
          }
          col = col + PIXEL_SIZE;
        }
        break;
      default:
        break;
    }
    return false;
  }

  @Override
  public void keyPressed(KeyEvent event) {
    if (!timer.isRunning()) {
      timer.start();
    }
    if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
      moves++;
      if (!left) {
        right = true;
      } else {
        right = false;
        left = true;
      }
      up = false;
      down = false;
    } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
      moves++;
      if (!right) {
        left = true;
      } else {
        left = false;
        right = true;
      }
      up = false;
      down = false;
    } else if (event.getKeyCode() == KeyEvent.VK_UP) {
      moves++;
      if (!down) {
        up = true;
      } else {
        up = false;
        down = true;
      }
      left = false;
      right = false;
    } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
      moves++;
      if (!up) {
        down = true;
      } else {
        down = false;
        up = true;
      }
      left = false;
      right = false;
    }

    if (event.getKeyCode() == KeyEvent.VK_SPACE) {
      score = 0;
      moves = 0;
      lengthOfSnake = 3;
      repaint();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (right) {
      for (int i = lengthOfSnake - 1; i >= 0; i--) {
        ySnakeLength[i + 1] = ySnakeLength[i];
      }
      for (int i = lengthOfSnake; i >= 0; i--) {
        if (i == 0) {
          xSnakeLength[i] = xSnakeLength[i] + PIXEL_SIZE;
        } else {
          xSnakeLength[i] = xSnakeLength[i - 1];
        }
        if (xSnakeLength[i] > 850) {
          xSnakeLength[i] = 25;
        }
      }
      repaint();
    } else if (left) {
      for (int i = lengthOfSnake - 1; i >= 0; i--) {
        ySnakeLength[i + 1] = ySnakeLength[i];
      }
      for (int i = lengthOfSnake; i >= 0; i--) {
        if (i == 0) {
          xSnakeLength[i] = xSnakeLength[i] - PIXEL_SIZE;
        } else {
          xSnakeLength[i] = xSnakeLength[i - 1];
        }
        if (xSnakeLength[i] < 25) {
          xSnakeLength[i] = 850;
        }
      }
      repaint();
    } else if (up) {
      for (int i = lengthOfSnake - 1; i >= 0; i--) {
        xSnakeLength[i + 1] = xSnakeLength[i];
      }
      for (int i = lengthOfSnake; i >= 0; i--) {
        if (i == 0) {
          ySnakeLength[i] = ySnakeLength[i] - PIXEL_SIZE;
        } else {
          ySnakeLength[i] = ySnakeLength[i - 1];
        }
        if (ySnakeLength[i] < 75) {
          ySnakeLength[i] = 625;
        }
      }
      repaint();
    } else if (down) {
      for (int i = lengthOfSnake - 1; i >= 0; i--) {
        xSnakeLength[i + 1] = xSnakeLength[i];
      }
      for (int i = lengthOfSnake; i >= 0; i--) {
        if (i == 0) {
          ySnakeLength[i] = ySnakeLength[i] + PIXEL_SIZE;
        } else {
          ySnakeLength[i] = ySnakeLength[i - 1];
        }
        if (ySnakeLength[i] > 625) {
          ySnakeLength[i] = 75;
        }
      }
      repaint();
    }
  }
}
