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
public class PatrocinadorLogic 
{
    /**
     * La persistencia de la logica del patrocinador
     */
    @Inject
    private PatrocinadorPersistence persistence;
    
    /**
     * El logger de la persistencia
     */
    private static final Logger LOGGER = Logger.getLogger(PatrocinadorLogic.class.getName());

    /**
     * Crea un patrocinador a partir de un entity que entra por parametro
     * @param entity
     * @return
     * @throws BusinessLogicException Si alguno de los atriburtos del patrocinador es vacio o nulo.
     */
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
    
    /**
     * Actializa los atributos del patrociandor especifico a partir de un id
     * @param id
     * @param entity
     * @return 
     */
    public PatrocinadorEntity updatePatrocinador(Long id, PatrocinadorEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el Patrocinador con id = {0}", id);
        PatrocinadorEntity nuevoEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el Patrocinador con id = {0}", id);
        return nuevoEntity;
    }
    
    /**
     * Borra un patrocinador a partir de su id
     * @param id
     * @throws BusinessLogicException 
     */
    public void deletePatrocinador(Long id){
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el patrocinador con id = {0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el patrocinador con id = {0}", id);
    }
    
    /**
     * Busca y devuelve un patrocinador especifico a partir de su id
     * @param id
     * @return 
     */
    public PatrocinadorEntity getPatrocinador(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el patrociandor con id = {0}", id);
        PatrocinadorEntity entity = persistence.find(id);
        if (entity == null) {
            LOGGER.log(Level.SEVERE, "El patrocinador con el id = {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el patrocinador con id = {0}", id);
        return entity;
    }
    
    /**
     * Devuelve una lista de todos los patrocinadores que se encuentren en la base de datos.
     * @return 
     */
    public List<PatrocinadorEntity> getPatrocinadores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los patrocinadores");
        List<PatrocinadorEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los patrocinadores");
        return lista;
    }
}
