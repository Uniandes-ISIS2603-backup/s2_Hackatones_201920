/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ActualPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author a.pedraza
 */
@Stateless
public class ActualLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ActualLogic.class.getName());
    
    @Inject
    private ActualPersistence persistence;
    
    public ActualEntity  createActual(ActualEntity actual) throws BusinessLogicException{
        
        if(actual.getReglas() ==null){
            throw new BusinessLogicException("Las reglas esta vacia");
        }
        if(actual.getRestricciones()==null){
            throw new BusinessLogicException("Las restricciones esta vacia");
        }
        if(actual.getReglas().equals("")){
            throw new BusinessLogicException("Las reglas es la cadena vacia");
        } 
        if(actual.getRestricciones().equals("")){
            throw new BusinessLogicException("Las restricciones es la cadena vacia");
        }
        
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la hackaton actual");
        actual = persistence.create(actual);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la hackaton actual");
        return actual;
    }
    
    public List<ActualEntity> getActuales() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los actuales");
        List<ActualEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los actuales");
        return lista;
    }
    
    public ActualEntity getActual(Long actualId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la actual con id = {0}", actualId);
        ActualEntity actualEntity = persistence.find(actualId);
        if (actualEntity == null) {
            LOGGER.log(Level.SEVERE, "La actual con el id = {0} no existe", actualId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la actual con id = {0}", actualId);
        return actualEntity;
    }
    
    public ActualEntity updateActual(Long actualId, ActualEntity actualEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la actual con id = {0}", actualId);
        ActualEntity newActualEntity = persistence.update(actualEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la actual con id = {0}", actualId);
        return newActualEntity;
    }
    
     public void deleteActual(Long actualId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la actual con id = {0}", actualId);
        persistence.delete(actualId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la actual con id = {0}", actualId);
    }
}