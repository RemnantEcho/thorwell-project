package entities;

import city.cs.engine.AttachedImage;
import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import main.GameWorld;
import org.jbox2d.common.Vec2;

/**
 * This class is used to create (and display) the arms of the player and allows
 * for the direction to change according to specified inputs.
 *
 * @author jly09
 */
public class Arms extends StaticBody {

    List<BodyImage> armImageList = new ArrayList<>();
    ;
    AttachedImage aimage;
    int armCode;

    private final Shape shape = new PolygonShape(
            0.35f, 1.55f, 0.95f, 0.7f, 0.89f, -1.05f, 0.6f, -1.6f, -0.48f, -1.6f, -0.97f, 0.65f, 0.09f, 1.56f);

    /**
     * Creates the arm and sets the image depending on the imageCode parsed.
     *
     * @param world Sets the GameWorld for the class to reference freely.
     * @param imageCode Sets the relevant image depending on the value.
     */
    public Arms(GameWorld world, int imageCode) {
        super(world);

        switch (imageCode) {
            case 0:
                armImageList.add(new BodyImage("./data/charsprite/bowUpGreen.png", 3.2f));
                armImageList.add(new BodyImage("./data/charsprite/bowDownGreen.png", 3.2f));
                armImageList.add(new BodyImage("./data/charsprite/bowupLeftGreen.png", 3.2f));
                armImageList.add(new BodyImage("./data/charsprite/bowLeftGreen.png", 3.2f));
                armImageList.add(new BodyImage("./data/charsprite/bowdownLeftGreen.png", 3.2f));
                break;
            case 1:
                armImageList.add(new BodyImage("./data/charsprite/bowUpRed.png", 3.2f));
                armImageList.add(new BodyImage("./data/charsprite/bowDownRed.png", 3.2f));
                armImageList.add(new BodyImage("./data/charsprite/bowupLeftRed.png", 3.2f));
                armImageList.add(new BodyImage("./data/charsprite/bowLeftRed.png", 3.2f));
                armImageList.add(new BodyImage("./data/charsprite/bowdownLeftRed.png", 3.2f));
                break;
        }

        this.addImage(armImageList.get(3));

        aimage = new AttachedImage(this, armImageList.get(3), 0, 0, new Vec2(0, 0));
        //SolidFixture sfix = new SolidFixture(this, shape);
    }

    /**
     * Sets the arm image corresponding to armCode value, visibly changing the
     * direction of the arm.
     *
     * @param armCode Sets the arm image based on the armCode.
     */
    public void setArms(int armCode) {
        this.armCode = armCode;
        this.removeAllImages();

        switch (armCode) {
            //Facing Right
            //up
            case 0:
                this.addImage(armImageList.get(0));
                flipHorizontal();
                break;
            //down
            case 1:
                this.addImage(armImageList.get(1));
                flipHorizontal();
                break;
            //up right
            case 2:
                this.addImage(armImageList.get(2));
                flipHorizontal();
                break;
            //right
            case 3:
                this.addImage(armImageList.get(3));
                flipHorizontal();
                break;
            //down right
            case 4:
                this.addImage(armImageList.get(4));
                flipHorizontal();
                break;
            //Facing Left
            //up
            case 5:
                this.addImage(armImageList.get(0));

                break;
            //down
            case 6:
                this.addImage(armImageList.get(1));
                break;
            //up left
            case 7:
                this.addImage(armImageList.get(2));
                break;
            //left
            case 8:
                this.addImage(armImageList.get(3));
                break;
            //down left
            case 9:
                this.addImage(armImageList.get(4));
                break;
        }
    }

    /**
     * Flips the image of the first entry within the image List of the body
     * horizontally.
     */
    public void flipHorizontal() {
        List<AttachedImage> aimagelist = new ArrayList<>(this.getImages());
        AttachedImage aimage;
        aimage = aimagelist.get(0);
        aimage.flipHorizontal();
    }

    /**
     * The arm code of this class instance.
     *
     * @return a copy of the arm code
     */
    public int getArmCode() {
        return armCode;
    }
}
