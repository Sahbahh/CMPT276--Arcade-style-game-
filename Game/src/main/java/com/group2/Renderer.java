package com.group2;

import java.awt.Canvas;
import java.awt.Dimension;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.group2.managers.entityManager;

/**
 * renderer class for game
 */
public class Renderer extends Canvas{
	private static final long serialVersionUID = 1L;
	/** frame for game */
	private JFrame frame;
	/** user input for game */
	private userInput userInput;
	
	/**
	 * renderer constructor
	 * @param width sets map width
	 * @param height sets map height
	 * @param title sets title
	 * @param entities passes entity manager
	 * @param maps maps class
	 */
	public Renderer(int width, int height, String title, entityManager entities, Maps maps) {	
		this.frame = new JFrame();
		Dimension size = new Dimension(width, height);
		this.setPreferredSize(size);
		frame.setTitle(title);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		userInput = new userInput(entities, maps);
		this.addKeyListener(userInput);
		this.addMouseListener(userInput);
	}
	
	/**
	 * @return bufferStrategy
	 */
	public BufferStrategy getBuffer() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			bs = this.getBufferStrategy();
		}
		return bs;
	}

	/**
	 * destroys frame
	 */
	public void destroyFrame() {
		frame.dispose();
		frame = null;
	}
}	
