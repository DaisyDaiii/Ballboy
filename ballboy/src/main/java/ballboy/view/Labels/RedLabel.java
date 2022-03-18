package ballboy.view.Labels;

import javafx.scene.paint.Color;

public class RedLabel extends CreateLabel{

    public RedLabel(double x, double y){
        super(x, y);
        l.setText("Red Score: 0");
        l.setTextFill(Color.RED);
    }

    @Override
    public void changeText(String s) {
        l.setText("Red Score: " + s);
    }
}
