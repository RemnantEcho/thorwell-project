package entities;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Fixture;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.Walker;
import city.cs.engine.World;
import handlers.AnimationDelay;
import static handlers.B2DVariables.GS;
import handlers.DashCooldown;
import handlers.DashHandler;
import handlers.PlayerGroundSensor;
import handlers.PlayerWallSensor;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import main.GameWorld;
import org.jbox2d.common.Vec2;

/**
 *
 * @author jly09
 */
public class Player2 extends Player implements StepListener {

    GameWorld world;
    Arms arms;
    State state;

    /*private static final Shape shape = new PolygonShape(
            -1.67f/PPM,-3.98f/PPM, 1.58f/PPM,-3.98f/PPM, 2.27f/PPM,-2.64f/PPM, 2.39f/PPM,1.75f/PPM, 0.84f/PPM,3.91f/PPM, 0.24f/PPM,3.92f/PPM, -2.4f/PPM,1.63f/PPM);*/
    private static final Shape shape = new PolygonShape(
            0.35f, 1.55f, 0.95f, 0.7f, 0.89f, -1.05f);

    /*private static final Shape shape = new PolygonShape(
            0.42f,1.89f, 1.16f,0.86f, 1.1f,-1.28f, 0.73f,-1.92f, -0.58f,-1.92f, -1.17f,0.84f, 0.13f,1.88f);*/
    private static final BodyImage image = new BodyImage("./data/charsprite/idleImageGreen.png", 3.2f);
    private static final BodyImage image2 = new BodyImage("./data/charsprite/idleImageRed.png", 3.2f);

    /*private Sensor walkFixture;
    private Sensor idleFixture;
    private Sensor dashFixture;
    private Sensor jumpFixture;

    private SolidFixture hat;
    private SolidFixture head;
    private SolidFixture body;
    private SolidFixture legs;*/
    private Fixture fullBody;

    List<BodyImage> idleImages = new ArrayList<>();
    List<BodyImage> walkImages = new ArrayList<>();
    List<BodyImage> jumpingImages = new ArrayList<>();
    List<BodyImage> dashingImages = new ArrayList<>();

    Vec2 absolutePosition;

    int ammo = 3;
    
    float gScale;

    /**
     *
     * @param world
     * @param facingRight
     * @param arms
     */
    public Player2(GameWorld world, boolean facingRight, Arms arms) {
        super(world);
        this.world = world;
        this.facingRight = facingRight;
        this.arms = arms;

        dashTimer = new Timer(200, new DashHandler(world, this));
        dashTimer.setRepeats(false);

        dashCooldown = new Timer(200, new DashCooldown(world, this));
        dashCooldown.setRepeats(false);

        animation = new AnimationDelay();

        idleImages.add(new BodyImage("./data/charsprite/idleImageRed.png", 3.2f));

        for (int i = 1; i <= 6; i++) {
            walkImages.add(new BodyImage("./data/charsprite/walkImageRed" + i + ".png", 3.2f));

        }
        jumpingImages.add(new BodyImage("./data/charsprite/jumpImageRed.png", 3.2f));
        dashingImages.add(new BodyImage("./data/charsprite/dashImageRed.png", 3.2f));

        this.addImage(idleImages.get(0));

        /*hat = new SolidFixture(this, new PolygonShape(0.3f,1.51f, 0.9f,0.63f, -0.89f,0.68f, 0.11f,1.5f));
        head = new SolidFixture(this, new PolygonShape(0.75f,0.6f, 0.45f,-0.01f, -0.49f,-0.01f, -0.61f,0.1f, -0.62f,0.64f));
        body = new SolidFixture(this, new PolygonShape(-0.45f,-0.04f, -0.55f,-0.25f, -0.56f,-0.75f, -0.48f,-0.82f, 0.56f,-0.82f, 0.55f,-0.23f, 0.48f,-0.03f));
        legs = new SolidFixture(this, new PolygonShape(-0.48f,-0.82f, 0.57f,-0.81f, 0.55f,-1.57f, 0.23f,-1.57f, -0.39f,-1.56f, -0.46f,-1.47f));*/
        fullBody = new SolidFixture(this, new PolygonShape(-0.61f, 0.95f, -0.61f, -1.6f, 0.7f, -1.6f, 0.7f, 0.73f, 0.32f, 1.48f, 0.1f, 1.48f), 1f);
        //fullBody = new SolidFixture(this, new BoxShape(1.4f, 1.4f));
        //fullBody = new SolidFixture(this, new PolygonShape(-0.0474f,0.0541f, -0.0477f,-0.0608f, -0.0301f,-0.1139f, 0.0404f,-0.1124f, 0.0498f,-0.0851f, 0.0519f,0.0544f, 0.0283f,0.0999f, -9.0E-4f,0.0993f));

        PlayerGroundSensor ps = new PlayerGroundSensor(world);
        PlayerWallSensor ws = new PlayerWallSensor(world);
        ps.setPlayer2(this);

        height = 3.2f;
        width = 3.2f;

        bottomPoint = -1.6f;
        
        left = new Sensor(this, new BoxShape(0.1f, 0.1f, new Vec2(-0.6f, 0.0f)));
        left.addSensorListener(ws);
        right = new Sensor(this, new BoxShape(0.1f, 0.1f, new Vec2(0.7f, 0.0f)));
        right.addSensorListener(ws);
        feet = new Sensor(this, new BoxShape(0.35f, 0.3f, new Vec2(0.04f, -1.45f)));
        feet.addSensorListener(ps);

        arms.setPosition(this.getPosition());

        if (facingRight) {
            direction = 2;
        } else {
            direction = 6;
        }

        falling = false;
        jumping = false;
        attacking = false;
        dashing = false;
        walking = false;
        idle = true;
        onGround = true;
        onWall = false;
        wallRight = false;
        leaveWall = false;
        wallJumped = false;
        jumpCount = 1;
        jumpCountLimit = 1;

        isDead = false;
        allowMomentum = false;
        wallClimbing = false;
        passthrough = false;

        dashOnCooldown = false;

        shielded = false;
        hasRunningBoots = false;
        hasFlyingBoots = false;

        arrowList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arrowList.add(new DefaultArrow(world));
        }

        world.addStepListener(this);
        world.addStepListener(animation);

        //arms.setPosition(this.getPosition());
        gScale = this.getGravityScale() * GS;
        this.setGravityScale(gScale);
        this.setAlwaysOutline(false);
        this.setBullet(true);
        drawArms();
        
        this.setName("player2");
    }

    /**
     *
     * @param b
     */
    public void setAttacking(boolean b) {
        this.attacking = b;
    }

    /**
     *
     * @param b
     */
    public void setDashing(boolean b) {
        this.dashing = b;
        //state = state.dashing;
    }

    /*public void setWalking(boolean isWalking, boolean facingRight) {
        this.walking = isWalking;
        this.facingRight = facingRight;
        
    }*/
    /**
     *
     * @param isWalking
     * @param facingRight
     */
    public void setWalking(boolean isWalking, boolean facingRight) {
        if (!dashing) {
            state = state.walking;
            this.facingRight = facingRight;
        }

    }

    /*public void setJumping(Boolean b) {
        this.jumping = b;
    }*/
    /**
     *
     */
    public void setJumping() {
        state = state.jumping;
    }

    /**
     *
     * @param b
     */
    public void setIdle(Boolean b) {
        this.idle = b;
    }

    /**
     *
     * @param onGround
     */
    @Override
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
        if (onGround) {
            falling = false;
            jumping = false;
        }
    }

    /*void createFixture() {
        destroyFixtures(this);

        if (facingRight) {
            if (dashing) {
                dashFixture = new Sensor(this, shape);
            } else if (jumping) {
                jumpFixture = new Sensor(this, shape);
            } else if (!dashing && walking || !jumping && walking) {

            }
        } else {
            if (dashing) {
                dashFixture = new Sensor(this, shape);
            } else if (jumping) {
                jumpFixture = new Sensor(this, shape);
            } else if (!dashing && walking || !jumping && walking) {

            }
        }

    }*/
    void draw() {
        this.removeAllImages();

        if (facingRight) {
            this.addImage(animation.getImage());
            flipHorizontal(this);

        } else {
            this.addImage(animation.getImage());
        }

        drawArms();
    }

    /**
     *
     * @param se
     */
        @Override
    public void preStep(StepEvent se) {
        if (this.getLinearVelocity().y < 0 && !onGround) {
            onGround = false;
            falling = true;
            jumping = false;

            if (dashing == true) {
                state = state.dashing;
            }
            else {
                state = state.falling;
            }
        }
        else if (this.getLinearVelocity().y > 0 && !onGround) {
            state = state.jumping;
        }
        
        if (hasFlyingBoots) {
            jumpCountLimit = 2;
        }

        if (onGround) {
            jumpCount = jumpCountLimit;
            falling = false;
            jumping = false;
            if (dashing) {
                state = state.dashing;
            }
            else if (walking) {
                state = state.walking;
            }
            else if (idle) {
                state = state.idle;
            }
        }

        if (!dashing) {
            if (attacking) {

                if (ammo != 0) {
                    if (arrowList.get(0) instanceof DefaultArrow) {
                        Arrow tempArrow = new DefaultArrow(world, 1, false, direction);
                        tempArrow.setPosition(new Vec2(this.getPosition().x, this.getPosition().y));
                        world.getArrowList().add(tempArrow);
                    }

                    if (arrowList.get(0) instanceof LaserArrow) {
                        Arrow tempArrow = new LaserArrow(world, 1, false, direction);
                        tempArrow.setPosition(new Vec2(this.getPosition().x, this.getPosition().y));
                        world.getArrowList().add(tempArrow);
                    }

                    world.getSounds().playBowFiring().play();
                    ammo--;
                    this.arrowList.remove(0);
                }

                attacking = false;
            }
        }

        if (dashOnCooldown) {
            dashing = false;
            if (!dashCooldown.isRunning()) {
                dashCooldown.start();
            }
        }

        if (dashing) {
            falling = false;
            jumping = false;
            idle = false;
            wallClimbing = false;
            this.setGravityScale(gScale);
            state = state.dashing;
            if (!dashOnCooldown) {
                //dashing = true;
                if (!dashTimer.isRunning()) {
                    absolutePosition = this.getPosition();
                    dashTimer.start();
                }
                
                if (facingRight) {
                    this.setPosition(new Vec2(this.getPosition().x, absolutePosition.y));
                    this.applyImpulse(new Vec2(40f, 0));

                } else {
                    this.setPosition(new Vec2(this.getPosition().x, absolutePosition.y));
                    this.applyImpulse(new Vec2(-40f, 0));

                }

            }

        } else {
            dashing = false;
            if (!onGround) {
                state = state.falling;
                if (walking) {
                    
                    if (facingRight) {
                        startWalking(12f);
                    } else {
                        startWalking(-12f);
                    }

                }
                
                if (onWall) {
                    falling = false;
                    wallClimbing = true;
                    absolutePosition = this.getPosition();
                    
                }
                else {
                    wallClimbing = false;
                }
                
            } else if (onGround) {
                if (walking) {
                    state = state.walking;
                    if (facingRight) {
                        startWalking(12f);
                    } else {
                        startWalking(-12f);
                    }

                } else {
                    state = state.idle;
                }
            }
        }

        if (wallClimbing && !dashing && !jumping & !leaveWall) {
            falling = false;
            jumping = false;
            walking = false;
            this.setPosition(new Vec2(absolutePosition.x, absolutePosition.y));
            this.setLinearVelocity(new Vec2(0f, 0.0f));
            this.setGravityScale(0);
        }
        else {
            leaveWall = false;
            this.setGravityScale(gScale);
        }

        /*System.out.println("Jumping: " + jumping);
        System.out.println("Falling: " + falling);
        System.out.println("Dashing: " + dashing);
        System.out.println("Walking: " + walking);
        System.out.println("WallClimbing" + wallClimbing);*/
        if (!walking && !jumping && !dashing && !falling && !wallClimbing) {
            walking = false;
            jumping = false;
            dashing = false;
            idle = true;

            state = state.idle;
        } else {
            idle = false;
        }

        if (idle) {
            //this.setGravityScale(this.getGravityScale() * GS);
            //this.setLinearVelocity(new Vec2(0, 0));
        }
        
        if (onRide && !jumping) {
            if (walking) {
                if (rideDirection == "horizontal") {
                    if (rideSpeed > 0) {
                        if (facingRight) {
                              this.setLinearVelocity(new Vec2(this.getLinearVelocity().x + rideSpeed, 0));
                        }
                        else {
                              
                        }
                    }
                    else {
                        if (facingRight) {

                        }
                        else {
                            this.setLinearVelocity(new Vec2(this.getLinearVelocity().x + rideSpeed, 0));
                        }
                    }
                    this.setLinearVelocity(new Vec2(this.getLinearVelocity().x, 0));
                }
                else if (rideDirection == "vertical") {
    //                this.setLinearVelocity(new Vec2(this.getLinearVelocity().x + rideSpeed, 0));
                    
                }
                
            }
            else if (idle) {
                if (rideDirection == "horizontal") {
                    this.setLinearVelocity(new Vec2(rideSpeed, 0));
                }
                else if (rideDirection == "vertical") {

                }
            }
        }

        /*if (animation.getPlayedOnce()) {
            //createFixture();
            if (state == state.jumping) {
                animation.setDelay(100);
                animation.setFrames(jumpingImages);
            } else if (state == state.falling) {
                animation.setDelay(100);
                animation.setFrames(jumpingImages);
            } else if (state == state.dashing) {
                animation.setDelay(100);
                animation.setFrames(dashingImages);
            } else if (state == state.walking) {
                animation.setDelay(200);
                animation.setFrames(walkImages);
            } else if (state == state.idle) {
                animation.setDelay(200);
                animation.setFrames(idleImages);
            }

            if (jumping) {
                animation.setDelay(100);
                animation.setFrames(jumpingImages);
            }
            else if (falling) {
                animation.setDelay(100);
                animation.setFrames(jumpingImages);
            }
            else if (dashing) {
                animation.setDelay(100);
                animation.setFrames(dashingImages);
            }
            else if (walking) {
                if (!jumping) {
                    animation.setDelay(200);
                    animation.setFrames(walkImages);
                }
            }
            else if (idle) {
                animation.setDelay(200);
                animation.setFrames(idleImages);
            }
        }*/
        checkStateChange();
        if (this.getPosition().y < -60f) {
            this.world.getScores().decrementP2Score();
            isDead = true;
        }
    }

    /**
     *
     * @param se
     */
    @Override
    public void postStep(StepEvent se) {
        draw();

        arms.setPosition(this.getPosition());
        //arms.addImage(new BodyImage("data/charsprite/bowLeftGreen.png", 3.2f));

        if (isDead) {
            arms.destroy();
            this.destroyFixtures(this);
            this.destroy();

            world.respawnPlayer(this);
            //world.getWindow().getView().repaint();
        }
    }

    /**
     *
     * @param walkSpeed
     */
    @Override
    public void startWalking(float walkSpeed) {
        if (!dashing) {
            if (wallClimbing) {
                if (facingRight) {
                    if (!wallRight) {
                        leaveWall = true;
                    }
                }
                else {
                    if (wallRight) {
                       leaveWall = true;
                    }
                }
            }
            
            if (hasRunningBoots) {
                if (facingRight) {
                    //super.startWalking(walkSpeed + 6f);
//                    this.applyImpulse(new Vec2(10f, 0));
                    this.setLinearVelocity(new Vec2(walkSpeed + 6f, this.getLinearVelocity().y));
                } else {
//                    this.applyImpulse(new Vec2(-10f, 0));
                    //super.startWalking(walkSpeed - 6f);
                    this.setLinearVelocity(new Vec2(walkSpeed - 6f, this.getLinearVelocity().y));
                }
            } else {
                
                     
                this.setLinearVelocity(new Vec2(walkSpeed, this.getLinearVelocity().y));
                
                //super.startWalking(walkSpeed);
            }
            if (!falling && !jumping) {
                
                state = state.walking;
            }
            walking = true;
        }
    }

    /**
     *
     */
    @Override
    public void stopWalking() {
        walking = false;
        if (!dashing) {
            if (allowMomentum) {
                super.stopWalking();
            } else {
                //super.startWalking(0);
                this.setLinearVelocity(new Vec2(0, this.getLinearVelocity().y));
                super.stopWalking();
            }
        }
    }

    /**
     *
     * @param jumpingSpeed
     */
    @Override
    public void jump(float jumpingSpeed) {
        if (!dashing) {
            //super.jump(jumpingSpeed);
            
            if (wallClimbing && !wallJumped) {
                wallClimbing = false;
                wallJumped = true;
                this.setGravityScale(gScale);
                //wall right
                if (wallRight) {
                    this.applyImpulse(new Vec2(-60f, jumpingSpeed * 5));
                    facingRight = false;
                    setDirection(6);
                    jumping = true;
                    state = state.jumping;
                }
                //wall left
                else {
                    this.applyImpulse(new Vec2(60f, jumpingSpeed * 5));
                    facingRight = true;
                    setDirection(2);
                    jumping = true;
                    state = state.jumping;
                }
            }
            else {
                if (onGround && jumpCount > 0) {
                    //super.jump(jumpingSpeed);
                    this.applyImpulse(new Vec2(0f, jumpingSpeed * 5));
                    jumpCount--;
                    onGround = false;
                    jumping = true;
                    state = state.jumping;
                } else if (!onGround && jumpCount > 0) {
                    this.applyImpulse(new Vec2(0f, jumpingSpeed * 5));
                    jumpCount--;
                    onGround = false;
                    jumping = true;
                    state = state.jumping;
                }
            }
        }
        
    }
    
    @Override
    public void setOnRide(boolean onRide, float rideSpeed, String rideDirection) {
        this.onRide = onRide;
        this.rideSpeed = rideSpeed;
        this.rideDirection = rideDirection;
        
        absolutePosition = this.getPosition();
    }

    /**
     *
     * @param direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     *
     * @param facingRight
     */
    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    /**
     *
     * @return
     */
    public Arms getArms() {
        return arms;
    }

    /**
     *
     * @param arrow
     */
    public void incrementAmmo(Arrow arrow) {
        ammo++;
        arrowList.add(arrow);
    }

    /**
     *
     * @param arrow
     */
    public void decrementAmmo(Arrow arrow) {
        ammo--;
        arrowList.remove(arrow);
    }

    /**
     *
     * @param ammo
     */
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    /**
     *
     * @return
     */
    public int getAmmo() {
        return ammo;
    }

    private void drawArms() {
        switch (direction) {
            //up
            case 0:
                if (facingRight) {
                    arms.setArms(0);
                } else {
                    arms.setArms(5);
                }
                break;
            //up right
            case 1:
                arms.setArms(2);
                break;
            //right
            case 2:
                arms.setArms(3);
                break;
            //down right
            case 3:
                arms.setArms(4);
                break;
            //down
            case 4:
                if (facingRight) {
                    arms.setArms(1);
                } else {
                    arms.setArms(6);
                }
                break;
            //up left
            case 5:
                arms.setArms(7);
                break;
            //left
            case 6:
                arms.setArms(8);
                break;
            //down left
            case 7:
                arms.setArms(9);
                break;
        }
    }

    /**
     *
     * @return
     */
    public List<Arrow> getArrowList() {
        return arrowList;
    }

    private void checkStateChange() {
        if (animation.getFinished()) {

        }
        switch (state) {
            case idle:
                animation.setDelay(200);
                animation.setFrames(idleImages);
                break;
            case walking:
                if (animation.getPlayedOnce()) {
                    animation.setDelay(150);
                    animation.setFrames(walkImages);
                }
                break;
            case jumping:
                animation.setDelay(100);
                animation.setFrames(jumpingImages);
                break;
            case falling:
                animation.setDelay(100);
                animation.setFrames(jumpingImages);
                break;
            case dashing:
                animation.setDelay(100);
                animation.setFrames(dashingImages);
                break;
        }
    }

    private enum State {
        idle,
        walking,
        jumping,
        falling,
        dashing;
    }
}
