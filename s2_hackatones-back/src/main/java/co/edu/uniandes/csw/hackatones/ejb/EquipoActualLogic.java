/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ActualPersistence;
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
public class EquipoActualLogic {
    
    
    private static final Logger LOGGER = Logger.getLogger(EquipoActualLogic.class.getName());

    @Inject
    private ActualPersistence actualPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private EquipoPersistence equipoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Agregar un autor a un premio
     *
     * @param equiposId El id premio a guardar
     * @param actualsId El id del autor al cual se le va a guardar el premio.
     * @return El premio que fue agregado al autor.
     */
    public ActualEntity addActual(Long actualsId, Long equiposId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el autor con id = {0} al premio con id = " + equiposId, actualsId);
        ActualEntity autorEntity = actualPersistence.find(actualsId);
        EquipoEntity equipoEntity = equipoPersistence.find(equiposId);
       // equipoEntity.setHackaton(autorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el autor con id = {0} al premio con id = " + equiposId, actualsId);
        return actualPersistence.find(actualsId);
    }

    /**
     *
     * Obtener un premio por medio de su id y el de su autor.
     *
     * @param equiposId id del premio a ser buscado.
     * @return el autor solicitada por medio de su id.
     */
    
    public ActualEntity getActual(Long equiposId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el autor del premio con id = {0}", equiposId);
        ActualEntity actualEntity = equipoPersistence.find(equiposId).getHackaton();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el autor del premio con id = {0}", equiposId);
        return actualEntity;
    }
    
 
    /**
     * Remplazar autor de un premio
     *
     * @param equiposId el id del premio que se quiere actualizar.
     * @param actualsId El id del nuebo autor asociado al premio.
     * @return el nuevo autor asociado.
     */
    
    public ActualEntity replaceActual(Long equiposId, Long actualsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor del premio premio con id = {0}", equiposId);
        ActualEntity autorEntity = actualPersistence.find(actualsId);
        EquipoEntity equipoEntity = equipoPersistence.find(equiposId);
        equipoEntity.setHackaton(autorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el autor con id = {0} al premio con id = " + equiposId, actualsId);
        return actualPersistence.find(actualsId);
    }
    

    /**
     * Borrar el autor de un premio
     *
     * @param equiposId El premio que se desea borrar del autor.
     * @throws BusinessLogicException si el premio no tiene autor
     */
    
    public void removeActual(Long equiposId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el autor del premio con id = {0}", equiposId);
        EquipoEntity equipoEntity = equipoPersistence.find(equiposId);
        if (equipoEntity.getHackaton() == null) {
            throw new BusinessLogicException("El premio no tiene autor");
        }
        ActualEntity actualEntity = actualPersistence.find(equipoEntity.getHackaton().getId());
        equipoEntity.setHackaton(null);
        actualEntity.getEquipos().remove(equipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0} del premio con id = " + equiposId, actualEntity.getId());
    }
    

}
