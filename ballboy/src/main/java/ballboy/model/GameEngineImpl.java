package ballboy.model;

import ballboy.model.observer.ChangeScore;
import ballboy.view.Labels.AddLabel;


/**
 * Implementation of the GameEngine interface.
 * This provides a common interface for the entire game.
 */
public class GameEngineImpl implements GameEngine {
    private final Level level;

    private ChangeScore changeScore;

    public GameEngineImpl(Level level) {
        this.level = level;
    }

    public Level getCurrentLevel() {
        return level;
    }

    public void startLevel() {
        // TODO: Handle when multiple levels has been implemented
        return;
    }

    public boolean boostHeight() {
        return level.boostHeight();
    }

    public boolean dropHeight() {
        return level.dropHeight();
    }

    public boolean moveLeft() {
        return level.moveLeft();
    }

    public boolean moveRight() {
        return level.moveRight();
    }

    public void tick() {
        level.update();
    }

    public void setChangeScore(AddLabel add) {
        changeScore = new ChangeScore(add);
        level.setScore(changeScore);
    }

}