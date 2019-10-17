package com.dyukov.games.tzfe.model.move;

import com.dyukov.games.tzfe.model.BusyCell;
import com.dyukov.games.tzfe.model.field.Cell;
import com.dyukov.games.tzfe.model.field.GameField;
import com.dyukov.games.tzfe.model.field.Tile;
import com.sun.istack.internal.Nullable;

import java.util.Arrays;

/**
 * @author Dmitry_Dyukov
 * @created on 10/10/2019
 */
public abstract class Movement {

   public abstract boolean makeMovement(GameField gameField);

   public boolean move(GameField gameField) {
      boolean merged = makeMovement(gameField);
      markTilesUnMerged(gameField);
      return merged;
   }

   protected boolean mergeTiles(BusyCell nextInDirectionBusyCell, GameField gameField, int column, int row) {
      Tile nextInDirectionTile = nextInDirectionBusyCell.getTile();
      nextInDirectionTile.setWeight(nextInDirectionTile.getWeight() * 2);
      nextInDirectionTile.setMerged(true);
      gameField.getTiles()[column][row] = null;
      gameField.getFreeCells().add(new Cell(column, row));
      return true;
   }

   protected boolean moveTile(GameField gameField, int column, int row) {
      Tile movedTile = gameField.getTiles()[column][row];
      if (movedTile != null) {
         BusyCell nextInDirectionBusyCell = getNextInDirectionBusyCell(gameField, column, row);
         if (nextInDirectionBusyCell == null) {
            return moveTileToBorder(movedTile, gameField, column, row);
         } else {
            if (!nextInDirectionBusyCell.getTile().isMerged() && nextInDirectionBusyCell.getTile().equals(movedTile)) {
               return mergeTiles(nextInDirectionBusyCell, gameField, column, row);
            } else {
               return moveTileInDirection(movedTile, nextInDirectionBusyCell, gameField, column, row);
            }
         }
      }
      return false;
   }

   protected abstract BusyCell getNextInDirectionBusyCell(GameField gameField, int column, int row);

   protected boolean moveTileToBorder(Tile movedTile, GameField gameField, int column, int row) {
      int weight = movedTile.getWeight();
      if (!isBorderTile(column, row)) {
         deleteTile(gameField, column, row);
         initializeBorderTile(gameField, column, row, weight);
         return true;
      } else {
         return false;
      }
   }

   protected abstract boolean isBorderTile(int column, int row);

   protected void deleteTile(GameField gameField, int column, int row) {
      gameField.getTiles()[column][row] = null;
      gameField.getFreeCells().add(new Cell(column, row));
   }

   protected boolean moveTileInDirection(Tile movedTile, BusyCell nextInDirectionBusyCell, GameField gameField, int column, int row) {
      if (isNeigbourCells(nextInDirectionBusyCell, column, row))
         return false;
      deleteTile(gameField, column, row);
      createTile(movedTile, nextInDirectionBusyCell, gameField, column, row);
      return true;
   }

   protected abstract boolean isNeigbourCells(BusyCell nextInDirectionBusyCell, int column, int row);

   protected void createTile(Tile movedTile, BusyCell nextInDirectionBusyCell, GameField gameField, int column, int row) {
      int weight = movedTile.getWeight();
      int newColumn = getNewColumn(column, row, nextInDirectionBusyCell);
      int newRow = getNewRow(column, row, nextInDirectionBusyCell);
      gameField.getTiles()[newColumn][newRow] = new Tile(weight);
      gameField.getFreeCells().remove(new Cell(newColumn, newRow));
   }

   @Nullable
   protected BusyCell getBusyCell(int column, int row, Tile tile) {
      BusyCell busyCell = null;
      if (tile != null) {
         busyCell = new BusyCell(new Cell(column, row), tile);
      }
      return busyCell;
   }

   protected abstract void initializeBorderTile(GameField gameField, int column, int row, int weight);

   protected abstract int getNewColumn(int column, int row, BusyCell busyCell);

   protected abstract int getNewRow(int column, int row, BusyCell busyCell);

   private void markTilesUnMerged(GameField gameField) {
      Arrays.stream(gameField.getTiles()).forEach(tilesRow ->
         Arrays.stream(tilesRow).forEach(tile -> {
            if (tile != null) {
               tile.setMerged(false);
            }
         })
      );
   }

}
