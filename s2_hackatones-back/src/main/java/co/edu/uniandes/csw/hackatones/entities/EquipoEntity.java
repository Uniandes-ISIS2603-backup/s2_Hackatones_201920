/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ja.torresl
 */
@Entity
public class EquipoEntity extends BaseEntity implements Serializable{
    private String nombre;
  
//    @PodamExclude
//    @ManyToOne
//    private ActualEntity hackaton;
    
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the hackaton
     */
   // public ActualEntity getHackaton() {
    //    return hackaton;
    //}

    /**
     * @param hackaton the hackaton to set
     */
//    public void setHackaton(ActualEntity hackaton) {
//        this.hackaton = hackaton;
//    }
}
