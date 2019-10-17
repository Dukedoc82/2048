package com.dyukov.games.tzfe.model.field;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Random;

/**
 * @author Dmitry_Dyukov
 * @created on 10/10/2019
 */
public class Tile {

   private static final double TWO_WEIGHT_CREATION_PROBABILITY = 0.8d;

   private int weight;
   private boolean merged = false;

   public Tile() {
      if (new Random().nextDouble() > TWO_WEIGHT_CREATION_PROBABILITY) {
         weight = 4;
      } else {
         weight = 2;
      }
   }

   public Tile(int weight) {
      this.weight = weight;
   }

   public Tile(int weight, boolean merged) {
      this(weight);
      this.merged = merged;
   }

   public void setWeight(int weight) {
      this.weight = weight;
   }

   public int getWeight() {
      return weight;
   }

   public boolean isMerged() {
      return merged;
   }

   public void setMerged(boolean merged) {
      this.merged = merged;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;

      if (o == null || getClass() != o.getClass())
         return false;

      Tile tile = (Tile) o;

      return new EqualsBuilder()
              .append(weight, tile.weight)
              .isEquals();
   }

   @Override
   public int hashCode() {
      return new HashCodeBuilder(17, 37)
              .append(weight)
              .toHashCode();
   }

   @Override
   public String toString() {
      return "Tile{" + "weight=" + weight + '}';
   }
}
