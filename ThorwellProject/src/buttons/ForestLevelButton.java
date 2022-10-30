package buttons;

import city.cs.engine.World;
import handlers.LevelSelector;
import handlers.StateManager;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import main.GameWorld;
import main.OptionsPanel;

/**
 * Button used to select the ForestLevel while the game is running.
 *
 * @author jly09
 */
public class ForestLevelButton extends JButton {

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
    public ForestLevelButton(int x, int y, GameWorld world, StateManager stateManager) {
        this.world = world;
        this.stateManager = stateManager;

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

        this.setIcon(new ImageIcon("./data/buttons/forestlevelbuttonwithtext.png"));
        this.setRolloverIcon(new ImageIcon("./data/buttons/forestlevelbuttonhoverwithtext.png"));

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                levelSelect = levelSelect.ForestLevel;
                world.setLevelSelector(levelSelect);

                stateManager.setLevelCode(1);
                stateManager.checkState(false);
            }
        }
        );

        repaint();
    }
}
