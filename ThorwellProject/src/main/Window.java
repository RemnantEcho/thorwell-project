package main;

import handlers.CustomMouse;
import main.*;
import city.cs.engine.UserView;
import city.cs.engine.*;
import handlers.StateManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

/**
 * Creates the important JComponents including; JFrame, the GameView, and the
 * GameWorld.
 *
 * @author jly09
 */
public class Window extends JFrame {

    StateManager stateManager;
    GameView view;
    GameWorld world;

    /**
     * Call constructor to initialise all the main JComponents.
     */
    public Window() {
        this.setTitle("Thorwell");
        stateManager = new StateManager();

        world = new GameWorld(60, this, stateManager);
        view = new GameView(world, 1120, 840, stateManager);

        stateManager.setLevelCode(0);
        stateManager.setView(view);
        stateManager.setWorld(world);
        world.setStateManager(stateManager);

        int width = 1120;
        int height = 840;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(view);

//        this.requestFocus();

        

        this.setMinimumSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        
        this.pack();
        
        this.setLocationRelativeTo(null);
        JFrame debugView = new DebugViewer(world, 1120, 840);

        this.addMouseListener(new CustomMouse(this));

        //Shows the Window
        this.setVisible(true);

        stateManager.checkState(false);
        //world.newStart();

    }

    /**
     * Program Entry point.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        new Window();
    }

    /**
     * Gets the GameView.
     *
     * @return view instance
     */
    public GameView getView() {
        return view;
    }

    /**
     * Gets the GameWorld
     *
     * @return world instance
     */
    public GameWorld getWorld() {
        return world;
    }

}
