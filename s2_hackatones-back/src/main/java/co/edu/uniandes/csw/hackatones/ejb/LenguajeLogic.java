/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.LenguajePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Santiago Estupinan
 */
@Stateless
public class LenguajeLogic 
{
    /**
     * Representa la persistencia del lenguaje
     */
    @Inject
    private LenguajePersistence persistence;
    
    /**
     * El logger de la clase
     */
    private static final Logger LOGGER = Logger.getLogger(LenguajeLogic.class.getName());

    /**
     * Crea un nuevo lenguaje y la persiste en la base de datos a partir de una entidad
     * @param entity La nueva entidad a ser persistida base de datos
     * @return El nuevo lenguaje en forma de entidad
     * @throws BusinessLogicException Si el no,bre del lenguaje esta vacio o es nulo
     */
    public LenguajeEntity createLenguaje(LenguajeEntity entity) throws BusinessLogicException
    {
        if(entity.getName().equals("") || entity.getName() == null)
        {
            throw new BusinessLogicException("El nombre del lenguaje esta vacío");
        }
        entity = persistence.create(entity);
        return entity;
    }
    
    /**
     * Hace un update a los atributos de un lenguaje que tiene un id especifico
     * @param id del lenguaje a cambiar
     * @param entity datos nuevos del lenguaje a cambiar
     * @return lenguaje cambiado
     */
    public LenguajeEntity updateLenguaje(Long id, LenguajeEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el lenguaje con id = {0}", id);
        LenguajeEntity nuevoEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el lenguaje con id = {0}", id);
        return nuevoEntity;
    }
    
    /**
     * Elimina un lenguaje a partir de su id
     * @param id del lenguaje que se quiere eliminar
     * @throws BusinessLogicException Si no existe un lenguaje con el id
     */
    public void deleteLenguaje(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el lenguaje con id = {0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el lenguaje con id = {0}", id);
    }
    
    /**
     * Devuelve un lenguaje buscado por si id
     * @param id del lenguaje buscado
     * @return lenguaje buscado
     */
    public LenguajeEntity getLenguaje(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el lenguaje con id = {0}", id);
        LenguajeEntity entity = persistence.find(id);
        if (entity == null) {
            LOGGER.log(Level.SEVERE, "El lenguaje con el id = {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el lenguaje con id = {0}", id);
        return entity;
    }
    
    /**
     * Devuelve todos los lenguajes en la base de datos
     * @return todos los lenguajes en la base de datos
     */
    public List<LenguajeEntity> getLenguajes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los Lenguajes");
        List<LenguajeEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los Lenguajes");
        return lista;
    }
}
