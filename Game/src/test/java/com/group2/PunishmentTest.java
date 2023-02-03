package com.group2;

import static org.junit.Assert.*;

import com.group2.entities.*;

import org.junit.*;

/**
 * Unit test for Punishment.
 */
public class PunishmentTest {

    @Test
    public void PunishmentConstructorTest(){

        SpriteSheet spriteSheet = new SpriteSheet();

        punishment pNegative = new punishment(0,0,-10,spriteSheet);
        assertEquals(0,pNegative.getX());
        assertEquals(0,pNegative.getY());
        assertEquals(-10,pNegative.getScore());

        punishment pPositive = new punishment(0,0,10,spriteSheet);
        assertEquals(0,pPositive.getX());
        assertEquals(0,pPositive.getY());
        assertEquals(10,pPositive.getScore());

    }
}