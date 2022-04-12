package AStar;

import Heuristic.Heuristic;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AStar {
    private static PriorityQueue<State> pq = new PriorityQueue<>(10, (State o1, State o2) -> o1.cost - o2.cost);;
    
    
    public static LinkedList<State> searchAStar(Map map, Heuristic heuristic){
        
        HashMap<State, State> predecessor = new HashMap<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        State src = new State(map);
        State goal = null;
        src.setCost(0);
        pq.add(src);
        visited.put(src.toString(), true);
        while(!pq.isEmpty()){
            State u = pq.poll();
            if(u.isGoal()){
                goal = u;
                break;
            }
            u.getNeighbors().forEach((v) -> {
                int cost = u.cost + 1 + heuristic.getValue(v);
                if (!contains(visited, v)) {
                    v.setCost(cost);
                    pq.add(v);
                    predecessor.put(v, u);
                    visited.put(v.toString(), true);
                }
            });
        }

        return Path.getPath(predecessor, goal);
    };
    
    
    private static boolean contains(HashMap<String, Boolean> visited, State v) {
        return visited.containsKey(v.toString());
    }

}