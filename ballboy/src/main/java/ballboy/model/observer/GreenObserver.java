package ballboy.model.observer;

import ballboy.view.Labels.CreateLabel;

// observe green score
public class GreenObserver extends Observer {
    private static int score = 0;

    public GreenObserver(CreateLabel label) {
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

