/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
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
public class PatrocinadorDetailDTO extends PatrocinadorDTO implements Serializable{
    
    private List<HackatonDTO> hackatones;
    
    private UsuarioDTO responsable;
    
    public PatrocinadorDetailDTO()
    {
        super();
    }
    
    public PatrocinadorDetailDTO(PatrocinadorEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            hackatones = new ArrayList<>();
            for (HackatonEntity hackatone : entity.getHackatones()) {
                hackatones.add(new HackatonDTO(hackatone));
            }
            this.responsable = new UsuarioDTO(entity.getResponsable());
        }
    }
    
    @Override
    public PatrocinadorEntity toEntity()
    {
        PatrocinadorEntity entity = super.toEntity();
        if(responsable!= null)
        {
            entity.setResponsable(responsable.toEntity());
        }
        if(hackatones != null)
        {
            List<HackatonEntity> hack = new ArrayList<>();
            for (HackatonDTO hackatone : hackatones) {
                hack.add(hackatone.toEntity());
            }
            entity.setHackatones(hack);
        }
        return entity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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

    /**
     * @return the responsable
     */
    public UsuarioDTO getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(UsuarioDTO responsable) {
        this.responsable = responsable;
    }
}
