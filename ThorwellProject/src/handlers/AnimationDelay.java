/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import entities.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Impromptu timer used to create a delay between the change of each frame for
 * the player, after each delay images are retrieved from this class, uses a
 * step listener instead of a java swing timer.
 *
 * @author jly09
 */
public class AnimationDelay implements StepListener {

    private long startTime;
    private long delay;
    private boolean finished;
    private boolean started;
    private long elapsed;
    private int currentFrame;
    private boolean playedOnce;

    private List<BodyImage> imageList = new ArrayList<>();

    /**
     *
     */
    public AnimationDelay() {
        finished = true;
        started = false;
        playedOnce = true;
        currentFrame = 0;

        delay = 1000;
        startTime = System.nanoTime();
    }

    /**
     *
     * @param d
     */
    public void setDelay(long d) {
        delay = d;
    }

    /**
     *
     */
    public void setStarted() {
        started = true;
        finished = false;
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
    public boolean getFinished() {
        return finished;
    }

    /**
     *
     * @return
     */
    public int getCurrentFrame() {
        return currentFrame;
    }

    /**
     *
     * @return
     */
    public boolean getPlayedOnce() {
        return playedOnce;
    }

    /**
     *
     * @param imageList
     */
    public void setFrames(List<BodyImage> imageList) {
        this.imageList = imageList;
        //System.out.println(imageList.size());
        started = true;
        finished = false;
        startTime = System.nanoTime();
        playedOnce = false;
        currentFrame = 0;
    }

    /**
     *
     * @return
     */
    public BodyImage getImage() {
        return imageList.get(currentFrame);
    }

    /**
     *
     * @return
     */
    public List<BodyImage> getImageList() {
        return imageList;
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

        if (playedOnce) {
            return;
        }
        elapsed = (System.nanoTime() - startTime) / 1000000;

        //System.out.println("Start Time: " + startTime);
        //System.out.println("Elapsed Time: " + elapsed);
        if (elapsed >= delay) {
            currentFrame++;
            finished = true;
            started = false;
            startTime = System.nanoTime();
            playedOnce = false;
        }

        if (currentFrame == imageList.size()) {
            playedOnce = true;
            currentFrame = 0;
        }

    }

}
