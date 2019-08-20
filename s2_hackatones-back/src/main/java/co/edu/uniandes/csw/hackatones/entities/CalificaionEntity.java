/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Esta clase representa una calificación que puso un usuario sobre una hackatón.
 * Contiene un valoración numérica (Máx. 5), un comentario y la hackatón a la que califica. 
 * @author a.pedraza
 */
@Entity
public class CalificaionEntity extends BaseEntity implements Serializable{
    
    //Calificación numérica del usuario. calificacion>=0 & calificion<=5
    private Double calificacion;
   
    //Comentario del usuario
    private String comentario;

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
    
    
}
