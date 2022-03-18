package ballboy.model.Memento;

// save memento
public class CareTaker {
    private Memento memento = null;

    public void add(Memento state){
        memento = state;
    }

    public Memento get(){
        return memento;
    }
}
