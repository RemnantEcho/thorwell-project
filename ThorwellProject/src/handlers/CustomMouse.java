package handlers;

import main.GameView;
import city.cs.engine.WorldView;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import main.GameView;

/**
 * A mouse listener that changes mouse icon.
 */
public class CustomMouse extends MouseAdapter {

    private Component target;

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    /**
     *
     * @param target
     */
    public CustomMouse(Component target) {
        this.target = target;
        Image image = toolkit.getImage("./data/Cursor.gif");
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
        target.setCursor(c);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        target.requestFocus();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Click");
        Image image = toolkit.getImage("./data/CursorClick.gif");
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
        target.setCursor(c);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Image image = toolkit.getImage("./data/Cursor.gif");
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
        target.setCursor(c);
    }
}
