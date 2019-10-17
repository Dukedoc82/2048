package com.dyukov.games.tzfe.model.move;

import com.dyukov.games.tzfe.model.BusyCell;
import com.dyukov.games.tzfe.model.field.Cell;
import com.dyukov.games.tzfe.model.field.GameField;
import com.dyukov.games.tzfe.model.field.Tile;

/**
 * @author Dmitry_Dyukov
 * @created on 10/10/2019
 */
public class UpMovement extends Movement {

   @Override
   public boolean makeMovement(GameField gameField) {
      boolean isMoved = false;
      for (int column = 0; column < GameField.DIMENSION; column++) {
         for (int row = 1; row < GameField.DIMENSION; row++) {
            isMoved = moveTile(gameField, column, row) || isMoved;
         }
      }
      return isMoved;
   }

   protected BusyCell getNextInDirectionBusyCell(GameField gameField, int column, int row) {
      if (row == 0) {
         return null;
      }
      Tile[][] gameTiles = gameField.getTiles();
      Tile tile = gameTiles[column][--row];
      while (tile == null && row > 0) {
         tile = gameTiles[column][--row];
      }
      return getBusyCell(column, row, tile);
   }

   @Override
   protected boolean isBorderTile(int column, int row) {
      return row == 0;
   }

   @Override
   protected boolean isNeigbourCells(BusyCell nextInDirectionBusyCell, int column, int row) {
      return row - 1 == nextInDirectionBusyCell.getCell().getY();
   }

   @Override
   protected void initializeBorderTile(GameField gameField, int column, int row, int weight) {
      gameField.getTiles()[column][0] = new Tile(weight);
      gameField.getFreeCells().remove(new Cell(column, 0));
   }

   @Override
   protected int getNewColumn(int column, int row, BusyCell busyCell) {
      return column;
   }

   @Override
   protected int getNewRow(int column, int row, BusyCell busyCell) {
      return busyCell.getCell().getY() + 1;
   }

}
