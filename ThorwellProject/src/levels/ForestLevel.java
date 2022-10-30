package levels;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
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
import platforms.Ground;
import platforms.PassableGround;

/**
 *
 * @author jly09
 */
public class ForestLevel extends Levels {

    BufferedImage bimage = null;
    BufferedImage mimage = null;

    /**
     *
     * @param world
     */
    public ForestLevel(GameWorld world) {
        spawnPositions = new ArrayList<>();
        spawnPositions.add(new Vec2(23f, -3.2f));
        spawnPositions.add(new Vec2(-23f, -3.2f));

        itemSpawnLocations = new Vec2(0f, 0f);

        itemList = new ArrayList<>();

        itemList.add(new Shield(world));
        itemList.add(new RunningBoots(world));
        itemList.add(new WingedBoots(world));
        itemList.add(new DefaultArrow(world));
        itemList.add(new LaserArrow(world));

        try {
            bimage = ImageIO.read(new File("./data/backgrounds/forestbackground.png"));
            mimage = ImageIO.read(new File("./data/backgrounds/forestmiddleground.png"));
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
        /*Shape groundShape = new BoxShape(40f, 5);
                Ground ground = new Ground(world, groundShape);
                ground.setPosition(new Vec2(0, -40));*/

        //Bridge Platforms
        Shape bridgeEndShape = new BoxShape(2.4f, 0.8f);
        /*Ground bridgeEnd1 = new Ground(world, bridgeEndShape);
                bridgeEnd1.setPosition(new Vec2(22.4f, -5.6f));
                
                Ground bridgeEnd2 = new Ground(world, bridgeEndShape);
                bridgeEnd2.setPosition(new Vec2(-22.4f, -5.6f));*/

        Shape bridgeShape = new BoxShape(8.4f, 0.65f);
        PassableGround rightBridge = new PassableGround(world, new Vec2(8.4f, 0.65f), 14);
        //StaticBody rightBridge = new StaticBody(world, bridgeShape);
        rightBridge.setPosition(new Vec2(16.4f, -5.5f));

        Ground leftBridge = new Ground(world, bridgeShape, 3);
        leftBridge.setPosition(new Vec2(-16.4f, -5.5f));

        //Cabin Platforms
        Shape cabinFloorShape = new BoxShape(4.8f, 0.85f);
        Ground cabinFloor = new Ground(world, cabinFloorShape, 2);
        cabinFloor.setPosition(new Vec2(0f, -5.7f));

        Shape cabinWallShape = new BoxShape(0.8f, 1.6f);
        Ground cabinRightWall = new Ground(world, cabinWallShape, 15);
        cabinRightWall.setPosition(new Vec2(7.2f, 1.35f));

        Ground cabinLeftWall = new Ground(world, cabinWallShape, 4);
        cabinLeftWall.setPosition(new Vec2(-7.2f, 1.35f));

        Shape cabinRoofShape1 = new BoxShape(1.6f, 0.8f);
        Ground cabinRoofR1 = new Ground(world, cabinRoofShape1, 24);
        cabinRoofR1.setPosition(new Vec2(8f, 3.75f));

        Ground cabinRoofR2 = new Ground(world, cabinRoofShape1, 24);
        cabinRoofR2.setPosition(new Vec2(6.4f, 5.35f));

        Ground cabinRoofL1 = new Ground(world, cabinRoofShape1, 13);
        cabinRoofL1.setPosition(new Vec2(-8f, 3.75f));

        Ground cabinRoofL2 = new Ground(world, cabinRoofShape1, 13);
        cabinRoofL2.setPosition(new Vec2(-6.4f, 5.35f));

        Shape cabinRoofShape2 = new BoxShape(6.4f, 0.8f);
        Ground cabinRoofT1 = new Ground(world, cabinRoofShape2, 25);
        cabinRoofT1.setPosition(new Vec2(0f, 6.95f));

        Shape cabinRoofShape3 = new BoxShape(4.8f, 0.8f);
        Ground cabinRoofT2 = new Ground(world, cabinRoofShape3, 26);
        cabinRoofT2.setPosition(new Vec2(0f, 8.45f));

        //Boundary Platforms
        Shape upperWallShape = new BoxShape(1.6f, 8.9f);
        Ground upWall1 = new Ground(world, upperWallShape, 18);
        upWall1.setPosition(new Vec2(26.4f, 12.1f));

        Ground upWall2 = new Ground(world, upperWallShape, 7);
        upWall2.setPosition(new Vec2(-26.4f, 12.1f));

        Shape lowerWallShape = new BoxShape(1.6f, 8.075f);
        Ground lowWall1 = new Ground(world, lowerWallShape, 17);
        lowWall1.setPosition(new Vec2(26.4f, -12.925f));

        Ground lowWall2 = new Ground(world, lowerWallShape, 6);
        lowWall2.setPosition(new Vec2(-26.4f, -12.925f));

        Shape floorShape = new BoxShape(9.75f, 1.6f);
        Ground floor1 = new Ground(world, floorShape, 5);
        floor1.setPosition(new Vec2(-15.06f, -19.4f));

        Ground floor2 = new Ground(world, floorShape, 16);
        floor2.setPosition(new Vec2(15.06f, -19.4f));

        Shape roofShape = new BoxShape(9.725f, 1.6f);
        Ground roof1 = new Ground(world, roofShape, 23);
        roof1.setPosition(new Vec2(15.075f, 19.4f));

        Ground roof2 = new Ground(world, roofShape, 12);
        roof2.setPosition(new Vec2(-15.075f, 19.4f));

        Shape miscShape1 = new BoxShape(1.6f, 0.8f);
        Ground topHorizontalBlock1 = new Ground(world, miscShape1, 10);
        topHorizontalBlock1.setPosition(new Vec2(-21.6f, 17f));

        Ground topHorizontalBlock2 = new Ground(world, miscShape1, 11);
        topHorizontalBlock2.setPosition(new Vec2(-6.95f, 17f));

        Ground topHorizontalBlock3 = new Ground(world, miscShape1, 22);
        topHorizontalBlock3.setPosition(new Vec2(6.95f, 17f));

        Ground topHorizontalBlock4 = new Ground(world, miscShape1, 21);
        topHorizontalBlock4.setPosition(new Vec2(21.6f, 17f));

        Shape miscShape2 = new BoxShape(0.8f, 1.6f);
        Ground topVerticalBlock1 = new Ground(world, miscShape2, 9);
        topVerticalBlock1.setPosition(new Vec2(-24f, 16.2f));

        Ground topVerticalBlock2 = new Ground(world, miscShape2, 20);
        topVerticalBlock2.setPosition(new Vec2(24f, 16.2f));

        Shape miscShape3 = new BoxShape(1.6f, 1.2f);
        Ground botHorizontalBlock1 = new Ground(world, miscShape3, 27);
        botHorizontalBlock1.setPosition(new Vec2(6.95f, -16.6f));

        Ground botHorizontalBlock2 = new Ground(world, miscShape3, 27);
        botHorizontalBlock2.setPosition(new Vec2(-6.95f, -16.6f));

        Shape miscShape4 = new BoxShape(1.6f, 1.6f);

        Ground centerSupportBlock1 = new Ground(world, miscShape4, 1);
        centerSupportBlock1.setPosition(new Vec2(6.4f, -6.45f));

        Ground centerSupportBlock2 = new Ground(world, miscShape4, 1);
        centerSupportBlock2.setPosition(new Vec2(-6.4f, -6.45f));
    }

    /**
     *
     * @param view
     */
    public void createView(WorldView view) {

    }

}
