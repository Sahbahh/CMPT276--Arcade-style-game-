package com.group2;

import static org.junit.Assert.*;

import com.group2.entities.*;
import org.junit.*;

/**
 * Unit test for Regular Rewards.
 */
public class RegularRewardsTest {

    @Test
    public void RegularRewardsConstructorTest(){

        SpriteSheet spriteSheet = new SpriteSheet();
        regularReward rReward = new regularReward(0,0,10,spriteSheet);

        assertEquals(0,rReward.getX());
        assertEquals(0,rReward.getY());
        assertEquals(10,rReward.getScore());

    }
}