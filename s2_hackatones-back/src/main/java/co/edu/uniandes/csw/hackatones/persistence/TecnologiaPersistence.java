/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ja.torresl
 */
@Stateless
public class TecnologiaPersistence {
    
            @PersistenceContext(unitName = "HackatonPU")
    protected EntityManager em;
    
    public TecnologiaEntity create(TecnologiaEntity tecnologia){
       em.persist(tecnologia);
       
       return tecnologia;
    }
}
