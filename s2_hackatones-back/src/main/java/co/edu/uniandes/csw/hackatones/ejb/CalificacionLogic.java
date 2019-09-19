/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @calificacion a.pedraza
 */
@Stateless
public class CalificacionLogic {
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    
    @Inject
    private CalificacionPersistence persistence;
    
    @Inject
    private HackatonPersistence hackatonPersistence;
    
    public CalificacionEntity  createCalificacion(CalificacionEntity calificacion) throws BusinessLogicException{
        
        /**
        if (calificacion.getHackaton() == null || hackatonPersistence.find(calificacion.getHackaton().getId()) == null) {
            throw new BusinessLogicException("El hackaton es inválido");
        }
        */
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
        
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la calificación");
        calificacion = persistence.create(calificacion);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la  calificcación");
        return calificacion;
    }
    
    public List<CalificacionEntity> getCalificaciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los calificaciones");
        List<CalificacionEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los calificaciones");
        return lista;
    }
    
    public CalificacionEntity getCalificacion(Long calificacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la calificación con id = {0}", calificacionId);
        CalificacionEntity calificacionEntity = persistence.find(calificacionId);
        if (calificacionEntity == null) {
            LOGGER.log(Level.SEVERE, "La calificación con el id = {0} no existe", calificacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la calificación con id = {0}", calificacionId);
        return calificacionEntity;
    }
    
    public CalificacionEntity updateCalificacion(Long calificacionId, CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la calificación con id = {0}", calificacionId);
        CalificacionEntity newCalificacionEntity = persistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la calificación con id = {0}", calificacionId);
        return newCalificacionEntity;
    }
    
     public void deleteCalificacion(Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificacion con id = {0}", calificacionId);
        persistence.delete(calificacionId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la calificacion con id = {0}", calificacionId);
    }
}