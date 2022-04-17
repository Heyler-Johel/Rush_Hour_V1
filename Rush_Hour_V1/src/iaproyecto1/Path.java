/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iaproyecto1;

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
