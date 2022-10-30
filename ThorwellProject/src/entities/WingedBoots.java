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
 *
 * @author jly09
 */
public class WingedBoots extends Items {

    //private static final Shape shape = new PolygonShape(-0.025f,0.0523f, -0.08f,0.0025f, -0.08f,-0.0519f, 0.05f,-0.0525f, 0.0798f,0.0173f, 0.0798f,0.0323f, 0.0496f,0.0523f);
    private static final Shape shape = new PolygonShape(-0.252f, 0.519f, -0.802f, 0.023f, -0.8f, -0.525f, 0.498f, -0.525f, 0.798f, 0.175f, 0.8f, 0.329f, 0.498f, 0.519f);

    private static final BodyImage image = new BodyImage("./data/pickups/wingedboots.png", 1.05f);

    /**
     *
     * @param world
     */
    public WingedBoots(GameWorld world) {
        super(world);

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
        this.setName("wingboots");
    }

}
