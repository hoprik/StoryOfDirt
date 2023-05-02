package ru.hoprik.story.hero;
import javafx.util.Callback;
import ru.hoprik.HoprikStory;

import java.util.ArrayList;
import java.util.List;
public class Executer {
    List<Action> callbacks = new ArrayList<>();
    boolean Run = true;
    int idAction = 0;
    public Executer(){
    }
    public void add(Runnable func, int second){
        callbacks.add(new Action(func, second));
    }
    public void addS(Runnable func, int second){
        callbacks.add(new Action(func, second*1000));
    }
    public void Stop(){
        Run = false;
    }
    public void Resume(){
        Run = true;
    }
    public void Exec(){
        new Thread(()->{
            while (Run){
                Action action = callbacks.get(idAction);
                action.callback.run();
                try {
                    Thread.sleep(action.timer);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                idAction++;
                if (idAction == callbacks.size()){
                    Run = false;
                    idAction = 0;
                }
            }
        }).start();
    }
}
class Action{
    Runnable callback;
    int timer;
    public Action(Runnable callback, int timer){
        this.callback = callback;
        this.timer = timer;
    }
    @Override
    public String toString() {
        return "Action{" +
                "callback=" + callback +
                ", timer=" + timer +
                '}';
    }
}