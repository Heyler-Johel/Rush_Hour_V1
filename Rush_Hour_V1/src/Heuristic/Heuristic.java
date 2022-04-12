package Heuristic;

import AStar.State;
import AStar.Map;
import AStar.Vehicle;
import java.util.ArrayList;

/**
 * This is a template for the class corresponding to your original advanced
 * heuristic. This class is an implementation of the <tt>Heuristic</tt>
 * interface. After thinking of an original heuristic, you should implement it
 * here, filling in the constructor and the <tt>getValue</tt> method.
 */
public class Heuristic {

    private Map map;
    private State state;
    private int numVehicles;
    ArrayList<Integer> visited;

    public Heuristic() {
    }
public int getValue(State state) {
		Vehicle redvehicle = state.map.getRedVehicle();
		int gridSize = state.map.gridSize;
		int numberOfMovements = 0;
		for (int i = redvehicle.y+2; i < gridSize; i++) {
			int x = redvehicle.x;
			int y = i;
			if(state.map.crashVehicles(x, y)){
				Vehicle vehicle = state.map.crashedVehicle; 
				if(!state.map.canMoveDown(vehicle) && !state.map.canMoveUp(vehicle))
					numberOfMovements += 2;
				else
					numberOfMovements ++;
			}
		}
		return numberOfMovements;
	}

}
