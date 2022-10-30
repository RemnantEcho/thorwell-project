package entities;

import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import main.GameWorld;
import org.jbox2d.common.Vec2;

/**
 * Abstract class for items to be inherited from; items are set spawn location,
 * fixtures and allows items to be created.
 *
 * @author jly09
 */
public abstract class Items extends DynamicBody {

    GameWorld world;
    Shape shape;
    SolidFixture fix;
    Vec2 spawnLocation;

    /**
     *
     * @param world
     */
    public Items(GameWorld world) {
        super(world);

        this.world = world;
    }

    /**
     *
     * @param spawnLocation
     */
    public void createItem(Vec2 spawnLocation) {
        this.setPosition(spawnLocation);
    }

    /**
     *
     * @param spawnLocation
     */
    public void setSpawnLocation(Vec2 spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    /**
     *
     * @return
     */
    public Vec2 getSpawnLocation() {
        return spawnLocation;
    }

    /**
     *
     * @return
     */
    public SolidFixture getFixture() {
        return fix;
    }

}
