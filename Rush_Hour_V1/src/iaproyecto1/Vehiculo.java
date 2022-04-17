/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iaproyecto1;

import java.awt.Color;

/**
 *
 * @author Daniel
 */
class Vehiculo {
    public int id;
    public Boolean principal;
    public Boolean esCarro;
    public Color color;
    public String posicionX; //1,0
    public String posicionY; //0,0
    public String nombre;
    public Boolean horizontal;
    public int x;
    public int y;
    
    Vehiculo(int id,Boolean principal,Boolean esCarro,Color color,String posicionX,String posicionY,String nombre,Boolean horizontal,int x, int y){
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
    }
}
