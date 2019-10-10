/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
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
        
        
        if (calificacion.getHackaton() == null || hackatonPersistence.find(calificacion.getHackaton().getId()) == null) {
            throw new BusinessLogicException("El hackaton es inválido");
        }
        else if(calificacion.getCalificacion()==null){
            throw new BusinessLogicException("La calificación numérica esta vacia");
        }
        else if(calificacion.getComentario()==null){
            throw new BusinessLogicException("El comentario de la calificación esta vacio");
        }
        else if(calificacion.getCalificacion()>5 || calificacion.getCalificacion()<0){
            throw new BusinessLogicException("La calificación numérica esta fuera del rango predeterminado");
        }
        else if(calificacion.getComentario().equals("")){
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
    
    public List<CalificacionEntity> getCalificaciones(Long hackatonId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los calificaciones");
        List<CalificacionEntity> lista = hackatonPersistence.find(hackatonId).getCalificaciones();
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
    
    public CalificacionEntity updateCalificacion(Long calificacionId, CalificacionEntity calificacionEntity, boolean b) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la calificación con id = {0}", calificacionId);
        CalificacionEntity newCalificacionEntity = persistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la calificación con id = {0}", calificacionId);
        return newCalificacionEntity;
    }
    
    public CalificacionEntity updateCalificacion(Long hackatonesId, CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el calificacion con id = {0} del hackaton con id = " + hackatonesId, calificacionEntity.getId());
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonesId);
        calificacionEntity.setHackaton(hackatonEntity);
        persistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el calificacion con id = {0} del hackaton con id = " + hackatonesId, calificacionEntity.getId());
        return calificacionEntity;
    }
    
     public void deleteCalificacion(Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificacion con id = {0}", calificacionId);
        persistence.delete(calificacionId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la calificacion con id = {0}", calificacionId);
    }

    public CalificacionEntity getCalificacion(Long hackatonId, Long calificacionsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las calificaciones");
        List<CalificacionEntity> lista = hackatonPersistence.find(hackatonId).getCalificaciones();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos las calificaciones");
        CalificacionEntity calificacion=null;
        for(int i=0; i<lista.size() && calificacion==null; i++){
            if(lista.get(i).getId()==calificacionsId)
                calificacion=lista.get(i);
        }
        return calificacion;
    }

    public void deleteCalificacion(Long hackatonesId, Long calificacionesId) throws BusinessLogicException {        
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificación con id = {0} de la hackaton con id = " + hackatonesId, calificacionesId);
        CalificacionEntity old = getCalificacion(hackatonesId, calificacionesId);
        if (old == null) {
            throw new BusinessLogicException("La calificacion con id = " + calificacionesId + " no esta asociado a la hackaton con id = " + hackatonesId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la calificacion con id = {0} de la hackaton con id = " + hackatonesId, calificacionesId);
    }

}