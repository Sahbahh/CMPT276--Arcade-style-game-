package com.group2.entities;

import com.group2.SpriteSheet;
import com.group2.managers.entityManager;

/**
 * a character class to move around the board and collect rewards and punishments
 *  extends entity class
 */
public class userCharacter extends entity
{
    final int UP = 0;
    final int DOWN = 1;
    final int RIGHT = 2;
    final int LEFT = 3;
    private boolean upHeld = false;
    private boolean downHeld = false;
    private boolean rightHeld = false;
    private boolean leftHeld = false;

    /**
     * sets upHeld
     * @param bool bool to set to
     */
    public void setUpHeld(boolean bool){
        upHeld = bool;
    }

    /**
     * gets upHeld
     * @return upHeld bool
     */
    public boolean getUpHeld(){ return upHeld; }

    /**
     * sets downHeld
     * @param bool bool to set to
     */
    public void setDownHeld(boolean bool){
        downHeld = bool;
    }

    /**
     * gets downHeld
     * @return downHeld bool
     */
    public boolean getDownHeld(){ return downHeld; }

    /**
     * sets rightHeld
     * @param bool bool to set to
     */
    public void setRightHeld(boolean bool){
        rightHeld = bool;
    }

    /**
     * gets rightHeld
     * @return bool of rightHeld
     */
    public boolean getRightHeld(){ return rightHeld; }

    /**
     * sets upHeld
     * @param bool bool to set to
     */
    public void setLeftHeld(boolean bool){
        leftHeld = bool;
    }
    
    /**
     * gets leftHeld
     * @return bool of leftHeld
     */
    public boolean getLeftHeld(){ return leftHeld; }

    /**
     * moves the instance of userCharacter depending on what key is held
     */
    public void move(){
        if(upHeld){
            if(canMove(UP))
                this.posY--;
        }
        else if(downHeld){
            if(canMove(DOWN))
                this.posY++;
        }
        else if(rightHeld){
            if(canMove(RIGHT))
                this.posX++;
        }
        else if(leftHeld){
            if(canMove(LEFT))
                this.posX--;
        }
    }

    /**
     * constructor for userCharacter
     * @param x specified x position of character
     * @param y specified y position of character
     * @param manager entitymanager used to allow instance of userCharacter to interact with orth entities
     * @param spriteSheet used to generate the specified userCharacter sprite when rendering
     * @param sprite indicates which character sprite to use
     */
    public userCharacter(int x, int y, entityManager manager, SpriteSheet spriteSheet, int sprite){
    	super(x,y,manager);
    	if(sprite == 0) this.image = spriteSheet.getSprite(0, 0);
    	else this.image = spriteSheet.getSprite(sprite, 1);
        this.score = 0;
    }

   
    /**
     * updates and moves the userCharacter
     * if there is a collision with any reward or punishment the userCharacter score is updated to reflect this collision and that entity is removed from the game
     */
    public void update()
    {
        this.move();

        for(int i = 0; i < manager.getList().size(); i++){
            entity e = manager.getList().get(i);
            if(this.collision(e)){
               if(e instanceof userCharacter){
                   continue;
               }
               else if(e instanceof bonusReward || e instanceof punishment || e instanceof regularReward){
                   this.addScore(e.getScore());
                   manager.removeFromList(e);

                   System.out.println("Score = " + this.getScore());
               }
            }
        }
    }
}
