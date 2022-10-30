package labels;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author jly09
 */
public class TitleLabel extends JLabel {

    /**
     *
     * @param x
     * @param y
     */
    public TitleLabel(int x, int y) {
        Font font = new Font("Arial", Font.BOLD, 32);
        ImageIcon icon = new ImageIcon("./data/titlelabel.png");
        int width = 400;
        int height = 100;
        this.setSize(width, height);
        this.setLocation(x - (width / 2), y);
        this.setFont(font);
        this.setText("");
        this.setLayout(null);
        this.setHorizontalAlignment(CENTER);
        this.setForeground(Color.BLACK);
        this.setIcon(icon);
    }
}
