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
import org.jbox2d.dynamics.joints.WeldJoint;

/**
 * Abstract class of the Arrow entity, handles setting the image, direction,
 * arming, and welding of the arrow.
 *
 * @author jly09
 */
public abstract class Arrow extends Items implements StepListener {

    GameWorld world;
    BodyImage bodyImage;
    InitialArm initialArm;
    Timer initialArmedTimer;
    int owner;
    boolean armed;
    int direction;
    boolean weld;
    Vec2 weldPosition;

    Sensor bodySensor;
    Sensor headSensor;

    /**
     * Creates a Arrow object, initialises values parsed, and adds it to the
     * world.
     *
     * @param world Sets the GameWorld to allow access to methods within the
     * object
     * @param owner Sets the owner of the Arrow Entity, image and collision is
     * based off of this value.
     * @param armed Sets whether the arrow is armed.
     * @param direction Sets the direction of which the arrow is travelling.
     */
    public Arrow(GameWorld world, int owner, boolean armed, int direction) {
        super(world);
        this.world = world;
        this.owner = owner;
        this.armed = armed;
        this.direction = direction;

        world.addStepListener(this);
        world.getArrowList().add(this);
    }

    /**
     * Creates a Arrow object without any values being set, used for instance
     * referencing without spawning the object to the world.
     *
     * @param world
     */
    public Arrow(GameWorld world) {
        super(world);
    }

    /**
     * Checks the direction of the arrow path and applies the initial impulse
     * for it to move.
     */
    public void checkDirection() {
        switch (direction) {
            //up
            case 0:
                this.applyImpulse(new Vec2(0, 48f));
                break;
            //up right
            case 1:
                this.applyImpulse(new Vec2(48f, 54f));
                break;
            //right
            case 2:
                this.applyImpulse(new Vec2(54f, 15f));
                break;
            //down right
            case 3:
                this.applyImpulse(new Vec2(48f, -54f));
                break;
            //down
            case 4:
                this.applyImpulse(new Vec2(0, -54f));
                break;
            //up left
            case 5:
                this.applyImpulse(new Vec2(-48f, 54f));
                break;
            //left
            case 6:
                this.applyImpulse(new Vec2(-54f, 15f));
                break;
            //down left
            case 7:
                this.applyImpulse(new Vec2(-48f, -54f));
                break;
        }

    }

    /**
     * Gets the armed value of this instance.
     *
     * @return armed value
     */
    public boolean checkArmed() {
        return armed;
    }

    /**
     * Sets the armed value of this instance.
     *
     * @param armed
     */
    public void setArmed(boolean armed) {
        this.armed = armed;
    }

    /**
     * Sets the weld value of this instance.
     *
     * @param weld
     */
    public void setWeld(boolean weld) {
        this.weld = weld;
    }

    /**
     * Gets the weld value of this instance.
     *
     * @return weld boolean value
     */
    public boolean getWeld() {
        return weld;
    }

    /**
     * Sets the position of which the Arrow is welded.
     *
     * @param weldPosition
     */
    public void setWeldPosition(Vec2 weldPosition) {
        this.weldPosition = weldPosition;
    }

    /**
     * Gets the weld position value of this instance.
     *
     * @return weld position
     */
    public Vec2 getWeldPosition() {
        return weldPosition;
    }

    /**
     * Gets the owner of the current Arrow.
     *
     * @return owner value
     */
    public int getOwner() {
        return owner;
    }

    /**
     * Gets the body sensor, full body, of the current Arrow entity.
     *
     * @return a copy of the body sensor
     */
    public Sensor getBodySensor() {
        return bodySensor;
    }

    /**
     * Gets the head sensor, arrow head, of the current Arrow entity.
     *
     * @return a copy of the head sensor
     */
    public Sensor getHeadSensor() {
        return headSensor;
    }

    /**
     * Gets a Timer value, the timer is used to arm the Arrow when initialised.
     *
     * @return a copy of the initial armed timer
     */
    public Timer getInitialArmedTimer() {
        return initialArmedTimer;
    }

    /**
     * Called immediately before each simulation step.
     *
     * @param e - the event descriptor
     */
    @Override
    public void preStep(StepEvent e) {
        if (weld) {
            this.setPosition(weldPosition);
        }
    }

    /**
     * Called immediately after each simulation step.
     *
     * @param e - the event descriptor
     */
    @Override
    public void postStep(StepEvent e) {
        if (armed || initialArmedTimer.isRunning()) {
            float flyingAngle = (float) Math.atan2(this.getLinearVelocity().y, this.getLinearVelocity().x);

            this.setAngle(flyingAngle);
        }

        if (weld) {
            this.setPosition(weldPosition);
        }

        if (this.getPosition().y < -60f || this.getPosition().y > 60f || this.getPosition().x < -42f || this.getPosition().x > 42f) {
            this.destroy();
        }
    }

    /**
     * Creates the ActionListener for the initial arming timer.
     */
    public class InitialArm implements ActionListener {

        Arrow arrow;

        /**
         * Creates a new instance and sets the arrow value locally for the
         * class.
         *
         * @param arrow The arrow being parsed.
         */
        public InitialArm(Arrow arrow) {
            this.arrow = arrow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            arrow.setArmed(true);
            arrow.getInitialArmedTimer().stop();
        }

    }

}
