/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import co.edu.uniandes.csw.hackatones.persistence.LugarPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.monsalve
 */
@Stateless
public class HackatonLugarLogic {
    
  private static final Logger LOGGER = Logger.getLogger(HackatonLugarLogic.class.getName());

    @Inject
    private HackatonPersistence hackatonPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private LugarPersistence lugarPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Agregar un lugar a una hackaton
     *
     * @param lugarId El id del lugar a añadir
     * @param hackatonId El id de la hackaton a la cual se le va a guardar el lugar.
     * @return El lugar que fue agregado a la hackaton.
     */
    public LugarEntity addLugar(Long lugarId, Long hackatonId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el lugar con id = {0} a la hackaton con id = " + lugarId, hackatonId);
        LugarEntity lugarEntity = lugarPersistence.find(lugarId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        hackatonEntity.setLugar(lugarEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el lugar con id = {0} a la hackaton con id = " + lugarId, hackatonId);
        return lugarPersistence.find(lugarId);
    }

    /**
     *
     * Obtener un lugar por medio del id de la hackaton.
     *
     * @param hackatonId id de la hackaton a ser buscada.
     * @return el lugar de la hackaton dada.
     */
    public LugarEntity getLugar(Long hackatonId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el lugar de la hackaton con id = {0}", hackatonId);
        LugarEntity lugarEntity = hackatonPersistence.find(hackatonId).getLugar();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el lugar de la hackaton con id = {0}", hackatonId);
        return lugarEntity;
    }

    /**
     * Remplazar lugar de una hackaton
     *
     * @param hackatonId el id de la hackaton que se quiere actualizar.
     * @param lugarId El id del nuevo lugar asociado a la hackaton.
     * @return el nuevo lugar asociado.
     */
    public LugarEntity replaceLugar(Long hackatonId, Long lugarId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el lugar de la hackaton  con id = {0}", hackatonId);
        LugarEntity lugarEntity = lugarPersistence.find(lugarId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        hackatonEntity.setLugar(lugarEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el lugar con id = {0} a la hackaton con id = " + lugarId, hackatonId);
        return lugarPersistence.find(lugarId);
    }

    /**
     * Borrar el lugar de una hackaton dada
     *
     * @param hackatonId La hackaton la cual se le desea borrar su lugar.
     * @throws BusinessLogicException si la hackaton no tiene lugar
     */
    public void removeLugar(Long hackatonId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el lugar de la hackaton con id = {0}", hackatonId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        if (hackatonEntity.getLugar() == null) {
            throw new BusinessLogicException("La hackaton no tiene lugar");
        }
        LugarEntity lugarEntity = lugarPersistence.find(hackatonEntity.getLugar().getId());
        hackatonEntity.setLugar(null);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el lugar con id = {0} de la hackaton con id = " + lugarEntity.getId(), hackatonId);
    }
  }
