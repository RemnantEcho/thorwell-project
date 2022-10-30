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
import platforms.PassableGround;
import platforms.Platform;

/**
 * Sensor used to detect when a Auto Moving Platform hits a wall and moves back
 * and forth corresponding to the sensor colliding with.
 *
 * @author jly09
 */
public class PassableGroundSensor implements SensorListener {

    GameWorld world;
    PassableGround pg;
    Player1 p1;
    Player2 p2;

    /**
     *
     * @param world
     * @param amp
     */
    public PassableGroundSensor(GameWorld world, PassableGround pg) {
        this.world = world;
        this.pg = pg;
        
        p1 = world.getPlayer1();
        p2 = world.getPlayer2();
    }

    /**
     *
     * @param e
     */
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getSensor() == pg.getGroundSensor()) {
            
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void endContact(SensorEvent e) {
        if (e.getSensor() == pg.getGroundSensor()) {
            if (e.getContactBody() == p1) {
                p1.setOnRide(false);
                p1.setPassthrough(false);
            }
            
            if (e.getContactBody() == p2) {
                p2.setOnRide(false);
                p2.setPassthrough(false);
            }
        }
    }

}
