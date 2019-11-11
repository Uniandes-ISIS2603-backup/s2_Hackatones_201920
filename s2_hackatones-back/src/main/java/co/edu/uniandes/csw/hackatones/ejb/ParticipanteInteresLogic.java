/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.torresl
 */
@Stateless
public class ParticipanteInteresLogic {
    
//    private static final Logger LOGGER = Logger.getLogger(ParticipanteInteresLogic.class.getName());
//
//    @Inject
//    private InteresPersistence interesPersistence;
//    
//    @Inject
//    private ParticipantePersistence participantePersistence;
//
//    /**
//     * Asocia un Interes existente a un Participante
//     *
//     * @param participantesId Identificador de la instancia de Participante
//     * @param interessId Identificador de la instancia de Interes
//     * @return Instancia de InteresEntity que fue asociada a Participante
//     */
//    public InteresEntity addInteres(Long participantesId, Long interessId) {
//        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", participantesId);
//        ParticipanteEntity participanteEntity = participantePersistence.find(participantesId);
//        InteresEntity interesEntity = interesPersistence.find(interessId);
//        interesEntity.getParticipantes().add(participanteEntity);
//        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", participantesId);
//        return interesPersistence.find(interessId);
//    }
//
//    /**
//     * Obtiene una colección de instancias de InteresEntity asociadas a una
//     * instancia de Participante
//     *
//     * @param participantesId Identificador de la instancia de Participante
//     * @return Colección de instancias de InteresEntity asociadas a la instancia de
//     * Participante
//     */
//    
//    public List<InteresEntity> getIntereses(Long participantesId) {
//        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", participantesId);
//        return participantePersistence.find(participantesId).getIntereses();
//    }
//    
//    /**
//     * Obtiene una instancia de InteresEntity asociada a una instancia de Participante
//     *
//     * @param participantesId Identificador de la instancia de Participante
//     * @param interessId Identificador de la instancia de Interes
//     * @return La entidadd de Libro del autor
//     * @throws BusinessLogicException Si el libro no está asociado al autor
//     */
//    
//    public InteresEntity getInteres(Long participantesId, Long interessId) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del autor con id = " + participantesId, interessId);
//        List<InteresEntity> intereses = participantePersistence.find(participantesId).getIntereses();
//        InteresEntity interesEntity = interesPersistence.find(interessId);
//        int index = intereses.indexOf(interesEntity);
//        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del autor con id = " + participantesId, interessId);
//        if (index >= 0) {
//            return intereses.get(index);
//        }
//        throw new BusinessLogicException("El libro no está asociado al autor");
//    }
//    
//
//    /**
//     * Remplaza las instancias de Interes asociadas a una instancia de Participante
//     *
//     * @param participanteId Identificador de la instancia de Participante
//     * @param interess Colección de instancias de InteresEntity a asociar a instancia
//     * de Participante
//     * @return Nueva colección de InteresEntity asociada a la instancia de Participante
//     */
//    
//    public List<InteresEntity> replaceInteress(Long participanteId, List<InteresEntity> interess) {
//        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al participante con id = {0}", participanteId);
//        ParticipanteEntity participanteEntity = participantePersistence.find(participanteId);
//        List<InteresEntity> interesList = interesPersistence.findAll();
//        for (InteresEntity interes : interesList) {
//            if (interess.contains(interes)) {
//                if (!interes.getParticipantes().contains(participanteEntity)) {
//                    interes.getParticipantes().add(participanteEntity);
//                }
//            } else {
//                interes.getParticipantes().remove(participanteEntity);
//            }
//        }
//        participanteEntity.setIntereses(interess);
//        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los libros asocidos al participante con id = {0}", participanteId);
//        return participanteEntity.getIntereses();
//    }
//    
//    /**
//     * Desasocia un Interes existente de un Participante existente
//     *
//     * @param participantesId Identificador de la instancia de Participante
//     * @param interessId Identificador de la instancia de Interes
//     */
//    
//    public void removeInteres(Long participantesId, Long interessId) {
//        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del participante con id = {0}", participantesId);
//        ParticipanteEntity participanteEntity = participantePersistence.find(participantesId);
//        InteresEntity interesEntity = interesPersistence.find(interessId);
//        interesEntity.getParticipantes().remove(participanteEntity);
//        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del participante con id = {0}", participantesId);
//    }
    
}
