package AStar;

import java.util.ArrayList;

public class Road {
    
    public Road(){};
    
    public ArrayList<Node> listNodes = new ArrayList<>();
    
    public void addNode(Node n) {
        listNodes.add(n);
    }
}
