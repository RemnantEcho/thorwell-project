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

/**
 * Action listener for the cooldown of the player after dashing, after the timer
 * reaches the delay this class will set the player dash cooldown boolean
 * variable to false to allow players to dash again.
 *
 * @author jly09
 */
public class DashCooldown implements ActionListener {

    GameWorld world;
    Player player;
    Timer timer;

    /**
     *
     * @param world
     * @param player
     */
    public DashCooldown(GameWorld world, Player player) {
        this.world = world;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (player instanceof Player1) {
            Player1 p1 = world.getPlayer1();

            p1.setDashOnCooldown(false);
            p1.getDashCooldown().stop();
        } else if (player instanceof Player2) {
            Player2 p2 = world.getPlayer2();

            p2.setDashOnCooldown(false);
            p2.getDashCooldown().stop();
        }

    }
}
