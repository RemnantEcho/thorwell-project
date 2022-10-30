package levels;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.World;
import city.cs.engine.WorldView;
import entities.DefaultArrow;
import entities.Icicle;
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
import platforms.Ground;
import platforms.Ice;

/**
 *
 * @author jly09
 */
public class IceLevel extends Levels {

    BufferedImage bimage = null;
    BufferedImage mimage = null;

    /**
     *
     * @param world
     */
    public IceLevel(GameWorld world) {
        spawnPositions = new ArrayList<>();
        spawnPositions.add(new Vec2(18f, -17f));
        spawnPositions.add(new Vec2(-18f, -17f));

        itemSpawnLocations = new Vec2(0f, -17f);

        itemList = new ArrayList<>();

        itemList.add(new Shield(world));
        itemList.add(new RunningBoots(world));
        itemList.add(new WingedBoots(world));
        itemList.add(new DefaultArrow(world));
        itemList.add(new LaserArrow(world));

        try {
            bimage = ImageIO.read(new File("./data/backgrounds/icebackground.png"));
            //mimage = ImageIO.read(new File("data/backgrounds/icemiddleground.png"));
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
        Shape wallShape = new BoxShape(1.6f, 21f);
        Ground upWall1 = new Ground(world, wallShape, 29);
        upWall1.setPosition(new Vec2(26.4f, 0f));

        Ground upWall2 = new Ground(world, wallShape, 28);
        upWall2.setPosition(new Vec2(-26.4f, 0f));

        Shape floorShape = new BoxShape(21.6f, 1.6f);
        Ice floor1 = new Ice(world, floorShape, 30);
        floor1.setPosition(new Vec2(0f, -19.4f));

        Shape roofShape = new BoxShape(28f, 1.6f);
        Ground roof1 = new Ground(world, roofShape, 31);
        roof1.setPosition(new Vec2(0f, 19.4f));

        Shape centerPlatformShape = new BoxShape(7.2f, 1.6f);
        Ground centerPlatform1 = new Ground(world, centerPlatformShape, 32);
        centerPlatform1.setPosition(new Vec2(0f, 0f));

        Icicle icicle = new Icicle(world, new Vec2(0f, -1.8f));
        Icicle icicle1 = new Icicle(world, new Vec2(-1.6f, -1.8f));
        Icicle icicle2 = new Icicle(world, new Vec2(-3.2f, -1.8f));
    }

}
