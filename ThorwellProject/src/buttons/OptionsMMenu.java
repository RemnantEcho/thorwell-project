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
import main.GameWorld;
import main.OptionsPanel;

/**
 * A button used to go to the MainMenu, added to the Options Panel.
 *
 * @author jly09
 */
public class OptionsMMenu extends JButton {

    OptionsPanel options;
    StateManager stateManager;
    Dimension size = new Dimension(135, 78);

    /**
     * Creates a Button with the specified x and y values.
     *
     * @param x Sets the x-value of the component on the screen.
     * @param y Sets the y-value of the component on the screen.
     * @param options Sets the OptionsPanel locally for the class to reference.
     * @param world Sets the GameWorld for the class to allow reference to
     * GameWorld objects.
     */
    public OptionsMMenu(int x, int y, OptionsPanel options, GameWorld world) {
        this.options = options;
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

        stateManager = world.getStateManager();

        this.setVisible(true);

        this.setIcon(new ImageIcon("./data/buttons/mainbtn.png"));
        this.setRolloverIcon(new ImageIcon("./data/buttons/mainbtnhover.png"));

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                options.setVisibility(false);
                stateManager.setLevelCode(0);
                stateManager.checkState(false);
            }
        }
        );

        repaint();
    }
}
