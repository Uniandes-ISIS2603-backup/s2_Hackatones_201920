/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author a.pedraza
 */
@Stateless
public class ProximaPersistence {
    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
   
    public ProximaEntity create(ProximaEntity proxima){
        em.persist(proxima);
        return proxima;
    }
    
}
