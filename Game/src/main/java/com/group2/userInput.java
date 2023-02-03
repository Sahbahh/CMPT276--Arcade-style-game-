package com.group2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.group2.entities.userCharacter;
import com.group2.managers.entityManager;

/**
 * This class detects all input from the keyboard
 */
public class userInput extends KeyAdapter implements MouseListener{
    private entityManager entities; 
	private Maps maps;
	final int UP = 0;
    final int DOWN = 1;
    final int RIGHT = 2;
    final int LEFT = 3;
	
	/**
	 * userInput constructor
	 * @param entities passes entityManager
	 * @param maps passes maps
	 */
    public userInput(entityManager entities, Maps maps){
    	this.entities = entities;
		this.maps = maps;
    }

	/**
	 * sets keypressed
	 */
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        userCharacter ch = (userCharacter)entities.findEntity("userCharacter");

		if(key == KeyEvent.VK_W) {
            ch.setUpHeld(true);
        }else if(key == KeyEvent.VK_S){
			ch.setDownHeld(true);
        }else if(key == KeyEvent.VK_D){
			ch.setRightHeld(true);
        }else if(key == KeyEvent.VK_A){
			ch.setLeftHeld(true);
		}
    }

	/**
	 * sets when key released
	 */
    public void keyReleased(KeyEvent e){
		if(App.state == App.STATE.GAME){
        	int key = e.getKeyCode();

        	userCharacter ch = (userCharacter)entities.findEntity("userCharacter");

			if(key == KeyEvent.VK_W) {
        	   ch.setUpHeld(false);
      	  }else if(key == KeyEvent.VK_S){
				ch.setDownHeld(false);
      	  }else if(key == KeyEvent.VK_D){
				ch.setRightHeld(false);
      	  }else if(key == KeyEvent.VK_A){
				ch.setLeftHeld(false);
		}
		}
    }   

    @Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if(App.state == App.STATE.MAINMENU) {
			if(mx >= 0 && mx <= 300 && my >= 200 && my <= 300) {
				maps.setCurrentMap(0);
				App.state = App.STATE.CHARACTERSELECT;
			}
			else if(mx >= 0 && mx <= 300 && my >= 325 && my <= 425) {
				App.state = App.STATE.LEVELSELECT;
			}
			else if(mx >= 0 && mx <= 300 && my >= 450 && my <= 550) {
				App.state = App.STATE.INSTRUCTIONS;
			}
			else if(mx >= 0 && mx <= 300 && my >= 575 && my <= 675) {
				App.state = App.STATE.QUIT;
			}
		}
		
		else if(App.state == App.STATE.INSTRUCTIONS || App.state == App.STATE.GAMEOVER) {
			if(mx >= 0 && mx <= 300 && my >= 500 && my <= 600) {
				App.state = App.STATE.MAINMENU;
			}
		}

		else if(App.state == App.STATE.CHARACTERSELECT) {
			if(mx >= 0 && mx <= 300 && my >= 200 && my <= 300) {
				App.state = App.STATE.SETUP;
				entities.charSprite = 1;
			}
			else if(mx >= 0 && mx <= 300 && my >= 325 && my <= 425) {
				App.state = App.STATE.SETUP;
				entities.charSprite = 2;
			}
			else if(mx >= 0 && mx <= 300 && my >= 450 && my <= 550) {
				App.state = App.STATE.SETUP;
				entities.charSprite = 3;
			}
			else if(mx >= 0 && mx <= 300 && my >= 575 && my <= 675) {
				App.state = App.STATE.SETUP;
				entities.charSprite = 4;
			}
			else if(mx >= 500 && mx <= 800 && my >= 200 && my <= 300) {
				App.state = App.STATE.SETUP;
				entities.charSprite = 0;
			}
		}
		else if(App.state == App.STATE.LEVELSELECT) {
			
			if(mx >= 0 && mx <= 300 && my >= 500 && my <= 600) {
				App.state = App.STATE.MAINMENU;
			}
			else if(mx >= 0 && mx <= 300 && my >= 200 && my <= 300) {
				maps.setCurrentMap(0);
				App.state = App.STATE.CHARACTERSELECT;
			}
			else if(mx >= 0 && mx <= 300 && my >= 325 && my <= 425) {
				maps.setCurrentMap(1);
				App.state = App.STATE.CHARACTERSELECT;
			}
			else if(mx >= 500 && mx <= 800 && my >= 200 && my <= 300) {
				maps.setCurrentMap(2);
				App.state = App.STATE.CHARACTERSELECT;
			}
			else if(mx >= 500 && mx <= 800 && my >= 325 && my <= 425) {
				maps.setCurrentMap(3);
				App.state = App.STATE.CHARACTERSELECT;
			}
		}
		else if( App.state == App.STATE.GAMEWON) {
			if(mx >= 0 && mx <= 300 && my >= 500 && my <= 600) {
				App.state = App.STATE.MAINMENU;
			}
			else if(mx >= 0 && mx <= 300 && my >= 375 && my <= 475) {
				maps.nextMap();
				App.state = App.STATE.SETUP;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

		
	}   
}
