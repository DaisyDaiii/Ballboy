package ballboy.view.Labels;

import javafx.scene.control.Label;

public abstract class CreateLabel {
    protected Label l;

    public CreateLabel(double x, double y){
        l = new Label();
        l.setLayoutY(y);
        l.setLayoutX(x);
        l.setStyle("-fx-font-family: 'serif'");
    }

    public Label getLabel(){
        return l;
    };

    public abstract void changeText(String s);
}
