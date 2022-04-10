package AStar;

import java.util.ArrayList;
import java.util.HashMap;

public class AStar {

    public HashMap<Integer, Vehicle> vehicles = new HashMap<>();
    public ArrayList<Map> open = new ArrayList<>();
    public ArrayList<Map> close = new ArrayList<>();
    
    public static void start (){
        System.out.println("Hola Mundo"); 
    };
    
    public Road shortestRoad (){
        return null;
    };
    
    
    public static void main (String args[]){
        AStar.start();
    }
}
