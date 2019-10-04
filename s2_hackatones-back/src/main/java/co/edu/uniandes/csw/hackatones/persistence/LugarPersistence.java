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
 *Clase que maneja la persistencia para la clase Lugar
 * @author jd.monsalve
 */
@Stateless
public class LugarPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(LugarPersistence.class.getName());

  @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager manager;
   
  
  /**
   * Método que persiste una entidad en la base de datos
   * @param nombre el lugar que se creará en la base de datos
   * @return devuelve la entidad creada
   */
    public LugarEntity create(LugarEntity nombre){
    LOGGER.log(Level.INFO, "Creando un lugar nuevo");
    manager.persist(nombre);
    LOGGER.log(Level.INFO, "Lugar creado");
    return nombre;
    }
    /**
     * Encuentra un lugar por su identificador
     * @param lugarId el identificador del lugar que se desea buscar
     * @return retorna el lugar encontrado
     */
    public LugarEntity find (Long lugarId){
    LOGGER.log(Level.INFO, "Consultado lugar con id = {0}", lugarId);
    return manager.find(LugarEntity.class, lugarId);
    }
    
    /**
     * Metodo que devuelve todos los lugares encontrados en la base de datos
     * @return lista de lugares encontrados
     */
    public List<LugarEntity> findAll(){
    LOGGER.log(Level.INFO, "Consultando todos los lugares");
    Query q = manager.createQuery("select u from LugarEntity u");
    return q.getResultList();
    }
    /**
     * actualiza un lugar
     * @param lugar, lugar que viene con los nuevos cambios
     * @return  el lugar con los cambios aplicados
     */
    public LugarEntity update (LugarEntity lugar){    
    LOGGER.log(Level.INFO, "Actualizando lugar con id = {0}", lugar.getId());
    return manager.merge(lugar);
    }
    /**
     * elimina el lugar de la base de datos
     * @param lugarId, lugar que se desea eliminar 
     */
    public void delete(Long lugarId){
    LOGGER.log(Level.INFO, "Borrando lugar con id = = {0}", lugarId);
    LugarEntity entiy = manager.find(LugarEntity.class, lugarId);
    manager.remove(entiy);
    }
}