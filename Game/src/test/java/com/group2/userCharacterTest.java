package com.group2;
import static org.junit.Assert.*;

import com.group2.entities.userCharacter;
import com.group2.managers.entityManager;

import org.junit.*;

public class userCharacterTest {

    private entityManager em;
    private SpriteSheet spriteSheet;
    private userCharacter ch;
    private int x = 5;
    private int y = 10;

    @Before
    public void setUp(){
        em = new entityManager();
        spriteSheet = new SpriteSheet();
        ch = new userCharacter(x, y, em, spriteSheet, 0);
        ch.setXBound(15);
        ch.setYBound(15);
    }

    @Test
    public void characterConstructorTest(){
        assertNotNull(ch);
        assertEquals(x, ch.getX());
        assertEquals(y, ch.getY());
        assertFalse(ch.getUpHeld());
        assertFalse(ch.getDownHeld());
        assertFalse(ch.getRightHeld());
        assertFalse(ch.getLeftHeld());
    }

    @Test
    public void upHeldTest(){
        ch.setUpHeld(true);
        assertTrue("setUpHeld() did not change upHeld", ch.getUpHeld());
        ch.setUpHeld(false);
    }

    @Test
    public void downHeldTest(){
        ch.setDownHeld(true);
        assertTrue("setDownHeld() did not change downHeld", ch.getDownHeld());
        ch.setDownHeld(false);
    }

    @Test
    public void rightHeldTest(){
        ch.setRightHeld(true);
        assertTrue("setRightHeld() did not change rightHeld", ch.getRightHeld());
        ch.setRightHeld(false);
    }

    @Test
    public void leftHeldTest(){
        ch.setLeftHeld(true);
        assertTrue("setLeftHeld() did not change leftHeld", ch.getLeftHeld());
        ch.setLeftHeld(false);
    }

    @Test
    public void moveTest(){
        ch.setUpHeld(true);
        ch.move();
        assertEquals("moving up changed the x pos", x, ch.getX());
        assertEquals("moving up did not correctly change the y pos", y-1, ch.getY());
        ch.setUpHeld(false);

        ch.setDownHeld(true);
        ch.move();
        assertEquals("moving down changed the x pos", x, ch.getX());
        assertEquals("moving down did not correctly change the y pos", y, ch.getY());
        ch.setDownHeld(false);

        ch.setRightHeld(true);
        ch.move();
        assertEquals("moving right did not correctly change the x pos", x+1, ch.getX());
        assertEquals("moving right changed the y pos", y, ch.getY());
        ch.setRightHeld(false);

        ch.setLeftHeld(true);
        ch.move();
        assertEquals("moving left did not correctly change the x pos", x, ch.getX());
        assertEquals("moving right changed the y pos", y, ch.getY());
        ch.setLeftHeld(false);
    }

    @Test
    public void testUpdate(){
        int score = 0;
        assertEquals(score, ch.getScore());

        em.newEntity(x, y-1, "bonusReward", spriteSheet);
        ch.setUpHeld(true);
        ch.update();
        assertNull(em.findEntity("bonusReward"));
        ch.setUpHeld(false);
        score += 175;
        assertEquals(score, ch.getScore());

        em.newEntity(x, y, "regularReward", spriteSheet);
        ch.setDownHeld(true);
        ch.update();
        assertNull(em.findEntity("regularReward"));
        ch.setDownHeld(false);
        score += 100;
        assertEquals(score, ch.getScore());

        em.newEntity(x, y-1, "punishment", spriteSheet);
        ch.setUpHeld(true);
        ch.update();
        assertNull(em.findEntity("punishment"));
        ch.setUpHeld(false);
        score -= 20;
        assertEquals(score, ch.getScore());
    }
}
