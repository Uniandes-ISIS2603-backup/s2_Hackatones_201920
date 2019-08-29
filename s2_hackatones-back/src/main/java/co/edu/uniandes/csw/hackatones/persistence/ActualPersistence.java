/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author a.pedraza
 */
@Stateless
public class ActualPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(ActualPersistence.class.getName());

    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
   
    public ActualEntity create(ActualEntity actual){
        LOGGER.log(Level.INFO, "Creando una actual nueva");
        em.persist(actual);
        LOGGER.log(Level.INFO, "Actual creada");
        return actual;
    }
    
     public List<ActualEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las actuales");
        Query q = em.createQuery("select u from ActualEntity u");
        return q.getResultList();
    }
     
    public ActualEntity find(Long califcacionId) {
        LOGGER.log(Level.INFO, "Consultando la actual con id={0}", califcacionId);
        return em.find(ActualEntity.class, califcacionId);
    }
    
    public ActualEntity update(ActualEntity actualEntity) {
        LOGGER.log(Level.INFO, "Actualizando la actual con id={0}", actualEntity.getId());
        return em.merge(actualEntity);
    }

     public void delete(Long actualId) {
        LOGGER.log(Level.INFO, "Borrando la actual con id={0}", actualId);
        ActualEntity bookEntity = em.find(ActualEntity.class, actualId);
        em.remove(bookEntity);
    }
    
}
