package com.group2.managers;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.group2.App;
import com.group2.SpriteSheet;
import com.group2.entities.barrier;
import com.group2.entities.bonusReward;
import com.group2.entities.entity;
import com.group2.entities.exitCell;
import com.group2.entities.movingEnemy;
import com.group2.entities.punishment;
import com.group2.entities.regularReward;
import com.group2.entities.userCharacter;

/**
 * manages all entity for the game, includes newEntity, update for all entities, and find entity
 */
public class entityManager
{
    private ArrayList<entity> entityList;
    /** holds character sprite value */
    public int charSprite = 0;

    /**
     * constructor for entityManager initializes list for entities
     */
    public entityManager() {
    	entityList = new ArrayList<entity>();
    }

    /**
     * creates a new entity of type specified when called and adds it to the entityList
     * if entity requires a score this is where it is set
     * @param posX specified x position of entity
     * @param posY specified y position of entity
     * @param type string to define type of new entity to be created
     * @param spriteSheet spritesheet to be used for rendering
     * @return newly created entity
     */
    public entity newEntity(int posX, int posY, String type, SpriteSheet spriteSheet){
    	switch(type){
            case "barrier":
                barrier barrierVar = new barrier(posX,posY, spriteSheet);
                entityList.add(barrierVar);
                return barrierVar;
            case "bonusReward":
                bonusReward bonusRewardVar = new bonusReward(posX,posY,175,spriteSheet);
                entityList.add(bonusRewardVar);
                return bonusRewardVar;
            case "movingEnemy":
                movingEnemy movingEnemyVar = new movingEnemy(posX,posY,this,spriteSheet);
                entityList.add(movingEnemyVar);
                return movingEnemyVar;
            case "punishment":
                punishment punishmentVar = new punishment(posX,posY, -20,spriteSheet);
                entityList.add(punishmentVar);
                return punishmentVar;
            case "regularReward":
                regularReward regularRewardVar = new regularReward(posX,posY, 100,spriteSheet);
                entityList.add(regularRewardVar);
                return regularRewardVar;
            case "userCharacter":
                userCharacter userCharacterVar = new userCharacter(posX, posY, this,spriteSheet, charSprite);
                entityList.add(userCharacterVar);
                return userCharacterVar;
            case "exitCell":
                exitCell exitCellVar = new exitCell(posX, posY, 0,spriteSheet);
                entityList.add(exitCellVar);
                return exitCellVar;
            }
        return null;
    }

    /**
     * returns entityList
     * @return a list of entities from entityManager
     */
    public ArrayList<entity> getList(){ return entityList; }

    /**
     * moves  and updates all entities and checks for game over or game won
     */
    public void update(){
        for(int i = 0; i < entityList.size(); i++){
            entity e = entityList.get(i);
            e.update();
            if(e instanceof userCharacter && e.getScore() < 0)
                return;
            
            else if(e instanceof bonusReward && ((bonusReward) e).deleteBonusReward()){
                removeFromList(e);
            }

            else if(e instanceof movingEnemy){
                userCharacter ch = ((movingEnemy)e).findCharacterInList(entityList);
                if(ch.getScore() < 0){
                    //App.state = App.STATE.GAMEOVER;
                    return;
                }
            }

            else if(e instanceof exitCell && this.findEntity("regularReward") == null){
                userCharacter ch = ((exitCell)e).findCharacterInList(entityList);
                if(e.collision(ch))
                    App.state = App.STATE.GAMEWON;
            }
        }
    }

    /**
     * renders all entities in entityManager
     * @param g graphics to render
     */
    public void render(Graphics2D g) {
		for(int i = 0; i < entityList.size(); i++) {
			entityList.get(i).render(g);
		}
	}

    /**
     * removes an entity from the list
     * @param e entity to be removed
     */
    public void removeFromList(entity e){
        this.entityList.remove(e);
    }

    /**
     * clears list
     */
    public void clearList() {
        entityList.clear();
        //entityList = new ArrayList<entity>();
    }

    /**
     * finds and returns a specified type of entity in entityList
     * @param entityName string of type of entity to find
     * @return entity found
     */
    public entity findEntity(String entityName){
        for(int i = 0; i < entityList.size(); i++){
            if(entityName == "userCharacter" && entityList.get(i) instanceof userCharacter)
                return (userCharacter)entityList.get(i);
            if(entityName == "movingEnemy" && entityList.get(i) instanceof movingEnemy)
                return (movingEnemy)entityList.get(i);
            if(entityName == "bonusReward" && entityList.get(i) instanceof bonusReward)
                return (bonusReward)entityList.get(i);
            if(entityName == "barrier" && entityList.get(i) instanceof barrier)
                return (barrier)entityList.get(i);
            if(entityName == "punishment" && entityList.get(i) instanceof punishment)
                return (punishment)entityList.get(i);
            if(entityName == "regularReward" && entityList.get(i) instanceof regularReward)
                return (regularReward)entityList.get(i);
            if(entityName == "exitCell" && entityList.get(i) instanceof exitCell)
                return (exitCell)entityList.get(i); 
        }
        return null;
    }
}
