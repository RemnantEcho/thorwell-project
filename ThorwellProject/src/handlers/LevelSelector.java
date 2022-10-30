package handlers;

/**
 * Enum used to determine the level selected; levels should be added to this
 * enum to allow the game to change to it.
 *
 * @author jly09
 */
public enum LevelSelector {

    /**
     *
     */
    ForestLevel(0),
    /**
     *
     */
    IceLevel(1),
    /**
     *
     */
    SkyLevel(2);

    int levelCode;

    LevelSelector(int levelCode) {
        this.levelCode = levelCode;
    }

    /**
     *
     * @return
     */
    public int getLevelCode() {
        return levelCode;
    }

}
