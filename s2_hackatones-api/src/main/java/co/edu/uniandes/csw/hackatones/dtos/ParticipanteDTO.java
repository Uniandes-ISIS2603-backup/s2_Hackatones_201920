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
    
    /**
     * id
     */
    private Long id;
    /**
     * inscrito
     */
    private boolean inscrito;
    /**
     * hackaton
     */
    private ActualDTO hackaton;
    /**
     * equipo
     */
    private EquipoDTO equipo;
    
    /**
     * constructor vacio
     */
    public ParticipanteDTO () {
    }
    
    /**
     * crea un dto de participante
     * @param entity la entidad a crear
     */
    public ParticipanteDTO(ParticipanteEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.inscrito = entity.isInscrito();
            if (entity.getHackaton() != null)
                this.hackaton = new ActualDTO(entity.getHackaton());
            else
                this.hackaton = null;
            if (entity.getEquipo() != null )
                this.equipo = new EquipoDTO(entity.getEquipo());
            else
                this.equipo = null;
            
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
    
    /**
     * 
     * @return si el participante esta inscrito
     */
    public boolean isInscrito() {
        return inscrito;
    }
    
    /**
     * 
     * @param insc el estado de inscripcion a fijar
     */
    public void setInscrito(boolean insc) {
        this.inscrito = insc;
    }

    /**
     * 
     * @return la hackaton del participante
     */
    public ActualDTO getHackaton() {
        return hackaton;
    }

    /**
     * 
     * @param hackaton la hackaton a fijar
     */
    public void setHackaton(ActualDTO hackaton) {
        this.hackaton = hackaton;
    }

    /**
     * devuelve el equipo del participante
     * @return el equipo
     */
    public EquipoDTO getEquipo() {
        return equipo;
    }

    /**
     * 
     * @param equipo el equipo a fijar
     */
    public void setEquipo(EquipoDTO equipo) {
        this.equipo = equipo;
    }
    
    /**
     * convierte a entidad
     * @return la entidad
     */
    public ParticipanteEntity toEntity() {
        ParticipanteEntity entity = new ParticipanteEntity();
        entity.setId(this.id);
        entity.setInscrito(this.inscrito);
        return entity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
