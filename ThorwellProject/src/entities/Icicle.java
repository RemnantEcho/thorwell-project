/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import city.cs.engine.Fixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import handlers.IcicleCollision;
import main.GameWorld;
import org.jbox2d.common.Vec2;

/**
 *
 * @author jly09
 */
public class Icicle extends DynamicBody implements StepListener {

    SolidFixture sfix;
    Shape shape = new PolygonShape(0.698f, 0.689f, -0.002f, -0.643f, -0.698f, 0.689f);

    boolean armed;
    Integer owner;
    Vec2 startPosition;

    /**
     *
     * @param world
     * @param startPosition
     */
    public Icicle(GameWorld world, Vec2 startPosition) {
        super(world);
        this.startPosition = startPosition;
        this.setPosition(startPosition);

        sfix = new SolidFixture(this, shape);
        sfix.setDensity(4f);
        sfix.setFriction(0);
        sfix.setRestitution(0);

        this.setGravityScale(0);
        armed = false;
        owner = null;

        this.addImage(new BodyImage("./data/icicle.png", 1.4f));
        this.addCollisionListener(new IcicleCollision(this, world));
        this.setName("icicle");
        
        
        world.addStepListener(this);
    }

    /**
     *
     * @return
     */
    public Fixture getFixture() {
        return sfix;
    }

    /**
     *
     * @return
     */
    public boolean getArmed() {
        return armed;
    }

    /**
     *
     * @param armed
     */
    public void setArmed(boolean armed) {
        this.armed = armed;
    }

    /**
     *
     * @param owner
     */
    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    /**
     *
     * @return
     */
    public Integer getOwner() {
        return owner;
    }

    /**
     *
     * @param e
     */
    @Override
    public void preStep(StepEvent e) {

    }

    /**
     *
     * @param e
     */
    @Override
    public void postStep(StepEvent e) {
        if (armed) {
            this.setGravityScale(10);

        } else {
            this.setPosition(startPosition);
            this.setGravityScale(0);
        }
    }
}
