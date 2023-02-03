package com.group2;
import static org.junit.Assert.*;

import com.group2.entities.*;
import com.group2.managers.entityManager;

import org.junit.*;

public class entityManagerTest {

    private entityManager em;
    private SpriteSheet spriteSheet;
    private entity e;

    @Before
    public void setUp(){
        em = new entityManager();
        spriteSheet = new SpriteSheet();
    }

    @Test
    public void constructorTest(){
        assertNotNull(em);
        assertEquals("entityList Not Empty", em.getList().size(), 0);
    }

    @Test
    public void clearListTest(){
        em.newEntity(0, 0, "bonusReward", spriteSheet);
        em.newEntity(0, 0, "bonusReward", spriteSheet);
        em.newEntity(0, 0, "bonusReward", spriteSheet);
        em.newEntity(0, 0, "bonusReward", spriteSheet);
        em.newEntity(0, 0, "bonusReward", spriteSheet);
        em.clearList();
        assertEquals(0, em.getList().size());
    }

    @Test
    public void newEntityTest(){
        em.newEntity(0, 0, "barrier", spriteSheet);
        assertEquals("entityList not added to", em.getList().size(), 1);
        e = em.getList().get(0);
        assertTrue("entity not of type barrier", e instanceof barrier);
        assertEquals("X", 0, e.getX());
        assertEquals("Y", 0, e.getY());
        em.clearList();

        em.newEntity(0, 0, "bonusReward", spriteSheet);
        assertEquals("entityList not added to", em.getList().size(), 1);
        e = em.getList().get(0);
        assertTrue("entity not of type bonusReward", e instanceof bonusReward);
        assertEquals("X", 0, e.getX());
        assertEquals("Y", 0, e.getY());
        em.clearList();

        em.newEntity(0, 0, "movingEnemy", spriteSheet);
        assertEquals("entityList not added to", em.getList().size(), 1);
        e = em.getList().get(0);
        assertTrue("entity not of type movingEnemy", e instanceof movingEnemy);
        assertEquals("X", 0, e.getX());
        assertEquals("Y", 0, e.getY());
        em.clearList();

        em.newEntity(0, 0, "punishment", spriteSheet);
        assertEquals("entityList not added to", em.getList().size(), 1);
        e = em.getList().get(0);
        assertTrue("entity not of type punishment", e instanceof punishment);
        assertEquals("X", 0, e.getX());
        assertEquals("Y", 0, e.getY());
        em.clearList();

        em.newEntity(0, 0, "regularReward", spriteSheet);
        assertEquals("entityList not added to", em.getList().size(), 1);
        e = em.getList().get(0);
        assertTrue("entity not of type regularReward", e instanceof regularReward);
        assertEquals("X", 0, e.getX());
        assertEquals("Y", 0, e.getY());
        em.clearList();

        em.newEntity(0, 0, "userCharacter", spriteSheet);
        assertEquals("entityList not added to", em.getList().size(), 1);
        e = em.getList().get(0);
        assertTrue("entity not of type userCharacter", e instanceof userCharacter);
        assertEquals("X", 0, e.getX());
        assertEquals("Y", 0, e.getY());
        em.clearList();

        em.newEntity(0, 0, "exitCell", spriteSheet);
        assertEquals("entityList not added to", em.getList().size(), 1);
        e = em.getList().get(0);
        assertTrue("entity not of type exitCell", e instanceof exitCell);
        assertEquals("X", 0, e.getX());
        assertEquals("Y", 0, e.getY());
        em.clearList();
    }

    @Test
    public void findEntityTest(){
        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "punishment", spriteSheet);
        assertTrue("findEntity not returning correct object", em.findEntity("punishment") instanceof punishment);
        em.clearList();

        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "userCharacter", spriteSheet);
        assertTrue("findEntity not returning correct object", em.findEntity("userCharacter") instanceof userCharacter);
        em.clearList();

        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "movingEnemy", spriteSheet);
        assertTrue("findEntity not returning correct object", em.findEntity("movingEnemy") instanceof movingEnemy);
        em.clearList();

        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "bonusReward", spriteSheet);
        assertTrue("findEntity not returning correct object", em.findEntity("bonusReward") instanceof bonusReward);
        em.clearList();

        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "regularReward", spriteSheet);
        assertTrue("findEntity not returning correct object", em.findEntity("regularReward") instanceof regularReward);
        em.clearList();

        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "exitCell", spriteSheet);
        assertTrue("findEntity not returning correct object", em.findEntity("exitCell") instanceof exitCell);
        em.clearList();

        em.newEntity(0, 0, "punishment", spriteSheet);
        em.newEntity(0, 0, "punishment", spriteSheet);
        em.newEntity(0, 0, "barrier", spriteSheet);
        assertTrue("findEntity not returning correct object", em.findEntity("barrier") instanceof barrier);
        em.clearList();
    }

    @Test
    public void removeFromListTest(){
        em.newEntity(0, 0, "barrier", spriteSheet);
        em.newEntity(0, 0, "punishment", spriteSheet);
        em.removeFromList(em.findEntity("punishment"));
        assertNull("findEntity not returning correct object", em.findEntity("punishment"));
        em.clearList();
    }

    @Test
    public void updateTest(){
        //Testing for bonusReward
        em.newEntity(0, 0, "bonusReward", spriteSheet);
        bonusReward e = (bonusReward)em.findEntity("bonusReward");
        for(int i = 0; i < 10; i++)
            e.update();
        em.update();
        assertEquals("List not empty", 0, em.getList().size());
        em.clearList();
    }
}
