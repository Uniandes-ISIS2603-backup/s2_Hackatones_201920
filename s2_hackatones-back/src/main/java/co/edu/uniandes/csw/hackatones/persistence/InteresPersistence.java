package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ja.torresl
 */
@Stateless
public class InteresPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(InteresPersistence.class.getName());
         
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
    
   public InteresEntity create (InteresEntity interesEntity) {
        LOGGER.info("Creando un interes nuevo");
        em.persist(interesEntity);
        LOGGER.info("Interes creado");
        return interesEntity;
    }
    
    public InteresEntity find(Long interesId) {
        LOGGER.log(Level.INFO, "Consultando interes con id={0}", interesId);
        return em.find(InteresEntity.class, interesId);
    }
    
    public List<InteresEntity> findAll(){
        LOGGER.log(Level.INFO, "Consultando todos los interes");
        Query q = em.createQuery("Select u from InteresEntity u");
        return q.getResultList();
    }
    
    public InteresEntity update(InteresEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando interes con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando interes con id={0}", id);
        InteresEntity entity = em.find(InteresEntity.class, id);
        em.remove(entity);
    }
}
