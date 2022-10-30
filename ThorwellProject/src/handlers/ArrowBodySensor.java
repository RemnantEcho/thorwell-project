package handlers;

import city.cs.engine.Fixture;
import city.cs.engine.Sensor;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import entities.Arrow;
import entities.DefaultArrow;
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
 * Sensor for the whole arrow body, used to allow players to pick up arrows
 * which are disarmed or when player is dashing.
 *
 */
public class ArrowBodySensor implements SensorListener {

    GameWorld world;
    Arrow arrow;
    Player1 p1;
    Player2 p2;
    Scores scores;

    List<Fixture> fixtureList;
    Sensor arrowBody;

    /**
     *
     * @param arrow
     * @param world
     */
    public ArrowBodySensor(Arrow arrow, GameWorld world) {
        this.arrow = arrow;
        this.world = world;
        scores = world.getScores();
        arrowBody = arrow.getBodySensor();
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
        if (e.getSensor() == arrowBody) {
            //Pick up arrows
            if (!arrow.checkArmed()) {
                if (!arrow.getInitialArmedTimer().isRunning()) {
                    if (e.getContactBody() instanceof Player) {
                        if (e.getContactBody() instanceof Player1) {
                            p1 = world.getPlayer1();
                            if (p1.getAmmo() < 6) {
                                if (e.getSensor().getBody() instanceof DefaultArrow) {
                                    p1.incrementAmmo(new DefaultArrow(world));
                                }

                                arrow.destroy();
                            }

                        }

                        if (e.getContactBody() instanceof Player2) {
                            p2 = world.getPlayer2();
                            if (p2.getAmmo() < 6) {
                                if (e.getSensor().getBody() instanceof DefaultArrow) {
                                    p2.incrementAmmo(new DefaultArrow(world));
                                }
                                arrow.destroy();
                            }
                        }
                    }
                }

            } else {
                if (e.getContactBody() instanceof Player) {
                    if (e.getContactBody() instanceof Player1) {
                        p1 = world.getPlayer1();
                        if (p1.getDashing()) {
                            if (p1.getAmmo() < 6) {
                                if (e.getSensor().getBody() instanceof DefaultArrow) {
                                    p1.incrementAmmo(new DefaultArrow(world));
                                }

                                arrow.destroy();
                            }
                        }
                    }

                    if (e.getContactBody() instanceof Player2) {
                        p2 = world.getPlayer2();
                        if (p2.getDashing()) {
                            if (p2.getAmmo() < 6) {
                                if (e.getSensor().getBody() instanceof DefaultArrow) {
                                    p2.incrementAmmo(new DefaultArrow(world));
                                }
                                arrow.destroy();
                            }
                        }
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
