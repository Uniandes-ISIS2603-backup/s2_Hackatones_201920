/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
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
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable{
    
    private List<HackatonDetailDTO> hackatones;
    
    private List<TecnologiaDetailDTO> tecnologias;
    
    private List<LenguajeDetailDTO> lenguajes;
    
    private List<InteresDetailDTO> intereses;
    
    public UsuarioDetailDTO()
    {
        super();
    }
    
    public UsuarioDetailDTO(UsuarioEntity entity)
    {
        super(entity);
        if(entity!= null){
            if (entity.getHackatones() != null) 
            {
                hackatones = new ArrayList<>();
                for (HackatonEntity entityHackaton : entity.getHackatones()) 
                {
                    hackatones.add(new HackatonDetailDTO(entityHackaton));
                }
            }
            if (entity.getTecnologias() != null) 
            {
                tecnologias = new ArrayList<>();
                for (TecnologiaEntity entityTecnologia : entity.getTecnologias()) 
                {
                    tecnologias.add(new TecnologiaDetailDTO(entityTecnologia));
                }
            }
            if (entity.getLenguajes() != null) 
            {
                lenguajes = new ArrayList<>();
                for (LenguajeEntity entityLenguaje : entity.getLenguajes()) 
                {
                    lenguajes.add(new LenguajeDetailDTO(entityLenguaje));
                }
            }
            if (entity.getIntereses() != null) 
            {
                intereses = new ArrayList<>();
                for (InteresEntity entityInteres : entity.getIntereses()) 
                {
                    intereses.add(new InteresDetailDTO(entityInteres));
                }
            }
        }
        
    }
    
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = super.toEntity();
        if (hackatones != null) {
            List<HackatonEntity> hackatonesEntity = new ArrayList<>();
            for (HackatonDetailDTO dtoHackaton : hackatones) {
                hackatonesEntity.add(dtoHackaton.toEntity());
            }
            entity.setHackatones(hackatonesEntity);
        }
        if (tecnologias != null) {
            List<TecnologiaEntity> tecnologiasEntity = new ArrayList<>();
            for (TecnologiaDetailDTO dtoTecnologia : tecnologias) {
                tecnologiasEntity.add(dtoTecnologia.toEntity());
            }
            entity.setTecnologias(tecnologiasEntity);
        }
        if (lenguajes != null) {
            List<LenguajeEntity> lenguajesEntity = new ArrayList<>();
            for (LenguajeDetailDTO dtoLenguaje : lenguajes) {
                lenguajesEntity.add(dtoLenguaje.toEntity());
            }
            entity.setLenguajes(lenguajesEntity);
        }
        if (intereses != null) {
            List<InteresEntity> interesesEntity = new ArrayList<>();
            for (InteresDetailDTO dtoInteres : intereses) {
                interesesEntity.add(dtoInteres.toEntity());
            }
            entity.setIntereses(interesesEntity);
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
    public List<HackatonDetailDTO> getHackatones() {
        return hackatones;
    }

    /**
     * @param hackatones the hackatones to set
     */
    public void setHackatones(List<HackatonDetailDTO> hackatones) {
        this.hackatones = hackatones;
    }

    /**
     * @return the tecnologias
     */
    public List<TecnologiaDetailDTO> getTecnologias() {
        return tecnologias;
    }

    /**
     * @param tecnologias the tecnologias to set
     */
    public void setTecnologias(List<TecnologiaDetailDTO> tecnologias) {
        this.tecnologias = tecnologias;
    }

    /**
     * @return the lenguajes
     */
    public List<LenguajeDetailDTO> getLenguajes() {
        return lenguajes;
    }

    /**
     * @param lenguajes the lenguajes to set
     */
    public void setLenguajes(List<LenguajeDetailDTO> lenguajes) {
        this.lenguajes = lenguajes;
    }

    /**
     * @return the intereses
     */
    public List<InteresDetailDTO> getIntereses() {
        return intereses;
    }

    /**
     * @param intereses the intereses to set
     */
    public void setIntereses(List<InteresDetailDTO> intereses) {
        this.intereses = intereses;
    }
    
}
