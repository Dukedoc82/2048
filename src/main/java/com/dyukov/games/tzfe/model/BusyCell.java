package com.dyukov.games.tzfe.model;

import com.dyukov.games.tzfe.model.field.Cell;
import com.dyukov.games.tzfe.model.field.Tile;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Dmitry_Dyukov
 * @created on 10/10/2019
 */
public class BusyCell {

   private Cell cell;
   private Tile tile;

   private BusyCell() {}

   public BusyCell(Cell cell, Tile tile) {
      this.cell = cell;
      this.tile = tile;
   }

   public Cell getCell() {
      return cell;
   }

   public void setCell(Cell cell) {
      this.cell = cell;
   }

   public Tile getTile() {
      return tile;
   }

   public void setTile(Tile tile) {
      this.tile = tile;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;

      if (o == null || getClass() != o.getClass())
         return false;

      BusyCell busyCell = (BusyCell) o;

      return new EqualsBuilder().append(cell, busyCell.cell).append(tile, busyCell.tile).isEquals();
   }

   @Override
   public int hashCode() {
      return new HashCodeBuilder(17, 37).append(cell).append(tile).toHashCode();
   }
}
