/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;


import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ja.torresl
 */
@Stateless
public class TecnologiaPersistence {
    
    
    private static final Logger LOGGER = Logger.getLogger(TecnologiaPersistence.class.getName());
    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
    
    public TecnologiaEntity create (TecnologiaEntity tecnologiaEntity) {
        LOGGER.info("Creando una tecnologia nueva");
        em.persist(tecnologiaEntity);
        LOGGER.info("Tecnologia creado");
        return tecnologiaEntity;
    }
    
    public TecnologiaEntity find(Long tecnologiaId) {
        LOGGER.log(Level.INFO, "Consultando tecnologia con id={0}", tecnologiaId);
        return em.find(TecnologiaEntity.class, tecnologiaId);
    }
    
    public List<TecnologiaEntity> findAll(){
        LOGGER.log(Level.INFO, "Consultando todas las tecnologias");
        Query q = em.createQuery("Select u from TecnologiaEntity u");
        return q.getResultList();
    }
    
    public TecnologiaEntity update(TecnologiaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando tecnologia con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando tecnologia con id={0}", id);
        TecnologiaEntity entity = em.find(TecnologiaEntity.class, id);
        em.remove(entity);
    }
    
}
