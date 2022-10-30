package handlers;

import city.cs.engine.*;
import entities.*;
import main.GameWorld;
import org.jbox2d.common.Vec2;
import platforms.*;

/**
 * Sensor attached to player bodies; Checks whether players are on a
 * ground/platform, Sets values for players depending on the ground or whether
 * they're on the ground.
 *
 * @author jly09
 */
public class PlayerGroundSensor implements SensorListener {

    private GameWorld world;
    private Player1 p1 = null;
    private Player2 p2 = null;
    Scores scores;

    boolean passThrough;
    //private List<SolidFixture>

    /**
     *
     * @param world
     */
    public PlayerGroundSensor(GameWorld world) {
        this.world = world;
        scores = world.getScores();
    }

    /**
     *
     * @param e
     */
    @Override
    public void beginContact(SensorEvent e) {
        p1 = world.getPlayer1();
        p2 = world.getPlayer2();

        if (p1 != null) {
            if (e.getSensor() == p1.getFeet()) {
                if (e.getContactBody() instanceof Platform) {
                    p1.setOnGround(true);
                    p1.setAllowMomentum(false);

                    if (e.getContactBody() instanceof Ice) {
                        p1.setAllowMomentum(true);
                    } else if (e.getContactBody() instanceof Ground) {

                    }
                }
                System.out.println("Contact");
                if (e.getContactBody() instanceof PassableGround) {
//                        System.out.println("Contact");
//                        p1.setOnRide(true, 0, "passing");
                    }
                
                if (e.getContactBody() instanceof DynamicPlatform) {
                    p1.setOnGround(true);
                    p1.setAllowMomentum(false);

                    if (e.getContactBody() instanceof AutoMovingPlatform) {
                        AutoMovingPlatform amp = (AutoMovingPlatform) e.getContactBody();
                        
//                        p1.setLinearVelocity(new Vec2(p1.getLinearVelocity().x + amp.getSpeed(), 0));
                        p1.setOnRide(true, amp.getSpeed(), "horizontal");
                        p1.setAutoPlatform(amp);
//                        System.out.println("Velocity X: " + p1.getLinearVelocity().x + " Velocity Y: " + p1.getLinearVelocity().y);
                    }
                }
                
                if (e.getContactBody() instanceof Player2) {
                    p1.setOnGround(true);
                    p1.setAllowMomentum(false);
                    world.getSounds().playArrowHittingBody().play();
                    p2.setIsDead(true);
                    scores.incrementP1Score();
                    
                }
            }
            /*if (e.getSensor() == p1.getFeet() && e.getContactFixture() == p2.getHeadFixture()) {
                p2.setIsDead(true);
            }*/
        }

        if (p2 != null) {
            if (e.getSensor() == p2.getFeet()) {
                if (e.getContactBody() instanceof Platform) {
                    //System.out.println(p1.getAllowMomentum());
                    p2.setOnGround(true);
                    p2.setAllowMomentum(false);

                    if (e.getContactBody() instanceof Ice) {
                        p2.setAllowMomentum(true);
                    } else if (e.getContactBody() instanceof Ground) {

                    } else if (e.getContactBody() instanceof PassableGround) {
//                        p2.setOnRide(true, 0, "passing");
                    }
                }

                if (e.getContactBody() instanceof DynamicPlatform) {
                    p2.setOnGround(true);
                    p2.setAllowMomentum(false);

                    if (e.getContactBody() instanceof AutoMovingPlatform) {
                        AutoMovingPlatform amp = (AutoMovingPlatform) e.getContactBody();
                        
                        p2.setOnRide(true, amp.getSpeed(), "horizontal");
                        p2.setAutoPlatform(amp);
                    }
                }
                
                if (e.getContactBody() instanceof Player1) {
                    p2.setOnGround(true);
                    p2.setAllowMomentum(false);
                    world.getSounds().playArrowHittingBody().play();
                    p1.setIsDead(true);
                    scores.incrementP2Score();
                                
                    
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
        if (p1 != null) {
            if (e.getSensor() == p1.getFeet()) {
                if (e.getContactBody() instanceof Platform) {

                }
                

                if (e.getContactBody() instanceof AutoMovingPlatform) {
                        p1.setOnRide(false);
//                        System.out.println("Velocity X: " + p1.getLinearVelocity().x + " Velocity Y: " + p1.getLinearVelocity().y);
                }
                
            }
        }

        if (p2 != null) {

        }
    }

    /**
     *
     * @param p1
     */
    public void setPlayer1(Player1 p1) {
        this.p1 = p1;
    }

    /**
     *
     * @param p2
     */
    public void setPlayer2(Player2 p2) {
        this.p2 = p2;
    }

    /**
     *
     * @param world
     */
    public void setWorld(GameWorld world) {
        this.world = world;
    }

}
