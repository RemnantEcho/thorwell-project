/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import entities.Arms;
import entities.Player;
import entities.Player1;
import entities.Player2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import main.GameWorld;
import org.jbox2d.common.Vec2;

/**
 * Action listener used to allow players to continue dashing, after which this
 * class will set the dashing to false and stop all momentum that would have
 * occurred as well as putting the dash on cooldown.
 *
 * @author jly09
 */
public class DashHandler implements ActionListener {

    GameWorld world;
    Player player;
    Timer timer;

    long delay;
    long elapsed;

    /**
     *
     * @param world
     * @param player
     */
    public DashHandler(GameWorld world, Player player) {
        this.world = world;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (player instanceof Player1) {
            Player1 p1 = world.getPlayer1();

            p1.setDashing(false);
            p1.setDashOnCooldown(true);
            p1.setLinearVelocity(new Vec2(0, 0));
            p1.getDashTimer().stop();
        } else if (player instanceof Player2) {
            Player2 p2 = world.getPlayer2();

            p2.setDashing(false);
            p2.setDashOnCooldown(true);
            p2.setLinearVelocity(new Vec2(0, 0));
            p2.getDashTimer().stop();
        }

        /*for (Timer t: world.getTimerList()) {
            if (timer == t) {
                t.stop();
            }
        }*/
    }

    /**
     *
     * @param timer
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

}
