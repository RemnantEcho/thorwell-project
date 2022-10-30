/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import city.cs.engine.AttachedImage;
import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import city.cs.engine.Fixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.Walker;
import city.cs.engine.World;
import handlers.AnimationDelay;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;
import platforms.AutoMovingPlatform;

/**
 * Abstract class for players subclasses to be inherited from; holds important
 * variables and methods to be mutated from.
 *
 * @author jly09
 */
public abstract class Player extends Walker {

    World world;
    Shape shape;
    //DashTimer dashTimer;
    AttachedImage attachedImage;
    AnimationDelay animation;
    Arms arms;
    
    Sensor left;
    Sensor right;
    Sensor feet;
    SolidFixture head;
    AutoMovingPlatform ap;

    Timer dashTimer;
    Timer dashCooldown;

    boolean attacking;
    boolean dashing;
    boolean walking;
    boolean fullStop;
    boolean jumping;
    boolean idle;

    boolean isDead;

    boolean onGround;
    boolean onWall;
    boolean wallRight;
    boolean leaveWall;
    boolean allowMomentum;
    boolean falling;
    boolean wallClimbing;
    boolean wallJumped;
    boolean onRide;

    int jumpCount;
    int jumpCountLimit;

    boolean hasFlyingBoots;
    boolean hasRunningBoots;

    boolean facingRight;

    boolean dashOnCooldown;

    boolean passthrough;

    int playerCount = 0;
    boolean shielded;
    boolean invincible;

    int direction;

    float height;
    float width;
    float bottomPoint;
    float rideSpeed;
    
    String rideDirection;
    
    List<Arrow> arrowList;

    Player(World world, Shape shape, float[] hitboxPoints) {
        super(world, shape);
        this.world = world;
        this.shape = shape;
    }

    Player(World world) {
        super(world);
        this.world = world;

    }

    /**
     *
     * @return
     */
    public int calculatePlayerCount() {
        int c = 0;
        List<DynamicBody> tempBodyList = new ArrayList(world.getDynamicBodies());
        for (DynamicBody body : tempBodyList) {
            if (body instanceof Player1 || body instanceof Player2) {
                c++;
            }
        }

        return c;
    }

    /**
     *
     * @return
     */
    public int getPlayerCount() {
        this.playerCount = calculatePlayerCount();
        return playerCount;
    }

    /**
     *
     * @param shielded
     */
    public void setShielded(boolean shielded) {
        this.shielded = shielded;
    }

    /**
     *
     * @return
     */
    public boolean getShielded() {
        return shielded;
    }

    /**
     *
     * @param body
     * @param image
     * @param scale
     * @param rotation
     * @param offset
     * @return
     */
    public AttachedImage createAttachedImage(Body body, BodyImage image, float scale, float rotation, Vec2 offset) {
        return attachedImage = new AttachedImage(body, image, scale, rotation, offset);
    }

    /**
     *
     * @param player
     */
    public void destroyFixtures(Player player) {
        List<Fixture> fixtureList = new ArrayList<>(player.getFixtureList());
        for (Fixture fix : fixtureList) {
            fix.destroy();
        }
    }

    /**
     *
     * @param player
     */
    public void flipHorizontal(Player player) {
        List<AttachedImage> aimagelist = new ArrayList<>(player.getImages());
        AttachedImage aimage;
        aimage = aimagelist.get(0);
        aimage.flipHorizontal();
    }

    /**
     *
     * @param width
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     *
     * @return
     */
    public float getWidth() {
        return width;
    }

    /**
     *
     * @param height
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     *
     * @return
     */
    public float getHeight() {
        return height;
    }

    /**
     *
     * @return
     */
    public Sensor getFeet() {
        return feet;
    }
    
    /**
     *
     * @return
     */
    public Sensor getLeft() {
        return left;
    }
    
    /**
     *
     * @return
     */
    public Sensor getRight() {
        return right;
    }
    
    /**
     *
     * @param dashOnCooldown
     */
    public void setWallJumped(boolean wallJumped) {
        this.wallJumped = wallJumped;
    }
    
    /**
     *
     * @param dashOnCooldown
     */
    public boolean getWallJumped() {
        return wallJumped;
    }

    /**
     *
     * @param dashOnCooldown
     */
    public void setDashOnCooldown(boolean dashOnCooldown) {
        this.dashOnCooldown = dashOnCooldown;
    }

    /**
     *
     * @return
     */
    public boolean getDashOnCooldown() {
        return dashOnCooldown;
    }

    /**
     *
     * @param isDead
     */
    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    /**
     *
     * @return
     */
    public boolean getIsDead() {
        return isDead;
    }

    /**
     *
     * @return
     */
    public boolean getDashing() {
        return dashing;
    }

    /**
     *
     * @param hasRunningBoots
     */
    public void setHasRunningBoots(boolean hasRunningBoots) {
        this.hasRunningBoots = hasRunningBoots;
    }

    /**
     *
     * @param hasFlyingBoots
     */
    public void setHasFlyingBoots(boolean hasFlyingBoots) {
        this.hasFlyingBoots = hasFlyingBoots;
    }

    /**
     *
     * @return
     */
    public boolean getHasRunningBoots() {
        return hasRunningBoots;
    }

    /**
     *
     * @return
     */
    public boolean getHasFlyingBoots() {
        return hasFlyingBoots;
    }

    /**
     *
     * @return
     */
    public boolean getFacingRight() {
        return facingRight;
    }

    /**
     *
     * @return
     */
    public SolidFixture getHeadFixture() {
        return head;
    }

    /**
     *
     * @param allowMomentum
     */
    public void setAllowMomentum(boolean allowMomentum) {
        this.allowMomentum = allowMomentum;
    }

    /**
     *
     * @param onGround
     */
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
        jumping = false;
        falling = false;
    }
    
    /**
     *
     * @param onWall
     */
    public void setOnWall(boolean onWall, boolean wallRight) {
        this.onWall = onWall;
        this.wallRight = wallRight;
    }
    
    /**
     *
     * @param onWall
     */
    public void setLeaveWall(boolean leaveWall) {
        this.leaveWall = leaveWall;
    }
    
    /**
     *
     * @return
     */
    public boolean getLeaveWall() {
        return leaveWall;
    }
    
    /**
     *
     * @param onRide
     */
    public void setOnRide(boolean onRide) {
        this.onRide = onRide;
    }
    
    /**
     *
     * @param onRide
     */
    public void setOnRide(boolean onRide, float rideSpeed, String rideDirection) {
        this.onRide = onRide;
        this.rideSpeed = rideSpeed;
        this.rideDirection = rideDirection;
    }
    
    /**
     *
     * @return
     */
    public boolean getOnRide() {
        return onRide;
    }
    
    /**
     *
     * @param ap
     */
    public void setAutoPlatform(AutoMovingPlatform ap) {
        this.ap = ap;
    }
    
    /**
     *
     * @return
     */
    public AutoMovingPlatform getAutoPlatform() {
        return ap;
    }
    
    /**
     *
     * @param rideSpeed
     */
    public void setRideSpeed(float rideSpeed) {
        this.rideSpeed = rideSpeed;
    }
    
    /**
     *
     * @return
     */
    public float getRideSpeed() {
        return rideSpeed;
    }

    /**
     *
     * @return
     */
    public float getBottomPoint() {
        return bottomPoint;
    }

    /**
     *
     * @return
     */
    public boolean getAllowMomentum() {
        return allowMomentum;
    }

    /**
     *
     * @param passthrough
     */
    public void setPassthrough(boolean passthrough) {
        this.passthrough = passthrough;
    }

    /**
     *
     * @return
     */
    public boolean getPassthrough() {
        return passthrough;
    }
    
    /**
     *
     * @param rideDirection
     */
    public void setRideDirection(String rideDirection) {
        this.rideDirection = rideDirection;
    }

    /**
     *
     * @return
     */
    public String getRideDirection() {
        return rideDirection;
    }

    /**
     *
     * @return
     */
    public Timer getDashTimer() {
        return dashTimer;
    }

    /**
     *
     * @return
     */
    public Timer getDashCooldown() {
        return dashCooldown;
    }

}
