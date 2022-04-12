/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memento;

import Business.Map;
import java.util.ArrayList;

/**
 *
 * @author johel
 */
public class Caretaker {
    
    private final ArrayList<Memento> states = new ArrayList<>();
    
    public Caretaker (){}
    
    public void addMemento(Memento memento){
        states.add(memento);
    }
    
    public Memento getMemento(int index){
        return states.get(index);
    }
   
}
