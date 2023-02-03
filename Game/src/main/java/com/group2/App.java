package com.group2;
import java.awt.*;
import java.awt.image.BufferStrategy;
import com.group2.UI.CharacterSelect;
import com.group2.UI.GameOver;
import com.group2.UI.HUD;
import com.group2.UI.Instructions;
import com.group2.UI.LevelFinish;
import com.group2.UI.MainMenu;
import com.group2.UI.LevelSelect;
import java.util.Random;

import com.group2.entities.entity;
import com.group2.entities.movingEnemy;
import com.group2.entities.userCharacter;
import com.group2.managers.entityManager;

/**
 * runs an instance of our game
 */
public class App implements Runnable
{
    private boolean running = false;
    private Thread thread;
    private SpriteSheet spriteSheet;
    private entityManager entities;
    private Renderer renderer;
    private CharacterSelect charSelect;
    private Maps map;
    private HUD hud;

    private MainMenu mainMenu;
    private Instructions instructions;
    private GameOver gameOver;
    private LevelFinish levelFinish;
    private LevelSelect levelSelect;

    final int UP = 0;
    final int DOWN = 1;
    final int RIGHT = 2;
    final int LEFT = 3;

    /**
     * STATE manager for app
     */
    public static enum STATE
    {
        /**GAME STATE GAME */
        GAME,
        /**GAME STATE SETUP */
        SETUP,
        /**GAME STATE MAINMENU */
        MAINMENU,
        /**GAME STATE CHARACTERSELECT */
        CHARACTERSELECT,
        /**GAME STATE INSTRUCTIONS */
        INSTRUCTIONS,
        /**GAME STATE LEVELMENU */
        LEVELMENU,
        /**GAME STATE LEVELSELECT */
        LEVELSELECT,
        /**GAME STATE GAMEOVER */
        GAMEOVER,
        /**GAME STATE GAMEWON */
        GAMEWON,
        /**GAME STATE QUIT */
        QUIT,
    };

    /**set STATE to MAINMENU */
    public static STATE state = STATE.MAINMENU;

    /**
     * initializes the board, spritesheet, renderer, map, entitymanager, and hud
     */
    private void setup()
    {
        mainMenu = new MainMenu();
    	
    	spriteSheet = new SpriteSheet();
        instructions = new Instructions(spriteSheet);
        charSelect = new CharacterSelect(spriteSheet);
        levelSelect = new LevelSelect();
        map = new Maps(spriteSheet);
        entities = new entityManager();
        renderer = new Renderer(1024, 736, "CPMT 276 Game Project", entities, map);
    }

    /**
     * initializes the new level
     */
    private void setupLevel() {
    	entities.clearList();
    	map.initMap(entities, spriteSheet);
    	setAllEntityBounds();
    	hud = new HUD((userCharacter)entities.findEntity("userCharacter"));
        hud.startTimer();
        levelFinish = new LevelFinish(entities, hud);
        gameOver = new GameOver(entities, hud);
    }

    /**
     * sets bounds for userCharacter and moving enemy based on map bounds
     */
    private void setAllEntityBounds(){
        for(int i = 0; i < entities.getList().size(); i++){
            entity e = entities.getList().get(i);
            if(e instanceof userCharacter || e instanceof movingEnemy){
                e.setXBound(map.mapWidth());
                e.setYBound(map.mapHeight());
            }
        }
    }

    /**
     * creates a new thread for running the game
     */
    private synchronized void startGame()
    {
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * removes the thread of the game
     */
    synchronized void stopGame()
    {
        renderer.destroyFrame();
        thread.interrupt();
        try
        {
            thread.join(0);
        }
        
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        System.exit(1);
    }

    /**
     * sets the number of ticks per second, calls the game initializer (setup()), calls update for all entities, then calls render to draw to the game window
     */
    public void run()
    {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 5.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

		setup();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1)
            {
                if(state == STATE.GAME)
            		update();

                else if(state == STATE.QUIT) {
                	System.out.println();
                	running = false;
                }

                else if(state == STATE.SETUP) {
                    setupLevel();
                    state = STATE.GAME;
                }
                
                delta--;
                updates++;
            }
            render();

           frames++;
           if(System.currentTimeMillis() - timer > 1000)
           {
               timer += 1000;
               System.out.println(updates + " Ticks, FPS " + frames);
               updates = 0;
               frames = 0;
           }
        }
        System.out.println("stoppingGame");
        stopGame();
    }

    /**
     * has a possibliity of spawning a bonus reaward at a random position on the map, also adds this instance of bonus reward to entityList
     */
    private void createBonusReward(){
        
        Random rand = new Random();
        boolean canSpawn = true;
        if(rand.nextInt(100) <= 10){
            int brX = rand.nextInt(map.mapWidth());
            int brY = rand.nextInt(map.mapHeight()-1);
            brY++;
            for(int i = 0; i < entities.getList().size(); i++){
                if(brX == entities.getList().get(i).getX() && brY == entities.getList().get(i).getY())
                    canSpawn = false;
            }
            if(canSpawn)
                entities.newEntity(brX, brY, "bonusReward", spriteSheet);

        }   
    }

    /**
     * calls update for all entities and checks if player score is < 0
     * if score < 0 game state is set to GAMEOVER
     */
    private void update() 
    {
        entities.update();
        if(entities.findEntity("userCharacter").getScore() < 0) {
            System.out.println("SCORE < 0");
            state = STATE.GAMEOVER;
        }
        createBonusReward();
    }

    /**
     * renders all changes made to entities and checks and renders game state
     */
    private void render(){
    	BufferStrategy buffer = renderer.getBuffer();
    	Graphics2D graphics = (Graphics2D) buffer.getDrawGraphics();
    	graphics.setColor(new Color(64, 86, 161));
        graphics.fillRect(0, 0, 1024, 736);
        if(state == STATE.GAME){
            entities.render(graphics);
        	hud.render(graphics);
        }
        else if(state == STATE.GAMEOVER){
        	gameOver.render(graphics);
        }
        else if(state == STATE.INSTRUCTIONS){
        	instructions.render(graphics);
        }
        else if(state == STATE.CHARACTERSELECT) {
        	charSelect.render(graphics);
        }
        else if(state == STATE.LEVELSELECT) {
        	levelSelect.render(graphics);
        }
        else if(state == STATE.MAINMENU){
            mainMenu.render(graphics);
        }
        else if(state == STATE.GAMEWON){
            levelFinish.render(graphics);
        }
        
        graphics.dispose();
        buffer.show();
    }
    
    /**
     * main method
     * @param args main string args
     */
    public static void main( String[] args )
    {
        App game = new App();
        game.startGame();
    }
}
