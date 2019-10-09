/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import javax.ejb.Stateless;
import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author a.pedraza
 */
@Stateless
public class CalificacionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
   
    public CalificacionEntity create(CalificacionEntity calificacion){
        LOGGER.log(Level.INFO, "Creando una calificacion nueva");
        em.persist(calificacion);
        LOGGER.log(Level.INFO, "Calificacion creada");
        return calificacion;
    }
    
     public List<CalificacionEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las calificaciones");
        Query q = em.createQuery("select u from CalificacionEntity u");
        return q.getResultList();
    }
     
    public CalificacionEntity find(Long calificacionId) {
        LOGGER.log(Level.INFO, "Consultando la calificacion con id={0}", calificacionId);
        return em.find(CalificacionEntity.class, calificacionId);
    }
    
    public CalificacionEntity update(CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Actualizando la calificacion con id={0}", calificacionEntity.getId());
        return em.merge(calificacionEntity);
    }

     public void delete(Long calificacionId) {
        LOGGER.log(Level.INFO, "Borrando la calificacion con id={0}", calificacionId);
        CalificacionEntity calificacionEntity = em.find(CalificacionEntity.class, calificacionId);
        em.remove(calificacionEntity);
    }
}
