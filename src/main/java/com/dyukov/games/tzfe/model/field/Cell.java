package com.dyukov.games.tzfe.model.field;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Dmitry_Dyukov
 * @created on 10/10/2019
 */
public class Cell {

   private int x;
   private int y;

   private Cell() {
      super();
   }

   public Cell(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;

      if (o == null || getClass() != o.getClass())
         return false;

      Cell cell = (Cell) o;

      return new EqualsBuilder().append(x, cell.x).append(y, cell.y).isEquals();
   }

   @Override
   public int hashCode() {
      return new HashCodeBuilder(17, 37).append(x).append(y).toHashCode();
   }
}
