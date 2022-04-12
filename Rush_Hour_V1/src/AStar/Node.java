package AStar;

import java.util.ArrayList;

public class Node implements IPrototype<Node>, Comparable<Node>{
    private State state;
    private int f;
    private int vehicleId;
    private boolean goal;
    private boolean obstacle;
    private int line;
    private int column;
    private ArrayList<Node> neighborList;
    private boolean visited;
    private Node previousNode;
    private int depth;
    private Node parent;
    
    public Node(){
        neighborList = new ArrayList<Node>();
    }

    public Node(ArrayList<Node> nodes){
        neighborList = nodes;
    }
    
    public Node(State state, int depth, Node parent) {
        this.state = state;
        this.depth = depth;
        this.parent = parent;
    }
    
    public Node getParent() {
        return parent;
    }
    
    public State getState() {
        return state;
    }
    	
    public int getDepth() {
	return depth;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public boolean isGoal() {
        return goal;
    }

    public boolean isObstacle() {
        return obstacle;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
    
    public ArrayList<Node> getNeighborList() {
//        State[] new_states = state.expand();
        ArrayList<Node> new_nodes = new ArrayList<>();
        
        //for (int i = 0; i < new_states.length; i++)
         //   new_nodes.add(new Node(new_states[i], depth + 1, this));
        return new_nodes;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public Node getPreviousNode() {
        return previousNode;
    }
    
    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }
    
    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setNeighborList(ArrayList<Node> neighborList) {
        this.neighborList = neighborList;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    public void setF(int h) {
	this.f = this.getDepth() + h;		
    }

    @Override
    public Node clone() {
        Node clone = new Node();
        clone.setColumn(getColumn());
        clone.setGoal(isGoal());
        clone.setLine(getLine());
        clone.setObstacle(isObstacle());
        clone.setVehicleId(getVehicleId());
        clone.setVisited(isVisited());
        clone.setNeighborList(getNeighborList());
        return clone;
    }

    @Override
    public Node deepClone() {
        ArrayList<Node> cloneNeighbor = new ArrayList();
        for (Node node:neighborList) {
            cloneNeighbor.add(node.clone());
        }
        Node clone = new Node(cloneNeighbor);
        clone.setColumn(getColumn());
        clone.setGoal(isGoal());
        clone.setLine(getLine());
        clone.setObstacle(isObstacle());
        clone.setVehicleId(getVehicleId());
        clone.setVisited(isVisited());
        return clone;
    }

    @Override
    public int compareTo(Node node) {
        if (this.f == node.f) {
            return 0;
        }

        if (this.f > node.f) {
            return 1;
        }
        return -1;
    }
}
