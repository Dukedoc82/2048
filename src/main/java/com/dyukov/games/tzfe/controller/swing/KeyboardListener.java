package com.dyukov.games.tzfe.controller.swing;

import com.dyukov.games.tzfe.model.field.Directions;
import com.dyukov.games.tzfe.model.field.GameField;
import com.dyukov.games.tzfe.view.swing.GameFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Dmitry_Dyukov
 * @created on 16/10/2019
 */
public class KeyboardListener implements KeyListener {

   private GameField gameField;
   private GameFrame gameFrame;

   public KeyboardListener(GameField gameField, GameFrame gameFrame) {
      this.gameField = gameField;
      this.gameFrame = gameFrame;
   }

   @Override
   public void keyTyped(KeyEvent e) {
      // DO NOTHING
   }

   @Override
   public void keyPressed(KeyEvent e) {
      // DO NOTHING
   }

   @Override
   public void keyReleased(KeyEvent e) {
      Directions direction = getDirection(e);
      if (direction != null && gameField.move(direction)) {
         gameFrame.drawCurrentFieldState(gameField);
      }
   }

   private Directions getDirection(KeyEvent e) {
      switch (e.getKeyCode()) {
         case KeyEvent.VK_UP:
            return Directions.UP;
         case KeyEvent.VK_DOWN:
            return Directions.DOWN;
         case KeyEvent.VK_LEFT:
            return Directions.LEFT;
         case KeyEvent.VK_RIGHT:
            return Directions.RIGHT;
         default:
            return null;
      }
   }
}
