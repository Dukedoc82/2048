package com.dyukov.games.tzfe.view.swing;

import javax.swing.*;

/**
 * @author Dmitry_Dyukov
 * @created on 16/10/2019
 */
public class TileFactory {

   private TileFactory() {
      super();
   }

   public static JComponent getTile(TileType tileType, int tileWeight) {
      switch (tileType) {
         case BUTTON:
            return createButtonTile(tileWeight);
         case PANEL:
            return createPanelTile(tileWeight);
         default:
            throw new IllegalArgumentException("Tile type " + tileType + "is not supported!");
      }

   }

   private static JComponent createButtonTile(int tileWeight) {
      if (tileWeight == 0)
         return new Tile();
      else
         return new Tile(tileWeight);
   }

   private static JComponent createPanelTile(int tileWeight) {
      if (tileWeight == 0) {
         return new PanelTile();
      } else {
         return new PanelTile(tileWeight);
      }
   }

}
