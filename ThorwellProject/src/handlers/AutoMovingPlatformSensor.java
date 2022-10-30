package handlers;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.Sensor;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import entities.Player1;
import entities.Player2;
import main.GameWorld;
import platforms.AutoMovingPlatform;
import platforms.DynamicPlatform;
import platforms.Platform;

/**
 * Sensor used to detect when a Auto Moving Platform hits a wall and moves back
 * and forth corresponding to the sensor colliding with.
 *
 * @author jly09
 */
public class AutoMovingPlatformSensor implements SensorListener {

    GameWorld world;
    AutoMovingPlatform amp;
    Player1 p1;
    Player2 p2;

    /**
     *
     * @param world
     * @param amp
     */
    public AutoMovingPlatformSensor(GameWorld world, AutoMovingPlatform amp) {
        this.world = world;
        this.amp = amp;
    }

    /**
     *
     * @param e
     */
    @Override
    public void beginContact(SensorEvent e) {
        p1 = world.getPlayer1();
        p2 = world.getPlayer2();
        
        if (e.getSensor() == amp.getLeftSensor()) {
            if (e.getContactBody() instanceof Platform) {
                if (amp.getDirection()) {
                    amp.setSpeed(-Math.abs(amp.getSpeed()));
                    if (p1.getAutoPlatform() == amp) {
                        p1.setRideSpeed(-Math.abs(amp.getSpeed()));
                    }
                    if (p2.getAutoPlatform() == amp) {
                        p2.setRideSpeed(-Math.abs(amp.getSpeed()));
                    }
                } else {
                    System.out.println("Go Right");
                    amp.setSpeed(Math.abs(amp.getSpeed()));
                    if (p1.getAutoPlatform() == amp) {
                        p1.setRideSpeed(Math.abs(amp.getSpeed()));
                    }
                    if (p2.getAutoPlatform() == amp) {
                        p2.setRideSpeed(Math.abs(amp.getSpeed()));
                    }
                }
            }

            if (e.getContactBody() instanceof DynamicPlatform) {
                if (amp.getDirection()) {
                    amp.setSpeed(-Math.abs(amp.getSpeed()));
                    if (p1.getAutoPlatform() == amp) {
                        p1.setRideSpeed(-Math.abs(amp.getSpeed()));
                    }
                    if (p2.getAutoPlatform() == amp) {
                        p2.setRideSpeed(-Math.abs(amp.getSpeed()));
                    }
                } else {
                    System.out.println("Go Right");
                    amp.setSpeed(Math.abs(amp.getSpeed()));
                    if (p1.getAutoPlatform() == amp) {
                        p1.setRideSpeed(Math.abs(amp.getSpeed()));
                    }
                    if (p2.getAutoPlatform() == amp) {
                        p2.setRideSpeed(Math.abs(amp.getSpeed()));
                    }
                }
            }
        }

        if (e.getSensor() == amp.getRightSensor()) {
            if (e.getContactBody() instanceof Platform) {
                if (amp.getDirection()) {
                    amp.setSpeed(-Math.abs(amp.getSpeed()));
                    if (p1.getAutoPlatform() == amp) {
                        p1.setRideSpeed(-Math.abs(amp.getSpeed()));
                    }
                    if (p2.getAutoPlatform() == amp) {
                        p2.setRideSpeed(-Math.abs(amp.getSpeed()));
                    }
                } else {
                    System.out.println("Go Right");
                    amp.setSpeed(Math.abs(amp.getSpeed()));
                    if (p1.getAutoPlatform() == amp) {
                        p1.setRideSpeed(Math.abs(amp.getSpeed()));
                    }
                    if (p2.getAutoPlatform() == amp) {
                        p2.setRideSpeed(Math.abs(amp.getSpeed()));
                    }
                }
            }

            if (e.getContactBody() instanceof DynamicPlatform) {
                if (amp.getDirection()) {
                    amp.setSpeed(-Math.abs(amp.getSpeed()));
                    if (p1.getAutoPlatform() == amp) {
                        p1.setRideSpeed(-Math.abs(amp.getSpeed()));
                    }
                    if (p2.getAutoPlatform() == amp) {
                        p2.setRideSpeed(-Math.abs(amp.getSpeed()));
                    }
                } else {
                    System.out.println("Go Right");
                    amp.setSpeed(Math.abs(amp.getSpeed()));
                    if (p1.getAutoPlatform() == amp) {
                        p1.setRideSpeed(Math.abs(amp.getSpeed()));
                    }
                    if (p2.getAutoPlatform() == amp) {
                        p2.setRideSpeed(Math.abs(amp.getSpeed()));
                    }
                }
            }
        }

    }

    /**
     *
     * @param e
     */
    @Override
    public void endContact(SensorEvent e) {

    }

}
