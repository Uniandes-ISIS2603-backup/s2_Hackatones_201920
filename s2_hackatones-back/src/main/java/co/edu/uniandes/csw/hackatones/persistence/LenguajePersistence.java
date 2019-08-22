/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Santiago Estupinan
 */
@Stateless
public class LenguajePersistence {
    
    @PersistenceContext(unitName = "hackatones")
    protected EntityManager em;

    
    public LenguajeEntity create(LenguajeEntity lenguaje)
    {
        em.persist(lenguaje);
        return lenguaje;
    }
     
}
