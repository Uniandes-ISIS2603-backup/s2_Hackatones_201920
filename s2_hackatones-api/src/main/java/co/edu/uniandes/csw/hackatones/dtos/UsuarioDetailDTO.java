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
    
    private CredencialesDTO credenciales;
    
    public UsuarioDetailDTO()
    {
        super();
    }
    
    public UsuarioDetailDTO(UsuarioEntity entity)
    {
        super();
        if(entity.getCredenciales() != null)
        {
            this.credenciales = new CredencialesDTO(entity.getCredenciales());
        }
    }
    
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = super.toEntity();
        if (getCredenciales()!= null) {
            entity.setCredenciales(getCredenciales().toEntity());
        }
        return entity;
    }
    

    /**
     * @return the credenciales
     */
    public CredencialesDTO getCredenciales() {
        return credenciales;
    }

    /**
     * @param credenciales the credenciales to set
     */
    public void setCredenciales(CredencialesDTO credenciales) {
        this.credenciales = credenciales;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}