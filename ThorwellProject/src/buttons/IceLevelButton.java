package buttons;

import city.cs.engine.World;
import handlers.LevelSelector;
import handlers.StateManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import main.GameWorld;
import main.OptionsPanel;

/**
 * Button used to select the IceLevel while the game is running.
 *
 * @author jly09
 */
public class IceLevelButton extends JButton {

    GameWorld world;
    StateManager stateManager;
    LevelSelector levelSelect;
    Dimension size = new Dimension(280, 840);

    /**
     * Creates a Button with the specified x and y values.
     *
     * @param x Sets the x-value of the component on the screen.
     * @param y Sets the y-value of the component on the screen.
     * @param world Sets the GameWorld for the class to allow reference to
     * GameWorld objects.
     * @param stateManager Sets the StateManager to allow dynamic changing of
     * the Game States.
     */
    public IceLevelButton(int x, int y, GameWorld world, StateManager stateManager) {
        this.world = world;
        this.stateManager = stateManager;

        this.setSize(size);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setOpaque(true);
        this.setBackground(Color.RED);
        this.setLocation(x, y);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setBorder(null);

        checkLocked();

        this.levelSelect = world.getLevelSelector();
        this.setVisible(true);

        this.setIcon(new ImageIcon("./data/buttons/icelevelbuttonwithtext.png"));
        this.setRolloverIcon(new ImageIcon("./data/buttons/icelevelbuttonhoverwithtext.png"));

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!world.getIceLevel().getLocked()) {
                    levelSelect = levelSelect.IceLevel;
                    world.setLevelSelector(levelSelect);

                    stateManager.setLevelCode(1);
                    stateManager.checkState(false);
                }
            }
        }
        );

        repaint();
    }

    /**
     * Checks whether the IceLevel is locked and disables or enables the button
     * depending on the result.
     */
    public void checkLocked() {
        if (world.getIceLevel().getLocked()) {
            this.setRolloverEnabled(false);
        } else {
            this.setRolloverEnabled(true);
        }

        this.repaint();
    }
}
