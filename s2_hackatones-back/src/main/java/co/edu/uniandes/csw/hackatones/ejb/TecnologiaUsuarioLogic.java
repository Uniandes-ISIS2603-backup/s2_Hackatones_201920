/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.TecnologiaPersistence;
import co.edu.uniandes.csw.hackatones.persistence.UsuarioPersistence;
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
public class TecnologiaUsuarioLogic 
{

    private static final Logger LOGGER = Logger.getLogger(TecnologiaUsuarioLogic.class.getName());

    @Inject
    private UsuarioPersistence usuarioPersistence;

    @Inject
    private TecnologiaPersistence tecnologiaPersistence;

    /**
     * Asocia un Participante existente a un Tecnologia
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @param participantesId Identificador de la instancia de Participante
     * @return Instancia de UsuarioEntity que fue asociada a Tecnologia
     */
    public UsuarioEntity addParticipante(Long tecnologiasId, Long participantesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un usuario al tecnologia con id = {0}", tecnologiasId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(participantesId);
        usuarioEntity.getTecnologias().add(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un usuario al tecnologia con id = {0}", tecnologiasId);
        return usuarioPersistence.find(participantesId);
    }

    /**
     * Obtiene una colección de instancias de UsuarioEntity asociadas a una
     * instancia de Tecnologia
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @return Colección de instancias de UsuarioEntity asociadas a la instancia de
     * Tecnologia
     */
   
    public List<UsuarioEntity> getParticipantes(Long tecnologiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los usuarios del tecnologia con id = {0}", tecnologiasId);
        return tecnologiaPersistence.find(tecnologiasId).getParticipantes();
    }
 
    /**
     * Obtiene una instancia de UsuarioEntity asociada a una instancia de Tecnologia
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @param participantesId Identificador de la instancia de Participante
     * @return La entidadd de Usuario del tecnologia
     * @throws BusinessLogicException Si el usuario no está asociado al tecnologia
     */
  
    public UsuarioEntity getParticipante(Long tecnologiasId, Long participantesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el usuario con id = {0}" , participantesId);
        List<UsuarioEntity> participantes = tecnologiaPersistence.find(tecnologiasId).getParticipantes();
        UsuarioEntity usuarioEntity = usuarioPersistence.find(participantesId);
        int index = participantes.indexOf(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el usuario con id = {0}", participantesId);
        if (index >= 0) {
            return participantes.get(index);
        }
        throw new BusinessLogicException("El usuario no está asociado al tecnologia");
    }
 
    /**
     * Remplaza las instancias de Participante asociadas a una instancia de Tecnologia
     *
     * @param tecnologiaId Identificador de la instancia de Tecnologia
     * @param participantes Colección de instancias de UsuarioEntity a asociar a instancia
     * de Tecnologia
     * @return Nueva colección de UsuarioEntity asociada a la instancia de Tecnologia
     */

    public List<UsuarioEntity> replaceParticipantes(Long tecnologiaId, List<UsuarioEntity> participantes) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los usuarios asocidos al tecnologia con id = {0}", tecnologiaId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiaId);
        List<UsuarioEntity> participanteList = usuarioPersistence.findAll();
        for (UsuarioEntity participante : participanteList) {
            if (participantes.contains(participante)) {
                if (!participante.getTecnologias().contains(tecnologiaEntity)) {
                    participante.getTecnologias().add(tecnologiaEntity);
                }
            } else {
                participante.getTecnologias().remove(tecnologiaEntity);
            }
        }
        tecnologiaEntity.setParticipantes(participantes);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los usuarios asocidos al tecnologia con id = {0}", tecnologiaId);
        return tecnologiaEntity.getParticipantes();
    }

    /**
     * Desasocia un Participante existente de un Tecnologia existente
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @param participantesId Identificador de la instancia de Participante
     */
  
    public void removeParticipante(Long tecnologiasId, Long participantesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un usuario del tecnologia con id = {0}", tecnologiasId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(participantesId);
        usuarioEntity.getTecnologias().remove(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un usuario del tecnologia con id = {0}", tecnologiasId);
    }
    
}
