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
import main.HelpPanel;
import main.OptionsPanel;

/**
 * A button used to access the OptionsPanel while playing the game
 *
 * @author jly09
 */
public class ControlsButton extends JButton {

    BufferedImage bimage = null;
    HelpPanel help;

    /**
     * Creates a Button with the specified x and y values.
     *
     * @param x Sets the x-value of the component on the screen.
     * @param y Sets the y-value of the component on the screen.
     * @param options Sets the OptionsPanel locally for the class to reference.
     */
    public ControlsButton(int x, int y, HelpPanel help) {
        this.help = help;
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

        this.setIcon(new ImageIcon("./data/buttons/controlButton.gif"));
//        this.setRolloverIcon(new ImageIcon("./data/buttons/optionsbuttonhover2.gif"));

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (help.getVisible()) {
                    help.setVisibility(false);
                } else {
                    help.setVisibility(true);
                }
            }
        }
        );

        repaint();
    }

}
