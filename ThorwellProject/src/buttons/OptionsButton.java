package buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import main.OptionsPanel;

/**
 * A button used to access the OptionsPanel while playing the game
 *
 * @author jly09
 */
public class OptionsButton extends JButton {

    BufferedImage bimage = null;
    OptionsPanel options;

    /**
     * Creates a Button with the specified x and y values.
     *
     * @param x Sets the x-value of the component on the screen.
     * @param y Sets the y-value of the component on the screen.
     * @param options Sets the OptionsPanel locally for the class to reference.
     */
    public OptionsButton(int x, int y, OptionsPanel options) {
        this.options = options;
        this.setSize(new Dimension(65, 65));
        this.setPreferredSize(new Dimension(65, 65));
        this.setMinimumSize(new Dimension(65, 65));
        this.setMaximumSize(new Dimension(65, 65));
        this.setOpaque(false);
        this.setLocation(x, y);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setRolloverEnabled(true);
        this.setBorder(null);

        this.setVisible(true);

        this.setIcon(new ImageIcon("./data/buttons/optionsbutton2.gif"));
        this.setRolloverIcon(new ImageIcon("./data/buttons/optionsbuttonhover2.gif"));

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (options.getVisible()) {
                    options.setVisibility(false);
                } else {
                    options.setVisibility(true);
                }
            }
        }
        );

        repaint();
    }

}
