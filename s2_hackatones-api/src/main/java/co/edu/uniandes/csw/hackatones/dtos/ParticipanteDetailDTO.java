/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author ne.cardenas
 */
public class ParticipanteDetailDTO extends ParticipanteDTO implements Serializable {
    
    /**
     * tecnologias
     */
    private List<TecnologiaDTO> tecnologias;   
    /**
     * intereses
     */
    private List<InteresDTO> intereses;  
    /**
     * lenguajes
     */
    private List<LenguajeDTO> lenguajes;
    
    /**
     * constructor vacio
     */
    public ParticipanteDetailDTO () {
    }
    
    /**
     * crea un detalle dto de participante
     * @param entity 
     */
    public ParticipanteDetailDTO (ParticipanteEntity entity) {
        super(entity);
        if (entity != null) {
            if (entity.getTecnologias() != null) {
                tecnologias = new ArrayList<>();
                for (TecnologiaEntity entityT : entity.getTecnologias()) {
                    tecnologias.add(new TecnologiaDTO(entityT));
                }
            }
            if (entity.getIntereses() != null) {
                intereses = new ArrayList<>();
                for (InteresEntity entityI : entity.getIntereses())
                    intereses.add(new InteresDTO(entityI));
            }
            if (entity.getLenguajes() != null) {
                lenguajes = new ArrayList<>();
                for (LenguajeEntity entityL : entity.getLenguajes())
                    lenguajes.add(new LenguajeDTO(entityL));
            }
        }
    }
    
    @Override
    public ParticipanteEntity toEntity() {
        ParticipanteEntity entity = super.toEntity();
        if (tecnologias != null) {
            List<TecnologiaEntity> tEntity = new ArrayList<>();
            for (TecnologiaDTO dtoTec : tecnologias) {
                tEntity.add(dtoTec.toEntity());
            }
            entity.setTecnologias(tEntity);
        }
        if (intereses != null) {
            List<InteresEntity> intEntity = new ArrayList<>();
            for (InteresDTO dtoInt : intereses) {
                intEntity.add(dtoInt.toEntity());
            }
            entity.setIntereses(intEntity);
        }
        if (lenguajes != null) {
            List<LenguajeEntity> lengEntity = new ArrayList<>();
            for (LenguajeDTO dtoLeng : lenguajes)
                lengEntity.add(dtoLeng.toEntity());
            entity.setLenguajes(lengEntity);
        }
        return entity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * devuelve las tecnologias
     * @return tecnologias
     */
    public List<TecnologiaDTO> getTecnologias() {
        return tecnologias;
    }

    /**
     * fija tecnologias
     * @param tecnologias las tecnologias a fijar
     */
    public void setTecnologias(List<TecnologiaDTO> tecnologias) {
        this.tecnologias = tecnologias;
    }

    /**
     * devuelve los intereses
     * @return intereses
     */
    public List<InteresDTO> getIntereses() {
        return intereses;
    }

    /**
     * fija los intereses
     * @param intereses los intereses a fijar
     */
    public void setIntereses(List<InteresDTO> intereses) {
        this.intereses = intereses;
    }

    /**
     * devuelve los lenguajes
     * @return lenguajes
     */
    public List<LenguajeDTO> getLenguajes() {
        return lenguajes;
    }

    /**
     * fija los lenguajes
     * @param lenguajes los lenguajes a fijar
     */
    public void setLenguajes(List<LenguajeDTO> lenguajes) {
        this.lenguajes = lenguajes;
    }
    
}
