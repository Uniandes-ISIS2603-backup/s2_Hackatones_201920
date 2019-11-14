/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Santiago Estupinan
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{
    
    /**
     * El nombre del usuario
     */
    protected String nombre;
    
    private String correo;
    
    private String contrasenha;
    
    @PodamExclude
    @ManyToOne
    private EquipoEntity equipo;
    
    @PodamExclude
    @ManyToMany
    private List<HackatonEntity> hackatones;
    
    @PodamExclude
    @ManyToMany
    private List<TecnologiaEntity> tecnologias = new ArrayList<>();
    
    @PodamExclude
    @ManyToMany
    private List<LenguajeEntity> lenguajes = new ArrayList<>();
    
    @PodamExclude
    @ManyToMany
    private List<InteresEntity> intereses = new ArrayList<>();
    
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
    public EquipoEntity getEquipo() {
        return equipo;
    }

    /**
     * @param equipo the equipo to set
     */
    public void setEquipo(EquipoEntity equipo) {
        this.equipo = equipo;
    }

   

    /**
     * @return the tecnologias
     */
    public List<TecnologiaEntity> getTecnologias() {
        return tecnologias;
    }

    /**
     * @param tecnologias the tecnologias to set
     */
    public void setTecnologias(List<TecnologiaEntity> tecnologias) {
        this.tecnologias = tecnologias;
    }

    /**
     * @return the lenguajes
     */
    public List<LenguajeEntity> getLenguajes() {
        return lenguajes;
    }

    /**
     * @param lenguajes the lenguajes to set
     */
    public void setLenguajes(List<LenguajeEntity> lenguajes) {
        this.lenguajes = lenguajes;
    }

    /**
     * @return the intereses
     */
    public List<InteresEntity> getIntereses() {
        return intereses;
    }

    /**
     * @param intereses the intereses to set
     */
    public void setIntereses(List<InteresEntity> intereses) {
        this.intereses = intereses;
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
