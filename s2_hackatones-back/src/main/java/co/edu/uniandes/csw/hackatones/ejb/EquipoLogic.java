/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;


import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.EquipoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.torresl
 */
 
@Stateless
public class EquipoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(EquipoLogic.class.getName());

    @Inject
    private EquipoPersistence persistence;

    /**
     * Se encarga de crear un Equipo en la base de datos.
     *
     * @param equipoEntity Objeto de EquipoEntity con los datos nuevos
     * @return Objeto de EquipoEntity con los datos nuevos y su ID.
     */
    public EquipoEntity createEquipo(EquipoEntity equipoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del autor");
        EquipoEntity newEquipoEntity = persistence.create(equipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del autor");
        return newEquipoEntity;
    }

    /**
     * Obtiene la lista de los registros de Equipo.
     *
     * @return Colección de objetos de EquipoEntity.
     */
    public List<EquipoEntity> getEquipoes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las equipos");
        List<EquipoEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los autores");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Equipo a partir de su ID.
     *
     * @param equiposId Identificador de la instancia a consultar
     * @return Instancia de EquipoEntity con los datos del Equipo consultado.
     */
    public EquipoEntity getEquipo(Long equiposId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el autor con id = {0}", equiposId);
        EquipoEntity equipoEntity = persistence.find(equiposId);
        if (equipoEntity == null) {
            LOGGER.log(Level.SEVERE, "La equipo con el id = {0} no existe", equiposId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la equipo con id = {0}", equiposId);
        return equipoEntity;
    }

    /**
     * Actualiza la información de una instancia de Equipo.
     *
     * @param equiposId Identificador de la instancia a actualizar
     * @param equipoEntity Instancia de EquipoEntity con los nuevos datos.
     * @return Instancia de EquipoEntity con los datos actualizados.
     */
    public EquipoEntity updateEquipo(Long equiposId, EquipoEntity equipoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor con id = {0}", equiposId);
        EquipoEntity newEquipoEntity = persistence.update(equipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el autor con id = {0}", equiposId);
        return newEquipoEntity;
    }

    /**
     * Elimina una instancia de Equipo de la base de datos.
     *
     * @param equiposId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteEquipo(Long equiposId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el autor con id = {0}", equiposId);
        persistence.delete(equiposId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0}", equiposId);
    }
    
}
