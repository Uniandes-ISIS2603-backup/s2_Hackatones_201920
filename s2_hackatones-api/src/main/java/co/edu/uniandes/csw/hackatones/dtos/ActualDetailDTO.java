/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @actual a.pedraza
 */
public class ActualDetailDTO extends ActualDTO implements Serializable {
    
    //relacion 1 o muchos participantes
    private List<ParticipanteDTO> participantes;
    
    //relacion 1 o muchos equipos
    private List<EquipoDTO> equipos;
    
    public ActualDetailDTO(){
        super();
    }
    
    public ActualDetailDTO(ActualEntity actualEntity) {
        super(actualEntity);
        if (actualEntity != null) {
            participantes = new ArrayList<>();
//            for (ParticipanteEntity entityParticipantes : actualEntity.getParticipantes()) {
//                participantes.add(new ParticipanteDTO(entityParticipantes));
//            }
//            equipos = new ArrayList();
//            for (EquipoEntity entityEquipo : actualEntity.getEquipos()) {
//                equipos.add(new EquipoDTO(entityEquipo));
//            }
        }
    }
    
    @Override
    public ActualEntity toEntity() {
        ActualEntity actualEntity = super.toEntity();
//        if (participantes != null) {
//            List<ParticipanteEntity> participantesEntity = new ArrayList<>();
//            for (ParticipanteDTO dtoParticipante : participantes) {
//                participantesEntity.add(dtoParticipante.toEntity());
//            }
//            actualEntity.setParticipantes(participantesEntity);
//        }
//        if (equipos != null) {
//            List<EquipoEntity> equiposEntity = new ArrayList<>();
//            for (EquipoDTO dtoEquipo : equipos) {
//                equiposEntity.add(dtoEquipo.toEntity());
//            }
//            actualEntity.setEquipos(equiposEntity);
//        }
        return actualEntity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the participantes
     */
    public List<ParticipanteDTO> getParticipantes() {
        return participantes;
    }

    /**
     * @param participantes the participantes to set
     */
    public void setParticipantes(List<ParticipanteDTO> participantes) {
        this.participantes = participantes;
    }

    /**
     * @return the equipos
     */
    public List<EquipoDTO> getEquipos() {
        return equipos;
    }

    /**
     * @param equipos the equipos to set
     */
    public void setEquipos(List<EquipoDTO> equipos) {
        this.equipos = equipos;
    }
    
    
}
