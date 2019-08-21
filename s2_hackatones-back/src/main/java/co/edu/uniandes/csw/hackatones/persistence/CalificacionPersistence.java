/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import javax.ejb.Stateless;
import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author a.pedraza
 */
@Stateless
public class CalificacionPersistence {
    
    @PersistenceContext(unitName = "HackatonPU")
    protected EntityManager em;
   
    public CalificacionEntity create(CalificacionEntity calificacion){
        em.persist(calificacion);
        return calificacion;
    }
    
}
