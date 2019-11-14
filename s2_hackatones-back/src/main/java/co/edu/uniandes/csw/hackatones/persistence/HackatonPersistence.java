/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jc.higuera
 */
@Stateless
public class HackatonPersistence {
    
    
    private  static final Logger LOGGER = Logger.getLogger(HackatonPersistence.class.getName());
    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param hackaton objeto hackatonEntity que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public HackatonEntity create (HackatonEntity hackaton)
    {
        LOGGER.log(Level.INFO, "Creando una nueva hackaton");
        em.persist(hackaton);
        LOGGER.log(Level.INFO, "Hackaton creada");
        return hackaton;
    }
    
    /**
     * Busca si hay alguna hackaton con el id que se envía de argumento
     *
     * @param hackatonId: id correspondiente a la hackaton buscada.
     * @return una hackaton.
     */
    public HackatonEntity find(Long hackatonId){
        LOGGER.log(Level.INFO, "Consultando hackaton con id {0}", hackatonId);
        return em.find(HackatonEntity.class, hackatonId);
    }
    
    /**
     * Devuelve todas las hackatones de la base de datos.
     *
     * @return una lista con todas las hackatones que encuentre en la base de
     * datos, "select u from HackatonlEntity u" es como un "select * from
     * HackatonEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<HackatonEntity> findAll(){
        LOGGER.log(Level.INFO, "Consultando todas las hackatones");
        Query q = em.createQuery("Select u from HackatonEntity u");
        return q.getResultList();
    }
    
    /**
     * Actualiza una hackaton.
     *
     * @param hackatonEntity: la hackaton que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una hackaton con los cambios aplicados.
     */
    public HackatonEntity update(HackatonEntity hackaton){
        LOGGER.log(Level.INFO, "Actualizando hackaton con id = {0}", hackaton.getId());
        return em.merge(hackaton);
    }
    
    /**
     *
     * Borra una hackaton de la base de datos recibiendo como argumento el id
     * de la hackaton
     *
     * @param hackatonId: id correspondiente a la hackaton a borrar.
     */
    public void delete(Long hackatonId){
        LOGGER.log(Level.INFO, "Borrando hackaton con id = {0}", hackatonId);
        HackatonEntity entity = em.find(HackatonEntity.class, hackatonId);
        em.remove(entity);
    }
}
