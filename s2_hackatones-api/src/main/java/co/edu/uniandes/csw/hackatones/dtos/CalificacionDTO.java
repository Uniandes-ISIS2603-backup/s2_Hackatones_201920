/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * CalificacionDTO Objeto de transferencia de datos de Hackatones. Los DTO contienen
 * las representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 *   {
 *      "id": number,
 *      "comentario": string,
 *      "calificacion": double,
 *      "hackaton": {@link HackatonDTO}
 *   }

 *
 * @author a.pedraza
 */
public class CalificacionDTO implements Serializable{
    
    private long id;
    private String comentario;
    private Double calificacion;
    
    /*
    * Relación a una hackaton  
    * dado que esta tiene cardinalidad 1.
     */
    private HackatonDTO hackaton;
         
    /**
     * Constructor por defecto
     */
    public CalificacionDTO() {
    }
    
    /**
     * Constructor a partir de la entidad
     *
     * @param calificacionEntity La entidad del libro
     */
    public CalificacionDTO(CalificacionEntity calificacionEntity) {
        if (calificacionEntity != null) {
            this.id = calificacionEntity.getId();
            this.calificacion = calificacionEntity.getCalificacion();
            this.comentario = calificacionEntity.getComentario();
            
            if (calificacionEntity.getHackaton() != null) {
               //this.hackaton = new HackatonDTO(calificacionEntity.getHackaton());
            } else {
            this.hackaton = null;
           }
        }
    }
    
    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad de la calificacion asociado.
     */
    public CalificacionEntity toEntity() {
        CalificacionEntity calificacionEntity = new CalificacionEntity();
        calificacionEntity.setId(this.getId());
        calificacionEntity.setCalificacion(this.getCalificacion());
        calificacionEntity.setComentario(this.getComentario());
        if (this.hackaton != null) {
    //        calificacionEntity.setHackaton(this.hackaton.toEntity());
       }
        return calificacionEntity;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
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
     * @return the hackaton
     */
    public HackatonDTO getHackaton() {
        return hackaton;
    }

    /**
     * @param hackaton the hackaton to set
     */
    public void setHackaton(HackatonDTO hackaton) {
       this.hackaton = hackaton;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
