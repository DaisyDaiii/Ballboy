package ballboy.model.observer;
import java.util.ArrayList;
import java.util.List;

// notify specific observers
public class Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    // add
    public void attach(Observer observer){
        observers.add(observer);
    }

    // delete
    public void detach(Observer observer){
        observers.remove(observer);
    }

    public void notifyObserver(int num){
        for (Observer observer : observers) {
            observer.update(num);
        }
    }

    public void resetObserver(int num){
        for (Observer observer : observers) {
            observer.reset(num);
        }
    }


}
