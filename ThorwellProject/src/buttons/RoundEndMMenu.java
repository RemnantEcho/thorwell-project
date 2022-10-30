/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttons;

import city.cs.engine.World;
import handlers.StateManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import main.GameWorld;

/**
 * A button used to go to the MainMenu, accessible when a round has ended.
 *
 * @author jly09
 */
public class RoundEndMMenu extends JButton {

    StateManager stateManager;
    Dimension size = new Dimension(135, 78);

    /**
     * Creates a Button with the specified x and y values.
     *
     * @param x Sets the x-value of the component on the screen.
     * @param y Sets the y-value of the component on the screen.
     * @param world Sets the GameWorld for the class to allow reference to
     * GameWorld objects.
     */
    public RoundEndMMenu(int x, int y, GameWorld world) {
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setOpaque(false);
        this.setLocation(x - (size.width / 2), y);
        //this.setContentAreaFilled(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setRolloverEnabled(true);
        this.setBorder(null);

        this.setBackground(Color.RED);

        stateManager = world.getStateManager();

        this.setVisible(true);

        this.setIcon(new ImageIcon("./data/buttons/mainbtn.png"));
        this.setRolloverIcon(new ImageIcon("./data/buttons/mainbtnhover.png"));

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stateManager.setLevelCode(0);
                stateManager.checkState(false);
            }
        }
        );

        repaint();
    }
}
