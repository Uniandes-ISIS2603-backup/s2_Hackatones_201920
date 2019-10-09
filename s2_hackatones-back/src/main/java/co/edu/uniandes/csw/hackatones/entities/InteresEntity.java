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
public class InteresEntity extends BaseEntity implements Serializable{
    
    private String nombre;
    
    private String descripcion;
    
    /**
    @PodamExclude
    @ManyToMany(mappedBy = "intereses")
    private List<ParticipanteEntity> participantes = new ArrayList<>();
    */
 
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

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
    public List<ParticipanteEntity> getParticipantes() {
        return paticipantes;
    }

    public void setPaticipantes(List<ParticipanteEntity> participantes) {
        this.participantes = participantes;
    }
    */
    
    
    
}
