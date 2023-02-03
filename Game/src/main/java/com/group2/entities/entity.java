package com.group2.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.group2.managers.entityManager;

/**
 * an abstract class for all entities in the game
 */
public abstract class entity
{
    /** x position for entity */
    protected int posX;
    /** y position for entity */
    protected int posY;
    /** x bound for entity */
    protected int xBound;
    /** y bound for entity */
    protected int yBound;
    /** score for entity */
    protected int score;
    final int UP = 0;
    final int DOWN = 1;
    final int RIGHT = 2;
    final int LEFT = 3;
    /** entity image */
    protected BufferedImage image;
    /** entitymanager */
    protected entityManager manager = null;
    /** entity move direction */
    protected int direction = -1;

    /**
     * base entity constructor for positions
     * @param x specified x position of entity
     * @param y specified y position of entity
     */
    entity(int x, int y){ 
        this.posX = x; 
        this.posY = y;
    }

    /**
     * @param x specified x position of entity
     * @param y specified y position of entity
     * @param manager entitymanager used to allow instance of entity to interact with other entities
     */
    entity(int x, int y, entityManager manager){
        this.posX = x;
        this.posY = y;
        this.manager = manager;
    }
    /**
     * updates the entity
     */
    public void update(){}

    /**
     * sets position of entity to x,y
     * @param x specified x position of entity
     * @param y specified y position of entity
     */
    public void setPosition(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    /**
     * @return x position of entity
     */
    public int getX(){return this.posX;}

    /**
     * @return y position of entity
     */
    public int getY(){return this.posY;}

    /**
     * @param x sets specified x position of entity
     */
    public void setXBound(int x){ this.xBound = x; }

    /**
     * @param y sets specified y position of entity
     */
    public void setYBound(int y){ this.yBound = y; }

    /**
     * uses entityList to check and return if this instance of entity can move
     * returns false if collision
     * returns true if no collision
     * @param direction specifies direction to move
     * @return true if entity can move and false if not
     */
    protected boolean canMove(int direction){

        int tempX = this.getX();
        int tempY = this.getY();

        switch(direction){
            case DOWN:
                tempY++;
                if(tempY > yBound){
                    System.out.println("yMin Reached");
                    return false;
                }
                break;
            case UP:
                tempY--;
                if(tempY < 0){
                    System.out.println("yMax Reached");
                    return false;
                }
                break;
            case LEFT:
                tempX--;
                if(tempX < 0){
                    System.out.println("xMin Reached");
                    return false;
                }
                break;
            case RIGHT:
                tempX++;
                if(tempX > xBound){
                    System.out.println("xMax Reached");
                    return false;
                }
                break;
        }

        for(int i = 0; i < manager.getList().size(); i++){
            entity e = manager.getList().get(i);
            if(e.getClass() == this.getClass() && e instanceof userCharacter){
                continue;
            }
            
            else if(e.getX() == tempX && e.getY() == tempY && e instanceof barrier){
                return false;
            }
            else if(e.getX() == tempX && e.getY() == tempY && e instanceof movingEnemy)
                return false;
        }
        return true;
    }


    /**
     * checks if entity can move using canMove and if true moves this instance of entity
     * @param direction direction of which to move
     */
    public void move(int direction){
        if(!canMove(direction))
            return;
        if (direction == DOWN){
            this.posY++;
        }
        else if (direction == UP){
            this.posY--;
        }
        else if (direction == RIGHT ){
            this.posX++;
        }
        else if (direction == LEFT){
            this.posX--;
        }

    }

    /**
     * @param x sets score of entity to x
     */
    public void setScore(int x){this.score = x;}

    /**
     * @return score of entity
     */
    public int getScore(){return this.score;}

    /**
     * adds score to this instance of entity
     * to be used with userCharacter
     * @param val adds val to entity score
     * @return score of entity
     */
    public int addScore(int val){
        this.score += val;
        return this.score;
    }

    /**
     * @param graphics graphics of app
     */
	public void render(Graphics2D graphics) {
		graphics.drawImage(image, posX * 32, posY * 32, null);
	}

    /**
     * finds and returns the instance of userCharacter in the game for use with other classes
     * @param entities array of entities to find instance of userCharacter in
     * @return the found userCharacter
     */
    public userCharacter findCharacterInList(ArrayList<entity> entities){
        for(int i = 0; i < entities.size(); i++)
            if(entities.get(i) instanceof userCharacter)
                return (userCharacter)entities.get(i);
        return null;
    }

    /**
     * checks if this instance has a collision with specified entity e
     * @param e checks for entity collision with entity e
     * @return true if collision and false if no collision
     */
    public boolean collision(entity e)
    {
        if(e.getX() == this.getX() && e.getY() == this.getY())
            return true;
        
    	return false;
    }

}
