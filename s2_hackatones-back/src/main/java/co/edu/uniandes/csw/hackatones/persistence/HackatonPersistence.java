/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jc.higuera
 */
@Stateless
public class HackatonPersistence {
    
    private final static Logger LOGGER = Logger.getLogger(HackatonPersistence.class.getName());
    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
    
    public HackatonEntity create (HackatonEntity hackaton)
    {
        LOGGER.log(Level.INFO, "Creando una nueva hackaton");
        em.persist(hackaton);
        LOGGER.log(Level.INFO, "Hackaton creada");
        return hackaton;
    }
    
    public HackatonEntity find(Long hackatonId){
        LOGGER.log(Level.INFO, "Consultando hackaton con id {0}", hackatonId);
        return em.find(HackatonEntity.class, hackatonId);
    }
    
    public List<HackatonEntity> findAll(){
        LOGGER.log(Level.INFO, "Consultando todas las hackatones");
        Query q = em.createQuery("Select u from HackatonEntity u");
        return q.getResultList();
    }
    
    public HackatonEntity update(HackatonEntity hackaton){
        LOGGER.log(Level.INFO, "Actualizando hackaton con id = {0}", hackaton.getId());
        return em.merge(hackaton);
    }
    
    public void delete(Long hackatonId){
        LOGGER.log(Level.INFO, "Borrando hackaton con id = {0}", hackatonId);
        HackatonEntity entity = em.find(HackatonEntity.class, hackatonId);
        em.remove(entity);
    }
}
