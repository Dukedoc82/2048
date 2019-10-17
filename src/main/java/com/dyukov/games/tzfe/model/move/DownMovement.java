package com.dyukov.games.tzfe.model.move;

import com.dyukov.games.tzfe.model.BusyCell;
import com.dyukov.games.tzfe.model.field.Cell;
import com.dyukov.games.tzfe.model.field.GameField;
import com.dyukov.games.tzfe.model.field.Tile;
import com.sun.istack.internal.Nullable;

/**
 * @author Dmitry_Dyukov
 * @created on 10/10/2019
 */
public class DownMovement extends Movement {

   @Override
   public boolean makeMovement(GameField gameField) {
      boolean isMoved = false;
      for (int column = 0; column < GameField.DIMENSION; column++) {
         for (int row = GameField.DIMENSION - 2; row >= 0; row--) {
            isMoved = moveTile(gameField, column, row) || isMoved;
         }
      }
      return isMoved;
   }

   @Nullable
   @Override
   protected BusyCell getNextInDirectionBusyCell(GameField gameField, int column, int row) {
      if (row == GameField.DIMENSION - 1) {
         return null;
      }
      Tile[][] gameTiles = gameField.getTiles();
      row = row + 1;
      Tile tile = gameTiles[column][row];
      while (tile == null && row < GameField.DIMENSION - 1) {
         row = row + 1;
         tile = gameTiles[column][row];
      }
      return getBusyCell(column, row, tile);
   }

   @Override
   protected boolean isBorderTile(int column, int row) {
      return row == GameField.DIMENSION - 1;
   }

   @Override
   protected boolean isNeigbourCells(BusyCell nextInDirectionBusyCell, int column, int row) {
      return row + 1 == nextInDirectionBusyCell.getCell().getY();
   }

   protected void initializeBorderTile(GameField gameField, int column, int row, int weight) {
      gameField.getTiles()[column][GameField.DIMENSION - 1] = new Tile(weight);
      gameField.getFreeCells().remove(new Cell(column, GameField.DIMENSION - 1));
   }

   @Override
   protected int getNewColumn(int column, int row, BusyCell busyCell) {
      return column;
   }

   @Override
   protected int getNewRow(int column, int row, BusyCell busyCell) {
      return busyCell.getCell().getY() - 1;
   }

}
