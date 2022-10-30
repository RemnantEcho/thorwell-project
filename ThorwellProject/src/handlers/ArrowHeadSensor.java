package handlers;

import city.cs.engine.Fixture;
import city.cs.engine.Sensor;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import entities.Arrow;
import entities.DefaultArrow;
import entities.Icicle;
import entities.LaserArrow;
import entities.Player;
import entities.Player1;
import entities.Player2;
import java.util.List;
import main.GameWorld;
import org.jbox2d.common.Vec2;
import platforms.DynamicPlatform;
import platforms.Platform;

/**
 * Sensor for the arrow head, handles when arrow hits certain bodies within the
 * world; arrows hitting players destroys them and increments score, hitting
 * platforms anchors it to world.
 *
 */
public class ArrowHeadSensor implements SensorListener {

    GameWorld world;
    Arrow arrow;
    Player1 p1;
    Player2 p2;
    Scores scores;

    List<Fixture> fixtureList;
    Sensor arrowHead;

    /**
     *
     * @param arrow
     * @param world
     */
    public ArrowHeadSensor(Arrow arrow, GameWorld world) {
        this.arrow = arrow;
        this.world = world;
        scores = world.getScores();
        arrowHead = arrow.getHeadSensor();
        /*fixtureList = arrow.getFixtureList();
        for (Fixture fix : fixtureList) {
            if (fix == arrow.getArrowHeadFixture()) {
                arrowHead = fix;
            }
        }*/
    }

    /**
     *
     * @param e
     */
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getSensor() == arrowHead) {
            if (arrow.checkArmed()) {
                if (!arrow.getInitialArmedTimer().isRunning()) {
                    if (e.getContactBody() instanceof Player) {
                        if (e.getContactBody() instanceof Player1) {
                            p1 = world.getPlayer1();
                            if (p1.getDashing()) {
                                if (arrow instanceof DefaultArrow) {
                                    p1.incrementAmmo(new DefaultArrow(world));
                                }
                                else if (arrow instanceof LaserArrow) {
                                    p1.incrementAmmo(new LaserArrow(world));
                                }
                                
                            } else if (p1.getShielded()) {
                                rebound();

                                world.getSounds().playShieldHit().play();
                                p1.setShielded(false);

                            } else {
                                world.getSounds().playArrowHittingBody().play();
                                p1.setIsDead(true);

                                if (arrow.getOwner() == 0) {
                                    scores.decrementP1Score();
                                } else if (arrow.getOwner() == 1) {
                                    scores.incrementP2Score();
                                }
                            }

                        }

                        if (e.getContactBody() instanceof Player2) {
                            p2 = world.getPlayer2();
                            System.out.println(world.getPlayer2());
                            if (p2.getDashing()) {
                                if (arrow instanceof DefaultArrow) {
                                    p2.incrementAmmo(new DefaultArrow(world));
                                }
                                else if (arrow instanceof LaserArrow) {
                                    p2.incrementAmmo(new LaserArrow(world));
                                }
                            } else if (p2.getShielded()) {
                                rebound();

                                world.getSounds().playShieldHit().play();
                                p2.setShielded(false);

                            } else {
                                world.getSounds().playArrowHittingBody().play();
                                p2.setIsDead(true);

                                if (arrow.getOwner() == 0) {
                                    scores.incrementP1Score();
                                } else if (arrow.getOwner() == 1) {
                                    scores.decrementP2Score();
                                }
                            }

                        }
                    }
                }

            }

            /*if (e.getContactBody() instanceof Arrow && e.getContactBody() != arrow) {
                arrow.setLinearVelocity(new Vec2(0, -0.1f));
                world.getSounds().playArrowAgainstArrowHit().play();
            }*/
            if (e.getContactBody() instanceof Platform) {
                arrow.setArmed(false);
                arrow.setWeld(true);
                arrow.setWeldPosition(arrow.getPosition());
                if (e.getSensor().getBody() instanceof LaserArrow) {
                    arrow.setArmed(true);
                    arrow.setWeld(false);
                } else {
                    world.getSounds().playArrowHitting().play();
                }

            }

            if (e.getContactBody() instanceof DynamicPlatform) {
                arrow.setArmed(false);
                arrow.setWeld(true);
                arrow.setWeldPosition(arrow.getPosition());
                if (e.getSensor().getBody() instanceof LaserArrow) {
                    arrow.setArmed(true);
                    arrow.setWeld(false);
                } else {
                    world.getSounds().playArrowHitting().play();
                }
            }

            if (e.getContactBody() instanceof Icicle) {
                Icicle icicle = (Icicle) e.getContactBody();

                icicle.setArmed(true);
                icicle.setOwner(arrow.getOwner());
                rebound();
            }
        }
    }

    void rebound() {
        if (arrow.getLinearVelocity().y < 0) {
            if (arrow.getLinearVelocity().x < 0) {
                arrow.setLinearVelocity(new Vec2(-arrow.getLinearVelocity().x * 0.5f, -arrow.getLinearVelocity().y * 0.5f));
            } else if (arrow.getLinearVelocity().x > 0) {
                arrow.setLinearVelocity(new Vec2(-arrow.getLinearVelocity().x * 0.5f, -arrow.getLinearVelocity().y * 0.5f));
            } else if (arrow.getLinearVelocity().x == 0) {
                arrow.setLinearVelocity(new Vec2(-arrow.getLinearVelocity().x * 0.5f, -arrow.getLinearVelocity().y * 0.5f));
            }
        } else if (arrow.getLinearVelocity().y > 0) {
            if (arrow.getLinearVelocity().x < 0) {
                arrow.setLinearVelocity(new Vec2(-arrow.getLinearVelocity().x * 0.5f, -arrow.getLinearVelocity().y * 0.5f));
            } else if (arrow.getLinearVelocity().x > 0) {
                arrow.setLinearVelocity(new Vec2(-arrow.getLinearVelocity().x * 0.5f, -arrow.getLinearVelocity().y * 0.5f));
            } else if (arrow.getLinearVelocity().x == 0) {
                arrow.setLinearVelocity(new Vec2(-arrow.getLinearVelocity().x * 0.5f, -arrow.getLinearVelocity().y * 0.5f));
            }
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void endContact(SensorEvent e) {
        /*if (e.getSensor() == arrowHead) {
            if (e.getContactBody() instanceof Player) {
                if (!arrow.checkArmed()) {
                    arrow.setArmed(true);
                }
            }
        }*/
    }

}
