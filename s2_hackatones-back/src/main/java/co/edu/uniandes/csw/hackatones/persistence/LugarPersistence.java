/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;
import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author jd.monsalve
 */
@Stateless
public class LugarPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(LugarPersistence.class.getName());
    
    
  @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager manager;
   
  
    public LugarEntity create(LugarEntity nombre){
    LOGGER.log(Level.INFO, "Creando un lugar nuevo");
    manager.persist(nombre);
    LOGGER.log(Level.INFO, "Lugar creado");
    return nombre;
    }
    
    public LugarEntity find (Long lugarId){
    LOGGER.log(Level.INFO, "Consultado lugar con id = {0}", lugarId);
    return manager.find(LugarEntity.class, lugarId);
    }
    
    public List<LugarEntity> findAll(){
    LOGGER.log(Level.INFO, "Consultando todos los lugares");
    Query q = manager.createQuery("select u from LugarEntity u");
    return q.getResultList();
    }
    
    public LugarEntity update (LugarEntity lugar){    
    LOGGER.log(Level.INFO, "Actualizando lugar con id = {0}", lugar.getId());
    return manager.merge(lugar);
    }
    
    public void delete(Long lugarId){
    LOGGER.log(Level.INFO, "Borrando lugar con id = = {0}", lugarId);
    LugarEntity entiy = manager.find(LugarEntity.class, lugarId);
    manager.remove(entiy);
    }
}