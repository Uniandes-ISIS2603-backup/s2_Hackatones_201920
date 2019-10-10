/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Santiago Estupinan
 */
@Entity
public class LenguajeEntity extends BaseEntity implements Serializable
{
    /**
     * EL nombre del lenguaje
     */
    private String name;
    
    /**
     * La lista de participantes que usan el lenguaje
     */
    @PodamExclude
    @ManyToMany
    private List<ParticipanteEntity> participantes = new ArrayList<>();
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
        
        
}
