/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
import co.edu.uniandes.csw.hackatones.persistence.ParticipantePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author ja.torresl
 */
public class InteresParticipanteLogic {
    
    
    private static final Logger LOGGER = Logger.getLogger(InteresParticipanteLogic.class.getName());

    @Inject
    private ParticipantePersistence participantePersistence;

    @Inject
    private InteresPersistence interesPersistence;

    /**
     * Asocia un Participante existente a un Interes
     *
     * @param interesesId Identificador de la instancia de Interes
     * @param participantesId Identificador de la instancia de Participante
     * @return Instancia de ParticipanteEntity que fue asociada a Interes
     */
    public ParticipanteEntity addParticipante(Long interesesId, Long participantesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", interesesId);
        InteresEntity interesEntity = interesPersistence.find(interesesId);
        ParticipanteEntity participanteEntity = participantePersistence.find(participantesId);
        participanteEntity.getIntereses().add(interesEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", interesesId);
        return participantePersistence.find(participantesId);
    }

    /**
     * Obtiene una colección de instancias de ParticipanteEntity asociadas a una
     * instancia de Interes
     *
     * @param interesesId Identificador de la instancia de Interes
     * @return Colección de instancias de ParticipanteEntity asociadas a la instancia de
     * Interes
     */
    
    public List<ParticipanteEntity> getParticipantes(Long interesesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", interesesId);
        return interesPersistence.find(interesesId).getParticipantes();
    }
    
    /**
     * Obtiene una instancia de ParticipanteEntity asociada a una instancia de Interes
     *
     * @param interesesId Identificador de la instancia de Interes
     * @param participantesId Identificador de la instancia de Participante
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si el libro no está asociado al autor
     */
    
    public ParticipanteEntity getParticipante(Long interesesId, Long participantesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del autor con id = " + interesesId, participantesId);
        List<ParticipanteEntity> participantes = interesPersistence.find(interesesId).getParticipantes();
        ParticipanteEntity participanteEntity = participantePersistence.find(participantesId);
        int index = participantes.indexOf(participanteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del autor con id = " + interesesId, participantesId);
        if (index >= 0) {
            return participantes.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al autor");
    }
    
    /**
     * Remplaza las instancias de Participante asociadas a una instancia de Interes
     *
     * @param interesId Identificador de la instancia de Interes
     * @param participantes Colección de instancias de ParticipanteEntity a asociar a instancia
     * de Interes
     * @return Nueva colección de ParticipanteEntity asociada a la instancia de Interes
     */
    
    public List<ParticipanteEntity> replaceParticipantes(Long interesId, List<ParticipanteEntity> participantes) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al interes con id = {0}", interesId);
        InteresEntity interesEntity = interesPersistence.find(interesId);
        List<ParticipanteEntity> participanteList = participantePersistence.findAll();
        for (ParticipanteEntity participante : participanteList) {
            if (participantes.contains(participante)) {
                if (!participante.getIntereses().contains(interesEntity)) {
                    participante.getIntereses().add(interesEntity);
                }
            } else {
                participante.getIntereses().remove(interesEntity);
            }
        }
        interesEntity.setParticipantes(participantes);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los libros asocidos al interes con id = {0}", interesId);
        return interesEntity.getParticipantes();
    }
    
    /**
     * Desasocia un Participante existente de un Interes existente
     *
     * @param interesesId Identificador de la instancia de Interes
     * @param participantesId Identificador de la instancia de Participante
     */
    
    public void removeParticipante(Long interesesId, Long participantesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del interes con id = {0}", interesesId);
        InteresEntity interesEntity = interesPersistence.find(interesesId);
        ParticipanteEntity participanteEntity = participantePersistence.find(participantesId);
        participanteEntity.getIntereses().remove(interesEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del interes con id = {0}", interesesId);
    }
    
}
