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
    /**
     * Logger de la persistencia de usuario
     */
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());
    
    /**
     * Entity manager de la persistencia de usuario
     */
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;

    /**
     * Crea un nuevo usuario en la base de datos
     * @param usuario
     * @return 
     */
    public UsuarioEntity create(UsuarioEntity usuario)
    {
        LOGGER.info("Creando un usuario nuevo");
        em.persist(usuario);
        LOGGER.info("Usuario creado");
        return usuario;
    }
    
    /**
     * Encuentra un usuario a partir de su id
     * @param usuarioId
     * @return 
     */
    public UsuarioEntity find(Long usuarioId) {
        LOGGER.log(Level.INFO, "Consultando usuario con id={0}", usuarioId);
        return em.find(UsuarioEntity.class, usuarioId);
    } 
    
    /**
     * Actualiza los atributos en la base de datos de un usuario dado por su id
     * @param entity
     * @return 
     */
    public UsuarioEntity update(UsuarioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando usuario con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Borra un usuaruo a partir de un id dado
     * @param usuarioId 
     */
    public void delete(Long usuarioId) {
        LOGGER.log(Level.INFO, "Borrando usuario con id={0}", usuarioId);
        UsuarioEntity entity = em.find(UsuarioEntity.class, usuarioId);
        em.remove(entity);
    }
    
    /**
     * Devuelve todos los usuarios que se encuentren en la base de datos
     * @return 
     */
    public List<UsuarioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas los usuarios");
        Query q = em.createQuery("select u from UsuarioEntity u");
        return q.getResultList();
    }
          
}
