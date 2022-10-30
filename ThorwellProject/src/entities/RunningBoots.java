package entities;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.CircleShape;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.World;
import main.GameWorld;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.FixtureDef;

/**
 * Running Boots Item
 *
 */
public class RunningBoots extends Items {

    private static final Shape shape = new PolygonShape(-0.098f, 0.597f, -0.645f, -0.05f, -0.647f, -0.598f, 0.65f, -0.595f, 0.65f, 0.598f);

    private static final BodyImage image = new BodyImage("./data/pickups/runningboots.png", 1.2f);

    float speedModifier;

    /**
     *
     * @param world
     */
    public RunningBoots(GameWorld world) {
        super(world);

        speedModifier = 6f;
    }

    /**
     *
     * @param spawnLocation
     */
    @Override
    public void createItem(Vec2 spawnLocation) {
        super.createItem(spawnLocation);
        this.spawnLocation = spawnLocation;
        this.addImage(image);
        fix = new SolidFixture(this, shape);
        FixtureDef fdef = new FixtureDef();
        fdef.isSensor = true;
        
        this.setName("runboots");
    }

    /**
     *
     * @return
     */
    public float getSpeedModifier() {
        return speedModifier;
    }

}
