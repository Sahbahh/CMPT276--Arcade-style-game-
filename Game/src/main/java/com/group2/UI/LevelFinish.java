package com.group2.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.group2.managers.entityManager;

/**
 * class to initialize and create a level finish menu feature
 */
public class LevelFinish {

	private Rectangle menuButton = new Rectangle (0, 500, 300, 100);
	private Rectangle nextLevelButton = new Rectangle (0, 375, 300, 100);
	private entityManager entities;
	private HUD hud;
	
	/**
	 * levelfinish constructor
	 * @param entities passes entity manager
	 * @param hud timer
	 */
	public LevelFinish(entityManager entities, HUD hud) {
		this.entities = entities;
		this.hud = hud;
	}
	
	/**
	 * draws LevelFinish
	 * @param g graphics
	 */
	public void render(Graphics2D g) {
		Font font = new Font("arial", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(Color.white);
		FontMetrics metrics = g.getFontMetrics(font);
		g.drawString("YOU WIN", 1024 / 2 - metrics.stringWidth("YOU WIN") / 2, 100);
		g.drawString("Code Quality: " + entities.findEntity("userCharacter").getScore(), 1024 - 500, 300);
		g.drawString("TIME: " + hud.getTimerString(), 1024 - 500, 500);
		g.setColor(Color.lightGray);
		g.fill(menuButton);
		g.fill(nextLevelButton);
		g.setColor(Color.black);
		g.drawString("Main Menu", menuButton.x + 10, menuButton.y + 70);
		g.drawString("Next Level", nextLevelButton.x + 10, nextLevelButton.y + 70);
	}
}
