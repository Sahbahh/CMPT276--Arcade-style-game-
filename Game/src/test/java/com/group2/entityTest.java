package com.group2;
import static org.junit.Assert.*;
import org.junit.*;

import com.group2.entities.*;
import com.group2.managers.entityManager;



public class entityTest {

    private entityManager em;
    private SpriteSheet spriteSheet;
    private entity e;

    @Before
    public void setUp(){
        em = new entityManager();
        spriteSheet = new SpriteSheet();
        //e = new userCharacter(2,2,em,spriteSheet);
    }

    @Test
    public void constructorTest(){
        e = new userCharacter(2,2,em,spriteSheet, 0);
        assertNotNull(e);
        assertEquals(2, e.getX());
        assertEquals(2, e.getY());
    }

    @Test
    public void setPositionTest(){
        e = new userCharacter(2,2,em,spriteSheet, 0);
        //general case
        e.setPosition(4,6);
        assertEquals(4, e.getX());
        assertEquals(6, e.getY());
        //robustness check
        e.setPosition(-1,-7);
        assertEquals(-1, e.getX());
        assertEquals(-7, e.getY());

    }


    /**This test assumes constructors for all entity classes are working*/
    //moveTest should be done with movingEnemy because userCharacter overwrites entity move function
    //UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3
    @Test
    public void moveTest(){
        e = new movingEnemy(5,5,em,spriteSheet);
        e.setXBound(10);
        e.setYBound(10);

        assertEquals(5, e.getX());
        assertEquals(5, e.getY());
        e.move(0);
        assertEquals(5, e.getX());
        assertEquals(4, e.getY());
        e.move(3);
        assertEquals(4, e.getX());
        assertEquals(4, e.getY());
        e.move(1);
        assertEquals(4, e.getX());
        assertEquals(5, e.getY());
        e.move(2);
        assertEquals(5, e.getX());
        assertEquals(5, e.getY());

        /**testing branches of canMove: */

        //trying to move out of bounds
        e.setPosition(10,10);
        e.move(1);
        assertEquals(10, e.getX());
        assertEquals(10, e.getY());
        e.move(2);
        assertEquals(10, e.getX());
        assertEquals(10, e.getY());

        e.setPosition(0,0);
        e.move(0);
        assertEquals(0, e.getX());
        assertEquals(0, e.getY());
        e.move(3);
        assertEquals(0, e.getX());
        assertEquals(0, e.getY());

        //trying to move into other entities


        em.newEntity(5, 4, "bonusReward", spriteSheet);
        em.newEntity(6, 4, "punishment", spriteSheet);
        em.newEntity(7, 4, "regularReward", spriteSheet);
        em.newEntity(8, 4, "barrier", spriteSheet);
        em.newEntity(9, 4, "movingEnemy", spriteSheet);

        //bonusReward
        e.setPosition(5,5);
        e.move(0);
        assertEquals(5, e.getX());
        assertEquals(4, e.getY());
        //punishment
        e.setPosition(6,5);
        e.move(0);
        assertEquals(6, e.getX());
        assertEquals(4, e.getY());
        //regularReward
        e.setPosition(7,5);
        e.move(0);
        assertEquals(7, e.getX());
        assertEquals(4, e.getY());
        //barrier
        e.setPosition(8,5);
        e.move(0);
        assertEquals(8, e.getX());
        assertEquals(5, e.getY());
        //movingEnemy
        e.setPosition(9,5);
        e.move(0);
        assertEquals(9, e.getX());
        assertEquals(5, e.getY());

        //trying to break canMove with multiple entities on a tile
        e.setPosition(2,2);
        assertEquals(2, e.getX());
        assertEquals(2, e.getY());

        em.newEntity(2, 1, "bonusReward", spriteSheet);
        em.newEntity(2, 1, "movingEnemy", spriteSheet);
        e.move(0);
        assertEquals(2, e.getX());
        assertEquals(2, e.getY());



    }


    @Test
    public void addScoreTest() {
        e = new userCharacter(5, 5, em, spriteSheet, 0);
        e.setXBound(10);
        e.setYBound(10);

        e.setScore(100);
        e.addScore(1000);
        assertEquals(1100, e.getScore());

        e.addScore(-10);
        assertEquals(1090, e.getScore());
    }

    @Test
    public void findCharacterInListTest() {
        e = new movingEnemy(5, 5, em, spriteSheet);
        e.setXBound(10);
        e.setYBound(10);

        assertNull(e.findCharacterInList(em.getList()));

        em.newEntity(2, 1, "userCharacter", spriteSheet);

        assertNotNull(e.findCharacterInList(em.getList()));

    }

    @Test
    public void collisionTest() {
        e = new movingEnemy(5, 5, em, spriteSheet);
        e.setXBound(10);
        e.setYBound(10);

        entity u = new userCharacter(5, 5, em, spriteSheet, 0);

        assertEquals(true, u.collision(e));
        assertEquals(true, e.collision(u));

        u.setPosition(6,6);

        assertEquals(false, u.collision(e));
        assertEquals(false, e.collision(u));



    }

}