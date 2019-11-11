/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.CatalogoEntity;
import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author ne.cardenas
 */
public class CatalogoDetailDTO extends CatalogoDTO implements Serializable {
    
    /**
     * patrocinadores
     */
    private List<PatrocinadorDTO> patrocinadores;
    
    /**
     * eventos próximos
     */
    private List<HackatonDTO> eventosProximos;
    
    /**
     * eventos en curso
     */
    private List<HackatonDTO> eventosEnCurso;
    
    /**
     * constructor vacío
     */
    public CatalogoDetailDTO() {
        super();
    }
    
    /**
     * crea un catalogo detail
     * @param entity la entidad a crear
     */
    public CatalogoDetailDTO(CatalogoEntity entity) {
        super(entity);
        if (entity != null) {
            if (entity.getPatrocinadores()!= null) {
                patrocinadores = new ArrayList<>();
                for (PatrocinadorEntity entityP : entity.getPatrocinadores()) {
                    patrocinadores.add(new PatrocinadorDTO(entityP));
                }
            }
            if (entity.getEventosProximos()!= null) {
                eventosProximos = new ArrayList<>();
                for (HackatonEntity entityP : entity.getEventosProximos()) {
                    eventosProximos.add(new HackatonDTO(entityP));
                }
            }
            if (entity.getEventosEnCurso()!= null) {
                eventosEnCurso = new ArrayList<>();
                for (HackatonEntity entityA : entity.getEventosEnCurso()) {
                    eventosEnCurso.add(new HackatonDTO(entityA));
                }
            }
        }
    }
    
    /**
     * pasa el dto a entidad
     * @return la entidad convertida
     */
    @Override
    public CatalogoEntity toEntity() {
        CatalogoEntity editorialEntity = super.toEntity();
        if (getPatrocinadores() != null) {
            List<PatrocinadorEntity> patEntity = new ArrayList<>();
            for (PatrocinadorDTO dtoPat : getPatrocinadores()) {
                patEntity.add(dtoPat.toEntity());
            }
            editorialEntity.setPatrocinadores(patEntity);
        }
        if (getEventosProximos() != null) {
            List<HackatonEntity> proxEntity = new ArrayList<>();
            for (HackatonDTO dtoProx : getEventosProximos()) {
                proxEntity.add(dtoProx.toEntity());
            }
            editorialEntity.setEventosProximos(proxEntity);
        }
        if (getEventosEnCurso() != null) {
            List<HackatonEntity> actEntity = new ArrayList<>();
            for (HackatonDTO dtoAct : getEventosEnCurso()) {
                actEntity.add(dtoAct.toEntity());
            }
            editorialEntity.setEventosEnCurso(actEntity);
        }
        return editorialEntity;
    }
    
    /**
     * @return the patrocinadores
     */
    public List<PatrocinadorDTO> getPatrocinadores() {
        return patrocinadores;
    }

    /**
     * @param patrocinadores the patrocinadores to set
     */
    public void setPatrocinadores(List<PatrocinadorDTO> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    /**
     * @return the eventosProximos
     */
    public List<HackatonDTO> getEventosProximos() {
        return eventosProximos;
    }

    /**
     * @param eventosProximos the eventosProximos to set
     */
    public void setEventosProximos(List<HackatonDTO> eventosProximos) {
        this.eventosProximos = eventosProximos;
    }

    /**
     * @return the eventosEnCurso
     */
    public List<HackatonDTO> getEventosEnCurso() {
        return eventosEnCurso;
    }

    /**
     * @param eventosEnCurso the eventosEnCurso to set
     */
    public void setEventosEnCurso(List<HackatonDTO> eventosEnCurso) {
        this.eventosEnCurso = eventosEnCurso;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
