package ballboy.view.Labels;

public class CurrentLabel extends CreateLabel {

    public CurrentLabel(double x, double y){
        super(x, y);
        l.setText("Current Level Score: 0");
    }

    @Override
    public void changeText(String s) {
        l.setText("Current Level Score: " + s);
    }
}
