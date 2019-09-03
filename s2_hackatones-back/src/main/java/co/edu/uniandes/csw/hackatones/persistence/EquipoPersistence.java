/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ja.torresl
 */
@Stateless
public class EquipoPersistence {
    
    
     private static final Logger LOGGER = Logger.getLogger(EquipoPersistence.class.getName());
     
        @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
    
    public EquipoEntity create (EquipoEntity equipoEntity) {
        LOGGER.info("Creando un equipo nuevo");
        em.persist(equipoEntity);
        LOGGER.info("Equipo creado");
        return equipoEntity;
    }
    
    public EquipoEntity find(Long equipoId) {
        LOGGER.log(Level.INFO, "Consultando equipo con id={0}", equipoId);
        return em.find(EquipoEntity.class, equipoId);
    }
    
    public List<EquipoEntity> findAll(){
        LOGGER.log(Level.INFO, "Consultando todas las tecnologias");
        Query q = em.createQuery("Select u from EquipoEntity u");
        return q.getResultList();
    }
    
    public EquipoEntity update(EquipoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando equipo con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando equipo con id={0}", id);
        EquipoEntity entity = em.find(EquipoEntity.class, id);
        em.remove(entity);
    }
    
}
