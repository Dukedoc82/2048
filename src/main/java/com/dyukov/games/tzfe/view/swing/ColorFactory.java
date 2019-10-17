package com.dyukov.games.tzfe.view.swing;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry_Dyukov
 * @created on 16/10/2019
 */
public class ColorFactory {

   private static final Map<Integer, Color> colorMap = new HashMap<>();
   private static final Color DEFAULT_COLOR = new Color(255, 0, 0);

   private ColorFactory() {
      super();
   }

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

   public static Color getColor(int weight) {
      if (colorMap.get(weight) != null) {
         return colorMap.get(weight);
      } else {
         return DEFAULT_COLOR;
      }
   }

}
