/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.CredencialesEntity;
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
public class CredencialesPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CredencialesPersistence.class.getName());
    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
    
    public CredencialesEntity create (CredencialesEntity credencialesEntity) {
        LOGGER.info("Creando nuevas credenciales");
        em.persist(credencialesEntity);
        LOGGER.info("Credenciales creadas");
        return credencialesEntity;
    }
    
    public CredencialesEntity find(Long credencialesId) {
        LOGGER.log(Level.INFO, "Consultando credenciales con id={0}", credencialesId);
        return em.find(CredencialesEntity.class, credencialesId);
    }
    
    public CredencialesEntity findByCorreo(String correo) {
        LOGGER.log(Level.INFO, "Consultando credenciales con correo = ", correo);
        TypedQuery<CredencialesEntity> q
                = em.createQuery("select u from CredencialesEntity u where u.correo = :correo", CredencialesEntity.class);
        q = q.setParameter("correo", correo);
        List<CredencialesEntity> list = q.getResultList();
        CredencialesEntity entity;
        if (list == null)
            entity = null;
        else if (list.isEmpty())
            entity = null;
        else
            entity = list.get(0);
        LOGGER.log(Level.INFO, "Saliendo de consultar credenciales por correo ", correo);
        return entity;
    }
    
    public List<CredencialesEntity> findAll() {
        LOGGER.info("Consultando todos las credenciales");
        TypedQuery<CredencialesEntity> query = em.createQuery("select u from CredencialesEntity u", CredencialesEntity.class);
        return query.getResultList();
    }
    
    public CredencialesEntity update(CredencialesEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando credenciales con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando credenciales con id={0}", id);
        CredencialesEntity entity = em.find(CredencialesEntity.class, id);
        em.remove(entity);
    }
}
