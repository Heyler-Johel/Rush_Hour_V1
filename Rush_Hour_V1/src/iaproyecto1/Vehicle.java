/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iaproyecto1;

import java.awt.Color;

public class Vehicle implements IPrototype<Vehicle> {

    public int x, y, size, id;
    public Boolean horizontal, principal, esCarro;
    public Color color;
    public String posicionX, posicionY, nombre;
	
    public Vehicle(int x, int y, boolean horizontal, int size) {
        super();

        this.x = x;
        this.y = y;
        this.size = size;
        this.horizontal = horizontal;
        //this.color = color;
    }
        
    public Vehicle(int id,Boolean principal,Boolean esCarro,Color color,String posicionX,String posicionY,String nombre,Boolean horizontal,int x, int y){
        this.id=id;
        this.principal=principal;
        this.esCarro=esCarro;
        this.color=color;
        this.posicionX=posicionX;
        this.posicionY=posicionY;
        this.nombre=nombre;
        this.horizontal=horizontal;
        this.x=x;
        this.y=y;
        if(esCarro)
            this.size = 2;
        else
            this.size = 3;
    }
	
    public void moveDown() {
            this.x ++;
    }

    public void moveUp() {
            this.x --;
    }

    public void moveRight() {
            this.y ++;
    }

    public void moveLeft() {
            this.y --;
    }

    public boolean isHorizontal() {
            return horizontal;
    }

    public boolean isVertical() {
            return !horizontal;
    }
	
    @Override
    public Vehicle clone(){
        return new Vehicle(this.id,this.principal,this.esCarro,this.color,
                           this.posicionX,this.posicionY,this.nombre,
                           this.horizontal,this.x, this.y);
    }

    @Override
    public Vehicle deepClone() {
        return clone();
    }
}

