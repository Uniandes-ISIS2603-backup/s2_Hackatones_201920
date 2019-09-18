/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author ne.cardenas
 */
public class ParticipanteDTO implements Serializable {
    
    private Long id;
    
    private boolean inscrito;
    
    public ParticipanteDTO(ParticipanteEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.inscrito = entity.isInscrito();
        }    
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
    
    public boolean isInscrito() {
        return inscrito;
    }
    
    public void setInscrito(boolean insc) {
        this.inscrito = insc;
    }
    
    public ParticipanteEntity toEntity() {
        ParticipanteEntity entity = new ParticipanteEntity();
        entity.setId(this.id);
        return entity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
