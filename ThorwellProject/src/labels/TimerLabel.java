package labels;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author jly09
 */
public class TimerLabel extends JLabel {

    /**
     *
     * @param x
     * @param y
     */
    public TimerLabel(int x, int y) {
        Font font = new Font("Arial", Font.BOLD, 32);
        int width = 100;
        int height = 100;
        this.setSize(width, height);
        this.setLocation(x - (width / 2), y);
        this.setFont(font);
        //this.setText("2:00");
        this.setLayout(null);
        this.setHorizontalAlignment(CENTER);
        this.setForeground(Color.WHITE);
    }
}
