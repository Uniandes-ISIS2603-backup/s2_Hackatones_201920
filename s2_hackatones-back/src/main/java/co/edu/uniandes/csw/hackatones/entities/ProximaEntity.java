/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author a.pedraza
 */
@Entity
public class ProximaEntity extends HackatonEntity implements Serializable{
    
    private String reglas;
    
    private String restricciones;

    @PodamExclude
    @OneToMany
    private List<ParticipanteEntity> listaDeEspera;
            
    @PodamExclude
    @OneToMany
    private List<ParticipanteEntity> inscritos;
    
    
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
     * @return the listaDeEspera
     */
    public List<ParticipanteEntity> getListaDeEspera() {
        return listaDeEspera;
    }

    /**
     * @param listaDeEspera the listaDeEspera to set
     */
    public void setListaDeEspera(List<ParticipanteEntity> listaDeEspera) {
        this.listaDeEspera = listaDeEspera;
    }

    /**
     * @return the inscritos
     */
    public List<ParticipanteEntity> getInscritos() {
        return inscritos;
    }

    /**
     * @param inscritos the inscritos to set
     */
    public void setInscritos(List<ParticipanteEntity> inscritos) {
        this.inscritos = inscritos;
    }
}
