/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.CredencialesEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.CredencialesPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ne.cardenas
 */
@Stateless
public class CredencialesLogic {
    
    private static final Logger LOGGER = Logger.getLogger(CredencialesLogic.class.getName());
    
    @Inject
    private CredencialesPersistence persistencia;
    
    public CredencialesEntity createCredenciales(CredencialesEntity entity) throws BusinessLogicException {
        
        if (entity.getCorreo() == null)
            throw new BusinessLogicException("El correo está vacío");
        else
        {
            if (persistencia.findByCorreo(entity.getCorreo()) != null)
                throw new BusinessLogicException("El correo ya existe");
            if (entity.getCorreo().isEmpty())
                throw new BusinessLogicException("El correo no tiene contenido");
        }
        
        if (entity.getContrasenha() == null)
            throw new BusinessLogicException("La contraseña está vacía");
        
        entity = persistencia.create(entity);
        return entity;
    }
    
    public List<CredencialesEntity> getAllCredenciales() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las credenciales");
        List<CredencialesEntity> books = persistencia.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos las credenciales");
        return books;
    }
    
    public CredencialesEntity getCredenciales(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las credenciales con id = {0}", id);
        CredencialesEntity entity = persistencia.find(id);
        if (entity == null) {
            LOGGER.log(Level.SEVERE, "Las credenciales con el id = {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar las credenciales con id = {0}", id);
        return entity;
    }
    
    public CredencialesEntity updateCredenciales(Long booksId, CredencialesEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar las credenciales con id = {0}", booksId);
        if (!validarCorreo(entity.getCorreo())) {
            throw new BusinessLogicException("El correo es inválido");
        }
        CredencialesEntity newEntity = persistencia.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar las credenciales con id = {0}", entity.getId());
        return newEntity;
    }
    
    public void deleteCredenciales(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar las credenciales con id = {0}", id);
        persistencia.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar las credenciales con id = {0}",id);
    }
    
    public boolean validarCorreo(String correo) {
        return !(correo == null || correo.isEmpty());
    }
}
