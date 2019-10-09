/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import co.edu.uniandes.csw.hackatones.persistence.TecnologiaPersistence;
import co.edu.uniandes.csw.hackatones.persistence.ParticipantePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author ja.torresl
 */
public class TecnologiaParticipanteLogic {
    
    
    private static final Logger LOGGER = Logger.getLogger(TecnologiaParticipanteLogic.class.getName());

    @Inject
    private ParticipantePersistence participantePersistence;

    @Inject
    private TecnologiaPersistence tecnologiaPersistence;

    /**
     * Asocia un Participante existente a un Tecnologia
     *
     * @param tecnologiaesId Identificador de la instancia de Tecnologia
     * @param participantesId Identificador de la instancia de Participante
     * @return Instancia de ParticipanteEntity que fue asociada a Tecnologia
     */
    public ParticipanteEntity addParticipante(Long tecnologiaesId, Long participantesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", tecnologiaesId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiaesId);
        ParticipanteEntity participanteEntity = participantePersistence.find(participantesId);
        //participanteEntity.getTecnologias().add(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", tecnologiaesId);
        return participantePersistence.find(participantesId);
    }

    /**
     * Obtiene una colección de instancias de ParticipanteEntity asociadas a una
     * instancia de Tecnologia
     *
     * @param tecnologiaesId Identificador de la instancia de Tecnologia
     * @return Colección de instancias de ParticipanteEntity asociadas a la instancia de
     * Tecnologia
     */
    /**
    public List<ParticipanteEntity> getParticipantes(Long tecnologiaesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", tecnologiaesId);
        return tecnologiaPersistence.find(tecnologiaesId).getParticipantes();
    }
    */
    /**
     * Obtiene una instancia de ParticipanteEntity asociada a una instancia de Tecnologia
     *
     * @param tecnologiaesId Identificador de la instancia de Tecnologia
     * @param participantesId Identificador de la instancia de Participante
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si el libro no está asociado al autor
     */
    /**
    public ParticipanteEntity getParticipante(Long tecnologiaesId, Long participantesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del autor con id = " + tecnologiaesId, participantesId);
        List<ParticipanteEntity> participantes = tecnologiaPersistence.find(tecnologiaesId).getParticipantes();
        ParticipanteEntity participanteEntity = participantePersistence.find(participantesId);
        int index = participantes.indexOf(participanteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del autor con id = " + tecnologiaesId, participantesId);
        if (index >= 0) {
            return participantes.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al autor");
    }
    */
    /**
     * Remplaza las instancias de Participante asociadas a una instancia de Tecnologia
     *
     * @param tecnologiaId Identificador de la instancia de Tecnologia
     * @param participantes Colección de instancias de ParticipanteEntity a asociar a instancia
     * de Tecnologia
     * @return Nueva colección de ParticipanteEntity asociada a la instancia de Tecnologia
     */
    /**
    public List<ParticipanteEntity> replaceParticipantes(Long tecnologiaId, List<ParticipanteEntity> participantes) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al tecnologia con id = {0}", tecnologiaId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiaId);
        List<ParticipanteEntity> participanteList = participantePersistence.findAll();
        for (ParticipanteEntity participante : participanteList) {
            if (participantes.contains(participante)) {
                if (!participante.getTecnologias().contains(tecnologiaEntity)) {
                    participante.getTecnologias().add(tecnologiaEntity);
                }
            } else {
                participante.getTecnologias().remove(tecnologiaEntity);
            }
        }
        tecnologiaEntity.setParticipantes(participantes);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los libros asocidos al tecnologia con id = {0}", tecnologiaId);
        return tecnologiaEntity.getParticipantes();
    }
    */
    /**
     * Desasocia un Participante existente de un Tecnologia existente
     *
     * @param tecnologiaesId Identificador de la instancia de Tecnologia
     * @param participantesId Identificador de la instancia de Participante
     */
    /**
    public void removeParticipante(Long tecnologiaesId, Long participantesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del tecnologia con id = {0}", tecnologiaesId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiaesId);
        ParticipanteEntity participanteEntity = participantePersistence.find(participantesId);
        participanteEntity.getTecnologias().remove(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del tecnologia con id = {0}", tecnologiaesId);
    }
    */
}
