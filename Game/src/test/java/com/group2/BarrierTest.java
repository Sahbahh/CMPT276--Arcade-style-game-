package com.group2;
import static org.junit.Assert.*;

import com.group2.entities.*;

import org.junit.*;

/**
 * Unit test for Barrier.
 */
public class BarrierTest {

    @Test
    public void barrierConstructorTest(){
        SpriteSheet spriteSheet = new SpriteSheet();
        barrier b = new barrier(0,0,spriteSheet);
        assertEquals(0,b.getX());
        assertEquals(0,b.getY());

    }
}