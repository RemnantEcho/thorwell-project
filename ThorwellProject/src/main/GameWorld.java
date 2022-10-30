package main;

import entities.*;
import handlers.KeyController;
import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Fixture;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import city.cs.engine.UserView;
import city.cs.engine.World;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import org.jbox2d.common.Vec2;
import handlers.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import levels.*;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * A custom instance of World with multiple mutators to add levels, listeners and bridge important class objects for cross-referencing.
 * @author jly09
 */
public class GameWorld extends World implements ContactListener {
    LevelSelector levelSelect;
    StateManager stateManager;
    Window window;
    GameView view;
    Scores scores;
    RoundHandler roundHandler;
    ItemSpawn itemSpawn;
    Arms arms1;
    Arms arms2;
    Player1 p1;
    Player2 p2;
    Arrow arrow;
    KeyController keyController;
    ForestLevel forestLevel;
    IceLevel iceLevel;
    SkyLevel skyLevel;
    Sounds sounds;
    
    List<Vec2> playerSpawnLocation;
    Vec2 itemSpawnLocation;
    List<Items> itemList;
    List<Arrow> arrowList;
    List<Timer> timerList;
    
    BufferedImage background = null;
    BufferedImage middleground = null;
    
    /**
     * Creates the instance of GameWorld, sets the frame rate, window, and initial state, and initialises other classes.
     * @param framesPerSecond Sets the frame rate of the game.
     * @param window Sets the window.
     * @param stateManager Sets the state manager to allow state changing.
     */
    public GameWorld(int framesPerSecond, Window window, StateManager stateManager) {
        super(framesPerSecond);
        this.window = window;
        this.stateManager = stateManager;
        
        levelSelect = levelSelect.ForestLevel;
        
        scores = new Scores(this); 
        sounds = new Sounds();
        
        keyController = new KeyController(this);
        window.addKeyListener(keyController);
        
        roundHandler = new RoundHandler(this);
        
        forestLevel = new ForestLevel(this);
        iceLevel = new IceLevel(this);
        skyLevel = new SkyLevel(this);
        
        background = null;
        arrowList = new ArrayList<>();
        playerSpawnLocation = new ArrayList<>();
        timerList = new ArrayList<>();
        
        /*createWorld();
        spawnPlayers();
        addListeners();
        
        arrow = new Arrow(this, 0, true);*/
    }

    /**
     * Creates world, choosing between the level selected and pulling images, spawn locations, and item lists from specified level.
     */
    public void createWorld() {       
        switch (levelSelect.getLevelCode()) {
            case 0:
                forestLevel.createWorld(this);
                setBackground(forestLevel.getBackgroundImage());
                setMiddleground(forestLevel.getMiddlegroundImage());
                
                playerSpawnLocation = forestLevel.getSpawnPositions();
                itemSpawnLocation = forestLevel.getItemSpawnLocations();
                itemList = forestLevel.getItemList();
                
                break;
            case 1:
                iceLevel.createWorld(this);
                setBackground(iceLevel.getBackgroundImage());
                setMiddleground(iceLevel.getMiddlegroundImage());
                
                playerSpawnLocation = iceLevel.getSpawnPositions();
                itemSpawnLocation = iceLevel.getItemSpawnLocations();
                itemList = iceLevel.getItemList();
                break;
            default:
                skyLevel.createWorld(this);
                setBackground(skyLevel.getBackgroundImage());
                setMiddleground(skyLevel.getMiddlegroundImage());
                
                playerSpawnLocation = skyLevel.getSpawnPositions();
                itemSpawnLocation = skyLevel.getItemSpawnLocations();
                itemList = skyLevel.getItemList();
                break;
        }
    }
    
    private void spawnPlayers() {
        arms1 = new Arms(this, 0);
        arms2 = new Arms(this, 1);
        p1 = new Player1(this, false, arms1);
        p1.setPosition(playerSpawnLocation.get(0));
        //p1.setPosition(new Vec2(0f / PPM, 2f / PPM));
        p2 = new Player2(this, false, arms2);
        p2.setPosition(playerSpawnLocation.get(1));
        
    }
    
    private void addListeners() {
        keyController.setPlayers(p1, p2);
        itemSpawn = new ItemSpawn(this);
        this.addStepListener(itemSpawn);
        this.addStepListener(roundHandler);
        //this.addStepListener(new EnvironmentTimer(this));
    }
    
    /**
     * Retrieves all the bodies currently initialised in the world and destroys them.
     */
    public void destroyWorld() {
        List<DynamicBody> dynamicBodyList = new ArrayList<>();
        List<StaticBody> staticBodyList = new ArrayList<>();
        
        dynamicBodyList = this.getDynamicBodies();
        staticBodyList = this.getStaticBodies();
        
        for (DynamicBody db : dynamicBodyList) {
            db.destroy();
        }
        
        for (StaticBody sb : staticBodyList) {
            sb.destroy();
        }
        
    }
    
    /**
     * Used to reinitialise the world.
     */
    public void resetWorld() {
        destroyWorld();
        
        
        createWorld();
        spawnPlayers();
        newStart();
        addListeners();
        scores.resetScores();
    }
    
    /**
     * Respawns the player specified by the parsed player variable.
     * @param player The player variable used to determine the player being respawned.
     */
    public void respawnPlayer(Player player) {
        /*if (player instanceof Player1) {
            p1 = null;
        }
        if (player instanceof Player2) {
            p2 = null;
        }*/
        
        RespawnTimer rt = new RespawnTimer(this, player);
        Timer timer = new Timer(1500, rt);
        timerList.add(timer);
        
        //rt.setTimer(timer);
        timer.setRepeats(false);
        timer.start();
    }
    
    /**
     * Overrided from the inherited World class, starts the world.
     */
    @Override
    public void start() {
        super.start();
        
    }
    
    /**
     * Uses the start() method to restarts the world.
     */
    public void newStart() {
        start();
        roundHandler.setPreRound(true);
    }
    
    /**
     * Uses the start() method to resume the world simulation.
     */
    public void resume() {
        start();
    }
    
    /**
     * Sets the value of the player 1 instance in the world.
     * @param p1 - player 1 instance
     */
    public void setPlayer1(Player1 p1) {
        this.p1 = p1;
    }
    
    /**
     * Sets the value of the player 2 instance in the world.
     * @param p2 - player 2 instance
     */
    public void setPlayer2(Player2 p2) {
        this.p2 = p2;
    }
    
    /**
     * Gets the value of the player 1 in the current world instance.
     * @return the player 1 value
     */
    public Player1 getPlayer1() {
        return p1;
    }
    
    /**
     * Gets the value of the player 2 in the current world instance
     * @return the player 2 value
     */
    public Player2 getPlayer2() {
        return p2;
    }
    
    /**
     * Sets the key controller to the world.
     * @param keyController - key controller value
     */
    public void setKeyController(KeyController keyController) {
        this.keyController = keyController;
    }
    
    /**
     * Gets the key controller in the current world instance
     * @return key controller value
     */
    public KeyController getKeyController() {
        return keyController;
    }
    
    /**
     * Sets the round handler object for the world.
     * @param roundHandler - round handler value
     */
    public void setRoundHandler(RoundHandler roundHandler) {
        this.roundHandler = roundHandler;
    }
    
    /**
     * Gets the round handler object of the current world instance
     * @return round handler value
     */
    public RoundHandler getRoundHandler() {
        return roundHandler;
    }
    
     /**
     * Sets the item spawn object for the world.
     * @param itemSpawn - item spawn value
     */
    public void setItemSpawn(ItemSpawn itemSpawn) {
        this.itemSpawn = itemSpawn;
    }
    
    /**
     * Gets the item spawn object of the current world instance
     * @return item spawn value
     */
    public ItemSpawn getItemSpawn() {
        return itemSpawn;
    }
    
    /**
     * Sets the scores object for the current world instance
     * @param scores - the scores instance
     */
    public void setScores(Scores scores) {
        this.scores = scores;
    }
    
    /**
     * Gets the Scores object from the world.
     * @return the scores object
     */
    public Scores getScores() {
        return scores;
    }
    
    /**
     * Sets the background image for the current world instance, image is retrieved from the level during creation.
     * @param image - background image of level
     */
    public void setBackground(BufferedImage image) {
        this.background = image;
    }
    
    /**
     * Gets the background image from the world.
     * @return background image
     */
    public BufferedImage getBackground() {
        return background;
    }
    
    /**
     * Sets the middle ground image for the current world, parsed from the level.
     * @param image - the middle ground value
     */
    public void setMiddleground(BufferedImage image) {
        this.middleground = image;
    }
    
    /**
     * Gets the middle ground in the current world instance.
     * @return middle ground value
     */
    public BufferedImage getMiddleground() {
        return middleground;
    }
    
    /**
     * Gets the level selector currently in use by the world.
     * @return level selector object
     */
    public LevelSelector getLevelSelector() {
        return levelSelect;
    }
    
    /**
     * Sets the level selector to the current world instance.
     * @param levelSelect level selector value
     */
    public void setLevelSelector(LevelSelector levelSelect) {
        this.levelSelect = levelSelect;
    }
    
    /**
     * Sets the state manager to the current world instance.
     * @param stateManager - state manager object
     */
    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }
    
    /**
     * Gets the state manager of the current world instance
     * @return state manager object
     */
    public StateManager getStateManager() {
        return stateManager;
    }
    
    /**
     * Sets the player spawn location, spawning players are based on this parse value.
     * @param playerSpawnLocation - sets the spawn location value
     */
    public void setPlayerSpawnLocation(List<Vec2> playerSpawnLocation) {
        this.playerSpawnLocation = playerSpawnLocation ;
    }
    
    /**
     * Gets the player spawn location of the current world (current level).
     * @return the spawn location value of players
     */
    public List<Vec2> getPlayerSpawnLocation() {
        return playerSpawnLocation;
    }
    
    /**
     * Sets the item spawn location, retrieved from the level and to be used to determine the item spawn location
     * @param itemSpawnLocation - item spawn location value
     */
    public void setItemSpawnLocation(Vec2 itemSpawnLocation) {
        this.itemSpawnLocation = itemSpawnLocation ;
    }
    
    /**
     * Gets the item spawn location of the current world instance.
     * @return current item spawn location
     */
    public Vec2 getItemSpawnLocation() {
        return itemSpawnLocation;
    }
 
    /**
     * Gets the item list from the world instance.
     * @return item list value
     */
    public List<Items> getItemList() {
        return itemList;
    }
    
    /**
     * Gets the forest level.
     * @return forest level object
     */
    public ForestLevel getForestLevel() {
        return forestLevel;
    }
    
    /**
     * Gets the ice level.
     * @return ice level object
     */
    public IceLevel getIceLevel() {
        return iceLevel;
    }
    
    /**
     * Gets the sky level.
     * @return sky level object
     */
    public SkyLevel getSkyLevel() {
        return skyLevel;
    }
    
    /**
     * Gets the arrow list within the current world.
     * @return arrow list
     */
    public List<Arrow> getArrowList() {
        return arrowList;
    }
    
    /**
     * Gets timer list of the current world.
     * @return timer list
     */
    public List<Timer> getTimerList() {
        return timerList;
    }
    
    /**
     * Gets the window object in the current world instance.
     * @return window object
     */
    public Window getWindow() {
        return window;
    }
    
    /**
     * Gets the sound object in the current world instance.
     * @return sound object
     */
    public Sounds getSounds() {
        return sounds;
    }

    @Override
    public void beginContact(Contact cntct) {
        
    }

    @Override
    public void endContact(Contact cntct) {
        
    }

    @Override
    public void preSolve(Contact cntct, Manifold mnfld) {
        //we have to disable contact when our "player" fixture collide with "ground" fixture
            if(cntct.getFixtureA().getBody().getUserData() == "passable" && cntct.getFixtureB().getUserData() == "player1" || 
                    cntct.getFixtureA().getBody().getUserData() == "passable" && cntct.getFixtureB().getUserData() == "player2"){
                cntct.setEnabled(false);
            }
            //and we need to disable contact when our "groundChecker" will collide with "ground" and we need to check what velocity.y of player body is, when it is bigger than 0 contact should be falsed
            if(cntct.getFixtureA().getBody().getUserData() == "passable" && cntct.getFixtureB().getUserData() == "player1" && cntct.getFixtureB().getBody().getLinearVelocity().y > 0 ||
                    cntct.getFixtureA().getBody().getUserData() == "passable" && cntct.getFixtureB().getUserData() == "player2" && cntct.getFixtureB().getBody().getLinearVelocity().y > 0){
                cntct.setEnabled(false);
            }
    }

    @Override
    public void postSolve(Contact cntct, ContactImpulse ci) {
        
    }
    
}
/**
 * 
 * Respawn action listener used in correspondence with the respawn timer to respawn the player after the timer has finished.
 */
class RespawnTimer implements ActionListener {
    GameWorld world;
    Player player;
    Timer timer;
    
    long delay;
    long elapsed;
    
    /**
     * Initialises the action listener, and executes the player respawn.
     * @param world Sets the world object for the current class.
     * @param player Sets the player object for the current class.
     */
    public RespawnTimer(GameWorld world, Player player) {
        this.world = world;
        this.player = player;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Respawned");
        int random = (int) (Math.random() * world.getPlayerSpawnLocation().size());
        
        if (player instanceof Player1) {
            Arms arm;
            Player1 p1;
            arm = new Arms(world, 0);
            p1 = new Player1(world, true, arm);
            p1.setPosition(world.getPlayerSpawnLocation().get(random));
            world.setPlayer1(p1);
            world.getKeyController().setPlayer1(p1);
        }
        else if (player instanceof Player2) {
            Arms arm;
            Player2 p2;
            arm = new Arms(world, 1);
            p2 = new Player2(world, true, arm);
            p2.setPosition(world.getPlayerSpawnLocation().get(random));
            world.setPlayer2(p2);
            world.getKeyController().setPlayer2(p2);
        }
        
        /*for (Timer t: world.getTimerList()) {
            if (timer == t) {
                t.stop();
            }
        }*/
    }
    
    /**
     * Sets the timer, to be stopped and started after its initialised.
     * @param timer - sets the timer object
     */
    
    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
    
    
}
