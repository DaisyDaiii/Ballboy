package ballboy.model.Memento;

import ballboy.model.Entity;
import java.util.ArrayList;
import java.util.List;

// save states of level
public class Memento {
    private List<Entity> states = new ArrayList<Entity>();
    private int levelNum;
    private List<Integer> scores = new ArrayList<Integer>();


    public Memento(List<Entity> state){
        this.states.addAll(state);
    }

    public List<Entity> getState(){
        return states;
    }

    public void setLevelNum(int n){
        levelNum = n;
    }

    public int getLevelNum(){
        return levelNum;
    }

    public void setScores(List<Integer> i){
        scores = i;
    }

    public List<Integer> getScores(){
        return scores;
    }
}
