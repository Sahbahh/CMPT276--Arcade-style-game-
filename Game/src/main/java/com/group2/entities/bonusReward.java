package com.group2.entities;

import com.group2.SpriteSheet;

/**
 * The bonus rewards are not necessary for finishing the game, but contain a higher reward compared to the regular rewards and collecting them improves the final score of the player. 
 * A bonus reward appears randomly during the game, and disappears after a while (few ticks).
 */
public class bonusReward extends entity
{
    /**
     * number of ticks that this instance of bonusReward has been active and on screen
     */
    private int tikTimer;

    /**
     * constructor for bonusReward
     * @param x specified x position of entity
     * @param y specified y position of entity
     * @param score amount a bonus reward is worth which affects the userCharacter
     * @param spriteSheet used to generate the specified sprite when rendering
     */
    public bonusReward(int x, int y, int score, SpriteSheet spriteSheet) {
        super(x, y);
        this.setScore(score);
        this.image = spriteSheet.getSprite(2, 0);
    }

    /**
     * updates the bonusReward timer each tic
     */
    public void update(){
        this.incrementTimer();
    }

    /**
     * returns true if this instance of bonusReward is to be removed because it has been on screen for n ticks
     * @return timer amount
     */
    public boolean deleteBonusReward(){ return tikTimer > 10; }

    /**
     * increments timer in bonusReward
     */
    private void incrementTimer(){ tikTimer++; }
}
