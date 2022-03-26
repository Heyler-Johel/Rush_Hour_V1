/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;

/**
 *
 * @author johel
 */
public class Road {
    private final ArrayList<Node> coordenadasRoad = new ArrayList<>();
        
    public Road() {
    }
    //devuelve el tamaño del camino
    public int getLength() {
        return coordenadasRoad.size();
    }

    public Node getCoordenadasRoad(int index) {
        return coordenadasRoad.get(index);
    }


    public int getX(int index) {
        return getCoordenadasRoad(index).getX();
    }


    public int getY(int index) {
        return getCoordenadasRoad(index).getY();
    }

   // este añade el nodo al final
    public void añadirCordenadas(Node n) {
        coordenadasRoad.add(n);
    }

   // este añade el nodo al principio
    public void prependWayPoint(Node n) {
        coordenadasRoad.add(0, n);
    }

   //comprueba si una posicion pertenece al camino
    public boolean contains(int x, int y) {
        for(Node node : coordenadasRoad) {
            if (node.getX() == x && node.getY() == y)
                return true;
        }
        return false;
    }
}
