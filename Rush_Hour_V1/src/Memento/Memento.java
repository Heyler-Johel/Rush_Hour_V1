/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memento;

import Business.Map;

/**
 *
 * @author johel
 */
public class Memento {
    
    private Map state;
    
    public Memento(Map map){
        this.state = map;
    }
    
    public Map getSavedState(){
        return this.state;
    }
}
