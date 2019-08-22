/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Estudiante
 */
@Stateless
public class HackatonPersistence {
    
    @PersistenceContext(unitName = "hackaton")
    protected EntityManager em;
    
    public HackatonEntity create (HackatonEntity hackaton)
    {
        em.persist(hackaton);
        return hackaton;
    }
}
