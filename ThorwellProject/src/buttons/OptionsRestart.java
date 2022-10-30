/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttons;

import city.cs.engine.World;
import handlers.LevelSelector;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import main.GameWorld;
import main.OptionsPanel;

/**
 * Button used to restart the current round of the game, accessible only through
 * the Options Panel.
 *
 * @author jly09
 */
public class OptionsRestart extends JButton {

    OptionsPanel options;
    GameWorld world;
    LevelSelector levelSelect;
    Dimension size = new Dimension(212, 38);

    /**
     * Creates a Button with the specified x and y values.
     *
     * @param x Sets the x-value of the component on the screen.
     * @param y Sets the y-value of the component on the screen.
     * @param world Sets the GameWorld for the class to allow reference to
     * GameWorld objects.
     * @param options Sets the OptionsPanel locally for the class to reference.
     */
    public OptionsRestart(int x, int y, OptionsPanel options, GameWorld world) {
        this.options = options;
        this.world = world;
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setOpaque(false);
        this.setLocation(x, y);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setRolloverEnabled(true);
        this.setBorder(null);

        this.levelSelect = world.getLevelSelector();
        this.setVisible(true);

        this.setIcon(new ImageIcon("./data/buttons/restartbtn.png"));
        this.setRolloverIcon(new ImageIcon("./data/buttons/restartbtnhover.png"));

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                world.resetWorld();
                world.getStateManager().checkState(true);
                world.oneStep();
                world.getRoundHandler().resetTimer(true);
                world.getItemSpawn().resetTimer();
                world.getScores().resetScores();
                if (options.getVisible()) {
                    options.setVisibility(false);
                }
                //options.setVisibility(true);
            }
        }
        );

        repaint();
    }
}
