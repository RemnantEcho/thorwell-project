package entities;

import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import city.cs.engine.Fixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.Sensor;
import city.cs.engine.SolidFixture;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import handlers.ArrowBodySensor;
import handlers.ArrowHeadSensor;
import static handlers.B2DVariables.GS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javax.swing.Timer;
import main.GameWorld;
import org.jbox2d.common.Vec2;
import static org.jbox2d.common.Vec2.dot;

/**
 * Creates a instance of the basic (unmodified) arrow, and mutators specifically
 * for the class inherited from the Arrow abstract class.
 *
 * @author jly09
 */
public class DefaultArrow extends Arrow implements StepListener {

    /**
     * The polygon shape for the arrow head.
     */
    public static PolygonShape arrowHeadShape = new PolygonShape(0.25f, 0.3f, 0.550f, 0.3f, 0.8f, 0.05f, 0.8f, -0.05f, 0.550f, -0.3f, 0.25f, -0.3f);

    /**
     * The polygon shape for the full arrow body.
     */
    public static PolygonShape bodyShape = new PolygonShape(-0.8f, 0.3f, 0.550f, 0.3f, 0.8f, 0.05f, 0.8f, -0.05f, 0.550f, -0.3f, -0.8f, -0.3f);

    /**
     * Creates the arrow and sets local variables
     *
     * @param world Sets the GameWorld and allows referencing to and from the
     * GameWorld
     * @param owner Sets the owner value
     * @param armed Sets the armed value
     * @param direction Sets the direction of the arrow
     */
    public DefaultArrow(GameWorld world, int owner, boolean armed, int direction) {
        super(world, owner, armed, direction);
        initialArm = new InitialArm(this);

        weld = false;
        weldPosition = new Vec2(0f, 0f);

        headSensor = new Sensor(this, arrowHeadShape, 0f);
        headSensor.addSensorListener(new ArrowHeadSensor(this, world));

        headSensor.setDensity(5.6f);

        bodySensor = new Sensor(this, bodyShape, 0f);
        bodySensor.addSensorListener(new ArrowBodySensor(this, world));

        if (owner == 0) {
            bodyImage = new BodyImage("./data/projectiles/greenarrow2.png", 0.7f);
        } else if (owner == 1) {
            bodyImage = new BodyImage("./data/projectiles/redarrow2.png", 0.7f);
        }

        this.addImage(bodyImage);
        this.setGravityScale(this.getGravityScale() * GS);
        this.setBullet(true);
        //this.setLinearVelocity(new Vec2((float)Math.cos(angle)*20, (float)Math.sin(angle)*20

        initialArmedTimer = new Timer(10, initialArm);
        initialArmedTimer.setRepeats(false);
        initialArmedTimer.start();
        
        this.setName("arrow");
        checkDirection();
    }

    /**
     * Creates an instance of the arrow, to be used when referencing the
     * instance without spawning the object
     *
     * @param world Sets GameWorld value
     */
    public DefaultArrow(GameWorld world) {
        super(world);

    }

    /**
     * Creates the item and allows it to appear in the world as a pick up item.
     *
     * @param spawnLocation Sets the spawn location of the instance and is used
     * to draw it to the world.
     */
    @Override
    public void createItem(Vec2 spawnLocation) {
        super.createItem(spawnLocation);
        bodyImage = new BodyImage("./data/projectiles/blankarrow.png", 0.7f);
        this.addImage(bodyImage);

        this.spawnLocation = spawnLocation;

        fix = new SolidFixture(this, bodyShape);
    }

    /**
     * Checks the direction of the arrow.
     */
    @Override
    public void checkDirection() {
        super.checkDirection();
    }

    /**
     * Called immediately before each simulation step.
     *
     * @param e - the event descriptor
     */
    @Override
    public void preStep(StepEvent e) {
        super.preStep(e);
    }

    /**
     * Called immediately after each simulation step.
     *
     * @param e - the event descriptor
     */
    @Override
    public void postStep(StepEvent e) {
        super.postStep(e);
    }

}
