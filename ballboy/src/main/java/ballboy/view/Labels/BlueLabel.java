package ballboy.view.Labels;

import javafx.scene.paint.Color;

public class BlueLabel extends CreateLabel {

    public BlueLabel(double x, double y){
        super(x, y);

        l.setText("Blue Score: 0");
        l.setTextFill(Color.BLUE);
    }

    @Override
    public void changeText(String s) {
        l.setText("Blue Score: " + s);
    }
}
