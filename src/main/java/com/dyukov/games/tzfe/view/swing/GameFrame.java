package com.dyukov.games.tzfe.view.swing;

import com.dyukov.games.tzfe.model.field.GameField;

import javax.swing.*;
import java.awt.*;

/**
 * @author Dmitry_Dyukov
 * @created on 11/10/2019
 */
public class GameFrame extends JFrame {

   private JPanel panel;

   public GameFrame(GameField gameField) {
      panel = new JPanel();
      panel.setBorder(BorderFactory.createLoweredBevelBorder());
      drawCurrentFieldState(gameField);
      panel.setLayout(new GridLayout(4, 4));
      add(panel);
   }

   public void drawCurrentFieldState(GameField gameField) {
      removePreviousStateDrawing();
      drawNewState(gameField);
      updateView(gameField);
   }

   private void updateView(GameField gameField) {
      panel.doLayout();
      panel.invalidate();
      panel.validate();
      panel.repaint();
      if (gameField.isGameOver()) {
         JDialog dialog = new JDialog(this, "Game Over", true);
         dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         dialog.setSize(180, 90);
         dialog.setVisible(true);
      }
   }

   private void drawNewState(GameField gameField) {
      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 4; j++) {
            if (gameField.getTiles()[j][i] == null) {
               panel.add(TileFactory.getTile(TileType.PANEL, 0));
            } else {
               panel.add(TileFactory.getTile(TileType.PANEL, gameField.getTiles()[j][i].getWeight()));
            }
         }
      }
   }

   private void removePreviousStateDrawing() {
      panel.removeAll();
   }

}
