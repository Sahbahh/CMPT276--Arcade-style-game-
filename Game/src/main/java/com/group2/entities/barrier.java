package com.group2.entities;

import com.group2.SpriteSheet;

/**
 * barrier which blocks the movement of the main character and enemies to those cells
 */
public class barrier extends entity{

    /**
     * constructor for barrier which blocks the movement of the main character and enemies to those cells
     * @param x specified x position of character
     * @param y specified y position of character
     * @param spriteSheet used to generate the specified userCharacter sprite when rendering
     */
    public barrier(int x, int y, SpriteSheet spriteSheet) {
        super(x, y);
        this.image = spriteSheet.getSprite(1, 0);
    } 
}
