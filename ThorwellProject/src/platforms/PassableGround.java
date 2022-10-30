package platforms;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Fixture;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.Sensor;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import handlers.PassableGroundSensor;
import java.awt.BasicStroke;
import main.GameWorld;
import city.cs.engine.Shape;
import static handlers.FilterBits.*;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.FixtureDef;

/**
 *
 * @author jly09
 */
public class PassableGround extends Platform {

    Shape shape;
    private Sensor groundSensor;

    /**
     *
     * @param world
     * @param shape
     * @param platformCode
     */
    public PassableGround(GameWorld world, Vec2 size, int platformCode) {
        super(world);
        this.platformCode = platformCode;
        this.shape = new BoxShape(size.x, size.y);

        SolidFixture sfix = new SolidFixture(this, shape, 5f);
//        GhostlyFixture gfix = new GhostlyFixture(this, shape, 5f);
        
        groundSensor = new Sensor(this, shape, 0f);
//        groundSensor.addSensorListener(new PassableGroundSensor(world, this));
        
        this.setName("passable");
        
        PolygonShape pshape = new PolygonShape();
        pshape.setAsBox(size.x, size.y);
        
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = pshape;
//        fixtureDef.density = 0.1f;
//        fixtureDef.restitution = 0.5f;
//        fixtureDef.filter.categoryBits = GROUND_CATEGORY_BITS;
//        fixtureDef.filter.maskBits = GROUND_MASK_BITS;

        //sfix.setFriction(10f);
        this.addImage(imageList.get(platformCode));
    }
    
    public Sensor getGroundSensor() { return groundSensor; }

}
