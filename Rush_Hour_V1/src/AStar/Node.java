package AStar;

import java.util.ArrayList;

public class Node implements IPrototype<Node>{
    private int vehicleId;
    private boolean goal;
    private boolean obstacle;
    private int line;
    private int column;
    private ArrayList<Node> neighborList;
    private boolean visited;

    public Node(){
        neighborList = new ArrayList<Node>();
    }

    public Node(ArrayList<Node> nodes){
        neighborList = nodes;
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
        return neighborList;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
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
}
