package platforms;

import city.cs.engine.BodyImage;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import java.util.ArrayList;
import java.util.List;
import org.jbox2d.common.Vec2;

/**
 *
 * @author jly09
 */
public abstract class Platform extends StaticBody {

    Vec2 shapeVec;
    Shape shape;
    List<BodyImage> imageList = new ArrayList<>();
    float shapeWidth;
    float shapeHeight;
    int platformCode;

    /**
     *
     * @param world
     */
    public Platform(World world) {
        super(world);
        //blanktile 0
        imageList.add(new BodyImage("./data/tiles/forest/blanktile.png", 3.2f));

        //forest 1~27
        imageList.add(new BodyImage("./data/tiles/forest/floorblock1.png", 3.2f));
        imageList.add(new BodyImage("./data/tiles/forest/floorblock2.png", 1.7f));
        imageList.add(new BodyImage("./data/tiles/forest/leftbridge.png", 1.3f));
        imageList.add(new BodyImage("./data/tiles/forest/leftdoorframe.png", 3.2f));
        imageList.add(new BodyImage("./data/tiles/forest/leftfloorgrassblock.png", 3.2f));
        imageList.add(new BodyImage("./data/tiles/forest/leftgrassblock1.png", 16.15f));
        imageList.add(new BodyImage("./data/tiles/forest/leftgrassblock2.png", 17.8f));
        imageList.add(new BodyImage("./data/tiles/forest/leftmiscblock1.png", 2.35f));
        imageList.add(new BodyImage("./data/tiles/forest/leftmiscblock2.png", 3.2f));
        imageList.add(new BodyImage("./data/tiles/forest/leftmiscblock3.png", 1.6f));
        imageList.add(new BodyImage("./data/tiles/forest/leftmiscblock4.png", 1.6f));
        imageList.add(new BodyImage("./data/tiles/forest/leftroofgrassblock.png", 3.2f));
        imageList.add(new BodyImage("./data/tiles/forest/leftrooftile1.png", 1.6f));
        imageList.add(new BodyImage("./data/tiles/forest/rightbridge.png", 1.3f));
        imageList.add(new BodyImage("./data/tiles/forest/rightdoorframe.png", 3.2f));
        imageList.add(new BodyImage("./data/tiles/forest/rightfloorgrassblock.png", 3.2f));
        imageList.add(new BodyImage("./data/tiles/forest/rightgrassblock1.png", 16.15f));
        imageList.add(new BodyImage("./data/tiles/forest/rightgrassblock2.png", 17.8f));
        imageList.add(new BodyImage("./data/tiles/forest/rightmiscblock1.png", 2.35f));
        imageList.add(new BodyImage("./data/tiles/forest/rightmiscblock2.png", 3.2f));
        imageList.add(new BodyImage("./data/tiles/forest/rightmiscblock3.png", 1.6f));
        imageList.add(new BodyImage("./data/tiles/forest/rightmiscblock4.png", 1.6f));
        imageList.add(new BodyImage("./data/tiles/forest/rightroofgrassblock.png", 3.2f));
        imageList.add(new BodyImage("./data/tiles/forest/rightrooftile1.png", 1.6f));
        imageList.add(new BodyImage("./data/tiles/forest/rooftile1.png", 1.6f));
        imageList.add(new BodyImage("./data/tiles/forest/rooftile2.png", 1.6f));
        imageList.add(new BodyImage("./data/tiles/forest/floormiscblock.png", 2.35f));

        //ice 28~32
        imageList.add(new BodyImage("./data/tiles/ice/leftwall1.png", 42f));
        imageList.add(new BodyImage("./data/tiles/ice/rightwall1.png", 42f));
        imageList.add(new BodyImage("./data/tiles/ice/icefloor1.png", 3.2f));
        imageList.add(new BodyImage("./data/tiles/ice/roof1.png", 3.2f));
        imageList.add(new BodyImage("./data/tiles/ice/centerplatform1.png", 3.2f));

        //sky 33~35
        imageList.add(new BodyImage("./data/tiles/sky/wall1.png", 42f));
        imageList.add(new BodyImage("./data/tiles/sky/platform1.png", 1.6f));
        imageList.add(new BodyImage("./data/tiles/sky/platform2.png", 1.6f));
    }

    /**
     *
     * @param platformCode
     */
    public void setPlatformCode(int platformCode) {
        this.platformCode = platformCode;
    }

    /**
     *
     * @return
     */
    public int getPlatformCode() {
        return platformCode;
    }

    /**
     *
     * @return
     */
    public Shape getShape() {
        return shape;
    }

    /**
     *
     * @return
     */
    public float getShapeWidth() {
        return shapeWidth;
    }

    /**
     *
     * @return
     */
    public float getShapeHeight() {
        return shapeHeight;
    }

}
