/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iaproyecto1;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Daniel
 */
public class Vehiculo2 {

    public int id;
    public Boolean principal;
    public Boolean esCarro;
    public String color;
    public int posicionX;
    public int posicionY;

    // Getter
    public int getId() {
        return id;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public Boolean getPrincipal() {
        return principal;
    }
    // Setter

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public Boolean getEsCarro() {
        return esCarro;
    }
    // Setter

    public void setEsCarro(Boolean esCarro) {
        this.esCarro = esCarro;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void posicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int posicionX() {
        return posicionX;
    }
    
        public void posicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int posicionY() {
        return posicionY;
    }
}
