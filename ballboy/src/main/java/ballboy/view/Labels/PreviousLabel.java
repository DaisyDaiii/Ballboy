package ballboy.view.Labels;

public class PreviousLabel extends CreateLabel {

    public PreviousLabel(double x, double y){
        super(x, y);
        l.setText("Previous Total Score: 0");
    }

    @Override
    public void changeText(String s) {
        l.setText("Previous Total Score: " + s);
    }
}
