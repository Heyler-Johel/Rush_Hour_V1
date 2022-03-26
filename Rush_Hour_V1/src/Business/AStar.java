/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author johel
 */

public final class AStar {
    private final Map map;
    private final Heuristic heuristic;

    private ArrayList<Node> listaCerrada;
    private ListaNodesOrdenadas listaAbierta;
    private Road caminoMasCorto;

    public AStar(Map map, Heuristic heuristic) {
        this.map = map;
        this.heuristic = heuristic;

        listaCerrada = new ArrayList<>();
        listaAbierta = new ListaNodesOrdenadas();
    }

    public Road calcularRoadMasCorto(Coordinates coord_Inicio, Coordinates coord_Fin, int[][] obstacleMap ) {

        map.setLocalizacionInicial(coord_Inicio.getCoord_x(), coord_Inicio.getCoord_Y());
        map.setLocalizacionDeLaMeta(coord_Fin.getCoord_x(), coord_Fin.getCoord_Y());
        map.setMapDeObstaculos(obstacleMap);

        listaCerrada = new ArrayList<>();
        listaAbierta = new ListaNodesOrdenadas();

        if (map.getNode(coord_Fin.getCoord_x(), coord_Fin.getCoord_Y()).isObstacle()) {
            return null;
        }

        map.getNodeInicial().setDistanceFromStart(0);
        listaCerrada.clear();
        listaAbierta.clear();
        listaAbierta.add(map.getNodeInicial());

        while(listaAbierta.size() != 0) {
            Node actual = listaAbierta.getFirst();

            if(actual.getX() == map.getLocalizacionFinalX() && actual.getY() == map.getLocalizacionFinalY()) {
                    return reconstruirRoad(actual);
            }

            listaAbierta.remove(actual);
            listaCerrada.add(actual);

            actual.getNeighborList().forEach((vecino) -> {
                boolean esMejorVecino;
                if (!(listaCerrada.contains(vecino))) {
                    if (!vecino.isObstacle()) {
                        float distanciaDesdeElPrincipioAlVecino = (actual.getDistanceFromStart() + map.getDistanceBetween(actual, vecino));
                        if(!listaAbierta.contains(vecino)) {
                            listaAbierta.add(vecino);
                            esMejorVecino = true;

                        } else if(distanciaDesdeElPrincipioAlVecino < actual.getDistanceFromStart()) {
                            esMejorVecino = true;
                        } else {
                            esMejorVecino = false;
                        }
                        if (esMejorVecino) {
                            vecino.setPreviousNode(actual);
                            vecino.setDistanceFromStart(distanciaDesdeElPrincipioAlVecino);
                            vecino.setHeuristicDistanceFromGoal(heuristic.calcularDistanciaAMeta(vecino.getX(), vecino.getY(), map.getLocalizacionFinalX(), map.getLocalizacionFinalY()));
                        }
                    }
                }
            });
        }
        return null;
}


    public void pintarRoad() {
        Node nodo;
        for(int x=0; x<map.getAnchuraMapLogico(); x++) {
            if (x==0) {
                for (int i=0; i<=map.getAnchuraMapLogico(); i++)
                    System.out.print("-");
                System.out.println();   
            }
            System.out.print("|");

            for(int y=0; y<map.getAlturaMapLogico(); y++) {
                nodo = map.getNode(x, y);
                if (nodo.isObstacle()) {
                    System.out.print("O");
                } else if (nodo.isStart) {
                    System.out.print("I");
                } else if (nodo.isGoal) {
                    System.out.print("F");
                } else if (caminoMasCorto.contains(nodo.getX(), nodo.getY())) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
                if (y==map.getAlturaMapLogico())
                    System.out.print("_");
            }

            System.out.print("|");
            System.out.println();
        }
        for (int i=0; i<=map.getAnchuraMapLogico(); i++)
            System.out.print("-");
    }

    private Road reconstruirRoad(Node nodo) {
        Road camino = new Road();
        while(!(nodo.getPreviousNode() == null)) {
            camino.prependWayPoint(nodo);
            nodo = nodo.getPreviousNode();
        }
        this.caminoMasCorto = camino;
        return camino;
    }

    //clase anidada de la lista de nodos ordenada.
    private class ListaNodesOrdenadas {
        //Lista de nodos.
        private final ArrayList<Node> list = new ArrayList<>();

        public Node getFirst() {
             return list.get(0);
        }

        public void clear() {
            list.clear();
        }

        public void add(Node node) {
            list.add(node);
            //Collections.sort(list);
        }

        public void remove(Node n) {
            list.remove(n);
        }

        public int size() {
            return list.size();
        }
        // devuelve si esta ese nodo en la lista.
        public boolean contains(Node n) {
            return list.contains(n);
        }
     }
    
    public Map getMap(){
       return map;
    }
    
    public Road getRoadMasCorto(){
       return caminoMasCorto;
    }
}
