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
public class PlayerWallSensor implements SensorListener {

    private GameWorld world;
    private Player1 p1 = null;
    private Player2 p2 = null;

    //private List<SolidFixture>

    /**
     *
     * @param world
     */
    public PlayerWallSensor(GameWorld world) {
        this.world = world;
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
            if (e.getSensor() == p1.getLeft() && p1.getLeaveWall() == false) {
                    if (e.getContactBody() instanceof Platform) {
                        p1.setOnWall(true, false);

                        if (e.getContactBody() instanceof Ice) {

                        } else if (e.getContactBody() instanceof Ground) {

                        }
                    }

                    if (e.getContactBody() instanceof DynamicPlatform) {
                        p1.setOnWall(true, false);

                        if (e.getContactBody() instanceof AutoMovingPlatform) {
                            AutoMovingPlatform amp = (AutoMovingPlatform) e.getContactBody();
                            p1.setLinearVelocity(new Vec2(amp.getSpeed(), 0));
                        }
                    }
            }
            else if (e.getSensor() == p1.getRight() && p1.getLeaveWall() == false) {
                 if (e.getContactBody() instanceof Platform) {
                        p1.setOnWall(true, true);

                        if (e.getContactBody() instanceof Ice) {

                        } else if (e.getContactBody() instanceof Ground) {

                        }
                    }

                    if (e.getContactBody() instanceof DynamicPlatform) {
                        p1.setOnWall(true, true);

                        if (e.getContactBody() instanceof AutoMovingPlatform) {
                            AutoMovingPlatform amp = (AutoMovingPlatform) e.getContactBody();
                            p1.setLinearVelocity(new Vec2(amp.getSpeed(), 0));
                        }
                }
            }
            

        }

        if (p2 != null) {
            if (e.getSensor() == p2.getLeft() && p2.getLeaveWall() == false) {
                    if (e.getContactBody() instanceof Platform) {
                        p2.setOnWall(true, false);

                        if (e.getContactBody() instanceof Ice) {

                        } else if (e.getContactBody() instanceof Ground) {

                        }
                    }

                    if (e.getContactBody() instanceof DynamicPlatform) {
                        p2.setOnWall(true, false);

                        if (e.getContactBody() instanceof AutoMovingPlatform) {
                            AutoMovingPlatform amp = (AutoMovingPlatform) e.getContactBody();
                            p1.setLinearVelocity(new Vec2(amp.getSpeed(), 0));
                        }
                    }
            }
            else if (e.getSensor() == p2.getRight() && p2.getLeaveWall() == false) {
                 if (e.getContactBody() instanceof Platform) {
                        p2.setOnWall(true, true);

                        if (e.getContactBody() instanceof Ice) {

                        } else if (e.getContactBody() instanceof Ground) {

                        }
                    }

                    if (e.getContactBody() instanceof DynamicPlatform) {
                        p2.setOnWall(true, true);

                        if (e.getContactBody() instanceof AutoMovingPlatform) {
                            AutoMovingPlatform amp = (AutoMovingPlatform) e.getContactBody();
                            p2.setLinearVelocity(new Vec2(amp.getSpeed(), 0));
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
        p1 = world.getPlayer1();
        p2 = world.getPlayer2();

        if (p1 != null) {
            if (e.getSensor() == p1.getLeft()) {
                    if (e.getContactBody() instanceof Platform) {
                        p1.setOnWall(false, false);
                        p1.setLeaveWall(false);
                        p1.setWallJumped(false);
                    }

                    if (e.getContactBody() instanceof DynamicPlatform) {
                        p1.setOnWall(false, false);
                        p1.setLeaveWall(false);
                        p1.setWallJumped(false);
                    }
            }
            else if (e.getSensor() == p1.getRight()) {
                 if (e.getContactBody() instanceof Platform) {
                        p1.setOnWall(false, true);
                        p1.setLeaveWall(false);
                        p1.setWallJumped(false);
                 }

                    if (e.getContactBody() instanceof DynamicPlatform) {
                        p1.setOnWall(false, true);
                        p1.setLeaveWall(false);
                        p1.setWallJumped(false);
                }
            }
            

        }

        if (p2 != null) {
            if (e.getSensor() == p2.getLeft()) {
                    if (e.getContactBody() instanceof Platform) {
                        p2.setOnWall(false, false);
                        p2.setLeaveWall(false);
                        p2.setWallJumped(false);
                    }

                    if (e.getContactBody() instanceof DynamicPlatform) {
                        p2.setOnWall(false, false);
                        p2.setLeaveWall(false);
                        p2.setWallJumped(false);
                    }
            }
            else if (e.getSensor() == p2.getRight()) {
                 if (e.getContactBody() instanceof Platform) {
                        p2.setOnWall(false, true);
                        p2.setLeaveWall(false);
                        p2.setWallJumped(false);
                    }

                    if (e.getContactBody() instanceof DynamicPlatform) {
                        p2.setOnWall(false, true);
                        p2.setLeaveWall(false);
                        p2.setWallJumped(false);
                }
            }
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
