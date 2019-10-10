/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.TecnologiaPersistence;
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
public class TecnologiaLogic {
    
  private static final Logger LOGGER = Logger.getLogger(TecnologiaLogic.class.getName());

    @Inject
    private TecnologiaPersistence persistence;

    /**
     * Se encarga de crear un Tecnologia en la base de datos.
     *
     * @param tecnologiaEntity Objeto de TecnologiaEntity con los datos nuevos
     * @return Objeto de TecnologiaEntity con los datos nuevos y su ID.
     */
    public TecnologiaEntity createTecnologia(TecnologiaEntity tecnologiaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del autor");
        TecnologiaEntity newTecnologiaEntity = persistence.create(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del autor");
        return newTecnologiaEntity;
    }

    /**
     * Obtiene la lista de los registros de Tecnologia.
     *
     * @return Colección de objetos de TecnologiaEntity.
     */
    public List<TecnologiaEntity> getTecnologias() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las tecnologias");
        List<TecnologiaEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los autores");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Tecnologia a partir de su ID.
     *
     * @param tecnologiasId Identificador de la instancia a consultar
     * @return Instancia de TecnologiaEntity con los datos del Tecnologia consultado.
     */
    public TecnologiaEntity getTecnologia(Long tecnologiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el autor con id = {0}", tecnologiasId);
        TecnologiaEntity tecnologiaEntity = persistence.find(tecnologiasId);
        if (tecnologiaEntity == null) {
            LOGGER.log(Level.SEVERE, "La tecnologia con el id = {0} no existe", tecnologiasId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la tecnologia con id = {0}", tecnologiasId);
        return tecnologiaEntity;
    }

    /**
     * Actualiza la información de una instancia de Tecnologia.
     *
     * @param tecnologiasId Identificador de la instancia a actualizar
     * @param tecnologiaEntity Instancia de TecnologiaEntity con los nuevos datos.
     * @return Instancia de TecnologiaEntity con los datos actualizados.
     */
    public TecnologiaEntity updateTecnologia(Long tecnologiasId, TecnologiaEntity tecnologiaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor con id = {0}", tecnologiasId);
        TecnologiaEntity newTecnologiaEntity = persistence.update(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el autor con id = {0}", tecnologiasId);
        return newTecnologiaEntity;
    }

    /**
     * Elimina una instancia de Tecnologia de la base de datos.
     *
     * @param tecnologiasId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteTecnologia(Long tecnologiasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el autor con id = {0}", tecnologiasId);
        persistence.delete(tecnologiasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0}", tecnologiasId);
    }
    
}
