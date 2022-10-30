/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import main.OptionsPanel;

/**
 * A button, which on press, closes the program, exclusive to the Options Panel.
 *
 * @author jly09
 */
public class OptionsQuit extends JButton {

    OptionsPanel options;
    Dimension size = new Dimension(126, 38);

    /**
     * Creates a Button with the specified x and y values.
     *
     * @param x Sets the x-value of the component on the screen.
     * @param y Sets the y-value of the component on the screen.
     * @param options Sets the OptionsPanel locally for the class to reference.
     */
    public OptionsQuit(int x, int y, OptionsPanel options) {
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

        this.setVisible(true);

        this.setIcon(new ImageIcon("./data/buttons/quitbtn.png"));
        this.setRolloverIcon(new ImageIcon("./data/buttons/quitbtnhover.png"));

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        );

        repaint();
    }

}
