/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Santiago Estupinan
 */
@Stateless
public class PatrocinadorPersistence 
{
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(PatrocinadorPersistence.class.getName());
    
    public PatrocinadorEntity create(PatrocinadorEntity patrocinador)
    {
        LOGGER.info("Creando un patrocinador nuevo");
        em.persist(patrocinador);
        LOGGER.info("Patrocinador creado");
        return patrocinador;
    }
     
    public PatrocinadorEntity find(Long patrocinadorId) {
        LOGGER.log(Level.INFO, "Consultando patrocinador con id={0}", patrocinadorId);
        return em.find(PatrocinadorEntity.class, patrocinadorId);
    } 
    
    public PatrocinadorEntity update(PatrocinadorEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando patrocinador con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public void delete(Long patrocinadorId) {
        LOGGER.log(Level.INFO, "Borrando patrocinador con id={0}", patrocinadorId);
        PatrocinadorEntity entity = em.find(PatrocinadorEntity.class, patrocinadorId);
        em.remove(entity);
    }
    
    public List<PatrocinadorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los patrocinadores");
        Query q = em.createQuery("select u from PatrocinadorEntity u");
        return q.getResultList();
    }
                 
            
            
}
