/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.UsuarioPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Santiago Estupinan
 */
@Stateless
public class UsuarioLogic {
    @Inject
    private UsuarioPersistence persistence;
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException
    {
        if(entity.getNombre().equals("") || entity.getNombre() == null)
        {
            throw new BusinessLogicException("El nombre del usuario esta vacío");
        }
        entity = persistence.create(entity);
        return entity;
    }
    
        
    public UsuarioEntity updateUsuario(Long id, UsuarioEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el usuario con id = {0}", id);
        UsuarioEntity nuevoEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el usuario con id = {0}", id);
        return nuevoEntity;
    }
    
    public void deleteUsuario(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el usuario con id = {0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el usuario con id = {0}", id);
    }
    
    public UsuarioEntity getUsuario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el usuario con id = {0}", id);
        UsuarioEntity entity = persistence.find(id);
        if (entity == null) {
            LOGGER.log(Level.SEVERE, "El usuario con el id = {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el usuario con id = {0}", id);
        return entity;
    }
    
}
