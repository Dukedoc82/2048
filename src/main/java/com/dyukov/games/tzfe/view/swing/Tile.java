package com.dyukov.games.tzfe.view.swing;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry_Dyukov
 * @created on 15/10/2019
 */
public class Tile extends JButton {

   private static final Map<Integer, Color> colorMap = new HashMap<>();



   static {
      colorMap.put(2, new Color(0, 0, 128));
      colorMap.put(4, new Color(0, 0, 255));
      colorMap.put(8, new Color(0, 128, 128));
      colorMap.put(16, new Color(0, 128, 255));
      colorMap.put(32, new Color(0, 255, 0));
      colorMap.put(64, new Color(0, 255, 128));
      colorMap.put(128, new Color(0, 255, 255));
      colorMap.put(256, new Color(128, 0, 0));
      colorMap.put(528, new Color(128, 0, 128));
      colorMap.put(1024, new Color(128, 0, 255));
      colorMap.put(2048, new Color(255, 0, 0));
   }

   public Tile() {
      Font font = getFont();
      Font newFont = new Font(font.getFontName(), font.getStyle(), 40);
      setSize(64, 64);
      setFont(newFont);
   }

   public Tile(int weight) {
      this();
      setText(String.valueOf(weight));
      setForeground(getColor(weight));
   }

   private Color getColor(int weight) {
      return colorMap.get(weight);
   }

}
