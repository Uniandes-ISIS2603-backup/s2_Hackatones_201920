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
 * @author a.pedraza
 */
@Stateless
public class ProximaParticipanteLogic {
//    private static final Logger LOGGER = Logger.getLogger(ProximaParticipanteLogic.class.getName());
//    
//    @Inject
//    private HackatonPersistence proximaPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
//    
//    @Inject
//    private ParticipantePersistence participantePersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
//    
//   /**
//     * Asocia un participante existente a una Hackaton
//     *
//     * @param proximaId Identificador de la instancia de Hackaton
//     * @param participanteId Identificador de la instancia de Participante
//     * @return Instancia de ParticipanteEntity que fue asociada a Hackaton
//     */
//    public ParticipanteEntity addParticipante(Long proximaId, Long participanteId) {
//        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un participante a la proxima con id = {0}", proximaId);
//        ParticipanteEntity participanteEntity = participantePersistence.find(participanteId);
//        HackatonEntity proximaEntity = proximaPersistence.find(proximaId);
//        int equipoSize = proximaEntity.getTamanoEquipos();
//       //TODO
//        LOGGER.log(Level.INFO, "Termina proceso de asociarle un participante a la proxima con id = {0}", proximaId);
//        return participantePersistence.find(participanteId);
//    }
//
//    /**
//     * Obtiene una colección de instancias de ParticipanteEntity asociadas a una
//     * instancia de Hackaton
//     *
//     * @param proximaId Identificador de la instancia de Hackaton
//     * @return Colección de instancias de ParticipanteEntity asociadas a la instancia de
//     * Hackaton
//     */
//    public List<ParticipanteEntity> getParticipantes(Long proximaId) {
//        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los participantes de la proxima con id = {0}", proximaId);
//        List<ParticipanteEntity> participantes = proximaPersistence.find(proximaId).getInscritos();
//       //TODO
//        return participantes;
//    }
//
//    /**
//     * Obtiene una instancia de ParticipanteEntity asociada a una instancia de proxima
//     *
//     * @param proximaId Identificador de la instancia de proxima
//     * @param participanteId Identificador de la instancia de Participante
//     * @return La entidad de participante de la proxima
//     * @throws BusinessLogicException Si el participante no está asociado a la proxima
//     */
//    public ParticipanteEntity getParticipante(Long proximaId, Long participanteId) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "Inicia proceso de consultar el participante con id = {0} de la proxima con id = " + proximaId, participanteId);
//        List<ParticipanteEntity> participantes = proximaPersistence.find(proximaId).getInscritos();
//        //TODO
//        int i = 0;
//        LOGGER.log(Level.INFO, "Termina proceso de consultar el participante con id = {0} de la proxima con id = " + proximaId, participanteId);
//        while(i<participantes.size()) {
//            ParticipanteEntity actual=participantes.get(i);
//            if(actual.getId().equals(participanteId))
//                return actual;
//        }
//        throw new BusinessLogicException("El participante no está asociado a la proxima");
//    }
//
//    /**
//     * Remplaza las instancias de Participante asociadas a una instancia de proxima
//     *
//     * @param proximaId Identificador de la instancia de proxima
//     * @param participantes Colección de instancias de ParticipanteEntity a asociar a instancia
//     * de proxima
//     * @return Nueva colección de ParticipanteEntity asociada a la instancia de proxima
//     */
//    public List<ParticipanteEntity> replaceParticipantess(Long proximaId, List<ParticipanteEntity> participantes) {
//        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los participantes asocidos a la proxima con id = {0}", proximaId);
//        HackatonEntity proximaEntity = proximaPersistence.find(proximaId);
//        int cantidad=proximaEntity.getTamanoEquipos()*(participantes.size()/proximaEntity.getTamanoEquipos());
//        proximaEntity.getInscritos().clear();
//        for(int i=0; i<cantidad;i++){
//            proximaEntity.getInscritos().add(participantes.get(i));
//        }
//        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los participantes asocidos a la proxima con id = {0}", proximaId);
//        return proximaEntity.getInscritos();
//    }
//
//    /**
//     * Desasocia un Participante existente de una proxima existente
//     *
//     * @param proximaId Identificador de la instancia de Hackaton
//     * @param participanteId Identificador de la instancia de Participante
//     */
//    public void removeParticipante(Long proximaId, Long participanteId) {
//        LOGGER.log(Level.INFO, "Inicia proceso de borrar un participante de la proxima con id = {0}", proximaId);
//        HackatonEntity proximaEntity = proximaPersistence.find(proximaId);
//        ParticipanteEntity participanteEntity = participantePersistence.find(participanteId);
//        //TODO
//        LOGGER.log(Level.INFO, "Termina proceso de borrar un participante de la proxima con id = {0}", proximaId);
//    }
}