/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.CredencialesEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ne.cardenas
 */
@Stateless
public class CredencialesPersistence {
    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
    
    public CredencialesEntity create (CredencialesEntity credencialesEntity) {
        em.persist(credencialesEntity);
        return credencialesEntity;
    }
    
    public CredencialesEntity find(Long credencialesId) {
        return em.find(CredencialesEntity.class, credencialesId);
    }
    
    public List<CredencialesEntity> findAll() {
        TypedQuery<CredencialesEntity> query = em.createQuery("select u from CredencialesEntity u", CredencialesEntity.class);
        return query.getResultList();
    }
}
