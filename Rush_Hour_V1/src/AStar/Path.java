package AStar;

import java.util.HashMap;
import java.util.LinkedList;

public class Path {
    
    public static LinkedList<State> path = new LinkedList<>();
    
    private static void addFirstState(State n) {
        path.addFirst(n);
    }
    
    public static LinkedList<State> getPath(HashMap<State, State> pred, State goal) {
        State u = goal;
        addFirstState(u.clone());
        while(pred.get(u) != null){
            State parent = pred.get(u);
            addFirstState(parent.clone());
            u = parent;
        }
        return path;
    }
}
