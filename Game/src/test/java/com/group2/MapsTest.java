package com.group2;

import static org.junit.Assert.*;

import org.junit.*;

import com.group2.managers.entityManager;
/**
 * Unit test for the maps class.
 */
public class MapsTest 
{
	Maps map;
	SpriteSheet ss;
	entityManager em;
	
	@Before
	public void setup() {
		em = new entityManager();
		ss = new SpriteSheet();
		map = new Maps(ss);
	}
	
	@Test
	public void constructorTest() {
		assertNotNull(map);
	}
	
	@Test
	public void mapDimensionTest() {
		assertTrue("mapWidth returns non positive value.", map.mapWidth() > 0);
		assertTrue("mapHeight returns non positive value.", map.mapHeight() > 0);
	}
	
	@Test
	public void mapInitTest() {
		map.initMap(em, ss);
		assertTrue("EntityManager was not populated with entities.", em.getList().size() > 0);
	}
}
