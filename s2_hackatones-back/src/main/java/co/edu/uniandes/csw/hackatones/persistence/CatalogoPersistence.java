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

/**
 *
 * @author ne.cardenas
 */
@Stateless
public class CatalogoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CatalogoPersistence.class.getName());
    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
    
    public CatalogoEntity create (CatalogoEntity catalogoEntity) {
        LOGGER.log(Level.INFO, "Creando un catalogo nueva");
        em.persist(catalogoEntity);
        LOGGER.log(Level.INFO, "Catologo creada");
        return catalogoEntity;
    }
    
    public CatalogoEntity find(Long catalogoId) {
        LOGGER.log(Level.INFO, "Consultando catalogo con id={0}", catalogoId);
        return em.find(CatalogoEntity.class, catalogoId);
    }
    
    public CatalogoEntity update(CatalogoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando catalogo con id={0}", entity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la editorial con id = {0}", entity.getId());
        return em.merge(entity);
    }
    
    public List<CatalogoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los catalogos");
        Query q = em.createQuery("select u from CatalogoEntity u");
        return q.getResultList();
    }
    
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando catalogo con id={0}", id);
        CatalogoEntity entity = em.find(CatalogoEntity.class, id);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la editorial con id = {0}", id);
    }
}
