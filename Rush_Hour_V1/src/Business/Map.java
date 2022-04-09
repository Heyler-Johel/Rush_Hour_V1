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

public class Map{
    public int anchuraMap;
    public int alturaMap;
    private ArrayList<ArrayList<Node>> map;
    private int localizacionInicialX = 0;
    private int localizacionInicialY = 0;
    private int localizacionFinalX = 0;
    private int localizacionFinalY = 0;
    private int[][] mapDeObstaculos;
        
    public Map(int ancho, int alto/*, int[][] obstacleMap*/) {
        this.anchuraMap = ancho;
        this.alturaMap = alto;
        //this.mapDeObstaculos = obstacleMap;
        this.mapDeObstaculos = new int[anchuraMap + 1][alturaMap + 1];
        crearMap();
        establecerBordesMap();
    }
        
    private void crearMap() {
        Node node;
        map = new ArrayList<ArrayList<Node>>();
        for (int x=0; x<anchuraMap; x++) {
            map.add(new ArrayList<Node>());
            for (int y=0; y<alturaMap; y++) {
                node = new Node(x,y);
                if (mapDeObstaculos[x][y] == 1)
                    node.setObstical(true);
                map.get(x).add(node);
            }
        }
    }


    private void establecerBordesMap() {
        for ( int x = 0; x < anchuraMap; x++ ) {
            for ( int y = 0; y < alturaMap; y++ ) {
                Node node = map.get(x).get(y);
                if (!(y==0))
                    node.setNorth(map.get(x).get(y-1));
                if (!(y==0) && !(x==anchuraMap-1))
                    node.setNorthEast(map.get(x+1).get(y-1));
                if (!(x==anchuraMap-1))
                    node.setEast(map.get(x+1).get(y));
                if (!(x==anchuraMap-1) && !(y==alturaMap-1))
                    node.setSouthEast(map.get(x+1).get(y+1));
                if (!(y==alturaMap-1))
                    node.setSouth(map.get(x).get(y+1));
                if (!(x==0) && !(y==alturaMap-1))
                    node.setSouthWest(map.get(x-1).get(y+1));
                if (!(x==0))
                    node.setWest(map.get(x-1).get(y));
                if (!(x==0) && !(y==0))
                    node.setNorthWest(map.get(x-1).get(y-1));
            }
        }
    }
        
        

    public ArrayList<ArrayList<Node>> getNodes() {
        return map;
    }
    public void setObstaculo(int x, int y, boolean isObstical) {
        map.get(x).get(y).setObstical(isObstical);
    }

    public Node getNode(int x, int y) {
        return map.get(x).get(y);
    }

    public void setLocalizacionInicial(int x, int y) {
        map.get(localizacionInicialX).get(localizacionInicialY).setStart(false);
        map.get(x).get(y).setStart(true);
        localizacionInicialX = x;
        localizacionInicialY = y;
    }

    public void setLocalizacionDeLaMeta(int x, int y) {
        map.get(localizacionFinalX).get(localizacionFinalY).setGoal(false);
        map.get(x).get(y).setGoal(true);
        localizacionFinalX = x;
        localizacionFinalY = y;
    }

    public int getLocalizacionInicialX() {
        return localizacionInicialX;
    }

    public int getLocalizacionInicialY() {
        return localizacionInicialY;
    }

    public Node getNodeInicial() {
        return map.get(localizacionInicialX).get(localizacionInicialY);
    }

    public int getLocalizacionFinalX() {
        return localizacionFinalX;
    }

    public int getLocalizacionFinalY() {
        return localizacionFinalY;
    }

    public Node getLocalizacionFinal() {
        return map.get(localizacionFinalX).get(localizacionFinalY);
    }
    public ArrayList<ArrayList<Node>> getMap(){
        return map;
    }

    public float getDistanceBetween(Node node1, Node node2) {
        //if the nodes are on top or next to each other, return 1
        if (node1.getX() == node2.getX() || node1.getY() == node2.getY()){
            return 1*(alturaMap+anchuraMap);
        } else { //if they are diagonal to each other return diagonal distance: sqrt(1^2+1^2)
            return (float) 1.7*(alturaMap+anchuraMap);
        }
    }

    public int getAnchuraMapLogico() {
        return anchuraMap;
    }
    public int getAlturaMapLogico() {
        return alturaMap;
    }
    public void clear() {
        localizacionInicialX = 0;
        localizacionInicialY = 0;
        localizacionFinalX = 0;
        localizacionFinalY = 0;
        crearMap();
        establecerBordesMap ();
    }
    /**
     * @param mapDeObstaculos the mapDeObstaculos to set
     */
    public void setMapDeObstaculos(int[][] mapDeObstaculos) {
        this.mapDeObstaculos = mapDeObstaculos;
        crearMap();
        establecerBordesMap();
    }

}
