package ballboy.model.observer;

import ballboy.view.Labels.CreateLabel;

// observe a label
public abstract class Observer {
    protected CreateLabel label;

    public Observer(CreateLabel label) {
        this.label = label;
    }

    // change score
    public abstract void update(int num);

    // reset to the original score 0
    public abstract void reset(int num);

    // return score
    public abstract int getScore();
}
