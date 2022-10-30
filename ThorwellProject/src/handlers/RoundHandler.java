/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import static java.lang.Math.abs;
import main.GameWorld;

/**
 * Impromptu timer used to determine the current round timer and round state;
 * involves pre round, main round, round ending, round end.
 *
 * @author jly09
 */
public class RoundHandler implements StepListener {

    GameWorld world;
    KeyController keyControl;
    private long startTime;
    private long pauseTime;
    private long delay;
    private boolean finished;
    private boolean started;
    private long elapsed;
    private long elapsedSecs;
    
    private boolean isPaused;

    private boolean mainRound;
    private boolean roundEnding;
    private boolean preRound;
    private boolean roundEnd;

    /**
     *
     * @param world
     */
    public RoundHandler(GameWorld world) {
        this.world = world;
        keyControl = world.getKeyController();
        mainRound = false;
        roundEnding = false;
        preRound = false;
        roundEnd = false;
        
        isPaused = false;

        finished = true;
        started = false;

        delay = 2000;
        startTime = System.nanoTime();
    }

    /**
     *
     * @param started
     */
    public void setStarted(boolean started) {
        this.started = started;
        finished = false;

        startTime = System.nanoTime();
    }

    /**
     *
     * @param started
     */
    public void setPreRound(boolean started) {
        this.started = started;
        finished = false;

        preRound = true;
        mainRound = false;
        roundEnding = false;
        roundEnd = false;

        delay = 2000;
        startTime = System.nanoTime();
    }

    /**
     *
     * @param started
     */
    public void setMainRound(boolean started) {
        this.started = started;
        finished = false;

        preRound = false;
        mainRound = true;
        roundEnding = false;
        roundEnd = false;

        delay = 120000;
        startTime = System.nanoTime();
    }

    /**
     *
     * @param started
     */
    public void setRoundEnd(boolean started) {
        this.started = started;
        finished = false;

        preRound = false;
        mainRound = false;
        roundEnding = true;
        roundEnd = false;

        delay = 2000;
        startTime = System.nanoTime();
    }

    /**
     *
     * @return
     */
    public boolean getStarted() {
        return started;
    }

    /**
     *
     * @return
     */
    public boolean getRoundEnd() {
        return roundEnd;
    }

    /**
     *
     * @return
     */
    public String getRoundTimer() {
        long roundTime = delay / 1000;
        long currentTime = roundTime - elapsedSecs;

        long minutes = (int) currentTime / 60;
        long seconds = currentTime - minutes * 60;

        if (minutes <= 0 && seconds <= 0) {
            return "0:00";
        }

        return Long.toString(minutes) + ":" + String.format("%02d", seconds);
        //return Long.toString(minutes);
    }

    /**
     *
     * @return
     */
    public boolean getMainRound() {
        return mainRound;
    }

    /**
     *
     * @return
     */
    public boolean getPreRound() {
        return preRound;
    }

    /**
     *
     * @return
     */
    public boolean getRoundEnding() {
        return roundEnding;
    }

    /**
     *
     * @param e
     */
    @Override
    public void preStep(StepEvent e) {

    }

    /**
     *
     * @param e
     */
    @Override
    public void postStep(StepEvent e) {
        //System.out.println(finished);
        if (finished) {
            return;
        }

        if (preRound) {
            keyControl.setLockControlsP1(true);
            keyControl.setLockControlsP2(true);
        } else {
            keyControl.setLockControlsP1(false);
            keyControl.setLockControlsP2(false);
        }
        
        
        elapsed = (System.nanoTime() - startTime) / 1000000;
        elapsedSecs = elapsed / 1000;
        
        

        //System.out.println("Start Time: " + startTime);
        //System.out.println("Elapsed Time: " + elapsed);
        if (elapsed >= delay) {
            //finished = true;
            started = false;
            startTime = System.nanoTime();

            if (preRound) {

                setMainRound(true);
            } else if (mainRound) {

                setRoundEnd(true);
            } else if (roundEnding) {
                finished = true;
                roundEnding = false;
                roundEnd = true;

                if (world.getLevelSelector().getLevelCode() == 0) {
                    world.getIceLevel().setLocked(false);
                } else if (world.getLevelSelector().getLevelCode() == 1) {
                    world.getSkyLevel().setLocked(false);
                } else if (world.getLevelSelector().getLevelCode() == 2) {

                }

                world.getStateManager().setLevelCode(2);
                world.getStateManager().checkState(false);
            }
        }

    }

    /**
     *
     * @param started
     */
    public void resetTimer(boolean started) {
        this.started = started;
        finished = false;

        preRound = true;
        mainRound = false;
        roundEnding = false;
        roundEnd = false;

        delay = 2000;
        startTime = System.nanoTime();
    }
    
    public void setPause(boolean p) {
        isPaused = p;
        if (p) {
            pauseTime = System.nanoTime();
        }
        else {
            long diff = Math.abs(System.nanoTime() - pauseTime);
            startTime = startTime + diff;
        }
    }
    
    public boolean getPause() {
        return isPaused;
    }
    
    public void togglePause() {
        if (isPaused) {
            isPaused = false;
            long diff = Math.abs(System.nanoTime() - pauseTime);
            startTime = startTime + diff;
        }
        else {
            isPaused = true;
            pauseTime = System.nanoTime();
        }
    }

}
