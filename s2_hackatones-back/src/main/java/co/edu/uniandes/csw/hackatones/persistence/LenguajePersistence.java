/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
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
public class LenguajePersistence {
    
    /**
     * Logger de la persistencia de lenguaje
     */
    private static final Logger LOGGER = Logger.getLogger(LenguajePersistence.class.getName());
    
    /**
     * Entity manager de la persistencia de lenguaje
     */
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;

    /**
     * Crea un nuevo lenguaje en la base de datos
     * @param lenguaje
     * @return 
     */
    public LenguajeEntity create(LenguajeEntity lenguaje)
    {
        LOGGER.info("Creando un lenguaje nuevo");
        em.persist(lenguaje);
        LOGGER.info("Lenguaje creado");
        return lenguaje;
    }
    
    /**
     * Busca un lenguaje en la base de datos a partir de su id
     * @param lenguajeId
     * @return 
     */
    public LenguajeEntity find(Long lenguajeId) {
        LOGGER.log(Level.INFO, "Consultando lenguaje con id={0}", lenguajeId);
        return em.find(LenguajeEntity.class, lenguajeId);
    } 
    
    /**
     * Actualiza en la base de datos los atributos de un lenguaje dado por su id
     * @param entity
     * @return 
     */
    public LenguajeEntity update(LenguajeEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando lenguaje con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * Borra un lenguaje dado por su id
     * @param lenguajeId 
     */
    public void delete(Long lenguajeId) {
        LOGGER.log(Level.INFO, "Borrando lenguaje con id={0}", lenguajeId);
        LenguajeEntity entity = em.find(LenguajeEntity.class, lenguajeId);
        em.remove(entity);
    }
    
    /**
     * Devuelve todos los lenguajes que esten en la base de datos
     * @return 
     */
    public List<LenguajeEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas los lenguajes");
        Query q = em.createQuery("select u from LenguajeEntity u");
        return q.getResultList();
    }
}
