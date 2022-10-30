package levels;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.World;
import city.cs.engine.WorldView;
import entities.DefaultArrow;
import entities.LaserArrow;
import entities.RunningBoots;
import entities.Shield;
import entities.WingedBoots;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GameWorld;
import org.jbox2d.common.Vec2;
import platforms.AutoMovingPlatform;
import platforms.Ground;

/**
 *
 * @author jly09
 */
public class SkyLevel extends Levels {

    BufferedImage bimage = null;
    BufferedImage mimage = null;

    /**
     *
     * @param world
     */
    public SkyLevel(GameWorld world) {
        spawnPositions = new ArrayList<>();
        spawnPositions.add(new Vec2(15f, -2.6f));
        spawnPositions.add(new Vec2(-15f, -2.6f));

        itemSpawnLocations = new Vec2(0f, 6.4f);

        itemList = new ArrayList<>();

        itemList.add(new Shield(world));
        itemList.add(new RunningBoots(world));
        itemList.add(new WingedBoots(world));
        itemList.add(new DefaultArrow(world));
        itemList.add(new LaserArrow(world));

        try {
            bimage = ImageIO.read(new File("./data/backgrounds/skybackground.png"));
            mimage = ImageIO.read(new File("./data/backgrounds/skymiddleground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        locked = false;

        this.background = bimage;
        this.middleground = mimage;
    }

    /**
     *
     * @param world
     */
    public void createWorld(GameWorld world) {
        //Boundary Platforms
        Shape wallShape = new BoxShape(0.8f, 21f);
        Ground upWall1 = new Ground(world, wallShape, 33);
        upWall1.setPosition(new Vec2(26.4f, 0f));

        Ground upWall2 = new Ground(world, wallShape, 33);
        upWall2.setPosition(new Vec2(-26.4f, 0f));

        Shape floorShape = new BoxShape(3.2f, 0.8f);
        Ground floor1 = new Ground(world, floorShape, 35);
        floor1.setPosition(new Vec2(0f, 5f));

        Ground floor2 = new Ground(world, floorShape, 35);
        floor2.setPosition(new Vec2(-15f, -5f));

        Ground floor3 = new Ground(world, floorShape, 35);
        floor3.setPosition(new Vec2(15f, -5f));

        //BoxShape platform = new BoxShape(5.3f, 0.8f);
        AutoMovingPlatform apm1 = new AutoMovingPlatform(world, 5.3f, 0.8f, 34, new Vec2(-15f, 10f));

        AutoMovingPlatform apm2 = new AutoMovingPlatform(world, 5.3f, 0.8f, 34, new Vec2(0f, 0f));

        AutoMovingPlatform apm3 = new AutoMovingPlatform(world, 5.3f, 0.8f, 34, new Vec2(15f, -10f));

    }

    /**
     *
     * @param view
     */
    public void createView(WorldView view) {

    }

}
