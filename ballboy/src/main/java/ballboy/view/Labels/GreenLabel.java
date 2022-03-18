package ballboy.view.Labels;

import javafx.scene.paint.Color;

public class GreenLabel extends CreateLabel {

    public GreenLabel(double x, double y){
        super(x, y);
        l.setText("Green Score: 0");
        l.setTextFill(Color.GREEN);
    }

    @Override
    public void changeText(String s) {
        l.setText("Green Score: " + s);
    }
}
