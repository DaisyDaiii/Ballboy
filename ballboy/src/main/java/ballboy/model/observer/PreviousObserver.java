package ballboy.model.observer;

import ballboy.view.Labels.CreateLabel;

// observe total score of previous levels
public class PreviousObserver extends Observer {
    private static int score = 0;

    public PreviousObserver(CreateLabel label) {
        super(label);
    }

    @Override
    public void update(int num) {
        score += num;
        label.changeText(String.valueOf(score));
    }

    @Override
    public void reset(int num) {
        this.score = num;
        label.changeText(String.valueOf(score));
    }

    @Override
    public int getScore() {
        return score;
    }
}
