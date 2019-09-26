/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author s.estupinan
 */
class PatrocinadorDTO implements Serializable{
    
    private Long id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String infoAdicional;
    
    public PatrocinadorDTO()
    {
        
    }
    
    public PatrocinadorDTO(PatrocinadorEntity patrocinadorEntity)
    {
        if(patrocinadorEntity != null)
        {
            this.id = patrocinadorEntity.getId();
            this.descripcion = patrocinadorEntity.getDescripcion();
            this.infoAdicional = patrocinadorEntity.getInfoAdicional();
            this.nombre = patrocinadorEntity.getNombre();
            this.ubicacion = patrocinadorEntity.getUbicacion();
        }
    }
    
    public PatrocinadorEntity toEntity()
    {
        PatrocinadorEntity nuevo = new PatrocinadorEntity();
        nuevo.setId(this.getId());
        nuevo.setDescripcion(this.getDescripcion());
        nuevo.setInfoAdicional(this.getInfoAdicional());
        nuevo.setNombre(this.getNombre());
        nuevo.setUbicacion(this.getUbicacion());
        return nuevo;
    }
    
    
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the infoAdicional
     */
    public String getInfoAdicional() {
        return infoAdicional;
    }

    /**
     * @param infoAdicional the infoAdicional to set
     */
    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }
}
