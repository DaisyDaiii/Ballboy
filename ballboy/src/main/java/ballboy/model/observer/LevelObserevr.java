package ballboy.model.observer;

import ballboy.view.Labels.CreateLabel;

// observe current level score
public class LevelObserevr extends Observer  {
    private static int score = 0;

    public LevelObserevr(CreateLabel label) {
        super(label);
    }

    @Override
    public void update(int num) {
        score += num;
        label.changeText(String.valueOf(score));
    }

    @Override
    public void reset(int num) {
        score = num;
        label.changeText(String.valueOf(score));
    }

    @Override
    public int getScore() {
        return score;
    }
}
