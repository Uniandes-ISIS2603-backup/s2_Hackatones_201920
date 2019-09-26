/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.PatrocinadorPersistence;
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
public class PatrocinadorLogic {
    @Inject
    private PatrocinadorPersistence persistence;
    
    private static final Logger LOGGER = Logger.getLogger(PatrocinadorLogic.class.getName());

    
    public PatrocinadorEntity createPatrocinador(PatrocinadorEntity entity) throws BusinessLogicException
    {
        if(entity.getNombre().equals("") || entity.getNombre() == null)
        {
            throw new BusinessLogicException("El nombre del patrocinador esta vacío");
        }
        else if(entity.getId() == null)
        {
            throw new BusinessLogicException("El id del patrocinador esta vacío");

        }
        else if(entity.getDescripcion() == null)
        {
            throw new BusinessLogicException("La descripcion del patrocinador esta vacio");

        }
        else if(entity.getInfoAdicional() == null)
        {
            throw new BusinessLogicException("La informacion adicional del patrocinador esta vacío");

        }
        else if(entity.getUbicacion() == null)
        {
            throw new BusinessLogicException("La ubicacion del patrocinador esta vacío");

        }
        entity = persistence.create(entity);
        return entity;
    }
    
    public PatrocinadorEntity updatePatrocinador(Long id, PatrocinadorEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el Patrocinador con id = {0}", id);
        PatrocinadorEntity nuevoEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el Patrocinador con id = {0}", id);
        return nuevoEntity;
    }
    
    public void deletePatrocinador(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el patrocinador con id = {0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el patrocinador con id = {0}", id);
    }
    
    public PatrocinadorEntity getPatrocinador(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el patrociandor con id = {0}", id);
        PatrocinadorEntity entity = persistence.find(id);
        if (entity == null) {
            LOGGER.log(Level.SEVERE, "El patrocinador con el id = {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el patrocinador con id = {0}", id);
        return entity;
    }
    
    
}
