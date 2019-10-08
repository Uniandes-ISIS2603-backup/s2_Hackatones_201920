/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author s.estupinan
 */
public class LenguajeDetailDTO extends LenguajeDTO implements Serializable{
    private List<ParticipanteDTO> participantes;
        
    public LenguajeDetailDTO()
    {
        super();
    }
    
    public LenguajeDetailDTO(LenguajeEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            participantes = new ArrayList<>();
            for (ParticipanteEntity particip : entity.getParticipantes()) {
                participantes.add(new ParticipanteDTO(particip));
            }
        }
    }
    
    @Override
    public LenguajeEntity toEntity()
    {
        LenguajeEntity entity = super.toEntity();
        if(participantes != null)
        {
            List<ParticipanteEntity> particip = new ArrayList<>();
            for (ParticipanteDTO p : participantes) {
                particip.add(p.toEntity());
            }
            entity.setParticipantes(particip);
        }
        return entity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
