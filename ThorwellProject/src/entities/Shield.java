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
public class Shield extends Items {

    private static final Shape shape = new PolygonShape(-0.648f, 0.819f, -0.646f, -0.367f, -0.05f, -0.819f, 0.048f, -0.819f, 0.648f, -0.362f, 0.648f, 0.821f);

    private static final BodyImage image = new BodyImage("./data/pickups/shieldtrim.png", 1.65f);

    /**
     *
     * @param world
     */
    public Shield(GameWorld world) {
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
        this.setName("shield");
    }
}
