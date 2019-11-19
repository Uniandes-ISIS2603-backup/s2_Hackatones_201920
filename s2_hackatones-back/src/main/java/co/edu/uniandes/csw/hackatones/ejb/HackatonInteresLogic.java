/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @hackaton a.pedraza
 */
@Stateless
public class HackatonInteresLogic {
    
    private static final Logger LOGGER = Logger.getLogger(HackatonInteresLogic.class.getName());

    @Inject
    private HackatonPersistence hackatonPersistence;

    @Inject
    private InteresPersistence interesPersistence;

    /**
     * Asocia un Interes existente a un Hackaton
     *
     * @param hackatonsId Identificador de la instancia de Hackaton
     * @param interessId Identificador de la instancia de Interes
     * @return Instancia de InteresEntity que fue asociada a Hackaton
     */
    public InteresEntity addInteres(Long hackatonsId, Long interessId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un autor al libro con id = {0}", hackatonsId);
        InteresEntity interesEntity = interesPersistence.find(interessId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonsId);
        hackatonEntity.getIntereses().add(interesEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un autor al libro con id = {0}", hackatonsId);
        return interesPersistence.find(interessId);
    }

    /**
     * Obtiene una colección de instancias de InteresEntity asociadas a una
     * instancia de Hackaton
     *
     * @param hackatonsId Identificador de la instancia de Hackaton
     * @return Colección de instancias de InteresEntity asociadas a la instancia
     * de Hackaton
     */
    public List<InteresEntity> getInteress(Long hackatonsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", hackatonsId);
        return hackatonPersistence.find(hackatonsId).getIntereses();
    }

    /**
     * Obtiene una instancia de InteresEntity asociada a una instancia de Hackaton
     *
     * @param hackatonsId Identificador de la instancia de Hackaton
     * @param interessId Identificador de la instancia de Interes
     * @return La entidad del Autor asociada al libro
     */
    public InteresEntity getInteres(Long hackatonsId, Long interessId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", hackatonsId);
        List<InteresEntity> interess = hackatonPersistence.find(hackatonsId).getIntereses();
        InteresEntity interesEntity = interesPersistence.find(interessId);
        int index = interess.indexOf(interesEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un autor del libro con id = {0}", hackatonsId);
        if (index >= 0) {
            return interess.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de Interes asociadas a una instancia de Hackaton
     *
     * @param hackatonsId Identificador de la instancia de Hackaton
     * @param list Colección de instancias de InteresEntity a asociar a instancia
     * de Hackaton
     * @return Nueva colección de InteresEntity asociada a la instancia de Hackaton
     */
    public List<InteresEntity> replaceInteress(Long hackatonsId, List<InteresEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los autores del libro con id = {0}", hackatonsId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonsId);
        hackatonEntity.setIntereses(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los autores del libro con id = {0}", hackatonsId);
        return hackatonPersistence.find(hackatonsId).getIntereses();
    }

    /**
     * Desasocia un Interes existente de un Hackaton existente
     *
     * @param hackatonsId Identificador de la instancia de Hackaton
     * @param interessId Identificador de la instancia de Interes
     */
    public void removeInteres(Long hackatonsId, Long interessId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor del libro con id = {0}", hackatonsId);
        InteresEntity interesEntity = interesPersistence.find(interessId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonsId);
        hackatonEntity.getIntereses().remove(interesEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un autor del libro con id = {0}", hackatonsId);
    }
}
