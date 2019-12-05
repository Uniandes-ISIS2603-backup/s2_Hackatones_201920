/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
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
    private List<UsuarioDTO> participantes;
    private List<HackatonDTO> hackatones;
    
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
            for (UsuarioEntity particip : entity.getParticipantes()) {
                participantes.add(new UsuarioDTO(particip));
            }
            hackatones = new ArrayList<>();
            for (HackatonEntity hackaton : entity.getHackatones()) {
                hackatones.add(new HackatonDTO(hackaton));
            }
        }
    }
    
    @Override
    public LenguajeEntity toEntity()
    {
        LenguajeEntity entity = super.toEntity();
        if(getParticipantes() != null)
        {
            List<UsuarioEntity> particip = new ArrayList<>();
            for (UsuarioDTO p : getParticipantes()) {
                particip.add(p.toEntity());
            }
            entity.setParticipantes(particip);
        }
        if(getHackatones()!= null)
        {
            List<HackatonEntity> hackaton = new ArrayList<>();
            for (HackatonDTO p : getHackatones()) {
                hackaton.add(p.toEntity());
            }
            entity.setHackatones(hackaton);
        }
        return entity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the participantes
     */
    public List<UsuarioDTO> getParticipantes() {
        return participantes;
    }

    /**
     * @param participantes the participantes to set
     */
    public void setParticipantes(List<UsuarioDTO> participantes) {
        this.participantes = participantes;
    }

    /**
     * @return the hackatones
     */
    public List<HackatonDTO> getHackatones() {
        return hackatones;
    }

    /**
     * @param hackatones the hackatones to set
     */
    public void setHackatones(List<HackatonDTO> hackatones) {
        this.hackatones = hackatones;
    }
    
    
}
