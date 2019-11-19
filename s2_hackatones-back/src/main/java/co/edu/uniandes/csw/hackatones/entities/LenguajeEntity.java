/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Santiago Estupinan
 */
@Entity
public class LenguajeEntity extends BaseEntity implements Serializable
{
    /**
     * EL nombre del lenguaje
     */
    private String name;
    
    @PodamExclude
    @ManyToMany(mappedBy = "lenguajes")
    private List<HackatonEntity> hackatones = new ArrayList<>();
    
    /**
     * La lista de participantes que usan el lenguaje
     */
    @PodamExclude
    @ManyToMany
    private List<UsuarioEntity> participantes = new ArrayList<>();
    
    @Override
    public boolean equals(Object obj) {
    if (! super.equals(obj)) {
      return false;
    }
    LenguajeEntity fobj = (LenguajeEntity) obj;
    return this.getId().equals(fobj.getId());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.hackatones);
        hash = 11 * hash + Objects.hashCode(this.participantes);
        return hash;
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

    /**
     * @return the participantes
     */
    public List<UsuarioEntity> getParticipantes() {
        return participantes;
    }

    /**
     * @param participantes the participantes to set
     */
    public void setParticipantes(List<UsuarioEntity> participantes) {
        this.participantes = participantes;
    }

    /**
     * @return the hackatones
     */
    public List<HackatonEntity> getHackatones() {
        return hackatones;
    }

    /**
     * @param hackatones the hackatones to set
     */
    public void setHackatones(List<HackatonEntity> hackatones) {
        this.hackatones = hackatones;
    }
        
        
}
