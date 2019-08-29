/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
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
public class ProximaPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(ProximaPersistence.class.getName());

    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
   
    public ProximaEntity create(ProximaEntity proxima){
        LOGGER.log(Level.INFO, "Creando una proxima nueva");
        em.persist(proxima);
        LOGGER.log(Level.INFO, "Proxima creada");
        return proxima;
    }
    
     public List<ProximaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las proximas");
        Query q = em.createQuery("select u from ProximaEntity u");
        return q.getResultList();
    }
     
    public ProximaEntity find(Long proximaId) {
        LOGGER.log(Level.INFO, "Consultando la proxima con id={0}", proximaId);
        return em.find(ProximaEntity.class, proximaId);
    }
    
    public ProximaEntity update(ProximaEntity proximaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la proxima con id={0}", proximaEntity.getId());
        return em.merge(proximaEntity);
    }

     public void delete(Long proximaId) {
        LOGGER.log(Level.INFO, "Borrando la proxima con id={0}", proximaId);
        ProximaEntity bookEntity = em.find(ProximaEntity.class, proximaId);
        em.remove(bookEntity);
    }
    
}
