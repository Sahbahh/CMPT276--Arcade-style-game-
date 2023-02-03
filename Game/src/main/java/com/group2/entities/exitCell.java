package com.group2.entities;
import com.group2.SpriteSheet;

/**
 * The player can win the game by guiding the main character to the end cell after collecting all regular rewards.
 */
public class exitCell extends entity{

    /**
     * constructor for exitCell
     * @param x specified x position of entity
     * @param y specified y position of entity
     * @param score score of entity
     * @param spriteSheet to render which sprite to use
     */
    public exitCell(int x, int y, int score, SpriteSheet spriteSheet) {
        super(x, y);
        this.image = spriteSheet.getSprite(0, 1);
    }
    
}
