package handlers;

import entities.*;
import city.cs.engine.Walker;
import city.cs.engine.WorldView;
import static handlers.B2DVariables.GS;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import main.GameWorld;
import org.jbox2d.common.Vec2;

/**
 * Handles the Key Event that occurs when a key on a keyboard is pressed;
 * involves player movement and attacking, and menu hotkeys
 *
 * @author jly09
 */
public class KeyController extends KeyAdapter {

    GameWorld world;
    Player1 p1;
    Player2 p2;

    //Jump height value
    private static final float JUMPING_SPEED = 3.2f * GS;
    //Walk speed value
    private static final float WALKING_SPEED = 12f;

    //Lock controls
    private boolean lockControlsP1 = false;
    private boolean lockControlsP2 = false;

    //Set of Pressed Keys
    private Set<Integer> pressedInt;

    //private Set<Integer> p1KeySet = new HashSet<>();
    //private Set<Integer> p2KeySet = new HashSet<>();
    //keys
    private boolean space;
    private boolean w;
    private boolean a;
    private boolean s;
    private boolean d;
    private boolean upArrow;
    private boolean leftArrow;
    private boolean rightArrow;
    private boolean downArrow;
    private boolean num0;

    /**
     *
     * @param world
     */
    public KeyController(GameWorld world) {
        this.world = world;

        pressedInt = new HashSet<>();

        //Player 1 controls
        /*p1KeySet.add(KeyEvent.VK_W);
        p1KeySet.add(KeyEvent.VK_A);
        p1KeySet.add(KeyEvent.VK_S);
        p1KeySet.add(KeyEvent.VK_D);
        p1KeySet.add(KeyEvent.VK_SPACE);
        p1KeySet.add(KeyEvent.VK_G);
        p1KeySet.add(KeyEvent.VK_SHIFT);

        //Player 2 controls
        p2KeySet.add(KeyEvent.VK_UP);
        p2KeySet.add(KeyEvent.VK_LEFT);
        p2KeySet.add(KeyEvent.VK_DOWN);
        p2KeySet.add(KeyEvent.VK_RIGHT);
        p2KeySet.add(KeyEvent.VK_NUMPAD0);
        p2KeySet.add(KeyEvent.VK_NUMPAD4);
        p2KeySet.add(KeyEvent.VK_ENTER);*/
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        //Add pressed keys to key array
        pressedInt.add(e.getKeyCode());

        //Look through key array
        for (Integer keyInt : pressedInt) {
            if (keyInt == KeyEvent.VK_W) {
                w = true;
            }
            if (keyInt == KeyEvent.VK_S) {
                s = true;
            }
            if (keyInt == KeyEvent.VK_A) {
                a = true;
            }
            if (keyInt == KeyEvent.VK_D) {
                d = true;
            }
            if (keyInt == KeyEvent.VK_UP) {
                upArrow = true;
            }
            if (keyInt == KeyEvent.VK_DOWN) {
                downArrow = true;
            }
            if (keyInt == KeyEvent.VK_LEFT) {
                leftArrow = true;
            }
            if (keyInt == KeyEvent.VK_RIGHT) {
                rightArrow = true;
            }
        }

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE) { // Esc Key = quit or open Options Menu
            if (world.getStateManager().getLevelCode() == 1) {
                if (world.getWindow().getView().getHelpPanel().getVisible()) {
                    world.getWindow().getView().getHelpPanel().setVisibility(false);
                }
                else {
                    if (world.getWindow().getView().getOptionsPanel().getVisible()) {
                        world.getWindow().getView().getOptionsPanel().setVisibility(false);
                    } else {
                        world.getWindow().getView().getOptionsPanel().setVisibility(true);
                    }
                }
                
            } else {
                System.exit(0);
            }

        }
        if (!lockControlsP1) {
            if (p1 != null) {
                if (space && s) {
                    
                }
                
                if (w) {
                    
                }
                
                switch (code) {
                    case KeyEvent.VK_SPACE:
                        // Spacebar = jump
                        if (s && p1.getOnRide() && p1.getRideDirection() == "passing") {
                            p1.setPassthrough(true);
                        }
                        else {
                            p1.jump(JUMPING_SPEED);
                        }
                        
                        break;
                    /*
                    case KeyEvent.VK_A:
                        p1.setWalking(true, false);
                        p1.startWalking(-WALKING_SPEED); // A = walk left
                        
                        break;
                    case KeyEvent.VK_D:
                        p1.setWalking(true, true);
                        p1.startWalking(WALKING_SPEED); // D = walk right      
                        
                        break;
                        */
                    case KeyEvent.VK_SHIFT:
                        // Shift = dashing
                        p1.setDashing(true);
                        break;
                    case KeyEvent.VK_G:
                        // G = attacking
                        p1.setAttacking(true);
                        break;
                    default:
                        break;
                }

                //up
                if (w) {
                    p1.setDirection(0);
                }

                //down
                if (s) {
                    p1.setDirection(4);
                }
                
                //left
                if (a) {
                    p1.setWalking(true, false);
                    p1.startWalking(-WALKING_SPEED);
                    p1.setDirection(6);
                }
                
                //right
                if (d) {
                    p1.startWalking(WALKING_SPEED);
                    p1.setWalking(true, true);
                    p1.setDirection(2);
                }

                //up left
                if (a && w) {
                    p1.setDirection(5);
                }

                //up right
                if (d && w) {
                    p1.setDirection(1);
                }

                //down left
                if (a && s) {
                    p1.setWalking(true, false);
                    p1.startWalking(-WALKING_SPEED);
                    p1.setDirection(7);
                }

                //down right
                if (d && s) {
                    p1.setWalking(true, true);
                    p1.startWalking(WALKING_SPEED);
                    p1.setDirection(3);
                }
            }
        }

        if (!lockControlsP2) {
            if (p2 != null) {
                if (downArrow && num0) {
//                    p2.setPassthrough(true);
                }

                switch (code) {
                    case KeyEvent.VK_NUMPAD0:
                        // Numpad0 = jump
                        if (s && p2.getOnRide() && p2.getRideDirection() == "passing") {
                            p2.setPassthrough(true);
                        }
                        else {
                            p2.jump(JUMPING_SPEED);
                        }
                        break;
                    /*
                    case KeyEvent.VK_LEFT:
                        p2.startWalking(-WALKING_SPEED); // Left Arrow Key = walk left
                        p2.setWalking(true, false);
                        p2.setDirection(6);
                        break;
                    case KeyEvent.VK_RIGHT:
                        p2.startWalking(WALKING_SPEED); // Right Arrow Key = walk right
                        p2.setWalking(true, true);
                        p2.setDirection(2);
                        break;
                    */
                        
                    case KeyEvent.VK_ENTER:
                        // Enter Key = Dashing
                        p2.setDashing(true);
                        break;
                    case KeyEvent.VK_NUMPAD4:
                        // Numpad 1 = attacking
                        p2.setAttacking(true);
                        break;
                    default:
                        break;
                }

                //up
                if (upArrow) {
                    p2.setDirection(0);
                }

                //down
                if (downArrow) {
                    p2.setDirection(4);
                }
                
                //left
                if (leftArrow) {
                    p2.setWalking(true, false);
                    p2.startWalking(-WALKING_SPEED);
                    p2.setDirection(6);
                }
                
                //right
                if (rightArrow) {
                    p2.startWalking(WALKING_SPEED);
                    p2.setWalking(true, true);
                    p2.setDirection(2);
                }

                //up left
                if (leftArrow && upArrow) {
                    p2.setWalking(true, false);
                    p2.startWalking(-WALKING_SPEED);
                    p2.setDirection(5);
                }

                //up right
                if (rightArrow && upArrow) {
                    p2.setWalking(true, true);
                    p2.startWalking(WALKING_SPEED);
                    p2.setDirection(1);
                }

                //down left
                if (leftArrow && downArrow) {
                    p2.setWalking(true, false);
                    p2.startWalking(-WALKING_SPEED);
                    p2.setDirection(7);
                }

                //down right
                if (rightArrow && downArrow) {
                    p2.setWalking(true, true);
                    p2.startWalking(WALKING_SPEED);
                    p2.setDirection(3);
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_SPACE) {
            space = false;
        }
        if (code == KeyEvent.VK_NUMPAD0) {
            num0 = false;
        }

        if (code == KeyEvent.VK_W) {
            w = false;
        }
        if (code == KeyEvent.VK_S) {
            s = false;
        }
        if (code == KeyEvent.VK_A) {
            a = false;
        }
        if (code == KeyEvent.VK_D) {
            d = false;
        }
        if (code == KeyEvent.VK_UP) {
            upArrow = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downArrow = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftArrow = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightArrow = false;
        }

        //remove keys from key array
        pressedInt.remove(e.getKeyCode());
        checkDirection(e);
        
        if (!lockControlsP1) {
            if (a == false && d == false) {
                if (code == KeyEvent.VK_A) {
                    p1.stopWalking();
                    p1.setWalking(false, false);
                } else if (code == KeyEvent.VK_D) {

                    p1.stopWalking();
                    p1.setWalking(false, true);
                }
            }
            else {
                if (a) {
                    p1.setWalking(true, false);
                    p1.startWalking(-WALKING_SPEED);
                    if (w) {
                        p1.setDirection(7);
                    }
                    else if (s) {
                        p1.setDirection(5);
                    }
                    else {
                        p1.setDirection(6);
                    }
                }
                else if (d) {
                    p1.startWalking(WALKING_SPEED);
                    p1.setWalking(true, true);
                    if (w) {
                        p1.setDirection(1);
                    }
                    else if (s) {
                        p1.setDirection(3);
                    }
                    else {
                        p1.setDirection(2);
                    }
                }
            }
            
        }

        if (!lockControlsP2) {
            if (leftArrow == false && rightArrow == false) {
                if (code == KeyEvent.VK_LEFT) {
                    p2.stopWalking();
                    p2.setWalking(false, false);
                } else if (code == KeyEvent.VK_RIGHT) {
                    p2.stopWalking();
                    p2.setWalking(false, true);
                }
            }
            else {
                if (leftArrow) {
                    p2.setWalking(true, false);
                    p2.startWalking(-WALKING_SPEED);
                    
                    if (upArrow) {
                        p2.setDirection(7);
                    }
                    else if (downArrow) {
                        p2.setDirection(5);
                    }
                    else {
                        p2.setDirection(6);
                    }
                }
                else if (rightArrow) {
                    p2.startWalking(WALKING_SPEED);
                    p2.setWalking(true, true);
                    
                    if (upArrow) {
                        p2.setDirection(1);
                    }
                    else if (downArrow) {
                        p2.setDirection(3);
                    }
                    else {
                        p2.setDirection(2);
                    }
                }
            }
            
            
        }
    }

    /**
     *
     * @param b
     */
    public void setLockControlsP1(boolean b) {
        lockControlsP1 = b;
    }

    /**
     *
     * @param b
     * @return
     */
    public boolean getLockControlsP1(boolean b) {
        return lockControlsP1;
    }

    /**
     *
     * @param b
     */
    public void setLockControlsP2(boolean b) {
        lockControlsP2 = b;
    }

    /**
     *
     * @param b
     * @return
     */
    public boolean getLockControlsP2(boolean b) {
        return lockControlsP2;
    }

    /**
     *
     * @param p1
     * @param p2
     */
    public void setPlayers(Player1 p1, Player2 p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     *
     * @param p1
     */
    public void setPlayer1(Player1 p1) {
        this.p1 = p1;
    }

    /**
     *
     * @param p2
     */
    public void setPlayer2(Player2 p2) {
        this.p2 = p2;
    }

    //check direction after releasing keys || default values
    /**
     *
     * @param e
     */
    public void checkDirection(KeyEvent e) {
        for (Integer keyInt : pressedInt) {
            //Player 1
            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (keyInt == KeyEvent.VK_A) {
                    p1.setDirection(6);
                } else if (keyInt == KeyEvent.VK_D) {
                    p1.setDirection(2);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                if (keyInt == KeyEvent.VK_A) {
                    p1.setDirection(6);
                } else if (keyInt == KeyEvent.VK_D) {
                    p1.setDirection(2);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (keyInt == KeyEvent.VK_W) {
                    p1.setDirection(0);
                } else if (keyInt == KeyEvent.VK_S) {
                    p1.setDirection(4);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (keyInt == KeyEvent.VK_W) {
                    p1.setDirection(0);
                } else if (keyInt == KeyEvent.VK_S) {
                    p1.setDirection(4);
                }
            }

            //Player 2
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (keyInt == KeyEvent.VK_LEFT) {
                    p2.setDirection(6);
                } else if (keyInt == KeyEvent.VK_RIGHT) {
                    p2.setDirection(2);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (keyInt == KeyEvent.VK_LEFT) {
                    p2.setDirection(6);
                } else if (keyInt == KeyEvent.VK_RIGHT) {
                    p2.setDirection(2);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (keyInt == KeyEvent.VK_UP) {
                    p2.setDirection(0);
                } else if (keyInt == KeyEvent.VK_DOWN) {
                    p2.setDirection(4);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (keyInt == KeyEvent.VK_UP) {
                    p2.setDirection(0);
                } else if (keyInt == KeyEvent.VK_DOWN) {
                    p2.setDirection(4);
                }
            }
        }

    }

}
