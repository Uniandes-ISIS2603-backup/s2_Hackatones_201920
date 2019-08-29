/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author a.pedraza
 * 
 */
@Entity
public class ActualEntity  extends BaseEntity implements Serializable{
      
    private String reglas;
    
    private String restricciones;

    /**
     * @return the reglas
     */
    public String getReglas() {
        return reglas;
    }

    /**
     * @param reglas the reglas to set
     */
    public void setReglas(String reglas) {
        this.reglas = reglas;
    }

    /**
     * @return the restricciones
     */
    public String getRestricciones() {
        return restricciones;
    }

    /**
     * @param restricciones the restricciones to set
     */
    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }
}
