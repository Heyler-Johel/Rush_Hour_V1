/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iaproyecto1;

import java.util.ArrayList;

public class Heuristic {
    ArrayList<Integer> visited;

    public Heuristic() {
    }
    public int getValue(State state) {
        Vehicle redvehicle = state.getMap().getRedVehicle();
        int gridSize = state.getMap().gridSize;
        int numberOfMovements = 0;
        if (redvehicle.isHorizontal()){
            if (redvehicle.y < state.getMap().goaly){
                for (int i = redvehicle.y+2; i < gridSize; i++) {
                    int x = redvehicle.x;
                    int y = i;
                    if(state.getMap().crashVehicles(x, y)){
                        Vehicle vehicle = state.getMap().crashedVehicle; 
                        if(!state.getMap().canMoveDown(vehicle) && !state.getMap().canMoveUp(vehicle))
                            numberOfMovements += 2;
                        else
                            numberOfMovements ++;
                    }
                }
            }
            else {
                for (int i = redvehicle.y+2; i > -1; i--) {
                    int x = redvehicle.x;
                    int y = i;
                    if(state.getMap().crashVehicles(x, y)){
                        Vehicle vehicle = state.getMap().crashedVehicle; 
                        if(!state.getMap().canMoveDown(vehicle) && !state.getMap().canMoveUp(vehicle))
                            numberOfMovements += 2;
                        else
                            numberOfMovements ++;
                    }
                }
            }
        }
        else {
            if (redvehicle.x < state.getMap().goalx){
                for (int i = redvehicle.x+2; i < gridSize; i++) {
                    int y = redvehicle.y;
                    int x = i;
                    if(state.getMap().crashVehicles(x, y)){
                        Vehicle vehicle = state.getMap().crashedVehicle; 
                        if(!state.getMap().canMoveLeft(vehicle) && !state.getMap().canMoveRight(vehicle))
                            numberOfMovements += 2;
                        else
                            numberOfMovements ++;
                    }
                }
            }
            else{
                for (int i = redvehicle.x+2; i > -1; i--) {
                    int y = redvehicle.y;
                    int x = i;
                    if(state.getMap().crashVehicles(x, y)){
                        Vehicle vehicle = state.getMap().crashedVehicle; 
                        if(!state.getMap().canMoveLeft(vehicle) && !state.getMap().canMoveRight(vehicle))
                            numberOfMovements += 2;
                        else
                            numberOfMovements ++;
                    }
                }
            }
        }
        return numberOfMovements;
    }
}
