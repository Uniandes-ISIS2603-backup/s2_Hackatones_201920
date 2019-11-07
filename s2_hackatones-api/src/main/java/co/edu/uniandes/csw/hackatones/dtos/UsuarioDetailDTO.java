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
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable{
    
    
    public UsuarioDetailDTO()
    {
        super();
    }
    
    public UsuarioDetailDTO(UsuarioEntity entity)
    {
        super();
    }
    
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = super.toEntity();
        return entity;
    }
    


    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
