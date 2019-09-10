/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.higuera
 */
@Stateless
public class HackatonLogic {
    
    private static final Logger LOGGER = Logger.getLogger(HackatonLogic.class.getName());
    
    @Inject
    private HackatonPersistence persistence;
    
    public HackatonEntity createHackaton(HackatonEntity hackaton)throws BusinessLogicException
    {
        if(hackaton.getNombre()==null){
            throw new BusinessLogicException("El nombre de la hackaton está vacío");
        }
        LOGGER.log(Level.INFO, "Inicia el proceso de creacion de la hackaton");
        hackaton= persistence.create(hackaton);
        LOGGER.log(Level.INFO, "Termina el proceso de creacion de la hackaton");
        return hackaton;
    }
    
    
    public List<HackatonEntity> getHackatones()
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar todas las hackatones");
        List<HackatonEntity> hackatones = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina el proceso de consultar todas las hackatones");
        return hackatones;
    }
    
    public HackatonEntity getHackaton(Long hackatonId)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar la hackaton con id={0}", hackatonId);
        HackatonEntity hackaton = persistence.find(hackatonId);
        if(hackaton == null){
            LOGGER.log(Level.SEVERE, "La hackaton con el id = {0} no existe", hackatonId);
        }
        LOGGER.log(Level.INFO, "Termina el proceso de consultar la hackaton con id={0}", hackatonId);
        return hackaton;
    }
    
    
    public HackatonEntity updateHackaton(Long hackatonId, HackatonEntity hackatonEntity)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar hackaton con id = {0}", hackatonId);
        HackatonEntity newEntity = persistence.update(hackatonEntity);
        LOGGER.log(Level.INFO, "Termina el proceso de actualizar la hackaton con id={0}", hackatonEntity.getId());
        return newEntity;
    }
    
    
    public void deleteHackaton(Long hackatonId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar hackaton con id = {0}", hackatonId);
        HackatonEntity hackatonEntity = persistence.find(hackatonId);
        persistence.delete(hackatonId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar hackaton con id = {0}", hackatonId);
        
    }
}
