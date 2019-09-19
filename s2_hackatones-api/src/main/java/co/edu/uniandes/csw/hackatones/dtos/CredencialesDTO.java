/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.CredencialesEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author ne.cardenas  
 */
public class CredencialesDTO implements Serializable {
    
    private Long id;
    
    private String correo;
    
    private String contrasenha;

    public CredencialesDTO(CredencialesEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.correo = entity.getCorreo();
            this.contrasenha = entity.getContrasenha();
        }    
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
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the contrasenha
     */
    public String getContrasenha() {
        return contrasenha;
    }

    /**
     * @param contrasenha the contrasenha to set
     */
    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }
    
    public CredencialesEntity toEntity() {
        CredencialesEntity entity = new CredencialesEntity();
        entity.setId(this.id);
        entity.setCorreo(this.correo);
        entity.setContrasenha(this.contrasenha);
        return entity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
