package com.group2.entities;

import com.group2.SpriteSheet;

/**
 * When the game starts, the board is populated with multiple regular rewards.
 * The player has to collect all regular rewards to win game
 */
public class regularReward extends entity{
   /**
    * constructor for regularReward
    * @param x specified x position of character
    * @param y specified y position of character
    * @param score amount punishment is worth
    * @param spriteSheet spritesheet to be used for rendering
    */
   public regularReward(int x, int y, int score, SpriteSheet spriteSheet){ 
      super(x,y); 
      this.setScore(score); 
      this.image = spriteSheet.getSprite(5, 0);
   }
}
