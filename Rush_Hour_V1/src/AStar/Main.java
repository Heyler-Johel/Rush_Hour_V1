/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AStar;

import static AStar.AStar.searchAStar;
import java.util.LinkedList;

/**
 *
 * @author johel
 */
public class Main {
    
    private static Map readInput() {
        LinkedList<Vehicle> vehicles = new LinkedList<>();
        int size = 6;
//        char[][] grid = 
//            { {'#', '#', '#', '.', '.', '@'},
//              {'.', '.', '@', '.', '.', '@'},
//              {'=', '=', '@', '.', '.', '@'},
//              {'+', '.', '@', '.', '*', '*'},
//              {'+', '.', '.', '.', '+', '.'},
//              {'#', '#', '#', '.', '+', '.'} };
        char[][] grid = 
            { {'.', '˄', '.', '.', '.', '˄'},
              {'.', '˅', '<', '-', '>', '˅'},
              {'<', '>', '˄', '.', '.', '˄'},
              {'.', '.', '˅', '.', '.', '|'},
              {'˄', '<', '>', '.', '.', '˅'},
              {'˅', '.', '.', '.', '<', '>'} };
        
        int i = 0, j = 0;
        while (i < grid.length && j < grid.length){
            int sizev = 1;
            switch (grid[i][j]) {
                case '<':
                    while (grid[i][j] != '>'){
                        sizev++;
                        j++;
                    }
                    int jOrigin = j-(sizev-1);
                    if (i == 2 && jOrigin == 0)
                        vehicles.addFirst(new Vehicle(i, jOrigin, true, 2, "Rojo"));
                    else
                        vehicles.add(new Vehicle(i, jOrigin, true, sizev, "Verde"));
                    j++;
                    break;
                case '˄':
                    while (grid[i][j] != '˅'){
                        sizev++;
                        i++;
                    }
                    i = i-(sizev-1);
                    vehicles.add(new Vehicle(i, j, false, sizev, "Azul")); 
                    j++;
                    break;
                default:
                    j++;
                    break;
            }
            if (j==grid.length){
                i++;
                j = 0;
                if (i == grid.length)
                    j = grid.length;
            }
        };
        
       return new Map(size, vehicles);
    }
        	private static void print(LinkedList<State> path) {
		System.out.println("Number of optimal movements = " + (path.size()-1) + "\n");
		int index = 0;
		for(State s : path){
			if(index == 0)
				System.out.println("Initial state:");
			else
				System.out.println("Step " + index + ":");
			s.print();
			index ++;
		}
	}
                
    	private static void run() {
		
		
		Heuristic heuristic = new Heuristic();
		Map map = readInput();
		long startTime = System.currentTimeMillis();
		LinkedList<State> path = searchAStar(map, heuristic);
		long endTime = System.currentTimeMillis();
		long timeTaken1 = endTime - startTime;
		
		
		System.out.println("Solution using Heuristic.");
		System.out.println("#########################");
		print(path);
		System.out.println("Time taken using heuristic : " + timeTaken1);
		System.out.println();
		
	}
	
	public static void main(String[] args) {
            run();
	}
}
