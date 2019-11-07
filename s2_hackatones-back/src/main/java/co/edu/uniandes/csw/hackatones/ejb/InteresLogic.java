/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
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
public class InteresLogic {
    
    private static final Logger LOGGER = Logger.getLogger(InteresLogic.class.getName());

    @Inject
    private InteresPersistence persistence;

    /**
     * Se encarga de crear un Interes en la base de datos.
     *
     * @param interesEntity Objeto de InteresEntity con los datos nuevos
     * @return Objeto de InteresEntity con los datos nuevos y su ID.
     */
    public InteresEntity createInteres(InteresEntity interesEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del autor");
        InteresEntity newInteresEntity = persistence.create(interesEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del autor");
        return newInteresEntity;
    }

    /**
     * Obtiene la lista de los registros de Interes.
     *
     * @return Colección de objetos de InteresEntity.
     */
    public List<InteresEntity> getIntereses() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las interess");
        List<InteresEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los autores");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Interes a partir de su ID.
     *
     * @param interessId Identificador de la instancia a consultar
     * @return Instancia de InteresEntity con los datos del Interes consultado.
     */
    public InteresEntity getInteres(Long interessId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el autor con id = {0}", interessId);
        InteresEntity interesEntity = persistence.find(interessId);
        if (interesEntity == null) {
            LOGGER.log(Level.SEVERE, "La interes con el id = {0} no existe", interessId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la interes con id = {0}", interessId);
        return interesEntity;
    }

    /**
     * Actualiza la información de una instancia de Interes.
     *
     * @param interessId Identificador de la instancia a actualizar
     * @param interesEntity Instancia de InteresEntity con los nuevos datos.
     * @return Instancia de InteresEntity con los datos actualizados.
     */
    public InteresEntity updateInteres(Long interessId, InteresEntity interesEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor con id = {0}", interessId);
        InteresEntity newInteresEntity = persistence.update(interesEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el autor con id = {0}", interessId);
        return newInteresEntity;
    }

    /**
     * Elimina una instancia de Interes de la base de datos.
     *
     * @param interessId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteInteres(Long interessId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el autor con id = {0}", interessId);
        persistence.delete(interessId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0}", interessId);
    }
    

    
}
