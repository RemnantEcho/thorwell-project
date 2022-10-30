package main;

import buttons.CloseButton;
import buttons.ForestLevelButton;
import buttons.IceLevelButton;
import buttons.OptionsButton;
import buttons.OptionsQuit;
import buttons.PlayButton;
import buttons.QuitButton;
import buttons.RoundEndMMenu;
import buttons.SkyLevelButton;
import city.cs.engine.UserView;
import city.cs.engine.World;
import entities.*;
import handlers.LevelSelector;
import handlers.RoundHandler;
import handlers.StateManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import labels.ScoreLabel;
import labels.ScoreTextArea;
import labels.TimerLabel;
import labels.TitleLabel;
import org.jbox2d.common.Vec2;

/**
 * GameView used to render all components of the game to the screen (Window),
 * includes; drawing hud elements, main menu, backgrounds, and buttons.
 *
 * @author jly09
 */
public class GameView extends UserView {

    GameWorld world;
    LevelSelector levelSelect;
    StateManager stateManager;
    OptionsPanel options;
    OptionsButton optionsButton;
    HelpPanel help;
    
    RoundHandler roundHandler;
    RoundEndMMenu menuButton;

    TitleLabel titleLabel;
    PlayButton playButton;
    QuitButton quitButton;
    ForestLevelButton forestLevelButton;
    IceLevelButton iceLevelButton;
    SkyLevelButton skyLevelButton;

    Player1 p1;
    Player2 p2;

    BufferedImage mmBackground = null;
    BufferedImage arrowIcon = null;
    BufferedImage laserArrowIcon = null;

    TimerLabel roundTimer;
    ScoreTextArea p1Score;
    ScoreTextArea p2Score;
    ScoreLabel score;

    int width;
    int height;
    boolean selectingLevel;

    /**
     * Initialises the GameView and sets the local variables to be used directly
     * from the current class.
     *
     * @param world Sets the GameWorld to be used locally in the current
     * GameView.
     * @param width Sets the width of the screen.
     * @param height Sets the height of the screen.
     * @param stateManager Sets the State Manager, allowing dynamic referencing
     * of the current state.
     */
    public GameView(GameWorld world, int width, int height, StateManager stateManager) {
        super(world, width, height);
        this.world = world;
        this.stateManager = stateManager;
        this.width = width;
        this.height = height;
        this.setOpaque(true);

        this.roundHandler = this.world.getRoundHandler();
        this.levelSelect = this.world.getLevelSelector();

        this.setLayout(null);
        //this.setBackground(Color.BLACK);
        this.setView(new Vec2(0, 0), 20);

        loadImages();
        addButtons();
        reinit();
        repaint();
    }

    /**
     * Loads all images from files.
     */
    void loadImages() {
        try {
            mmBackground = ImageIO.read(new File("./data/backgrounds/mmBackground.png"));
            arrowIcon = ImageIO.read(new File("./data/icons/defaultarrowicon.png"));
            laserArrowIcon = ImageIO.read(new File("./data/icons/laserarrowicon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialises and adds all buttons the GameView.
     */
    void addButtons() {
        help = new HelpPanel(this.world, 640, 360, this.world.getKeyController());
        help.setLocation(calculateCenter(640, 360));
        
        options = new OptionsPanel(this.world, this, 370, 400, this.world.getKeyController());
        options.setLocation(calculateCenter(370, 400));
        
        titleLabel = new TitleLabel(width / 2, 200);
        
        optionsButton = new OptionsButton(width - 80, 5, options);
        forestLevelButton = new ForestLevelButton(0, 0, world, stateManager);
        iceLevelButton = new IceLevelButton(280, 0, world, stateManager);
        skyLevelButton = new SkyLevelButton(560, 0, world, stateManager);
        menuButton = new RoundEndMMenu(width / 2, 550, world);

        playButton = new PlayButton(width / 2, 400, world, this);
        quitButton = new QuitButton(width / 2, 500, this);

        roundTimer = new TimerLabel(width / 2, 0);
        roundTimer.setVisible(false);
        menuButton.setVisible(false);

        p1Score = new ScoreTextArea(width / 4, 10);
        p2Score = new ScoreTextArea(3 * (width / 4), 10);
        score = new ScoreLabel(width / 2, 40);

        p1Score.setForeground(Color.GREEN);
        p2Score.setForeground(Color.RED);

        this.add(titleLabel);
        this.add(help);
        this.add(options);
        
        this.add(roundTimer);
        this.add(p1Score);
        this.add(p2Score);
        this.add(score);
        this.add(optionsButton);
        this.add(forestLevelButton);
        this.add(iceLevelButton);
        this.add(skyLevelButton);
        this.add(menuButton);
        this.add(playButton);
        this.add(quitButton);
    }

    /**
     * Handles whether the hud should be viewed or not.
     *
     * @param visible - sets the visibility of the hud elements
     */
    public void hudHandler(Boolean visible) {
        //menuButton.setVisible(false);
        if (visible) {
            optionsButton.setVisible(true);
            roundTimer.setVisible(true);
            p1Score.setVisible(true);
            p2Score.setVisible(true);
        } else {
            optionsButton.setVisible(false);
            roundTimer.setVisible(false);
            p1Score.setVisible(false);
            p2Score.setVisible(false);

        }
    }

    /**
     * Show the components which are associated with the Main Menu.
     */
    public void showMainMenuComponents() {
        selectingLevel = false;

        titleLabel.setVisible(true);
        playButton.setVisible(true);
        quitButton.setVisible(true);
        forestLevelButton.setVisible(false);
        iceLevelButton.setVisible(false);
        skyLevelButton.setVisible(false);
        score.setVisible(false);
        menuButton.setVisible(false);
    }

    /**
     * Show the Level Selection screen and checks whether the buttons are
     * locked.
     */
    public void showLevelSelect() {
        selectingLevel = true;
        iceLevelButton.checkLocked();
        skyLevelButton.checkLocked();

        titleLabel.setVisible(false);
        playButton.setVisible(false);
        quitButton.setVisible(false);
        forestLevelButton.setVisible(true);
        iceLevelButton.setVisible(true);
        skyLevelButton.setVisible(true);

    }

    /**
     * Hides all components associated with the Main Menu.
     */
    public void hideMainMenuComponents() {
        titleLabel.setVisible(false);
        playButton.setVisible(false);
        quitButton.setVisible(false);
        forestLevelButton.setVisible(false);
        iceLevelButton.setVisible(false);
        skyLevelButton.setVisible(false);
    }

    /**
     * Draws the background content.
     *
     * @param g - graphics object
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        if (stateManager.getLevelCode() == 0) {
            if (!selectingLevel) {
                g.drawImage(mmBackground, 0, 0, this);
            } else {
                repaint();
                g.setColor(new Color(24, 24, 24));
                g.fillRect(0, 0, width, height);
            }

        } else if (stateManager.getLevelCode() == 1) {
            if (world.getBackground() == null) {
                g.setColor(new Color(24, 24, 24));
                g.fillRect(0, 0, width, height);

                //System.out.println(this.getLocation());
            } else {
                if (levelSelect == levelSelect.ForestLevel) {
                    g.drawImage(world.getBackground(), 0, 0, this);
                    g.drawImage(world.getMiddleground(), 0, 0, this);

                } else if (levelSelect == levelSelect.IceLevel) {
                    g.drawImage(world.getBackground(), 0, 0, this);

                } else if (levelSelect == levelSelect.SkyLevel) {
                    g.drawImage(world.getBackground(), 0, 0, this);
                    g.drawImage(world.getMiddleground(), 0, 0, this);

                }

            }

        } else if (stateManager.getLevelCode() == 2) {
            g.setColor(new Color(24, 24, 24));
            g.fillRect(0, 0, width, height);
        }

        g.setColor(Color.RED);
        //g.fillRect(0, 0, 300, 300);

        //System.out.println(world.getBackground());
    }

    /**
     * Draws the foreground.
     *
     * @param g - graphics object
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        if (stateManager.getLevelCode() == 1) {
            if (!world.getPlayer1().getIsDead()) {
                List<Arrow> tempList = new ArrayList<>(world.getPlayer1().getArrowList());
                int offset = 0;
                int count = 1;
                int padding = 8;
                Point2D.Float p1Position;
                p1Position = worldToView(world.getPlayer1().getPosition());

                if (tempList.size() == 1) {
                    offset = arrowIcon.getWidth() / 2;
                } else if (tempList.size() > 1) {
                    offset = (tempList.size() * arrowIcon.getWidth() + 2) / 2;
                }

                for (Arrow arrow : tempList) {
                    if (arrow instanceof DefaultArrow) {
                        g.drawImage(arrowIcon, (int) p1Position.x - (offset - (count * arrowIcon.getWidth() + 2)) - padding, (int) p1Position.y + 40, this);
                        count++;
                    }

                    if (arrow instanceof LaserArrow) {
                        g.drawImage(laserArrowIcon, (int) p1Position.x - (offset - (count * arrowIcon.getWidth() + 2)) - padding, (int) p1Position.y + 40, this);
                        count++;
                    }
                }
            }

            if (!world.getPlayer2().getIsDead()) {
                List<Arrow> tempList = new ArrayList<>(world.getPlayer2().getArrowList());
                int offset = 0;
                int count = 1;
                int padding = 8;
                Point2D.Float p1Position;
                p1Position = worldToView(world.getPlayer2().getPosition());

                if (tempList.size() == 1) {
                    offset = arrowIcon.getWidth() / 2;
                } else if (tempList.size() > 1) {
                    offset = (tempList.size() * arrowIcon.getWidth() + 2) / 2;
                }

                for (Arrow arrow : tempList) {
                    if (arrow instanceof DefaultArrow) {
                        g.drawImage(arrowIcon, (int) p1Position.x - (offset - (count * arrowIcon.getWidth() + 2)) - padding, (int) p1Position.y + 40, this);
                        count++;
                    }

                    if (arrow instanceof LaserArrow) {
                        g.drawImage(laserArrowIcon, (int) p1Position.x - (offset - (count * arrowIcon.getWidth() + 2)) - padding, (int) p1Position.y + 40, this);
                        count++;
                    }
                }
            }

        }

        if (roundHandler.getPreRound()) {
            roundTimer.setText("-:--");
            p1Score.setText("P1\n" + world.getScores().getP1Score());
            p2Score.setText("P2\n" + world.getScores().getP2Score());
        } else if (roundHandler.getMainRound()) {
            roundTimer.setText(roundHandler.getRoundTimer());
            p1Score.setText("P1\n" + world.getScores().getP1Score());
            p2Score.setText("P2\n" + world.getScores().getP2Score());
        } else if (roundHandler.getRoundEnding()) {
            roundTimer.setText("0:00");
            p1Score.setText("P1\n" + world.getScores().getP1Score());
            p2Score.setText("P2\n" + world.getScores().getP2Score());
        }
        //drawPlayerItems(g);

        g.setColor(Color.RED);
        //g.fillRect(0, 0, 300, 300);

        //System.out.println(world.getBackground());
    }

    /**
     * Reinitialises relevant components, currently the round timer and scores.
     */
    public void reinit() {
        roundTimer.setText("-:--");
        p1Score.setText("P1\n" + world.getScores().getP1Score());
        p2Score.setText("P2\n" + world.getScores().getP2Score());
    }

    private Point calculateCenter(int width, int height) {
        Point tempPoint = new Point(
                (this.width / 2) - (width / 2), (this.height / 2) - (height / 2));
        return tempPoint;
    }

    /**
     * Sets the player 1 object in the current GameView.
     *
     * @param p1 - player 1 object
     */
    public void setPlayer1(Player1 p1) {
        this.p1 = p1;
    }

    /**
     * Sets the player 2 object in the current GameView.
     *
     * @param p2 - player 2 object
     */
    public void setPlayer2(Player2 p2) {
        this.p2 = p2;
    }

    /**
     * Sets the round handler object in the current GameView
     *
     * @param roundHandler - round handler object
     */
    public void setRoundHandler(RoundHandler roundHandler) {
        this.roundHandler = roundHandler;
    }

    /**
     * Gets the forest level button currently initialised in the GameView.
     *
     * @return forest level button object
     */
    public ForestLevelButton getForestLevelButton() {
        return forestLevelButton;
    }

    /**
     * Gets the ice level button currently initialised in the GameView.
     *
     * @return ice level button object
     */
    public IceLevelButton getIceLevelButton() {
        return iceLevelButton;
    }

    /**
     * Gets the sky level button currently initialised in the GameView.
     *
     * @return sky level button object
     */
    public SkyLevelButton getSkyLevelButton() {
        return skyLevelButton;
    }

    /**
     * Gets the options panel object currently initialised in the GameView.
     *
     * @return
     */
    public OptionsPanel getOptionsPanel() {
        return options;
    }
    
    /**
     * Gets the help panel object currently initialised in the GameView.
     *
     * @return
     */
    public HelpPanel getHelpPanel() {
        return help;
    }
    
    /**
     * Gets the round handler object currently initialised in the GameView.
     *
     * @return
     */
    public RoundHandler getRoundHandler() {
        return roundHandler;
    }

    /**
     * Draws/sets visible relevant components when the round ends.
     */
    public void showRoundEnd() {
        world.setBackground(null);
        world.setMiddleground(null);

        menuButton.setVisible(true);
        score.setVisible(true);
        //score.setOpaque(true);

        if (world.getScores().calculateWinner() == world.getPlayer1()) {
            score.setForeground(Color.GREEN);
            score.setText("Player 1 Wins");
        } else if (world.getScores().calculateWinner() == world.getPlayer2()) {
            score.setForeground(Color.RED);
            score.setText("Player 2 Wins");
        }
        if (world.getScores().calculateWinner() == null) {
            score.setForeground(Color.WHITE);
            score.setBackground(Color.WHITE);
            score.setText("Draw");
        }

        this.repaint();
        score.repaint();
    }
    
}
