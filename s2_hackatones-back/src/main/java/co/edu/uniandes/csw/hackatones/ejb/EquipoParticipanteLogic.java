/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.EquipoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.torresl
 */
@Stateless
public class EquipoParticipanteLogic {
    
//    private static final Logger LOGGER = Logger.getLogger(EquipoParticipanteLogic.class.getName());
//
//    @Inject
//    private EquipoPersistence EquipoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
//
//    @Inject
//    private ParticipantePersistence participantePersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
//
//    /**
//     * Agregar un autor a un premio
//     *
//     * @param participantesId El id premio a guardar
//     * @param equiposId El id del autor al cual se le va a guardar el premio.
//     * @return El premio que fue agregado al autor.
//     */
//    public EquipoEntity addEquipo(Long equiposId, Long participantesId) {
//        LOGGER.log(Level.INFO, "Inicia proceso de asociar el autor con id = {0} al premio con id = " + participantesId, equiposId);
//        EquipoEntity equipoEntity = EquipoPersistence.find(equiposId);
//        ParticipanteEntity participanteEntity = participantePersistence.find(participantesId);
//        //participanteEntity.setEquipo(equipoEntity);
//        LOGGER.log(Level.INFO, "Termina proceso de asociar el autor con id = {0} al premio con id = " + participantesId, equiposId);
//        return EquipoPersistence.find(equiposId);
//    }
//
//    /**
//     *
//     * Obtener un premio por medio de su id y el de su autor.
//     *
//     * @param participantesID id del premio a ser buscado.
//     * @return el autor solicitada por medio de su id.
//     */
//    public EquipoEntity getEquipo(Long participantesID) {
//        LOGGER.log(Level.INFO, "Inicia proceso de consultar el autor del premio con id = {0}", participantesID);
//        EquipoEntity equipoEntity = participantePersistence.find(participantesID).getEquipo();
//        LOGGER.log(Level.INFO, "Termina proceso de consultar el autor del premio con id = {0}", participantesID);
//        return equipoEntity;
//    }
//    
//    
//    /**
//     * Remplazar autor de un premio
//     *
//     * @param participantesID el id del premio que se quiere actualizar.
//     * @param equiposID El id del nuebo autor asociado al premio.
//     * @return el nuevo autor asociado.
//     */
//  
//    public EquipoEntity replaceEquipo(Long participantesID, Long equiposID) {
//        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor del premio premio con id = {0}", participantesID);
//        EquipoEntity equipoEntity = EquipoPersistence.find(equiposID);
//        ParticipanteEntity participanteEntity = participantePersistence.find(participantesID);
//        participanteEntity.setEquipo(equipoEntity);
//        LOGGER.log(Level.INFO, "Termina proceso de asociar el autor con id = {0} al premio con id = " + participantesID, equiposID);
//        return EquipoPersistence.find(equiposID);
//    }
//
//
//    /**
//     * Borrar el autor de un premio
//     *
//     * @param participantesID El premio que se desea borrar del autor.
//     * @throws BusinessLogicException si el premio no tiene autor
//     */
//    
//    public void removeEquipo(Long participantesID) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "Inicia proceso de borrar el autor del premio con id = {0}", participantesID);
//        ParticipanteEntity participanteEntity = participantePersistence.find(participantesID);
//        if (participanteEntity.getEquipo() == null) {
//            throw new BusinessLogicException("El premio no tiene autor");
//        }
//        EquipoEntity equipoEntity = EquipoPersistence.find(participanteEntity.getEquipo().getId());
//        participanteEntity.setEquipo(null);
//        equipoEntity.getParticipantes().remove(participanteEntity);
//        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0} del premio con id = " + participantesID, equipoEntity.getId());
//    }
    
}
