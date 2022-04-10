package AStar;

import java.util.ArrayList;

public class Map implements IPrototype<Map>{
    private ArrayList<ArrayList<Node>> map;

    public Map(){
        map = new ArrayList<>();
        for (int i = 0;i < 6;i++){
            ArrayList column = new ArrayList<Node>();
            for (int j = 0; j < 6; j++) {
                column.add(new Node());
            }
            map.add(column);
        }
    }

    public Map(ArrayList map){
        this.map = map;
    }

    @Override
    public Map clone() {
        return new Map();
    }

    @Override
    public Map deepClone() {
        ArrayList<ArrayList<Node>> cloneMap = new ArrayList<>();
        for (ArrayList<Node> row:map) {
            ArrayList<Node> line = new ArrayList<Node>();
            for (Node column:row) {
                line.add(column.deepClone());
            }
        }
        return new Map(cloneMap);
    }
}
