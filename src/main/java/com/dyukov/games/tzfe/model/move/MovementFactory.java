package com.dyukov.games.tzfe.model.move;

import com.dyukov.games.tzfe.model.field.Directions;

/**
 * @author Dmitry_Dyukov
 * @created on 10/10/2019
 */
public class MovementFactory {

   public Movement getMovement(Directions direction) {
      switch (direction) {
         case UP:
            return new UpMovement();
         case DOWN:
            return new DownMovement();
         case LEFT:
            return new LeftMovement();
         case RIGHT:
            return new RightMovement();
         default:
            throw new IllegalArgumentException();
      }

   }

}
