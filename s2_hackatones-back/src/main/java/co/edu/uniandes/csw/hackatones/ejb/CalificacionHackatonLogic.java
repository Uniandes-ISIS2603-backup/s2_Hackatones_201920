/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author s.estupinan
 */
public class CalificacionHackatonLogic {
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionHackatonLogic.class.getName());

    @Inject
    private CalificacionPersistence calificacionPersistence;

    @Inject
    private HackatonPersistence hackatonPersistence;

    /**
     * Remplazar la hackaton de un calificacion.
     *
     * @param calificacionId id del calificacion que se quiere actualizar.
     * @param hackatonsId El id de la hackaton que se será del calificacion.
     * @return el nuevo calificacion.
     */
    public CalificacionEntity replacehackaton(Long calificacionId, Long hackatonsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar calificacion con id = {0}", calificacionId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonsId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionId);
        calificacionEntity.setHackaton(hackatonEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar calificacion con id = {0}", calificacionEntity.getId());
        return calificacionEntity;
    }

    /**
     * Borrar un calificacion de una hackaton. Este metodo se utiliza para borrar la
     * relacion de un calificacion.
     *
     * @param calificacionId El calificacion que se desea borrar de la hackaton.
     */
    public void removehackaton(Long calificacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la hackaton del calificacion con id = {0}", calificacionId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(calificacionEntity.getHackaton().getId());
        calificacionEntity.setHackaton(null);
        hackatonEntity.getCalificaciones().remove(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la hackaton del calificacion con id = {0}", calificacionEntity.getId());
    }
}
