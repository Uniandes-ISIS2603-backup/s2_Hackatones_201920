/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import java.io.Serializable;

/**
 *
 * @author ja.torresl
 */
 public class InteresDTO implements  Serializable{
    
      
    private Long id;
     
    private String nombre;
    
    private String descripcion;

    public InteresDTO() {
        
    }

    
        /**
     * Crea un objeto InteresDTO a partir de un objeto InteresEntity.
     *
     * @param interesEntity Entidad InteresEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public InteresDTO(InteresEntity interesEntity) {
        if (interesEntity != null) {
            this.id = interesEntity.getId();
            this.nombre = interesEntity.getNombre();
            this.descripcion = interesEntity.getDescripcion();     
        }
    }

    /**
     * Convierte un objeto InteresDTO a InteresEntity.
     *
     * @return Nueva objeto InteresEntity.
     *
     */
    public InteresEntity toEntity() {
        InteresEntity interesEntity = new InteresEntity();
        interesEntity.setId(this.getId());
        interesEntity.setNombre(this.getNombre());
        interesEntity.setDescripcion(this.descripcion);
     
        return interesEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
