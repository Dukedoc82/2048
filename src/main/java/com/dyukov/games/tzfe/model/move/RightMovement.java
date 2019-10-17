package com.dyukov.games.tzfe.model.move;

import com.dyukov.games.tzfe.model.BusyCell;
import com.dyukov.games.tzfe.model.field.Cell;
import com.dyukov.games.tzfe.model.field.GameField;
import com.dyukov.games.tzfe.model.field.Tile;

/**
 * @author Dmitry_Dyukov
 * @created on 10/10/2019
 */
public class RightMovement extends Movement {

   @Override
   public boolean makeMovement(GameField gameField) {
      boolean isMoved = false;
      for (int row = 0; row < GameField.DIMENSION; row++) {
         for (int column = GameField.DIMENSION - 1; column >= 0; column--) {
            isMoved = moveTile(gameField, column, row) || isMoved;
         }
      }
      return isMoved;
   }

   @Override
   protected BusyCell getNextInDirectionBusyCell(GameField gameField, int column, int row) {
      if (column == GameField.DIMENSION - 1) {
         return null;
      }
      Tile[][] gameTiles = gameField.getTiles();
      Tile tile = gameTiles[++column][row];
      while (tile == null && column < GameField.DIMENSION - 1) {
         tile = gameTiles[++column][row];
      }
      return getBusyCell(column, row, tile);
   }

   @Override
   protected boolean isBorderTile(int column, int row) {
      return column == GameField.DIMENSION - 1;
   }

   @Override
   protected boolean isNeigbourCells(BusyCell nextInDirectionBusyCell, int column, int row) {
      return column + 1 == nextInDirectionBusyCell.getCell().getX();
   }

   @Override
   protected void initializeBorderTile(GameField gameField, int column, int row, int weight) {
      gameField.getTiles()[GameField.DIMENSION - 1][row] = new Tile(weight);
      gameField.getFreeCells().remove(new Cell(GameField.DIMENSION - 1, row));
   }

   @Override
   protected int getNewColumn(int column, int row, BusyCell busyCell) {
      return busyCell.getCell().getX() - 1;
   }

   @Override
   protected int getNewRow(int column, int row, BusyCell busyCell) {
      return row;
   }

}
