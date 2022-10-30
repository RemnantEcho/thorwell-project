package platforms;

import city.cs.engine.Body;
import city.cs.engine.Fixture;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import java.awt.BasicStroke;
import org.jbox2d.dynamics.FixtureDef;

/**
 *
 * @author jly09
 */
public class Ice extends Platform {

    Shape shape;
    FixtureDef fdef;

    /**
     *
     * @param world
     * @param shape
     * @param platformCode
     */
    public Ice(World world, Shape shape, int platformCode) {
        super(world);
        this.platformCode = platformCode;
        this.shape = shape;

        SolidFixture sfix = new SolidFixture(this, shape);

        sfix.setFriction(0.05f);

        this.addImage(imageList.get(platformCode));
    }

}
