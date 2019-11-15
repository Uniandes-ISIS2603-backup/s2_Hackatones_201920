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
    
    private String nombre;
    
    private String correo;
    
    private String contrasenha;
    
     /*
    * Relación a un equipo
    * dado que esta tiene cardinalidad 1.
     */
    private EquipoDTO equipo;

    public UsuarioDTO()
    {
        
    }
    
    public UsuarioDTO(UsuarioEntity usuarioEntity)
    {
        if(usuarioEntity != null)
        {
            this.id = usuarioEntity.getId();
            this.nombre = usuarioEntity.getNombre();
            this.correo = usuarioEntity.getCorreo();
            this.contrasenha = usuarioEntity.getContrasenha();
            if(usuarioEntity.getEquipo() != null){
                this.equipo = new EquipoDTO(usuarioEntity.getEquipo());
            }else{
                this.equipo = null;
            }
        }
    }
    
    public UsuarioEntity toEntity()
    {
        UsuarioEntity nuevo = new UsuarioEntity();
        nuevo.setId(this.id);
        nuevo.setNombre(this.getNombre());
        nuevo.setCorreo(this.correo);
        nuevo.setContrasenha(this.contrasenha);
        if(this.equipo != null){
            nuevo.setEquipo(this.equipo.toEntity());
        }
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
        return getNombre();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.setNombre(name);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * @return the equipo
     */
    public EquipoDTO getEquipo() {
        return equipo;
    }

    /**
     * @param equipo the equipo to set
     */
    public void setEquipo(EquipoDTO equipo) {
        this.equipo = equipo;
    }
    
    
}
