/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ParticipantePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ne.cardenas
 */
@Stateless  
public class ParticipanteLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ParticipanteLogic.class.getName());
    
    @Inject
    private ParticipantePersistence persistence;
    
    public ParticipanteEntity createParticipante(ParticipanteEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del participante");
        if (entity.getHackaton() == null)
            throw new BusinessLogicException("El participante debe estar inscrito en al menos una hackatón");
        if (entity.getLenguajes() == null)
            throw new BusinessLogicException("El participante debe tener al menos un lenguaje");
        else
            if (entity.getLenguajes().isEmpty())
                throw new BusinessLogicException("El participante debe tener al menos un lenguaje");
        if (entity.getIntereses() == null)
            throw new BusinessLogicException("El participante debe tener al menos un interés");
        else
            if (entity.getIntereses().isEmpty())
                throw new BusinessLogicException("El participante debe tener al menos un interés");
        if (entity.getTecnologias() == null)
            throw new BusinessLogicException("El participante debe tener al menos una tecnología");
        else
            if (entity.getTecnologias().isEmpty())
                throw new BusinessLogicException("El participante debe tener al menos una tecnología");
        persistence.create(entity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de participante");
        return entity;
        
    }
    
    public List<ParticipanteEntity> getParticipantes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los participantes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ParticipanteEntity> parts = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas los participantes");
        return parts;
    }
    
    public ParticipanteEntity getParticipante(Long participanteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar participante con id = {0}", participanteId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        ParticipanteEntity participanteEntity = persistence.find(participanteId);
        if (participanteEntity == null) {
            LOGGER.log(Level.SEVERE, "El participante con el id = {0} no existe", participanteId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar participante con id = {0}", participanteId);
        return participanteEntity;
    }
    
    public ParticipanteEntity updateParticipante(Long participanteId, ParticipanteEntity participanteEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar participante con id = {0}", participanteId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        ParticipanteEntity newEntity = persistence.update(participanteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar participante con id = {0}", participanteEntity.getId());
        return newEntity;
    }
    
    public void deleteParticipante(Long participanteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar participante con id = {0}", participanteId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delete(participanteId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar participante con id = {0}", participanteId);
    }
}
