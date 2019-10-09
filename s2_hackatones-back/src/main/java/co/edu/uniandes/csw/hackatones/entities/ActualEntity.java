/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author a.pedraza
 * 
 */
@Entity
public class ActualEntity  extends HackatonEntity implements Serializable{
      
    private String reglas;
    private String restricciones;
    
//    
//    @PodamExclude
//    @OneToMany(mappedBy = "hackaton")
//    private ParticipanteEntity participantes;
    
   /**
    @PodamExclude
    @OneToMany(mappedBy = "hackaton")
    private EquipoEntity equipos;
  */  
    
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

    /**
//     * @return the participantes
//     */
//    public ParticipanteEntity getParticipantes() {
//        return participantes;
//    }
//
//    /**
//     * @param participantes the participantes to set
//     */
//    public void setParticipantes(ParticipanteEntity participantes) {
//        this.participantes = participantes;
//    }

    /**
     * @return the equipos
     */
 //   public EquipoEntity getEquipos() {
  //      return equipos;
    //}

    /**
     * @param equipos the equipos to set
     */
  //  public void setEquipos(EquipoEntity equipos) {
//        this.equipos = equipos;
  //  }
}
