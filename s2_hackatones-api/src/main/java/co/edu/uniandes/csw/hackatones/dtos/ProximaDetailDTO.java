/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author a.pedraza
 */
public class ProximaDetailDTO extends ProximaDTO implements Serializable{

    //relacion 0 o muchos participantes
    private List<ParticipanteDTO> inscirtos;

    //relacion 0 o muchos participantes
    private List<ParticipanteDTO> listaDeEspera;

    public ProximaDetailDTO() {
        super();
    }
    
    public ProximaDetailDTO(ProximaEntity proximaEntity) {
        super(proximaEntity);
        if (proximaEntity != null) {
            inscirtos = new ArrayList<>();
            for (ParticipanteEntity entityParticipantes : proximaEntity.getInscritos()) {
                inscirtos.add(new ParticipanteDTO(entityParticipantes));
            }
            
            listaDeEspera = new ArrayList<>();
            for (ParticipanteEntity entityParticipantes : proximaEntity.getListaDeEspera()) {
                listaDeEspera.add(new ParticipanteDTO(entityParticipantes));
            }
        }
    }
    
     @Override
    public ProximaEntity toEntity() {
        ProximaEntity proximaEntity = super.toEntity();
        if (getInscirtos() != null) {
            List<ParticipanteEntity> participantesEntity = new ArrayList<>();
            for (ParticipanteDTO dtoParticipante : getInscirtos()) {
                participantesEntity.add(dtoParticipante.toEntity());
            }
            proximaEntity.setInscritos(participantesEntity);
        }
        if (getListaDeEspera() != null) {
            List<ParticipanteEntity> participantesEntity = new ArrayList<>();
            for (ParticipanteDTO dtoParticipante : getListaDeEspera()) {
                participantesEntity.add(dtoParticipante.toEntity());
            }
            proximaEntity.setListaDeEspera(participantesEntity);
        }
        return proximaEntity;
    }
    
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the inscirtos
     */
    public List<ParticipanteDTO> getInscirtos() {
        return inscirtos;
    }

    /**
     * @param inscirtos the inscirtos to set
     */
    public void setInscirtos(List<ParticipanteDTO> inscirtos) {
        this.inscirtos = inscirtos;
    }

    /**
     * @return the listaDeEspera
     */
    public List<ParticipanteDTO> getListaDeEspera() {
        return listaDeEspera;
    }

    /**
     * @param listaDeEspera the listaDeEspera to set
     */
    public void setListaDeEspera(List<ParticipanteDTO> listaDeEspera) {
        this.listaDeEspera = listaDeEspera;
    }
}
