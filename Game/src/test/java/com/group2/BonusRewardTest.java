package com.group2;

import static org.junit.Assert.*;

import com.group2.entities.*;

import org.junit.*;


/**
 * Unit test for Bonus Reward.
 */
public class BonusRewardTest {


    @Test
    public void BonusRewardConstructorTest(){
        SpriteSheet spriteSheet = new SpriteSheet();

        bonusReward bonus = new bonusReward(0,0,10,spriteSheet);

        assertEquals(0,bonus.getX());
        assertEquals(0,bonus.getY());
        assertEquals(10,bonus.getScore());

    }


    @Test
    public void bonusTikTimerTest(){
        SpriteSheet spriteSheet = new SpriteSheet();

        bonusReward bonus = new bonusReward(0,0,10,spriteSheet);
        for(int i = 0; i<10; i++){
            bonus.update();
            assertEquals(false, bonus.deleteBonusReward());
        }
        bonus.update();
        assertEquals(true, bonus.deleteBonusReward());

    }
}