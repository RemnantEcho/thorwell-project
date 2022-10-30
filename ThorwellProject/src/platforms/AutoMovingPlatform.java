package platforms;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Fixture;
import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import handlers.AutoMovingPlatformSensor;
import java.awt.BasicStroke;
import static java.lang.Math.abs;
import main.GameWorld;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.FixtureDef;

/**
 *
 * @author jly09
 */
public class AutoMovingPlatform extends DynamicPlatform implements StepListener {

    private Shape shape;
    private SolidFixture sfix;
    private Sensor leftSensor;
    private Sensor rightSensor;
    private float speed;
    private Vec2 defaultPos;

    private Shape leftSensorShape;
    private Shape rightSensorShape;

    /**
     *
     * @param world
     * @param shapeWidth
     * @param shapeHeight
     * @param platformCode
     * @param defaultPos
     */
    public AutoMovingPlatform(GameWorld world, float shapeWidth, float shapeHeight, int platformCode, Vec2 defaultPos) {
        super(world);
        this.platformCode = platformCode;
        this.shape = new BoxShape(shapeWidth, shapeHeight);
        this.defaultPos = defaultPos;
        leftSensorShape = new BoxShape(0.6f, 0.6f, new Vec2(-shapeWidth + 0.5f, 0f));
        rightSensorShape = new BoxShape(0.6f, 0.6f, new Vec2(shapeWidth - 0.5f, 0f));

        this.setPosition(defaultPos);

        sfix = new SolidFixture(this, shape);

        sfix.setFriction(5f);

        leftSensor = new Sensor(this, leftSensorShape, 0f);
        leftSensor.addSensorListener(new AutoMovingPlatformSensor(world, this));

        rightSensor = new Sensor(this, rightSensorShape, 0f);
        rightSensor.addSensorListener(new AutoMovingPlatformSensor(world, this));
        
        this.setBullet(false);

        //this.addImage(imageList.get(platformCode));
        this.setGravityScale(0);
        speed = 5f;
        world.addStepListener(this);
    }

    /**
     *
     * @return
     */
    public SolidFixture getSolidFixture() {
        return sfix;
    }

    /**
     *
     * @param speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     */
    public float getSpeed() {
        return speed;
    }

    /**
     *
     * @param e
     */
    @Override
    public void preStep(StepEvent e) {
        this.setAngle(0);
        this.setPosition(new Vec2(this.getPosition().x, defaultPos.y));
        this.setLinearVelocity(new Vec2(speed, 0));

    }

    /**
     *
     * @param e
     */
    @Override
    public void postStep(StepEvent e) {
        this.setAngle(0);
        this.setPosition(new Vec2(this.getPosition().x, defaultPos.y));
        this.setLinearVelocity(new Vec2(speed, 0));
        //System.out.println(speed);
    }

    /**
     *
     * @return
     */
    public boolean getDirection() {
        if (speed > 0) {
            return true;
        } else if (speed < 0) {
            return false;
        }

        return true;
    }

    /**
     *
     * @return
     */
    public Sensor getLeftSensor() {
        return leftSensor;
    }

    /**
     *
     * @return
     */
    public Sensor getRightSensor() {
        return rightSensor;
    }

}
