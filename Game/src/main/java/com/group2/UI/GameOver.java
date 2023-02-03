package com.group2.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.group2.managers.entityManager;

/**
 * class to initialize and create a gameover feature
 */
public class GameOver {
	
	/** sets menubutton for gameover */
	public Rectangle menuButton = new Rectangle (0, 500, 300, 100);
	
	private entityManager entities;
	private HUD hud;

	/**
	 * Gameover constructor
	 * @param entities entity list to find character
	 * @param hud hud for overlay menu
	 */
	public GameOver(entityManager entities, HUD hud) {
		this.entities = entities;
		this.hud = hud;
	}

	/**
	 * renders gameOver menu
	 * @param g graphics
	 */
	public void render(Graphics2D g) {
		Font font = new Font("arial", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(Color.white);
		FontMetrics metrics = g.getFontMetrics(font);
		g.drawString("GAME OVER", 1024 / 2 - metrics.stringWidth("GAME OVER") / 2, 100);
		g.drawString("Code Quality: " + entities.findEntity("userCharacter").getScore(), 1024 - 500, 300);
		g.drawString("TIME: " + hud.getTimerString(), 1024 - 500, 500);
		g.setColor(Color.lightGray);
		g.fill(menuButton);
		g.setColor(Color.black);
		g.drawString("Main Menu", menuButton.x + 10, menuButton.y + 70);
	}
}
