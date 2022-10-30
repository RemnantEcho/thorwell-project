package levels;

import entities.Items;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import org.jbox2d.common.Vec2;

/**
 * Abstract class composed of methods and variables for the levels to inherit
 * from and mutate depending on the level, relevant variables include; spawn
 * positions for both player and items, backgrounds and middle ground images,
 * and the locked value.
 *
 * @author jly09
 */
public abstract class Levels {

    List<Vec2> spawnPositions;
    Vec2 itemSpawnLocations;
    List<Items> itemList;
    BufferedImage background;
    BufferedImage middleground;
    boolean locked;

    /**
     *
     */
    public Levels() {

    }

    /**
     * Creates the world, initialise bodies here; to be overridden by
     * subclasses.
     */
    public void createWorld() {

    }

    /**
     * Sets the spawn position of the players.
     *
     * @param spawnPositions
     */
    public void setSpawnPositions(List<Vec2> spawnPositions) {
        this.spawnPositions = spawnPositions;
    }

    /**
     * Gets the spawn positions of the players;
     *
     * @return
     */
    public List<Vec2> getSpawnPositions() {
        return spawnPositions;
    }

    /**
     * Sets the spawn location for items.
     *
     * @param itemSpawnLocations
     */
    public void setItemSpawnLocations(Vec2 itemSpawnLocations) {
        this.itemSpawnLocations = itemSpawnLocations;
    }

    /**
     * Gets the spawn location for items.
     *
     * @return
     */
    public Vec2 getItemSpawnLocations() {
        return itemSpawnLocations;
    }

    /**
     * Gets the item list, the items which can be spawned on the level.
     *
     * @return
     */
    public List<Items> getItemList() {
        return itemList;
    }

    /**
     * Sets the background image for the specific level.
     *
     * @param background
     */
    public void setBackgroundImage(BufferedImage background) {
        this.background = background;
    }

    /**
     * Gets the background image for the specific level.
     *
     * @return
     */
    public BufferedImage getBackgroundImage() {
        return background;
    }

    /**
     * Sets the middle ground image for the specific level.
     *
     * @param background
     */
    public void setMiddlegroundImage(BufferedImage background) {
        this.middleground = middleground;
    }

    /**
     * Gets the middle ground image for the specific level.
     *
     * @return
     */
    public BufferedImage getMiddlegroundImage() {
        return middleground;
    }

    /**
     * Sets whether the level should be locked or not.
     *
     * @param locked
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * Gets the current locked value of the specified level.
     *
     * @return
     */
    public boolean getLocked() {
        return locked;
    }

}
