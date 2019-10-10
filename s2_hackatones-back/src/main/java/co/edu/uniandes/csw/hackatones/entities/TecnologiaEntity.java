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
 * @author ja.torresl
 */
@Entity
public class TecnologiaEntity extends BaseEntity implements Serializable{
  
    private String nombre;

     
    @PodamExclude
    @ManyToMany(mappedBy = "tecnologias")
    private List<ParticipanteEntity> participantes = new ArrayList<>();
    
 
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

    
    public List<ParticipanteEntity> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<ParticipanteEntity> participantes) {
        this.participantes = participantes;
    }
    
}