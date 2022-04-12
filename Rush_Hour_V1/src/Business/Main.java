/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Memento.Caretaker;
import Memento.Memento;

/**
 *
 * @author johel
 */
public class Main {
        public static void main (String args[]){
        Caretaker car = new Caretaker();
        Map map = new Map(6, 6);
        Map map2 = new Map(6, 6);
        Memento me1 = new Memento(map);
        car.addMemento(me1);
        Memento me2 = new Memento(map);
        car.addMemento(me2);
        
    }
}
