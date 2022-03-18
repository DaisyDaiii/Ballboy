package ballboy.model.Memento;

import ballboy.model.Entity;
import ballboy.model.GameEngine;

import java.util.ArrayList;
import java.util.List;

public class CreateMemento {
    private Originator originator = new Originator();
    private CareTaker careTaker = new CareTaker();

    public void create(GameEngine model){
        originator.setState(model.getCurrentLevel().cloneEntity());
        Memento mem = originator.saveStateToMemento();
        mem.setLevelNum(model.getCurrentLevel().getChangeLevel().getCurr());
        List<Integer> score = new ArrayList<Integer>();
        score.add(model.getCurrentLevel().getChangeScore().getCurrObserver().getScore());
        score.add(model.getCurrentLevel().getChangeScore().getPreObserver().getScore());
        score.add(model.getCurrentLevel().getChangeScore().getRed().getScore());
        score.add(model.getCurrentLevel().getChangeScore().getGreen().getScore());
        score.add(model.getCurrentLevel().getChangeScore().getBlue().getScore());
        mem.setScores(score);
        careTaker.add(mem);
    }

    public void getMem(GameEngine model){
        Memento me = careTaker.get();
        if(me != null){
            originator.getStateFromMemento(me);
            List<Entity> state = originator.getState();
            List<Entity> newS = new ArrayList<>();
            for(int i = 0; i<state.size(); i++){
                newS.add(state.get(i).cloneEntity());
            }
            model.getCurrentLevel().changeLevel(me.getLevelNum(), newS, me.getScores());
        }
    }

}
