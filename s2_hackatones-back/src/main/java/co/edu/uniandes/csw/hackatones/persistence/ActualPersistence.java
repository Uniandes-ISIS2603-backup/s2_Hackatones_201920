/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author a.pedraza
 */
@Stateless
public class ActualPersistence {
    
    @PersistenceContext(unitName = "Hackaton")
    protected EntityManager em;
   
    public ActualEntity create(ActualEntity actual){
        em.persist(actual);
        return actual;
    }
    
}
