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
    
    public CatalogoEntity create (CatalogoEntity catalogoEntity) {
        LOGGER.info("Creando un catalogo nuevo");
        em.persist(catalogoEntity);
        LOGGER.info("Catalogo creado");
        return catalogoEntity;
    }
    
    public CatalogoEntity find(Long catalogoId) {
        LOGGER.log(Level.INFO, "Consultando catalogo con id={0}", catalogoId);
        return em.find(CatalogoEntity.class, catalogoId);
    }
    
    public CatalogoEntity update(CatalogoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando catalogo con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando catalogo con id={0}", id);
        CatalogoEntity entity = em.find(CatalogoEntity.class, id);
        em.remove(entity);
    }
}
