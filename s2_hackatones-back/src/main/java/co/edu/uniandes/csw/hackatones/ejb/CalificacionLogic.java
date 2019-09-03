/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.CalificacionPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author a.pedraza
 */
@Stateless
public class CalificacionLogic {
    
    @Inject
    private CalificacionPersistence persistence;
    
    public CalificacionEntity  createCalificacion(CalificacionEntity calificacion) throws BusinessLogicException{
        
        if(calificacion.getCalificacion()==null){
            throw new BusinessLogicException("La calificación numérica esta vacia");
        }
        if(calificacion.getComentario()==null){
            throw new BusinessLogicException("El comentario de la calificación esta vacio");
        }
        if(calificacion.getCalificacion()>5 || calificacion.getCalificacion()<0){
            throw new BusinessLogicException("La calificación numérica esta fuera del rango predeterminado");
        }
        if(calificacion.getComentario().equals("")){
            throw new BusinessLogicException("El comentario de la califiacación es la cadena vacia");
        }
        
        
        calificacion = persistence.create(calificacion);
        return calificacion;
    }
}
