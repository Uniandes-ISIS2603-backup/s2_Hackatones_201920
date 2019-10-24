/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ne.cardenas
 */
@Entity
public class ParticipanteEntity extends UsuarioEntity implements Serializable {
    
    private char inscrito;
    
    @PodamExclude
    @ManyToOne
    private ActualEntity actual;
    
    @PodamExclude
    @ManyToOne
    private EquipoEntity equipo;
    
    @PodamExclude
    @ManyToMany
    private List<TecnologiaEntity> tecnologias;
    
    @PodamExclude
    @ManyToMany( mappedBy = "participantes")
    private List<InteresEntity> intereses;
    
    @PodamExclude
    @ManyToMany
    private List<LenguajeEntity> lenguajes;

    /**
     * @return the inscrito
     */
    public char isInscrito() {
        return inscrito;
    }

    /**
     * @param inscrito the inscrito to set
     */
    public void setInscrito(char inscrito) {
        this.inscrito = inscrito;
    }

    /**
     * Devuelve el actual 
     * @return ActualEntity
     */
    public ActualEntity getActual() {
        return actual;
    }
    
    /**
     * Fija un actual
     * @param actual El actual a fijar
     */
    public void setActual(ActualEntity actual) {
        this.actual = actual;
    }
    
    /**
    * @return the hackaton
    */
    public ActualEntity getHackaton() {
       return actual;
    }   
    
    /**
    * @param hackaton the hackaton to set
    */
    public void setHackaton(ActualEntity hackaton) {
       this.actual = hackaton;
    }
    
    /**
     * Devuelve el equipo
     * @return el equipio
     */
    public EquipoEntity getEquipo() {
        return equipo;
    }
    
    /**
     * Cambia el equipo
     * @param ee el equipo a fijar
     */
    public void setEquipo(EquipoEntity ee) {
        equipo = ee;
    }
    
    /**
     * Devuelve las tecnologías
     * @return las tecnologias
     */
    public List<TecnologiaEntity> getTecnologias() {
        return tecnologias;
    }
    
    /**
     * Cambia las tecnolgoias
     * @param te las tecnologias a fijar
     */
    public void setTecnologias(List<TecnologiaEntity> te) {
        tecnologias = te;
    }
    
    /**
     * Devuelve los intereses
     * @return los intereses
     */
    public List<InteresEntity> getIntereses() {
        return intereses;
    }
    
    /**
     * Cambia los intereses
     * @param ie los intereses a fijar
     */
    public void setIntereses(List<InteresEntity> ie) {
        intereses = ie;
    }
    
    /**
     * Devuelve los lenguajes
     * @return los lenguajes
     */
    public List<LenguajeEntity> getLenguajes() {
        return lenguajes;
    }
    
    /**
     * Cambia los lenguajes
     * @param le los lenguajes a fijar
     */
    public void setLenguajes(List<LenguajeEntity> le) {
        lenguajes = le;
    }
    
}
