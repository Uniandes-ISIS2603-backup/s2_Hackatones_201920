/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author s.estupinan
 */
public class LenguajeDTO implements Serializable 
{
    private Long id;
    private String name;
    
    public LenguajeDTO()
    {
        
    }

    public LenguajeDTO(LenguajeEntity lenguajeEntity)
    {
        if(lenguajeEntity != null)
        {
            this.id = lenguajeEntity.getId();
            this.name = lenguajeEntity.getName();
        }
    }
    
    public LenguajeEntity toEntity()
    {
        LenguajeEntity nuevo = new LenguajeEntity();
        nuevo.setId(this.getId());
        nuevo.setName(this.getName());
        return nuevo;
    }
    
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}
