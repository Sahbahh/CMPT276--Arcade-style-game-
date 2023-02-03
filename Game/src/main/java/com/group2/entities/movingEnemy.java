package com.group2.entities;
import com.group2.App;

import com.group2.SpriteSheet;
import com.group2.managers.entityManager;

/**
 * an animated enemy, moving towards the player at every other tick of the game.  if moving enemy collides with character gameover
 */
public class movingEnemy extends entity
{
    final int UP = 0;
    final int DOWN = 1;
    final int RIGHT = 2;
    final int LEFT = 3;
    private boolean hasMoved;

    /**
     * constructor for movingEnemy
     * @param x specified x position of character
     * @param y specified y position of character
     * @param manager entitymanager used to allow instance of entity to interact with other entities
     * @param spriteSheet spritesheet to be used for rendering
     */
    public movingEnemy(int x, int y, entityManager manager, SpriteSheet spriteSheet){
        super(x, y, manager);
        this.image = spriteSheet.getSprite(3, 0);
    }

    /**
     * checks for collision with userCharacter in entityManager, if true playerscore set to 0
     */
    private void characterCollision(){
        userCharacter ch = findCharacterInList(manager.getList());
        if(this.getX() == ch.getX() && this.getY() == ch.getY()){
            System.out.println("Setting Score to 0 in movingEnemy.characterCollision");
            App.state = App.STATE.GAMEOVER;
            //ch.setScore(-100);
        }

    }

    /**
     * updates and moves this instance of movingEnemy every other tick
     */
    public void update(){
        if(!hasMoved){
            this.move(findShortestPath());
            this.characterCollision();
            hasMoved = true;
            return;
        }
        hasMoved = false;
    }

    /**
     * finds which direction to move this instance of movingEnemy towards the userCharacter
     * 
     ***********BFS to be implemented in phase 3*************
     * @return which direction for movingEnemy to move
     */
    private int findShortestPath(){
        //need to add checks for obstacles when the maps are implemented
        userCharacter ch = findCharacterInList(manager.getList());

        if (ch.getX() > this.getX()){
            if(this.canMove(RIGHT))
                return RIGHT;
            if(ch.getY() > this.getY())
                if(this.canMove(DOWN))
                    return DOWN;
            if(this.canMove(UP))
                return UP;
            return LEFT;
        }else if(ch.getX() < this.getX()){
            if(this.canMove(LEFT))
                return LEFT;
            if(ch.getY() > this.getY())
                if(this.canMove(DOWN))
                    return DOWN;
            if(this.canMove(UP))
                return UP;
            return RIGHT;
        }else if(ch.getX() == this.getX()){
            if(ch.getY() > this.getY())
                if(this.canMove(DOWN))
                    return DOWN;
            return UP;
        }

        else{
            System.out.println("Something must be wrong with the ai pathing function");
        }

        return -1;

    }
}
