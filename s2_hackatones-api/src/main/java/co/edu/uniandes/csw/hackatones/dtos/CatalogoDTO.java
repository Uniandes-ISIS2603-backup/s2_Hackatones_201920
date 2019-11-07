/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.CatalogoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author ne.cardenas
 */
public class CatalogoDTO implements Serializable {
    
    /**
     * id del catalogo
     */
    private Long id;
    
    /**
     * constructor vacio
     */
    public CatalogoDTO () {
    }
    
    /**
     * crea un catalogo
     * @param entity la entidad a crear
     */
    public CatalogoDTO(CatalogoEntity entity) {
        if (entity != null)
            this.id = entity.getId();
    }   
    
    /**
     * pasa a entidad
     * @return entidad de catalogo
     */
    public CatalogoEntity toEntity() {
        CatalogoEntity entity = new CatalogoEntity();
        entity.setId(this.id);
        return entity;
    }

    @Override
    public String toString() {
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
}
