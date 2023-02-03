package com.group2.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * class to initialize and create a level select menu feature
 */
public class LevelSelect {

	private Rectangle menuButton = new Rectangle (0, 500, 300, 100);
	private Rectangle lvl1Button = new Rectangle (0, 200, 300, 100);
	private Rectangle lvl2Button = new Rectangle (0, 325, 300, 100);
	private Rectangle lvl3Button = new Rectangle (500, 200, 300, 100);
	private Rectangle lvl4Button = new Rectangle (500, 325, 300, 100);
	
	/**
	 * draws levelSelect
	 * @param g graphics
	 */
	public void render(Graphics2D g) {
		Font font = new Font("arial", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(Color.white);
		FontMetrics metrics = g.getFontMetrics(font);
		g.drawString("Select a Level", 1024 / 2 - metrics.stringWidth("YOU WIN") / 2, 100);
		g.setColor(Color.lightGray);
		g.fill(menuButton);
		g.fill(lvl1Button);
		g.fill(lvl2Button);
		g.fill(lvl3Button);
		g.fill(lvl4Button);
		g.setColor(Color.black);
		g.drawString("Main Menu", menuButton.x + 10, menuButton.y + 70);
		g.drawString("Level 1", lvl1Button.x + 10, lvl1Button.y + 70);
		g.drawString("Level 2", lvl2Button.x + 10, lvl2Button.y + 70);
		g.drawString("Level 3", lvl3Button.x + 10, lvl3Button.y + 70);
		g.drawString("Level 4", lvl4Button.x + 10, lvl4Button.y + 70);
	}
}