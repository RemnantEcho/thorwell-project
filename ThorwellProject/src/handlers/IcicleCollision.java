/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import entities.Arrow;
import entities.Icicle;
import entities.Player;
import entities.Player1;
import entities.Player2;
import main.GameWorld;
import org.jbox2d.common.Vec2;
import platforms.Platform;

/**
 * Collision listener which handles the collision of the icicle against bodies,
 * icicle hitting the player will destroy the player, icicle hitting the
 * ground/platform would destroy the icicle,
 *
 * @author jly09
 */
public class IcicleCollision implements CollisionListener {

    Icicle icicle;
    GameWorld world;
    Player1 p1;
    Player2 p2;
    Scores scores;

    /**
     *
     * @param icicle
     * @param world
     */
    public IcicleCollision(Icicle icicle, GameWorld world) {
        this.icicle = icicle;
        this.world = world;

        scores = world.getScores();
    }

    /**
     *
     * @param e
     */
    @Override
    public void collide(CollisionEvent e) {
        p1 = world.getPlayer1();
        p2 = world.getPlayer2();

        if (e.getReportingBody() == icicle && e.getOtherBody() instanceof Platform) {
            if (icicle.getArmed()) {
                icicle.destroy();
            }
        }

        if (e.getReportingBody() == icicle) {
            if (e.getOtherBody() == p1) {
                icicle.setPosition(icicle.getPosition());
                icicle.setLinearVelocity(new Vec2(0, icicle.getLinearVelocity().y));
                icicle.setAngleDegrees(0);

                if (null == icicle.getOwner()) {
                    scores.decrementP1Score();
                } else {
                    switch (icicle.getOwner()) {
                        case 0:
                            scores.decrementP1Score();
                            break;
                        case 1:
                            scores.incrementP2Score();
                            break;
                        default:
                            break;
                    }
                }
                p1.setIsDead(true);
            }

            if (e.getOtherBody() == p2) {
                icicle.setPosition(icicle.getPosition());
                icicle.setLinearVelocity(new Vec2(0, icicle.getLinearVelocity().y));
                icicle.setAngleDegrees(0);

                if (null == icicle.getOwner()) {
                    scores.decrementP2Score();
                } else {
                    switch (icicle.getOwner()) {
                        case 0:
                            scores.incrementP1Score();
                            break;
                        case 1:
                            scores.decrementP2Score();
                            break;
                        default:
                            break;
                    }
                }
                p2.setIsDead(true);
            }
        }

    }

}
