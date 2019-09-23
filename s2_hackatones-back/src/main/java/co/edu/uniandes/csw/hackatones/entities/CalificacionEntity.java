/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Esta clase representa una calificación que puso un usuario sobre una hackatón.
 * Contiene un valoración numérica (Máx. 5), un comentario y la hackatón a la que califica. 
 * @author a.pedraza
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable{
    
    //Calificación numérica del usuario. calificacion>=0 & calificion<=5
    private Double calificacion;
   
    //Comentario del usuario
    private String comentario;

    
//    @PodamExclude
//    @ManyToOne
//    private HackatonEntity hackaton; 

    /**
     * 
     * @return the calificacion
     */
    public Double getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the hackaton
     */
//    public HackatonEntity getHackaton() {
//        return hackaton;
//    }

    /**
     * @param hackaton the hackaton to set
     */
//    public void setHackaton(HackatonEntity hackaton) {
//        this.hackaton = hackaton;
//   }
    
    
}