package platforms;

import city.cs.engine.Body;
import city.cs.engine.Fixture;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import java.awt.BasicStroke;
import main.GameWorld;
import org.jbox2d.dynamics.FixtureDef;

/**
 *
 * @author jly09
 */
public class Ground extends Platform {

    Shape shape;

    /**
     *
     * @param world
     * @param shape
     * @param platformCode
     */
    public Ground(GameWorld world, Shape shape, int platformCode) {
        super(world);
        this.platformCode = platformCode;
        this.shape = shape;

        SolidFixture sfix = new SolidFixture(this, shape, 5f);

        //sfix.setFriction(10f);
        this.addImage(imageList.get(platformCode));
    }

}
