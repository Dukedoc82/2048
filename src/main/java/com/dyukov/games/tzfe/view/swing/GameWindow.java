package com.dyukov.games.tzfe.view.swing;

import com.dyukov.games.tzfe.controller.swing.KeyboardListener;
import com.dyukov.games.tzfe.model.field.GameField;

import javax.swing.*;

/**
 * @author Dmitry_Dyukov
 * @created on 11/10/2019
 */
public class GameWindow {

   public void runGame() {
      GameField gameField = new GameField();
      GameFrame gameFrame = new GameFrame(gameField);
      gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      gameFrame.setSize(ViewConstants.FIELD_SIZE, ViewConstants.FIELD_SIZE);
      gameFrame.setVisible(true);
      gameFrame.addKeyListener(new KeyboardListener(gameField, gameFrame));
   }


}
