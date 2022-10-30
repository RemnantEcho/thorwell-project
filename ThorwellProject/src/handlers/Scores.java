package handlers;

import entities.Player;
import main.GameWorld;

/**
 * Handles the scores of the players; allows scores to be set and retrieved from
 * this class.
 *
 * @author jly09
 */
public class Scores {

    GameWorld world;

    private int p1Score;
    private int p2Score;

    /**
     *
     * @param world
     */
    public Scores(GameWorld world) {
        this.world = world;
        p1Score = 0;
        p2Score = 0;
    }

    /**
     *
     */
    public void incrementP1Score() {
        p1Score++;
    }

    /**
     *
     */
    public void decrementP1Score() {
        p1Score--;
    }

    /**
     *
     * @param score
     */
    public void setP1Score(int score) {
        p1Score = score;
    }

    /**
     *
     * @return
     */
    public int getP1Score() {
        return p1Score;
    }

    /**
     *
     */
    public void incrementP2Score() {
        System.out.println("Increment");
        p2Score++;
    }

    /**
     *
     */
    public void decrementP2Score() {
        p2Score--;
    }

    /**
     *
     * @param score
     */
    public void setP2Score(int score) {
        p2Score = score;
    }

    /**
     *
     * @return
     */
    public int getP2Score() {
        return p2Score;
    }

    /**
     *
     */
    public void resetScores() {
        p1Score = 0;
        p2Score = 0;
    }

    /**
     *
     * @return
     */
    public Player calculateWinner() {
        if (p1Score > p2Score) {
            return world.getPlayer1();
        } else if (p2Score > p1Score) {
            return world.getPlayer2();
        }

        return null;
    }
}
