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
    
    @Inject
    private LenguajePersistence persistence;
    
    private static final Logger LOGGER = Logger.getLogger(LenguajeLogic.class.getName());

    
    public LenguajeEntity createLenguaje(LenguajeEntity entity) throws BusinessLogicException
    {
        if(entity.getName().equals("") || entity.getName() == null)
        {
            throw new BusinessLogicException("El nombre del lenguaje esta vacío");
        }
        entity = persistence.create(entity);
        return entity;
    }
    
    
    public LenguajeEntity updateLenguaje(Long id, LenguajeEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el lenguaje con id = {0}", id);
        LenguajeEntity nuevoEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el lenguaje con id = {0}", id);
        return nuevoEntity;
    }
    
    public void deleteLenguaje(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el lenguaje con id = {0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el lenguaje con id = {0}", id);
    }
    
    public LenguajeEntity getLenguaje(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el lenguaje con id = {0}", id);
        LenguajeEntity entity = persistence.find(id);
        if (entity == null) {
            LOGGER.log(Level.SEVERE, "El lenguaje con el id = {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el lenguaje con id = {0}", id);
        return entity;
    }
    
    public List<LenguajeEntity> getLenguajes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los Lenguajes");
        List<LenguajeEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los Lenguajes");
        return lista;
    }
}
