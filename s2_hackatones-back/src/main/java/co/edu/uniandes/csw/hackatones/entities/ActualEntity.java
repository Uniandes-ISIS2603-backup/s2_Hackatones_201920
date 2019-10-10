/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import java.util.List;
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
    
    
    @PodamExclude
    @OneToMany(mappedBy = "actual")
    private List<ParticipanteEntity> participantes;
    
   
    @PodamExclude
    @OneToMany(mappedBy = "hackaton")
    private List<EquipoEntity> equipos;
      
    
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
     * @return the participantes
     */
    public List<ParticipanteEntity> getParticipantes() {
        return participantes;
    }

    /**
     * @param participantes the participantes to set
     */
    public void setParticipantes(List<ParticipanteEntity> participantes) {
        this.participantes = participantes;
    }

    /**
     * @return the equipos
     */
    public List<EquipoEntity> getEquipos() {
        return equipos;
    }

    /**
     * @param equipos the equipos to set
     */
    public void setEquipos(List<EquipoEntity> equipos) {
        this.equipos = equipos;
    }
}
