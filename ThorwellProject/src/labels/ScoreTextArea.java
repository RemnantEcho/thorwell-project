package labels;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author jly09
 */
public class ScoreTextArea extends JTextPane {

    /**
     *
     * @param x
     * @param y
     */
    public ScoreTextArea(int x, int y) {
        Font font = new Font("Arial", Font.BOLD, 32);
        int width = 100;
        int height = 100;
        this.setSize(width, height);
        this.setLocation(x - (width / 2), y);
        this.setFont(font);
        //this.setText("");
        this.setLayout(null);
        this.setEditable(false);
        this.setFocusable(false);
        this.setBorder(null);
        this.setOpaque(false);
        //this.setForeground(Color.BLACK);

        StyledDocument doc = this.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }
}
