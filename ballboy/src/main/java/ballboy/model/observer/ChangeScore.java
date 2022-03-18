package ballboy.model.observer;

import ballboy.model.Entity;
import ballboy.view.Labels.AddLabel;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

/**
 * change scores
 * 1. squarecat touches an enemy
 * 2. finish a level
 */
public class ChangeScore {
    private AddLabel add;
    private Subject subject;
    private Observer enemyObserver;
    private Observer currObserver;
    private Observer preObserver;
    private Observer red;
    private Observer green;
    private Observer blue;

    public ChangeScore(AddLabel add){
        this.add = add;
        this.subject = new Subject();
        this.currObserver = new LevelObserevr(add.getLabel("level"));
        this.preObserver = new PreviousObserver(add.getLabel("pre"));
        this.red = new RedObserver(add.getLabel("red"));
        this.green = new GreenObserver(add.getLabel("green"));
        this.blue = new BlueObserver(add.getLabel("blue"));
    }

    public Observer getEnemy(Entity e){ // identify the color of the enemy
        PixelReader pixelReader = e.getImage().getPixelReader();
        Color color = pixelReader.getColor((int) (e.getImage().getHeight()/2), (int) (e.getImage().getWidth()/2));

        if(color.toString().equals("0xb6df0aff")){
            return green;
        }else if (color.toString().equals("0x9cbbfdff")){
            return blue;
        }else if (color.toString().equals("0xfd9c9cff")){
            return red;
        }
        return null;
    }

    public void score(Entity e){ // when squarecat touches an enemy, add a score
        enemyObserver = getEnemy(e);
        subject.attach(enemyObserver);
        subject.attach(currObserver);
        subject.notifyObserver(1);
        subject.detach(enemyObserver);
        subject.detach(currObserver);
    }

    public void newLevel(){ // change to a new level, update scores
        subject.attach(preObserver);
        subject.notifyObserver(currObserver.getScore());
        subject.detach(preObserver);

        subject.attach(red);
        subject.attach(green);
        subject.attach(blue);
        subject.attach(currObserver);
        subject.resetObserver(0);
        subject.detach(red);
        subject.detach(green);
        subject.detach(blue);
        subject.detach(currObserver);
    }

    public Observer getPreObserver(){
        return preObserver;
    }

    public Observer getCurrObserver(){
        return currObserver;
    }

    public Observer getRed(){
        return red;
    }

    public Observer getBlue(){
        return blue;
    }

    public Observer getGreen(){
        return green;
    }


}
