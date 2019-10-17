package com.dyukov.games.tzfe.model.field;

import com.dyukov.games.tzfe.model.move.Movement;
import com.dyukov.games.tzfe.model.move.MovementFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Dmitry_Dyukov
 * @created on 10/10/2019
 */
public class GameField {

   public static final int DIMENSION = 4;

   private Tile[][] tiles = new Tile[DIMENSION][DIMENSION];

   private List<Cell> freeCells = new ArrayList<>();

   private Random randomGenerator = new Random();

   private MovementFactory movementFactory;

   public GameField() {
      initFreeCells();
      initEmptyTiles();
      initInitialTiles();
   }

   private void initFreeCells() {
      for (int i = 0; i < DIMENSION; i++) {
         for (int j = 0; j < DIMENSION; j++) {
            freeCells.add(new Cell(i, j));
         }
      }
   }

   private void initEmptyTiles() {
      for (int i = 0; i < DIMENSION; i++) {
         for (int j = 0; j < DIMENSION; j++) {
            tiles[i][j] = null;
         }
      }
   }

   private void initInitialTiles() {
      createTile();
      createTile();
   }

   public void createTile() {
      Cell cell = getFreeRandomCell();
      tiles[cell.getX()][cell.getY()] = new Tile();
      freeCells.remove(cell);
   }

   private Cell getFreeRandomCell() {
      return freeCells.remove(randomGenerator.nextInt(freeCells.size()));
   }

   public boolean move(Directions direction) {
      Movement movement = getMovementFactory().getMovement(direction);
      boolean isMoved = movement.move(this);
      if (isMoved)
         createTile();
      return isMoved;
   }

   private MovementFactory getMovementFactory() {
      if (movementFactory == null) {
         movementFactory = new MovementFactory();
      }
      return movementFactory;
   }

   public Tile[][] getTiles() {
      return tiles;
   }

   public List<Cell> getFreeCells() {
      return freeCells;
   }

   public boolean isGameOver() {
      return freeCells.isEmpty() && !availableMerges();
   }

   private boolean availableMerges() {
      for (int column = 0; column < DIMENSION; column++) {
         for (int row = 0; row < DIMENSION; row++) {
            if (tiles[column][row] != null && isMergeAvailable(column, row)) {
               return true;
            }
         }
      }
      return false;
   }

   private boolean isMergeAvailable(int column, int row) {
      Tile tile = tiles[column][row];
      int weight = tile.getWeight();
      return isLeftMergeAvailable(weight, column, row)
              || isRightMergeAvailable(weight, column, row)
              || isUpMergeAvailable(weight, column, row)
              || isDownMergeAvailable(weight, column, row);
   }

   private boolean isLeftMergeAvailable(int weight, int column, int row) {
      return column > 0 && isTileMayBeMerged(weight, tiles[column - 1][row]);
   }

   private boolean isRightMergeAvailable(int weight, int column, int row) {
      return column < DIMENSION - 1 && isTileMayBeMerged(weight, tiles[column + 1][row]);
   }

   private boolean isUpMergeAvailable(int weight, int column, int row) {
      return row > 0 && isTileMayBeMerged(weight, tiles[column][row - 1]);
   }

   private boolean isDownMergeAvailable(int weight, int column, int row) {
      return row < DIMENSION - 1 && isTileMayBeMerged(weight, tiles[column][row + 1]);
   }

   private boolean isTileMayBeMerged(int weight, Tile tile) {
      return tile != null && tile.getWeight() == weight;
   }

}
