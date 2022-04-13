package AStar;

import java.util.LinkedList;

public final class Map{
    public int gridSize;
    public LinkedList<Vehicle> vehicles;
    public Vehicle crashedVehicle;
    public int goalx;
    public int goaly;

    public Map(int gridSize, LinkedList<Vehicle> vehicles, int[] goalPoint) {
        super();
        this.gridSize = gridSize;
        this.vehicles = vehicles;
        this.goalx = goalPoint[0];
        this.goaly = goalPoint[1];
        valideGoal();
    }

    private void valideGoal(){
        if (this.getRedVehicle().horizontal){
            if (this.goalx != this.getRedVehicle().x)
                this.goalx = this.getRedVehicle().x;
        }
        else{
            if (this.goaly != this.getRedVehicle().y)
                this.goaly = this.getRedVehicle().y;
        }
    }

    public boolean valideVehicle(Vehicle newvehicle){
        int x = newvehicle.x;
        int y = newvehicle.y;
        int size = newvehicle.size;
        
        if (x > gridSize-size || y > gridSize-size || x<0 || y<0){
            return false;
        }
        return !crashVehicles(x, y, size);
    }


    public Vehicle getRedVehicle(){
            return vehicles.get(0);
    }

    public boolean canMoveDown(Vehicle vehicle) {
            if(vehicle.x + vehicle.size < gridSize && !crashVehicles(vehicle.x+vehicle.size, vehicle.y))
                    return true;
            return false;
    }

    public boolean canMoveUp(Vehicle vehicle) {
            if(vehicle.x > 0 && !crashVehicles(vehicle.x-1, vehicle.y))
                    return true;
            return false;
    }

    public boolean canMoveRight(Vehicle vehicle) {
            if(vehicle.y + vehicle.size < gridSize && !crashVehicles(vehicle.x, vehicle.y+vehicle.size))
                    return true;
            return false;
    }
	
    public boolean canMoveLeft(Vehicle vehicle) {
            if(vehicle.y > 0 && !crashVehicles(vehicle.x, vehicle.y-1))
                    return true;
            return false;
    }
	
    public boolean crashVehicles(int x, int y, int size) {
        for(Vehicle vehicle : vehicles){
            if(vehicle.isHorizontal()){
                if(x == vehicle.x && y >= vehicle.y && y < vehicle.y + vehicle.size){
                        crashedVehicle = vehicle;
                        return true;
                }
                else if(x >= vehicle.x + size && y >= vehicle.y && y < vehicle.y + vehicle.size){
                    crashedVehicle = vehicle;
                    return true;
                }
            }
            else if(vehicle.isVertical()){
                if(y == vehicle.y && x >= vehicle.x && x < vehicle.x + vehicle.size){
                    crashedVehicle = vehicle;
                    return true;
                }
                else if(y > vehicle.y - size && y < vehicle.y + size && x >= vehicle.x && x < vehicle.x + vehicle.size){
                    crashedVehicle = vehicle;
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean crashVehicles(int x, int y) {
        for(Vehicle vehicle : vehicles){
            if(vehicle.isHorizontal()){
                if(x == vehicle.x && y >= vehicle.y && y < vehicle.y + vehicle.size){
                        crashedVehicle = vehicle;
                        return true;
                }
            }
            else if(vehicle.isVertical()){
                if(y == vehicle.y && x >= vehicle.x && x < vehicle.x + vehicle.size){
                    crashedVehicle = vehicle;
                    return true;
                }
            }
        }
        return false;
    }
}
