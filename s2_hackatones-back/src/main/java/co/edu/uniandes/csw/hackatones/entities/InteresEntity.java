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
    
    private String imagen;
    
    
    @PodamExclude
    @ManyToMany
    private List<UsuarioEntity> participantes = new ArrayList<>();
    
 
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
     * @return the descripcion
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
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

   
    public List<UsuarioEntity> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<UsuarioEntity> participantes) {
        this.participantes = participantes;
    }
    
    
    
    
}
