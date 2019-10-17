package com.dyukov.games.tzfe.view.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @author Dmitry_Dyukov
 * @created on 16/10/2019
 */
public class PanelTile extends JPanel {

   public PanelTile() {
      setLayout(new GridBagLayout());
      setSize(ViewConstants.TILE_SIZE, ViewConstants.TILE_SIZE);
   }

   public PanelTile(int weight) {
      this();
      add(createLabel(weight));
      if (weight == 0) {
         setBorder(BorderFactory.createLoweredBevelBorder());
      } else {
         setBorder(BorderFactory.createRaisedBevelBorder());
      }
   }

   private JLabel createLabel(int weight) {
      JLabel label = new JLabel(String.valueOf(weight));
      Font font = label.getFont();
      Font newFont = new Font(font.getFontName(), font.getStyle(), ViewConstants.FONT_SIZE);
      label.setFont(newFont);
      label.setForeground(ColorFactory.getColor(weight));
      return label;
   }
}
