/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.CatalogoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ne.cardenas
 */
@Stateless
public class CatalogoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CatalogoPersistence.class.getName());
        
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
   
    public CatalogoEntity create(CatalogoEntity catalogo){
        LOGGER.log(Level.INFO, "Creando un catalogo nuevo");
        em.persist(catalogo);
        LOGGER.log(Level.INFO, "Catalogo creado");
        return catalogo;
    }
    
     public List<CatalogoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los catálogos");
        TypedQuery q = em.createQuery("select u from CatalogoEntity u", CatalogoEntity.class);
        return q.getResultList();
    }
     
    public CatalogoEntity find(Long catalogoId) {
        LOGGER.log(Level.INFO, "Consultando el catalogo con id={0}", catalogoId);
        return em.find(CatalogoEntity.class, catalogoId);
    }
    
    public CatalogoEntity update(CatalogoEntity catalogoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el catalogo con id={0}", catalogoEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el catalogo con id = {0}", catalogoEntity.getId());
        return em.merge(catalogoEntity);
    }

     public void delete(Long catalogoId) {
        LOGGER.log(Level.INFO, "Borrando el catalogo con id={0}", catalogoId);
        CatalogoEntity catalogoEntity = em.find(CatalogoEntity.class, catalogoId);
        em.remove(catalogoEntity);
    }
}
