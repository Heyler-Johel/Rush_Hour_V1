/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author johel
 */
public class Heuristic {
        /**
        * 
        * The heuristic tries to guess how far a given Node is from the goal Node.
        * The lower the cost, the more likely a Node will be searched next. 
        * 
        * @param startX The x coordinate of the tile being evaluated
        * @param startY The y coordinate of the tile being evaluated
        * @param goalX The x coordinate of the target location
        * @param goalY The y coordinate of the target location
        * @return The cost associated with the given tile
        */
        public float calcularDistanciaAMeta(int startX, int startY, int goalX, int goalY){                     
            //Distancia en el eje de las x
            float dx = goalX - startX;
            //Distancia en el eje de las y
            float dy = goalY - startY;
            //Calculamos la distancia utilizando el teorema de pitagoras
            return (float) Math.sqrt((dx*dx)+(dy*dy));
        }
}
