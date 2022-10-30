package handlers;

import city.cs.engine.UserView;
import city.cs.engine.World;
import entities.Arrow;
import main.*;

/**
 * Handles the state of the game; involves main menu, play, round end.
 *
 * @author jly09
 */
public class StateManager {

    GameWorld world;
    GameView view;

    int levelCode;

    /**
     *
     */
    public StateManager() {
        this.world = (GameWorld) world;
        this.view = (GameView) view;
        levelCode = 0;
    }

    /**
     *
     * @param reset
     */
    public void checkState(boolean reset) {
        switch (levelCode) {
            case 0:
                world.stop();
                world.destroyWorld();
                world.setBackground(null);
                world.setMiddleground(null);
                view.hudHandler(false);
                view.showMainMenuComponents();
                view.repaint();
                break;
            case 1:
                view.hudHandler(true);
                view.setRoundHandler(world.getRoundHandler());
                view.hideMainMenuComponents();
                world.resetWorld();
                view.setPlayer1(world.getPlayer1());
                view.setPlayer2(world.getPlayer2());

                if (reset) {
                    view.reinit();
                } else {
                    world.newStart();
                }

                view.repaint();
                break;
            case 2:
                world.stop();
                world.destroyWorld();
                view.showRoundEnd();
                view.hudHandler(false);
                view.repaint();
                break;
        }
    }

    /**
     *
     * @param levelCode
     */
    public void setLevelCode(int levelCode) {
        this.levelCode = levelCode;
    }

    /**
     *
     * @return
     */
    public int getLevelCode() {
        return levelCode;
    }

    /**
     *
     * @param world
     */
    public void setWorld(GameWorld world) {
        this.world = world;
    }

    /**
     *
     * @param view
     */
    public void setView(GameView view) {
        this.view = view;
    }

}
