package ballboy.model.Memento;

import ballboy.model.Entity;
import ballboy.model.Level;

import java.util.ArrayList;
import java.util.List;

// create memento
public class Originator {
    private List<Entity> states = new ArrayList<>();

    public void setState(List<Entity> entity){
        states.clear();
        states.addAll(entity);
    }

    public List<Entity> getState(){
        return states;
    }

    public Memento saveStateToMemento(){
        return new Memento(states);
    }

    public void getStateFromMemento(Memento Memento){
        states.clear();
        states.addAll(Memento.getState());
    }
}
