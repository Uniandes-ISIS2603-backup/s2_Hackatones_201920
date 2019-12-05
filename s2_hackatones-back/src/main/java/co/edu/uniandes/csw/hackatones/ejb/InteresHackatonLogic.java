/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author ja.torresl
 */
@Stateless
public class InteresHackatonLogic 
{
    private static final Logger LOGGER = Logger.getLogger(InteresHackatonLogic.class.getName());

    @Inject
    private HackatonPersistence hackatonPersistence;

    @Inject
    private InteresPersistence interesPersistence;

    /**
     * Asocia un Participante existente a un Interes
     *
     * @param interesesId Identificador de la instancia de Interes
     * @param hackatonId Identificador de la instancia de Participante
     * @return Instancia de HackatonEntity que fue asociada a Interes
     */
    public HackatonEntity addHackaton(Long interesesId, Long hackatonId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", interesesId);
        InteresEntity interesEntity = interesPersistence.find(interesesId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        hackatonEntity.getIntereses().add(interesEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", interesesId);
        return hackatonPersistence.find(hackatonId);
    }

    /**
     * Obtiene una colección de instancias de HackatonEntity asociadas a una
     * instancia de Interes
     *
     * @param interesesId Identificador de la instancia de Interes
     * @return Colección de instancias de HackatonEntity asociadas a la instancia de
     * Interes
     */
    
    public List<HackatonEntity> getHackatones(Long interesesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", interesesId);
        return interesPersistence.find(interesesId).getHackatones();
    }
    
    /**
     * Obtiene una instancia de HackatonEntity asociada a una instancia de Interes
     *
     * @param interesesId Identificador de la instancia de Interes
     * @param hackatonesId Identificador de la instancia de Participante
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si el libro no está asociado al autor
     */
    
    public HackatonEntity getHackaton(Long interesesId, Long hackatonesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del autor con id = " , hackatonesId);
        List<HackatonEntity> hackatones = interesPersistence.find(interesesId).getHackatones();
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonesId);
        int index = hackatones.indexOf(hackatonEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del autor con id = " , hackatonesId);
        if (index >= 0) {
            return hackatones.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al autor");
    }
    
    /**
     * Remplaza las instancias de Participante asociadas a una instancia de Interes
     *
     * @param interesId Identificador de la instancia de Interes
     * @param hackatones Colección de instancias de HackatonEntity a asociar a instancia
     * de Interes
     * @return Nueva colección de HackatonEntity asociada a la instancia de Interes
     */
    
    public List<HackatonEntity> replaceHackatones(Long interesId, List<HackatonEntity> hackatones) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al interes con id = {0}", interesId);
        InteresEntity interesEntity = interesPersistence.find(interesId);
        List<HackatonEntity> hackatonList = hackatonPersistence.findAll();
        for (HackatonEntity hackaton : hackatonList) {
            if (hackatones.contains(hackaton)) {
                if (!hackaton.getIntereses().contains(interesEntity)) {
                    hackaton.getIntereses().add(interesEntity);
                }
            } else {
                hackaton.getIntereses().remove(interesEntity);
            }
        }
        interesEntity.setHackatones(hackatones);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los libros asocidos al interes con id = {0}", interesId);
        return interesEntity.getHackatones();
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
        HackatonEntity hackatonEntity = hackatonPersistence.find(participantesId);
        hackatonEntity.getIntereses().remove(interesEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del interes con id = {0}", interesesId);
    }
}
