/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ne.cardenas
 */
@Entity
public class ParticipanteEntity extends UsuarioEntity implements Serializable {
    
    private Boolean inscrito;
    
    //@PodamExclude
    //@ManyToOne
    //private ActualEntity hackaton;
    
    // private EquipoEntity equipo;
    
    // private List<TecnologiaEntity> tecnologias;
    
    // private List<InteresEntity> intereses;
    
    // private List<LenguajeEntity> lenguajes;

    /**
     * @return the inscrito
     */
    public boolean isInscrito() {
        return inscrito;
    }

    /**
     * @param inscrito the inscrito to set
     */
    public void setInscrito(boolean inscrito) {
        this.inscrito = inscrito;
    }

    /**
     * @return the hackaton
     */
  //  public ActualEntity getHackaton() {
   //     return hackaton;
    //}

    /**
     * @param hackaton the hackaton to set
     */
 //   public void setHackaton(ActualEntity hackaton) {
   //     this.hackaton = hackaton;
    //}
    
    //public EquipoEntity getEquipo() {
        //return equipo;
    //}
    
    //public void setEquipo(EquipoEntity ee) {
        //equipo = ee;
    //}
    
    //public List<TecnologiaEntity> getTecnologias() {
        //return tecnologias;
    //}
    
    //public void setTecnologias(List<TecnologiaEntity> te) {
        //tecnologias = te;
    //}
    
    //public List<InteresEntity> getIntereses() {
        //return intereses;
    //}
    
    //public void setIntereses(List<InteresEntity> ie) {
        //intereses = ie;
    //}
    
    //public List<LenguajeEntity> getLenguajes() {
        //return lenguajes;
    //}
    
    //public void setLenguajes(List<LenguajeEntity> le) {
        //lenguajes = le;
    //}
}
