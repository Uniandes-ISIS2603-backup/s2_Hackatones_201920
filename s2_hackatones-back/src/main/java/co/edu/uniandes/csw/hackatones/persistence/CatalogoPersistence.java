/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.CatalogoEntity;
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
public class CatalogoPersistence {
    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
    
    public CatalogoEntity create (CatalogoEntity catalogoEntity) {
        em.persist(catalogoEntity);
        return catalogoEntity;
    }
    
    public CatalogoEntity find(Long catalogoId) {
        return em.find(CatalogoEntity.class, catalogoId);
    }
    
    public List<CatalogoEntity> findAll() {
        TypedQuery<CatalogoEntity> query = em.createQuery("select u from CatalogoEntity u", CatalogoEntity.class);
        return query.getResultList();
    }
    
}
