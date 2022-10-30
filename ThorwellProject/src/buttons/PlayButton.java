/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttons;

import city.cs.engine.World;
import handlers.StateManager;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import main.GameView;
import main.GameWorld;
import main.OptionsPanel;

/**
 * A button used to start the game and leads to the Level Select screen,
 * accessible on the Main Menu.
 *
 * @author jly09
 */
public class PlayButton extends JButton {

    StateManager stateManager;
    GameView view;
    Dimension size = new Dimension(170, 78);

    /**
     * Creates a Button with the specified x and y values.
     *
     * @param x Sets the x-value of the component on the screen.
     * @param y Sets the y-value of the component on the screen.
     * @param world Sets the GameWorld for the class to allow reference to
     * GameWorld objects.
     * @param view Sets the GameView for the class to allow reference to
     * GameView objects.
     */
    public PlayButton(int x, int y, GameWorld world, GameView view) {
        this.view = view;
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setOpaque(false);
        this.setLocation((int) (x - (size.getWidth() / 2)), y);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setRolloverEnabled(true);
        this.setBorder(null);

        stateManager = world.getStateManager();

        this.setVisible(true);

        this.setIcon(new ImageIcon("./data/buttons/fightbtn.png"));
        this.setRolloverIcon(new ImageIcon("./data/buttons/fightbtnhover.png"));

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.showLevelSelect();
            }
        }
        );

        repaint();
    }
}
