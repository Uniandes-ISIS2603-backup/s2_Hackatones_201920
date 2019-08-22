package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ja.torresl
 */
@Stateless
public class InteresPersistence {
    
    @PersistenceContext(unitName = "HackatonPU")
    protected EntityManager em;
    
    public InteresEntity create(InteresEntity interes){
       em.persist(interes);
       
       return interes;
    }
}
