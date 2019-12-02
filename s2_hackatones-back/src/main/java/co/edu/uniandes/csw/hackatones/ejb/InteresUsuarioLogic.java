/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
import co.edu.uniandes.csw.hackatones.persistence.UsuarioPersistence;
import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author jc.higuera
 */
@Stateless
public class InteresUsuarioLogic 
{
    private static final Logger LOGGER = Logger.getLogger(InteresUsuarioLogic.class.getName());

    @Inject
    private UsuarioPersistence usuarioPersistence;

    @Inject
    private InteresPersistence interesPersistence;

    /**
     * Asocia un Participante existente a un Interes
     *
     * @param interesesId Identificador de la instancia de Interes
     * @param participantesId Identificador de la instancia de Participante
     * @return Instancia de UsuarioEntity que fue asociada a Interes
     */
    public UsuarioEntity addParticipante(Long interesesId, Long participantesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", interesesId);
        InteresEntity interesEntity = interesPersistence.find(interesesId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(participantesId);
        usuarioEntity.getIntereses().add(interesEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", interesesId);
        return usuarioPersistence.find(participantesId);
    }

    /**
     * Obtiene una colección de instancias de UsuarioEntity asociadas a una
     * instancia de Interes
     *
     * @param interesesId Identificador de la instancia de Interes
     * @return Colección de instancias de UsuarioEntity asociadas a la instancia de
     * Interes
     */
    
    public List<UsuarioEntity> getParticipantes(Long interesesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", interesesId);
        return interesPersistence.find(interesesId).getParticipantes();
    }
    
    /**
     * Obtiene una instancia de UsuarioEntity asociada a una instancia de Interes
     *
     * @param interesesId Identificador de la instancia de Interes
     * @param participantesId Identificador de la instancia de Participante
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si el libro no está asociado al autor
     */
    
    public UsuarioEntity getParticipante(Long interesesId, Long participantesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0}" , participantesId);
        List<UsuarioEntity> participantes = interesPersistence.find(interesesId).getParticipantes();
        UsuarioEntity usuarioEntity = usuarioPersistence.find(participantesId);
        int index = participantes.indexOf(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} ", participantesId);
        if (index >= 0) {
            return participantes.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al autor");
    }
    
    /**
     * Remplaza las instancias de Participante asociadas a una instancia de Interes
     *
     * @param interesId Identificador de la instancia de Interes
     * @param participantes Colección de instancias de UsuarioEntity a asociar a instancia
     * de Interes
     * @return Nueva colección de UsuarioEntity asociada a la instancia de Interes
     */
    
    public List<UsuarioEntity> replaceParticipantes(Long interesId, List<UsuarioEntity> participantes) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al interes con id = {0}", interesId);
        InteresEntity interesEntity = interesPersistence.find(interesId);
        List<UsuarioEntity> participanteList = usuarioPersistence.findAll();
        for (UsuarioEntity participante : participanteList) {
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
        UsuarioEntity usuarioEntity = usuarioPersistence.find(participantesId);
        usuarioEntity.getIntereses().remove(interesEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del interes con id = {0}", interesesId);
    }
}
