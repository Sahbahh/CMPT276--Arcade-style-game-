package com.group2;

import static org.junit.Assert.*;
import java.awt.image.BufferedImage;

import org.junit.*;
/**
 * Unit test for the SpriteSheet class.
 */
public class SpriteSheetTest {
	
	private SpriteSheet ss;
	
	@Before
	public void setup() {
		ss = new SpriteSheet();
	}
	
	@Test
	public void constructorTest() {
		assertNotNull(ss);
	}
	
	@Test
	public void imageTest() {
		BufferedImage image;
		for(int x = 0; x < 6; x++) {
			for(int y = 0; y < 2; y++) {
				image = ss.getSprite(x,y);
				assertNotNull(image);
			}
		}
	}
}
