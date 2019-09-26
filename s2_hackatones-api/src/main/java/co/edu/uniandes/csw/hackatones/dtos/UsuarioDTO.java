/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author s.estupinan
 */
public class UsuarioDTO implements Serializable
{
    private Long id;
    private String name;

    public UsuarioDTO()
    {
        
    }
    
    public UsuarioDTO(UsuarioEntity usuarioEntity)
    {
        if(usuarioEntity != null)
        {
            this.id = usuarioEntity.getId();
            this.name = usuarioEntity.getNombre();
        }
    }
    
    public UsuarioEntity toEntity()
    {
        UsuarioEntity nuevo = new UsuarioEntity();
        nuevo.setId(this.id);
        nuevo.setNombre(this.name);
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
