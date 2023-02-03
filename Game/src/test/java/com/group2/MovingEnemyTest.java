package com.group2;

import static org.junit.Assert.*;

import com.group2.entities.*;
import com.group2.managers.entityManager;

import org.junit.*;

/**
 * Unit test for Moving enemy.
 */
public class MovingEnemyTest {

    private entityManager em;
    private SpriteSheet spriteSheet;
    private movingEnemy enemy;

    @Before
    public void setUp() {
        spriteSheet = new SpriteSheet();
        em = new entityManager();

    }

    @Test
    public void MovingEnemyConstructorTest(){
        enemy = new movingEnemy(1, 1, em, spriteSheet);

        assertEquals(1,enemy.getX());
        assertEquals(1,enemy.getY());

    }

    public void characterCollisionTest(){
        em.newEntity(4,10,"userCharacter",spriteSheet);
        userCharacter ch = (userCharacter) em.findEntity("userCharacter");

        enemy = new movingEnemy(5, 10, em, spriteSheet);
        ch.setScore(100);
        enemy.update();
        assertEquals(0, ch.getScore());

    }

    @Test
    public void findShortestPathTest(){

        enemy = new movingEnemy(1, 1, em, spriteSheet);
        enemy.setXBound(100);
        enemy.setYBound(100);

        em.newEntity(2,2,"userCharacter",spriteSheet);
        userCharacter ch = (userCharacter) em.findEntity("userCharacter");

        //The charachter is straight up from the enemy
        enemy.setPosition(5,5);
        ch.setPosition(5,2);
        enemy.update();
        enemy.update();
        assertEquals(5,enemy.getX());
        assertEquals(4,enemy.getY());

        //straight down
        enemy.setPosition(5,5);
        ch.setPosition(5,7);
        // System.out.println("1 Enemy: "+ enemy.getX()+enemy.getY()+"Character: "+ ch.getX()+ch.getY());
        enemy.update();
        // System.out.println("2 Enemy: "+ enemy.getX()+enemy.getY()+"Character: "+ ch.getX()+ch.getY());
        enemy.update();
        assertEquals(5,enemy.getX());
        assertEquals(6,enemy.getY());

        //straight right
        enemy.setPosition(5,5);
        ch.setPosition(7,5);
        enemy.update();
        enemy.update();
        assertEquals(6,enemy.getX());
        assertEquals(5,enemy.getY());

        //straight left
        enemy.setPosition(5,5);
        ch.setPosition(3,5);
        enemy.update();
        enemy.update();
        assertEquals(4,enemy.getX());
        assertEquals(5,enemy.getY());

        //The charachter is upper right from the enemy
        enemy.setPosition(5,5);
        ch.setPosition(7,4);
        enemy.update();
        enemy.update();
        assertEquals(6,enemy.getX());
        assertEquals(5,enemy.getY());

        //The charachter is upper left from the enemy
        enemy.setPosition(5,5);
        ch.setPosition(4,3);
        enemy.update();
        enemy.update();
        assertEquals(4,enemy.getX());
        assertEquals(5,enemy.getY());

        //The charachter is lower right from the enemy
        enemy.setPosition(5,5);
        ch.setPosition(6,8);
        enemy.update();
        enemy.update();
        assertEquals(6,enemy.getX());
        assertEquals(5,enemy.getY());

        //The charachter is lower left from the enemy
        enemy.setPosition(5,5);
        ch.setPosition(3,8);
        enemy.update();
        enemy.update();
        assertEquals(4,enemy.getX());
        assertEquals(5,enemy.getY());


        em.newEntity(10, 10, "barrier", spriteSheet);
        em.newEntity(29, 30, "barrier", spriteSheet);
        em.newEntity(30, 29, "barrier", spriteSheet);
        em.newEntity(31, 30, "barrier", spriteSheet);
        em.newEntity(30, 31, "barrier", spriteSheet);


        /*
        //The charachter is straight up from the enemy with the barrier
        enemy.setPosition(10,11);
        ch.setPosition(10,9);
         System.out.println("1 Enemy: "+ enemy.getX()+enemy.getY()+"Character: "+ ch.getX()+ch.getY());
        enemy.update();
        System.out.println("2 Enemy: "+ enemy.getX()+enemy.getY()+"Character: "+ ch.getX()+ch.getY());
        enemy.update();
        System.out.println("3 Enemy: "+ enemy.getX()+enemy.getY()+"Character: "+ ch.getX()+ch.getY());
        assertEquals(11,enemy.getX());
        assertEquals(11,enemy.getY());

         */


        // u, 1, 0
        // 1, 0, 1
        // e, 1, 0
        enemy.setPosition(29,31);
        ch.setPosition(29,29);
        enemy.update();
        enemy.update();
        assertEquals(29,enemy.getX());
        assertEquals(31,enemy.getY());

        // 0, 1, 0
        // 1, 0, 1
        // e, 1, u
        enemy.setPosition(29,31);
        ch.setPosition(31,31);
        enemy.update();
        enemy.update();
        assertEquals(28,enemy.getX());
        assertEquals(31,enemy.getY());

        // 0, 1, u
        // 1, 0, 1
        // e, 1, 0
        enemy.setPosition(29,31);
        ch.setPosition(31,29);
        enemy.update();
        enemy.update();
        assertEquals(28,enemy.getX());
        assertEquals(31,enemy.getY());

        // e, 1, u
        // 1, 0, 1
        // 0, 1, 0
        enemy.setPosition(29,29);
        ch.setPosition(31,29);
        enemy.update();
        enemy.update();
        assertEquals(29,enemy.getX());
        assertEquals(28,enemy.getY());

        // e, 1, 0
        // 1, 0, 1
        // 0, 1, u
        enemy.setPosition(29,29);
        ch.setPosition(31,31);
        enemy.update();
        enemy.update();
        assertEquals(29,enemy.getX());
        assertEquals(28,enemy.getY());

        // e, 1, 0
        // 1, 0, 1
        // u, 1, 0
        enemy.setPosition(29,29);
        ch.setPosition(29,31);
        enemy.update();
        enemy.update();
        assertEquals(29,enemy.getX());
        assertEquals(28,enemy.getY());

        // u, 1, e
        // 1, 0, 1
        // 0, 1, 0
        enemy.setPosition(31,29);
        ch.setPosition(29,29);
        enemy.update();
        enemy.update();
        assertEquals(31,enemy.getX());
        assertEquals(28,enemy.getY());

        // 0, 1, e
        // 1, 0, 1
        // u, 1, 0
        enemy.setPosition(31,29);
        ch.setPosition(29,31);
        enemy.update();
        enemy.update();
        assertEquals(31,enemy.getX());
        assertEquals(28,enemy.getY());

        // 0, 1, e
        // 1, 0, 1
        // 0, 1, u
        enemy.setPosition(31,29);
        ch.setPosition(31,31);
        enemy.update();
        enemy.update();
        assertEquals(31,enemy.getX());
        assertEquals(28,enemy.getY());

        // 0, 1, u
        // 1, 0, 1
        // 0, 1, e
        enemy.setPosition(31,31);
        ch.setPosition(31,29);
        enemy.update();
        enemy.update();
        assertEquals(31,enemy.getX());
        assertEquals(31,enemy.getY());

        // u, 1, 0
        // 1, 0, 1
        // 0, 1, e
        enemy.setPosition(31,31);
        ch.setPosition(29,29);
        enemy.update();
        enemy.update();
        assertEquals(32,enemy.getX());
        assertEquals(31,enemy.getY());

        // 0, 1, 0
        // 1, 0, 1
        // u, 1, e
        enemy.setPosition(31,31);
        ch.setPosition(29,29);
        enemy.update();
        enemy.update();
        assertEquals(32,enemy.getX());
        assertEquals(31,enemy.getY());

    }
}