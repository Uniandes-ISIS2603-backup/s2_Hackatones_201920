/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author Santiago Estupinan
 */
@Stateless
public class UsuarioPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());

    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;

    
    public UsuarioEntity create(UsuarioEntity usuario)
    {
        LOGGER.info("Creando un usuario nuevo");
        em.persist(usuario);
        LOGGER.info("Usuario creado");
        return usuario;
    }
        
    public UsuarioEntity find(Long usuarioId) {
        LOGGER.log(Level.INFO, "Consultando usuario con id={0}", usuarioId);
        return em.find(UsuarioEntity.class, usuarioId);
    } 
    
    public UsuarioEntity update(UsuarioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando usuario con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public void delete(Long usuarioId) {
        LOGGER.log(Level.INFO, "Borrando usuario con id={0}", usuarioId);
        UsuarioEntity entity = em.find(UsuarioEntity.class, usuarioId);
        em.remove(entity);
    }
    
    public List<UsuarioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas los usuarios");
        Query q = em.createQuery("select u from UsuarioEntity u");
        return q.getResultList();
    }
          
}
