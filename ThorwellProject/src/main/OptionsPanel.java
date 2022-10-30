package main;

import buttons.ControlsButton;
import buttons.OptionsButton;
import buttons.OptionsMMenu;
import buttons.OptionsQuit;
import buttons.ForestLevelButton;
import buttons.OptionsRestart;
import city.cs.engine.UserView;
import city.cs.engine.World;
import handlers.KeyController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Creates the options panel and adds it to the view, allows the options to be
 * viewed when playing.
 *
 * @author jly09
 */
public class OptionsPanel extends JPanel {

    GameWorld world;
    GameView view;
    KeyController keyController;
    OptionsQuit quitBtn;
    OptionsMMenu mainMenuBtn;
    OptionsRestart restartBtn;
    ControlsButton controlsBtn;

    Dimension panelDimensions = new Dimension(370, 400);
    Dimension parentDimensions;
    BufferedImage bimage = null;
    Point mainPanelCenter;
    boolean visible = false;

    /**
     * Initialises the option panel and sets the local variables.
     *
     * @param world Sets the GameWorld object of the current world.
     * @param width Sets the width of the option panel.
     * @param height Sets the height of the option panel.
     * @param keyController Sets the key controller object to be used in the
     * current class.
     */
    public OptionsPanel(GameWorld world, GameView view, int width, int height, KeyController keyController) {
        this.world = world;
        this.view = view;
        this.keyController = keyController;

        this.setMinimumSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(new Dimension(width, height));

        this.setOpaque(false);

        this.setLayout(null);

        try {
            bimage = ImageIO.read(new File("./data/optionpane.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        parentDimensions = new Dimension(width, height);
        mainPanelCenter = new Point(calculateCentre(panelDimensions.width, panelDimensions.height));

        quitBtn = new OptionsQuit(122, 272, this);
        mainMenuBtn = new OptionsMMenu(116, 165, this, this.world);
        restartBtn = new OptionsRestart(79, 93, this, this.world);
        controlsBtn = new ControlsButton(253, 285, this.view.getHelpPanel());

        this.add(quitBtn);
        this.add(mainMenuBtn);
        this.add(restartBtn);
        this.add(controlsBtn);
        this.setVisible(false);
    }

    /**
     * Sets the visibility of the Options Panel, calling lockControls().
     *
     * @param b - boolean value for setting visibility
     */
    public void setVisibility(Boolean b) {
        this.setVisible(b);
        this.visible = b;
        lockControls();
        this.view.getRoundHandler().setPause(b);
    }

    /**
     * Locks the controls of the players and stops or resumes the world based on
     * the visibility.
     */
    public void lockControls() {
        if (this.getVisible()) {
            world.stop();
            //keyController.setLockControlsP1(true);
            //keyController.setLockControlsP2(true);
        } else {
            world.resume();
            //keyController.setLockControlsP1(false);
            //keyController.setLockControlsP2(false);
        }
    }

    /**
     * Gets the visibility boolean value of the current panel.
     *
     * @return the visibility value
     */
    public boolean getVisible() {
        return visible;
    }

    /**
     * Calculates the position of a object to be positioned at the centre of the
     * screen,
     *
     * @param width Sets the width of the object.
     * @param height Sets the height of the object.
     * @return The position after calculating the centre value.
     */
    private Point calculateCentre(int width, int height) {
        Point tempPoint = new Point(
                (parentDimensions.width / 2) - (width / 2), (parentDimensions.height / 2) - (height / 2));
        return tempPoint;
    }

    @Override
    public void paint(Graphics g) {
        //System.out.println("Draw");
        //g.setColor(Color.BLUE);
        //g.fillRect(calculateCenter(320, 180).x, calculateCenter(320, 180).y, 180, 320);
        //g.fillRect(0, 0, 600, 600);

        //g.drawImage(bimage, mainPanelCenter.x, mainPanelCenter.y, this);
        g.drawImage(bimage, 0, 0, this);
        super.paint(g);
    }

}
