package ballboy.view.Labels;

import javafx.scene.layout.Pane;

public class AddLabel{
    private CreateLabel blue;
    private CreateLabel red;
    private CreateLabel green;
    private CreateLabel level;
    private CreateLabel pre;

    public AddLabel(){
        blue = new BlueLabel(30,90);
        red = new RedLabel(30, 70);
        green = new GreenLabel(30, 110);
        level = new CurrentLabel(30, 50);
        pre = new PreviousLabel(30, 30);
    }

    public CreateLabel getLabel(String name){
        switch(name){
            case "red":
                return red;
            case "blue":
                return blue;
            case "green":
                return green;
            case "pre":
                return pre;
            case "level":
                return level;
        }
        return null;
    }

    public void add(Pane pane){
        pane.getChildren().add(blue.getLabel());
        pane.getChildren().add(red.getLabel());
        pane.getChildren().add(green.getLabel());
        pane.getChildren().add(level.getLabel());
        pane.getChildren().add(pre.getLabel());
    }
}
