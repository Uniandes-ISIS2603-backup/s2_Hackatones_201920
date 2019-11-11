/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.TecnologiaPersistence;
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
public class ParticipanteTecnologiaLogic {
    
//    private static final Logger LOGGER = Logger.getLogger(ParticipanteTecnologiaLogic.class.getName());
//
//    @Inject
//    private TecnologiaPersistence tecnologiaPersistence;
//    
//    //@Inject
//    //private ParticipantePersistence participantePersistence;
//
//    /**
//     * Asocia un Tecnologia existente a un Participante
//     *
//     * @param participantesId Identificador de la instancia de Participante
//     * @param tecnologiasId Identificador de la instancia de Tecnologia
//     * @return Instancia de TecnologiaEntity que fue asociada a Participante
//     */
//    public TecnologiaEntity addTecnologia(Long participantesId, Long tecnologiasId) {
//        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", participantesId);
//        //ParticipanteEntity participanteEntity = participantePersistence.find(participantesId);
//        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
//        //tecnologiaEntity.getParticipantes().add(participanteEntity);
//        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", participantesId);
//        return tecnologiaPersistence.find(tecnologiasId);
//    }
//
//    /**
//     * Obtiene una colección de instancias de TecnologiaEntity asociadas a una
//     * instancia de Participante
//     *
//     * @param participantesId Identificador de la instancia de Participante
//     * @return Colección de instancias de TecnologiaEntity asociadas a la instancia de
//     * Participante
//     */
// 
//    public List<TecnologiaEntity> getTecnologias(Long participantesId) {
//        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", participantesId);
//        return participantePersistence.find(participantesId).getTecnologias();
//    }
//  
//    /**
//     * Obtiene una instancia de TecnologiaEntity asociada a una instancia de Participante
//     *
//     * @param participantesId Identificador de la instancia de Participante
//     * @param tecnologiasId Identificador de la instancia de Tecnologia
//     * @return La entidadd de Libro del autor
//     * @throws BusinessLogicException Si el libro no está asociado al autor
//     */
//  
//    public TecnologiaEntity getTecnologia(Long participantesId, Long tecnologiasId) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del autor con id = " + participantesId, tecnologiasId);
//        List<TecnologiaEntity> tecnologiaes = participantePersistence.find(participantesId).getTecnologias();
//        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
//        int index = tecnologiaes.indexOf(tecnologiaEntity);
//        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del autor con id = " + participantesId, tecnologiasId);
//        if (index >= 0) {
//            return tecnologiaes.get(index);
//        }
//        throw new BusinessLogicException("El libro no está asociado al autor");
//    }
//  
//
//    /**
//     * Remplaza las instancias de Tecnologia asociadas a una instancia de Participante
//     *
//     * @param participanteId Identificador de la instancia de Participante
//     * @param tecnologias Colección de instancias de TecnologiaEntity a asociar a instancia
//     * de Participante
//     * @return Nueva colección de TecnologiaEntity asociada a la instancia de Participante
//     */
//   
//    public List<TecnologiaEntity> replaceTecnologias(Long participanteId, List<TecnologiaEntity> tecnologias) {
//        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al participante con id = {0}", participanteId);
//        ParticipanteEntity participanteEntity = participantePersistence.find(participanteId);
//        List<TecnologiaEntity> tecnologiaList = tecnologiaPersistence.findAll();
//        for (TecnologiaEntity tecnologia : tecnologiaList) {
//            if (tecnologias.contains(tecnologia)) {
//                if (!tecnologia.getParticipantes().contains(participanteEntity)) {
//                    tecnologia.getParticipantes().add(participanteEntity);
//                }
//            } else {
//                tecnologia.getParticipantes().remove(participanteEntity);
//            }
//        }
//        participanteEntity.setTecnologias(tecnologias);
//        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los libros asocidos al participante con id = {0}", participanteId);
//        return participanteEntity.getTecnologias();
//    }
// 
//    /**
//     * Desasocia un Tecnologia existente de un Participante existente
//     *
//     * @param participantesId Identificador de la instancia de Participante
//     * @param tecnologiasId Identificador de la instancia de Tecnologia
//     */
//  
//    public void removeTecnologia(Long participantesId, Long tecnologiasId) {
//        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del participante con id = {0}", participantesId);
//        ParticipanteEntity participanteEntity = participantePersistence.find(participantesId);
//        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
//        tecnologiaEntity.getParticipantes().remove(participanteEntity);
//        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del participante con id = {0}", participantesId);
//    }
// 
}
