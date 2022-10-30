package labels;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author jly09
 */
public class ScoreLabel extends JLabel {

    /**
     *
     * @param x
     * @param y
     */
    public ScoreLabel(int x, int y) {
        Font font = new Font("Arial", Font.BOLD, 64);
        int width = 500;
        int height = 200;
        this.setSize(width, height);
        this.setLocation(x - (width / 2), y);
        this.setFont(font);
        //this.setText("2:00");
        this.setLayout(null);
        this.setHorizontalAlignment(CENTER);
        this.setForeground(Color.WHITE);

    }
}
