/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.EquipoPersistence;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @hackaton a.pedraza
 */
@Stateless
public class EquipoHackatonLogic {
 
    private static final Logger LOGGER = Logger.getLogger(EquipoHackatonLogic.class.getName());

    @Inject
    private HackatonPersistence hackatonPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private EquipoPersistence equipoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Agregar una hackaton a un equipo
     *
     * @param equipoId El id equipo a guardar
     * @param hackatonId El id de la hackaton a la cual se le va a guardar el equipo.
     * @return El equipo que fue agregado a la hackaton.
     */
    public HackatonEntity addHackaton(Long hackatonId, Long equipoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar la hackaton con id = {0} al equipo con id = " + equipoId, hackatonId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        EquipoEntity equipoEntity = equipoPersistence.find(equipoId);
        equipoEntity.setHackaton(hackatonEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar la hackaton con id = {0} al equipo con id = " + equipoId, hackatonId);
        return hackatonPersistence.find(hackatonId);
    }

    /**
     *
     * Obtener un equipo por medio de su id y el de su hackaton.
     *
     * @param equipoId id del equipo a ser buscado.
     * @return la hackaton solicitada por medio de su id.
     */
    public HackatonEntity getHackaton(Long equipoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la hackaton del equipo con id = {0}", equipoId);
        HackatonEntity hackatonEntity = equipoPersistence.find(equipoId).getHackaton();
        LOGGER.log(Level.INFO, "Termina proceso de consultar la hackaton del equipo con id = {0}", equipoId);
        return hackatonEntity;
    }

    /**
     * Remplazar hackaton de un equipo 
     *
     * @param equipoId el id del equipo que se quiere actualizar.
     * @param hackatonId El id del nuebo hackaton asociado al equipo.
     * @return el nuevo hackaton asociado.
     */
    public HackatonEntity replaceHackaton(Long equipoId, Long hackatonId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el hackaton del equipo equipo con id = {0}", equipoId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        EquipoEntity equipoEntity = equipoPersistence.find(equipoId);
        equipoEntity.setHackaton(hackatonEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el hackaton con id = {0} al equipo con id = " + equipoId, hackatonId);
        return hackatonPersistence.find(hackatonId);
    }

    /**
     * Borrar el hackaton de un equipo
     *
     * @param equipoId El equipo que se desea borrar del hackaton.
     * @throws BusinessLogicException si el equipo no tiene hackaton
     */
    public void removeHackaton(Long equipoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el hackaton del equipo con id = {0}", equipoId);
        EquipoEntity equipoEntity = equipoPersistence.find(equipoId);
        if (equipoEntity.getHackaton() == null) {
            throw new BusinessLogicException("El equipo no tiene hackaton");
        }
        HackatonEntity hackatonEntity = hackatonPersistence.find(equipoEntity.getHackaton().getId());
        equipoEntity.setHackaton(null);
        hackatonEntity.getEquipos().remove(equipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el hackaton con id = {0} del equipo con id = " + equipoId, hackatonEntity.getId());
    }
}
