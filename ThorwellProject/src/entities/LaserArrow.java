/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Sensor;
import city.cs.engine.SolidFixture;
import city.cs.engine.StepEvent;
import city.cs.engine.World;
import handlers.ArrowBodySensor;
import handlers.ArrowHeadSensor;
import javax.swing.Timer;
import main.GameWorld;
import org.jbox2d.common.Vec2;

/**
 *
 * @author jly09
 */
public class LaserArrow extends Arrow {

    /**
     *
     */
    public static PolygonShape arrowHeadShape = new PolygonShape(0.25f, 0.3f, 0.550f, 0.3f, 0.8f, 0.05f, 0.8f, -0.05f, 0.550f, -0.3f, 0.25f, -0.3f);

    /**
     *
     */
    public static PolygonShape bodyShape = new PolygonShape(-0.8f, 0.3f, 0.550f, 0.3f, 0.8f, 0.05f, 0.8f, -0.05f, 0.550f, -0.3f, -0.8f, -0.3f);

    /**
     *
     * @param world
     * @param owner
     * @param armed
     * @param direction
     */
    public LaserArrow(GameWorld world, int owner, boolean armed, int direction) {
        super(world, owner, armed, direction);
        initialArm = new InitialArm(this);

        weld = false;
        weldPosition = new Vec2(0f, 0f);

        headSensor = new Sensor(this, arrowHeadShape, 0f);
        headSensor.addSensorListener(new ArrowHeadSensor(this, world));

        //headSensor.setDensity(5.6f);
        bodySensor = new Sensor(this, bodyShape, 0f);
        bodySensor.addSensorListener(new ArrowBodySensor(this, world));

        if (owner == 0) {
            bodyImage = new BodyImage("./data/projectiles/greenlaserarrow.png", 0.7f);
        } else if (owner == 1) {
            bodyImage = new BodyImage("./data/projectiles/redlaserarrow.png", 0.7f);
        }
        this.addImage(bodyImage);
        this.setBullet(true);
        this.setGravityScale(0);

        initialArmedTimer = new Timer(10, initialArm);
        initialArmedTimer.setRepeats(false);
        initialArmedTimer.start();

        this.setName("laserarrow");
        checkDirection();
    }

    /**
     *
     * @param world
     */
    public LaserArrow(GameWorld world) {
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

        bodyImage = new BodyImage("./data/projectiles/blanklaserarrow.png", 0.7f);
        this.addImage(bodyImage);

        fix = new SolidFixture(this, bodyShape);
    }

    /**
     *
     */
    @Override
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
                this.applyImpulse(new Vec2(54f, 0f));
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
                this.applyImpulse(new Vec2(-54f, 0f));
                break;
            //down left
            case 7:
                this.applyImpulse(new Vec2(-48f, -54f));
                break;
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void preStep(StepEvent e) {
        super.preStep(e);
    }

    /**
     *
     * @param e
     */
    @Override
    public void postStep(StepEvent e) {
        super.postStep(e);

    }

}
