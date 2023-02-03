package com.group2.entities;

import com.group2.SpriteSheet;

/**
 * If the main character moves to a cell that contains a punishment, it will be penalized by the amount of the punishment
 */
public class punishment extends entity{ 
    /**
     * constructor for punishment
     * @param x specified x position of character
     * @param y specified y position of character
     * @param score amount punishment is worth
     * @param spriteSheet spritesheet to be used for rendering
     */
    public punishment(int x, int y, int score, SpriteSheet spriteSheet){ 
        super(x,y); 
        this.setScore(score);
        this.image = spriteSheet.getSprite(4,0);
    }
}
