/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iaproyecto1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStar {
    private static final PriorityQueue<State> open = new PriorityQueue<>(10, (State o1, State o2) -> o1.cost - o2.cost);
    private static final HashMap<String, Boolean> close = new HashMap<>();
    
    public static LinkedList<State> searchPath(Map map){
        Heuristic heuristic = new Heuristic();
        HashMap<State, State> predecessor = new HashMap<>();
        State src = new State(map);
        State goal = null;
        src.cost = 0;
        src.steps = 0;
        open.add(src);
        close.put(src.toString(), true);
        while(!open.isEmpty()){
            State u = open.poll();
            if(u.isGoal()){
                goal = u;
                break;
            }
            u.getNeighbors().forEach((v) -> {
                int cost = u.steps + heuristic.getValue(v);
                if (!contains(close, v)) { 
                    v.steps = u.steps + 1;
                    v.cost = cost;
                    open.add(v);
                    predecessor.put(v, u);
                    close.put(v.toString(), true);
                }
            });
        }

        return Path.getPath(predecessor, goal);
    };
    
    
    private static boolean contains(HashMap<String, Boolean> visited, State v){
        return visited.containsKey(v.toString());
    }
}
