/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import co.edu.uniandes.csw.hackatones.entities.CatalogoEntity;
import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
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
    private List<ProximaDTO> eventosProximos;
    
    /**
     * eventos en curso
     */
    private List<ActualDTO> eventosEnCurso;
    
    /**
     * constructor vacío
     */
    public CatalogoDetailDTO() {
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
//            if (entity.getProximos()!= null) {
//                eventosProximos = new ArrayList<>();
//                for (ProximaEntity entityP : entity.getProximos()) {
//                    eventosProximos.add(new ProximaDTO(entityP));
//                }
//            }
//            if (entity.getActuales()!= null) {
//                eventosEnCurso = new ArrayList<>();
//                for (ActualEntity entityA : entity.getActuales()) {
//                    eventosEnCurso.add(new ActualDTO(entityA));
//                }
//            }
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
            List<ProximaEntity> proxEntity = new ArrayList<>();
            for (ProximaDTO dtoProx : getEventosProximos()) {
                proxEntity.add(dtoProx.toEntity());
            }
//            editorialEntity.setProximos(proxEntity);
        }
        if (getEventosEnCurso() != null) {
            List<ActualEntity> actEntity = new ArrayList<>();
            for (ActualDTO dtoAct : getEventosEnCurso()) {
                actEntity.add(dtoAct.toEntity());
            }
//            editorialEntity.setActuales(actEntity);
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
    public List<ProximaDTO> getEventosProximos() {
        return eventosProximos;
    }

    /**
     * @param eventosProximos the eventosProximos to set
     */
    public void setEventosProximos(List<ProximaDTO> eventosProximos) {
        this.eventosProximos = eventosProximos;
    }

    /**
     * @return the eventosEnCurso
     */
    public List<ActualDTO> getEventosEnCurso() {
        return eventosEnCurso;
    }

    /**
     * @param eventosEnCurso the eventosEnCurso to set
     */
    public void setEventosEnCurso(List<ActualDTO> eventosEnCurso) {
        this.eventosEnCurso = eventosEnCurso;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
