/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ProximaPersistence;
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
public class ProximaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ProximaLogic.class.getName());
    
    @Inject
    private ProximaPersistence persistence;
    
    public ProximaEntity  createProxima(ProximaEntity proxima) throws BusinessLogicException{
        
        if(proxima.getReglas() ==null){
            throw new BusinessLogicException("Las reglas esta vacia");
        }
        if(proxima.getRestricciones()==null){
            throw new BusinessLogicException("Las restricciones esta vacia");
        }
        if(proxima.getReglas().equals("")){
            throw new BusinessLogicException("Las reglas es la cadena vacia");
        } 
        if(proxima.getRestricciones().equals("")){
            throw new BusinessLogicException("Las restricciones es la cadena vacia");
        }
        
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la hackaton proxima");
        proxima = persistence.create(proxima);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la hackaton proxima");
        return proxima;
    }
    
    public List<ProximaEntity> getProximas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los proximas");
        List<ProximaEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los proximas");
        return lista;
    }
    
    public ProximaEntity getProxima(Long proximaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la proxima con id = {0}", proximaId);
        ProximaEntity proximaEntity = persistence.find(proximaId);
        if (proximaEntity == null) {
            LOGGER.log(Level.SEVERE, "La proxima con el id = {0} no existe", proximaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la proxima con id = {0}", proximaId);
        return proximaEntity;
    }
    
    public ProximaEntity updateProxima(Long proximaId, ProximaEntity proximaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de Actualizar la proxima con id = {0}", proximaId);
        ProximaEntity newProximaEntity = persistence.update(proximaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la proxima con id = {0}", proximaId);
        return newProximaEntity;
    }
    
     public void deleteProxima(Long proximaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la proxima con id = {0}", proximaId);
        persistence.delete(proximaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la proxima con id = {0}", proximaId);
    }
}

