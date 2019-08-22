/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;
import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author jd.monsalve
 */
@Stateless
public class LugarPersistence {
    
  @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager manager;
   
    public LugarEntity create(LugarEntity nombre){
        manager.persist(nombre);
        return nombre;
    }
            
    
}
