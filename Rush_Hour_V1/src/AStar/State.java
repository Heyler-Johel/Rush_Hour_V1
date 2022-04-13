package AStar;

import java.util.ArrayList;
import java.util.LinkedList;

public class State implements IPrototype<State>{
	
	public Map map;
        public int cantMov;
	int cost;
	
	
	public State(Map map) {
		this.map = map;
		this.cost = 10000000;
	}
	
	public ArrayList<State> getNeighbors(){
		ArrayList<State> neighbors = new ArrayList<>();
		LinkedList<Vehicle> vehicles = this.map.vehicles; 
                int [] goalPoint = {this.map.goalx, this.map.goaly};
		for (int i = 0; i < vehicles.size(); i++) {
			Vehicle vehicle = vehicles.get(i);
			if(vehicle.isVertical()){
				LinkedList<Vehicle> newvehicles = cloneVehicles(vehicles);
				Vehicle newvehicle = newvehicles.get(i);
				while(map.canMoveDown(newvehicle)){
					newvehicle.moveDown();

					neighbors.add(new State(new Map(this.map.gridSize, newvehicles, goalPoint)));
					newvehicles = cloneVehicles(newvehicles);
					newvehicle = newvehicles.get(i);
				}
				newvehicles = cloneVehicles(vehicles);
				newvehicle = newvehicles.get(i);
				while(map.canMoveUp(newvehicle)){
					newvehicle.moveUp();
					neighbors.add(new State(new Map(this.map.gridSize, newvehicles, goalPoint)));
					newvehicles = cloneVehicles(newvehicles);
					newvehicle = newvehicles.get(i);
				}
			}
			else if(vehicle.isHorizontal()){
				LinkedList<Vehicle> newvehicles = cloneVehicles(vehicles);
				Vehicle newvehicle = newvehicles.get(i);
				while(map.canMoveRight(newvehicle)){
					newvehicle.moveRight();
					neighbors.add(new State(new Map(this.map.gridSize, newvehicles, goalPoint)));
					newvehicles = cloneVehicles(newvehicles);
					newvehicle = newvehicles.get(i);
				}
				newvehicles = cloneVehicles(vehicles);
				newvehicle = newvehicles.get(i);
				while(map.canMoveLeft(newvehicle)){
					newvehicle.moveLeft();
					neighbors.add(new State(new Map(this.map.gridSize, newvehicles, goalPoint)));
					newvehicles = cloneVehicles(newvehicles);
					newvehicle = newvehicles.get(i);
				}
			}
		}
		
		return neighbors;
	}
	
	private LinkedList<Vehicle> cloneVehicles(LinkedList<Vehicle> vehicles) {
		LinkedList<Vehicle> newvehicles = new LinkedList<>();
		for(Vehicle vehicle : vehicles){
			newvehicles.add(vehicle.clone());
		}
		return newvehicles;
	}


	public boolean isGoal(){
              return map.getRedVehicle().y == map.goaly && map.getRedVehicle().x == map.goalx;
//            if (map.getRedVehicle().horizontal)
//                return map.getRedVehicle().y == map.gridSize - 2;
//            else
//                return map.getRedVehicle().x == map.gridSize - 2;
	}
	
	public void print(){
		System.out.println(this.toString());
	}
	
	public String toString(){
		String[][] output = new String[map.gridSize][map.gridSize];
		for (int i = 0; i < output.length; i++) {
			for (int j = 0; j < output.length; j++) {
				output[i][j] = "□";
			}
		}
		for(Vehicle vehicle : map.vehicles){
                    if(vehicle.isHorizontal()){
                        if(vehicle.size == 2){
                                if(vehicle.equals(map.getRedVehicle())){
                                        output[vehicle.x][vehicle.y] = "◄";
                                        output[vehicle.x][vehicle.y+1] = "▶";
                                }
                                else{
                                        output[vehicle.x][vehicle.y] = "◄";
                                        output[vehicle.x][vehicle.y+1] = "▶";
                                }
                        }
                        else if(vehicle.size == 3){
                                output[vehicle.x][vehicle.y] = "◄";
                                output[vehicle.x][vehicle.y+1] = "■";            
                                output[vehicle.x][vehicle.y+2] = "▶";
                        }
                    }
                    else if(vehicle.isVertical()){
                        if(vehicle.size == 2){
                                output[vehicle.x][vehicle.y] = "▲";
                                output[vehicle.x+1][vehicle.y] = "▼";
                        }
                        else if(vehicle.size == 3){
                                output[vehicle.x][vehicle.y] = "▲";
                                output[vehicle.x+1][vehicle.y] = "■";
                                output[vehicle.x+2][vehicle.y] = "▼";
                        }
                    }
		}
		
		String result = "";
		for (int i = 0; i < output.length; i++) {
			result += String.join(" ", output[i] ) + "\n";
                        result += "\n";
		}
		
		return result;
	}
	
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
    @Override
    public State clone(){
        return new State(this.map);
    }

    @Override
    public State deepClone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}